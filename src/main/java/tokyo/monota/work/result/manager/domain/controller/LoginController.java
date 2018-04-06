package tokyo.monota.work.result.manager.domain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import tokyo.monota.work.result.manager.domain.form.LoginForm;

@Controller
@RequestMapping("common/login")
public class LoginController {

	@ModelAttribute
	public LoginForm initForm() {
		return new LoginForm();
	}

	@GetMapping
	public String index() {
		return "common/login";
	}
}
