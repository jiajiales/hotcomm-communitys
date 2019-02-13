drop database if exists ${databaseno};
create database if not exists ${databaseno};
use ${databaseno};

DROP TABLE IF EXISTS `hk_alarm`;

CREATE TABLE `hk_alarm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `handel_state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '处理状态 0：未处理；1：处理中；2：已由报警人员处理完成；3：已由报警人员处理但未完成；4：已转工单由工单人员处理完成；5：已关闭',
  `is_dispatch` tinyint(1) DEFAULT '0' COMMENT '是否派工 0:未处理环节 1:处理反馈-不派工 2:处理反馈-需派工',
  `wor_id` int(11) DEFAULT NULL COMMENT '工单编号',
  `worder_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工单编号（转为工单后生成）',
  `module_id` int(11) DEFAULT NULL COMMENT '模块ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `alarm_type` tinyint(1) NOT NULL COMMENT '报警类型 1:设备 2:车辆 3:人员',
  `alarm_source_type` int(11) DEFAULT NULL COMMENT '报警源 类别ID 例如 人口标签编号,车辆标签编号,报警类型编号',
  `alarm_source_id` int(11) DEFAULT NULL COMMENT '报警来源编号 设备编号|车辆编号|人员编号',
  `alarm_source` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报警源 人口名称|车牌号|设备mac',
  `alarm_view` json DEFAULT NULL COMMENT '相关图片、音频、视频列表',
  `alarm_message` varchar(100) DEFAULT NULL COMMENT '报警内容 需要组装 根据来源类型',
  `alarm_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报警位置',
  `alarm_nature_of_vehicle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报警车辆性质',
  `alarm_nature_of_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报警人员性质',
  `alarm_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备报警值',
  `alarm_level` int(2) DEFAULT NULL COMMENT '报警等级',
  `handel_user` json DEFAULT NULL COMMENT '指派处理人列表',
  `handle_source_id` int(11) DEFAULT NULL COMMENT '报警处理完时填入，处理源 类别ID 例如 报警处理类型编号',
  `handle_user_id` int(11) DEFAULT NULL COMMENT '最终处理人编号-报警',
  `handle_begin` datetime DEFAULT NULL COMMENT '处理开始时间',
  `handle_end` datetime DEFAULT NULL COMMENT '处理完成时间',
  `handle_over` datetime DEFAULT NULL COMMENT '处理关闭时间',
  `handle_tip_by_alarm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '处理说明，此段的存在于报警转工单前报警已处理时',
  `handle_tip_by_worder` varchar(255) DEFAULT NULL COMMENT '处理说明，此段的存在于工单已处理时',
  `handle_result_by_alarm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报警处理完时填入',
  `handle_result_by_worder` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报警转工单，工单处理完最终处理反馈 由工单解决完时回填',
  `close_user_id` int(2) DEFAULT NULL COMMENT '关闭人编号',
  `closed_result_id` int(2) DEFAULT NULL COMMENT '关闭原因 1误报 2设备自检 3人为干扰 4其他',
  `closed_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关闭备注',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人员',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最后修改人员',
  `message_state` int(2) DEFAULT NULL COMMENT '推送状态：0不需要推送 1已推送 2二次推送完成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='报警表';


