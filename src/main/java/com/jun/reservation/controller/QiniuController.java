package com.jun.reservation.controller;

import com.jun.reservation.response.ResponseResult;
import com.jun.reservation.response.Result;
import com.jun.reservation.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author jun
 * @date 2021/1/2
 */
@Controller
public class QiniuController{


    @Autowired
    QiniuUtils qiniuUtils;

    @ResponseBody
    @RequestMapping(value = "/images", method = RequestMethod.POST)
    private Result postImgs(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile) throws IOException {

        // 用来获取其他参数
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        if (!multipartFile.isEmpty()) {
            FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
            String path = qiniuUtils.uploadQNImg(inputStream, qiniuUtils.getRandomImgName()); // KeyUtil.genUniqueKey()生成图片的随机名
            System.out.print("七牛云返回的图片链接:" + path);
            return ResponseResult.success(path);
        }
        return ResponseResult.failure("上传失败");
    }


}
