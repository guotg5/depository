package com.guotg.depository.service;

import java.io.IOException;
import java.util.Map;

public interface ImagService {

    /**
     * 生成缩略图
     *
     * @param filePath 原文件路径
     * @return 缩略图文件路径
     * @throws IOException
     */
    public String compress(String filePath) throws IOException;

    /**
     * 获取图片元数据信息
     *
     * @param filePath
     * @return
     */
    public Map<String, String> getMetaData(String filePath);
}
