package com.pratyaksh.Springboot.myfirstwebapp.login;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class welcomecontroller {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String gotowelcome(ModelMap model) {
		model.put("name",  getusername());
		return "welcome";
	}

	private String getusername() {
		 org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

}
