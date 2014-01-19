package com.single.jfianl.controller;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.Restful;

public class LoginController extends Controller {

	public void login() {
		HttpServletRequest request = this.getRequest();
		render("../index.jsp");
	}
	
}
