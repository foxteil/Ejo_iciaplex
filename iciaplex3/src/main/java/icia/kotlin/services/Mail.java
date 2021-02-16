package icia.kotlin.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Mailbean;

@Service
public class Mail {
	@Autowired
	private JavaMailSenderImpl sendMail;
	ModelAndView mav;
	
//	public Mail() {}
	
	public ModelAndView entrance(Mailbean sendMail) {
		mav = this.sendTextMail(sendMail);
		return mav;
	}

	private ModelAndView sendTextMail(Mailbean sendMail) {
//		System.out.println(sendMail.getMailReceiver());
//		System.out.println(sendMail.getMailSubject());
//		System.out.println(sendMail.getMailContent());
		
		mav = new ModelAndView();
		         try {
//		            SimpleMailMessage simpleMail = new SimpleMailMessage();
//		            simpleMail.setFrom("britlove1246@naver.com");
//		            simpleMail.setTo(sendMail.getMailReceiver());
//		            simpleMail.setSubject(sendMail.getMailSubject());
//		            simpleMail.setText(sendMail.getMailContent());
//		            this.sendMail.send(simpleMail);
		            
		        	 MimeMessage mailMessage = this.sendMail.createMimeMessage();
		        	 MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "UTF-8");
		        	 
		        	 helper.setTo(sendMail.getMailReceiver());
		        	 helper.setSubject(sendMail.getMailSubject());
		        	 helper.setText(sendMail.getMailContent());
		        	 helper.setFrom("britlove1246@naver.com");
		        	 
		        	this.sendMail.send(mailMessage); 
		            mav.addObject("receiver", sendMail.getMailReceiver());
		            mav.setViewName("sendResult");

		         }catch(Exception e ) {
		            e.printStackTrace();
		            mav.setViewName("mail");
		            
		         }
		      return mav;
	}
	
	 
		
	
}
