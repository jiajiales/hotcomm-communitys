package com.hotcomm.community.api.test.query;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hotcomm.community.common.bean.ui.system.CommunityDetailListUM;
import com.hotcomm.community.common.bean.ui.system.LoginSuccessUM;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HotHttpResponse;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Slf4j
public class CommunityTest {

    protected final static String host = "http://112.74.51.248:8281/wisdom/";
    protected static Integer communityId;
    protected static String token;
    private final static String REDIS_HOST = "47.105.156.159";
    private final static Integer REDIS_PORT = 6379;
    private final static Integer REDIS_DATABASE_INDEX = 15;
    private final static String REDIS_PASSWORD = "hotcomm123";

    @BeforeClass
    public static void loginToToken() throws Exception {
        String loginUrl = "system/login";
        String validateCodeURL = "system/getValidateCode";
        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
        jedis.auth(REDIS_PASSWORD);
        jedis.select(REDIS_DATABASE_INDEX);
        log.info("获取验证码");
        String result = HttpClientUtils.doGet(host + validateCodeURL);
        Map<String, Object> ks = (Map<String, Object>) JSON.parse(result);
        if (!ks.get("code").equals("0")) return;
        ks = (Map<String, Object>) JSON.parse(ks.get("data").toString());
        String imgStr = ks.get("img").toString();
        String uuidCode = ks.get("uuid").toString();
        String validateCode = jedis.get(uuidCode);
        log.info("登入验证码：{}", validateCode);

        log.info("执行登入");
        List<HotHttpEntity> loginMap = new ArrayList<>();
        loginMap.add(new HotHttpEntity("userName", EntityEnum.TEXT, "admin"));
        loginMap.add(new HotHttpEntity("password", EntityEnum.TEXT, "123456"));
        loginMap.add(new HotHttpEntity("code", EntityEnum.TEXT, validateCode));
        loginMap.add(new HotHttpEntity("uuid", EntityEnum.TEXT, uuidCode));
        //System.out.printf(host+loginUrl);
        HotHttpResponse response = HttpClientUtils.post(loginMap, host + loginUrl);
        if (!response.isSuccess()) return;
        String loginResult = response.getReturnJson();
        JSONObject jso = JSON.parseObject(loginResult);//json字符串转换成jsonobject对象
        LoginSuccessUM user = JSON.parseObject(jso.get("data").toString(), LoginSuccessUM.class);

        log.info("登入 TOKEN:{}", user.getToken());

        Optional<CommunityDetailListUM> optional = user.getCommunityDetailList().stream().filter((e) -> e.getIsDefault() == 2).findFirst();
        CommunityDetailListUM community = optional.get();
        communityId = community.getCid();
        log.info("选择社区编号:{},社区名称:{}", communityId, community.getCname());
        token = user.getToken();
    }

}
