-- 酒店人员表
CREATE TABLE `hotel_staff` (
  `staff_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '员工ID',
  `staff_no` VARCHAR(20) NOT NULL COMMENT '工号',
  `name` VARCHAR(50) NOT NULL COMMENT '姓名',
  `password` VARCHAR(100) NOT NULL COMMENT '密码(加密)',
  `position` VARCHAR(50) NOT NULL COMMENT '职位',
  `department` VARCHAR(50) NOT NULL COMMENT '部门',
  `contact` VARCHAR(20) NOT NULL COMMENT '联系电话',
  `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态(0禁用1启用)',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`staff_id`),
  UNIQUE KEY `idx_staff_no` (`staff_no`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

-- 房型分类表
CREATE TABLE `room_type` (
  `type_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '房型ID',
  `type_name` VARCHAR(50) NOT NULL COMMENT '房型名称',
  `bed_type` ENUM('single','double','queen','king','twin') NOT NULL COMMENT '床型',
  `max_guests` TINYINT(2) NOT NULL COMMENT '最大住客数',
  `room_size` INT(5) NOT NULL COMMENT '面积(平方英尺)',
  `base_price` DECIMAL(10,2) NOT NULL COMMENT '基础价格',
  `description` TEXT COMMENT '房型描述',
  `is_active` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`type_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

-- 房间表
CREATE TABLE `room` (
  `room_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '房间ID',
  `room_number` VARCHAR(10) NOT NULL COMMENT '房间号',
  `type_id` INT(11) NOT NULL COMMENT '房型ID',
  `floor` TINYINT(3) NOT NULL COMMENT '所在楼层',
  `view_type` ENUM('city','garden','lake','sea','mountain') NOT NULL COMMENT '景观类型',
  `status` ENUM('available','occupied','maintenance','cleaning') NOT NULL DEFAULT 'available' COMMENT '房间状态',
  `last_clean` DATETIME DEFAULT NULL COMMENT '最后清洁时间',
  `next_maintenance` DATE DEFAULT NULL COMMENT '下次维护日期',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`room_id`),
  UNIQUE KEY `idx_room_number` (`room_number`),
  FOREIGN KEY (`type_id`) REFERENCES `room_type` (`type_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

-- 客户表
CREATE TABLE `guest` (
  `guest_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `name` VARCHAR(50) NOT NULL COMMENT '姓名',
  `phone` VARCHAR(20) NOT NULL COMMENT '手机号',
  `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
  `address` VARCHAR(200) DEFAULT NULL COMMENT '地址',
  `member_level` ENUM('regular','silver','gold','platinum') DEFAULT 'regular' COMMENT '会员等级',
  `create_time` DATETIME NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`guest_id`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;


-- 订单表
CREATE TABLE `reservation` (
  `reservation_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '预订ID',
  `reservation_no` VARCHAR(20) NOT NULL COMMENT '预订编号',
  `guest_id` INT(11) NOT NULL COMMENT '客户ID',
  `status` ENUM('pending','confirmed','checked_in','checked_out','cancelled') NOT NULL DEFAULT 'pending' COMMENT '状态',
  `check_in` DATE NOT NULL COMMENT '入住日期',
  `check_out` DATE NOT NULL COMMENT '离店日期',
  `adults` TINYINT(2) NOT NULL DEFAULT '1' COMMENT '成人数量',
  `children` TINYINT(2) DEFAULT '0' COMMENT '儿童数量',
  `total_amount` DECIMAL(10,2) NOT NULL COMMENT '总金额',
  `deposit` DECIMAL(10,2) DEFAULT '0.00' COMMENT '押金',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`reservation_id`),
  UNIQUE KEY `idx_reservation_no` (`reservation_no`),
  FOREIGN KEY (`guest_id`) REFERENCES `guest` (`guest_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

-- 订单房间关联表
CREATE TABLE `reservation_room` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '关联ID',
  `reservation_id` INT(11) NOT NULL COMMENT '预订ID',
  `room_id` INT(11) NOT NULL COMMENT '房间ID',
  `rate` DECIMAL(10,2) NOT NULL COMMENT '实际房价',
  `status` ENUM('reserved','checked_in','checked_out') DEFAULT 'reserved' COMMENT '状态',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`),
  FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

-- 管理员数据
INSERT INTO `hotel_staff` (`staff_id`, `staff_no`, `name`, `password`, `position`, `department`, `contact`, `status`, `create_time`) VALUES
(1, 'HS1001', '张经理', 'e10adc3949ba59abbe56e057f20f883e', '总经理', '管理部', '13800138001', 1, NOW()),
(2, 'HS1002', '李前台', 'e10adc3949ba59abbe56e057f20f883e', '前台主管', '前台部', '13800138002', 1, NOW()),
(3, 'HS1003', '王保洁', 'e10adc3949ba59abbe56e057f20f883e', '保洁组长', '客房部', '13800138003', 1, NOW()),
(4, 'HS1004', '赵财务', 'e10adc3949ba59abbe56e057f20f883e', '财务经理', '财务部', '13800138004', 1, NOW()),
(5, 'HS1005', '钱维修', 'e10adc3949ba59abbe56e057f20f883e', '工程主管', '工程部', '13800138005', 1, NOW()),
(6, 'HS1006', '孙接待', 'e10adc3949ba59abbe56e057f20f883e', '前台接待', '前台部', '13800138006', 1, NOW()),
(7, 'HS1007', '周夜班', 'e10adc3949ba59abbe56e057f20f883e', '夜班经理', '前台部', '13800138007', 1, NOW()),
(8, 'HS1008', '吴保洁', 'e10adc3949ba59abbe56e057f20f883e', '保洁员', '客房部', '13800138008', 1, NOW()),
(9, 'HS1009', '郑厨师', 'e10adc3949ba59abbe56e057f20f883e', '厨师长', '餐饮部', '13800138009', 1, NOW()),
(10, 'HS1010', '王保安', 'e10adc3949ba59abbe56e057f20f883e', '保安队长', '安保部', '13800138010', 1, NOW());

-- 房型数据
INSERT INTO `room_type` (`type_id`, `type_name`, `bed_type`, `max_guests`, `room_size`, `base_price`, `description`, `is_active`, `create_time`) VALUES
(1, '标准大床房', 'double', 2, 280, 380.00, '1.8米大床，基础配置', 1, NOW()),
(2, '豪华双床房', 'twin', 2, 350, 580.00, '两张1.35米床，舒适空间', 1, NOW()),
(3, '行政套房', 'king', 2, 550, 1200.00, '卧室+客厅分离，高端配置', 1, NOW());

-- 房间数据
INSERT INTO `room` (`room_id`, `room_number`, `type_id`, `floor`, `view_type`, `status`, `last_clean`, `next_maintenance`, `create_time`) VALUES
-- 标准大床房 (101-108)
(101, '101', 1, 1, 'city', 'available', NOW(), NULL, NOW()),
(102, '102', 1, 1, 'city', 'available', NOW(), NULL, NOW()),
(103, '103', 1, 1, 'garden', 'occupied', '2025-07-28 14:00:00', NULL, NOW()),
(104, '104', 1, 1, 'city', 'maintenance', NULL, '2025-08-15', NOW()),
(105, '105', 1, 1, 'city', 'available', NOW(), NULL, NOW()),
(106, '106', 1, 1, 'garden', 'cleaning', NULL, NULL, NOW()),
(107, '107', 1, 1, 'city', 'available', NOW(), NULL, NOW()),
(108, '108', 1, 1, 'city', 'available', NOW(), NULL, NOW()),

-- 豪华双床房 (201-208)
(201, '201', 2, 2, 'lake', 'available', NOW(), NULL, NOW()),
(202, '202', 2, 2, 'lake', 'available', NOW(), NULL, NOW()),
(203, '203', 2, 2, 'garden', 'available', NOW(), NULL, NOW()),
(204, '204', 2, 2, 'lake', 'occupied', '2025-07-29 10:00:00', NULL, NOW()),
(205, '205', 2, 2, 'garden', 'available', NOW(), NULL, NOW()),
(206, '206', 2, 2, 'lake', 'available', NOW(), NULL, NOW()),
(207, '207', 2, 2, 'city', 'available', NOW(), NULL, NOW()),
(208, '208', 2, 2, 'garden', 'available', NOW(), NULL, NOW()),

-- 行政套房 (301-304)
(301, '301', 3, 3, 'lake', 'available', NOW(), NULL, NOW()),
(302, '302', 3, 3, 'lake', 'available', NOW(), NULL, NOW()),
(303, '303', 3, 3, 'city', 'available', NOW(), NULL, NOW()),
(304, '304', 3, 3, 'lake', 'available', NOW(), NULL, NOW());

-- 客户数据
INSERT INTO `guest` (`guest_id`, `name`, `phone`, `email`, `address`, `member_level`, `create_time`) VALUES
(1, '张三', '13800138000', 'zhangsan@example.com', '北京市朝阳区', 'gold', NOW()),
(2, '李四', '13900139000', 'lisi@example.com', '上海市浦东新区', 'silver', NOW()),
(3, '王五', '13700137000', 'wangwu@example.com', '广州市天河区', 'regular', NOW()),
(4, '赵六', '13600136000', 'zhaoliu@example.com', '深圳市南山区', 'regular', NOW()),
(5, '钱七', '13500135000', 'qianqi@example.com', '杭州市西湖区', 'gold', NOW()),
(6, '孙八', '13400134000', 'sunba@example.com', '成都市锦江区', 'silver', NOW()),
(7, '周九', '13300133000', 'zhoujiu@example.com', '武汉市江汉区', 'regular', NOW()),
(8, '吴十', '13200132000', 'wushi@example.com', '南京市玄武区', 'platinum', NOW());

-- 订单数据
INSERT INTO `reservation` (`reservation_id`, `reservation_no`, `guest_id`, `status`, `check_in`, `check_out`, `adults`, `children`, `total_amount`, `deposit`, `create_time`) VALUES
(1, 'RES20250730001', 1, 'confirmed', '2025-07-30', '2025-08-02', 2, 0, 1440.00, 500.00, NOW()),
(2, 'RES20250730002', 2, 'pending', '2025-08-05', '2025-08-10', 1, 1, 2900.00, 0.00, NOW()),
(3, 'RES20250731001', 3, 'checked_in', '2025-07-31', '2025-08-03', 2, 0, 1140.00, 500.00, NOW()),
(4, 'RES20250801001', 4, 'confirmed', '2025-08-10', '2025-08-15', 2, 0, 2400.00, 800.00, NOW()),
(5, 'RES20250802001', 5, 'pending', '2025-08-20', '2025-08-22', 1, 0, 760.00, 0.00, NOW()),
(6, 'RES20250802002', 6, 'confirmed', '2025-08-25', '2025-08-30', 2, 1, 3000.00, 1000.00, NOW()),
(7, 'RES20250803001', 7, 'cancelled', '2025-08-05', '2025-08-08', 1, 0, 1140.00, 0.00, NOW()),
(8, 'RES20250803002', 8, 'confirmed', '2025-08-12', '2025-08-14', 2, 0, 2400.00, 800.00, NOW()),
(9, 'RES20250804001', 1, 'pending', '2025-08-18', '2025-08-20', 1, 0, 760.00, 0.00, NOW()),
(10, 'RES20250804002', 2, 'confirmed', '2025-08-22', '2025-08-25', 2, 0, 1740.00, 600.00, NOW());
-- 订单房间关联数据
INSERT INTO `reservation_room` (`id`, `reservation_id`, `room_id`, `rate`, `status`) VALUES
(1, 1, 101, 480.00, 'reserved'),
(2, 1, 102, 480.00, 'reserved'),
(3, 2, 301, 1200.00, 'reserved'),
(4, 3, 201, 580.00, 'checked_in'),
(5, 4, 202, 580.00, 'reserved'),
(6, 5, 103, 380.00, 'reserved'),
(7, 6, 302, 1200.00, 'reserved'),
(8, 8, 203, 580.00, 'reserved');

-- 房型图
ALTER TABLE `room_type` 
ADD COLUMN `cover_image` VARCHAR(255) NOT NULL DEFAULT '/default/room-type.jpg' COMMENT '房型封面图',
ADD COLUMN `image_gallery` JSON COMMENT '房型展示图(JSON数组)';

-- 实景图
ALTER TABLE `room` 
ADD COLUMN `room_images` JSON COMMENT '房间实景图(JSON数组)',
ADD COLUMN `floor_plan` VARCHAR(255) COMMENT '平面图路径';

-- 房型数据（3种）
UPDATE `room_type` SET 
`cover_image` = '/uploads/room-types/standard-double.jpg',
`image_gallery` = '[
  "/uploads/room-types/standard-double-1.jpg",
  "/uploads/room-types/standard-double-2.jpg",
  "/uploads/room-types/standard-double-bath.jpg"
]'
WHERE `type_id` = 1;

UPDATE `room_type` SET 
`cover_image` = '/uploads/room-types/deluxe-twin.jpg',
`image_gallery` = '[
  "/uploads/room-types/deluxe-twin-1.jpg",
  "/uploads/room-types/deluxe-twin-2.jpg",
  "/uploads/room-types/deluxe-twin-lobby.jpg"
]'
WHERE `type_id` = 2;

UPDATE `room_type` SET 
`cover_image` = '/uploads/room-types/suite.jpg',
`image_gallery` = '[
  "/uploads/room-types/suite-bedroom.jpg",
  "/uploads/room-types/suite-living.jpg",
  "/uploads/room-types/suite-bath.jpg"
]'
WHERE `type_id` = 3;

-- 房间实景图数据（20间）
-- 标准大床房图片
UPDATE `room` SET 
`room_images` = '[
  "/uploads/rooms/101/view.jpg",
  "/uploads/rooms/101/bed.jpg",
  "/uploads/rooms/101/bathroom.jpg"
]',
`floor_plan` = '/uploads/plans/standard-double.png'
WHERE `room_number` IN ('101','102','103','104','105','106','107','108');

-- 豪华双床房图片
UPDATE `room` SET 
`room_images` = '[
  "/uploads/rooms/201/view.jpg",
  "/uploads/rooms/201/twin-beds.jpg",
  "/uploads/rooms/201/desk.jpg"
]',
`floor_plan` = '/uploads/plans/deluxe-twin.png'
WHERE `room_number` IN ('201','202','203','204','205','206','207','208');

-- 行政套房图片
UPDATE `room` SET 
`room_images` = '[
  "/uploads/rooms/301/living.jpg",
  "/uploads/rooms/301/bedroom.jpg",
  "/uploads/rooms/301/terrace.jpg"
]',
`floor_plan` = '/uploads/plans/suite.png'
WHERE `room_number` IN ('301','302','303','304');





