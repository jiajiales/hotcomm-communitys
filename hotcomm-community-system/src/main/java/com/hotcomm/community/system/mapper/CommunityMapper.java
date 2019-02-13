package com.hotcomm.community.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.hotcomm.community.common.bean.db.system.CommunityDM;
import com.hotcomm.community.common.bean.pa.system.CommunityPagePA;
import com.hotcomm.community.common.bean.ui.system.CommunityDetailListUM;
import com.hotcomm.community.common.bean.ui.system.CommunityListAllUM;
import com.hotcomm.community.common.bean.ui.system.CommunityListUM;
import com.hotcomm.community.common.bean.ui.system.CommunityUM;
import com.hotcomm.community.common.bean.ui.system.UserListUM;

public interface CommunityMapper {

	@Insert("insert into hk_community(province_no,code,city_no,region_no,street_no,village_no,name,detail_address,lon,lat,link_user,link_phone,create_user,create_time,database_no,picture_url,outline_coords)"
			+ " values(#{provinceNo},#{code},#{cityNo},#{regionNo},#{streetNo},#{villageNo},#{cname},#{caddress},#{lon},#{lat},#{linkUser},#{linkPhone},#{createUser},#{createTime},#{databaseNo},#{pictureUrl},#{outlineCoords})")
	@SelectKey(statement = "SELECT LAST_INSERT_ID() AS ID ", keyProperty = "cid", before = false, resultType = int.class)
	public Integer add(CommunityDM cdm);

	public void updateCommunityDoorduDepId(@Param("cid") Integer cid, @Param("doorduDepId") Integer doorduDepId);

	@Select("select database_no from hk_community order by create_time desc limit 1")
	public String getLastDatabaseNO();

	public void delCommunity(CommunityDM cdm);

	public void updateCommunity(CommunityDM cdm);

	public CommunityUM getCommunity(@Param("cid") Integer cid);

	public List<CommunityListAllUM> queryListCommunityAll();

	public List<CommunityListUM> queryListCommunityByUserId(@Param("userId") Integer userId);

	public List<CommunityDetailListUM> queryListCommunityDetail(@Param("userId") Integer userId);

	public List<CommunityDetailListUM> queryListCommunityDetailAll();

	public List<CommunityUM> queryPageCommunity(@Param("params") CommunityPagePA params);

	public Long queryPageCommunityCount(@Param("params") CommunityPagePA params);

	public List<UserListUM> queryUserList(@Param("cid") Integer cid, @Param("roleId") String roleId);

	public Integer isCnameUsed(@Param("cname") String cname);

	public Integer isCodeUsed(@Param("code") String code);

	public void addUserCommunity(@Param("userId") Integer userId, @Param("cids") String[] cids);

	public void delUserCommunity(@Param("userId") Integer userId);

	public String getUserCommunity(@Param("userId") Integer userId);

	public void setUserCommunityDefaultCid(@Param("userId") Integer userId, @Param("defaultCid") Integer defaultCid);

}
