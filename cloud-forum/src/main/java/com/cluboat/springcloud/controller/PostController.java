package com.cluboat.springcloud.controller;

import cn.hutool.json.JSONObject;
import com.cluboat.springcloud.common.CommonResult;
import com.cluboat.springcloud.entity.PostEntity;
import com.cluboat.springcloud.entity.PostTagEntity;
import com.cluboat.springcloud.entity.dto.PostListDTO;
import com.cluboat.springcloud.entity.dto.PostTagDTO;
import com.cluboat.springcloud.entity.param.PostAddParam;
import com.cluboat.springcloud.entity.param.PostGetParam;
import com.cluboat.springcloud.entity.param.PostPutParam;
import com.cluboat.springcloud.entity.param.ReportAddParam;
import com.cluboat.springcloud.service.PostService;
import com.cluboat.springcloud.service.PostTagService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

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


//    Put中没有判断加的tag是否是该club下有的tag
    @PostMapping
    public CommonResult InsertPost(@RequestBody PostAddParam param) {
        try {
            //AI判定其是否违规
            JSONObject result = HttpGet("http://124.70.163.146:8001/ai/illegalCheck", param.getPostTitle());
            //如果AI那边没拿到东西，则添加失败
            if (result == null){
                return new CommonResult(401, "系统繁忙，请稍后重试");
            }
            JSONObject data = result.getJSONObject("data");
            Integer illegalScore = data.getInt("illegalScore");
            if (illegalScore > 1){
                return new CommonResult(402, "输入的帖子内容不合规，请稍后重试");
            }
            //AI那边拿到结果了才能继续
            //先创建相应的记录，以及tag的记录
            PostEntity post = new PostEntity();
            post.setPostTime(new Timestamp(System.currentTimeMillis()));
            post.setIsTop((byte)0);
            post.setPostTitle(param.getPostTitle());
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
            //byte无法用是否是空来判断，所以这里必须有这个参数
            post.setIsTop(param.getIsTop());
            boolean re = postService.updateById(post);

            //先把tag全删了
            postTagService.DeleteByPostId(post.getPostId());

            for(PostTagDTO tag:param.getPostTag()){
                postTagService.SavePostTag(post.getPostId(), tag.getTagName());
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

    @GetMapping()
    public CommonResult getPostList(@RequestBody(required = false) PostGetParam param) {
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


}
