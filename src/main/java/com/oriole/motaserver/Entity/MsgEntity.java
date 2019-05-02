package com.oriole.motaserver.Entity;

public class MsgEntity {
    private String State;
    private String Code;
    private String Msg;

    public MsgEntity(String state, String code, String msg) {
        State = state;
        Code = code;
        Msg = msg;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }
}
