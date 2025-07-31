package com.hotel.dao;

import com.hotel.model.RoomTypeImage;
import java.util.List;

/**
 * 房型图片数据访问接口
 * 提供对房型图片信息的CRUD及查询操作
 */
public interface RoomTypeImageDAO {
    /**
 * 根据ID查询房型图片信息
 * @param id 图片ID
 * @return 房型图片对象，若不存在则返回null
 */
RoomTypeImage findById(int id);
    /**
 * 根据房型ID查询图片列表
 * @param typeId 房型ID
 * @return 房型图片列表
 */
List<RoomTypeImage> findByTypeId(int typeId);
    /**
 * 查询房型的封面图片
 * @param typeId 房型ID
 * @return 封面图片对象，若不存在则返回null
 */
RoomTypeImage findCoverByTypeId(int typeId);
    /**
 * 保存房型图片信息
 * @param roomTypeImage 房型图片对象
 */
void save(RoomTypeImage roomTypeImage);
    /**
 * 更新房型图片信息
 * @param roomTypeImage 房型图片对象（需包含ID）
 */
void update(RoomTypeImage roomTypeImage);
    /**
 * 根据ID删除房型图片
 * @param id 图片ID
 */
void delete(int id);
    /**
 * 更新房型封面图片
 * @param typeId 房型ID
 * @param imageId 图片ID
 */
void updateCoverImage(int typeId, int imageId);
    /**
 * 批量更新房型图片排序顺序
 * @param roomTypeImages 包含更新排序的图片列表
 */
void batchUpdateSortOrder(List<RoomTypeImage> roomTypeImages);
}