package com.scc.server;

import com.scc.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@Api(value = "/", hidden = true)
@RequestMapping("/v1")
public class MyPostMethod {
    //这个变量用来装cookies信息
    private static Cookie cookie;

    //用户登录成功获取到cookies, 然后再访问其他接口获取到列表
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ApiOperation(value = "登录接口, 用来获取cookies信息", httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName", required = true) String userName,
                        @RequestParam(value = "password", required = true) String password ) {
        //验证userName和password合法
        if (userName.equals("shenchaochao") && password.equals("123456")) {
            cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            return userName + ", 恭喜你登录成功了!";
        }
        return "用户名或密码错误!";
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户列表的接口", httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                                       @RequestBody User u){
        //获取请求中的所有cookies
        final Cookie[] cookies = request.getCookies();
        final ArrayList<User> list = new ArrayList<>();
        //验证cookies是否合法
        if(cookies == null){
            return "cookies为空!";
        }
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("login") && cookie.getValue().equals("true")
            && u.getName().equals("shenchaochao")) {
                User jack = new User("Jack", 19, "male");
//                System.out.println(jack.toString());
                User mary = new User("Mary", 28, "female");
                User tom = new User("Tom", 22, "male");
                User jerry = new User("Jerry", 26, "male");
                User shenchaochao = new User("shenchaochao", 29, "male");
                list.add(jack);
                list.add(mary);
                list.add(tom);
                list.add(jack);
                list.add(shenchaochao);
                String listToString = "";
                for (User user: list) {
                    listToString = listToString + ", " + user.toString();
                }
                return listToString;
            }
        }

        return "身份不合法!";

    }
}
