package com.passtoss.myhome.task;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.passtoss.myhome.domain.MailVO;

@Component
public class SendMail {	

	@Autowired
	private JavaMailSenderImpl mailSender;
	
	private static final Logger logger = LoggerFactory.getLogger(SendMail.class);

	public void sendMail(MailVO vo) {
		
		MimeMessagePreparator mp = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				helper.setFrom(vo.getFrom());
				helper.setTo(vo.getTo());
				helper.setSubject(vo.getSubject());
				helper.setText(vo.getContent(), true);
			}

		};// new MimeMessagePreparator()

		mailSender.send(mp);
		logger.info("메일 전송");
	}// sendMail(MailVO vo)
}// class SendMail

