package com.hotcomm.community.api.test.query.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotcomm.community.api.test.query.CommunityTest;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HttpClientUtils;

public class SystemApiTest extends CommunityTest {

	public static final Logger LOG = LoggerFactory.getLogger(SystemApiTest.class);

	private void post(List<HotHttpEntity> requestMap, String url) {
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		HttpClientUtils.post(requestMap, host + url);
	}

	/*************************社区管理*************************/
	@Test
	public void getCommunity() {
		LOG.info("获取社区");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("cid", EntityEnum.TEXT, communityId));
		this.post(requestMap, "/community/get");
	}

	@Test
	public void getCommunityList() {
		LOG.info("获取社区列表");
		this.post(new ArrayList<HotHttpEntity>(), "/community/list");
	}

	@Test
	public void queryPageCommunity() {
		LOG.info("分页查询社区");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		this.post(requestMap, "/community/page");
	}

	@Test
	public void getCommunityRoleUserList() {
		LOG.info("获取用户列表（附带角色名称）");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("cid", EntityEnum.TEXT, communityId));
		this.post(requestMap, "/community/roleUserList");
	}

	@Test
	public void getUserCommunity() {
		LOG.info("获取用户社区关联");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("userId", EntityEnum.TEXT, 7));
		this.post(requestMap, "/community/getUserCommunity");
	}

	@Test
	public void getCommunityListAll() {
		LOG.info("获取所有社区列表");
		this.post(new ArrayList<HotHttpEntity>(), "/community/listAll");
	}

	@Test
	public void getCommunityUserList() {
		LOG.info("获取用户列表");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("cid", EntityEnum.TEXT, 7));
		this.post(requestMap, "/community/userList");
	}

	@Test
	public void getCommunityListDetail() {
		LOG.info("获取社区列表(详细信息)");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("userId", EntityEnum.TEXT, communityId));
		this.post(requestMap, "/community/listDetail");
	}

	/*************************工作人员管理*************************/
	@Test
	public void getWorker() {
		LOG.info("获取工作人员");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("workerId", EntityEnum.TEXT, 3));
		this.post(requestMap, "/worker/get");
	}

	@Test
	public void getWorkerList() {
		LOG.info("获取工作人员列表");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("cid", EntityEnum.TEXT, communityId));
		this.post(requestMap, "/worker/list");
	}

	@Test
	public void queryPageWorker() {
		LOG.info("分页查询工作人员");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("cid", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		this.post(requestMap, "/worker/page");
	}

	/*************************用户管理*************************/
	@Test
	public void getUser() {
		LOG.info("获取用户");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("userId", EntityEnum.TEXT, 7));
		this.post(requestMap, "/user/get");
	}

	@Test
	public void getUserList() {
		LOG.info("获取用户列表");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("cid", EntityEnum.TEXT, communityId));
		this.post(requestMap, "/user/list");
	}

	@Test
	public void queryPageUser() {
		LOG.info("分页查询用户");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("cid", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		this.post(requestMap, "/user/page");
	}

	/*************************角色管理*************************/
	@Test
	public void getRole() {
		LOG.info("获取角色");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("roleId", EntityEnum.TEXT, 1));
		this.post(requestMap, "/role/get");
	}

	@Test
	public void getRoleList() {
		LOG.info("获取角色列表");
		this.post(new ArrayList<HotHttpEntity>(), "/role/list");
	}

	@Test
	public void getRoleResources() {
		LOG.info("获取角色资源关联");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("roleId", EntityEnum.TEXT, 1));
		this.post(requestMap, "/role/getRoleResources");
	}

	/*************************资源管理*************************/
	@Test
	public void getResource() {
		LOG.info("获取资源");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("resId", EntityEnum.TEXT, 1));
		this.post(requestMap, "/resource/get");
	}

	@Test
	public void getResourceList() {
		LOG.info("获取资源列表");
		this.post(new ArrayList<HotHttpEntity>(), "/resource/list");
	}

	@Test
	public void getResourceMenus() {
		LOG.info("获取资源菜单列表");
		this.post(new ArrayList<HotHttpEntity>(), "/resource/menus");
	}

	@Test
	public void getResourceGrading() {
		LOG.info("获取分级资源菜单列表");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("userId", EntityEnum.TEXT, 1));
		this.post(requestMap, "/resource/gradingGet");
	}

	/*************************操作日志管理*************************/
	@Test
	public void queryPageOperateLog() {
		LOG.info("分页查询操作日志");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		this.post(requestMap, "/log/operate/page");
	}

	/*************************性能日志管理*************************/
	@Test
	public void queryPagePerformanceLog() {
		LOG.info("分页查询性能日志");
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		this.post(requestMap, "/log/performance/page");
	}

}
