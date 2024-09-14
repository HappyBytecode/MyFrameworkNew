package com.hxyc.myframework.bean;

import java.io.Serializable;
import java.util.List;

public class PromoteItemEntity implements Serializable {

    private List<BasicBean> active;
    private List<BasicBean> consume;
    private List<BasicBean> basic;

    public List<BasicBean> getActive() {
        return active;
    }

    public void setActive(List<BasicBean> active) {
        this.active = active;
    }

    public List<BasicBean> getConsume() {
        return consume;
    }

    public void setConsume(List<BasicBean> consume) {
        this.consume = consume;
    }

    public List<BasicBean> getBasic() {
        return basic;
    }

    public void setBasic(List<BasicBean> basic) {
        this.basic = basic;
    }

    public static class BasicBean implements Serializable {
        /**
         * uuid : memberrule001
         * name : 首次设置紧急联系人
         * type : 1
         * subType : 101
         * hxNum : 30
         * hxPercent : null
         * createTime : 1672117592000
         * remark : 仅获取一次，永久
         * isGoHx : 0
         * subTitle : +30和享值
         * goUrl : null
         */

        private String uuid;
        private String name;
        private int type;
        private int subType;
        private int hxNum;
        private Object hxPercent;
        private long createTime;
        private String remark;
        private int isGoHx;
        private String subTitle;
        private String goUrl;

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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getSubType() {
            return subType;
        }

        public void setSubType(int subType) {
            this.subType = subType;
        }

        public int getHxNum() {
            return hxNum;
        }

        public void setHxNum(int hxNum) {
            this.hxNum = hxNum;
        }

        public Object getHxPercent() {
            return hxPercent;
        }

        public void setHxPercent(Object hxPercent) {
            this.hxPercent = hxPercent;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getIsGoHx() {
            return isGoHx;
        }

        public void setIsGoHx(int isGoHx) {
            this.isGoHx = isGoHx;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getGoUrl() {
            return goUrl;
        }

        public void setGoUrl(String goUrl) {
            this.goUrl = goUrl;
        }
    }
}
