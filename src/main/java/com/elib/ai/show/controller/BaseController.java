package com.elib.ai.show.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: ai-show
 * @description: TODO
 * @author: konggang
 * @create: 2020-11-24 19:45
 **/
@Controller
public class BaseController {

    private int i=0;


    @GetMapping("/index")
    public String index(HttpServletRequest request, @RequestParam(value = "name", required = false, defaultValue = "springboot-thymeleaf") String name){
        request.setAttribute("name", name);
        return "index";
    }
    @GetMapping("/home/index")
    public String home(HttpServletRequest request, @RequestParam(value = "name", required = false, defaultValue = "springboot-HOME") String name){
        request.setAttribute("name", name);
        return "home/home";
    }

    @GetMapping("/home/show")
    public String show(HttpServletRequest request, @RequestParam(value = "name", required = false, defaultValue = "springboot-HOME") String name){
        request.setAttribute("name", name);
        return "home/show";
    }

    @PostMapping("/ajax")
    @ResponseBody
    public String ajax(HttpServletRequest request){
        i++;
        return String.valueOf(i);
    }

    @PostMapping("/beau")
    @ResponseBody
    public Object beau(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        JSONObject b = new JSONObject();
        b.put("k", 456465);
        jsonObject.put("a", "12313");
        jsonObject.put("b", b);
        return jsonObject;
    }
}
