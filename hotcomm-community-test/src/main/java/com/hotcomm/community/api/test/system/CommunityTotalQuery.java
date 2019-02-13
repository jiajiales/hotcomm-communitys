package com.hotcomm.community.api.test.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotcomm.community.common.bean.ui.system.CommunityDetailListUM;
import com.hotcomm.community.common.bean.ui.system.LoginSuccessUM;
import com.hotcomm.framework.comm.LoginUser;
import com.hotcomm.framework.utils.http.EntityEnum;
import com.hotcomm.framework.utils.http.HotHttpEntity;
import com.hotcomm.framework.utils.http.HotHttpResponse;
import com.hotcomm.framework.utils.http.HttpClientUtils;
import com.hotcomm.framework.web.result.ApiResult;
import jdk.nashorn.internal.runtime.options.Option;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Slf4j
public class CommunityTotalQuery {

    final static String host = "http://112.74.51.248:8281/wisdom/";
    final static String loginUrl = "system/login";
    final static String validateCode = "system/getValidateCode";


    static {
        log.info("请求后台接口服务请求地址:{}", host);
    }

    public static void main(String[] args) throws IOException {
        String redis_host = "47.105.156.159";
        Integer redis_port = 6379;
        String redis_password = "hotcomm123";
        Jedis jedis = new Jedis(redis_host, redis_port);
        jedis.auth(redis_password);
        jedis.select(15);
        log.info("获取验证码");
        String result = HttpClientUtils.doGet(host + validateCode);
        Map<String, Object> ks = (Map<String, Object>) JSON.parse(result);
        if (!ks.get("code").equals("0")) return;
        ks = (Map<String, Object>) JSON.parse(ks.get("data").toString());
        String imgStr = ks.get("img").toString();
        String uuidCode = ks.get("uuid").toString();
        String validateCode = jedis.get(uuidCode);
        log.info("登入验证码：{}", validateCode);

        log.info("执行登入");
        List<HotHttpEntity> loginMap = new ArrayList<>();
        loginMap.add(new HotHttpEntity("userName",EntityEnum.TEXT,"admin"));
        loginMap.add(new HotHttpEntity("password",EntityEnum.TEXT,"123456"));
        loginMap.add(new HotHttpEntity("code",EntityEnum.TEXT,validateCode));
        loginMap.add(new HotHttpEntity("uuid",EntityEnum.TEXT,uuidCode));
        //System.out.printf(host+loginUrl);
        HotHttpResponse  response = HttpClientUtils.post(loginMap,host+loginUrl);
        if(!response.isSuccess()) return ;
        String loginResult = response.getReturnJson();
        JSONObject jso=JSON.parseObject(loginResult);//json字符串转换成jsonobject对象
        LoginSuccessUM user = JSON.parseObject(jso.get("data").toString(),LoginSuccessUM.class);

        log.info("登入 TOKEN:{}",user.getToken());

        Optional<CommunityDetailListUM> optional =  user.getCommunityDetailList().stream().filter((e)->e.getIsDefault()==2).findFirst();
        CommunityDetailListUM community =  optional.get();
        Integer cid = community.getCid();
        log.info("选择社区编号:{},社区名称:{}",cid,community.getCname());
    }

}