DROP TABLE IF EXISTS `hk_alarm_log`;
CREATE TABLE `hk_alarm_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `alarm_id` int(11) NOT NULL COMMENT '报警记录编号',
  `alarm_type` tinyint(1) NOT NULL COMMENT '报警类型 1:设备 2:车辆 3:人员',
  `alarm_source_id` int(11) DEFAULT NULL COMMENT '报警来源编号 设备编号|车辆编号|人员编号',
  `alarm_source` varchar(50) NOT NULL COMMENT '报警源 人口名称|车牌号|设备自定义编号',
  `alarm_source_type` int(11) DEFAULT NULL COMMENT '报警源 类别ID 例如 人口标签编号,车辆标签编号,报警类型编号',
  `alarm_message` varchar(100) DEFAULT NULL COMMENT '报警内容 需要组装 根据来源类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人员',
  `alarm_value` varchar(50) DEFAULT NULL COMMENT '报警值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='报警日志表';


DROP TABLE IF EXISTS `hk_alarm_log_c`;
CREATE TABLE `hk_alarm_log_c` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `alarm_id` int(11) NOT NULL COMMENT '报警记录编号',
  `alarm_type` tinyint(1) NOT NULL COMMENT '报警类型 1:设备 2:车辆 3:人员',
  `alarm_source_id` int(11) DEFAULT NULL COMMENT '报警来源编号 设备编号|车辆编号|人员编号',
  `alarm_source` varchar(50) NOT NULL COMMENT '报警源 人口名称|车牌号|设备自定义编号',
  `alarm_source_type` int(11) DEFAULT NULL COMMENT '报警源 类别ID 例如 人口标签编号,车辆标签编号,报警类型编号',
  `alarm_message` varchar(100) DEFAULT NULL COMMENT '报警内容 需要组装 根据来源类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人员',
  `alarm_value` varchar(50) DEFAULT NULL COMMENT '报警值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报警日志表';



DROP TABLE IF EXISTS `hk_building`;
CREATE TABLE `hk_building` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '楼栋编号',
  `community_id` int(11) DEFAULT NULL COMMENT '小区编号',
  `doordu_building_id` int(11) DEFAULT NULL COMMENT '多度平台对应ID',
  `building_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '楼栋名称',
  `detailed_addr` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '详细地址',
  `attribute` int(10) DEFAULT NULL COMMENT '楼栋属性 参考字典表 :F01-商业型,F02-住宅型,F03-服务型,F04-商业住宅型,F05-商业服务型,F06-商业住宅服务型,F07-住宅服务型 楼栋属性总编号为MK0001 ',
  `lon` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '经度',
  `lat` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '维度',
  `rent_or_sale` int(7) DEFAULT NULL COMMENT '租售类型 参考枚举 1:租赁；2：购买 3 租赁购买',
  `create_time` datetime DEFAULT NULL COMMENT '登记时间',
  `create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登记人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `isdelete` tinyint(1) DEFAULT '0' COMMENT '1:删除，0：不删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='楼栋表';



DROP TABLE IF EXISTS `hk_car_alarm_records`;
CREATE TABLE `hk_car_alarm_records` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `num` varchar(20) NOT NULL COMMENT '车牌号',
  `type` tinyint(4) DEFAULT NULL COMMENT '车辆类型（0:小区车辆，1：单位车辆，2:外来登记车辆,3：陌生车）',
  `rule_id` int(11) NOT NULL COMMENT '预警规则ID',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `mac` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '摄像头mac地址',
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车辆抓拍照片',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(15) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆报警记录表';



DROP TABLE IF EXISTS `hk_car_alarm_rule`;
CREATE TABLE `hk_car_alarm_rule` (
  `rule_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '预警规则ID',
  `name` varchar(40) NOT NULL COMMENT '规则名称',
  `alarm_leve` tinyint(1) NOT NULL COMMENT '预警级别',
  `notify_users` json NOT NULL COMMENT '接受人员',
  `deal_users` json NOT NULL COMMENT '处理人员',
  `rule` json NOT NULL COMMENT '规则记录',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(15) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(15) DEFAULT NULL COMMENT '更改人',
  `state` tinyint(1) NOT NULL COMMENT '是否启用(0:启用,1:暂停)',
  PRIMARY KEY (`rule_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆预警规则表';

INSERT  INTO `hk_car_alarm_rule`(`rule_id`,`name`,`alarm_leve`,`notify_users`,`deal_users`,`rule`,`create_time`,`create_user`,`update_time`,`update_user`,`state`) VALUES (1,'过夜车',3,'{\"person\": [1, 2, 3]}','{\"person\": [1, 2]}','{\"endTime\": \"3:00\", \"stayTime\": \"2\", \"startTime\": \"22:00\"}','2018-10-28 11:13:53','管理员',NULL,NULL,0),(2,'多次出入',2,'{\"person\": [1, 2, 3]}','{\"person\": [1, 2]}','{\"maxCount\": \"10\"}','2018-10-28 11:13:52','管理员',NULL,NULL,0),(3,'长时间停留',1,'{\"person\": [1, 2, 3]}','{\"person\": [1, 2]}','{\"maxHours\": \"10\"}','2018-10-28 11:13:51','管理员',NULL,NULL,0),(4,'徘徊车辆',3,'{\"person\": [1, 2, 3]}','{\"person\": [1, 2]}','{}','2018-10-28 11:13:50','管理员',NULL,NULL,0),(5,'黑名单',1,'{\"person\": [1, 2, 3]}','{\"person\": [1, 2]}','{\"describe\": \"发现黑名单车辆\"}','2018-10-28 11:13:49','管理员',NULL,NULL,0);


DROP TABLE IF EXISTS `hk_car_label`;
CREATE TABLE `hk_car_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `car_label_id` int(11) NOT NULL COMMENT '标签类型ID',
  `name` varchar(40) NOT NULL COMMENT '标签类型名称',
  `label_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `from_source` tinyint(1) NOT NULL COMMENT '来源（0：系统，1：自定义）',
  `alarm_leve` tinyint(1) DEFAULT NULL COMMENT '警报级别',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(15) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(15) DEFAULT NULL COMMENT '更改人',
  `state` tinyint(1) DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_key` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆标签表';

INSERT  INTO `hk_car_label`(`id`,`car_label_id`,`name`,`label_describe`,`from_source`,`alarm_leve`,`create_time`,`create_user`,`update_time`,`update_user`,`state`) VALUES (1,13,'偷盗车辆','系偷盗车辆',0,1,'2018-10-12 10:10:39','管理员',NULL,NULL,0),(2,13,'非法车辆','非法改装车辆',0,1,'2018-10-22 10:20:39','管理员',NULL,NULL,0),(3,13,'肇事车辆','车辆发生肇事未处理',0,1,'2018-10-22 10:21:39','管理员',NULL,NULL,0),(4,14,'多次出入','外来车辆，当月5次以上出入',0,2,'2018-10-22 10:23:39','管理员',NULL,NULL,0),(5,14,'过夜车','外来车辆，停车时间大于30分钟 且3:00之前未驶出',0,3,'2018-10-22 10:24:39','管理员',NULL,NULL,0),(6,14,'长时间停留','外来车辆，连续停车大于等于3天',0,3,'2018-10-22 10:25:39','管理员',NULL,NULL,0),(7,15,'搬家公司','XX搬家公司',1,NULL,'2018-10-22 10:26:39','安保人员甲',NULL,NULL,0),(8,15,'校车','XX幼儿园校车',1,NULL,'2018-10-22 10:27:39','安保人员甲',NULL,NULL,0),(9,15,'送水','XX纯净水',1,NULL,'2018-10-22 10:28:39','安保人员甲',NULL,NULL,0),(10,15,'送货车','超市供货车',1,NULL,'2018-10-24 13:50:31','安保人员甲',NULL,NULL,0),(11,15,'警车','社区民警车辆',1,NULL,'2018-10-24 13:51:07','安保人员甲',NULL,NULL,0);


DROP TABLE IF EXISTS `hk_car_label_relation`;
CREATE TABLE `hk_car_label_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `car_num` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '车牌号',
  `label_id` int(11) DEFAULT NULL COMMENT '车辆标签ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `hk_car_pass_records`;
CREATE TABLE `hk_car_pass_records` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `car_num` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车牌号码',
  `car_type` tinyint(1) NOT NULL COMMENT '车辆类型（0:小区车辆，1：单位车辆，2:其他登记车,3：陌生车）',
  `pass_type` tinyint(1) NOT NULL COMMENT '出入类型(0:驶入，1:驶出，2:经过)',
  `color` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '颜色',
  `model` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '型号',
  `brand` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '品牌',
  `model_type` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车型',
  `adress` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出现地点',
  `video_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '摄像头的mac地址',
  `img_path` json DEFAULT NULL COMMENT '抓拍车辆图片',
  `first_img_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车辆登记照片（陌生车辆为第一次抓拍）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='通行车辆记录';


DROP TABLE IF EXISTS `hk_car_reg`;
CREATE TABLE `hk_car_reg` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '车辆ID',
  `photo` json DEFAULT NULL COMMENT '车辆图片',
  `num` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车牌号',
  `color` varchar(20) DEFAULT NULL COMMENT '颜色',
  `brand` varchar(20) DEFAULT NULL COMMENT '品牌',
  `model_type` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车型',
  `model` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '型号',
  `type` tinyint(1) NOT NULL COMMENT '车辆类型（0:小区车辆，1：单位车辆，2：其他登记车）',
  `person_id` int(1) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL COMMENT '车主房号ID',
  `alarm_count` int(11) NOT NULL COMMENT '本月报警次数',
  `company_id` int(11) DEFAULT NULL COMMENT '车辆所属单位ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车辆登记表';


DROP TABLE IF EXISTS `hk_car_str`;
CREATE TABLE `hk_car_str` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '陌生车辆ID',
  `photo` json DEFAULT NULL COMMENT '车辆图片',
  `num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车牌号',
  `color` varchar(20) DEFAULT NULL COMMENT '颜色',
  `brand` varchar(20) DEFAULT NULL COMMENT '品牌',
  `model` varchar(20) DEFAULT NULL COMMENT '型号',
  `model_type` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车型',
  `state` tinyint(1) NOT NULL COMMENT '车辆状态(0：驶入，1：离开)',
  `enter_out_time` datetime DEFAULT NULL COMMENT '出入时间',
  `enter_count` int(11) NOT NULL COMMENT '本月出入次数',
  `alarm_count` int(11) NOT NULL COMMENT '本月报警次数',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(15) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(15) DEFAULT NULL COMMENT '更改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='陌生车辆';


DROP TABLE IF EXISTS `hk_cor_per_relation`;
CREATE TABLE `hk_cor_per_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关联Id',
  `corporation_id` int(11) NOT NULL COMMENT '单位id',
  `person_id` int(11) NOT NULL COMMENT '人口id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `hk_corporation`;
CREATE TABLE `hk_corporation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '单位id',
  `cor_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '单位名称',
  `floor` json NOT NULL COMMENT '单位楼层信息',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '单位地址',
  `cor_type_id` int(11) NOT NULL COMMENT '单位类型（字典值）',
  `scope` varchar(200) NOT NULL COMMENT '经营范围',
  `longitude` double DEFAULT NULL COMMENT '经度',
  `latitude` double DEFAULT NULL COMMENT '纬度',
  `label_id` int(11) DEFAULT NULL COMMENT '标签id',
  `dev_count` int(11) NOT NULL DEFAULT '0',
  `bus_license` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '营业执照',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(15) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(15) DEFAULT NULL COMMENT '更改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;


DROP TABLE IF EXISTS `hk_corporation_label`;
CREATE TABLE `hk_corporation_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) NOT NULL COMMENT '标签类型id',
  `label_name` varchar(20) NOT NULL COMMENT '标签名',
  `describes` varchar(200) NOT NULL COMMENT '标签描述',
  `label_source` tinyint(4) NOT NULL COMMENT '来源',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(15) NOT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(15) DEFAULT NULL COMMENT '更改人',
  `state` tinyint(4) NOT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

