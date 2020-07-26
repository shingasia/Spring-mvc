package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AjaxTestController {
	
	@GetMapping("/rest")
	public String restTest() {
		return "sample/rest";
	}
	@GetMapping("/rest2")
	public String restText2() {
		return "sample/rest2";
	}
	
}
