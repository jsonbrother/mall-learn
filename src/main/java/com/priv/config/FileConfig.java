package com.priv.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Json
 * @date 2021/6/16 11:18
 */
@Component
@PropertySource(value = "classpath:file.properties")
public class FileConfig {

    @Value("${file.path}")
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

}
