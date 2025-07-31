package com.hotel.dao;

import com.hotel.model.Room;
import java.util.List;

/**
 * 房间数据访问接口
 * 提供对房间信息的CRUD及查询操作
 */
public interface RoomDAO {
    /**
     * 根据房间ID查询房间信息
     *
     * @param roomId 房间ID
     * @return 房间信息对象，若不存在则返回null
     */
    Room findById(int roomId);

    /**
     * 根据房型ID查询房间信息
     *
     * @param typeId 房型ID
     * @return 房间信息列表
     */
    List<Room> findByTypeId(int typeId);

    /**
     * 根据房间状态查询房间信息
     *
     * @param status 房间状态(available/occupied/maintenance/cleaning)
     * @return 房间信息列表
     */
    List<Room> findByStatus(String status);

    /**
     * 查询所有房间信息
     *
     * @return 房间信息列表
     */
    List<Room> findAll();

    /**
     * 保存新房间信息
     *
     * @param room 房间信息对象
     */
    void save(Room room);

    /**
     * 更新房间信息
     *
     * @param room 房间信息对象（需包含ID）
     */
    int update(Room room);

    /**
     * 更新房间状态
     *
     * @param roomId 房间ID
     * @param status 房间状态
     */
    void updateStatus(int roomId, String status);

    /**
     * 根据ID删除房间信息
     *
     * @param roomId 房间ID
     */
    void delete(int roomId);

    int add(Room room);

    /*分页查询所有的房间信息*/
    List<Room> findAllRooms(int offset, int pageSize);

    /*查询犯贱总数方法*/
    int countAllRooms();
}