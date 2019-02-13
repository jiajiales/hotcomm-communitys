package com.hotcomm.community.resful.controller.console.system;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotcomm.community.common.bean.pa.system.UserLoginPA;
import com.hotcomm.community.common.bean.ui.system.CommunityDetailListUM;
import com.hotcomm.community.common.bean.ui.system.LoginSuccessUM;
import com.hotcomm.community.common.bean.ui.system.ResourceGradingUM;
import com.hotcomm.community.common.service.system.CommunityService;
import com.hotcomm.community.common.service.system.ResourceService;
import com.hotcomm.community.common.service.system.UserService;
import com.hotcomm.community.resful.comm.RestfulUrlRecord;
import com.hotcomm.framework.annotation.LogEvent;
import com.hotcomm.framework.annotation.LogSkip;
import com.hotcomm.framework.annotation.Param;
import com.hotcomm.framework.annotation.ParamsValidate;
import com.hotcomm.framework.comm.LoginUser;
import com.hotcomm.framework.utils.RedisHelper;
import com.hotcomm.framework.utils.SpringUtil;
import com.hotcomm.framework.utils.UUIDUtils;
import com.hotcomm.framework.utils.VerifyCodeUtils;
import com.hotcomm.framework.web.exception.HKException;
import com.hotcomm.framework.web.result.ApiResult;

@RestController
public class LoginController {

	Logger log = LoggerFactory.getLogger("infoLogger");

	@Autowired
	UserService userService;

	@Autowired
	ResourceService resourceService;

	@Autowired
	CommunityService communityService;

	@Autowired
	RedisHelper redisHelper;

	@LogEvent(code = "LG00101", userIng = false)
	@RequestMapping(value = { RestfulUrlRecord.LOGIN }, method = { RequestMethod.POST })
	@ParamsValidate(validateParams = { @Param(key = "userName", code = "LOGIN_F01"), 
			   						   @Param(key = "password", code = "LOGIN_F02"),
			   						   @Param(key = "code", code = "LOGIN_F03"),
			   						   @Param(key = "uuid", code = "LOGIN_F04") })
	@LogSkip
	public ApiResult login(UserLoginPA userLoginPA) throws HKException, Exception {
		if (!userLoginPA.getCode().toUpperCase().equals("TEST")) { // 为方便测试,临时设置的通用验证码
			String oldCode = redisHelper.get(userLoginPA.getUuid());

			if (oldCode == null)
				return ApiResult.error("-1", "验证码已失效");

			if (!(oldCode.toUpperCase()).equals(userLoginPA.getCode().toUpperCase())) // 验证码不区分大小写
				return ApiResult.error("-1", "验证码不正确");

			redisHelper.del(userLoginPA.getUuid());
		}

		if (!userService.isUserValid(userLoginPA.getUserName()))
			return ApiResult.error("-1", "用户无效");

		if (!userService.exists(userLoginPA))
			return ApiResult.error("-1", "账号或密码错误");

		String token = UUIDUtils.get32BitUUID();
		log.info("登入成功-返回令牌:{}", token);

		ObjectMapper mapper = new ObjectMapper();
		RedisHelper redisHelper = SpringUtil.getBean(RedisHelper.class);
		LoginUser loginUser = userService.getUser(userLoginPA.getUserName(), userLoginPA.getPassword());
		Integer userId = loginUser.getUserId();
		List<ResourceGradingUM> resourceGrading = resourceService.getGradingResources(userId);

		List<CommunityDetailListUM> communityList;

		if (loginUser.getRoleCode().equals("F01"))
			communityList = communityService.queryListCommunityDetailAll();
		else
			communityList = communityService.queryListCommunityDetail(userId);

		LoginSuccessUM loginSuccessUM = new LoginSuccessUM(token, userId, loginUser.getRealName(),loginUser.getUserName(),resourceGrading, communityList);
		
		redisHelper.set(token, mapper.writeValueAsString(loginUser), 60 * 60 * 24);
		return ApiResult.success(loginSuccessUM);
	}

	@RequestMapping(value = { RestfulUrlRecord.GET_VALIDATE_CODE }, method = { RequestMethod.GET })
	public ApiResult getValidateCode(HttpServletResponse response, HttpServletRequest request) throws HKException, IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("image/jpeg");
		Map<String, String> map = new HashMap<>();
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 生成uuid
		String uuid = UUID.randomUUID().toString();
		map.put("uuid", uuid);
		// 存入缓存 300秒过期
		redisHelper.set(uuid, verifyCode, 300);
        //生成图片
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(100, 40, out, verifyCode);
        byte[] array = out.toByteArray();
        String imgString = Base64.encodeBase64String(array);
        imgString = "data:image/JPEG;base64," + imgString;
		// 获取通过base64加密后图形码字符串
		map.put("img", imgString);
		return ApiResult.success(map);
	}

}
