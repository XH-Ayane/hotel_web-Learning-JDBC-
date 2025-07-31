package com.hotel.model;

import java.time.LocalDateTime;

public class HotelStaff {
        private int staffId;          // 员工ID
        private String staffNo;       // 工号
        private String name;          // 姓名
        private String password;      // 密码(加密)
        private String position;      // 职位
        private String department;    // 部门
        private String contact;       // 联系电话
        private boolean status;       // 状态(0禁用1启用)
        private LocalDateTime hireDate; // 入职日期
    private LocalDateTime createTime; // 创建时间

        public HotelStaff() {

        }

        public HotelStaff(int staffId, String staffNo, String name, String password, String position, String department, String contact, boolean status, LocalDateTime hireDate, LocalDateTime createTime) {
                this.staffId = staffId;
                this.staffNo = staffNo;
                this.name = name;
                this.password = password;
                this.position = position;
                this.department = department;
                this.contact = contact;
                this.status = status;
        this.hireDate = hireDate;
        this.createTime = createTime;
        }

        public int getStaffId() {
                return staffId;
        }

        public void setStaffId(int staffId) {
                this.staffId = staffId;
        }

        public String getStaffNo() {
                return staffNo;
        }

        public void setStaffNo(String staffNo) {
                this.staffNo = staffNo;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getPosition() {
                return position;
        }

        public void setPosition(String position) {
                this.position = position;
        }

        public String getDepartment() {
                return department;
        }

        public void setDepartment(String department) {
                this.department = department;
        }

        public String getContact() {
                return contact;
        }

        public void setContact(String contact) {
                this.contact = contact;
        }

        public boolean isStatus() {
                return status;
        }

        public void setStatus(boolean status) {
                this.status = status;
        }

        public LocalDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

        public void setCreateTime(LocalDateTime createTime) {
                this.createTime = createTime;
        }

        @Override
        public String toString() {
                return "HotelStaff{" +
                        "staffId=" + staffId +
                        ", staffNo='" + staffNo + '\'' +
                        ", name='" + name + '\'' +
                        ", password='" + password + '\'' +
                        ", position='" + position + '\'' +
                        ", department='" + department + '\'' +
                        ", contact='" + contact + '\'' +
                        ", status=" + status +
                        ", createTime=" + createTime +
                        '}';
        }
}
