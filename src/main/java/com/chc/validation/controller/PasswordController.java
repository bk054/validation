package com.chc.validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chc.validation.service.PasswordService;

@RestController
public class PasswordController {
	@Autowired
	PasswordService passwordService;
	@RequestMapping(value = "/validatePassword", method = RequestMethod.GET)
	@ResponseBody boolean validatePassword(@RequestParam(value = "password", required = true) String password) {
		return passwordService.isPasswordValid(password);
	}
}
