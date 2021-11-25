package com.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler
	public String processError(Exception ex, Model model) {
		
		ex.printStackTrace();
		String msgError = ex.getMessage();
		model.addAttribute("msgError", msgError);
		return "error";
	}
}
