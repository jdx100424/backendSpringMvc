package com.maoshen.base.disconf;

import org.springframework.context.annotation.Configuration;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import com.baidu.disconf.client.common.update.IDisconfUpdate;

@Configuration
@DisconfFile(filename = "base.properties")
public class BaseDisconf implements IDisconfUpdate {
	private String projectUrl;

	@DisconfFileItem(name = "project.url", associateField = "projectUrl")
	public String getProjectUrl() {
		return projectUrl;
	}

	public void setProjectUrl(String projectUrl) {
		this.projectUrl = projectUrl;
	}

	@Override
	public void reload() throws Exception {
		// TODO Auto-generated method stub

	}
}