INSERT  INTO `hk_corporation_label`(`id`,`type_id`,`label_name`,`describes`,`source`,`create_time`,`create_user`,`update_time`,`update_user`,`state`) VALUES (1,1,'安全生产','安全生产',0,'2018-11-12 09:13:22','管理员',NULL,NULL,0),(2,2,'医疗机构','应急急救场所',0,'2018-11-14 10:47:02','管理员',NULL,NULL,0),(3,1,'重点保护','重点保护',0,'2018-11-14 10:48:14','管理员',NULL,NULL,0),(4,1,'消防监督','消防重点关注单位',0,'2018-11-14 10:48:25','管理员',NULL,NULL,0),(5,1,'三小场所','小档口、小作坊、小娱乐场所',0,'2018-11-14 10:48:35','管理员',NULL,NULL,0),(6,2,'学校','学校',0,'2018-11-14 10:48:48','管理员',NULL,NULL,0);


DROP TABLE IF EXISTS `hk_corporation_label_type`;
CREATE TABLE `hk_corporation_label_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签类型ID',
  `type_name` varchar(20) NOT NULL COMMENT '标签类型名称',
  `state` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

insert  into `hk_corporation_label_type`(`id`,`type_name`,`state`) values (1,'重点关注',0);

DROP TABLE IF EXISTS `hk_device`;
CREATE TABLE `hk_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mac` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'mac地址',
  `dev_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备功能类型',
  `dev_num` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备编号',
  `dev_trademark` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备品牌',
  `lat` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '纬度',
  `lon` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '经度',
  `x` double(9,2) DEFAULT NULL COMMENT '图片水平位置',
  `y` double(9,2) DEFAULT NULL COMMENT '图片垂直位置',
  `own_id` int(11) DEFAULT NULL COMMENT '责任人id',
  `state` int(11) DEFAULT '0' COMMENT '设备状态 0:正常 1:离线 2:故障 3:报警',
  `battery` double(9,2) DEFAULT '100.00' COMMENT '电量',
  `video_list` json DEFAULT NULL COMMENT '设备关联的视频id或编号 video',
  `module_id` int(11) DEFAULT NULL COMMENT '模块id',
  `picture_list` json DEFAULT NULL COMMENT '设备图片地址 picture',
  `install_explain` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '安装说明',
  `install_userid` int(11) DEFAULT NULL COMMENT '安装人id',
  `install_time` datetime DEFAULT NULL COMMENT '安装时间',
  `building_num` int(11) DEFAULT NULL COMMENT '楼栋编号',
  `unit_num` int(11) DEFAULT NULL COMMENT '单元id',
  `floor_num` int(11) DEFAULT NULL COMMENT '楼层编号',
  `dev_address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备地址',
  `use_type` int(11) DEFAULT NULL COMMENT '设备应用场景 0通行 1消防  2监控 3其他',
  `is_enble` tinyint(1) DEFAULT '1' COMMENT '是否可用',
  `lastdata_time` datetime DEFAULT NULL COMMENT '数据最后上传时间',
  `doorcontrol_attr_id` int(11) DEFAULT NULL COMMENT '门禁属性ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='设备表';


DROP TABLE IF EXISTS `hk_device_alarm_gz`;
CREATE TABLE `hk_device_alarm_gz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) NOT NULL COMMENT '设备类型|模块',
  `alarm_mac` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备mac',
  `alarm_typename` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报警类型名称',
  `alarm_level` int(11) DEFAULT NULL COMMENT '报警级别',
  `alarm_vaule` json DEFAULT NULL COMMENT '预警阈值',
  `alarm_middle` int(11) DEFAULT NULL COMMENT '时间间隔',
  `inner_userid` json DEFAULT NULL COMMENT '相关人员',
  `inform_userid` json DEFAULT NULL COMMENT '通知人员',
  `use_type` tinyint(1) DEFAULT NULL COMMENT '0 系统 1 自定义',
  `is_open` tinyint(1) DEFAULT '1' COMMENT '是否开启',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='设备预警规则表';


