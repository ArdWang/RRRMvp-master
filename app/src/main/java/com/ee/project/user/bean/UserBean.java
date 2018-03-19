package com.ee.project.user.bean;


import java.io.Serializable;

/**
 * Created by rnd on 2018/1/18.
 * 获取用户数据接口
 */

public class UserBean {

    /**
     * code : 200
     * tips : ok
     * message : success
     * userBean : {"userid":12,"username":"yuan","password":"123456","age":18,"sex":"男","phone":"435353","email":"4535435","hoby":"这家伙很懒","note":"没有备注","admin":0}
     */

    private String code;
    private String tips;
    private String message;
    private UserBeanBean userBean;

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

    public UserBeanBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBeanBean userBean) {
        this.userBean = userBean;
    }

    public static class UserBeanBean  implements Serializable {
        /**
         * userid : 12
         * username : yuan
         * password : 123456
         * age : 18
         * sex : 男
         * phone : 435353
         * email : 4535435
         * hoby : 这家伙很懒
         * note : 没有备注
         * admin : 0
         */

        private int userid;
        private String username;
        private String password;
        private int age;
        private String sex;
        private String phone;
        private String email;
        private String hoby;
        private String note;
        private int admin;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHoby() {
            return hoby;
        }

        public void setHoby(String hoby) {
            this.hoby = hoby;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public int getAdmin() {
            return admin;
        }

        public void setAdmin(int admin) {
            this.admin = admin;
        }
    }
}
