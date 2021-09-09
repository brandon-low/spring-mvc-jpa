package hello.test.mvc.service;


import java.time.LocalDate;

public interface FakeService {
    LocalDate getValidBirthday(String birthdayString) ;
    
    String getBirthDOW(LocalDate birthday);
    
    String getChineseZodiac(LocalDate birthday);

    String getStarSign(LocalDate birthday) ;
}