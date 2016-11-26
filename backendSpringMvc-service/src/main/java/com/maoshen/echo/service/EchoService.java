package com.maoshen.echo.service;

import java.util.List;

import com.maoshen.component.db.DataSource;
import com.maoshen.echo.domain.Echo;
import com.maoshen.echo.dto.EchoDto;

public interface EchoService {
	/**
	 * 默认现实的行数
	 */
	public static final int DEFAULT_COUNT = 10;
	/**
	 * 获取ECHO数据
	 * 
	 * @param echoDto
	 * @return
	 */
	public List<Echo> getList(EchoDto echoDto);
	public List<Echo> getList();
	public int getCount(EchoDto echoDto);
	public int getCount();
	/**
	 * dubbo健康检测
	 * 
	 * @return
	 */
	public boolean checkDubbo(Long id);

	/**
	 * 从库健康检测查询
	 * 
	 * @param id
	 * @return
	 */
	@DataSource("slave")
	public boolean checkEchoIsExist(Long id);

	/**
	 * 主库健康检测插入
	 * 
	 * @param echo
	 * @throws Exception
	 */
	@DataSource("master")
	public void insert(Echo echo) throws Exception;

	/**
	 * 缓存健康检测
	 * 
	 * @return
	 */
	public boolean checkRedis() throws Exception;
}
