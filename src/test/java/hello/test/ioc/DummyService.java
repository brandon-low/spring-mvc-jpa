package hello.test.ioc;

import org.springframework.stereotype.Service;

@Service
public class DummyService {
    public String getMessage() {
    	return "Hello World!";
    }
    public int multiplyNum(int num1, int num2) {
    	return num1 * num2;
    }
    public boolean isIdAvailable(long id) {
        if (id == 100) {
        	return true;
        }
        return false;
    }    
}
