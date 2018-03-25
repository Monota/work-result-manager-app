package tokyo.monota.work.result.manager.domain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

	@RequestMapping
	public String error() {
		return "/common/error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
