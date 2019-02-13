package com.hotcomm.community.system.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.hotcomm.community.common.service.system.CommunityAsyncService;
import com.hotcomm.framework.utils.SpringUtil;

@Service
public class CommunityAsyncServiceImpl implements CommunityAsyncService {

	@Override
	@Async
	public void dynamicGeneralDatabase(String databaseNo) throws IOException, SQLException {
		Environment env = SpringUtil.getBean(Environment.class);
		String scriptFile = env.getProperty("db_scripts", String.class);
		BufferedReader reader = new BufferedReader(new InputStreamReader(CommunityServiceImpl.class.getClassLoader().getResourceAsStream("community.sql")));
		String scriptTempPath = env.getProperty("db_script_temp_file", String.class);
		String tempScriptFIlePath = scriptTempPath + "temp_" + scriptFile;
		String line = null;
		StringBuffer content = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			content.append(line).append("\r\n");
		}
		String newContent = content.toString().replace("${databaseno}", databaseNo);
		FileCopyUtils.copy(newContent.getBytes(), new File(tempScriptFIlePath));
		BufferedReader newreader = new BufferedReader(new FileReader(tempScriptFIlePath));
		Connection conn = SpringUtil.getBean(DataSource.class).getConnection();
		ScriptRunner runner = new ScriptRunner(conn);
		Resources.setCharset(Charset.forName("UTF-8"));
		// runner.setLogWriter(null); // 设置是否输出日志
		runner.runScript(newreader);
		runner.closeConnection();
		conn.close();
		reader.close();
		newreader.close();
	}

}
