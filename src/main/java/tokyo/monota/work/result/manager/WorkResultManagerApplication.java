package tokyo.monota.work.result.manager;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WorkResultManagerApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(WorkResultManagerApplication.class).bannerMode(Banner.Mode.OFF).build().run(args);
	}
}
