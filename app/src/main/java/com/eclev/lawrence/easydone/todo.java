package com.eclev.lawrence.easydone;

/**
 * Created by SYSTEM on 10/9/2017.
 */

public class todo {
    private String desc;
    private String duration;

    public todo(){

    }

    public todo(String desc, String duration) {
        this.desc = desc;
        this.duration = duration;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
