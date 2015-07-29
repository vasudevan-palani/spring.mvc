package com.vasu.learning.spring.mvc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private final String ADD_USER = "INSERT into users(username,password) values (:username,:password)";
	
	SimpleJdbcTemplate jdbcTemplate;
	
	public SimpleJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Autowired
	@Required
	public void setJdbcTemplate(SimpleJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@RequestMapping(value="/home")
	public String home(Map<String,Object> model){
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "vasu");
		map.put("password", "password");
		
		this.jdbcTemplate.update(ADD_USER, map);
		
		return "home";
	}
}