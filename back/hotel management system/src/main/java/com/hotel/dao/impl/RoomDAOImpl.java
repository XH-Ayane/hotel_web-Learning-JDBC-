package com.hotel.dao.impl;

import com.hotel.dao.RoomDAO;
import com.hotel.model.Room;
import com.hotel.model.RoomType;
import com.hotel.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    @Override
    public Room findById(int roomId) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Room room = null;
        try {
            cn = JdbcUtils.getConnection();
            String sql = "SELECT * FROM room WHERE room_id = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, roomId);
            rs = ps.executeQuery();

            if (rs.next()) {
                room = new Room();
                room.setRoomId(rs.getInt("room_id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setTypeId(rs.getInt("type_id"));
                room.setFloor(rs.getInt("floor"));
                room.setViewType(rs.getString("view_type"));
                room.setStatus(rs.getString("status"));
                room.setLastClean(rs.getTimestamp("last_clean"));
                room.setNextMaintenance(rs.getDate("next_maintenance"));
                room.setCreateTime(rs.getTimestamp("create_time"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, cn);
        }
        return room;
    }

    @Override
    public List<Room> findByTypeId(int typeId) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Room> rooms = new ArrayList<>();
        try {
            cn = JdbcUtils.getConnection();
            String sql = "SELECT * FROM room WHERE type_id = ?";
            ps = cn.prepareStatement(sql);
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
                rooms.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, cn);
        }
        return rooms;
    }

    @Override
    public List<Room> findByStatus(String status) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Room> rooms = new ArrayList<>();
        try {
            cn = JdbcUtils.getConnection();
            String sql = "SELECT * FROM room WHERE status = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, status);
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
                rooms.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, cn);
        }
        return rooms;
    }

    @Override
    public List<Room> findAll() {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Room> rooms = new ArrayList<>();
        try {
            cn = JdbcUtils.getConnection();
            String sql = "SELECT * FROM room";
            ps = cn.prepareStatement(sql);
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
                rooms.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, cn);
        }
        return rooms;
    }

    @Override
    public void save(Room room) {
        add(room);
    }

    @Override
    public int update(Room room) {
        Connection cn = null;
        PreparedStatement ps = null;
        int result = 0;

        try {
            cn = JdbcUtils.getConnection();
            String sql = "UPDATE room SET room_number=?, type_id=?, floor=?, view_type=?, " +
                    "status=?, last_clean=?, next_maintenance=? " + // 添加空格
                    "WHERE room_id=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, room.getRoomNumber());
            ps.setInt(2, room.getTypeId());
            ps.setInt(3, room.getFloor());
            ps.setString(4, room.getViewType());
            ps.setString(5, room.getStatus());
            if (room.getLastClean() != null) {
                ps.setTimestamp(6, new java.sql.Timestamp(room.getLastClean().getTime()));
            } else {
                ps.setNull(6, java.sql.Types.TIMESTAMP);
            }

            if (room.getNextMaintenance() != null) {
                ps.setDate(7, new java.sql.Date(room.getNextMaintenance().getTime()));
            } else {
                ps.setNull(7, java.sql.Types.DATE);
            }
            // ps.setString(8, room.getRoomImages());
            // ps.setString(9, room.getFloorPlan());
            ps.setInt(8, room.getRoomId());

            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(null, ps, cn);
        }
        return result;
    }

    @Override
    public void updateStatus(int roomId, String status) {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = JdbcUtils.getConnection();
            String sql = "UPDATE room SET status = ? WHERE room_id = ?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, roomId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(null, ps, cn);
        }
    }

    @Override
    public void delete(int roomId) {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = JdbcUtils.getConnection();
            String sql = "DELETE FROM room WHERE room_id = ?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, roomId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(null, ps, cn);
        }
    }

    @Override
    public int add(Room room) {
        Connection cn = null;
        PreparedStatement ps = null;
        int result = 0;

        try {
            cn = JdbcUtils.getConnection();
            String sql = "INSERT INTO room (room_number, type_id, floor, view_type, status, " +
                    "last_clean, next_maintenance, create_time) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = cn.prepareStatement(sql);
            ps.setString(1, room.getRoomNumber());
            ps.setInt(2, room.getTypeId());
            ps.setInt(3, room.getFloor());
            ps.setString(4, room.getViewType());
            ps.setString(5, room.getStatus());
            if (room.getLastClean() != null) {
                ps.setTimestamp(6, new java.sql.Timestamp(room.getLastClean().getTime()));
            } else {
                ps.setNull(6, java.sql.Types.TIMESTAMP);
            }

            if (room.getNextMaintenance() != null) {
                ps.setDate(7, new java.sql.Date(room.getNextMaintenance().getTime()));
            } else {
                ps.setNull(7, java.sql.Types.DATE);
            }

            if (room.getCreateTime() != null) {
                ps.setTimestamp(8, new java.sql.Timestamp(room.getCreateTime().getTime()));
            } else {
                ps.setTimestamp(8, new java.sql.Timestamp(System.currentTimeMillis()));
            }
            // ps.setString(9, room.getRoomImages());
            // ps.setString(10, room.getFloorPlan());

            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(null, ps, cn);
        }
        return result;
    }

    @Override
    public List<Room> findAllRooms(int offset, int pageSize) {
        return findAllRooms(offset, pageSize, null, null, null);
    }

    public List<Room> findAllRooms(int offset, int pageSize, String keyword, Integer typeId, String status) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Room> list = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        try {
            cn = JdbcUtils.getConnection();
            StringBuilder sql = new StringBuilder(
                    "SELECT r.*, rt.type_name, rt.bed_type, rt.max_guests, rt.base_price " +
                            "FROM room r LEFT JOIN room_type rt ON r.type_id = rt.type_id WHERE 1=1");

            // 添加搜索条件
            if (keyword != null && !keyword.isEmpty()) {
                sql.append(" AND r.room_number LIKE ?");
                params.add("%" + keyword + "%");
            }
            if (typeId != null) {
                sql.append(" AND r.type_id = ?");
                params.add(typeId);
            }
            if (status != null && !status.isEmpty()) {
                sql.append(" AND r.status = ?");
                params.add(status);
            }

            sql.append(" ORDER BY r.room_id ASC LIMIT ?, ?");
            params.add(offset);
            params.add(pageSize);

            ps = cn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                Room room = new Room();
                RoomType roomType = new RoomType();
                room.setRoomId(rs.getInt("room_id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setTypeId(rs.getInt("type_id"));
                room.setFloor(rs.getInt("floor"));
                room.setViewType(rs.getString("view_type"));
                room.setStatus(rs.getString("status"));
                room.setLastClean(rs.getTimestamp("last_clean"));
                room.setNextMaintenance(rs.getDate("next_maintenance"));
                room.setCreateTime(rs.getTimestamp("create_time"));

                // 设置从room_type表查询的字段
                roomType.setTypeName(rs.getString("type_name"));
                roomType.setBedType(rs.getString("bed_type"));
                roomType.setMaxGuests(rs.getInt("max_guests"));
                roomType.setBasePrice(rs.getBigDecimal("base_price"));

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
    public int countAllRooms() {
        return countRoomsWithFilters(null, null, null);
    }

    public int countRoomsWithFilters(String keyword, Integer typeId, String status) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        List<Object> params = new ArrayList<>();

        try {
            cn = JdbcUtils.getConnection();
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM room WHERE 1=1");

            // 添加搜索条件
            if (keyword != null && !keyword.isEmpty()) {
                sql.append(" AND room_number LIKE ?");
                params.add("%" + keyword + "%");
            }
            if (typeId != null) {
                sql.append(" AND type_id = ?");
                params.add(typeId);
            }
            if (status != null && !status.isEmpty()) {
                sql.append(" AND status = ?");
                params.add(status);
            }

            ps = cn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, cn);
        }
        return count;
    }
}
