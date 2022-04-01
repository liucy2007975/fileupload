package com.anjubao.fileupload.controller;


import com.anjubao.fileupload.service.UploadImageService;
import com.anjubao.fileupload.vo.BaseResponse;
import com.anjubao.fileupload.vo.UploadImageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共基础API
 * 
 * @author wenpingping
 *
 */
@Slf4j
@Controller
@RequestMapping( "/base" )
public class BaseController {


    private static final long EXPIRE_DURATION_60L = 60L;

    @Autowired
    private UploadImageService uploadImageService;



    /**
     * 公共上传图片
     * 
     * @return
     * @throws
     */
    @ResponseBody
    @RequestMapping( value = "/uploadImages" , method = RequestMethod.POST )
    public BaseResponse<UploadImageVo> uploadImages(HttpServletRequest request) {
        log.info("【公共上传图片】-访问【GET /uploadImages】接口，传过来的有用参数：");
        BaseResponse<UploadImageVo> br = new BaseResponse<UploadImageVo>();
        Map<String, MultipartFile> fileMap = new HashMap<String, MultipartFile>();
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest mr = (MultipartHttpServletRequest) request;
            fileMap = mr.getFileMap();
        }
        if (fileMap == null || fileMap.isEmpty()) {
//            throw new CustomException(ErrorNum.UPLOAD_FILE_EMPTY);
        }
        br.setData(uploadImageService.uploadImages(fileMap));
        br.setMessage("上传图片成功");
        return br;
    }
}
