package com.priv.model.dto;

import java.util.List;
import java.util.Set;

/**
 * 渠道文件传输层
 *
 * @author Json
 * @date 2021/6/7 13:33
 */
public class ChannelFileAnalyseDTO {

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件名称集
     */
    private Set<String> fileNames;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Set<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(Set<String> fileNames) {
        this.fileNames = fileNames;
    }
}
