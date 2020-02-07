package com.demo.controller;

import com.demo.dto.AccesstokenDto;
import com.demo.dto.GithubUser;
import com.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("github.client.id")
    private  String client_id;

    @Value("github.client.secret")
    private  String client_secret;

    @Value("github.redirect.uri")
    private  String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccesstokenDto accesstokenDto = new AccesstokenDto();
        accesstokenDto.setClient_id(client_id);
        accesstokenDto.setClient_secret(client_secret);
        accesstokenDto.setCode(code);
        accesstokenDto.setRedirect_uri(redirect_uri);
        accesstokenDto.setState(state);
        String accessToken = githubProvider.getAccessToken(accesstokenDto);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
