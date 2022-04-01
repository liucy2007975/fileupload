package com.anjubao.fileupload.controller;

import com.anjubao.fileupload.vo.BaseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {


    @Value("${image.path)")
    String uploadImagePath;

    @Value("${prefix.url}")
    String imagePrefixUrl;

    @ResponseBody
    @RequestMapping( "/index" )
    public BaseResponse index(){

        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setMessage("欢迎访问！ImagePath:" +uploadImagePath+">>>>>PrefixUrl:"+imagePrefixUrl );
        return baseResponse;
    }
}
