package com.passtoss.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.passtoss.myhome.domain.Company;
import com.passtoss.myhome.domain.Member;

public class CustomUserDetails implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetails.class);

	//회원정보
	private String id; // 아이디
	private String password; // 비밀번호
	private String name; // 유저 이름
	private String dept;// 부서
	private String position;// 직책
	private String phone;// 연락처
	private String profile_img; // 프로필
	private int emailVerified; // 이메일 인증 여부
	
	//회사 정보
	private int companyId; // 회사 아이디
	private String companyName;// 회사 이름
	private String url; // 회사 url
	private String logo; // 회사 로고
	private int accessOption; // 직원 참여 옵션
	
	private boolean nonLocked = true; // 계정 잠김 여부
	private Collection<SimpleGrantedAuthority> authorities; // 권한 목록

	public CustomUserDetails(Member m, Company c) {
		this.id = m.getId();
		this.password = m.getPassword();
		this.name = m.getUsername();
		this.dept = m.getDept();
		this.position = m.getPosition();
		this.phone = m.getPhone();
		this.profile_img = m.getProfile_img();
		this.companyId = m.getCompany_id();
		this.companyName = c.getCompany_name();
		this.url = c.getUrl();
		this.logo=c.getLogo();
		this.emailVerified = m.getAuth_status();
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(m.getAuth()));
		this.authorities = roles;
	}

	// 로그인한 유저의 권한 목록
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	// 비밀번호
	@Override
	public String getPassword() {
		return password;
	}

	// 아이디
	@Override
	public String getUsername() {
		return id;
	}

	// 계정 만료여부
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정 잠김 여부
	@Override
	public boolean isAccountNonLocked() {
		return nonLocked;
	}

	// 비밀번호 만료 여부
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 활성화 여부
	@Override
	public boolean isEnabled() {

		System.out.println(emailVerified);
		System.out.println(nonLocked);
		emailVerified = 1; // 일단 1로 무조건 허용 해둠

		logger.info("이메일 인증여부 : " + emailVerified);
		logger.info("계정 잠김 여부 : " + nonLocked);

		return (emailVerified == 1 && nonLocked);
	}

		public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDept() {
		return dept;
	}

	public String getPosition() {
		return position;
	}

	public String getProfile_img() {
		return profile_img;
	}

	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}

	// 계정 이름
	public String getName() {
		return name;
	}
	
	public int getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getAccessOption() {
		return accessOption;
	}

	public void setAccessOption(int accessOption) {
		this.accessOption = accessOption;
	}
	
}
