package net.engineeringdigest.JournalApp.service;

import net.engineeringdigest.JournalApp.entity.User;
import net.engineeringdigest.JournalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {


    @Autowired
    private UserRepository userRepository;

//    @Test
    @ParameterizedTest
    @ValueSource (strings = {
            "ram",
            "priyanshu",
            "shyam"
    })
    public void testFindByUserName(String name){
        assertNotNull(userRepository.findByUserName(name),"Failed for:" + name);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"

    })
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}
