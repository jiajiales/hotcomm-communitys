package com.hotcomm.community.common.bean.ui.device.doorcontrol;

import java.io.Serializable;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class DevDoorDetailsUM extends DevAttrUM implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer buildingNum;
	private Integer unitNum;
	private Integer floorNum;
	private String devAddress;
	private String devFullAddress;
	private String lat;
	private String lon;
	private Double x;
	private Double y;
	private Integer useType;
	private String pictureList;
	private String installExplain;
	private Integer ownId;
	private String ownName;
	private String ownPhone;
	private Integer installUserid;
	private String installName;
	private String installPhone;
	private String installTime;
	private String videoList;
	private List<String> pictures;
	private List<Object> videos;

	@SuppressWarnings("unused")
	private void setPictureList(String pictureList) {
		Gson gson = new Gson();
		List<String> pic = gson.fromJson(pictureList, new TypeToken<List<String>>() {
		}.getType());
		this.pictures=pic;
		this.pictureList = pictureList;
	}
}
