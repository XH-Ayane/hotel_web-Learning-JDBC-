package com.hotel.model;

public class RoomImage {
    private int id;               // 图片ID
    private int roomId;          // 房间ID
    private String imageUrl;      // 图片URL
    private String imageType;     // 图片类型(view/bed/bathroom/living/terrace/floor_plan)
    private int sortOrder;       // 排序顺序

    public RoomImage() {

    }

    public RoomImage(int id, int roomId, String imageUrl, String imageType, int sortOrder) {
        this.id = id;
        this.roomId = roomId;
        this.imageUrl = imageUrl;
        this.imageType = imageType;
        this.sortOrder = sortOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        return "RoomImage{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageType='" + imageType + '\'' +
                ", sortOrder=" + sortOrder +
                '}';
    }
}