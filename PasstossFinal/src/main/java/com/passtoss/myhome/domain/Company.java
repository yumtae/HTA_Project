package com.passtoss.myhome.domain;

import org.springframework.web.multipart.MultipartFile;

public class Company {
	private int company_id;
	private String company_name;
	private String ceo_id;
	private String url;
	private String type;
	private MultipartFile logoFile;
	private String logo;	
	private int access_option = 0;
	private String create_date;

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCeo_id() {
		return ceo_id;
	}

	public void setCeo_id(String ceo_id) {
		this.ceo_id = ceo_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MultipartFile getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(MultipartFile logoFile) {
		this.logoFile = logoFile;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getAccess_option() {
		return access_option;
	}

	public void setAccess_option(int access_option) {
		this.access_option = access_option;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

}
