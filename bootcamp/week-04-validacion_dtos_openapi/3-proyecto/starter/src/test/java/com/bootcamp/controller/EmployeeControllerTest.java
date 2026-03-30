package com.bootcamp.controller;

import com.bootcamp.dto.EmployeeRequest;
import com.bootcamp.dto.EmployeeResponse;
import com.bootcamp.exception.EmployeeNotFoundException;
import com.bootcamp.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @MockBean EmployeeService employeeService;

    @Test
    void shouldReturnAllEmployees() throws Exception {
        when(employeeService.findAll(null)).thenReturn(List.of(
            new EmployeeResponse(1L, "Alice", "alice@co.com", 30, "Engineering", "Engineer"),
            new EmployeeResponse(2L, "Bob",   "bob@co.com",   45, "HR",          "Manager")
        ));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Alice"));
    }

    @Test
    void shouldReturnEmployeeById() throws Exception {
        when(employeeService.findById(1L)).thenReturn(
            new EmployeeResponse(1L, "Alice", "alice@co.com", 30, "Engineering", "Engineer")
        );

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("alice@co.com"));
    }

    @Test
    void shouldReturn404WhenEmployeeNotFound() throws Exception {
        when(employeeService.findById(99L)).thenThrow(new EmployeeNotFoundException(99L));

        mockMvc.perform(get("/api/employees/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void shouldCreateEmployeeAndReturn201() throws Exception {
        var request = new EmployeeRequest("Alice", "alice@co.com", 30, 75000, "Engineering", "Engineer");
        var response = new EmployeeResponse(1L, "Alice", "alice@co.com", 30, "Engineering", "Engineer");
        when(employeeService.create(any())).thenReturn(response);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void shouldReturn400WhenCreatingWithInvalidData() throws Exception {
        var invalidRequest = "{\"name\":\"\",\"email\":\"not-an-email\",\"age\":15,\"salary\":-100,\"department\":\"\",\"position\":\"\"}";

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").exists());
    }

    @Test
    void shouldUpdateEmployeeAndReturn200() throws Exception {
        var request = new EmployeeRequest("Alice Updated", "alice@co.com", 31, 80000, "Engineering", "Senior Engineer");
        var response = new EmployeeResponse(1L, "Alice Updated", "alice@co.com", 31, "Engineering", "Senior Engineer");
        when(employeeService.update(eq(1L), any())).thenReturn(response);

        mockMvc.perform(put("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position").value("Senior Engineer"));
    }

    @Test
    void shouldDeleteEmployeeAndReturn204() throws Exception {
        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn404WhenDeletingNonExistingEmployee() throws Exception {
        doThrow(new EmployeeNotFoundException(99L)).when(employeeService).delete(99L);

        mockMvc.perform(delete("/api/employees/99"))
                .andExpect(status().isNotFound());
    }
}
