package com.hotel.model;

import java.time.LocalDateTime;

public class Guest {
    private int guestId;          // 客户ID
    private String name;          // 姓名
    private String phone;         // 手机号
    private String email;         // 邮箱
    private String address;       // 地址
    private String gender;        // 性别
    private String idCard;        // 身份证号
    private String memberLevel;   // 会员等级(regular/silver/gold/platinum)
    private LocalDateTime createTime; // 注册时间

    public Guest() {

    }

    public Guest(int guestId, String name, String phone, String email, String address, String memberLevel, LocalDateTime createTime) {
        this.guestId = guestId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.memberLevel = memberLevel;
        this.createTime = createTime;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "guestId=" + guestId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", memberLevel='" + memberLevel + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}