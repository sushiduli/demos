package com.elib.ai.show.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.config.AccConfig;
import com.tal.ailab.util.Base64;
import com.tal.ailab.util.HttpUtil;
import com.tal.ailab.util.PostUtil;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.expression.Lists;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: ai-show
 * @description: TODO
 * @author: konggang
 * @create: 2020-11-24 19:45
 **/
@Controller
public class BaseController {

    @Autowired
    AccConfig accConfig;

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

    @ResponseBody
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public JSONObject upload(@RequestParam("file") MultipartFile file, HttpServletRequest request ) throws Exception {
        System.out.println("上传开始");
        JSONObject json = new JSONObject();
        json.put("code", "1");
        if( file.isEmpty() ) {
            json.put("msg", "上传文件为空");
            return json;
        }else {
            String savePath = request.getServletContext().getRealPath("/upload/");
            String fileName=file.getOriginalFilename();
            String pathname = savePath + fileName;
            File dest = new File(pathname);
            if( !dest.getParentFile().exists() ) {
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);

            String base64Str = Base64.ImageToBase64ByLocal(dest);
            Map<String, Object> urlparam = new HashMap<>();
            Map<String, Object> body = new HashMap<>();

            ArrayList<Object> list = new ArrayList<>();
            body.put("token","aaaaa");
            body.put("sid","sykpaper");
            body.put("url","");
            body.put("image", list.add(base64Str));
            //手写作文识别
            String url = "https://openai.100tal.com/aiocr/chcomp";

            //Novabell  手写中文识别
            // String url = "https://openai.100tal.com/aiimage/novabell/ocr-chinese";
            // body = new HashMap<>();
            // body.put("picture",base64Str);
            String res = PostUtil.sendPostOrPatchOrPutJSON(
                    accConfig.getAccesskey(), accConfig.getAccessSecret(),
                    url, urlparam, body, com.tal.ailab.enums.RequestMethod.POST);

            return JSON.parseObject(res);
        }
    }
}
