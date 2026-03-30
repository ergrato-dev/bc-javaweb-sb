package com.bootcamp.model;

import java.time.LocalDate;

/**
 * Represents a shipment in the logistics system.
 *
 * This is an immutable data model using Java 21 Records.
 * The compact constructor validates preconditions at creation time.
 *
 * @param id                unique shipment identifier
 * @param origin            city of origin
 * @param destination       city of destination
 * @param weightKg          weight in kilograms (must be > 0)
 * @param status            current shipment status
 * @param declaredValue     monetary value declared for the shipment
 * @param estimatedDelivery expected delivery date
 */
public record Shipment(
    String id,
    String origin,
    String destination,
    double weightKg,
    ShipmentStatus status,
    double declaredValue,
    LocalDate estimatedDelivery) {
  // Compact constructor for validation
  public Shipment {
    if (id == null || id.isBlank())
      throw new IllegalArgumentException("id must not be blank");
    if (weightKg <= 0)
      throw new IllegalArgumentException("weightKg must be > 0");
    if (declaredValue < 0)
      throw new IllegalArgumentException("declaredValue must be >= 0");
    if (estimatedDelivery == null)
      throw new IllegalArgumentException("estimatedDelivery required");
  }

  /**
   * Returns true if this shipment is delayed (estimated delivery is in the past
   * and status is not DELIVERED or CANCELLED).
   */
  public boolean isDelayed() {
    return estimatedDelivery.isBefore(LocalDate.now())
        && status != ShipmentStatus.DELIVERED
        && status != ShipmentStatus.CANCELLED;
  }
}
