package com.scc.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@Api(value = "/", description = "这是我全部的get方法")
public class MyGetMethod {

    @RequestMapping(value = "/getCookies", method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到cookies", httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        final Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "恭喜你获得cookies成功";
    }

    @RequestMapping(value = "/get/with/cookies", method = RequestMethod.GET)
    @ApiOperation(value = "这个方法要求客户端携带正确的cookies", httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request){
        final Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "你必须携带cookies信息来";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getValue().equals("true")) {
                return "恭喜你访问成功!";
            }
        }
        return "你的cookies信息有误!";
    }
}
