package com.hotel.servlet;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.hotel.dao.impl.RoomDAOImpl;
import com.hotel.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotel.dao.impl.RoomTypeDAOImpl; // 需要添加的导入语句

@WebServlet("/RoomServlet")
public class RoomServlet extends HttpServlet {
    // 成員變量定義
    private RoomDAOImpl roomDAO = new RoomDAOImpl();
    private RoomTypeDAOImpl roomTypeDAOImpl = new RoomTypeDAOImpl();

    // GET && POST
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 參數請求的參數調用那個方法
        String method = req.getParameter("method");
        if ("getRoomsByPage".equals(method)) {
            getRoomsByPage(req, resp);
        } else if ("getRoomById".equals(method)) {
            getRoomById(req, resp);
        } else if ("saveRoom".equals(method)) {
            saveRoom(req, resp);
        } else if ("editRoom".equals(method)) {
            editRoom(req, resp);
        } else if ("updateRoomStatus".equals(method)) {
            updateRoomStatus(req, resp);
        } else if ("deleteRooms".equals(method)) {
            deleteRooms(req, resp);
        } else if ("deleteRoom".equals(method)) {
            deleteRoom(req, resp);
        }
    }

    // 自定義業務方法，分頁獲取房間
    private void getRoomsByPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        String keyword = req.getParameter("keyword");
        String typeIdStr = req.getParameter("typeId");
        String status = req.getParameter("status");

        int pageNo = currentPageStr == null || currentPageStr.isEmpty() ? 1 : Integer.parseInt(currentPageStr);
        int pageSize = pageSizeStr == null || pageSizeStr.isEmpty() ? 10 : Integer.parseInt(pageSizeStr);
        int offset = (pageNo - 1) * pageSize;
        Integer typeId = null;
        if (typeIdStr != null && !typeIdStr.isEmpty()) {
            try {
                typeId = Integer.parseInt(typeIdStr);
            } catch (NumberFormatException e) {
                // 处理类型ID格式错误，默认为null
            }
        }

        // 使用筛选参数调用DAO方法
        List<Room> roomList = roomDAO.findAllRooms(offset, pageSize, keyword, typeId, status);
        int totalCount = roomDAO.countRoomsWithFilters(keyword, typeId, status);

        // 手动创建PageInfo对象
        PageInfo<Room> pageInfo = new PageInfo<>();
        pageInfo.setList(roomList);
        pageInfo.setPageNum(pageNo);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(totalCount);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "success");
        map.put("data", pageInfo);
        resp.getWriter().write(JSON.toJSONString(map));
    }

    private void getRoomById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String roomIdStr = req.getParameter("roomId");
        if (roomIdStr == null || roomIdStr.isEmpty()) {
            // resp.getWriter().write(JSON.toJSONString(Map.of("message", "fail", "error",
            // "Room ID is required")));
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "fail");
            map.put("error", "Room ID is required");
            resp.getWriter().write(JSON.toJSONString(map));
            return;
        }

        int roomId = Integer.parseInt(roomIdStr);
        Room room = roomDAO.findById(roomId);

        Map<String, Object> map = new HashMap<>();
        if (room != null) {
            map.put("message", "success");
            map.put("data", room);
        } else {
            map.put("message", "fail");
            map.put("error", "Room not found");
        }
        resp.getWriter().write(JSON.toJSONString(map));
    }

    private void saveRoom(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Room room = parseRoomFromRequest(req);
            int result = roomDAO.add(room);

            Map<String, Object> map = new HashMap<>();
            if (result > 0) {
                map.put("message", "success");
                map.put("roomId", room.getRoomId());
                map.put("tip", "房间创建成功，可上传图片");
            } else {
                map.put("message", "fail");
                map.put("error", "Failed to save room");
            }
            resp.getWriter().write(JSON.toJSONString(map));
        } catch (NumberFormatException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "fail");
            map.put("error", "Invalid numeric parameter: " + e.getMessage());
            resp.getWriter().write(JSON.toJSONString(map));
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> map = new HashMap<>();
            map.put("message", "fail");
            map.put("error", "Server error: " + e.getMessage());
            resp.getWriter().write(JSON.toJSONString(map));
        }
    }

    private void editRoom(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Room room = parseRoomFromRequest(req);
            int result = roomDAO.update(room);

            Map<String, Object> map = new HashMap<>();
            if (result > 0) {
                map.put("message", "success");
                map.put("tip", "房间更新成功，图片信息已同步更新");
            } else {
                map.put("message", "fail");
                map.put("error", "Failed to update room");
            }
            resp.getWriter().write(JSON.toJSONString(map));
        } catch (NumberFormatException e) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "fail");
            map.put("error", "Invalid numeric parameter: " + e.getMessage());
            resp.getWriter().write(JSON.toJSONString(map));
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> map = new HashMap<>();
            map.put("message", "fail");
            map.put("error", "Server error: " + e.getMessage());
            resp.getWriter().write(JSON.toJSONString(map));
        }
    }

    private void updateRoomStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String roomIdStr = req.getParameter("roomId");
        String status = req.getParameter("status");

        if (roomIdStr == null || roomIdStr.isEmpty() || status == null || status.isEmpty()) {
            // resp.getWriter().write(JSON.toJSONString(Map.of("message", "fail", "error",
            // "Room ID and status are required")));
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "fail");
            map.put("error", "Room ID and status are required");
            resp.getWriter().write(JSON.toJSONString(map));
            return;
        }

        int roomId = Integer.parseInt(roomIdStr);
        roomDAO.updateStatus(roomId, status);

        Map<String, Object> map = new HashMap<>();
        map.put("message", "success");
        resp.getWriter().write(JSON.toJSONString(map));
    }

    private void deleteRooms(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String roomIdsStr = req.getParameter("roomIds");
        if (roomIdsStr == null || roomIdsStr.isEmpty()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "fail");
            map.put("error", "Room IDs are required");
            resp.getWriter().write(JSON.toJSONString(map));
            return;
        }

        String[] roomIds = roomIdsStr.split(",");
        for (String id : roomIds) {
            roomDAO.delete(Integer.parseInt(id));
        }

        Map<String, Object> map = new HashMap<>();
        map.put("message", "success");
        resp.getWriter().write(JSON.toJSONString(map));
    }

    private void deleteRoom(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String roomIdStr = req.getParameter("roomId");
        if (roomIdStr == null || roomIdStr.isEmpty()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "fail");
            map.put("error", "Room ID is required");
            resp.getWriter().write(JSON.toJSONString(map));
            return;
        }

        int roomId = Integer.parseInt(roomIdStr);
        try {
            // 删除房间及其关联的图片
            roomDAO.delete(roomId);

            Map<String, Object> map = new HashMap<>();
            map.put("message", "success");
            map.put("tip", "房间已删除，相关图片也已清理");
            resp.getWriter().write(JSON.toJSONString(map));
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> map = new HashMap<>();
            map.put("message", "fail");
            map.put("error", "Failed to delete room: " + e.getMessage());
            resp.getWriter().write(JSON.toJSONString(map));
        }
    }

    private Room parseRoomFromRequest(HttpServletRequest req) throws NumberFormatException {
        Room room = new Room();

        String roomIdStr = req.getParameter("roomId");
        if (roomIdStr != null && !roomIdStr.isEmpty()) {
            room.setRoomId(Integer.parseInt(roomIdStr));
        }

        room.setRoomNumber(req.getParameter("roomNumber"));
        room.setTypeId(Integer.parseInt(req.getParameter("typeId")));
        room.setFloor(Integer.parseInt(req.getParameter("floor")));
        room.setViewType(req.getParameter("viewType"));
        room.setStatus(req.getParameter("status"));
        room.setRoomImages(req.getParameter("roomImages"));
        room.setFloorPlan(req.getParameter("floorPlan"));

        // 处理日期字段
        String lastCleanStr = req.getParameter("lastClean");
        if (lastCleanStr != null && !lastCleanStr.isEmpty()) {
            try {
                room.setLastClean(new java.sql.Timestamp(new java.util.Date(Long.parseLong(lastCleanStr)).getTime()));
            } catch (Exception e) {
                // 日期格式错误，忽略
            }
        }

        String nextMaintenanceStr = req.getParameter("nextMaintenance");
        if (nextMaintenanceStr != null && !nextMaintenanceStr.isEmpty()) {
            try {
                room.setNextMaintenance(new java.util.Date(Long.parseLong(nextMaintenanceStr)));
            } catch (Exception e) {
                // 日期格式错误，忽略
            }
        }

        return room;
    }
}
