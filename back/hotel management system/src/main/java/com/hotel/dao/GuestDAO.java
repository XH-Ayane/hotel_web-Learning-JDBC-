package com.hotel.dao;

import com.hotel.model.Guest;
import java.util.List;

/**
 * 客人信息数据访问接口
 * 提供对客人信息的CRUD及查询操作
 */
public interface GuestDAO {
    /**
 * 根据客人ID查询客人信息
 * @param guestId 客人ID
 * @return 客人信息对象，若不存在则返回null
 */
Guest findById(int guestId);
    /**
 * 查询所有客人信息
 * @return 客人信息列表
 */
List<Guest> findAll();
    /**
 * 保存新客人信息
 * @param guest 客人信息对象
 */
void save(Guest guest);
    /**
 * 更新客人信息
 * @param guest 客人信息对象（需包含ID）
 */
void update(Guest guest);
    /**
 * 根据ID删除客人信息
 * @param guestId 客人ID
 */
void delete(int guestId);
    /**
 * 根据手机号查询客人信息
 * @param phone 手机号
 * @return 客人信息对象，若不存在则返回null
 */
Guest findByPhone(String phone);
    /**
 * 根据邮箱查询客人信息
 * @param email 邮箱地址
 * @return 客人信息对象，若不存在则返回null
 */
Guest findByEmail(String email);
}