package com.hxyc.myframework.network.entity;

import org.litepal.crud.DataSupport;

/**
 * 日志记录模型
 */

public class LogRecordEntity extends DataSupport {

    /**
     * 日志记录时间
     */
    private long time;
    /**
     * 记录的标题（所属部分）
     */
    private String title;
    /**
     * 日志记录内容
     */
    private String content;
    /**
     * 日志记录备注
     */
    private String remark;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LogRecordEntity(long time, String title, String content) {
        this.time = time;
        this.title = title;
        this.content = content;
    }

    public LogRecordEntity(long time, String title, String content, String remark) {
        this.time = time;
        this.title = title;
        this.content = content;
        this.remark = remark;
    }
}