DROP TABLE IF EXISTS `hk_device_video`;
CREATE TABLE `hk_device_video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mac` varchar(30) NOT NULL COMMENT 'mac地址,设备序列号',
  `dev_type` int(11) DEFAULT NULL COMMENT '设备类型，0人脸相机，1车牌相机，2球机，3半球机',
  `dev_location` int(11) DEFAULT NULL COMMENT '监控位置，0入口，1出口，2路边，3其他',
  `dev_num` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '设备编号',
  `dev_trademark` varchar(30) DEFAULT NULL COMMENT '设备品牌',
  `user_name` varchar(30) DEFAULT NULL COMMENT '账号',
  `pass_word` varchar(30) DEFAULT NULL COMMENT '密码',
  `videoip` varchar(30) DEFAULT NULL COMMENT '摄像头ip地址',
  `nvrip` varchar(30) DEFAULT NULL COMMENT '硬盘录像机ip地址',
  `videoport` int(11) DEFAULT NULL COMMENT '摄像头端口',
  `PORT` int(11) DEFAULT NULL COMMENT '硬盘录像机rtsp端口',
  `nvruser_name` varchar(30) DEFAULT NULL COMMENT 'nvr账号',
  `nvrpass_word` varchar(30) DEFAULT NULL COMMENT 'nvr密码',
  `channel` varchar(30) DEFAULT NULL COMMENT '视频通道',
  `lat` varchar(30) DEFAULT NULL COMMENT '纬度',
  `lon` varchar(30) DEFAULT NULL COMMENT '经度',
  `X` double(9,0) DEFAULT NULL COMMENT '图片水平位置',
  `Y` double(9,0) DEFAULT NULL COMMENT '图片垂直位置',
  `own_id` int(11) DEFAULT NULL COMMENT '责任人id',
  `state` int(11) DEFAULT '0' COMMENT '设备状态 0:正常 1:离线',
  `picture_list` json DEFAULT NULL COMMENT '设备图片地址',
  `video_list` json DEFAULT NULL COMMENT '设备关联的视频mac video',
  `install_explain` varchar(30) DEFAULT NULL COMMENT '安装说明',
  `install_userid` int(11) DEFAULT NULL COMMENT '安装人id',
  `install_time` datetime DEFAULT NULL COMMENT '安装时间',
  `building_num` int(11) DEFAULT NULL COMMENT '楼栋id',
  `unit_num` int(11) DEFAULT NULL COMMENT '单元id',
  `floor_num` int(11) DEFAULT NULL COMMENT '楼层id',
  `dev_address` varchar(30) DEFAULT NULL COMMENT '设备地址',
  `use_type` int(11) DEFAULT 1 COMMENT '设备应用场景 0车辆监控 1消防 2监控 其他',
  `is_enble` tinyint(1) DEFAULT 1 COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='摄像头设备表';

