package com.hotel.dao;

import com.hotel.model.HotelStaff;
import java.util.List;

/**
 * 酒店员工数据访问接口
 * 提供对员工信息的CRUD及查询操作
 */
public interface HotelStaffDAO {
    /**
 * 根据员工ID查询员工信息
 * @param staffId 员工ID
 * @return 员工信息对象，若不存在则返回null
 */
HotelStaff findById(int staffId);
    /**
 * 根据工号查询员工信息
 * @param staffNo 工号
 * @return 员工信息对象，若不存在则返回null
 */
HotelStaff findByStaffNo(String staffNo);
    /**
 * 根据部门查询员工信息
 * @param department 部门名称
 * @return 员工信息列表
 */
List<HotelStaff> findByDepartment(String department);
    /**
 * 查询所有员工信息
 * @return 员工信息列表
 */
List<HotelStaff> findAll();
    /**
 * 保存新员工信息
 * @param staff 员工信息对象
 */
void save(HotelStaff staff);
    /**
 * 更新员工信息
 * @param staff 员工信息对象（需包含ID）
 */
void update(HotelStaff staff);
    /**
 * 根据ID删除员工信息
 * @param staffId 员工ID
 */
void delete(int staffId);
    /**
 * 根据联系方式查询员工信息
 * @param contact 联系电话
 * @return 员工信息对象，若不存在则返回null
 */
HotelStaff findByContact(String contact);
}