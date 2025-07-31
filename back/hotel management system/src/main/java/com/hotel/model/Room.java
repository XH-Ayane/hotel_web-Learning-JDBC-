package com.hotel.model;

import java.sql.Timestamp;
import java.util.Date;

public class Room {
    private int roomId;
    private String roomNumber;
    private int typeId;
    private int floor;
    private String viewType;
    private String status;
    private Timestamp lastClean;
    private Date nextMaintenance;
    private Timestamp createTime;


    public Room() {

    }

    public Room(int roomId, String roomNumber, int typeId, int floor, String viewType, String status, Timestamp lastClean, Date nextMaintenance, Timestamp createTime, String roomImages, String floorPlan) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.typeId = typeId;
        this.floor = floor;
        this.viewType = viewType;
        this.status = status;
        this.lastClean = lastClean;
        this.nextMaintenance = nextMaintenance;
        this.createTime = createTime;

    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getLastClean() {
        return lastClean;
    }

    public void setLastClean(Timestamp lastClean) {
        this.lastClean = lastClean;
    }

    public Date getNextMaintenance() {
        return nextMaintenance;
    }

    public void setNextMaintenance(Date nextMaintenance) {
        this.nextMaintenance = nextMaintenance;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomNumber='" + roomNumber + '\'' +
                ", typeId=" + typeId +
                ", floor=" + floor +
                ", viewType='" + viewType + '\'' +
                ", status='" + status + '\'' +
                ", lastClean=" + lastClean +
                ", nextMaintenance=" + nextMaintenance +
                ", createTime=" + createTime +
                '}';
    }
}