/*Table structure for table `hk_door_control_attribute` */

DROP TABLE IF EXISTS `hk_door_control_attribute`;

CREATE TABLE `hk_door_control_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备ID',
  `guid` varchar(32) DEFAULT NULL COMMENT '多度设备guid',
  `install_state` int(1) DEFAULT '0' COMMENT '安装：1，未安装：0',
  `faceCaptureOnOff` tinyint(1) DEFAULT NULL COMMENT '人脸抓拍开关,开：1，关：0',
  `faceDetectSupport` tinyint(1) DEFAULT NULL COMMENT '是否支持人脸识别，支持：1，不支持：0',
  `faceOpenDoorOnOff` tinyint(1) DEFAULT NULL COMMENT '人脸识别开门开关，开：1，关：0',
  `isDoorLock` tinyint(1) DEFAULT NULL COMMENT '是否开启门磁 0：不开启 1：开启',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `isdelete` int(1) DEFAULT '0' COMMENT '0:不删除，1：删除',
  `local_type` int(1) DEFAULT NULL COMMENT '门禁机位置类型,0:单元机 1:围墙机',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='门禁设备属性表';


DROP TABLE IF EXISTS `hk_event`;
CREATE TABLE `hk_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '事件id',
  `worder_id` int(11) DEFAULT NULL COMMENT '事件-关联工单编号',
  `handle_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '指定处理人ID',
  `create_time` datetime DEFAULT NULL COMMENT '上报时间(等同于创建时间)',
  `event_type` int(1) DEFAULT NULL COMMENT '事件类型 (1.设备 2.户政 3.消防 4.治安 5.刑事 6.交通 7.生产安全 8.其他)',
  `event_status` int(1) DEFAULT NULL COMMENT '事件状态 0待处理 1已转工单 2处理中 3已处理 4已关闭',
  `event_source` int(1) DEFAULT NULL COMMENT '事件来源分布(1.APP端,2.PC)',
  `event_no` varchar(50) DEFAULT NULL COMMENT '事件编号(自定义)',
  `event_title` varchar(255) DEFAULT NULL COMMENT '事件标题',
  `event_desc` varchar(255) DEFAULT NULL COMMENT '事件描述',
  `event_htime` datetime DEFAULT NULL COMMENT '事件发生时间(只能选今天之前的日期；默认当天)',
  `event_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '来源地址 楼栋名称,楼层名称,经纬度',
  `event_view` json DEFAULT NULL COMMENT '视图资源',
  `time_confine` int(50) DEFAULT NULL COMMENT '要求处理时间 60:一小时内(非常紧急) 480:八小时内(比较紧急) 1440:一天内(紧急) 4320:三天内(一般) 7200:其他(不紧急)',
  `reported_user_id` int(11) DEFAULT NULL COMMENT '上报人编号',
  `close_time` datetime DEFAULT NULL COMMENT '关闭时间',
  `close_reason_id` int(11) DEFAULT NULL COMMENT '关闭原因 1.不需要处理 2.事件已消除 3.误会 4.其他',
  `close_mark` varchar(255) DEFAULT NULL COMMENT '关闭备注',
  `close_user_id` int(11) DEFAULT NULL COMMENT '关闭人编号',
  `leader_tip` json DEFAULT NULL COMMENT '工作指示',
  `lng` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `video_relation` varchar(255) DEFAULT NULL COMMENT '关联摄像头',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `hk_floors`;
