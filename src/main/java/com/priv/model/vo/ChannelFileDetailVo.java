package com.priv.model.vo;

import java.util.List;

/**
 * @author Json
 * @date 2021/6/8 14:37
 */
public class ChannelFileDetailVo {

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 明细数据集
     */
    private List<List<String>> detailList;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<List<String>> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<List<String>> detailList) {
        this.detailList = detailList;
    }
}
