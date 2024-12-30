DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
    `id` BIGINT NOT NULL COMMENT 'id',
    `mobile` VARCHAR(11) COMMENT '手机号',
    PRIMARY KEY (`id`),
    UNIQUE KEY `mobile_unique` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员';


DROP TABLE IF EXISTS `passenger`;
CREATE TABLE `passenger` (
                             `id` BIGINT NOT NULL COMMENT 'id',
                             `member_id` BIGINT NOT NULL COMMENT '会员id',
                             `name` VARCHAR(20) NOT NULL COMMENT '姓名',
                             `id_card` VARCHAR(18) NOT NULL COMMENT '身份证',
                             `type` CHAR(1) NOT NULL COMMENT '旅客类型[PassengerTypeEnum]',
                             `create_time` DATETIME(3) COMMENT '新增时间',
                             `update_time` DATETIME(3) COMMENT '修改时间',
                             PRIMARY KEY (`id`),
                             INDEX `member_id_index` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='乘车人';