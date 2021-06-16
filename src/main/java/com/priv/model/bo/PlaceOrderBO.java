package com.priv.model.bo;

/**
 * 下单传输对象
 *
 * @author Json
 * @date 2021/4/20 17:23
 */
public class PlaceOrderBO {

    private String data;

    private String source;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "PlaceOrderBO{" +
                "data='" + data + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
