package com.passtoss.myhome.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.passtoss.myhome.domain.Company;
import com.passtoss.myhome.domain.MailAuth;
import com.passtoss.myhome.domain.Member;
import com.passtoss.myhome.mybatis.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	private MemberMapper dao;

	@Autowired
	public MemberServiceImpl(MemberMapper dao) {
		this.dao = dao;
	}

	@Override
	public int isId(String id) {
		Member member = dao.isId(id);
		return (member == null) ? -1 : 1;
	}

	@Override
	public int mailAuth(Map<String, String> map) {
		MailAuth mailauth = dao.isMailAuth(map);
		if (mailauth == null) {
			return dao.insertMailAuth(map);
		} else {
			return dao.updateMailAuth(map);
		}
	}

	@Override
	public int mailCertify(Map<String, String> map) {
		MailAuth mailauth = dao.isMailAuth(map);
		String originalNum = mailauth.getAuthNum();
		Date sendTime = mailauth.getSendTime();
		Date now = new Date();

		long sendTimel = sendTime.getTime() / 1000;
		long nowl = now.getTime() / 1000;
		int result = 0;

		logger.info("인증번호 전송 시간 : " + sendTime);
		logger.info("인증번호 입력시간 : " + now);
		logger.info("====================================");
		logger.info("전송시간 변환 : " + sendTimel);
		logger.info("입력시간 변환 : " + nowl);

		if ((nowl - sendTimel) < 180) {
			if (!originalNum.equals(map.get("authNum"))) {
				logger.info("인증번호 일치하지 않음");
			} else {
				logger.info("인증번호 일치함");
				result = 1;
			}
		} else {
			logger.info("인증시간 지남");
			result = -1;
		}
		return result;
	}

	@Override
	public Member memberInfo(String id) {
		return dao.isId(id);
	}

	@Override
	public int checkURL(String url) {
		Company c = dao.checkURL(url);
		return (c == null) ? 0 : 1;
	}

	@Override
	public Company isCompany(String url) {
		return dao.checkURL(url);		
	}

	@Override
	@Transactional
	public int createCompany(Company c, Member m) {
		int result = dao.createCompany(c);
		logger.info("회사 테이블 결과 : " + result);
		if (result == 1) {
			result = dao.insertUser(m);
			logger.info("멤버 테이블 결과 : " + result);
		}

		return result;
	}

	@Override
	public Map<String, Integer> joinCompany(Member m) {
		logger.info("insert 전 belong 값 : "+m.getAccess_option());
		Map<String, Integer> map=new HashMap<String, Integer>();
		int result = dao.insertUser(m);
		logger.info("insert 후 belong 값 : "+m.getAccess_option());
		map.put("result", result);
		map.put("belong", m.getAccess_option());
		return map;
	}

	@Override
	public int resetPassword(String id, String password) {
		return dao.resetPassword(id,password);
	}

	@Override
	public List<Map<String, Object>> getSearchMemberList(String searchword,List<String> persons) {
		return dao.getSearchMemberList(searchword, persons);
	}

	@Override
	public int updateProfile(Member m) {
		if(m.getUpdateType().equals("profileImg")) {
			logger.info("프로필 이미지 변경");
		}else if(m.getUpdateType().equals("member")) {
			logger.info("내 정보 변경");
		}
		return dao.updateProfile(m);
	}

	@Override
	public List<Object> getCompanyAllID(int companyId) {
		return dao.getCompanyAllID(companyId);
	}

}
