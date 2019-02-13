package com.hotcomm.community.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hotcomm.community.common.bean.db.system.ResourceDM;
import com.hotcomm.community.common.bean.pa.system.ResourcePA;
import com.hotcomm.community.common.bean.ui.system.ResourcMenuUM;
import com.hotcomm.community.common.bean.ui.system.ResourceGradingUM;
import com.hotcomm.community.common.bean.ui.system.ResourceUM;

public interface ResourceMapper {

	public void addResource(ResourcePA resourcePA);

	public void delResource(@Param("resourceId") Integer resourceId);

	public void updateResource(ResourcePA resourcePA);

	public ResourceUM getResource(@Param("resourceId") Integer resourceId);

	List<ResourceDM> getResourcesByUserId(@Param("userId") Integer userId);

	List<ResourceGradingUM> getResourcesGradingByUserId(@Param("userId") Integer userId);

	List<ResourceUM> getResourdTree();

	List<ResourcMenuUM> getMenus();

	Integer isPathExist(@Param("path") String path);

	Integer isKeyExist(@Param("key") String key);

}
