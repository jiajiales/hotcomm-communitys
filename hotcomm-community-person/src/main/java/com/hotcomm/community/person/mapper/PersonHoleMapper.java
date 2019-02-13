package com.hotcomm.community.person.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.person.HoleAndDetailInfoDM;
import com.hotcomm.community.common.bean.db.person.PersonHoleDM;
import com.hotcomm.community.common.bean.db.person.PersonHoleDetailDM;
import com.hotcomm.community.common.bean.db.person.PersonHoleInfoDM;
import com.hotcomm.community.common.bean.pa.person.AddPersonHolePA;

public interface PersonHoleMapper {

	
	Page<PersonHoleDM> PersonHolePage(@Param("tableParams")Map<String, Object> tableParams);
	
	/**
	 * 启动/关闭  人口预警布控
	 * @param tableParams
	 * @param onOff 0 关闭  1启动
	 * @return
	 */
	@Update("UPDATE ${tableParams.dynamic_dbname}.hk_person_hole hole SET hole.`hole_status`=#{onOff} WHERE hole.`id`=#{id}")
	Integer PersonHoleOnOff(@Param("tableParams")Map<String, Object> tableParams,@Param("onOff")Integer onOff,@Param("id")Integer id);

	/**
	 * 删除预警布控规则
	 * @param tableParams
	 * @param id 布控规则id
	 * @return
	 */
	@Delete("DELETE FROM ${tableParams.dynamic_dbname}.`hk_person_hole_detail` WHERE id=#{id}")
	Integer delPersonHoleDetailById(@Param("tableParams")Map<String, Object> tableParams,@Param("id")Integer id);

	/**
	 * 删除预警布控
	 * @param tableParams
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM  ${tableParams.dynamic_dbname}.hk_person_hole WHERE id=#{id}")
	Integer delPersonHole(@Param("tableParams")Map<String, Object> tableParams,@Param("id")Integer id);
	/**
	 * 删除预警布控相关规则
	 * @param tableParams
	 * @param id 布控id
	 * @return
	 */
	@Delete("DELETE FROM ${tableParams.dynamic_dbname}.hk_person_hole_detail WHERE `hole_id`=#{id}")
	Integer delPersonHoleDetail(@Param("tableParams")Map<String, Object> tableParams,@Param("id")Integer id);
	
	/**
	 * 布控详情
	 * @param tableParams
	 * @param id
	 * @return
	 */
	@Select("SELECT "
			+ "hole.`id`,"
			+ "hole.`hole_title`,"
			+ "hole.`hole_type`,"
			+ "hole.`hole_populations`,"
			+ "hole.`alarm_level`,"
			+ "hole.`hole_status`,"
			+ "hole.`record_population_time`,"
			+ "hole.`create_time`,"
			+ "hole.`create_user`,"
			+ "hole.`update_time`,"
			+ "hole.`update_user`,"
			+ "hole.`push` "
			+ "FROM ${tableParams.dynamic_dbname}.hk_person_hole hole "
			+ "WHERE hole.`id`=#{id}")
	PersonHoleInfoDM selectPersonHoleInfo(@Param("tableParams")Map<String, Object> tableParams,@Param("id")Integer id);
	
	/**
	 * 布控规则详情
	 * @param tableParams
	 * @param id 布控id
	 * @return
	 */
	List<PersonHoleDetailDM> selectHoleDetailInfo(@Param("tableParams")Map<String, Object> tableParams,@Param("id")Integer id);
	
	/**
	 * 添加布控
	 * @param tableParams
	 * @return
	 */
	int insertPersonHole(@Param("tableParams")Map<String, Object> tableParams,@Param("pa")AddPersonHolePA pa);
	
	/**
	 * 添加布控规则
	 * @param tableParams
	 * @return
	 */
	Integer insertPersonHoleDetail(@Param("tableParams")Map<String, Object> tableParams,@Param("pas")PersonHoleDetailDM pas);
	
	/**
	 * 根据时间筛选出已启动的预警布控 与 规则信息
	 * @param tableParams
	 * @param time 时间
	 * @return
	 */
	List<HoleAndDetailInfoDM> getHoleAndDetail(@Param("tableParams")Map<String, Object> tableParams,@Param("time")String time);
	
	/**
	 * 修改布控
	 * @param tableParams
	 * @param pa
	 * @return
	 */
	Integer updateHole(@Param("tableParams")Map<String, Object> tableParams,@Param("pa")AddPersonHolePA pa);
	
	/**
	 * 修改布控规则
	 * @param tableParams
	 * @param pas
	 * @return
	 */
	Integer updateHoleDetail(@Param("tableParams")Map<String, Object> tableParams,@Param("pas")PersonHoleDetailDM pas);

	/**
	 * 修改
	 * @param tableParams
	 * @param pId
	 * @param lableId
	 * @return
	 */
	Integer updateHolePopulationLableId(@Param("tableParams")Map<String, Object> tableParams,@Param("pId")Integer pId,@Param("lableId")Integer lableId);
}