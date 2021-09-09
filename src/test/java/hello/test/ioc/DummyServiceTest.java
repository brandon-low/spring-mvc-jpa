package hello.test.ioc;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jdk.internal.org.jline.utils.Log;


//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = AppConfig.class) 
// same as 
@SpringJUnitConfig(AppConfig.class)
public class DummyServiceTest {
	
	private static final Logger log = LoggerFactory.getLogger(DummyServiceTest.class);
	@Autowired
	private DummyService service;

	@Test
	public void messageTest() {
		log.debug("messageTest");
		String msg = service.getMessage();
		assertEquals("Hello World!", msg);
	}
	
	@Test
	public void multiplyNumTest() {
		log.debug("messageXNumberTest");
		int val = service.multiplyNum(5, 10);
		assertEquals(50, val);
	}

	@Test
	public void idAvailabilityTest() {
		log.debug("idAvailableTest");
		boolean val = service.isIdAvailable(100);
		assertTrue(val);
	}
}