package com.hotcomm.community.api.test.add;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.hotcomm.community.common.bean.db.car.CarStrDM;
import com.hotcomm.community.common.bean.pa.car.CarRegPA;
import com.hotcomm.community.common.bean.pa.corporation.CorPerRelationListPA;
import com.hotcomm.community.common.bean.pa.corporation.CorporationPA;
import com.hotcomm.community.common.service.car.CarRegService;
import com.hotcomm.community.common.service.car.CarStrService;
import com.hotcomm.community.common.service.corporation.CorporationService;
import com.hotcomm.community.resful.CommunityRunner;
import com.hotcomm.framework.web.exception.HKException;

import net.sf.json.JSONObject;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {CommunityRunner.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class CarServiceTest{

	@Autowired
	private CarRegService carRegService;
	
	@Autowired
	private CarStrService carStrService;
	
	@Autowired
	private CorporationService corporationService;
	
	
	String[] colorStr = {"白色","红色","银灰色","黑色","蓝色","粉红色"};
	String[] brandStr = {"本田","福田","奔驰","大众","BYD","宝马","丰田","吉利","法拉利","福特","现代","江淮"};

	//批量新增登记车辆
	@Test
	public void addRegCar() throws HKException {
		for (int i = 0; i < 20; i++) {
			CarRegPA carRegDM = new CarRegPA();
			String brand = brandStr[(int)(Math.random()*brandStr.length)];
			String modelTypeStr = "";
			String model = "";
			switch (brand) {
				case "本田": model ="奥德赛 " ;modelTypeStr="SUV"; break;
				case "奔驰": model ="GLS" ; modelTypeStr="SUV";break;
				case "大众": model ="桑塔拉" ;modelTypeStr="轿车"; break;
				case "BYD": model ="唐" ; modelTypeStr="SUV";break;
				case "宝马": model ="X5" ; modelTypeStr="轿车";break;
				case "丰田": model ="卡罗拉" ; modelTypeStr="轿车";break;
				case "吉利": model ="帝豪" ; modelTypeStr="轿车";break;
				case "江淮": model ="瑞风" ;modelTypeStr="货车"; break;
				case "法拉利": model ="458" ;modelTypeStr="跑车"; break;
				case "福特": model ="锐界" ; modelTypeStr="轿车";break;
				case "福田": model ="欧曼" ; modelTypeStr="大型车";break;
				case "现代": model ="名图 " ; modelTypeStr="轿车";break;
				default: model ="未知" ;break;
			}
			carRegDM.setBrand(brand);
			carRegDM.setModel(model);
			carRegDM.setNum("粤B"+this.getRandom(5));
			carRegDM.setCommunityId(1);
			carRegDM.setCreateUser("社区管理员");
			carRegDM.setColor(colorStr[(int)(Math.random()*colorStr.length)]);
			carRegDM.setModelType(modelTypeStr);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("side", "http://39.108.54.252:8185/hotcomm-community/common/20190103050235_e517d8e99d524bd3bb29fd6191946fb7.jpeg");
			jsonObject.put("tail", "http://39.108.54.252:8185/hotcomm-community/common/20190103050213_cb8042852d5e41efbb86e09146d77158.jpg");
			jsonObject.put("front", "http://39.108.54.252:8185/hotcomm-community/common/20190103030254_c5d8e526f73644b78e52165e7de7008f.jpg");
			jsonObject.put("else", "http://39.108.54.252:8185/hotcomm-community/common/20190103040231_7bb06b66ef114bb1a3163c3407117547.jpg");		
			carRegDM.setPhoto(jsonObject.toString());
			Integer type =  new java.util.Random().nextInt(3);
			Integer personId = null;
			Integer roomId = null;
			Integer companyId = null;
			Integer labelId =null;
			switch (type) {
			case 0:	 personId = new java.util.Random().nextInt(19)+1;roomId = new java.util.Random().nextInt(19)+1;break;
			case 1:companyId =  new java.util.Random().nextInt(9)+1;break; 
			case 2: labelId = new java.util.Random().nextInt(3)+1; break;
			default:break;
			}
			carRegDM.setType(type);
			carRegDM.setPersonId(personId);
//			carRegDM.setRoomId(roomId);
			carRegDM.setCompanyId(companyId);
			carRegDM.setLabelId(labelId);
			System.out.println("carRegDM:  "+carRegDM);
			carRegService.insert(carRegDM);
		}
	}

	//批量新增陌生车辆
	@Test
	public void addStrCar() throws HKException {
		for (int i = 0; i < 20; i++) {
			CarStrDM carStrDM = new CarStrDM();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("carImg", "http://39.108.54.252:8185/hotcomm-community/common/20190103130227_5ff96d6a0eee4b8e97dc610f165d305e.png");
			carStrDM.setPhoto(jsonObject.toString());
			carStrDM.setCreateUser("社区管理员");
			carStrDM.setCommunityId(1);
			carStrDM.setNum("粤B"+this.getRandom(5));
			String brand = brandStr[(int)(Math.random()*brandStr.length)];
			String model = "";
			String modelTypeStr = "";
			switch (brand) {
				case "本田": model ="奥德赛 " ;modelTypeStr="SUV"; break;
				case "奔驰": model ="GLS" ; modelTypeStr="SUV";break;
				case "大众": model ="桑塔拉" ;modelTypeStr="轿车"; break;
				case "BYD": model ="唐" ; modelTypeStr="SUV";break;
				case "宝马": model ="X5" ; modelTypeStr="轿车";break;
				case "丰田": model ="卡罗拉" ; modelTypeStr="轿车";break;
				case "吉利": model ="帝豪" ; modelTypeStr="轿车";break;
				case "江淮": model ="瑞风" ;modelTypeStr="货车"; break;
				case "法拉利": model ="458" ;modelTypeStr="跑车"; break;
				case "福特": model ="锐界" ; modelTypeStr="轿车";break;
				case "福田": model ="欧曼" ; modelTypeStr="大型车";break;
				case "现代": model ="名图 " ; modelTypeStr="轿车";break;
				default: model ="未知" ;break;
			}
			carStrDM.setBrand(brand);
			carStrDM.setModel(model);
			carStrDM.setModelType(modelTypeStr);
			carStrDM.setColor(colorStr[(int)(Math.random()*colorStr.length)]);
			carStrDM.setState(new java.util.Random().nextInt(2));
			carStrDM.setEnterCount(1);
			carStrDM.setAlarmCount(0);
			System.out.println("carStrDM:  "+carStrDM);
			carStrService.insert(carStrDM);
		}
	}
	
	//批量新增单位
	@Test
	public void addCor() throws HKException {
		List<CorporationPA> corList = new ArrayList<>();
		Integer building = new java.util.Random().nextInt(20)+1;
		Integer unit = new java.util.Random().nextInt(10)+1;
		Integer layer = new java.util.Random().nextInt(20)+1;
		Integer roomNum = Integer.parseInt(this.getRandom(3));
		String busLicense = "http://39.108.54.252:8185/hotcomm-community/common/20190104520907_6d8abcd45812404cb7d3426ac083bfc6.jpg";
		corList.add(new CorporationPA("航康信息","中大365",44,"智慧城区方案解决商",building,unit,layer,roomNum,113.878278,22.940874,1,0,busLicense,"社区管理员"));
		corList.add(new CorporationPA("东莞理工学院","东莞理工学院松山湖校区",39,"大学",building,null,null,null,113.875019,22.902542,6,0,busLicense,"社区管理员"));
		corList.add(new CorporationPA("东莞职业技术学院","东莞职业技术学院松山湖校区",39,"大学",building,null,null,null,113.85723,22.897054,6,0,busLicense,"社区管理员"));
		corList.add(new CorporationPA("星星幼儿园","中大365",39,"智慧城区方案解决商",building,unit,layer,roomNum,113.878278,22.940874,6,0,busLicense,"社区管理员"));
		corList.add(new CorporationPA("松山湖实验中学","松山湖实验中学松山湖校区",39,"中学",building,unit,layer,roomNum,113.871946,22.933483,6,0,busLicense,"社区管理员"));
		corList.add(new CorporationPA("华为南方基地","华为2期",42,"通讯方案解决商",building,null,null,null,113.903586,22.961629,1,3,busLicense,"社区管理员"));
		corList.add(new CorporationPA("嘉荣超市","松山湖万科广场",40,"连锁零售超市",building,unit,layer,roomNum,113.893733,22.930624,4,0,busLicense,"社区管理员"));
		corList.add(new CorporationPA("沃尔玛","松山湖沃尔玛",40,"连锁零售超市",building,unit,layer,roomNum,113.933132,22.944041,4,0,busLicense,"社区管理员"));
		corList.add(new CorporationPA("社区医院","松山湖医院",38,"急救场所",building,null,null,null,113.909672,22.934148,2,0,busLicense,"社区管理员"));
		corList.add(new CorporationPA("松山湖物业公司","松湖烟雨",43,"物业公司",building,unit,layer,roomNum,113.878278,22.940874,1,0,busLicense,"社区管理员"));		
		corList.add(new CorporationPA("在水一方酒店","松湖烟雨",44,"连锁酒店",building,unit,layer,roomNum,113.887265,22.925991,4,0,busLicense,"社区管理员"));	
		corList.add(new CorporationPA("管委会自行车租赁点","松湖大厦",43,"自行车租赁",building,unit,layer,roomNum,113.885827,22.925724,5,0,busLicense,"社区管理员"));	
		for (int i = 0; i < corList.size(); i++) {
			CorporationPA corporationPA = corList.get(i);
			corporationPA.setCommunityId(1);	
			corporationService.insert(corporationPA);
		}
	}
	
	//批量单位关联人口
	@Test
	public void addPerCor() throws HKException {
		List<CorPerRelationListPA> corPerList = new ArrayList<>();
		corPerList.add(new CorPerRelationListPA(1,"21,23"));
		corPerList.add(new CorPerRelationListPA(11,"24,25"));
		corPerList.add(new CorPerRelationListPA(10,"26"));
		corPerList.add(new CorPerRelationListPA(9,"26"));
		corPerList.add(new CorPerRelationListPA(5,"22,27"));
		corPerList.add(new CorPerRelationListPA(6,"28,29"));
		for (int i = 0; i < corPerList.size(); i++) {
			CorPerRelationListPA corPerRelationListDM = corPerList.get(i);
			corPerRelationListDM.setCommunityId(1);
			System.out.println("corPerRelationListDM:  "+corPerRelationListDM);
			corporationService.insertCorPerRelationBatch(corPerRelationListDM);
		}
	}
	
	public String getRandom(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			val += String.valueOf(random.nextInt(10));
		}
		return val;
	}

}
