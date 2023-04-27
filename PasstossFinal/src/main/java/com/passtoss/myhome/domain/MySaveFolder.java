package com.passtoss.myhome.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my")
public class MySaveFolder {

	private String savefolder;
	private String profileFolder;
	private String logoFolder;

	public String getSavefolder() {
		return savefolder;
	}

	public void setSavefolder(String savefolder) {
		this.savefolder = savefolder;
	}

	public String getProfileFolder() {
		return profileFolder;
	}

	public void setProfileFolder(String profileFolder) {
		this.profileFolder = profileFolder;
	}

	public String getLogoFolder() {
		return logoFolder;
	}

	public void setLogoFolder(String logoFolder) {
		this.logoFolder = logoFolder;
	}

}
