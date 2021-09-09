package hello.test.context;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class,
				initializers = MyAppContextInit.class)
public class MyAppTest {
	@Autowired
	private MyService myService;

	@Test
	public void messageTest() {
		System.out.println("messageTest");
		String msg = myService.getMessage();
		assertEquals("Hello World!", msg);
	}
	
	@Test
	public void multiplyNumTest() {
		System.out.println("messageXNumberTest");
		int val = myService.multiplyNum(5, 10);
		assertEquals(50, val);
	}

	@Test
	public void idAvailabilityTest() {
		System.out.println("idAvailableTest");
		boolean val = myService.isIdAvailable(100);
		assertTrue(val);
	}
}