package com.template;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.controller.UserController;
import com.template.model.User;
import com.template.service.UserService;

import static org.mockito.Mockito.when;

class UserControllerTests {
    
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private User user1 = new User(
        25L,
        "Alice Johnson",
        "123 Elm Street, Springfield",
        "9876543210",
        "alice@example.com",
        "Tech Innovations",
        "alice.tech",
        "password123",
        null,
        "Basic Plan",
        new HashSet<>()
    );

    private User user2 = new User(
        26L,
        "Bob Smith",
        "456 Oak Avenue, Metropolis",
        "8765432109",
        "bob@example.com",
        "Creative Solutions",
        "bob.creative",
        "password456",
        null,
        "Premium Plan",
        new HashSet<>()
    );

    private User user3 = new User(
        27L,
        "Charlie Brown",
        "789 Pine Road, Gotham City",
        "7654321098",
        "charlie@example.com",
        "Brown Enterprises",
        "charlie.brown",
        "password789",
        null,
        "Enterprise Plan",
        new HashSet<>()
    );

    @BeforeEach
    void setUp() {
    	
        MockitoAnnotations.openMocks(this);
        
        // Configure the MockMvc with JSON message converter
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
            .setMessageConverters(new MappingJackson2HttpMessageConverter())
            .build();
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() throws Exception {
        // Arrange
        List<User> users = Arrays.asList(user1, user2, user3);
        when(userService.getAllUsers()).thenReturn(users);

        // Act & Assert
        mockMvc.perform(get("/get-all-user")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].fullName").value(user1.getFullName()))
                .andExpect(jsonPath("$[0].email").value(user1.getEmail()))
                .andExpect(jsonPath("$[1].fullName").value(user2.getFullName()))
                .andExpect(jsonPath("$[2].fullName").value(user3.getFullName()));
    }


    
    @Test
    void saveUser_shouldSaveUserSuccessfully() throws Exception {
        // Arrange
        String userJson = objectMapper.writeValueAsString(user1);
        MockMultipartFile userPart = new MockMultipartFile(
            "user",
            null,
            MediaType.APPLICATION_JSON_VALUE,
            userJson.getBytes()
        );

        MockMultipartFile logoPart = new MockMultipartFile(
            "logo",
            "logo.png",
            MediaType.IMAGE_PNG_VALUE,
            "test logo".getBytes()
        );

        // Match the actual service response with CREATED status
        ResponseEntity<String> response = new ResponseEntity<>("User saved successfully", HttpStatus.CREATED);
        when(userService.saveUserInfo(any(User.class), any(MultipartFile.class)))
            .thenReturn(response);

        // Act & Assert
        mockMvc.perform(multipart("/save-user-info")
                .file(logoPart)
                .file(userPart)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())  // Expect 201 CREATED status
                .andExpect(content().json("\"User saved successfully\"")); // Expect JSON string with quotes
    }

    
    
    @Test
    void saveUser_shouldHandleDuplicateUsername() throws Exception {
        // Arrange
        String userJson = objectMapper.writeValueAsString(user1);
        MockMultipartFile userPart = new MockMultipartFile(
            "user",
            null,
            MediaType.APPLICATION_JSON_VALUE,
            userJson.getBytes()
        );

        MockMultipartFile logoPart = new MockMultipartFile(
            "logo",
            "logo.png",
            MediaType.IMAGE_PNG_VALUE,
            "test logo".getBytes()
        );

        // Mock duplicate username response
        ResponseEntity<String> response = new ResponseEntity<>(
            "Username is already present, try another username", 
            HttpStatus.BAD_REQUEST
        );
        when(userService.saveUserInfo(any(User.class), any(MultipartFile.class)))
            .thenReturn(response);

        // Act & Assert
        mockMvc.perform(multipart("/save-user-info")
                .file(logoPart)
                .file(userPart)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("\"Username is already present, try another username\""));
    }

    
    
    @Test
    void saveUser_shouldHandleValidationError() throws Exception {
        // Arrange
        String userJson = objectMapper.writeValueAsString(user1);
        MockMultipartFile userPart = new MockMultipartFile(
            "user",
            null,
            MediaType.APPLICATION_JSON_VALUE,
            userJson.getBytes()
        );

        MockMultipartFile logoPart = new MockMultipartFile(
            "logo",
            "logo.png",
            MediaType.IMAGE_PNG_VALUE,
            "test logo".getBytes()
        );

        // Mock validation error response
        ResponseEntity<String> response = new ResponseEntity<>(
            "Validation error message", 
            HttpStatus.BAD_REQUEST
        );
        when(userService.saveUserInfo(any(User.class), any(MultipartFile.class)))
            .thenReturn(response);

        // Act & Assert
        mockMvc.perform(multipart("/save-user-info")
                .file(logoPart)
                .file(userPart)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("\"Validation error message\""));
    }
}