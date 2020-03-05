package com.demo.service;

import com.demo.enums.CommentTypeEnum;
import com.demo.exception.CustomizeErrorCode;
import com.demo.exception.CustomizeException;
import com.demo.mapper.CommentMapper;
import com.demo.mapper.QuestionExtMapper;
import com.demo.mapper.QuestionMapper;
import com.demo.model.Comment;
import com.demo.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Transactional//增加事务（作用：比如当commentMapper.insert执行成功，增加回复数的方法失败，这样commentMapper.insert也不会执行，整个方法都在一个事务中）
    public void insert(Comment comment) {
        //评论没有parentId异常
        if (comment.getParentId()==null||comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        //评论没有type异常
        if (comment.getType()==null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);//回复数加1
        }
    }
}
