package com.hotcomm.community.common.service.system;

import java.util.List;
import java.util.Map;

import com.hotcomm.community.common.bean.db.system.ResourceDM;
import com.hotcomm.community.common.bean.pa.system.ResourcePA;
import com.hotcomm.community.common.bean.ui.system.ResourcMenuUM;
import com.hotcomm.community.common.bean.ui.system.ResourceGradingUM;
import com.hotcomm.community.common.bean.ui.system.ResourceUM;
import com.hotcomm.framework.web.exception.HKException;

public interface ResourceService {

	/**
	 * 新增资源
	 * @param resourcePA
	 * @throws HKException
	 */
	public void addResource(ResourcePA resourcePA) throws HKException;

	/**
	 * 删除资源
	 * @param resourceId
	 * @throws HKException
	 */
	public void delResource(Integer resourceId) throws HKException;

	/**
	 * 修改资源
	 * @param resourcePA
	 * @throws HKException
	 */
	public void updateResource(ResourcePA resourcePA) throws HKException;

	/**
	 * 查询资源
	 * @param resourceId
	 * @return ResourceUM
	 * @throws HKException
	 */
	public ResourceUM getResource(Integer resourceId) throws HKException;

	/**
	 * 查询资源菜单列表
	 * @return List
	 * @throws HKException
	 */
	public List<ResourceUM> getResourdTree() throws HKException;

	/**
	 * 根据用户ID查询资源菜单列表
	 * @param userId
	 * @return List
	 * @throws HKException
	 */
	public List<ResourceDM> getResourcesByUserId(Integer userId) throws HKException;

	/**
	 * 查询资源菜单
	 * @return List
	 * @throws HKException
	 */
	public List<ResourcMenuUM> getMenus() throws HKException;

	/**
	 * 根据用户ID查询资源按钮列表
	 * @param userId
	 * @return Map
	 * @throws HKException
	 */
	public Map<Object, Map<String, Boolean>> getResourcButtons(Integer userId) throws HKException;

	/**
	 * 获取
	 * @param userId
	 * @return Map
	 * @throws HKException
	 */
	public List<ResourceGradingUM> getGradingResources(Integer userId) throws HKException;
}
