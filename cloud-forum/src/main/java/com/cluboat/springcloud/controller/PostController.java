package com.cluboat.springcloud.controller;



import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.entity.*;
import com.cluboat.springcloud.entity.dto.PopularPostDTO;
import com.cluboat.springcloud.entity.dto.PostListDTO;
import com.cluboat.springcloud.entity.dto.PostTagDTO;
import com.cluboat.springcloud.entity.param.*;
import com.cluboat.springcloud.mapper.CommentMapper;
import com.cluboat.springcloud.service.CommentService;
import com.cluboat.springcloud.service.LikesService;
import com.cluboat.springcloud.service.PostService;
import com.cluboat.springcloud.service.PostTagService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
//import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/post")
public class PostController {
    public JSONObject HttpGet(String url, String param){
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url + "/" + param);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            String content = "";
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                //请求体内容
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("内容：" + content);
            }
            if (response != null) {
                response.close();
            }
            //相当于关闭浏览器
            httpclient.close();
            return new JSONObject(content);
        } catch (Exception e){
            return null;
        }
    }

//    @GetMapping("/{word}")
//    public CommonResult Test(@PathVariable String word){
//        JSONObject result = HttpGet("http://124.70.163.146:8001/ai/illegalCheck", word);
//        return new CommonResult(200, "获取成功", result);
//    }

    @Resource
    private PostService postService;

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private PostTagService postTagService;

    @Resource
    private CommentService commentService;

    @Resource
    private LikesService likesService;




//    Put中没有判断加的tag是否是该club下有的tag
    @PostMapping
    public CommonResult InsertPost(@RequestBody PostAddParam param) {
        try {
            RestTemplate restTemplate1 = new RestTemplate();
            //AI判定其是否违规
            JSONObject result = HttpGet("http://124.70.163.146:8001/ai/illegalCheck", param.getPostContent());
//            JSONObject result = restTemplate1.getForObject("http://124.70.163.146:8001/ai/illegalCheck" + param.getPostContent(), JSONObject.class);
            //如果AI那边没拿到东西，则添加失败
            if (result == null){
                return new CommonResult(401, "系统繁忙，请稍后重试");
            }
            JSONObject data = result.getJSONObject("data");
            Integer illegalScore = data.getInt("illegalScore");
            if (illegalScore > 1){
                return new CommonResult(402, "输入的帖子内容不合规，请稍后重试");
            }
            System.out.println(data.getStr("content"));
            //AI那边拿到结果了才能继续
            //先创建相应的记录，以及tag的记录
            PostEntity post = new PostEntity();
            post.setPostTime(new Timestamp(System.currentTimeMillis()));
            post.setIsTop((byte)0);
            post.setPostTitle(param.getPostTitle());
            post.setPostContent(param.getPostContent());
            post.setClubId(param.getClubId());
            post.setUserId(param.getUserId());
            //save之后post会自动被赋值一个id
            boolean re = postService.save(post);
            List<String> tagList = param.getPostTag();

            for(String tag:tagList){
                PostTagEntity postTag = new PostTagEntity();
                postTag.setPostId(post.getPostId());
                postTag.setTagName(tag);
                postTagService.save(postTag);
            }
            //如果是可能违规，提交给管理员
            if (illegalScore == 1){
                ReportAddParam reportAddParam = new ReportAddParam();
                reportAddParam.setTargetId(post.getPostId());
                reportAddParam.setTargetType("帖子");
                reportAddParam.setReportReason("该帖子内容可能违规");
                restTemplate.postForObject("http://cloud-forum-service/report", reportAddParam, CommonResult.class);
                post.setStatus("系统判定违规");
                postService.updateById(post);

                return new CommonResult(403, "输入的帖子内容可能违规，已提交给管理员审核，审核结果会以通知形式发送给您");
            }
            return new CommonResult(200, "添加成功", post.getPostId());
        }catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }

    }

    @DeleteMapping("/{id}")
    public CommonResult DeletePostById(@PathVariable("id") int id) {
        boolean state = postService.removeById(id);
        postTagService.DeleteByPostId(id);
        if (state) {
            return new CommonResult(200, "删除成功", state);
        } else {
            return new CommonResult(444, "无记录");
        }
    }

    @PutMapping
    public CommonResult PutPostById(@RequestBody PostPutParam param){
        try {
            PostEntity post = postService.getById(param.getPostId());
            if(param.getPostTitle()!=null){
                post.setPostTitle(param.getPostTitle());
            }
            if(param.getPostContent()!=null){
                post.setPostContent(param.getPostContent());
            }
            //byte无法用是否是空来判断，所以这里改为Byte类型
            if(param.getIsTop() != null)
                post.setIsTop(param.getIsTop().byteValue());
            boolean re = postService.updateById(post);

            if (param.getPostTag() != null){
                //先把tag全删了
                postTagService.DeleteByPostId(post.getPostId());

                for(PostTagDTO tag:param.getPostTag()){
                    postTagService.SavePostTag(post.getPostId(), tag.getTagName());
                }
            }
            return new CommonResult(200, "修改成功", post.getPostId());
        }
        catch (Exception e){
            e.printStackTrace();
            return new CommonResult(400, "修改失败");
        }

    }
