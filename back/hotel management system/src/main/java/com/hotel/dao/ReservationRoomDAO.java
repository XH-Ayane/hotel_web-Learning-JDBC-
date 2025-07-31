package com.hotel.dao;

import com.hotel.model.ReservationRoom;
import java.util.List;

/**
 * 预订房间关联数据访问接口
 * 提供对预订与房间关联信息的CRUD及查询操作
 */
public interface ReservationRoomDAO {
    /**
 * 根据ID查询预订房间关联信息
 * @param id 关联ID
 * @return 预订房间关联对象，若不存在则返回null
 */
ReservationRoom findById(int id);
    /**
 * 根据预订ID查询关联房间信息
 * @param reservationId 预订ID
 * @return 预订房间关联列表
 */
List<ReservationRoom> findByReservationId(int reservationId);
    /**
 * 根据房间ID查询关联预订信息
 * @param roomId 房间ID
 * @return 预订房间关联列表
 */
List<ReservationRoom> findByRoomId(int roomId);
    /**
 * 根据状态查询预订房间关联信息
 * @param status 状态(reserved/checked_in/checked_out)
 * @return 预订房间关联列表
 */
List<ReservationRoom> findByStatus(String status);
    /**
 * 保存预订房间关联信息
 * @param reservationRoom 预订房间关联对象
 */
void save(ReservationRoom reservationRoom);
    /**
 * 更新预订房间关联信息
 * @param reservationRoom 预订房间关联对象（需包含ID）
 */
void update(ReservationRoom reservationRoom);
    /**
 * 根据ID删除预订房间关联信息
 * @param id 关联ID
 */
void delete(int id);
}