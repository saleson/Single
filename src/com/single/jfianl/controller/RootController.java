package com.single.jfianl.controller;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class RootController extends Controller {

	@ActionKey("/")
	public void execute() {
		render("login.html");
	}
	
	@ActionKey("fds")
	public void fds() {
		getRequest().setAttribute("name", "saleson");
		render("test.ftl");
	}
}
