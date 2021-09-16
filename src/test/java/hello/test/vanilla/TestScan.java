package hello.test.vanilla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@Configuration
@EnableAutoConfiguration
@ComponentScan
//same as
//@SpringBootApplication
public class TestScan {
	private static ApplicationContext applicationContext;

	public class Example {
		
	}
    @Bean
    public Example example() {
        return new Example();
    }

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(TestScan.class, args);
        checkBeansPresence(
          "cat", "dog", "rose", "example", "testScan");

    }

    private static void checkBeansPresence(String... beans) {
        for (String beanName : beans) {
            System.out.println("Is " + beanName + " in ApplicationContext: " + 
              applicationContext.containsBean(beanName));
        }
    }

}
