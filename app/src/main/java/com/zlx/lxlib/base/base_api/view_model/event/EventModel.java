package com.zlx.lxlib.base.base_api.view_model.event;

/**
 * @date: 2019\5\29 0029
 * @author: zlx
 * @email: 1170762202@qq.com
 * @description: 组件通信
 */
public enum EventModel {
    AC2FG(1, "activity to fragment"),
    FG2AC(2, "fragment to activity"),
    ;

    private int code;
    private String msg;

    EventModel(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
