package com.hotel.dao;

import com.hotel.model.RoomImage;
import java.util.List;

/**
 * 房间图片数据访问接口
 * 提供对房间图片信息的CRUD及查询操作
 */
public interface RoomImageDAO {
    /**
 * 根据ID查询房间图片信息
 * @param id 图片ID
 * @return 房间图片对象，若不存在则返回null
 */
RoomImage findById(int id);
    /**
 * 根据房间ID查询图片列表
 * @param roomId 房间ID
 * @return 房间图片列表
 */
List<RoomImage> findByRoomId(int roomId);
    /**
 * 根据房间ID和图片类型查询图片
 * @param roomId 房间ID
 * @param imageType 图片类型(view/bed/bathroom/living/terrace/floor_plan)
 * @return 房间图片列表
 */
List<RoomImage> findByImageType(int roomId, String imageType);
    /**
 * 保存房间图片信息
 * @param roomImage 房间图片对象
 */
void save(RoomImage roomImage);
    /**
 * 更新房间图片信息
 * @param roomImage 房间图片对象（需包含ID）
 */
void update(RoomImage roomImage);
    /**
 * 根据ID删除房间图片
 * @param id 图片ID
 */
void delete(int id);
    /**
 * 批量更新图片排序顺序
 * @param roomImages 包含更新排序的图片列表
 */
void batchUpdateSortOrder(List<RoomImage> roomImages);
}