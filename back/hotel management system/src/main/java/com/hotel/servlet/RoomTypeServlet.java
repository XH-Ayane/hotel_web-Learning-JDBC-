package com.hotel.servlet;

import com.alibaba.fastjson.JSON;
import com.hotel.dao.impl.RoomDAOImpl;
import com.hotel.dao.impl.RoomTypeDAOImpl;
import com.hotel.model.RoomType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/roomTypeServlet")
public class RoomTypeServlet extends HttpServlet {

    private RoomTypeDAOImpl roomTypeDAO = new RoomTypeDAOImpl();
    private RoomDAOImpl roomDAO = new RoomDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if("getRoomTypeById".equals(method)) {
            getRoomTypeById(req, resp);
        } else if("getRoomTypesByRoomId".equals(method)) {
            getRoomTypesByRoomId(req, resp);
        } else if("getAllRoomTypes".equals(method)) {
            getAllRoomTypes(req, resp);
        }
    }

    private void getRoomTypeById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String typeIdStr = req.getParameter("typeId");
        if(typeIdStr == null || typeIdStr.isEmpty()) {
            HashMap<String,Object> map=new HashMap<>();
            map.put("message", "fail");
            map.put("error", "Type ID is required");
            resp.getWriter().write(JSON.toJSONString(map));
            return;
        }

        int typeId = Integer.parseInt(typeIdStr);
        RoomType roomType = roomTypeDAO.findById(typeId);

        Map<String, Object> map = new HashMap<>();
        if(roomType != null) {
            map.put("message", "success");
            map.put("data", roomType);
        } else {
            map.put("message", "fail");
            map.put("error", "Room type not found");
        }
        resp.getWriter().write(JSON.toJSONString(map));
    }

    private void getRoomTypesByRoomId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String roomIdStr = req.getParameter("roomId");
        if(roomIdStr == null || roomIdStr.isEmpty()) {
            //resp.getWriter().write(JSON.toJSONString(Map.of("message", "fail", "error", "Room ID is required")));
            HashMap<String,Object> map=new HashMap<>();
            map.put("message", "fail");
            map.put("error", "Room ID is required");
            resp.getWriter().write(JSON.toJSONString(map));
            return;
        }

        int roomId = Integer.parseInt(roomIdStr);
        RoomType roomType = roomTypeDAO.getRoomTypeByRoomId(roomId);

        Map<String, Object> map = new HashMap<>();
        if(roomType != null) {
            map.put("message", "success");
            map.put("data", roomType);
        } else {
            map.put("message", "fail");
            map.put("error", "Room type not found for this room");
        }
        resp.getWriter().write(JSON.toJSONString(map));
    }

    private void getAllRoomTypes(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<RoomType> roomTypes = roomTypeDAO.findAll(); // 假设有findAll方法

        Map<String, Object> map = new HashMap<>();
        map.put("message", "success");
        map.put("data", roomTypes);
        resp.getWriter().write(JSON.toJSONString(map));
    }
}