//
//    @GetMapping("/{postId}")
//    public CommonResult GetPost(@PathVariable Integer postId){
//
//    }

    // 用get方法前端请求体拿不到
    @PostMapping("/user")
    public CommonResult getUserPostList(@RequestBody(required = false) PostGetParam param) {
        try {
            List<PostListDTO> postList;
            if (param == null){
                postList = postService.GetAllPostList();
            }
            else {
                postList = postService.GetPostListByParam(param);
            }
            if(!postList.isEmpty()){
                //获取每个tag对应的tag名，并赋值
                for (PostListDTO post:postList) {
                    post.setPostTag(postTagService.GetPostTagListByPostId(post.getPostId()));
                }
                return new CommonResult(200,"查询成功", postList);
            }
            else{
                return new CommonResult(444,"无记录");
            }
        }catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }

    // 根据clubId和status获取对应的帖子
    @PostMapping("/club")
    public CommonResult getClubPostList(@RequestBody(required = false) PostGetByClubIdParam param) {
        try {
            List<PostListDTO> postList;
            if (param == null){
                postList = postService.GetAllPostList();
            }
            else {
                postList = postService.GetPostListByClubId(param);
            }
            if(!postList.isEmpty()){
                //获取每个tag对应的tag名，并赋值
                for (PostListDTO post:postList) {
                    post.setPostTag(postTagService.GetPostTagListByPostId(post.getPostId()));
                }
                return new CommonResult(200,"查询成功", postList);
            }
            else{
                return new CommonResult(444,"无记录");
            }
        }catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }

    // 获取某个帖子的摘要
    @GetMapping("/abstract/{postId}")
    public CommonResult getPostAbstract(@PathVariable Integer postId){
        try {
            RestTemplate restTemplate1 = new RestTemplate();
            PostEntity post = postService.getById(postId);
            if (post == null){
                return new CommonResult(401, "该帖子不存在");
            }
            //AI提取摘要
//            JSONObject result = HttpGet("http://124.70.163.146:8001/ai/abstract/", post.getPostContent());
            // 使用上一种方法会因为中文编码的问题得到乱码，会出很多麻烦，所以还是新建一个RestTemplate对象更方便一点
            JSONObject result = restTemplate1.getForObject("http://124.70.163.146:8001/ai/abstract/" + post.getPostContent(), JSONObject.class);
            //如果AI那边没拿到东西，则添加失败
            if (result == null){
                return new CommonResult(402, "系统繁忙，请稍后重试");
            }
            JSONObject data = result.getJSONObject("data");
            String abstractWord = data.getStr("abstract");

            //AI那边拿到结果了才能继续
            return new CommonResult(200, "获取成功", abstractWord);
        }
        catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }
    // 获取某个论坛的词云图
    @GetMapping("/topic/{clubId}")
    public CommonResult getForumTopic(@PathVariable Integer clubId){
        try {
            RestTemplate restTemplate1 = new RestTemplate();
            //AI提取摘要
            JSONObject result = HttpGet("http://124.70.163.146:8001/ai/wordCloudByClub/", Integer.toString(clubId));
//            JSONObject result = restTemplate1.getForObject("http://124.70.163.146:8001/ai/wordCloudByClub/" + clubId, JSONObject.class);
            //如果AI那边没拿到东西，则添加失败
            if (result == null){
                return new CommonResult(402, "系统繁忙，请稍后重试");
            }
            JSONObject data = result.getJSONObject("data");
            Integer code = result.getInt("code");
            if(code != 200){
                return new CommonResult(444, "该社团不存在");
            }
            // 引入的Json包使用本方式获取string
            String picUrl = data.getStr("picUrl");

            //AI那边拿到结果了才能继续
            return new CommonResult(200, "获取成功", picUrl);
        }
        catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }

    // 获取给某个用户推荐的帖子列表
    @GetMapping("/recommend/{userId}")
    public CommonResult getRecommendPost(@PathVariable Integer userId){
        try {
            RestTemplate restTemplate1 = new RestTemplate();
            //AI获取推荐帖子列表
            JSONObject result = HttpGet("http://124.70.163.146:8001/ai/recommend/", Integer.toString(userId));
//            JSONObject result = restTemplate1.getForObject("http://124.70.163.146:8001/ai/wordCloudByClub/" + clubId, JSONObject.class);
            //如果AI那边没拿到东西，则获取失败
            if (result == null){
                return new CommonResult(402, "系统繁忙，请稍后重试");
            }
            JSONArray data = result.getJSONArray("data");
            Integer code = result.getInt("code");
            String msg = result.getStr("message");
            if(code != 200){
                return new CommonResult(444, msg);
            }
            List<PostListDTO> postList = new ArrayList<>();
            for (Object item : data){
                PostListDTO postItem = new PostListDTO();
                JSONObject post = (JSONObject)item;
                Integer postId = post.getInt("postId");
                postItem = postService.GetPostById(postId);
                if (postItem != null){
                    postList.add(postItem);
                }
            }

            if(!postList.isEmpty()){
                //获取每个tag对应的tag名，并赋值
                for (PostListDTO post:postList) {
                    post.setPostTag(postTagService.GetPostTagListByPostId(post.getPostId()));
                }
                return new CommonResult(200,"查询成功", postList);
            }
            else{
                return new CommonResult(444,"无记录");
            }
        }
        catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }

    public static void swap(PopularPostDTO a, PopularPostDTO b){
        PopularPostDTO temp = b;
        b = a;
        a = temp;
    }

    // 快速排序
    //快排实现方法
    public static void quickRow(PopularPostDTO[] array, int low, int high){
        int i,j;
        PopularPostDTO pivot;
        //结束条件
        if (low >= high) {
            return;
        }
        i = low;
        j = high;
        //选择的节点，这里选择的数组的第一数作为节点
        pivot = array[low];
        while (i < j){
            //从右往左找比节点小的数，循环结束要么找到了，要么i=j
            while (array[j].getScore() >= pivot.getScore() && i < j){
                j--;
            }
            //从左往右找比节点大的数，循环结束要么找到了，要么i=j
            while (array[i].getScore() <= pivot.getScore() && i < j){
                i++;
            }
            //如果i!=j说明都找到了，就交换这两个数
            if (i < j){
                swap(array[i], array[j]);
            }
        }
        //i==j一轮循环结束，交换节点的数和相遇点的数
        array[low] = array[i];
        array[i] = pivot;
        //数组“分两半”,再重复上面的操作
        quickRow(array,low,i - 1);
        quickRow(array,i + 1,high);
    }



    @GetMapping("/popular")
    public CommonResult getPopularPost(){
        try {
            List<PopularPostDTO> popularPostDTOList = new ArrayList<>();
            LambdaQueryWrapper<PostEntity> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(PostEntity::getStatus, "正常");
            // 先获取所有正常的post
            List<PostEntity> postEntityList = postService.list(wrapper1);
            // 针对每个post，获取对应的评论数
            for (PostEntity postEntity : postEntityList){
                PopularPostDTO popularPostDTO = new PopularPostDTO();
                popularPostDTO.setPostId(postEntity.getPostId());
                LambdaQueryWrapper<CommentEntity> wrapper2 = new LambdaQueryWrapper<>();
                wrapper2.eq(CommentEntity::getPostId, postEntity.getPostId());
                Integer commentCount = (int)commentService.count(wrapper2);
                popularPostDTO.setCommentCount(commentCount);
                popularPostDTO.setLikesCount(0);
                popularPostDTOList.add(popularPostDTO);
            }
            // 针对每个post，获取对应的点赞数
            for (PopularPostDTO popularPostDTO : popularPostDTOList){
                LambdaQueryWrapper<LikesEntity> wrapper3 = new LambdaQueryWrapper<>();
                wrapper3.eq(LikesEntity::getPostId, popularPostDTO.getPostId());
                Integer likesCount = (int)likesService.count(wrapper3);
                popularPostDTO.setLikesCount(likesCount);
            }
            // 根据权重获取每个post对应的热度分数
            for (PopularPostDTO popularPostDTO : popularPostDTOList){
                Integer commentCount = popularPostDTO.getCommentCount();
                Integer likesCount = popularPostDTO.getLikesCount();
                popularPostDTO.setScore(commentCount * 5 + likesCount * 3);
            }
//            popularPostDTOList.sort();

            PopularPostDTO[] popularPostDTOArray = popularPostDTOList.toArray(new PopularPostDTO[popularPostDTOList.size()]);
//            quickRow(popularPostDTOArray, 0, popularPostDTOArray.length - 1);
            Arrays.sort(popularPostDTOArray);
            popularPostDTOList = Arrays.asList(popularPostDTOArray);
            // 把帖子信息填入结果中

            for (PopularPostDTO popularPostDTO : popularPostDTOList){
                PostListDTO postListDTO = postService.GetPostById(popularPostDTO.getPostId());
                postListDTO.setPostTag(postTagService.GetPostTagListByPostId(popularPostDTO.getPostId()));
                popularPostDTO.setUserId(postListDTO.getUserId());
                popularPostDTO.setClubId(postListDTO.getClubId());
                popularPostDTO.setPostTitle(postListDTO.getPostTitle());
                popularPostDTO.setPostContent(postListDTO.getPostContent());
                popularPostDTO.setPostTime(postListDTO.getPostTime());
                popularPostDTO.setPostTag(postListDTO.getPostTag());
            }

            return new CommonResult(200, "获取成功", popularPostDTOList);
        }
        catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }
}
