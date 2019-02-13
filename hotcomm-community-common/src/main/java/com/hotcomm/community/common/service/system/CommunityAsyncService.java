package com.hotcomm.community.common.service.system;

import java.io.IOException;
import java.sql.SQLException;

public interface CommunityAsyncService {

	/**
	 * 动态创建社区业务数据库
	 * @param databaseNo
	 * @throws IOException
	 * @throws SQLException
	 */
	public void dynamicGeneralDatabase(String databaseNo) throws IOException, SQLException;

}
