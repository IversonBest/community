package com.demo.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
    //"avatar_url": "https://avatars0.githubusercontent.com/u/1358776?v=4"
}
