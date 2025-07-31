package com.hotel.dao;

import com.hotel.model.Room;
import com.hotel.model.RoomType;
import java.math.BigDecimal;
import java.util.List;

/**
 * 房型数据访问接口
 * 提供对房型信息的CRUD及查询操作
 */
public interface RoomTypeDAO {

 //根据房型ID查询房型信息
 List<Room>getRoomsByTypeId(int typeId);
 /**
 * 根据房型名称查询房型信息
 * @param typeName 房型名称
 * @return 房型信息对象，若不存在则返回null
 */
RoomType findByName(String typeName);
    /**
 * 根据床型查询房型信息
 * @param bedType 床型(single/double/queen/king/twin)
 * @return 房型信息列表
 */
List<RoomType> findByBedType(String bedType);
    /**
 * 根据价格范围查询房型信息
 * @param minPrice 最低价格
 * @param maxPrice 最高价格
 * @return 房型信息列表
 */
List<RoomType> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    /**
 * 查询所有启用的房型
 * @return 启用的房型信息列表
 */
List<RoomType> findActiveTypes();
    /**
 * 查询所有房型信息
 * @return 房型信息列表
 */
List<RoomType> findAll();
    /**
 * 保存新房型信息
 * @param roomType 房型信息对象
 */
void save(RoomType roomType);
    /**
 * 更新房型信息
 * @param roomType 房型信息对象（需包含ID）
 */
void update(RoomType roomType);
    /**
 * 更新房型启用状态
 * @param typeId 房型ID
 * @param isActive 是否启用
 */
void updateStatus(int typeId, boolean isActive);
    /**
 * 根据ID删除房型信息
 * @param typeId 房型ID
 */
void delete(int typeId);

RoomType getRoomTypeByRoomId(int roomId);

RoomType findById(int typeId);
}