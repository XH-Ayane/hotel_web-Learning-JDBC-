package com.hotel.dao;

import com.hotel.model.Reservation;
import java.util.List;

public interface ReservationDAO {
    /**
     * 保存预订信息
     */
    int save(Reservation reservation) throws Exception;

    /**
     * 更新预订信息
     */
    int update(Reservation reservation) throws Exception;

    /**
     * 根据ID删除预订
     */
    int delete(Integer reservationId) throws Exception;

    /**
     * 根据ID查询预订
     */
    Reservation findById(Integer reservationId) throws Exception;

    /**
     * 根据状态查询预订
     */
    List<Reservation> findByStatus(String status) throws Exception;

    /**
     * 获取所有预订
     */
    List<Reservation> findAll() throws Exception;
}