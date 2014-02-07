package com.single.jfianl.controller;

import com.jfinal.core.Controller;

public class LoginController extends Controller {

	public void login() {
		//HttpServletRequest request = this.getRequest();
		render("../index.jsp");
	}
	
}
