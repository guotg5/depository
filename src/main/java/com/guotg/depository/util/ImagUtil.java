package com.guotg.depository.util;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

/**
 * 图片处理工具类
 * 使用开源Thumbnails
 * https://blog.csdn.net/chenleixing/article/details/44685817
 */
public class ImagUtil {

    /**
     * 图像压缩 直接压缩文件大小
     *
     * @param fromPic
     * @param toPic
     * @throws IOException
     */
    public static void compress(String fromPic, String toPic) throws IOException {
        Thumbnails.of(fromPic).scale(1f).outputQuality(0.25f).toFile(toPic);
    }

    public static void main(String[] args) {
        String from = "./src/main/resources/imag.jpg";
        String to = "./src/main/resources/imagys.jpg";

        try {
            compress(from, to);
        } catch (IOException e) {
            Logger.error("生成缩略图错误", e);
        }

    }

}
