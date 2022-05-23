package com.jagmeet.portfoliobuilder.service;

import com.jagmeet.portfoliobuilder.entities.User;
import com.jagmeet.portfoliobuilder.exceptions.NotFoundException;
import com.jagmeet.portfoliobuilder.exceptions.UserAlreadyExistsException;
import com.jagmeet.portfoliobuilder.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private UserService underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserService(userRepository,passwordEncoder);
    }

    @Test
    void shouldFindUserByUserName() {
        // Given
        User dummy = User.builder()
                .userName("jagmeet")
                .email("jagmeet0151")
                .build();
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.ofNullable(dummy));
        // When
        User actual = underTest.findUserByUserName("jagmeet");

        // Then
        assert dummy != null;
        Assertions.assertEquals(dummy.getUserName(),actual.getUserName());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenUserNotFoundByUsername(){
        // Given
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class,() -> underTest.findUserByUserName(anyString()));
    }

    @Test
    void shouldCreateNewUser() {
        // Given
        String username = "jagmeet";
        String email = "jagmeet.alagh@gmail.com";
        String password = "password";
        String role = "USER";
        User dummy = User.builder()
                .userName("jagmeet")
                .email("jagmeet.alagh@gmail.com")
                .role("USER")
                .build();
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.empty());

        // When
        underTest.createNewUser(username,email,role,password);
        // Then
        verify(userRepository).save(dummy);
    }

    @Test
    void shouldThrowUserAlreadyExistsExceptionWhenUserExists(){
        // Given
        User dummy = User.builder()
                .userName("jagmeet")
                .email("jagmeet.alagh@gmail.com")
                .role("USER")
                .build();
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.ofNullable(dummy));

        // Then
        assertThrows(
                UserAlreadyExistsException.class,
                () -> underTest.createNewUser("jagmeet","jagmeet.alagh@gmail.com","USER","password")
        );
    }
}