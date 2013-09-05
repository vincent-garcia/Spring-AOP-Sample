package com.credera.vgarcia;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * This method is public and called from outside the class, and can therefore be intercepted.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		// Method calls internal to the class cannot be intercepted because they do not go through the proxy
		makeInternalCall();
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	
	/**
	 * This method is only called internally, therefore it doesn't go through the proxy and is not intercepted.
	 */
	public void makeInternalCall() {
		logger.info("Doing something internally...");
	}
	
	
	/**
	 * This method is private, therefore cannot be intercepted.
	 */
	@RequestMapping(value = "shh", method = RequestMethod.GET)
	private String privatePage() {
		logger.info("Doing something private...");
		return "private";
	}	
}
