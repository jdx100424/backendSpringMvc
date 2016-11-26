package com.maoshen.echo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.maoshen.echo.domain.Echo;
import com.maoshen.echo.dto.EchoDto;

@Repository
public interface EchoDao {
	public Echo selectById(@Param("id") Long id);

	public void insert(Echo echo);

	public List<Echo> select(EchoDto echoDto);

	public int selectCount(EchoDto echoDto);
}
