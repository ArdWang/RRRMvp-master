package com.ee.project.main.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 * 消息块的Bean
 */

public class MessageBean {

    /**
     * code : 200
     * tips : 获取消息信息成功
     * message : success
     * messageBeans : [{"id":1,"userName":"admin","userID":60,"messageTitle":"测试信息","messageCon":"管理元测试信息","deviceInfo":"Android","messageImage":null,"messageTime":1520956800000,"messageRead":0},{"id":2,"userName":"admin","userID":60,"messageTitle":"admin","messageCon":"恭喜你登录成功","deviceInfo":"android","messageImage":null,"messageTime":1520956800000,"messageRead":0}]
     */

    private String code;
    private String tips;
    private String message;
    private List<MessageBeansBean> messageBeans;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MessageBeansBean> getMessageBeans() {
        return messageBeans;
    }

    public void setMessageBeans(List<MessageBeansBean> messageBeans) {
        this.messageBeans = messageBeans;
    }

    public static class MessageBeansBean {
        /**
         * id : 1
         * userName : admin
         * userID : 60
         * messageTitle : 测试信息
         * messageCon : 管理元测试信息
         * deviceInfo : Android
         * messageImage : null
         * messageTime : 1520956800000
         * messageRead : 0
         */

        private int id;
        private String userName;
        private int userID;

        public int getMessageType() {
            return messageType;
        }

        public void setMessageType(int messageType) {
            this.messageType = messageType;
        }

        //新增消息类型
        private int messageType;
        private String messageTitle;
        private String messageCon;
        private String deviceInfo;
        private Object messageImage;
        private long messageTime;
        private int messageRead;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getMessageTitle() {
            return messageTitle;
        }

        public void setMessageTitle(String messageTitle) {
            this.messageTitle = messageTitle;
        }

        public String getMessageCon() {
            return messageCon;
        }

        public void setMessageCon(String messageCon) {
            this.messageCon = messageCon;
        }

        public String getDeviceInfo() {
            return deviceInfo;
        }

        public void setDeviceInfo(String deviceInfo) {
            this.deviceInfo = deviceInfo;
        }

        public Object getMessageImage() {
            return messageImage;
        }

        public void setMessageImage(Object messageImage) {
            this.messageImage = messageImage;
        }

        public long getMessageTime() {
            return messageTime;
        }

        public void setMessageTime(long messageTime) {
            this.messageTime = messageTime;
        }

        public int getMessageRead() {
            return messageRead;
        }

        public void setMessageRead(int messageRead) {
            this.messageRead = messageRead;
        }
    }
}
