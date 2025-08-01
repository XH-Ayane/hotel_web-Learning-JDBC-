package com.hotel.test;

import com.hotel.util.JdbcUtils;
import com.hotel.dao.impl.RoomDAOImpl;
import com.hotel.dao.impl.RoomTypeDAOImpl;
import com.hotel.model.Room;
import com.hotel.model.RoomType;
import com.google.gson.Gson;
import java.sql.Connection;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        Connection cn = JdbcUtils.getConnection();
        System.out.println("数据库连接成功: " + cn);

        testRoomTypeDAOFindById();
        testRoomDAOFindAll();
        testRoomTypeDAOFindAll();
        testRoomDAOFindByStatus();
        testRoomImages();
        testRoomFindById();
    }

    public static void testRoomTypeDAOFindById() {
        RoomTypeDAOImpl dao = new RoomTypeDAOImpl();
        RoomType roomType = dao.findById(1);
        System.out.println("RoomType findById JSON: " + new Gson().toJson(roomType));
    }

    public static void testRoomTypeDAOFindAll() {
        RoomTypeDAOImpl dao = new RoomTypeDAOImpl();
        List<RoomType> roomTypes = dao.findAll();
        System.out.println("RoomType findAll JSON: " + new Gson().toJson(roomTypes));
    }

    public static void testRoomDAOFindAll() {
        RoomDAOImpl dao = new RoomDAOImpl();
        List<Room> rooms = dao.findAll();
        System.out.println("Room findAll JSON: " + new Gson().toJson(rooms));
    }

    public static void testRoomDAOFindByStatus() {
        RoomDAOImpl dao = new RoomDAOImpl();
        List<Room> rooms = dao.findByStatus("available");
        System.out.println("Room findByStatus JSON: " + new Gson().toJson(rooms));
    }

    public static void testRoomImages() {
        RoomDAOImpl dao = new RoomDAOImpl();
        List<Room> rooms = dao.findAll();
        System.out.println("测试所有房间图片字段:");
        for (Room room : rooms) {
            System.out.println("房间ID: " + room.getRoomId());
            //System.out.println("房间类型: " + room.getRoomType().getTypeName());
            System.out.println("房间状态: " + room.getStatus());
            System.out.println("房间图片列表: " + room.getRoomImages());
            System.out.println("房间平面图: " + room.getFloorPlan());
            System.out.println("------------------------");
        }
    }

    public static void testRoomFindById() {
        RoomDAOImpl dao = new RoomDAOImpl();
        // 测试查询单个房间的图片数据
        Room room = dao.findById(101); // 假设101是存在的房间ID
        if (room != null) {
            System.out.println("测试单个房间图片字段:");
            System.out.println("房间ID: " + room.getRoomId());
            //System.out.println("房间类型: " + room.getRoomType().getTypeName());
            System.out.println("房间状态: " + room.getStatus());
            System.out.println("房间图片列表: " + room.getRoomImages());
            System.out.println("房间平面图: " + room.getFloorPlan());
        } else {
            System.out.println("未找到房间ID为101的房间");
        }
    }
}
