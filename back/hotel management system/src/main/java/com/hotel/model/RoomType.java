package com.hotel.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class RoomType {
    private int typeId;           // 房型ID
    private String typeName;      // 房型名称
    private String bedType;       // 床型(single/double/queen/king/twin)
    private int maxGuests;        // 最大住客数
    private int roomSize;         // 面积(平方英尺)
    private BigDecimal basePrice; // 基础价格
    private String description;   // 房型描述
    private boolean isActive;     // 是否启用
    private Timestamp createTime; // 创建时间
    private String coverImage; // 房型封面图
    private String imageGallery; // 房型展示图

    public RoomType() {

    }

    public RoomType(int typeId, String typeName, String bedType, int maxGuests, int roomSize, BigDecimal basePrice, String description, boolean isActive, Timestamp createTime, String coverImage, String imageGallery) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.bedType = bedType;
        this.maxGuests = maxGuests;
        this.roomSize = roomSize;
        this.basePrice = basePrice;
        this.description = description;
        this.isActive = isActive;
        this.createTime = createTime;
        this.coverImage = coverImage;
        this.imageGallery = imageGallery;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getImageGallery() {
        return imageGallery;
    }

    public void setImageGallery(String imageGallery) {
        this.imageGallery = imageGallery;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", bedType='" + bedType + '\'' +
                ", maxGuests=" + maxGuests +
                ", roomSize=" + roomSize +
                ", basePrice=" + basePrice +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", createTime=" + createTime +
                ", coverImage='" + coverImage + '\'' +
                ", imageGallery='" + imageGallery + '\'' +
                '}';
    }
}
