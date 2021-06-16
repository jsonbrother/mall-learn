package com.priv.model.dto;

import java.util.List;

/**
 * @author Json
 * @date 2021/6/7 14:30
 */
public class ChannelFileImportDTO {

    private String departId;

    private String workDate;

    private List<String> fileNames;

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }
}
