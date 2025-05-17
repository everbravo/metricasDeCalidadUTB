package com.mesfix.platform.controller;

import com.mesfix.platform.domain.User;
import com.mesfix.platform.domain.dto.UserDto;
import com.mesfix.platform.domain.translate.UserMapper;
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

    @Mock
    private UserMapper userMapper;

    private User testUser;
    private UserDto testUserDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("123456");
        testUser.setEmail("test@example.com");
        testUser.setFullName("Test User");
        testUser.setPhone("1234567890");

        testUserDto = new UserDto();
        testUserDto.setUsername("testuser");
        testUserDto.setPassword("123456");
        testUserDto.setEmail("test@example.com");
        testUserDto.setFullName("Test User");
        testUserDto.setPhone("1234567890");
    }

    @Test
    void testRegister_UserDoesNotExist_ReturnsCreated() {
        when(userRepository.findByUsername("testuser")).thenReturn(null);
        when(userMapper.toEntity(testUserDto)).thenReturn(testUser);
        ResponseEntity<UserDto> response = userController.register(testUserDto);

        verify(userRepository).save(testUser);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testUserDto, response.getBody());
    }

    @Test
    void testRegister_UserAlreadyExists_ReturnsConflict() {
        when(userRepository.findByUsername("testuser")).thenReturn(testUser);
        when(userMapper.toEntity(testUserDto)).thenReturn(testUser);
        ResponseEntity<UserDto> response = userController.register(testUserDto);

        verify(userRepository, never()).save(any());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testLogin_Successful_ReturnsOk() {
        when(userRepository.findByUsername("testuser")).thenReturn(testUser);
        when(userMapper.toDto(testUser)).thenReturn(testUserDto);
        ResponseEntity<UserDto> response = userController.login(testUserDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUserDto, response.getBody());
    }

    @Test
    void testLogin_InvalidPassword_ReturnsUnauthorized() {
        UserDto wrongPasswordUser = new UserDto();
        wrongPasswordUser.setUsername("testuser");
        wrongPasswordUser.setPassword("wrong");

        when(userRepository.findByUsername("testuser")).thenReturn(testUser);
        when(userMapper.toEntity(testUserDto)).thenReturn(testUser);
        ResponseEntity<UserDto> response = userController.login(wrongPasswordUser);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testLogin_UserNotFound_ReturnsUnauthorized() {
        when(userRepository.findByUsername("testuser")).thenReturn(null);
        when(userMapper.toEntity(testUserDto)).thenReturn(testUser);
        ResponseEntity<UserDto> response = userController.login(testUserDto);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteUser_UserExists_ReturnsOk() {
        when(userRepository.findByUsername("testuser")).thenReturn(testUser);
        when(userMapper.toEntity(testUserDto)).thenReturn(testUser);
        ResponseEntity<Void> response = userController.deleteUser(testUserDto);

        verify(userRepository).delete(testUser);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteUser_UserNotFound_ReturnsNotFound() {
        when(userRepository.findByUsername("testuser")).thenReturn(null);
        when(userMapper.toEntity(testUserDto)).thenReturn(testUser);
        ResponseEntity<Void> response = userController.deleteUser(testUserDto);

        verify(userRepository, never()).delete(any());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
