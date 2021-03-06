package hello.mvc.config;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;


//works with just @Configuration 
@Configuration			// works just with this uncommented
//@EnableWebMvc			// activated to test , works as well
@ComponentScan("hello.mvc")	// activated this as well
public class MvcWebConfig implements WebMvcConfigurer {
	
	private static final Logger logger = LoggerFactory.getLogger(MvcWebConfig.class);

	@Autowired
	ApplicationContext applicationContext;
  
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
	  logger.debug("****************Add Interceptor *************");
      ThemeChangeInterceptor themeChangeInterceptor = new ThemeChangeInterceptor();
      themeChangeInterceptor.setParamName("theme");
      registry.addInterceptor(themeChangeInterceptor);

      // change language
      LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
      localeChangeInterceptor.setParamName("lang");
      registry.addInterceptor(localeChangeInterceptor);
   }
   
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
	   registry.addResourceHandler(
              "/webjars/**",
    		   "/resources/**",
               "/img/**",
               "/image/**",
               "/css/**",
               "/js/**")
               .addResourceLocations(
                      "classpath:/META-INF/resources/webjars/",
            		   "classpath:/resources/",
                       "classpath:/static/img/",
                       "classpath:/static/image/",
                       "classpath:/static/css/",
                       "classpath:/static/js/");
   }
   
   
   @Bean("messageSource")
   public MessageSource messageSource() {
	   logger.debug("*****INIT MESSAGE SOURCE *******");
	   ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
	   messageSource.setBasename("classpath:locale/messages");
	   messageSource.setDefaultEncoding("UTF-8");
	   messageSource.setUseCodeAsDefaultMessage(true);
	   return messageSource;
	}
	   
	   @Bean(name = "validator")
	   public LocalValidatorFactoryBean validator() {
		   logger.debug("*****INIT VALIDATOR WITh MESSAGE SOURCE *******");
	       LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	       bean.setValidationMessageSource(messageSource());
	       return bean;
	   }
	   
	   /*
	    * Internationalisation
	    */
	   @Bean
	   public LocaleResolver localeResolver() {
		   logger.debug("*****INIT LOCALE *******");
	      SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	      localeResolver.setDefaultLocale(Locale.US);
	      return localeResolver;
	   }
	   
	   /**
	    * interceptor to change language
	    * @return
	    */
	   
	   @Bean
	   public LocaleChangeInterceptor localeChangeInterceptor() {
	       LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	       lci.setParamName("lang");
	       return lci;
	   }
	  
	   //1. Creating SpringResourceTemplateResolver
	   @Bean
	   @Description("Thymeleaf Template Resolver")
	   public SpringResourceTemplateResolver springTemplateResolver(){	   
		   logger.debug("****************Init springTemplateResolver *************");
		    
		    // SpringResourceTemplateResolver automatically integrates with Spring's own
		    // resource resolution infrastructure, which is highly recommended.
	       SpringResourceTemplateResolver springTemplateResolver = new SpringResourceTemplateResolver();
	       springTemplateResolver.setApplicationContext(this.applicationContext);
	       springTemplateResolver.setPrefix("classpath:templates/");
	       springTemplateResolver.setSuffix(".html");
	       springTemplateResolver.setTemplateMode(TemplateMode.HTML);
	       
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
		   // SpringTemplateEngine automatically applies SpringStandardDialect and
		    // enables Spring's own MessageSource message resolution mechanisms.
	       SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
	       springTemplateEngine.setTemplateResolver(springTemplateResolver());
	       springTemplateEngine.setTemplateEngineMessageSource(messageSource());
	       
	       // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
	       // speed up execution in most scenarios, but might be incompatible
	       // with specific cases when expressions in one template are reused
	       // across different data types, so this flag is "false" by default
	       // for safer backwards compatibility.
	       springTemplateEngine.setEnableSpringELCompiler(true);
	       return springTemplateEngine;
	   }
	   
	   
	   //3. Registering ThymeleafViewResolver
	   @Bean
	   @Description("Thymeleaf View Resolver")
	   public ViewResolver viewResolver(){ 
		   logger.debug("****************Init viewResolver *************");
	       ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	       viewResolver.setTemplateEngine(springTemplateEngine());
	       viewResolver.setOrder(1);
	       viewResolver.setViewNames(new String[] {".html", ".xhtml"});
	       return viewResolver;
	   }
	   

}