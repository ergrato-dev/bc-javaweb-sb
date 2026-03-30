package com.bootcamp.controller;

import com.bootcamp.dto.RegisterRequest;
import com.bootcamp.dto.UserResponse;
import com.bootcamp.domain.Role;
import com.bootcamp.exception.UserNotFoundException;
import com.bootcamp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bootcamp.security.SecurityConfig;
import com.bootcamp.security.CustomUserDetailsService;

/**
 * Tests de seguridad para UserController.
 *
 * @WebMvcTest carga solo el slice web (controllers + security filters)
 * @WithMockUser simula un usuario autenticado sin necesitar credenciales reales
 */
@WebMvcTest(UserController.class)
@Import({SecurityConfig.class, CustomUserDetailsService.class})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    // CustomUserDetailsService necesita UserRepository — lo mockeamos
    @MockBean
    private com.bootcamp.repository.UserRepository userRepository;

    private final UserResponse sampleUser = new UserResponse(
            1L, "testuser", Role.ROLE_USER, true, LocalDateTime.now());

    // ─── Tests de autenticación ───────────────────────────────

    @Test
    void register_shouldReturn201WhenValidRequest() throws Exception {
        var request = new RegisterRequest("newuser", "password123", Role.ROLE_USER);
        when(userService.register(any())).thenReturn(sampleUser);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    void register_shouldReturn400WhenInvalidRequest() throws Exception {
        // password muy corto → falla @Size(min = 6)
        var request = new RegisterRequest("user", "123", null);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void adminEndpoint_shouldReturn401WhenNotAuthenticated() throws Exception {
        // Sin credenciales → 401 Unauthorized
        mockMvc.perform(get("/api/admin/users"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user1", authorities = "ROLE_USER")
    void adminEndpoint_shouldReturn403WhenUserRole() throws Exception {
        // Usuario con ROLE_USER intentando acceder a endpoint ADMIN → 403 Forbidden
        mockMvc.perform(get("/api/admin/users"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", authorities = "ROLE_ADMIN")
    void adminEndpoint_shouldReturn200WhenAdmin() throws Exception {
        // Usuario con ROLE_ADMIN puede acceder → 200 OK
        when(userService.findAll(any())).thenReturn(
                new org.springframework.data.domain.PageImpl<>(
                        java.util.List.of(sampleUser)));

        mockMvc.perform(get("/api/admin/users"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", authorities = "ROLE_ADMIN")
    void deactivateUser_shouldReturn204WhenAdminAndUserExists() throws Exception {
        // DELETE no falla → 204 No Content
        mockMvc.perform(delete("/api/admin/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "admin", authorities = "ROLE_ADMIN")
    void findByUsername_shouldReturn404WhenNotFound() throws Exception {
        when(userService.findByUsername("unknown"))
                .thenThrow(new UserNotFoundException("unknown"));

        mockMvc.perform(get("/api/admin/users/unknown"))
                .andExpect(status().isNotFound());
    }
}
