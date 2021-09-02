package hello.mvc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(1)
@WebFilter(urlPatterns = "/*")
public class SimpleFilter implements Filter {

	private final static Logger log = LoggerFactory.getLogger(SimpleFilter.class);

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		log.info("Initializing filter :{}", this);
	}
	
	private boolean isLoginPost(HttpServletRequest req) {
		try {
		return (req.getMethod().equalsIgnoreCase("POST") && 
					req.getServletPath().equalsIgnoreCase("/login"));
		} catch (Exception e) {
			
		}
			
		return false;
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		log.info("Start Transaction Logging Request  {} : {} : {} : {} : {}",
				req.getMethod(), req.getRequestURI(), req.getRequestURL(),
				req.getContentType(), req.getServletPath());
		// use req.getServletPath() and session to check permission
		log.debug("Is Login {} : {} : {}" , isLoginPost(req), req.getSession().getAttribute("user"), req.getSession().getAttribute("roles"));
		
		
		chain.doFilter(request, response);
		
		log.info("Committing Transaction for res {} : {}", req.getRequestURI(),  res.getContentType());
	
	}

	@Override
	public void destroy() {
		log.warn("Destructing filter :{}", this);
	}

}
