package com.hotcomm.community.api.test.add;

import com.hotcomm.community.common.bean.db.person.HoleObjDM;
import com.hotcomm.community.common.bean.db.person.PersonHoleDetailDM;
import com.hotcomm.community.common.bean.db.person.PersonIDAndNoDM;
import com.hotcomm.community.common.bean.db.person.PushDM;
import com.hotcomm.community.common.bean.pa.house.PersonRoomPA;
import com.hotcomm.community.common.bean.pa.person.AddPersonHolePA;
import com.hotcomm.community.common.bean.pa.person.AddPersonPA;
import com.hotcomm.community.common.service.person.PersonHoleService;
import com.hotcomm.community.common.service.person.PersonLableService;
import com.hotcomm.community.common.service.person.PersonRecordService;
import com.hotcomm.community.common.service.person.PersonService;
import com.hotcomm.community.resful.CommunityRunner;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import com.hotcomm.framework.web.exception.HKException;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;


//@Transactional
//@Rollback(false)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {CommunityRunner.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class PersonServiceTest extends CommunityTest {

	@Autowired
	private PersonService personService;
	@Autowired
	private PersonLableService lableService;
	@Autowired
	private PersonHoleService personHoleService;
	@Autowired
	private PersonRecordService personRecordService;

	private final Integer communityId = 1;

	/*	*/

	/**
	 * 添加30条居民记录 其中10个关爱人口
	 *//*
	@Test
	public void addPerson() {
		String headImagHostPreix = "http://39.108.54.252:8185/hotcomm-community/person/";
		List<AddPersonPA> list = Lists.newArrayList();
		//普通人口/关爱人群
		list.add(new AddPersonPA(headImagHostPreix+"20181227501150_aac54ed714094ab6846aa4171a317ceb.jpg", "100001", "张一", "13240557701", 1, "54252719800705031X", 38, 1, 0, "西藏自治区阿里地区措勤县", 0, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227511122_2d48af4f55e74019b42ae386acbbdbb8.jpg", "100002", "李二", "13240557702", 1, "310119198603235779", 32, 1, 0, "上海市市辖区", 0, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227511142_8412cc7a9e3f4e20871b81e205b878de.jpg", "100003", "王三", "13240557703", 1, "130625198909257074", 29, 1, 0, "河北省保定市徐水县", 1, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227001246_120a1a628cd34f91b71753bf31fdc90a.jpg", "100004", "吴梅", "13240557704", 1, "211122197610172383", 42, 2, 0, "辽宁省盘锦市盘山县", 1, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227011205_d7b101a2726646abbb61d3d2422fd79e.jpg", "100005", "陈秀", "13240557705", 1, "640104198304293604", 35, 2, 0, "宁夏回族自治区银川市", 0, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190102400315_d29ce63bc7c2482ab509ddd6a412ddfb.jpg", "100006", "周留", "13240557706", 1, "410425197911304396", 39, 1, 1, "河南省平顶山市郏县", 1, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190102400348_fcac9f024bee405db294af1656eb96d0.jpg", "100007", "冯器", "13240557707", 1, "130400199102154871", 27, 1, 1, "河北省邯郸市邯郸市", 1, "中国", "汉族", "本科", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190102410312_0e99b652067b4b66bd5e07e37ee1dbcc.jpg", "100008", "秦坝", "13240557708", 1, "420205198402194352", 34, 1, 1, "湖北省黄石市铁山区", 1, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190102410338_7db11e26e2034c6ba3c60693084e78a5.jpg", "100009", "曹鸠", "13240557709", 1, "652100197908089616", 39, 1, 1, "新疆维吾尔自治区吐鲁番地区吐鲁番地区", 0, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227460140_9426e56a27ad4803973dc649f68089c8.jpg", "100010", "花诗", "13240557710", 1, "420984199108306382", 27, 2, 0, "湖北省孝感市汉川市", 0, "中国", "汉族", "本科", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227470116_18a814013d0b405b9c34673c9f8413aa.jpg", "100011", "郝适宜", "13240557711", 1, "230321199305167824", 25, 2, 1, "黑龙江省鸡西市鸡东县", 0, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227470136_7e7c6573be14404e95e0f26342dcb243.jpg", "100012", "苗诗儿", "13240557712", 1, "370786198802194707", 30, 2, 1, "山东省潍坊市昌邑市", 0, "中国", "苗族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190102420312_8d6bc8bba40f4aabad1482c03f135888.jpg", "100013", "严石散", "13240557713", 1, "150900198408048939", 34, 1, 1, "内蒙古自治区", 0, "中国", "蒙古族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190102420343_2bde4b9fd6454f6ea82d51859eef321e.jpg", "100014", "郑士思", "13240557714", 1, "441622197503045936", 43, 1, 0, "广东省河源市龙川县", 1, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190107320545_f592e2bc22f4449499174bc629efabf7.jpg", "100015", "凤诗舞", "13240557715", 1, "430482199110224441", 27, 2, 1, "湖南省衡阳市常宁市", 1, "中国", "汉族", "本科", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227480101_74c6ca35cb51442bba2262e3a8c77216.jpg", "100016", "吕始柳", "13240557716", 1, "220105199109141122", 27, 2, 1, "吉林省长春市二道区", 1, "中国", "汉族", "本科", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190102430306_20cd999514f74a56bc170fb88014af30.jpg", "100017", "顾侍齐", "13240557717", 1, "440705198204213197", 36, 1, 0, "广东省江门市", 0, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227581114_c471c1946481419496a8eb66e0a5a748.jpg", "100018", "刘氏霸", "13240557718", 1, "37068119881204019X", 30, 1, 1, "山东省烟台市龙口市", 1, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227581126_c927dde03a0d41eea35f47ee39514a68.jpg", "100019", "丁拾玖", "13240557719", 1, "341300198201031893", 36, 1, 1, "安徽省宿州市宿州市", 1, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227581138_945b7cd95c794c3cbcecd9f2ef881b04.jpg", "100020", "宋児石", "13240557720", 1, "211401199112059090", 27, 1, 1, "辽宁省葫芦岛市市辖区", 0, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227470136_7e7c6573be14404e95e0f26342dcb243.jpg", "100021", "叶兒依", "13240557721", 1, "33018519930902282X", 25, 2, 1, "浙江省杭州市临安市", 0, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227581151_185341f4f05043b4ae754e589297d604.jpg", "100022", "金赫赫", "13240557722", 1, "623022197609132897", 42, 1, 1, "甘肃省甘南藏族自治州卓尼县", 0, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227480126_c030aacbfb0f484b980f86588ddcb39e.jpg", "100024", "童娥思", "13240557724", 1, "450204199009255167", 28, 2, 1, "广西壮族自治区柳州市柳南区", 0, "中国", "汉族", "本科", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227591120_47fed53837af410f91634f1748ec356a.jpg", "100025", "项尔武", "13240557725", 1, "450481197612182999", 42, 1, 1, "广西壮族自治区梧州市岑溪市", 0, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227591135_200961fed1b04cb086c3d871c3dfa9a7.jpg", "100026", "季二柳", "13240557726", 1, "321283198801249214", 30, 1, 1, "江苏省泰州市泰兴市", 0, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227591146_4a368c30ed7b43f88b62889c26191682.jpg", "100027", "邵迩琪", "13240557727", 1, "431028198602052339", 32, 1, 0, "湖南省郴州市安仁县", 0, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227001200_63eb6ad3444544c7a0af102b0d682f6e.jpg", "100028", "邱鹅魃", "13240557728", 1, "320706198005068432", 38, 1, 1, "江苏省连云港市海州区", 0, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190107330521_514db95623e24ce8b4ced994c0415533.jpg", "100029", "蔡厄揪", "13240557729", 1, "510114199210297354", 26, 1, 1, "四川省成都市", 0, "中国", "汉族", "本科", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227591104_d45f6b6b25604785b21e0e9a5b6a82d0.jpg", "100023", "汪尔散", "13240557723", 1, "37131219860827541X", 32, 1, 1, "山东省临沂市河东区", 0, "中国", "汉族", "本科", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20181227001232_9e13c15e306748b29f070f4842294284.jpg", "100030", "徐俕式", "13240557730", 1, "130402199209118750", 26, 1, 1, "河北省邯郸市邯山区", 0, "中国", "汉族", "本科", "群众", "未婚", 1, 1));
		//黑名单
		list.add(new AddPersonPA(headImagHostPreix+"20190107400349_8558e6c38c304c54932047ae41a67274.jpg", null, "程三义", null, 1, "441900199003075191", 29, 1, 0, "广东省东莞市", 4, "中国", "汉族", null, "群众", "未婚", 2, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190107480311_79de08bba4a941239bc2df71cf9aa842.jpg", null, "卢散逸", null, 1, "340102199003073510", 29, 1, 0, "安徽省合肥市瑶海区", 4, "中国", "汉族", null, "群众", "未婚", 2, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190107500349_d2dc42f549a4458f88e254945450aa56.jpg", null, "欧散俕", null, 1, "410702199003075453", 29, 1, 0, "河南省新乡市红旗区", 4, "中国", "汉族", null, "群众", "未婚", 2, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190107540324_b292fa9015294cf2bc3627dadc6ac72a.jpg", null, "司三思", null, 1, "330102199003078539", 29, 1, 0, "浙江省杭州市上城区", 4, "中国", "汉族", null, "群众", "未婚", 2, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190107560331_74a2acdf8d69451f82cfbc722664f792.jpg", null, "徐三巫", null, 1, "330102199003078379", 29, 1, 0, "浙江省杭州市上城区", 4, "中国", "汉族", null, "群众", "未婚", 2, 1));

		//区域服务人员
		list.add(new AddPersonPA(headImagHostPreix+"20190107580311_6f63e5640c48443084ab6938da0c4d08.jpg", null, "白叁骝", "13240557736", 1, "430102199003071912", 29, 1, 0, "湖南省长沙市芙蓉区", 3, "中国", "汉族", "本科", "群众", "未婚", 2, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190107020408_c1702f3bdb78492fb23d130ab1a50a1f.jpg", null, "赖三栖", "13240557737", 1, "430102199003077513", 29, 1, 0, "湖南省长沙市芙蓉区", 3, "中国", "汉族", "本科", "群众", "未婚", 2, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190107310559_75cbdbdac65d4e44943a028f91ef88f1.jpg", null, "冉散魃", "13240557738", 1, "430102199003071592", 29, 2, 0, "湖南省长沙市芙蓉区", 3, "中国", "汉族", "本科", "群众", "未婚", 2, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190107330511_d0024e3f409543c8924eed8ad1db3782.jpg", null, "牧三九", "13240557739", 1, "430102199003075198", 29, 1, 0, "湖南省长沙市芙蓉区", 3, "中国", "汉族", "本科", "群众", "未婚", 2, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190107060421_4d7ddd98af254868923b246021fb4f0c.jpg", null, "昌斯士", "13240557740", 1, "430102199003079818", 29, 1, 0, "湖南省长沙市芙蓉区", 3, "中国", "汉族", "本科", "群众", "未婚", 2, 1));
		//潜在危险人群
		list.add(new AddPersonPA(headImagHostPreix+"20190107090441_8f1aa8a252384b95b3e5d2155605ec15.jpg", "100041", "乐思议", "13240557741", 1, "520102199003074023", 29, 2, 1, "贵州省贵州市南明区", 2, "中国", "汉族", "本科", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190107130448_26d30c71673e484ea7a43f6f9f3eb8c2.jpg", "100042", "顾斯尔", "13240557742", 1, "44010319900307437X", 29, 1, 1, "广东省广州市荔湾区", 2, "中国", "汉族", "本科", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190107150433_34ee906236c347ec84b82657d45590f6.jpg", "100043", "雷思伞", "13240557743", 1, "440103199003072040", 29, 2, 1, "广东省广州市荔湾区", 2, "中国", "汉族", "本科", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix+"20190107180411_2724066ff8af403bb2f2f6a8d0d3844d.jpg", "100044", "谈司司", "13240557744", 1, "440103199003070221", 29, 2, 1, "广东省广州市荔湾区", 2, "中国", "汉族", "本科", "群众", "未婚", 1, 1));

		Integer result = this.addPersonMethods(list);
		System.out.println("居民添加成功:" + result + "条");
	}*/
	@Test
	public void addPerson() {
		String headImagHostPreix = "http://39.108.54.252:8185/hotcomm-community/common/";
		List<AddPersonPA> list = Lists.newArrayList();
		list.add(new AddPersonPA(headImagHostPreix + "20190108260314_cb665e5a9f9b4b5491e2b1ad5b65b052.png", "000001", "黄磊", "13712370550", 1, "420624199109285816", 27, 1, 1, "湖北省南漳县肖堰镇响水洞村一组", 1, "中国", "汉族", "大学", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108300303_c3cc49da1dba49179f6613f84ba2d67f.png", "000002", "钟瑶", "15002052051", 1, "42108119920706561X", 26, 1, 0, "湖北省石首市大垸乡黄木山村3组13号", 2, "中国", "汉族", "大学", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108310329_ae9719d54f064f6f9000487008fd28cb.png", "000003", "刘行", "15112654993", 1, "430621198908230035", 29, 1, 0, "广东省深圳市福田区福强路益田村110栋006H", 6, "中国", "汉族", "大学", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108330339_b9f45e51ff3b4813a5b30e0634afae56.png", "000004", "郭振华", "13826963622", 1, "441900197210215872", 46, 1, 1, "广东省东莞市桥头镇凯达华庭7座802号", 2, "中国", "汉族", "大学", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108340344_a3a1589670414d4f92d96f22b5a9c2d7.png", "000005", "吴艳", "18771037120", 1, "42108119920426228X", 26, 2, 0, "湖北省石首市新厂镇上闸村二组2-3", 1, "中国", "汉族", "大学", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108350356_cf3c9491e11f42a290bd7197e22fb6fc.png", "000006", "李成龙", "13242065122", 1, "411081199005122055", 28, 1, 0, "河南省禹州市梁北镇箕阿村６组", 4, "中国", "汉族", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108370317_09e01c536fdf4450a65a5415b75a343a.png", "000007", "李荣城", "13728261911", 1, "441424198212040533", 36, 1, 1, "广东省五华县河东镇增塘村建仁楼", 3, "中国", "汉族", "大学", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108380311_13da9cd45dd14ca782f71cbf0df3d6bd.png", "000008", "邹巧", "18926590381", 1, "362229198409170026", 34, 2, 1, "广州市天河区天河北路888号", 1, "中国", "汉族", "大学", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108390314_3345e9d23c1345e7b02f7b98098709fc.png", "000009", "邹剑锋", "13726253702", 1, "441624199201290815", 26, 1, 0, "广东省和平县合水镇丰岭村委会柑新村40号", 1, "中国", "汉族", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190111230239_9eadb3a7102c4d2f903f5278abf04b05.jpg", "000010", "夏强", "15179610561", 1, "362425199711180030", 21, 1, 0, "江西省吉安市永丰县恩江镇塘仔角路3号1单元403室", 1, "中国", "汉族", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108460336_047e2acc7c774bd48dd9501aae330a57.png", "000011", "李佳", "13510822102", 1, "429004198309070735", 35, 1, 1, "广东省深圳市南山区汕头街8号怡康楼", 6, "中国", "汉族", "大学", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108470343_aacfdadee16a4e94a2274b3c3c8bfb87.png", "000012", "姚文虎", "15361443061", 1, "622223198302286112", 35, 1, 0, "甘肃省民乐县杨坊乡上花园村三组", 3, "中国", "汉族", "大学", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108480328_3045cf89bf834a6bb1c7e77cf23934f2.png", "000013", "袁远星", "13826915718", 1, "360731199606130391", 22, 1, 1, "广东省东莞市石碣镇石碣上一村三巷35号", 1, "中国", "汉族", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108240459_7444e5624e774493937ea52ccb3a4acb.png", "000014", "李铠滔", "13546947009", 1, "440921199612248677", 22, 1, 1, "广东省信宜市兴中路20号", 4, "中国", "汉族", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108250439_6270ab2726784ef8b29ac91dc7db8b0d.png", "000015", "庄锦坤", "15625510600", 1, "445281199801081098", 21, 1, 1, "广东省普宁市燎原街道果陇村新兴里18号", 4, "中国", "汉族", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108260414_c4db6c157ff44155bd82d73abcccd890.png", "000016", "林圣", "15811880485", 1, "360722198404244218", 34, 1, 0, "江西省赣州市信丰县铁石口镇信丰县煤矿", 2, "中国", "汉族", "大学", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108260449_9f241b1a4dfa44e6857f175ee79e9c82.png", "000017", "封智超", "13527950456", 1, "441900199610060717", 22, 1, 1, "广东省东莞市南城区石鼓龙眼园街西二巷4号", 4, "中国", "汉族", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108270426_2fbebbe64d1a4cbda06ffa48ef7c9b5c.png", "000018", "刘锐杭", "13111112211", 1, "44148119970415167X", 21, 1, 1, "广东省兴宁市罗岗镇德丰村水口20号", 3, "中国", "汉族", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108280403_a0d819e4426149b38906b94a58ccf1ab.png", "000019", "何伟恒", "13537399263", 1, "441900199605184555", 22, 1, 1, "广东省东莞市大岭山镇杨屋草塘村七队46号", 1, "中国", "汉族", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108280437_2c9b3d0c5da44ae2a59642662a48aad6.png", "000020", "李旺炽", "13559774360", 1, "440825199310133735", 25, 1, 1, "广东省徐闻县西连镇乐琴下村282号", 1, "中国", "汉族", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108290418_82732d1afd3841e0b01f1b697c65d320.png", "000021", "林锡坚", "13790355109", 1, "44088219940906399X", 24, 1, 1, "广东省雷州市南兴镇高朗村84号", 3, "中国", "汉族", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108290452_945ac778c42b4527969138ec55829b36.png", "000022", "陈淑怡", "15217379536", 1, "441900199703053583", 21, 2, 1, "广东省东莞市厚街镇桥头塘面塘面路十一巷8号", 1, "中国", "汉族", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108310429_03429c0371074655b380123a877dbed4.png", "000023", "袁伟冰", "13712574826", 1, "441900199310014129", 25, 2, 1, "广东省东莞市寮步镇下圩16号", 6, "中国", "汉族", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190108340429_ed2e0c106f724ffb8cadedc3c0718604.png", "000024", "郭培鹏", "13111111111", 1, "440582199711086811", 22, 1, 1, "广东省汕头市潮阳区西胪镇外輋永安区四横巷1号", 4, "中国", "汉族", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190109090946_5c28e6163e9e4257a6d51cc10d05f919.png", "000025", "杨济民", "15766633043", 1, "440825199705314856", 21, 1, 1, "广东省湛江市市辖区人民大道中24号", 3, "中国", "汉", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190109140933_038964ab875d4cea992d91d6150f9228.png", "000026", "余斌", "13728152564", 1, "430725198310124167", 35, 2, 0, "湖南省常德市鼎城区十美堂镇农科站", 4, "中国", "汉", "大学", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190109190950_f9c2faa49c8c4dd1a359c9beca9582dd.png", "000027", "温志威", "13532133798", 1, "441381199507222111", 23, 1, 1, "广东省惠州市惠阳区镇隆镇岭下31号", 2, "中国", "汉", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190109240935_7135051966fc410f9355527944fa2575.png", "000028", "罗炳旦", "13267894024", 1, "440881199410223536", 24, 1, 1, "广东省廉江市安铺镇龙沟村85号105房", 4, "中国", "汉", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190109270905_f54c2e63ac1c4fc68820af25baf8dbef.png", "000029", "陈慧琳", "15622980706", 1, "445381199602201726", 22, 2, 1, "广东省罗定市罗平镇罗周围村委上高楼52号", 1, "中国", "汉", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190109280949_ed51472f228f46cc8d94738dbc0b68d0.png", "000030", "朱泓宇", "18872732050", 1, "421102199108200919", 27, 1, 0, "湖北省黄冈市黄州区涵晖路56号", 2, "中国", "汉", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190109310921_ee9940bf63624f058680e322437154d3.png", "000031", "杨旭涛", "18665958610", 1, "450881199008094117", 28, 1, 1, "广东省深圳市福田区福华一路6号免税商务大厦1403", 3, "中国", "汉", "大学", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190109320917_41a570fd61e5469e97b6fe4fed9fd636.png", "000032", "许志强", "18938944046", 1, "441581198810305979", 30, 1, 1, "广东省深圳市南山区高新南四道10号", 2, "中国", "汉", "大学", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190109350924_676bde6bc6654c24a8acb0d1585390df.png", "000033", "黄鸿林", "13559773854", 1, "445281199403261333", 24, 1, 1, "广东省普宁市占陇镇龙秋村楼脚片195号", 4, "中国", "汉", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190109360956_33c7ff99fe654519b032212a04deb83e.png", "000034", "何欢", "13649241497", 1, "610122199309055420", 25, 2, 0, "西安市蓝田县灞源乡西河寨村第四村民小组36号", 1, "中国", "汉", "大学", "群众", "未婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190109390919_7d552cbf2ee8437a909d46aa0c46da24.png", "000035", "马力", "13510945980", 1, "430902198707074555", 31, 1, 0, "湖南省益阳市资阳区长春镇茅岗坪村大垸子村民组5号", 2, "中国", "汉", "大学", "群众", "已婚", 1, 1));
		list.add(new AddPersonPA(headImagHostPreix + "20190109450953_2f839e078e9b4726af5362e74bd29cd8.png", "000036", "尹淑平", "13712108515", 1, "441900199204154144", 26, 2, 1, "广东省东莞市寮步镇西溪珠岗村332号", 4, "中国", "汉", "大学", "群众", "未婚", 1, 1));
		Integer result = this.addPersonMethods(list);
		System.out.println("居民添加成功:" + result + "条");
	}

	@Test
	/**
	 * 添加5个人口标签
	 */
	public void addPersonLable() {
		lableService.insertPersonLable(communityId, 1, 1, "关爱小孩", "小于12岁的小孩");
		lableService.insertPersonLable(communityId, 1, 2, "危险人员", "精神人员");
		lableService.insertPersonLable(communityId, 1, 3, "外卖人员", "周边送外卖的人员");
		lableService.insertPersonLable(communityId, 1, 4, "黑名单", "公安黑名单人员");
		lableService.insertPersonLable(communityId, 1, 0, "普通人", "普通人");
		lableService.updatePersonLableId(communityId, 5, 0);
		lableService.insertPersonLable(communityId, 1, 1, "关爱残疾人", "残疾人士");
	}

	/**
	 * 添加布控 规则1
	 * 关爱小孩
	 */
	@Test
	public void addPerosnHole1() {
		AddPersonHolePA holePA = new AddPersonHolePA();
		holePA.setCommunityId(1);
		holePA.setHoleType(1);

		HoleObjDM objDM = new HoleObjDM();
		List<Integer> age = Lists.newArrayList();
		age.add(1);
		age.add(12);
		objDM.setAge(age);
		objDM.setLable_id(1);
		holePA.setObj(objDM);

		//设置布控规则1
		PersonHoleDetailDM holeDetail = new PersonHoleDetailDM();
		holeDetail.setBegintime("2018-01-01");
		holeDetail.setEndtime("2028-01-08");
		holeDetail.setNoReturn(48);
		holeDetail.setWay(1);
		List<PersonHoleDetailDM> holedetailList = Lists.newArrayList();
		holedetailList.add(holeDetail);

		holePA.setHoleDetail(holedetailList);
		holePA.setHoleTitle("关爱小孩48小时未归布控");
		holePA.setAlarmLv(1);
		holePA.setCreateUser(1);

		PushDM pushDM = new PushDM();
		pushDM.setP_id(2);
		pushDM.setP_name("李二");
		pushDM.setMessage("关爱小孩48小时未归,快速速联系");
		List<PushDM> list = Lists.newArrayList();
		list.add(pushDM);
		holePA.setPushobj(list);
		personHoleService.addPersonHole(holePA);
	}

	/**
	 * 布控规则2
	 * 关爱老人布控
	 */
	@Test
	public void addPerosnHole2() {
		AddPersonHolePA holePA = new AddPersonHolePA();
		holePA.setCommunityId(1);
		holePA.setHoleType(1);

		HoleObjDM objDM = new HoleObjDM();
		List<Integer> age = Lists.newArrayList();
		age.add(65);
		age.add(100);
		objDM.setAge(age);
		objDM.setLable_id(1);
		holePA.setObj(objDM);

		//设置布控规则1
		PersonHoleDetailDM holeDetail = new PersonHoleDetailDM();
		holeDetail.setBegintime("2018-01-01");
		holeDetail.setEndtime("2028-01-08");
		holeDetail.setNoReturn(48);
		holeDetail.setWay(1);
		List<PersonHoleDetailDM> holedetailList = Lists.newArrayList();
		holedetailList.add(holeDetail);

		holePA.setHoleDetail(holedetailList);
		holePA.setHoleTitle("关爱老人48小时未归布控");

		holePA.setAlarmLv(1);
		holePA.setCreateUser(1);

		PushDM pushDM = new PushDM();
		pushDM.setP_id(2);
		pushDM.setP_name("李二");
		pushDM.setMessage("关爱老人48小时未归,快速速联系");
		List<PushDM> list = Lists.newArrayList();
		list.add(pushDM);
		holePA.setPushobj(list);
		personHoleService.addPersonHole(holePA);
	}

	/**
	 * 布控规则3
	 * 黑名单布控
	 */
	@Test
	public void addPerosnHole3() {
		AddPersonHolePA holePA = new AddPersonHolePA();
		holePA.setCommunityId(1);
		holePA.setHoleType(1);


		HoleObjDM objDM = new HoleObjDM();
		objDM.setLable_id(4);
		holePA.setObj(objDM);

		//设置布控规则1
		PersonHoleDetailDM holeDetail = new PersonHoleDetailDM();
		holeDetail.setBegintime("2018-01-01");
		holeDetail.setEndtime("2028-01-08");
		holeDetail.setDays(1);
		holeDetail.setNums(1);
		List<PersonHoleDetailDM> holedetailList = Lists.newArrayList();
		holedetailList.add(holeDetail);

		holePA.setHoleDetail(holedetailList);
		holePA.setHoleTitle("黑名单人员出没布控");

		holePA.setAlarmLv(1);
		holePA.setCreateUser(1);

		PushDM pushDM = new PushDM();
		pushDM.setP_id(2);
		pushDM.setP_name("李二");
		pushDM.setMessage("黑名单人员出没");
		List<PushDM> list = Lists.newArrayList();
		list.add(pushDM);
		holePA.setPushobj(list);
		personHoleService.addPersonHole(holePA);
	}

	/**
	 * 20条通行记录
	 */
	@Test
	public void addRecord() {
		Map<String, String> map = new HashMap<>();
		map.put("1", "http://39.108.54.252:8185/hotcomm-community/common/20190108260314_cb665e5a9f9b4b5491e2b1ad5b65b052.png");
		map.put("2", "http://39.108.54.252:8185/hotcomm-community/common/20190108290418_82732d1afd3841e0b01f1b697c65d320.png");
		map.put("3", "http://39.108.54.252:8185/hotcomm-community/common/20190108260414_c4db6c157ff44155bd82d73abcccd890.png");
		Random rand = new Random();
		Date dt = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dt);
		calendar.add(calendar.DATE, -1);//把日期往后增加一天.整数往后推,负数往前移动
		dt = calendar.getTime(); //这个时间就是日期往后推一天的结果
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		HashSet<Integer> set = new HashSet<Integer>();
		randomSet(0, 24, 24, set);//生成20个不重复的随机数
		Integer resultNum = 0;
		for (int i : set) {//生成几个随机数就循环几次
			Integer key = rand.nextInt(map.size()) + 1;
			String Time = sdf.format(dt) + " " + i + ":30:00";
			String beforeTime = sdf.format(dt) + " " + i + ":29:30";
			String afterTime = sdf.format(dt) + " " + i + ":30:30";
			resultNum += personRecordService.addPersonRecord(1, key.toString(), Time, map.get(key.toString()), "3M05329PAKED676," + beforeTime + "," + afterTime, 1, "3M05329PAKED676");
		}
		System.out.println("成功执行:" + resultNum + "条");
	}

	/**
	 * 添加人房关联
	 */
	@Test
	public void addPersonRoomRe() {
		List<PersonIDAndNoDM> personIDAndNo = personService.getPersonIDAndNo(communityId, null, null, null, -2, null, null, null);
		Integer resultNum = 0;
		for (PersonIDAndNoDM dm : personIDAndNo) {
			PersonRoomPA pa = new PersonRoomPA();
			pa.setPId(dm.getPId());
			pa.setRoomId(dm.getPId());
			pa.setRentOrSale(2);
			pa.setReason(31);
			pa.setRemark("户主");
			pa.setCommunityId(communityId);
			resultNum += personService.addPersonReRoom(pa);
		}
		System.out.println("成功执行:" + resultNum + "条");
	}


	public Integer addPersonMethods(List<AddPersonPA> lists) throws HKException {
		Integer addPersonMessage = 0;
		try {
			for (AddPersonPA list : lists) {
				list.setCommunityId(communityId);
				addPersonMessage += personService.addPersonMessage(list);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return addPersonMessage;
	}


	/**
	 * 随机指定范围内N个不重复的数
	 * 利用HashSet的特征，只能存放不同的值
	 *
	 * @param min 指定范围最小值
	 * @param max 指定范围最大值
	 * @param n   随机数个数
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

	/*public static void main(String[] args) {
		File file = new File("E:/aaa.txt");
		String a[]=txt2String(file).split(";");
		List<Map<String,String>> result= Lists.newArrayList();

		for (int i=0;i<a.length;i++) {
			Map<String,String> map=new HashMap<>();
			String b[]=a[i].split("VALUES");
			System.out.println(b.length);
			System.out.println(b[0].trim());
			System.out.println(b[1].trim());
			String c1[]=b[0].split(",");
			String c2[]=b[1].split(",");
			for (int x=0;x<c1.length;x++) {
				map.put(c1[x],c2[x]);
			}
			result.add(map);
		}
		System.out.println(result.toString());
		System.out.println("*************************************************");
		System.out.println();
		System.out.println("**************************************************");
		for (Map<String,String> map:result) {
			System.out.println("list.add(new AddPersonPA(\""+map.get("headImg")+"\", \""+map.get("entrance_cardno")+"\", \""+map.get("name")+"\", \""+map.get("phone")+"\","+map.get("card_type")+", \""+map.get("card_no")+"\","+map.get("age")+", "+map.get("sex")+", "+map.get("koseki")+", \""+map.get("account_address")+"\", "+map.get("lable_id")+", \""+map.get("nationality")+"\", \""+map.get("people")+"\", \""+map.get("degree")+"\", \""+map.get("political_outlook")+"\", \""+map.get("marital_status")+"\", "+map.get("data_type")+", "+map.get("create_user")+"));");
		}
	}

	public static String txt2String(File file){
		StringBuilder result = new StringBuilder();
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
			String s = null;
			while((s = br.readLine())!=null){//使用readLine方法，一次读一行
				result.append(System.lineSeparator()+s);
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result.toString();
	}*/
}