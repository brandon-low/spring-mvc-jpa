package hello.test.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class MyAppContextInit  implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext ac) {
		System.out.println("Init Context");
		String os = System.getProperty("os.name");
		String profile = (os.toLowerCase().startsWith("windows")) ? "windows" : "other";
		ConfigurableEnvironment ce = ac.getEnvironment();
		ce.addActiveProfile(profile);
		//ac.register(SpringAnnotationConfig.class);
	}
}