package com.hotel.model;

 public class RoomTypeImage {
    private int id;               // 图片ID
    private int typeId;           // 房型ID
    private String imageUrl;      // 图片URL
    private boolean isCover;      // 是否封面图
    private int sortOrder;       // 排序顺序

     public RoomTypeImage() {

     }

     public RoomTypeImage(int id, int typeId, String imageUrl, boolean isCover, int sortOrder) {
         this.id = id;
         this.typeId = typeId;
         this.imageUrl = imageUrl;
         this.isCover = isCover;
         this.sortOrder = sortOrder;
     }

     public int getId() {
         return id;
     }

     public void setId(int id) {
         this.id = id;
     }

     public int getTypeId() {
         return typeId;
     }

     public void setTypeId(int typeId) {
         this.typeId = typeId;
     }

     public String getImageUrl() {
         return imageUrl;
     }

     public void setImageUrl(String imageUrl) {
         this.imageUrl = imageUrl;
     }

     public boolean isCover() {
         return isCover;
     }

     public void setCover(boolean cover) {
         isCover = cover;
     }

     public int getSortOrder() {
         return sortOrder;
     }

     public void setSortOrder(int sortOrder) {
         this.sortOrder = sortOrder;
     }

     @Override
     public String toString() {
         return "RoomTypeImage{" +
                 "id=" + id +
                 ", typeId=" + typeId +
                 ", imageUrl='" + imageUrl + '\'' +
                 ", isCover=" + isCover +
                 ", sortOrder=" + sortOrder +
                 '}';
     }
 }