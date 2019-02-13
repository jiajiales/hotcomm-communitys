package com.hotcomm.community.data.person;

import com.hotcomm.community.common.service.person.PersonRecordService;
import com.hotcomm.community.data.basTask.basTask;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
public class PersonTask extends basTask {

	@Value("${face.addRecord.url}")
	public String url;
	@Autowired
	private PersonRecordService personRecordService;
	ExecutorService service = Executors.newFixedThreadPool(5);
//	ExecutorService service = Executors.newCachedThreadPool();
	/**
	 * 常住人群概况
	 */
	@Scheduled(cron = personCron)
	public void addPersonRecord(){
		HashSet<Integer> set = new HashSet<Integer>();
		randomSet(0,40,20,set);//随机生成数字组
		System.out.println("*******人脸通行假数据:"+set.size()+"*****************");
		for (int i:set) {
			service.execute(() -> {
				System.out.println("****");
				List<HotHttpEntity> requestMap = new ArrayList<>();
				requestMap.add(new HotHttpEntity("picture", EntityEnum.TEXT, ImageToBase64ByOnline(PersonImg()[i])));
				requestMap.add(new HotHttpEntity("mac", EntityEnum.TEXT, "3M05329PAKED676"));
				HttpClientUtils.post(requestMap, url);
			});
		}
	}

	/**
	 * 多度通行假数据
	 */
	@Scheduled(cron = personCron)
	public void addPersonRecordByDD(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		HashSet<Integer> set = new HashSet<Integer>();
		randomSet(0,36,20,set);//随机生成数字组
		System.out.println("######多度通行假数据:"+set.size()+"条############");
			for (Integer i : set) {
				service.execute(() -> {
					Integer num = i + 1;
					personRecordService.addPersonRecord(2, num.toString(), df.format(new Date()), PersonImg()[i], null, 2, "DMJ6001812-01740");
				});
			}
	}
	/**
	 * 在线图片转换成base64字符串
	 *
	 * @param imgURL	图片线上路径
	 * @return
	 *
	 * @author ZHANGJL
	 * @dateTime 2018-02-23 14:43:18
	 */
	public static String ImageToBase64ByOnline(String imgURL) {
		ByteArrayOutputStream data = new ByteArrayOutputStream();
		try {
			// 创建URL
			URL url = new URL(imgURL);
			byte[] by = new byte[1024];
			// 创建链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000);
			InputStream is = conn.getInputStream();
			// 将内容读取内存中
			int len = -1;
			while ((len = is.read(by)) != -1) {
				data.write(by, 0, len);
			}
			// 关闭流
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data.toByteArray());
	}
	/**
	 * 随机指定范围内N个不重复的数
	 * 利用HashSet的特征，只能存放不同的值
	 * @param min 指定范围最小值
	 * @param max 指定范围最大值
	 * @param n 随机数个数
	 * @param set 随机数结果集
	 */
	public static void randomSet(int min, int max, int n, HashSet<Integer> set) {
		if (n > (max - min + 1) || max < min) {
			return;
		}
		for (int i = 0; i < n; i++) {
			// 调用Math.random()方法
			int num = (int) (Math.random() * (max - min)) + min;
			set.add(num);// 将不同的数存入HashSet中
		}
		int setSize = set.size();
		// 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
		if (setSize < n) {
			randomSet(min, max, n - setSize, set);// 递归
		}
	}

	public static List<String> base64ListImg(String img[]){
		List<String> ImgList= Lists.newArrayList();
		for (String str:img) {
			ImgList.add(ImageToBase64ByOnline(str));
		}
		return ImgList;
	}

