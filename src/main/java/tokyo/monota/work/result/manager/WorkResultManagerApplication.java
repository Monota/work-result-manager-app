package tokyo.monota.work.result.manager;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

import tokyo.monota.work.result.manager.core.social.GoogleAutoConfigurationLocal;

@SpringBootApplication
@Import(GoogleAutoConfigurationLocal.class)
public class WorkResultManagerApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(WorkResultManagerApplication.class).bannerMode(Banner.Mode.OFF).build().run(args);
	}
}
