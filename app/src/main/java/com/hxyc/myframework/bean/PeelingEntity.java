package com.hxyc.myframework.bean;

public class PeelingEntity {

    /**
     * uuid : 7bf3f03d8f224c408ad544b324bc6f96
     * name : 虎年大吉
     * topImgUrl : http://oss-test.hexingyueche.com/www/images/sysAppPassImgSkin/7bf3f03d8f224c408ad544b324bc6f96top/full/20220809b6e5f560-0c5a-4f05-bf9b-5e59784b3d9b.jpg
     * middleImgUrl : http://oss-test.hexingyueche.com/www/images/sysAppPassImgSkin/7bf3f03d8f224c408ad544b324bc6f96middle/full/2022080905d88cb4-52f8-415c-b899-0c8d30c997a7.jpg
     * carImgUrl : http://oss-test.hexingyueche.com/www/images/sysAppPassImgSkin/7bf3f03d8f224c408ad544b324bc6f96car/full/20220809833eec95-f41a-4eff-aa83-5cf2c3dcb0e4.jpg
     * status : 1
     * startTime : 1660039200000
     * endTime : 1660233600000
     * createTime : 1660036710000
     * updateTime : 1660110855000
     * creater : 69cfcbbc7cc84248ae09d2b693b6f15a
     * updater : 69cfcbbc7cc84248ae09d2b693b6f15a
     */

    private String uuid;
    private String name;
    private String topImgUrl;
    private String middleImgUrl;
    private String carImgUrl;
    private int status;
    private long startTime;
    private long endTime;
    private long createTime;
    private long updateTime;
    private String creater;
    private String updater;
    private int openMourningKey;//哀悼模式1  开  0关

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopImgUrl() {
        return topImgUrl;
    }

    public void setTopImgUrl(String topImgUrl) {
        this.topImgUrl = topImgUrl;
    }

    public String getMiddleImgUrl() {
        return middleImgUrl;
    }

    public void setMiddleImgUrl(String middleImgUrl) {
        this.middleImgUrl = middleImgUrl;
    }

    public String getCarImgUrl() {
        return carImgUrl;
    }

    public void setCarImgUrl(String carImgUrl) {
        this.carImgUrl = carImgUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public int getOpenMourningKey() {
        return openMourningKey;
    }

    public void setOpenMourningKey(int openMourningKey) {
        this.openMourningKey = openMourningKey;
    }
}
