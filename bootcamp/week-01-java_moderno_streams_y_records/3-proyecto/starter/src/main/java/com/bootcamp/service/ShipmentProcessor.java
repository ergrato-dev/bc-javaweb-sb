package com.bootcamp.service;

import com.bootcamp.model.Shipment;
import com.bootcamp.model.ShipmentStatus;
import com.bootcamp.model.ShipmentSummary;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Processes shipment data using Java Streams, Records and Optional.
 *
 * Each method represents a business requirement (R1-R7).
 * Implement all methods using only the Stream API and Optional — no for loops.
 */
public class ShipmentProcessor {

  // ============================================
  // R1: Filter by status
  // ============================================
  /**
   * Returns all shipments with the given status.
   *
   * TODO:
   * 1. Use stream() on the shipments list
   * 2. Apply filter() with the status predicate
   * 3. Return result as an unmodifiable list with toList()
   */
  public List<Shipment> filterByStatus(List<Shipment> shipments, ShipmentStatus status) {
    // TODO: Implement using Stream.filter()
    return null;
  }

  // ============================================
  // R2: Total declared value
  // ============================================
  /**
   * Returns the sum of declaredValue for all shipments in the list.
   *
   * TODO:
   * 1. Use mapToDouble() to extract declaredValue
   * 2. Use sum() terminal operation
   */
  public double totalDeclaredValue(List<Shipment> shipments) {
    // TODO: Implement using mapToDouble().sum()
    return 0;
  }

  // ============================================
  // R3: Find shipment by ID
  // ============================================
  /**
   * Returns the shipment with the given ID, or empty if not found.
   *
   * TODO:
   * 1. Filter by id equality
   * 2. Return findFirst() which gives Optional<Shipment>
   */
  public Optional<Shipment> findById(List<Shipment> shipments, String id) {
    // TODO: Implement using filter().findFirst()
    return Optional.empty();
  }

  // ============================================
  // R4: Group shipments by status
  // ============================================
  /**
   * Returns a Map where the key is ShipmentStatus and the value
   * is the list of shipments in that status.
   *
   * TODO:
   * 1. Use Collectors.groupingBy(Shipment::status)
   */
  public Map<ShipmentStatus, List<Shipment>> groupByStatus(List<Shipment> shipments) {
    // TODO: Implement using Collectors.groupingBy()
    return Map.of();
  }

  // ============================================
  // R5: Shipment summary
  // ============================================
  /**
   * Returns a ShipmentSummary record with aggregate statistics for
   * all shipments in the list.
   *
   * TODO:
   * 1. Count total shipments
   * 2. Sum total weight with mapToDouble().sum()
   * 3. Sum total declared value
   * 4. Calculate avgValuePerKg = totalValue / totalWeightKg (handle division by
   * zero)
   * 5. Construct and return ShipmentSummary record
   */
  public ShipmentSummary summarize(List<Shipment> shipments) {
    // TODO: Implement and return a ShipmentSummary
    return null;
  }

  // ============================================
  // R6: Top 3 most valuable shipments
  // ============================================
  /**
   * Returns the top 3 shipments sorted by declaredValue descending.
   *
   * TODO:
   * 1. Sort by declaredValue reversed
   * 2. Limit to 3
   * 3. Collect to list
   */
  public List<Shipment> top3MostValuable(List<Shipment> shipments) {
    // TODO: Implement using sorted().limit().toList()
    return List.of();
  }

  // ============================================
  // R7: Check if all delivered
  // ============================================
  /**
   * Returns true if ALL shipments in the list have status DELIVERED.
   * Returns true for an empty list (vacuously true).
   *
   * TODO:
   * 1. Use allMatch() with a status predicate
   */
  public boolean allDelivered(List<Shipment> shipments) {
    // TODO: Implement using allMatch()
    return false;
  }
}
