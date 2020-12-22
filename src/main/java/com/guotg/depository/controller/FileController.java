package com.guotg.depository.controller;

import com.guotg.depository.service.FileService;
import com.guotg.depository.util.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/File")
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param type
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload/{type}", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
    public String sendMessage(@PathVariable String type, HttpServletRequest request) throws IOException {
        try{
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
            MultipartFile multipartFile = files.get(0);
            /*验证上传文件大小,防止浪费空间内存*/
            double sizeMb = multipartFile.getSize() / (1024 * 1024);
            if (sizeMb > 50){
                return "failed";
            }

            fileService.doUploadAndCompress(multipartFile.getOriginalFilename(), multipartFile.getInputStream());
        }catch (Exception e){
            Logger.error(e);
            return e.getMessage();
        }

        return "success";
    }
}
