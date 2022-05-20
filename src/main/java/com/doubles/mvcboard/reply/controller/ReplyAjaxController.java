package com.doubles.mvcboard.reply.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reply")
public class ReplyAjaxController {

	@RequestMapping("/test")
	public String replyAjaxTest() {
		return "/tutorial/reply_test";
	}

}
