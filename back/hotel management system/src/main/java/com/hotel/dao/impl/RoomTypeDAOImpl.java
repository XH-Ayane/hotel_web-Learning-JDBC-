package com.hotel.dao.impl;

import com.hotel.dao.RoomTypeDAO;
import com.hotel.model.Room;
import com.hotel.model.RoomType;
import com.hotel.util.JdbcUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDAOImpl implements RoomTypeDAO {

    @Override
    public List<Room> getRoomsByTypeId(int typeId) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Room> list = new ArrayList<>();

        try {
            cn = JdbcUtils.getConnection();
            ps = cn.prepareStatement("SELECT * FROM room WHERE type_id=?");
            ps.setInt(1, typeId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Room room = new Room();
                room.setRoomId(rs.getInt("room_id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setTypeId(rs.getInt("type_id"));
                room.setFloor(rs.getInt("floor"));
                room.setViewType(rs.getString("view_type"));
                room.setStatus(rs.getString("status"));
                room.setLastClean(rs.getTimestamp("last_clean"));
                room.setNextMaintenance(rs.getDate("next_maintenance"));
                room.setCreateTime(rs.getTimestamp("create_time"));
                // room.setRoomImages(rs.getString("room_images"));
                // room.setFloorPlan(rs.getString("floor_plan"));

                list.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, cn);
        }
        return list;
    }

    @Override
    public RoomType findById(int typeId) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        RoomType roomType = null;
        try {
            cn = JdbcUtils.getConnection();
            String sql = "SELECT * FROM room_type WHERE type_id = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, typeId);
            rs = ps.executeQuery();
            if (rs.next()) {
                roomType = new RoomType();
                roomType.setTypeId(rs.getInt("type_id"));
                roomType.setTypeName(rs.getString("type_name"));
                roomType.setBedType(rs.getString("bed_type"));
                roomType.setMaxGuests(rs.getInt("max_guests"));
                roomType.setRoomSize(rs.getInt("room_size"));
                roomType.setBasePrice(rs.getBigDecimal("base_price"));
                roomType.setDescription(rs.getString("description"));
                roomType.setActive(rs.getBoolean("is_active"));
                roomType.setCreateTime(rs.getTimestamp("create_time"));
                // roomType.setCoverImage(rs.getString("cover_image"));
                // roomType.setImageGallery(rs.getString("image_gallery"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, cn);
        }
        return roomType;
    }

    @Override
    public RoomType findByName(String typeName) {
        return null;
    }

    @Override
    public List<RoomType> findByBedType(String bedType) {
        return null;
    }

    @Override
    public List<RoomType> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return null;
    }

    @Override
    public List<RoomType> findActiveTypes() {
        return null;
    }

    @Override
    public List<RoomType> findAll() {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<RoomType> roomTypes = new ArrayList<>();

        try {
            cn = JdbcUtils.getConnection();
            String sql = "SELECT * FROM room_type";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                RoomType roomType = new RoomType();
                roomType.setTypeId(rs.getInt("type_id"));
                roomType.setTypeName(rs.getString("type_name"));
                roomType.setBedType(rs.getString("bed_type"));
                roomType.setMaxGuests(rs.getInt("max_guests"));
                roomType.setRoomSize(rs.getInt("room_size"));
                roomType.setBasePrice(rs.getBigDecimal("base_price"));
                roomType.setDescription(rs.getString("description"));
                roomType.setActive(rs.getBoolean("is_active"));
                roomType.setCreateTime(rs.getTimestamp("create_time"));
                // roomType.setCoverImage(rs.getString("cover_image"));
                // roomType.setImageGallery(rs.getString("image_gallery"));

                roomTypes.add(roomType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, cn);
        }
        return roomTypes;
    }

    @Override
    public void save(RoomType roomType) {

    }

    @Override
    public void update(RoomType roomType) {

    }

    @Override
    public void updateStatus(int typeId, boolean isActive) {

    }

    @Override
    public void delete(int typeId) {

    }

    @Override
    public RoomType getRoomTypeByRoomId(int roomId) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        RoomType roomType = null;

        try {
            cn = JdbcUtils.getConnection();
            String sql = "SELECT rt.* FROM room_type rt " +
                    "JOIN room r ON rt.type_id = r.type_id " +
                    "WHERE r.room_id=?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, roomId);
            rs = ps.executeQuery();

            if (rs.next()) {
                roomType = new RoomType();
                roomType.setTypeId(rs.getInt("type_id"));
                roomType.setTypeName(rs.getString("type_name"));
                roomType.setBedType(rs.getString("bed_type"));
                roomType.setMaxGuests(rs.getInt("max_guests"));
                roomType.setRoomSize(rs.getInt("room_size"));
                roomType.setBasePrice(rs.getBigDecimal("base_price"));
                roomType.setDescription(rs.getString("description"));
                roomType.setActive(rs.getBoolean("is_active"));
                roomType.setCreateTime(rs.getTimestamp("create_time"));
                // roomType.setCoverImage(rs.getString("cover_image"));
                // roomType.setImageGallery(rs.getString("image_gallery"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, cn);
        }
        return roomType;
    }
}
