package com.hotcomm.community.data.car;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.hotcomm.community.common.bean.pa.car.CarPassRecordsPA;
import com.hotcomm.community.common.bean.pa.car.CarRegPagePA;
import com.hotcomm.community.common.bean.pa.car.CarStrPagePA;
import com.hotcomm.community.common.bean.ui.car.CarRegUM;
import com.hotcomm.community.common.bean.ui.car.CarStrUM;
import com.hotcomm.community.common.service.car.CarPassRecordsService;
import com.hotcomm.community.common.service.car.CarRegService;
import com.hotcomm.community.common.service.car.CarStrService;
import com.hotcomm.community.data.basTask.basTask;
import com.hotcomm.framework.comm.PageInfo;
import com.hotcomm.framework.utils.DateUtils;


@Component
public class CarTask extends basTask {

	@Autowired
	private CarPassRecordsService carPassRecordsService;
	
	@Autowired
	private CarStrService carStrService;
	
	@Autowired
	private CarRegService carRegService;
	
	 /****新增车辆通行记录*****/
	  @Scheduled(cron = 	carCron)
	  public void insetPassRecords(){
		  System.out.println("************车辆通行记录开始执行，执行时间为："+DateUtils.getTimes()+"*****************");
		  String[] videoCode = {"4H05231PAJ554A3","3M05329PAKED676"};
		  CarPassRecordsPA carPassRecords  = new CarPassRecordsPA();
		  carPassRecords.setCommunityId(1);
		  carPassRecords.setVideoCode(videoCode[(int)(Math.random()*videoCode.length)]);
		  carPassRecords.setCreateTime(DateUtils.getNowTime());
		  
		  CarPassRecordsPA carPassRecordsPA =  new  CarPassRecordsPA();
		  carPassRecordsPA.setCommunityId(1);
		  CarRegPagePA carRegPA = new CarRegPagePA();
		  carRegPA.setPageIndex(new java.util.Random().nextInt(3));
		  carRegPA.setPageSize(new java.util.Random().nextInt(9)+1);
		  carRegPA.setCommunityId(1);
		  PageInfo<CarRegUM> carRegList = carRegService.page(carRegPA); 
		  carRegList.getData().stream().forEach(regCar -> {
			  carPassRecords.setImgPath(regCar.getFrontPhoto());
			  carPassRecords.setBrand(regCar.getBrand());
			  carPassRecords.setModelType(regCar.getModelType());
			  carPassRecords.setModel(regCar.getModel());
			  carPassRecords.setColor(regCar.getColor());
			  carPassRecords.setCarNum(regCar.getNum());
			  carPassRecordsService.insert(carPassRecords);
		  });
		  
		  CarStrPagePA carStrPA = new CarStrPagePA();
		  carStrPA.setCommunityId(1);;
		  carStrPA.setPageIndex(new java.util.Random().nextInt(3));
		  carStrPA.setPageSize(new java.util.Random().nextInt(9)+1);
		  PageInfo<CarStrUM> carStrList  = carStrService.page(carStrPA);
		  carStrList.getData().stream().forEach(strCar ->{
			  carPassRecords.setImgPath(strCar.getCarImg());
			  carPassRecords.setBrand(strCar.getBrand());
			  carPassRecords.setModelType(strCar.getModelType());
			  carPassRecords.setModel(strCar.getModel());
			  carPassRecords.setColor(strCar.getColor());
			  carPassRecords.setCarNum(strCar.getNum());
			  carPassRecordsService.insert(carPassRecords);
		  });
	  }
  
}
