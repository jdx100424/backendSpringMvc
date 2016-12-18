package com.maoshen.echo.dto;

import java.io.Serializable;

import com.maoshen.component.base.dto.BaseDto;

@SuppressWarnings("serial")
public class EchoDto extends BaseDto implements Serializable {
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
