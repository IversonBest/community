package com.demo.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {//特定异常处理

    QUESTION_NOT_FOUND(2001,"你找的问题不在，要不要换一个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登陆后重试"),
    SYS_ERROR(2004,"系统错误，服务器顶不住啦！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或者不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在"),
    COMMENT_IS_EMPTY(2007,"输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"兄弟你这是读别人信息呢"),
    NOTIFICATION_NOT_FOUND(2009,"消息莫非不翼而飞？");
    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
