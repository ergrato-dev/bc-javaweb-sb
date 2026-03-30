package com.bootcamp.service;

import com.bootcamp.dto.EmployeeRequest;
import com.bootcamp.dto.EmployeeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Business service for employee management.
 * Uses in-memory storage (JPA added in week 05).
 */
@Service
public class EmployeeService {

  private final AtomicLong counter = new AtomicLong(1);
  private final List<EmployeeResponse> store = new ArrayList<>(List.of(
      new EmployeeResponse(counter.getAndIncrement(), "Alice Johnson", "alice@company.com", 30, "Engineering",
          "Software Engineer"),
      new EmployeeResponse(counter.getAndIncrement(), "Bob Smith", "bob@company.com", 45, "HR", "HR Manager"),
      new EmployeeResponse(counter.getAndIncrement(), "Carol White", "carol@company.com", 28, "Finance", "Analyst")));

  /**
   * Returns all employees, optionally filtered by department.
   *
   * TODO:
   * 1. If department parameter is not null and not blank:
   * filter store by department (case-insensitive)
   * 2. Otherwise return all employees
   */
  public List<EmployeeResponse> findAll(String department) {
    // TODO: Implement with optional department filter
    return List.copyOf(store);
  }

  /**
   * Finds an employee by ID.
   *
   * TODO:
   * 1. Stream over store, filter by id, findFirst
   * 2. orElseThrow EmployeeNotFoundException(id)
   */
  public EmployeeResponse findById(Long id) {
    // TODO: Implement
    return null;
  }

  /**
   * Creates a new employee.
   *
   * TODO:
   * 1. Check email uniqueness — throw DuplicateEmailException if already exists
   * 2. Build EmployeeResponse with auto-incremented ID from request fields
   * 3. Add to store and return created employee
   */
  public EmployeeResponse create(EmployeeRequest request) {
    // TODO: Implement with email uniqueness validation
    return null;
  }

  /**
   * Updates an employee.
   *
   * TODO:
   * 1. Find by ID (throw EmployeeNotFoundException if not found)
   * 2. Check email uniqueness — only if email changed
   * 3. Create updated EmployeeResponse with same ID
   * 4. Replace in store and return updated employee
   */
  public EmployeeResponse update(Long id, EmployeeRequest request) {
    // TODO: Implement
    return null;
  }

  /**
   * Deletes an employee.
   *
   * TODO:
   * 1. Find by ID and remove (throw EmployeeNotFoundException if not found)
   */
  public void delete(Long id) {
    // TODO: Implement
  }
}
