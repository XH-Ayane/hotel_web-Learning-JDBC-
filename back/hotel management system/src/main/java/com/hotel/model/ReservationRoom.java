package com.hotel.model;

import java.math.BigDecimal;

public class ReservationRoom {
    private int id;               // 关联ID
    private int reservationId;    // 预订ID
    private int roomId;           // 房间ID
    private BigDecimal rate;      // 实际房价
    private String status;        // 状态(reserved/checked_in/checked_out)

    public ReservationRoom() {

    }

    public ReservationRoom(int id, int reservationId, int roomId, BigDecimal rate, String status) {
        this.id = id;
        this.reservationId = reservationId;
        this.roomId = roomId;
        this.rate = rate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReservationRoom{" +
                "id=" + id +
                ", reservationId=" + reservationId +
                ", roomId=" + roomId +
                ", rate=" + rate +
                ", status='" + status + '\'' +
                '}';
    }
}