CREATE TABLE `hk_floors` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '楼栋编号',
  `building_id` int(11) DEFAULT NULL COMMENT '关联楼栋ID',
  `unit_id` int(11) DEFAULT NULL COMMENT '单元ID',
  `floor_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '楼层号（数）或名称',
  `floor_num` int(5) DEFAULT NULL COMMENT '楼层序号',
  `detailed_addr` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '详细地址',
  `construct_area` double(9,2) DEFAULT NULL COMMENT '建筑面积',
  `use_area` double(9,2) DEFAULT NULL COMMENT '使用面积',
  `attribute` int(7) DEFAULT NULL COMMENT '属性: 参考字典表 与楼栋属性一致',
  `rent_or_sale` int(7) DEFAULT NULL COMMENT '租售类型 参考枚举 1:租赁；2：购买 3 租赁购买',
  `layout_path` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '布局图路径或图纸',
  `create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登记人',
  `create_time` datetime DEFAULT NULL COMMENT '登记时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `isdelete` tinyint(1) DEFAULT '0',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `building_id_key` (`building_id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='楼层表';


DROP TABLE IF EXISTS `hk_person`;
CREATE TABLE `hk_person` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `p_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '多度ID号',
  `duodu_card_id` int(11) DEFAULT NULL COMMENT '多度身份证id',
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户姓名 ',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系手机 ',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别 1 男 2 女 ',
  `card_type` int(1) DEFAULT NULL COMMENT '证件类型 1身份证号, 2港澳通行证,3台胞证, 4护照',
  `card_no` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '证件号码 身份证或者证件照',
  `koseki` tinyint(1) DEFAULT '0' COMMENT '户籍 0 本地 1 外地 ',
  `account_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '户口地址 ',
  `lable_id` int(11) DEFAULT '0' COMMENT '人口标签 普通老百姓则不填-默认给0 参考人口标签表 -1陌生人',
  `headImg` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '头像地址 人脸图片',
  `age` tinyint(11) DEFAULT NULL COMMENT '年龄',
  `birthday` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '出生年月日 ',
  `entrance_cardno` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '门禁卡号',
  `nationality` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '国籍',
  `people` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '民族',
  `degree` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学历',
  `political_outlook` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '政治面貌',
  `marital_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '婚姻状态',
  `alarm_nums` smallint(5) DEFAULT NULL COMMENT '报警次数',
  `create_time` datetime DEFAULT NULL COMMENT '登记时间',
  `create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登入人员',
  `update_time` datetime DEFAULT NULL COMMENT '最新修改时间',
  `update_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最新修改人员',
  `data_type` tinyint(1) DEFAULT NULL COMMENT '数据类型：是否本小区用户,1:是,2:不是',
  `data_source` int(1) DEFAULT NULL COMMENT '数据来源: 1:xxx门禁  2xxx摄像头',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除,0:否  1:是',
  `face_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '人脸编号',
  PRIMARY KEY (`p_id`) USING BTREE,
  UNIQUE KEY `cardkey` (`card_no`) USING BTREE,
  UNIQUE KEY `entrance_cardno_key` (`entrance_cardno`) USING BTREE,
  UNIQUE KEY `phone_key` (`phone`) USING BTREE,
  KEY `sex` (`sex`) USING BTREE,
  KEY `people` (`people`) USING BTREE,
  KEY `create_time` (`create_time`) USING BTREE,
  KEY `lable_id` (`lable_id`) USING BTREE,
  KEY `nationality` (`nationality`) USING BTREE,
  KEY `p_no_key` (`p_no`) USING BTREE,
  FULLTEXT KEY `namne_key` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='人口基础表';


DROP TABLE IF EXISTS `hk_person_hole`;
CREATE TABLE `hk_person_hole` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `hole_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '布控主题',
  `hole_type` tinyint(1) NOT NULL COMMENT '布控类型 1 群里 2 单人',
  `hole_populations` json DEFAULT NULL COMMENT '布控个人|群体  使用Json type=1 -->>p_id  type=2 {sex:boy,age:[20,50],nationality:china,people=han,lable=01}',
  `alarm_level` tinyint(1) DEFAULT NULL COMMENT '报警 级别',
  `hole_status` tinyint(1) DEFAULT '0' COMMENT '状态 0 未开启 1 已开启 2 已结束',
  `record_population_time` json DEFAULT NULL COMMENT '[{p_id,lastTime},......]',
  `create_time` datetime DEFAULT NULL COMMENT '登记时间',
  `create_user` int(20) DEFAULT NULL COMMENT '登入人员',
  `update_time` datetime DEFAULT NULL COMMENT '最新修改时间',
  `update_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最新修改人员',
  `push` json DEFAULT NULL COMMENT '推送人与信息  {"receiver":[{"p_id":1,"message":"GO","p-name":"zzz"},{"p_id":2,"message":"GO","p_name":"aaa"}]}',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='人口布控列表';


