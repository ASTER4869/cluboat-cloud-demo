package com.cluboat.springcloud.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cluboat.springcloud.entities.CommonResult;
import com.cluboat.springcloud.entity.*;
import com.cluboat.springcloud.entity.param.CommentAddParam;
import com.cluboat.springcloud.entity.param.CommentDeleteParam;
import com.cluboat.springcloud.entity.param.ReportAddParam;
import com.cluboat.springcloud.mapper.CommentMapper;
import com.cluboat.springcloud.mapper.PostMapper;
import com.cluboat.springcloud.service.CommentService;
import com.cluboat.springcloud.service.PostService;
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
@RequestMapping("/comment")
public class CommentController {

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

    @Resource
    private CommentService commentService;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private PostService postService;

    @Resource
    private PostMapper postMapper;


    //根据帖子的id获取评论列表
    @GetMapping("{postId}")
    public CommonResult GetCommentByPostId(@PathVariable Integer postId){
        try {
            LambdaQueryWrapper<CommentEntity> wrapper = new LambdaQueryWrapper<CommentEntity>()
                    .eq(CommentEntity::getPostId, postId);
            List<CommentEntity> commentEntityList = commentService.list(wrapper);
            if (commentEntityList == null || commentEntityList.isEmpty()){
                return new CommonResult(444, "无记录");
            }
            return new CommonResult(200, "获取成功", commentEntityList);
        }
        catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }

    @PostMapping
    public CommonResult InsertComment(@RequestBody CommentAddParam param){
        try {
            if(param.getPostId() == null || param.getUserId() == null || param.getCommentContent() == null){
                return new CommonResult(400, "输入参数不完整");
            }

            PostEntity post = postService.getById(param.getPostId());
            if (post == null){
                return new CommonResult(444, "该帖子不存在");
            }

            //AI判定其是否违规
            JSONObject result = HttpGet("http://124.70.163.146:8001/ai/illegalCheck", param.getCommentContent());
            //如果AI那边没拿到东西，则添加失败
            if (result == null){
                return new CommonResult(400, "系统繁忙，请稍后重试");
            }
            JSONObject data = result.getJSONObject("data");
            Integer illegalScore = data.getInt("illegalScore");
            if (illegalScore > 1){
                return new CommonResult(401, "输入的评论内容不合规，请稍后重试");
            }
            //AI那边拿到结果了才能继续
            //先创建相应的记录
            CommentEntity comment = new CommentEntity();
            comment.setCommentContent(param.getCommentContent());
            comment.setCommentTime(new Timestamp(System.currentTimeMillis()));
            comment.setUserId(param.getUserId());
            comment.setPostId(param.getPostId());
            comment.setStatus("正常");
            commentService.save(comment);

            //如果是可能违规，提交给管理员
            if (illegalScore == 1){
                ReportAddParam reportAddParam = new ReportAddParam();
                reportAddParam.setTargetId(comment.getCommentId());
                reportAddParam.setTargetType("评论");
                reportAddParam.setReportReason("该评论内容可能违规");
                restTemplate.postForObject("http://cloud-forum-service/report", reportAddParam, CommonResult.class);
                comment.setStatus("系统判定违规");
                commentService.updateById(comment);

                return new CommonResult(401, "输入的评论内容可能违规，已提交给管理员审核，审核结果会以通知形式发送给您");
            }
            return new CommonResult(200, "添加成功", comment.getCommentId());
        }catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }

    @DeleteMapping
//    public CommonResult DeleteComment(@RequestBody CommentDeleteParam param){
//        int commentNum = commentService.DeleteComment(param);
    public CommonResult DeleteComment(@PathVariable Integer commentId){
        try {
            boolean result = commentService.removeById(commentId);
            if(result == true){
                return new CommonResult(200, "删除成功");
            } else {
                return new CommonResult(444, "删除失败");
            }
        }catch (Exception e){
            return new CommonResult(400, "系统出现错误", e);
        }
    }
}
