package com.guotg.depository.service.impl;

import com.guotg.depository.service.FileService;
import com.guotg.depository.service.ImagService;
import com.guotg.depository.service.MQOperateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;


@Service
public class FileServiceImpl implements FileService {

    @Value("${depository.defaultFilePath}")
    private String defaultFilePath;

    @Resource
    private MQOperateService mqOperateService;

    @Resource
    private ImagService imagService;

    private static final String topic = "file";

    private static final String tag = "file-tag";

    @Override
    public String doUploadAndCompress(String fileName, InputStream in) throws IOException {
        //上传文件
        String filePath = doUpload(fileName, in);

        imagService.getMetaData(filePath);

        //发送生成缩略的消息
        mqOperateService.sendMessage(topic, tag, filePath);

        return filePath;
    }
    @Override
    public String doUpload(String fileName, InputStream in) throws IOException {
        //存储文件路径及名称 增加毫秒数防止重复
        String newPath = defaultFilePath + System.currentTimeMillis() + "_" + fileName;
        OutputStream out = null;

        try{
            File file = new File(defaultFilePath);
            if(!file.exists()){
                createDefaultDir(file);
            }
            //输出流
            out = new FileOutputStream(newPath);
            //缓冲字节数组  每次读取1024字节输出
            byte[] bytes = new byte[1024];
            //长度 读取的长度可能小于1024
            int len;

            while((len = in.read(bytes))!=-1){
                out.write(bytes);
            }
        }finally {
            out.flush();
            in.close();
            out.close();
        }

        return newPath;
    }

    public synchronized  void createDefaultDir(File file){
        file.mkdirs();
    }
}
