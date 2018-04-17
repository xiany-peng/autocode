package com.shulipeng.utils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @author pengxianyang
 * @date 2018/4/17
 * @company QingDao Airlines
 * @description
 */
public class ResourceUtils {

    public InputStream getResourceAsStream(String resourceName) {
        URL url = getResourceUrl(resourceName);
        if (url != null) {
            try {
                return url.openStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public URL getResourceUrl(String fileName) {
        if (fileName == null) {
            return null;
        }
        URL resource = getClass().getResource(fileName);
        if (resource == null) {
            resource = Thread.currentThread().getContextClassLoader().getResource(fileName);
            if (resource == null) {
                resource = ClassLoader.getSystemResource(fileName);
                if (resource == null) {
                    File file = new File(fileName);
                    if (file.exists()) {
                        try {
                            resource = file.toURI().toURL();
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
        return resource;
    }

    /**
     * 依次从以下顺序获取配置文件位置
     * 优先级为 一个/config当前目录下的子目录。
     *        当前目录
     *        一个类路径/config包
     *        类路径根
     * @param fileName
     * @return
     */
    public static String getResourceAddr(String fileName){
        String fallback =  "file:./config/,file:./,classpath:/config/,classpath:/";
        List<String> list = Arrays.asList(StringUtils.trimArrayElements(StringUtils.commaDelimitedListToStringArray(fallback)));
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        for(String prefix : list){
            String filePath = prefix + fileName;
            Resource resource = resourceLoader.getResource(filePath);
            if(resource != null && resource.exists()){
                return resource.getFilename();
            }
        }

        return null;
    }
}