	/*public static String[] PersonImg(){
		String headImagHostPreix = "http://39.108.54.252:8185/hotcomm-community/person/";
		String img[]={
				//普通人群/关爱人群
				headImagHostPreix+"20181227501150_aac54ed714094ab6846aa4171a317ceb"+".jpg",
				headImagHostPreix+"20181227511122_2d48af4f55e74019b42ae386acbbdbb8"+".jpg",
				headImagHostPreix+"20181227511142_8412cc7a9e3f4e20871b81e205b878de"+".jpg",
				headImagHostPreix+"20181227001246_120a1a628cd34f91b71753bf31fdc90a"+".jpg",
				headImagHostPreix+"20181227011205_d7b101a2726646abbb61d3d2422fd79e"+".jpg",
				headImagHostPreix+"20190102400315_d29ce63bc7c2482ab509ddd6a412ddfb"+".jpg",
				headImagHostPreix+"20190102400348_fcac9f024bee405db294af1656eb96d0"+".jpg",
				headImagHostPreix+"20190102410312_0e99b652067b4b66bd5e07e37ee1dbcc"+".jpg",
				headImagHostPreix+"20190102410338_7db11e26e2034c6ba3c60693084e78a5"+".jpg",
				headImagHostPreix+"20181227460140_9426e56a27ad4803973dc649f68089c8"+".jpg",
				headImagHostPreix+"20181227470116_18a814013d0b405b9c34673c9f8413aa"+".jpg",
				headImagHostPreix+"20181227470136_7e7c6573be14404e95e0f26342dcb243"+".jpg",
				headImagHostPreix+"20190102420312_8d6bc8bba40f4aabad1482c03f135888"+".jpg",
				headImagHostPreix+"20190102420343_2bde4b9fd6454f6ea82d51859eef321e"+".jpg",
				headImagHostPreix+"20181227470148_0f51f28d7d56490f8fab7342d33f8329"+".jpg",
				headImagHostPreix+"20181227480101_74c6ca35cb51442bba2262e3a8c77216"+".jpg",
				headImagHostPreix+"20190102430306_20cd999514f74a56bc170fb88014af30"+".jpg",
				headImagHostPreix+"20181227581114_c471c1946481419496a8eb66e0a5a748"+".jpg",
				headImagHostPreix+"20181227581126_c927dde03a0d41eea35f47ee39514a68"+".jpg",
				headImagHostPreix+"20181227581138_945b7cd95c794c3cbcecd9f2ef881b04"+".jpg",
				headImagHostPreix+"20181227470136_7e7c6573be14404e95e0f26342dcb243"+".jpg",
				headImagHostPreix+"20181227581151_185341f4f05043b4ae754e589297d604"+".jpg",
				headImagHostPreix+"20181227480126_c030aacbfb0f484b980f86588ddcb39e"+".jpg",
				headImagHostPreix+"20181227591120_47fed53837af410f91634f1748ec356a"+".jpg",
				headImagHostPreix+"20181227591135_200961fed1b04cb086c3d871c3dfa9a7"+".jpg",
				headImagHostPreix+"20181227591146_4a368c30ed7b43f88b62889c26191682"+".jpg",
				headImagHostPreix+"20181227001200_63eb6ad3444544c7a0af102b0d682f6e"+".jpg",
				headImagHostPreix+"20181227001217_b5c4407238784a86acb0f0153b1051c7"+".jpg",
				headImagHostPreix+"20181227591104_d45f6b6b25604785b21e0e9a5b6a82d0"+".jpg",
				headImagHostPreix+"20181227001232_9e13c15e306748b29f070f4842294284"+".jpg",
				//潜在危险人群
				headImagHostPreix+"20190107090441_8f1aa8a252384b95b3e5d2155605ec15"+".jpg",
				headImagHostPreix+"20190107130448_26d30c71673e484ea7a43f6f9f3eb8c2"+".jpg",
				headImagHostPreix+"20190107150433_34ee906236c347ec84b82657d45590f6"+".jpg",
				headImagHostPreix+"20190107180411_2724066ff8af403bb2f2f6a8d0d3844d"+".jpg",
				//黑名单
				headImagHostPreix+"20190107400349_8558e6c38c304c54932047ae41a67274"+".jpg",
				headImagHostPreix+"20190107480311_79de08bba4a941239bc2df71cf9aa842"+".jpg",
				headImagHostPreix+"20190107500349_d2dc42f549a4458f88e254945450aa56"+".jpg",
				headImagHostPreix+"20190107540324_b292fa9015294cf2bc3627dadc6ac72a"+".jpg",
				headImagHostPreix+"20190107560331_74a2acdf8d69451f82cfbc722664f792"+".jpg",
				//区域服务人员
				headImagHostPreix+"20190107580311_6f63e5640c48443084ab6938da0c4d08"+".jpg",
				headImagHostPreix+"20190107020408_c1702f3bdb78492fb23d130ab1a50a1f"+".jpg",
				headImagHostPreix+"20190107030427_9a1fce2130274f9886f774b2f46d2d0a"+".jpg",
				headImagHostPreix+"20190107040458_9196387666ca4a14affa4f003d4c3768"+".jpg",
				headImagHostPreix+"20190107060421_4d7ddd98af254868923b246021fb4f0c"+".jpg",

				//陌生人
				headImagHostPreix+"20190107310559_75cbdbdac65d4e44943a028f91ef88f1"+".jpg",
				headImagHostPreix+"20190107320517_d2ae7efc4acf4038946ad76d4532bed3"+".jpg",
				headImagHostPreix+"20190107320532_4edabb00805f4b00b3333959dab3c87e"+".jpg",
				headImagHostPreix+"20190107320545_f592e2bc22f4449499174bc629efabf7"+".jpg",
				headImagHostPreix+"20190107320557_b14bd5c03d2a4178bd799e6575f77791"+".jpg",
				headImagHostPreix+"20190107330511_d0024e3f409543c8924eed8ad1db3782"+".jpg",
				headImagHostPreix+"20190107330521_514db95623e24ce8b4ced994c0415533"+".jpg",
				headImagHostPreix+"20190107330537_d3c1cdca268946a2a67ccee34fc5a7a7"+".jpg",
				headImagHostPreix+"20190107330552_be261f4fdec44120a9ff145d05b00388"+".jpg",
				headImagHostPreix+"20190107310559_75cbdbdac65d4e44943a028f91ef88f1"+".jpg",
				headImagHostPreix+"20190107320517_d2ae7efc4acf4038946ad76d4532bed3"+".jpg",
				headImagHostPreix+"20190107320532_4edabb00805f4b00b3333959dab3c87e"+".jpg",
				headImagHostPreix+"20190107320545_f592e2bc22f4449499174bc629efabf7"+".jpg",
				headImagHostPreix+"20190107320557_b14bd5c03d2a4178bd799e6575f77791"+".jpg",
				headImagHostPreix+"20190107330511_d0024e3f409543c8924eed8ad1db3782"+".jpg",
				headImagHostPreix+"20190107330521_514db95623e24ce8b4ced994c0415533"+".jpg",
				headImagHostPreix+"20190107330537_d3c1cdca268946a2a67ccee34fc5a7a7"+".jpg",
				headImagHostPreix+"20190107330552_be261f4fdec44120a9ff145d05b00388"+".jpg",
				headImagHostPreix+"20190107310559_75cbdbdac65d4e44943a028f91ef88f1"+".jpg",
				headImagHostPreix+"20190107320517_d2ae7efc4acf4038946ad76d4532bed3"+".jpg",
				headImagHostPreix+"20190107320532_4edabb00805f4b00b3333959dab3c87e"+".jpg",
				headImagHostPreix+"20190107320545_f592e2bc22f4449499174bc629efabf7"+".jpg",
				headImagHostPreix+"20190107320557_b14bd5c03d2a4178bd799e6575f77791"+".jpg",
				headImagHostPreix+"20190107330511_d0024e3f409543c8924eed8ad1db3782"+".jpg",
				headImagHostPreix+"20190107330521_514db95623e24ce8b4ced994c0415533"+".jpg",
				headImagHostPreix+"20190107330537_d3c1cdca268946a2a67ccee34fc5a7a7"+".jpg",
				headImagHostPreix+"20190107330552_be261f4fdec44120a9ff145d05b00388"+".jpg",
				headImagHostPreix+"20190107310559_75cbdbdac65d4e44943a028f91ef88f1"+".jpg",
				headImagHostPreix+"20190107320517_d2ae7efc4acf4038946ad76d4532bed3"+".jpg",
				headImagHostPreix+"20190107320532_4edabb00805f4b00b3333959dab3c87e"+".jpg",
				headImagHostPreix+"20190107320545_f592e2bc22f4449499174bc629efabf7"+".jpg",
				headImagHostPreix+"20190107320557_b14bd5c03d2a4178bd799e6575f77791"+".jpg",
				headImagHostPreix+"20190107330511_d0024e3f409543c8924eed8ad1db3782"+".jpg",
				headImagHostPreix+"20190107330521_514db95623e24ce8b4ced994c0415533"+".jpg",
				headImagHostPreix+"20190107330537_d3c1cdca268946a2a67ccee34fc5a7a7"+".jpg",
				headImagHostPreix+"20190107330552_be261f4fdec44120a9ff145d05b00388"+".jpg",
				headImagHostPreix+"20190107310559_75cbdbdac65d4e44943a028f91ef88f1"+".jpg",
				headImagHostPreix+"20190107320517_d2ae7efc4acf4038946ad76d4532bed3"+".jpg",
				headImagHostPreix+"20190107320532_4edabb00805f4b00b3333959dab3c87e"+".jpg",
				headImagHostPreix+"20190107320545_f592e2bc22f4449499174bc629efabf7"+".jpg",
				headImagHostPreix+"20190107320557_b14bd5c03d2a4178bd799e6575f77791"+".jpg",
				headImagHostPreix+"20190107330511_d0024e3f409543c8924eed8ad1db3782"+".jpg",
				headImagHostPreix+"20190107330521_514db95623e24ce8b4ced994c0415533"+".jpg",
				headImagHostPreix+"20190107330537_d3c1cdca268946a2a67ccee34fc5a7a7"+".jpg",
				headImagHostPreix+"20190107330552_be261f4fdec44120a9ff145d05b00388"+".jpg",
				headImagHostPreix+"20190107310559_75cbdbdac65d4e44943a028f91ef88f1"+".jpg",
				headImagHostPreix+"20190107320517_d2ae7efc4acf4038946ad76d4532bed3"+".jpg",
				headImagHostPreix+"20190107320532_4edabb00805f4b00b3333959dab3c87e"+".jpg",
				headImagHostPreix+"20190107320545_f592e2bc22f4449499174bc629efabf7"+".jpg",
				headImagHostPreix+"20190107320557_b14bd5c03d2a4178bd799e6575f77791"+".jpg",
				headImagHostPreix+"20190107330511_d0024e3f409543c8924eed8ad1db3782"+".jpg",
				headImagHostPreix+"20190107330521_514db95623e24ce8b4ced994c0415533"+".jpg",
				headImagHostPreix+"20190107330537_d3c1cdca268946a2a67ccee34fc5a7a7"+".jpg",
				headImagHostPreix+"20190107330552_be261f4fdec44120a9ff145d05b00388"+".jpg"
		};

		return img;
	}*/

