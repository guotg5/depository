package com.guotg.depository.service;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件相关接口
 */
public interface FileService {

    public String doUploadAndCompress(String fileName, InputStream in) throws IOException;

    /**
     * 文件上传
     *
     * @param in
     */
    public String doUpload(String fileName, InputStream in) throws IOException;

}
