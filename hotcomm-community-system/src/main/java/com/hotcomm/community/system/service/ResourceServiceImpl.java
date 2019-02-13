package com.hotcomm.community.system.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotcomm.community.common.bean.db.system.ResourceDM;
import com.hotcomm.community.common.bean.en.system.ResourceEN.ResourceStatus;
import com.hotcomm.community.common.bean.en.system.ResourceEN.ResourceType;
import com.hotcomm.community.common.bean.pa.system.ResourcePA;
import com.hotcomm.community.common.bean.ui.system.ResourcMenuUM;
import com.hotcomm.community.common.bean.ui.system.ResourceGradingUM;
import com.hotcomm.community.common.bean.ui.system.ResourceUM;
import com.hotcomm.community.common.service.BaseService;
import com.hotcomm.community.common.service.system.ResourceService;
import com.hotcomm.community.common.service.system.RoleService;
import com.hotcomm.community.system.mapper.ResourceMapper;
import com.hotcomm.framework.web.exception.HKException;

@Service
public class ResourceServiceImpl extends BaseService implements ResourceService {

	@Autowired
	ResourceMapper resourceMapper;

	@Autowired
	RoleService roleService;

	@Override
	public void addResource(ResourcePA resourcePA) throws HKException {
//		if (resourceMapper.isPathExist(resourcePA.getPath()) != null)
//			throw this.exceptionManager.configLog(error).errorRecord("RES0001", HKException.instance());

		if (resourceMapper.isKeyExist(resourcePA.getKey()) != null)
			throw this.exceptionManager.configLog(error).errorRecord("RES0002", HKException.instance());

		if (resourcePA.getPid() == null)
			resourcePA.setPid(0);

		if (resourcePA.getIcon() == null)
			resourcePA.setIcon("");

		resourceMapper.addResource(resourcePA);
	}

	@Override
	public void delResource(Integer resourceId) throws HKException {
		resourceMapper.delResource(resourceId);	
	}

	@Override
	public void updateResource(ResourcePA resourcePA) throws HKException {
		ResourceUM resourceUM = resourceMapper.getResource(resourcePA.getResId());

//		if (!resourceUM.getPath().equals(resourcePA.getPath())) {
//			if (resourceMapper.isPathExist(resourcePA.getPath()) != null){
//				throw this.exceptionManager.configLog(error).errorRecord("RES0003", HKException.instance());
//			}
//		}

		if (!resourceUM.getKey().equals(resourcePA.getKey())) {
			if (resourceMapper.isKeyExist(resourcePA.getKey()) != null)
				throw this.exceptionManager.configLog(error).errorRecord("RES0004", HKException.instance());
		}

		if (resourcePA.getStatus() == null)
			resourcePA.setStatus(ResourceStatus.DISABLE.getValue());

		if (resourcePA.getIcon() == null)
			resourcePA.setIcon("");

		resourceMapper.updateResource(resourcePA);
	}

	@Override
	public ResourceUM getResource(Integer resourceId) throws HKException {
		return resourceMapper.getResource(resourceId);
	}

	@Override
	public List<ResourceUM> getResourdTree() throws HKException {
		return resourceMapper.getResourdTree();
	}

	@Override
	public List<ResourceDM> getResourcesByUserId(Integer userId) throws HKException {
		if (!roleService.isUserRoleStatusEnable(userId))
			return new ArrayList<ResourceDM>();

		return resourceMapper.getResourcesByUserId(userId);
	}

	@Override
	public List<ResourcMenuUM> getMenus() throws HKException {
		return resourceMapper.getMenus();
	}

	@Override
	public Map<Object, Map<String, Boolean>> getResourcButtons(Integer userId) throws HKException {
		Map<Object, Map<String, Boolean>> result = new HashMap<>();
		List<ResourceDM> memberResource = this.getResourcesByUserId(userId);
		List<ResourceUM> allResource = this.getResourdTree();

		for (ResourceUM resource : allResource) {
			ResourceType type = ResourceType.getByValue(resource.getType());

			if (type == ResourceType.API_BUTON || type == ResourceType.BUTTON) {
				String key = resource.getKey();
				Integer pid = resource.getPid();
				Map<String, Boolean> v = result.get(pid);

				if (v == null)
					v = new HashMap<String, Boolean>();

				v.put(key, exists(memberResource, key));
				result.put(pid, v);
			}
		}

		for (ResourceUM resource : allResource) {
			ResourceType type = ResourceType.getByValue(resource.getType());

			if (type == ResourceType.MENU) {
				Map<String, Boolean> buttons = result.get(resource.getResId());

				if (buttons != null) {
					result.put(resource.getKey(), buttons);
					result.remove(resource.getResId());
				}
			}
		}

		return result;
	}

	private boolean exists(List<ResourceDM> resources, String key) throws HKException {
		for (ResourceDM rs : resources) {
			if (rs.getKey().equals(key))
				return true;
		}
		return false;
	}

	@Override
	public List<ResourceGradingUM> getGradingResources(Integer userId) throws HKException {
		if (!roleService.isUserRoleStatusEnable(userId))
			return new ArrayList<ResourceGradingUM>();

		List<ResourceGradingUM> ResourceList = resourceMapper.getResourcesGradingByUserId(userId);
		List<ResourceGradingUM> firstMenus = new ArrayList<>();
		List<ResourceGradingUM> sencondMenus = new ArrayList<>();
		List<ResourceGradingUM> thirdResource = new ArrayList<>();

		ResourceList.forEach(r -> {
			if (r.getType() == ResourceType.MENU.getValue()) {
				if (r.getPid() != 0)
					sencondMenus.add(r);
				if (r.getPid() == 0) {
					firstMenus.add(r);
				}
			} else
				thirdResource.add(r);
		});

		sencondMenus.forEach(r2 -> {
			Integer id = r2.getResId();
			List<ResourceGradingUM> childrens = r2.getChildrens();
			thirdResource.forEach(r3 -> {
				Integer pid = r3.getPid();
				if (id == pid) {
					childrens.add(r3);
					r2.setChildrens(childrens);
				}
			});
			Collections.sort(childrens);
		});

		firstMenus.forEach(r1 -> {
			Integer id = r1.getResId();
			List<ResourceGradingUM> childrens = r1.getChildrens();
			sencondMenus.forEach(r2 -> {
				Integer pid = r2.getPid();
				if (id == pid) {
					childrens.add(r2);
					r1.setChildrens(childrens);
				}
			});
			Collections.sort(childrens);
		});

		Collections.sort(firstMenus);
		return firstMenus;
	}

}
