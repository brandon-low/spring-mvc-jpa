package hello.sample.test.basic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@SpringBootApplication
public class TestingWebApplication {

	private static final Logger logger = LoggerFactory.getLogger(TestingWebApplication.class);
	
	private ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		 SpringApplication.run(TestingWebApplication.class, args);
	}
	
	   //1. Creating SpringResourceTemplateResolver
	   @Bean
	   @Description("Thymeleaf Template Resolver")
	   public SpringResourceTemplateResolver springTemplateResolver(){	   
		   logger.debug("****************Init springTemplateResolver *************");
		    
		   //return null;
		    // SpringResourceTemplateResolver automatically integrates with Spring's own
		    // resource resolution infrastructure, which is highly recommended.
	       SpringResourceTemplateResolver springTemplateResolver = new SpringResourceTemplateResolver();
	       springTemplateResolver.setApplicationContext(this.applicationContext);
	     //  springTemplateResolver.setPrefix("classpath:templates/");
	      // springTemplateResolver.setSuffix(".html");
	      // springTemplateResolver.setTemplateMode(TemplateMode.HTML);
	       
	       // Template cache is true by default. Set to false if you want
	       // templates to be automatically updated when modified.
	       springTemplateResolver.setCacheable(true);
	       
	       return springTemplateResolver;
	   }
	   
	  
	   //2. Creating SpringTemplateEngine
	   @Bean
	   @Description("Thymeleaf Template Engine")
	   public SpringTemplateEngine springTemplateEngine(){		
		  
		   logger.debug("****************Init springTemplateEngine *************");
		   return null;
		  // // SpringTemplateEngine automatically applies SpringStandardDialect and
		    // enables Spring's own MessageSource message resolution mechanisms.
	     //  SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
	       //springTemplateEngine.setTemplateResolver(springTemplateResolver());
	       //springTemplateEngine.setTemplateEngineMessageSource(messageSource());
	       
	       // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
	       // speed up execution in most scenarios, but might be incompatible
	       // with specific cases when expressions in one template are reused
	       // across different data types, so this flag is "false" by default
	       // for safer backwards compatibility.
	      // springTemplateEngine.setEnableSpringELCompiler(true);
	     //  return springTemplateEngine;
	   }
	   
	   
	   //3. Registering ThymeleafViewResolver
	   @Bean
	   @Description("Thymeleaf View Resolver")
	   public ViewResolver viewResolver(){ 
		   logger.debug("****************Init viewResolver *************");
		   return new InternalResourceViewResolver();
		   /*
	       ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	       viewResolver.setTemplateEngine(springTemplateEngine());
	       viewResolver.setOrder(1);
	      // viewResolver.setViewNames(new String[] {".html", ".xhtml"});
	       
	       
	       return viewResolver;
	       */
	   }
}
