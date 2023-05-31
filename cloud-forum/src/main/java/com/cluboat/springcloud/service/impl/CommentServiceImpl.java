package com.cluboat.springcloud.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cluboat.springcloud.entity.CommentEntity;
import com.cluboat.springcloud.entity.PostTagEntity;
import com.cluboat.springcloud.entity.UserInfoEntity;
import com.cluboat.springcloud.entity.dto.CommentGetDTO;
import com.cluboat.springcloud.entity.dto.PopularPostDTO;
import com.cluboat.springcloud.entity.dto.PostTagDTO;
import com.cluboat.springcloud.entity.param.CommentDeleteParam;
import com.cluboat.springcloud.mapper.CommentMapper;
import com.cluboat.springcloud.service.CommentService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentEntity> implements CommentService {

    @Resource
    CommentMapper commentMapper;


    //原版api，先弃用
    @Override
    public int DeleteComment(CommentDeleteParam param){
        LambdaQueryWrapper<CommentEntity> wrapper = new LambdaQueryWrapper<CommentEntity>()
                .eq(CommentEntity::getCommentId, param.getCommentId())
                .eq(CommentEntity::getPostId,param.getPostId());
        return commentMapper.delete(wrapper);
    }

    @Override
    public List<CommentGetDTO> GetCommentByPostId(Integer postId){
        List<CommentGetDTO> commentGetDTOList = commentMapper.selectJoinList(CommentGetDTO.class,
                new MPJLambdaWrapper<CommentEntity>()
                    .selectAll(CommentEntity.class)
                    .leftJoin(UserInfoEntity.class, UserInfoEntity::getUserId, CommentEntity::getUserId)
                    .eq(CommentEntity::getPostId, postId)
//                    .select(UserInfoEntity::getUserName, UserInfoEntity::getUserPhotoUrl)
//                    .select(UserInfoEntity::getUserPhotoUrl)
                    .orderByAsc(CommentEntity::getCommentTime));
        return commentGetDTOList;
    }

//    @Override
//    public List<PopularPostDTO> GetPopularPost(){
//        List<PopularPostDTO> = commentMapper.selectJoinList(PopularPostDTO.class,
//                new MPJLambdaWrapper<CommentEntity>()
//                    .select(CommentEntity::getPostId)
//                    .select("COUNT(commentId) as commentCount")
//                )
//    }

}
