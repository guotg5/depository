package com.guotg.depository.service.impl;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.guotg.depository.service.ImagService;
import com.guotg.depository.util.Logger;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class ImagServiceImpl implements ImagService {

    private static final String THUMBNAIL = "_thumbnail";

    @Override
    public String compress(String filePath) throws IOException {

        String newPath = getNewPath(filePath, THUMBNAIL);

        Thumbnails.of(filePath).scale(1f).outputQuality(0.25f).toFile(newPath);

        return newPath;
    }

    @Override
    public Map<String, String> getMetaData(String filePath) {
        File jpegFile = new File(filePath);
        Map<String, String> allMetaData = new HashMap<>();
        Metadata metadata;
        try {
            metadata = JpegMetadataReader.readMetadata(jpegFile);
            Iterator<Directory> it = metadata.getDirectories().iterator();
            while (it.hasNext()) {
                Directory exif = it.next();
                Iterator<Tag> tags = exif.getTags().iterator();
                while (tags.hasNext()) {
                    Tag tag = tags.next();
                    allMetaData.put(tag.getTagName(), tag.getDescription());
                    System.out.println(tag);
                }
            }
        } catch (JpegProcessingException e) {
            Logger.error(e);
        } catch (IOException e) {
            Logger.error(e);
        }

        return allMetaData;
    }

    private String getNewPath(String filePath, String regx){
        int begin = filePath.lastIndexOf("/");
        int end = filePath.lastIndexOf(".");

        String fileName = filePath.substring(begin+1, end);

        return filePath.replace(fileName, fileName + regx);
    }
}