	public static String[] PersonImg(){
		String headImagHostPreix = "http://39.108.54.252:8185/hotcomm-community/common/";
		String personHeadImagHostPreix = "http://39.108.54.252:8185/hotcomm-community/person/";
		String img[]={
				//普通人群/关爱人群
				headImagHostPreix+"20190108260314_cb665e5a9f9b4b5491e2b1ad5b65b052.png",
				headImagHostPreix+"20190108300303_c3cc49da1dba49179f6613f84ba2d67f.png",
				headImagHostPreix+"20190108310329_ae9719d54f064f6f9000487008fd28cb.png",
				headImagHostPreix+"20190108330339_b9f45e51ff3b4813a5b30e0634afae56.png",
				headImagHostPreix+"20190108340344_a3a1589670414d4f92d96f22b5a9c2d7.png",
				headImagHostPreix+"20190108350356_cf3c9491e11f42a290bd7197e22fb6fc.png",
				headImagHostPreix+"20190108370317_09e01c536fdf4450a65a5415b75a343a.png",
				headImagHostPreix+"20190108380311_13da9cd45dd14ca782f71cbf0df3d6bd.png",
				headImagHostPreix+"20190108390314_3345e9d23c1345e7b02f7b98098709fc.png",
				headImagHostPreix+"20190108400345_c838eb20c17042c78cb988fa7c532104.png",
				headImagHostPreix+"20190108460336_047e2acc7c774bd48dd9501aae330a57.png",
				headImagHostPreix+"20190108470343_aacfdadee16a4e94a2274b3c3c8bfb87.png",
				headImagHostPreix+"20190108480328_3045cf89bf834a6bb1c7e77cf23934f2.png",
				headImagHostPreix+"20190108240459_7444e5624e774493937ea52ccb3a4acb.png",
				headImagHostPreix+"20190108250439_6270ab2726784ef8b29ac91dc7db8b0d.png",
				headImagHostPreix+"20190108260414_c4db6c157ff44155bd82d73abcccd890.png",
				headImagHostPreix+"20190108260449_9f241b1a4dfa44e6857f175ee79e9c82.png",
				headImagHostPreix+"20190108270426_2fbebbe64d1a4cbda06ffa48ef7c9b5c.png",
				headImagHostPreix+"20190108280403_a0d819e4426149b38906b94a58ccf1ab.png",
				headImagHostPreix+"20190108280437_2c9b3d0c5da44ae2a59642662a48aad6.png",
				headImagHostPreix+"20190108290418_82732d1afd3841e0b01f1b697c65d320.png",
				headImagHostPreix+"20190108290452_945ac778c42b4527969138ec55829b36.png",
				headImagHostPreix+"20190108310429_03429c0371074655b380123a877dbed4.png",
				headImagHostPreix+"20190108340429_ed2e0c106f724ffb8cadedc3c0718604.png",
				headImagHostPreix+"20190109090946_5c28e6163e9e4257a6d51cc10d05f919.png",
				headImagHostPreix+"20190109140933_038964ab875d4cea992d91d6150f9228.png",
				headImagHostPreix+"20190109190950_f9c2faa49c8c4dd1a359c9beca9582dd.png",
				headImagHostPreix+"20190109240935_7135051966fc410f9355527944fa2575.png",
				headImagHostPreix+"20190109270905_f54c2e63ac1c4fc68820af25baf8dbef.png",
				headImagHostPreix+"20190109280949_ed51472f228f46cc8d94738dbc0b68d0.png",
				headImagHostPreix+"20190109310921_ee9940bf63624f058680e322437154d3.png",
				headImagHostPreix+"20190109320917_41a570fd61e5469e97b6fe4fed9fd636.png",
				headImagHostPreix+"20190109350924_676bde6bc6654c24a8acb0d1585390df.png",
				headImagHostPreix+"20190109360956_33c7ff99fe654519b032212a04deb83e.png",
				headImagHostPreix+"20190109390919_7d552cbf2ee8437a909d46aa0c46da24.png",
				headImagHostPreix+"20190109450953_2f839e078e9b4726af5362e74bd29cd8.png",
				headImagHostPreix+"20181227501150_aac54ed714094ab6846aa4171a317ceb"+".jpg",
				headImagHostPreix+"20181227511122_2d48af4f55e74019b42ae386acbbdbb8"+".jpg",
				headImagHostPreix+"20181227511142_8412cc7a9e3f4e20871b81e205b878de"+".jpg",
				headImagHostPreix+"20181227001246_120a1a628cd34f91b71753bf31fdc90a"+".jpg",
				headImagHostPreix+"20181227011205_d7b101a2726646abbb61d3d2422fd79e"+".jpg"
		};

		return img;
	}

}