DROP TABLE IF EXISTS `hk_person_hole_detail`;
CREATE TABLE `hk_person_hole_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `hole_id` int(11) NOT NULL COMMENT '人口布控记录编号',
  `beginTime` datetime DEFAULT NULL COMMENT '布控开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '布控结束时间',
  `no_return` smallint(5) DEFAULT NULL COMMENT '未归时间 单位为小时',
  `no_go` smallint(5) DEFAULT NULL COMMENT '未出时间 单位未小时',
  `days` smallint(5) DEFAULT NULL COMMENT '天数',
  `nums` smallint(5) DEFAULT NULL COMMENT '次数',
  `row_key` tinyint(1) DEFAULT NULL COMMENT '是否连续,1:连续，2不连续',
  `way` tinyint(1) DEFAULT NULL COMMENT '触发方式 1 未归 2 未出 3 出行频率 4 出现频率-连续',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='人口布控规则记录表';


DROP TABLE IF EXISTS `hk_person_lable`;
CREATE TABLE `hk_person_lable` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签类型 ',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标签说明',
  `source_type` int(1) DEFAULT '1' COMMENT '来源类型  0 系统 1 自定义',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` int(10) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name_key` (`name`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='人口标签表';


DROP TABLE IF EXISTS `hk_person_record`;
CREATE TABLE `hk_person_record` (
  `uid` int(32) NOT NULL AUTO_INCREMENT COMMENT 'UUID编号',
  `p_id` int(11) DEFAULT NULL COMMENT '记录居民编号',
  `record_time` datetime DEFAULT NULL COMMENT '记录时间',
  `record_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '记录地址',
  `lable_id` int(11) DEFAULT '0' COMMENT '人口标签  ',
  `imgs` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片',
  `video` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '视频',
  `face_no` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '人脸编号',
  `record_type` tinyint(1) DEFAULT NULL COMMENT '记录类型 1 人脸感知 2 门禁摄像头 3 门卡开门  4 手机APP开门 5 密码开门',
  `device_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '记录设备',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除, 0 否  1 是',
  `doordu_log_id` int(32) DEFAULT NULL COMMENT '多度通行数据ID',
  PRIMARY KEY (`uid`) USING BTREE,
  KEY `p_id_key` (`p_id`) USING BTREE,
  KEY `lable_id` (`lable_id`) USING BTREE,
  KEY `record_time` (`record_time`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='通行进入库';


DROP TABLE IF EXISTS `hk_person_room`;
CREATE TABLE `hk_person_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) NOT NULL COMMENT '人口编号',
  `room_id` int(11) NOT NULL COMMENT '房间编号',
  `reason` int(5) NOT NULL COMMENT '关系,字典表ID，类型为R01',
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `rent_or_sale` int(2) DEFAULT NULL COMMENT '参考枚举，1：租赁；2：购买',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `isdelete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `p_id_key` (`p_id`) USING BTREE,
  KEY `room_id_key` (`room_id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='人口-房间-关系-中间表';


