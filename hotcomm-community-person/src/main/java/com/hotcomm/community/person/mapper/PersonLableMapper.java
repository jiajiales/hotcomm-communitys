package com.hotcomm.community.person.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.hotcomm.community.common.bean.db.person.LableTagDM;
import com.hotcomm.community.common.bean.ui.person.PersonLableUM;
import org.apache.ibatis.annotations.Select;

public interface PersonLableMapper {
	
	/**
	 * 标签列表(分页)
	 * @param tableParams
	 * @return
	 */
	public Page<PersonLableUM> pagelist(@Param("tableParams")Map<String, Object> tableParams);
	
	/**
	 * 标签信息详情
	 * @param tableParams
	 * @param id
	 * @return
	 */
	public PersonLableUM PersonLableInfo(@Param("tableParams")Map<String, Object> tableParams,@Param("id") Integer id);
	
	/**
	 * 删除人口标签
	 * @param tableParams
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM ${tableParams.dynamic_dbname}.hk_person_lable WHERE id=#{id} AND `source_type`!=0")
	public Integer deletePersonLable(@Param("tableParams")Map<String, Object> tableParams,@Param("id") Integer id);
	
	/**
	 * 修改人口标签
	 * @param tableParams
	 * @param id 标签id
	 * @param typeCode	标签类型
	 * @param name 标签名称
	 * @param description 标签说明
	 * @return
	 */
	public Integer updatePersonLable(@Param("tableParams")Map<String, Object> tableParams,
									@Param("id") Integer id,
									@Param("typeCode")String typeCode,
									@Param("name") String name,
									@Param("description")String description);/**
	 * 修改人口标签
	 * @param tableParams
	 * @param id 标签id
	 * @param uid uid
	 * @return
	 */
	public Integer updatePersonLableId(@Param("tableParams")Map<String, Object> tableParams,
									@Param("id") Integer id,
									@Param("uid")Integer uid);
	
	/**
	 * 添加人口标签
	 * @param tableParams 
	 * @param typeCode 标签类型
	 * @param name	标签名称
	 * @param description	标签说明
	 * @param createUser	创建人
	 * @return
	 */
	public Integer insertPersonLable(@Param("tableParams")Map<String, Object> tableParams,
									@Param("typeCode")String typeCode,
									@Param("name") String name,
									@Param("description")String description,
									@Param("createUser")Integer createUser);
	
	/**
	 * 所有标签
	 * @param tableParams
	 * @return
	 */
	public List<LableTagDM> selectLable(@Param("tableParams")Map<String, Object> tableParams);

	/*验证*/

	/**
	 * 检查标签是否绑定人物
	 * @param tableParams
	 * @param id
	 * @return
	 */
	Integer checkLablePersonRe(@Param("tableParams")Map<String, Object> tableParams,@Param("id") Integer id);

	/**
	 * 检查标签名称是否重复
	 * @param tableParams
	 * @param name
	 * @return
	 */
	@Select("SELECT COUNT(id) FROM ${tableParams.dynamic_dbname}.hk_person_lable WHERE `name`=#{name}")
	Integer checkLableName(@Param("tableParams")Map<String, Object> tableParams,@Param("name") String name);
}
