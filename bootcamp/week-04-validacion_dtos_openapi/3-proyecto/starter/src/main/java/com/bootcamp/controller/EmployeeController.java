package com.bootcamp.controller;

import com.bootcamp.dto.EmployeeRequest;
import com.bootcamp.dto.EmployeeResponse;
import com.bootcamp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * REST Controller for Employee CRUD operations.
 */
@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employees", description = "Employee management operations")
public class EmployeeController {

    private final EmployeeService employeeService;

    // TODO:
    //  1. Add constructor that receives EmployeeService

    // TODO:
    //  2. GET /api/employees
    //     Optional @RequestParam department
    //     @Operation(summary = "Get all employees")
    //     Return 200 with List<EmployeeResponse>

    // TODO:
    //  3. GET /api/employees/{id}
    //     @Operation(summary = "Get employee by ID")
    //     @ApiResponse(responseCode = "200") + @ApiResponse(responseCode = "404")
    //     Return 200 or let exception propagate to GlobalExceptionHandler

    // TODO:
    //  4. POST /api/employees
    //     @Valid @RequestBody EmployeeRequest
    //     @Operation(summary = "Create new employee")
    //     @ApiResponse(responseCode = "201") + @ApiResponse(responseCode = "400")
    //     Return 201 Created with Location header

    // TODO:
    //  5. PUT /api/employees/{id}
    //     @Valid @RequestBody EmployeeRequest
    //     Return 200 or 404

    // TODO:
    //  6. DELETE /api/employees/{id}
    //     Return 204 No Content or 404
}
