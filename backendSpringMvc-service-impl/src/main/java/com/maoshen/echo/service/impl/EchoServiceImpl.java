package com.maoshen.echo.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maoshen.component.redis.RedisService;
import com.maoshen.echo.domain.Echo;
import com.maoshen.echo.dto.EchoDto;
import com.maoshen.echo.dubbo.EchoDubbo;
import com.maoshen.echo.service.EchoService;

@Service("echoServiceImpl")
public class EchoServiceImpl implements EchoService {
	@Autowired
	private com.maoshen.echo.dao.EchoDao echoDao;

	@Autowired
	private RedisService redisService;

	@Autowired
	@Qualifier("echoDubboImpl")
	private EchoDubbo echoDubbo;

	private static final Logger LOGGER = Logger.getLogger(EchoServiceImpl.class);

	@Override
	public boolean checkEchoIsExist(Long id) {
		Echo echo = echoDao.selectById(id);
		boolean result = false;
		if (echo != null) {
			result = true;
		}
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insert(Echo echo) throws Exception {
		try {
			echoDao.insert(echo);
		} catch (Exception e) {
			LOGGER.error("EchoServiceImpl_insert fail", e);
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean checkRedis() throws Exception {
		try {
			String compareStr = "true";
			String randomKey = UUID.randomUUID().toString();
			redisService.insertByValue(randomKey, compareStr, 10, TimeUnit.SECONDS);
			Object result = redisService.getByValue(randomKey);
			if (compareStr.equals(result)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			LOGGER.error("EchoServiceImpl_checkRedis fail", e);
			throw e;
		}
	}

	@Override
	public boolean checkDubbo(Long id) {
		return echoDubbo.checkEchoIsExistByDubbo(id);
	}

	@Override
	public List<Echo> getList(EchoDto echoDto) {
		if(echoDto == null){
			echoDto = new EchoDto();
		}
		
		if(echoDto.getCount() == null || echoDto.getPage() < 1){
			echoDto.setCount(EchoService.DEFAULT_COUNT);
		}
		if(echoDto.getPage() == null || echoDto.getPage() < 1){
			echoDto.setPage(1);
		}else{
			echoDto.setPage((echoDto.getPage() - 1) * echoDto.getCount());
		}
		
		List<Echo> list = echoDao.select(echoDto);
		return list;
	}

	@Override
	public int getCount(EchoDto echoDto) {
		if(echoDto == null){
			echoDto = new EchoDto();
		}
		
		int count = echoDao.selectCount(echoDto);
		return count;
	}

	@Override
	public List<Echo> getList() {
		return getList(new EchoDto());
	}
	
	@Override
	public int getCount() {
		return getCount(new EchoDto());
	}
}