DROP TABLE IF EXISTS `hk_person_stranger`;
CREATE TABLE `hk_person_stranger` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `yun_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '人脸服务器 人脸编号',
  `headImg` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '头像地址 人脸图片',
  `frequency` smallint(5) DEFAULT '1' COMMENT '出现频率',
  `last_time` datetime DEFAULT NULL COMMENT '最近出现时间',
  `first_time` datetime DEFAULT NULL COMMENT '开始出现时间',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别 1 男 2女',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `card_no` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '证件号',
  `address` varchar(255) DEFAULT NULL COMMENT '首次出现地点',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `yun_no_key` (`yun_no`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='人口-陌生人表';


DROP TABLE IF EXISTS `hk_population_alarm`;
CREATE TABLE `hk_population_alarm` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `create_time` datetime DEFAULT NULL COMMENT '报警上报时间',
  `record_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '报警地址',
  `alarm_level` tinyint(1) DEFAULT NULL COMMENT '报警级别',
  `lable_id` int(11) DEFAULT '0' COMMENT '人口标签  ',
  `imgs` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片',
  `video` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '视频',
  `doordu_log_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '多度通行数据ID',
  `record_type` tinyint(1) DEFAULT NULL COMMENT '记录类型 1 人脸感知 2 门禁摄像头 3 门卡开门  4 手机APP开门 5 密码开门',
  `p_id` int(11) DEFAULT NULL COMMENT '用户id',
  `isdelete` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0' COMMENT '是否删除,0 否  1 是',
  `alarm_reason` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '警报原因',
  `alarm_reason_way` int(1) DEFAULT NULL COMMENT '警报来源类型 1未归 2未出 3 出行频率 4 出行频率 连续',
  `face_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '人脸编号',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `p_id` (`p_id`) USING BTREE,
  KEY `record_type` (`record_type`) USING BTREE,
  KEY `alarm_level` (`alarm_level`) USING BTREE,
  KEY `lable_id` (`lable_id`) USING BTREE,
  KEY `record_time` (`create_time`) USING BTREE,
  KEY `alarm_reason_way` (`alarm_reason_way`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='人口报警记录';


DROP TABLE IF EXISTS `hk_room`;
CREATE TABLE `hk_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '房间编号',
  `doordu_room_id` int(11) DEFAULT NULL COMMENT '多度平台对应ID',
  `building_id` int(11) DEFAULT NULL COMMENT '关联楼栋ID',
  `unit_id` int(11) DEFAULT NULL COMMENT '单元ID',
  `floor_id` int(11) DEFAULT NULL COMMENT '关联楼层ID',
  `room_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '房间名称',
  `detailed_addr` varchar(100) DEFAULT NULL COMMENT '详细地址',
  `construct_area` double(9,2) DEFAULT NULL COMMENT '建筑面积',
  `use_area` double(9,2) DEFAULT NULL COMMENT '使用面积',
  `attribute` tinyint(1) DEFAULT NULL COMMENT '属性: 参考枚举 1:商业型；2：住宅型；3：服务型 ',
  `rent_or_sale` tinyint(1) DEFAULT NULL COMMENT '租售类型   参考枚举 1:租赁；2：购买',
  `create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登记人',
  `create_time` datetime DEFAULT NULL COMMENT '登记时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `isdelete` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `building_id_key` (`building_id`) USING BTREE,
  KEY `floor_id_key` (`floor_id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='楼层表';


DROP TABLE IF EXISTS `hk_unit`;
CREATE TABLE `hk_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '单元ID',
  `doordu_unit_id` int(11) DEFAULT NULL COMMENT '多度平台对应ID',
  `building_id` int(11) DEFAULT NULL COMMENT '楼栋ID',
  `unit_no` varchar(11) NOT NULL COMMENT '单元号',
  `unit_name` varchar(32) DEFAULT NULL COMMENT '单元名称',
  `create_time` datetime DEFAULT NULL COMMENT '登记时间',
  `create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登记人',
  `isdelete` tinyint(1) DEFAULT '0' COMMENT '1:删除，0：不删除',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='单元表';

DROP TABLE IF EXISTS `hk_watch_place`;
CREATE TABLE `hk_watch_place` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '场所编号',
  `building_id` int(11) DEFAULT NULL COMMENT '所属楼栋',
  `unit_id` int(11) DEFAULT NULL COMMENT '单元ID',
  `floor_id` int(11) DEFAULT NULL COMMENT '所属楼层',
  `room_id` int(11) DEFAULT NULL COMMENT '所属房间',
  `ways` tinyint(1) NOT NULL DEFAULT '1' COMMENT '场所类型 1 隐患场所 2 服务场所',
  `place_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '场所名称',
  `place_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '场所地址',
  `place_type` int(10) DEFAULT NULL COMMENT '参考字典 隐患场所类型=[F01:重大危险源,F02:危化品,F03:网吧,F04:酒吧,F05:老旧危房],服务场所类型=[F01:避难场所,F02:医院,F03:医院,F04:福利院,F05:救助站,F06:养老院,F07:孤儿院,F08:消防站,F09:残疾人中心,F10:居委会]',
  `danger_level` int(10) DEFAULT NULL COMMENT '参考枚举 危险等级 1,2,3',
  `lon` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '经度',
  `lat` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '维度',
  `create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登记人',
  `create_time` datetime DEFAULT NULL COMMENT '登记时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `isdelete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name_key` (`place_name`) USING BTREE,
  KEY `building_id_key` (`building_id`) USING BTREE,
  KEY `floor_id_key` (`floor_id`) USING BTREE,
  KEY `room_id_key` (`room_id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='关注场所';


DROP TABLE IF EXISTS `hk_worder`;
CREATE TABLE `hk_worder` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工单编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` int(50) DEFAULT NULL COMMENT '创建人员编号',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最后修改人员',
  `module_id` int(5) DEFAULT NULL COMMENT '模块ID',
  `device_id` int(5) DEFAULT NULL COMMENT '设备ID',
  `order_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工单编号 系统生成',
  `order_title` varchar(100) DEFAULT NULL COMMENT '工单标题',
  `order_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '详细说明',
  `order_status` tinyint(1) DEFAULT NULL COMMENT '工单状态 1:待处理 2:处理中 3:挂起 4:已处理',
  `source_type` int(1) DEFAULT NULL COMMENT '工单来源 1:报警 2:事件 3:设备 4:其他',
  `source_id` int(11) DEFAULT NULL COMMENT '工单-关联编号 例如事件编号、报警编号、设备编号',
  `source_address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '来源地址 楼栋名称,楼层名称,经纬度',
  `handle_user_id` int(11) DEFAULT NULL COMMENT '处理人编号',
  `handle_view` json DEFAULT NULL COMMENT '相关图片、音频、视频列表',
  `handle_begin` datetime DEFAULT NULL COMMENT '处理开始时间',
  `handle_end` datetime DEFAULT NULL COMMENT '处理完成时间',
  `handle_over` datetime DEFAULT NULL COMMENT '处理审核时间',
  `error_cause` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '故障原因（填写）',
  `process_mode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '处理方式 1设备维修 2设备更换 3其他处理 4工单挂起',
  `handle_result` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '处理结果（填写）',
  `leader_tip` json DEFAULT NULL COMMENT '工作指示',
  `hang_state` int(5) DEFAULT '0' COMMENT '是否进行挂起：0否 1是',
  `lng` varchar(255) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `time_confine` int(50) DEFAULT NULL COMMENT '要求处理时间 60:一小时内(非常紧急) 480:八小时内(比较紧急) 1440:一天内(紧急) 4320:三天内(一般) 7200:其他(不紧急)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no` (`order_no`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='工单表';


use hotcomm_community;