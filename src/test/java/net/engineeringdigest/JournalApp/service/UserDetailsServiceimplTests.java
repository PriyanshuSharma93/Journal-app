package net.engineeringdigest.JournalApp.service;

import net.engineeringdigest.JournalApp.entity.User;   // <-- Ye import hona chahiye
import net.engineeringdigest.JournalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.mockito.Mockito.when;

@ActiveProfiles("dev")
public class UserDetailsServiceimplTests {

    @InjectMocks
    private UserDetailsServiceimpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest() {

        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().
                userName("ram").password("fvhgf").roles(List.of("USER")).build());
        UserDetails user = userDetailsService.loadUserByUsername("ram");

        Assertions.assertNotNull(user);
    }
}