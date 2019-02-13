CREATE TABLE `hk_area` (
  `id` mediumint(7) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` mediumint(7) unsigned NOT NULL DEFAULT '0' COMMENT '父级ID',
  `level` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '层级',
  `area_code` bigint(12) unsigned NOT NULL DEFAULT '0' COMMENT '行政代码',
  `zip_code` mediumint(6) unsigned zerofill NOT NULL DEFAULT '000000' COMMENT '邮政编码',
  `city_code` char(6) NOT NULL DEFAULT '' COMMENT '区号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `short_name` varchar(50) NOT NULL DEFAULT '' COMMENT '简称',
  `merger_name` varchar(50) NOT NULL DEFAULT '' COMMENT '组合名',
  `pinyin` varchar(30) NOT NULL DEFAULT '' COMMENT '拼音',
  `lng` decimal(10,6) NOT NULL DEFAULT '0.000000' COMMENT '经度',
  `lat` decimal(10,6) NOT NULL DEFAULT '0.000000' COMMENT '维度',
  PRIMARY KEY (`id`),
  KEY `idx_lev` (`level`,`parent_id`) USING BTREE
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COMMENT='中国行政地区表';

drop table if exists hk_operate_log;
create table if not exists hk_operate_log(
  id bigint(20) primary key AUTO_INCREMENT comment '编号',
  record_user varchar(32) comment '记录-用户-编号',
  record_event varchar(150) comment '记录-事件',
  record_code varchar(20) comment '日志编号',
  record_time datetime comment '记录-时间',
  record_ip varchar(20) comment '用户IP地址',
  community_id int(11)  comment '社区编号',
  key `record_user_key` (`record_user`),
  key `record_code_key` (`record_code`)
)engine = MyISAM default charset = utf8 comment '操作日志';

drop table if exists `hk_performance_log`;
CREATE TABLE `hk_performance_log` (
  `id` bigint(20) primary key AUTO_INCREMENT COMMENT '编号',
  `request_ip` varchar(20) DEFAULT NULL COMMENT '用户IP地址',
  `request_params` json DEFAULT NULL COMMENT '请求参数',
  `request_url` varchar(150) DEFAULT NULL COMMENT '请求地址',
  `execute_times` bigint(20) DEFAULT NULL COMMENT '执行时间 单位:毫秒',
  `response_obj` json DEFAULT NULL COMMENT '返回参数',
  `response_status` tinyint(1) DEFAULT NULL COMMENT '返回执行状态 1 成功 2 失败',
  `record_time` datetime DEFAULT NULL COMMENT '记录-时间'
) ENGINE=MyISAM COMMENT='性能日志'

create table if not exists hk_diction(
  id int(11) primary key AUTO_INCREMENT comment '编号',
  key_name varchar(20) comment '鍵',
  key_value varchar(100) comment '值',
  type varchar(20) comment '类型'
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='字典';

CREATE TABLE `hk_sys_user` (
  `user_id` int(1) primary key AUTO_INCREMENT NOT NULL COMMENT '用户编号',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `user_name` varchar(64) NOT NULL COMMENT '账号',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态 1 有效 2 无效 3',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `real_name` varchar(64) NOT NULL COMMENT '真实姓名',
  `telephone` varchar(64) DEFAULT NULL COMMENT '手机管理',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_user` varchar(30) DEFAULT NULL COMMENT '创建人',
  `user_level` tinyint(1) DEFAULT '1' COMMENT '用户级别 1:系统级 2 社区级',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `is_delete` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否删除 1 否 2 是',
  unique key `user_name_key` (`user_name`),
  unique key `email_key` (`email`),
  unique key `telephone_key` (`telephone`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='业务数据分支-系统用户';

CREATE TABLE `hk_sys_user_role` (
  `user_id` INT(11) NOT NULL COMMENT '用户-编号',
  `role_id` VARCHAR(32) NOT NULL COMMENT '角色-编号',
  KEY `role_id_key` (`role_id`),
  KEY `member_id_key` (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户-角色';

CREATE TABLE `hk_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色-编号',
  `role_code` varchar(30) NOT NULL COMMENT '角色-编号',
  `description` varchar(30) NOT NULL DEFAULT '' COMMENT '角色-备注',
  `role_name` varchar(30) NOT NULL COMMENT '角色-名称',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '角色-状态 1启用 2禁用',
  `level` tinyint(1) NOT NULL DEFAULT '1' COMMENT '角色-级别 1系统 2社区',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name_key` (`role_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='系统-角色';

CREATE TABLE `hk_sys_resource` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '资源-编号', 
  `res_path` VARCHAR(1024) DEFAULT NULL COMMENT '资源-路径',
  `res_key` VARCHAR(128) NOT NULL COMMENT '资源-唯一KEY值',
  `res_name` VARCHAR(128) NOT NULL COMMENT '资源-名称',
  `res_type` VARCHAR(20) DEFAULT NULL COMMENT '资源-类型 1 菜单(MENU) 2 API(FUNCTION) 3 API_BTN API和按钮  4 按钮',
  `res_status` TINYINT(1) DEFAULT NULL COMMENT '资源-状态',
  `res_pid` VARCHAR(32) DEFAULT NULL COMMENT '资源-父类-编号',
  `res_weight` INT(11) DEFAULT NULL COMMENT '权重',
  PRIMARY KEY (`id`),
  UNIQUE KEY `res_path_key` (`res_key`)
) ENGINE=INNODB  DEFAULT CHARSET=utf8 COMMENT='系统-资源';

CREATE TABLE `hk_sys_role_resource` (
  `role_id` INT(11) NOT NULL COMMENT '角色-编号',
  `resource_id` VARCHAR(32) NOT NULL COMMENT '角色-资源',
  KEY `resource_id_key` (`resource_id`),
  KEY `role_id_key` (`role_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='角色-资源';

drop table if exists hk_steet;
create table if  not exists hk_steet(
  id int(11) primary key auto_increment comment '街道',

)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='街道';

drop table if exists hk_community;
create table if not exists hk_community(
  id int(11) primary key AUTO_INCREMENT COMMENT '编号',
  code varchar(20) NOT NULL COMMENT '社区编码',
  province_no varchar(12) comment '归属省份',
  city_no varchar(12) comment '归属市区',
  region_no varchar(12) COMMENT '归属镇区',
  steet_str varchar(50) comment '街道-镇',
  village_str varchar(50) COMMENT '社区/村',
  name varchar(30) not null COMMENT '小区名称',
  detail_address varchar(150) COMMENT '详细地址',
  lon varchar(20) COMMENT '经度',
  lat varchar(20) COMMENT '维度',
  link_user varchar(20) COMMENT '联系人',
  link_phone varchar(20) COMMENT '联系手机',
  create_user varchar(20) COMMENT '录入人',
  create_time datetime COMMENT '录入时间', 
  update_time datetime COMMENT '修改时间',
  update_user varchar(20) comment '修改人',
  database_no varchar(50) COMMENT '社区-数据库-编号',
  is_delete tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否删除 1否 2是',
  unique key `name_key`(`name`),
  UNIQUE KEY `cy_code_key` (`code`),
  unique key `db_no`(`database_no`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='业务数据分支-社区表';

drop table if exists hk_community_user;
create table if not exists hk_community_user(
   user_id int(11) not null comment '用户编号',
   community_id int(11) not null comment '社区编号',
   is_default tinyint(1) default 1 comment '是否默认 1:非默认 2 默认',
   key `user_idkey` (`user_id`),
   key `community_id_key`(`community_id`)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='业务数据分支-社区-用户-关联表';

drop table if exists hk_community_device;
create table if not exists hk_community_device(
  c_id varchar(32) not null comment '社区编号',
  device_code varchar(32) not null comment '设备编号',
   KEY `c_id_key` (`c_id`),
   key `device_code_key`(`device_code`)
)engine = Innodb default charset = utf8 comment '业务数据分支-社区-设备-关联表';

DROP TABLE IF EXISTS hk_device_module;
CREATE TABLE IF NOT EXISTS hk_device_module(
  id INT(11) PRIMARY KEY AUTO_INCREMENT,
  module_name VARCHAR(30) NOT NULL COMMENT '模块名称',
  create_time DATETIME COMMENT '添加时间',
  add_userid VARCHAR(30) COMMENT '添加用户id',
  is_enble INT(11) DEFAULT '1' COMMENT '是否可用',
  code VARCHAR(30) COMMENT '设备唯一标识'
)engine = Innodb DEFAULT CHARSET=utf8 COMMENT '设备模块表';