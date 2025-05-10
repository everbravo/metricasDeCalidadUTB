package com.mesfix.platform.controller;

import com.mesfix.platform.domain.User;
import com.mesfix.platform.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("123456");
        testUser.setEmail("test@example.com");
        testUser.setFullName("Test User");
        testUser.setPhone("1234567890");
    }

    @Test
    void testRegister_UserDoesNotExist_ReturnsCreated() {
        when(userRepository.findByUsername("testuser")).thenReturn(null);

        ResponseEntity<User> response = userController.register(testUser);

        verify(userRepository).save(testUser);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void testRegister_UserAlreadyExists_ReturnsConflict() {
        when(userRepository.findByUsername("testuser")).thenReturn(testUser);

        ResponseEntity<User> response = userController.register(testUser);

        verify(userRepository, never()).save(any());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testLogin_Successful_ReturnsOk() {
        when(userRepository.findByUsername("testuser")).thenReturn(testUser);

        ResponseEntity<User> response = userController.login(testUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void testLogin_InvalidPassword_ReturnsUnauthorized() {
        User wrongPasswordUser = new User();
        wrongPasswordUser.setUsername("testuser");
        wrongPasswordUser.setPassword("wrong");

        when(userRepository.findByUsername("testuser")).thenReturn(testUser);

        ResponseEntity<User> response = userController.login(wrongPasswordUser);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testLogin_UserNotFound_ReturnsUnauthorized() {
        when(userRepository.findByUsername("testuser")).thenReturn(null);

        ResponseEntity<User> response = userController.login(testUser);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteUser_UserExists_ReturnsOk() {
        when(userRepository.findByUsername("testuser")).thenReturn(testUser);

        ResponseEntity<Void> response = userController.deleteUser(testUser);

        verify(userRepository).delete(testUser);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteUser_UserNotFound_ReturnsNotFound() {
        when(userRepository.findByUsername("testuser")).thenReturn(null);

        ResponseEntity<Void> response = userController.deleteUser(testUser);

        verify(userRepository, never()).delete(any());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
