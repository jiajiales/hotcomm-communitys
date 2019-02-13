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

	public static final Logger ROOT = LoggerFactory.getLogger(SystemApiTest.class);

	private void post(List<HotHttpEntity> requestMap, String url) {
		requestMap.add(new HotHttpEntity("source", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("token", EntityEnum.TEXT, token));
		HttpClientUtils.post(requestMap, host + url);
	}

	/*************************社区管理*************************/
	@Test
	public void getCommunity() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("cid", EntityEnum.TEXT, communityId));
		this.post(requestMap, "/community/get");
	}

	@Test
	public void getCommunityList() {
		this.post(new ArrayList<HotHttpEntity>(), "/community/list");
	}

	@Test
	public void queryPageCommunity() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		this.post(requestMap, "/community/page");
	}

	@Test
	public void getCommunityRoleUserList() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("cid", EntityEnum.TEXT, communityId));
		this.post(requestMap, "/community/roleUserList");
	}

	@Test
	public void getUserCommunity() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("userId", EntityEnum.TEXT, 7));
		this.post(requestMap, "/community/getUserCommunity");
	}

	@Test
	public void getCommunityListAll() {
		this.post(new ArrayList<HotHttpEntity>(), "/community/listAll");
	}

	@Test
	public void getCommunityUserList() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("cid", EntityEnum.TEXT, 7));
		this.post(requestMap, "/community/userList");
	}

	@Test
	public void getCommunityListDetail() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("userId", EntityEnum.TEXT, communityId));
		this.post(requestMap, "/community/listDetail");
	}

	/*************************工作人员管理*************************/
	@Test
	public void getWorker() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("workerId", EntityEnum.TEXT, 3));
		this.post(requestMap, "/worker/get");
	}

	@Test
	public void getWorkerList() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("cid", EntityEnum.TEXT, communityId));
		this.post(requestMap, "/worker/list");
	}

	@Test
	public void queryPageWorker() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("cid", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		this.post(requestMap, "/worker/page");
	}

	/*************************用户管理*************************/
	@Test
	public void getUser() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("userId", EntityEnum.TEXT, 7));
		this.post(requestMap, "/user/get");
	}

	@Test
	public void getUserList() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("cid", EntityEnum.TEXT, communityId));
		this.post(requestMap, "/user/list");
	}

	@Test
	public void queryPageUser() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("cid", EntityEnum.TEXT, communityId));
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		this.post(requestMap, "/user/page");
	}

	/*************************角色管理*************************/
	@Test
	public void getRole() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("roleId", EntityEnum.TEXT, 1));
		this.post(requestMap, "/role/get");
	}

	@Test
	public void getRoleList() {
		this.post(new ArrayList<HotHttpEntity>(), "/role/list");
	}

	@Test
	public void getRoleResources() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("roleId", EntityEnum.TEXT, 1));
		this.post(requestMap, "/role/getRoleResources");
	}

	/*************************资源管理*************************/
	@Test
	public void getResource() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("resId", EntityEnum.TEXT, 1));
		this.post(requestMap, "/resource/get");
	}

	@Test
	public void getResourceList() {
		this.post(new ArrayList<HotHttpEntity>(), "/resource/list");
	}

	@Test
	public void getResourceMenus() {
		this.post(new ArrayList<HotHttpEntity>(), "/resource/menus");
	}

	@Test
	public void getResourceGrading() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("userId", EntityEnum.TEXT, 1));
		this.post(requestMap, "/resource/gradingGet");
	}

	/*************************操作日志管理*************************/
	@Test
	public void queryPageOperateLog() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		this.post(requestMap, "/log/operate/page");
	}

	/*************************性能日志管理*************************/
	@Test
	public void queryPagePerformanceLog() {
		List<HotHttpEntity> requestMap = new ArrayList<HotHttpEntity>();
		requestMap.add(new HotHttpEntity("pageIndex", EntityEnum.TEXT, 1));
		requestMap.add(new HotHttpEntity("pageSize", EntityEnum.TEXT, 20));
		this.post(requestMap, "/log/performance/page");
	}

}
