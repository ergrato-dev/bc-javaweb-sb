package com.bootcamp.service;

import com.bootcamp.model.Shipment;
import com.bootcamp.model.ShipmentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for ShipmentProcessor (Proyecto Semana 01).
 *
 * All tests must pass after implementing the 7 requirements.
 * Do NOT modify this file.
 */
class ShipmentProcessorTest {

  private ShipmentProcessor processor;
  private List<Shipment> shipments;

  @BeforeEach
  void setUp() {
    processor = new ShipmentProcessor();

    var today = LocalDate.now();
    var tomorrow = today.plusDays(1);
    var yesterday = today.minusDays(1);

    shipments = List.of(
        new Shipment("S-001", "Bogotá", "Medellín", 2.5, ShipmentStatus.DELIVERED, 150.00, yesterday),
        new Shipment("S-002", "Cali", "Bogotá", 10.0, ShipmentStatus.IN_TRANSIT, 500.00, tomorrow),
        new Shipment("S-003", "Medellín", "Cali", 1.2, ShipmentStatus.PENDING, 80.00, tomorrow),
        new Shipment("S-004", "Barranquilla", "Bogotá", 5.0, ShipmentStatus.DELIVERED, 200.00, yesterday),
        new Shipment("S-005", "Bogotá", "Pasto", 3.0, ShipmentStatus.CANCELLED, 0.00, yesterday),
        new Shipment("S-006", "Cali", "Cartagena", 8.0, ShipmentStatus.DELIVERED, 950.00, yesterday),
        new Shipment("S-007", "Medellín", "Bogotá", 4.5, ShipmentStatus.IN_TRANSIT, 320.00, tomorrow));
  }

  @Test
  @DisplayName("R1: filterByStatus returns only matching shipments")
  void filterByStatus_returnsMatchingShipments() {
    var delivered = processor.filterByStatus(shipments, ShipmentStatus.DELIVERED);

    assertEquals(3, delivered.size());
    assertTrue(delivered.stream().allMatch(s -> s.status() == ShipmentStatus.DELIVERED));
  }

  @Test
  @DisplayName("R1: filterByStatus returns empty list when no match")
  void filterByStatus_returnsEmptyWhenNoMatch() {
    var returned = processor.filterByStatus(shipments, ShipmentStatus.RETURNED);
    assertTrue(returned.isEmpty());
  }

  @Test
  @DisplayName("R2: totalDeclaredValue sums all values correctly")
  void totalDeclaredValue_sumsCorrectly() {
    double total = processor.totalDeclaredValue(shipments);
    assertEquals(2200.00, total, 0.01);
  }

  @Test
  @DisplayName("R3: findById returns shipment when exists")
  void findById_returnsShipmentWhenExists() {
    var result = processor.findById(shipments, "S-003");

    assertTrue(result.isPresent());
    assertEquals("S-003", result.get().id());
  }

  @Test
  @DisplayName("R3: findById returns empty Optional when not found")
  void findById_returnsEmptyWhenNotFound() {
    var result = processor.findById(shipments, "S-999");
    assertTrue(result.isEmpty());
  }

  @Test
  @DisplayName("R4: groupByStatus groups shipments correctly")
  void groupByStatus_groupsCorrectly() {
    var grouped = processor.groupByStatus(shipments);

    assertEquals(3, grouped.get(ShipmentStatus.DELIVERED).size());
    assertEquals(2, grouped.get(ShipmentStatus.IN_TRANSIT).size());
    assertEquals(1, grouped.get(ShipmentStatus.PENDING).size());
    assertEquals(1, grouped.get(ShipmentStatus.CANCELLED).size());
  }

  @Test
  @DisplayName("R5: summarize computes correct statistics")
  void summarize_computesCorrectStats() {
    var summary = processor.summarize(shipments);

    assertNotNull(summary);
    assertEquals(7, summary.totalShipments());
    assertEquals(34.2, summary.totalWeightKg(), 0.01);
    assertEquals(2200.00, summary.totalValue(), 0.01);
    assertTrue(summary.avgValuePerKg() > 0);
  }

  @Test
  @DisplayName("R6: top3MostValuable returns top 3 by value descending")
  void top3MostValuable_returnsCorrectTop3() {
    var top3 = processor.top3MostValuable(shipments);

    assertEquals(3, top3.size());
    assertEquals("S-006", top3.get(0).id()); // 950.00
    assertEquals("S-002", top3.get(1).id()); // 500.00
    assertEquals("S-007", top3.get(2).id()); // 320.00
  }

  @Test
  @DisplayName("R7: allDelivered returns true when all are delivered")
  void allDelivered_returnsTrueWhenAll() {
    var delivered = processor.filterByStatus(shipments, ShipmentStatus.DELIVERED);
    assertTrue(processor.allDelivered(delivered));
  }

  @Test
  @DisplayName("R7: allDelivered returns false when mixed statuses")
  void allDelivered_returnsFalseWhenMixed() {
    assertFalse(processor.allDelivered(shipments));
  }

  @Test
  @DisplayName("R7: allDelivered returns true for empty list")
  void allDelivered_returnsTrueForEmptyList() {
    assertTrue(processor.allDelivered(List.of()));
  }
}
