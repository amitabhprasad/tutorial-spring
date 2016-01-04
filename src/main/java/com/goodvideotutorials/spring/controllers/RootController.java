package com.goodvideotutorials.spring.controllers;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.goodvideotutorials.spring.dto.SignupForm;
import com.goodvideotutorials.spring.mail.MailSender;
/**
 * Root controller
 * @author amitabh
 *
 */
//@RestController
@Controller
public class RootController {

	@Resource
	private MailSender mailSender;
	private Logger logger =  LoggerFactory.getLogger(RootController.class);;
	
	
	/*@Resource
	public void setMockMailSender(MailSender mockMailSender) {
		this.mockMailSender = mockMailSender;
	}*/
	
	/*@Autowired
	public RootController(@Qualifier("mock") MailSender mailSender){
		this.mockMailSender = mailSender;
	}*/
	/*@RequestMapping("/")
	public String home(){
		try {
			mailSender.send("prasadamitabh@gmail.com", "Test Mail", "Content of the mail, Hello World !!!");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "home";
	}*/
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String signup(Model model){
		//
		System.out.println("Called signup **************");
		model.addAttribute("signupForm", new SignupForm());
		return "signup";
	}
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public String signup(@ModelAttribute("signupForm") @Valid SignupForm signupForm,
			BindingResult result){
		//
		if(result.hasErrors()){
			return "signup";
		}
		logger.info(signupForm.getEmail());
		//redirect ...
		return "redirect:/";
	}
}
