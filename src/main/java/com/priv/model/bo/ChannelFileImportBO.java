package com.priv.model.bo;

import java.util.List;

/**
 * @author Json
 * @date 2021/6/9 10:00
 */
public class ChannelFileImportBO {

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
