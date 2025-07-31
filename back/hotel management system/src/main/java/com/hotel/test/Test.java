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
    public static void main(String[] args)throws Exception {
        Connection cn = JdbcUtils.getConnection();
        System.out.println("数据库连接成功: " + cn);
        
        testRoomTypeDAOFindById();
        testRoomDAOFindAll();
        testRoomTypeDAOFindAll();
        testRoomDAOFindByStatus();
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
}
