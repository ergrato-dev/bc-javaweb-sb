package com.bootcamp.model;

/**
 * Summary statistics for a group of shipments.
 *
 * @param totalShipments total number of shipments analyzed
 * @param totalWeightKg  combined weight of all shipments
 * @param totalValue     combined declared value of all shipments
 * @param avgValuePerKg  average declared value per kilogram
 */
public record ShipmentSummary(
    long totalShipments,
    double totalWeightKg,
    double totalValue,
    double avgValuePerKg) {
}
