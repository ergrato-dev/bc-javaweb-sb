import java.util.*;
import java.util.stream.*;

/**
 * Práctica 02 — Streams API
 *
 * Instrucciones: descomenta cada sección en orden.
 * Ejecuta y verifica la salida esperada antes de continuar.
 */
public class StreamsStarter {

  record Product(String name, String category, double price) {
  }

  record Order(String id, List<String> items) {
  }

  public static void main(String[] args) {

    var products = List.of(
        new Product("Laptop", "Electronics", 999.00),
        new Product("Phone", "Electronics", 599.00),
        new Product("Monitor", "Electronics", 350.00),
        new Product("Keyboard", "Electronics", 120.00),
        new Product("Desk", "Furniture", 299.00),
        new Product("Chair", "Furniture", 199.00),
        new Product("Lamp", "Furniture", 49.99),
        new Product("Pen", "Stationery", 1.99),
        new Product("Notebook", "Stationery", 9.99),
        new Product("Stapler", "Stationery", 12.50));

    // ============================================
    // PASO 1: filter y map básicos
    // ============================================
    // Filtra productos con precio > 300 y retorna solo los nombres
    // Salida esperada: [Laptop, Phone, Monitor]
    // Descomenta las siguientes líneas:

    // List<String> expensiveNames = products.stream()
    // .filter(p -> p.price() > 300)
    // .map(Product::name)
    // .toList();
    // System.out.println("Expensive: " + expensiveNames);

    // ============================================
    // PASO 2: sorted
    // ============================================
    // Ordena todos los productos de mayor a menor precio e imprime nombre + precio
    // Descomenta las siguientes líneas:

    // System.out.println("\n--- Sorted by price desc ---");
    // products.stream()
    // .sorted(Comparator.comparingDouble(Product::price).reversed())
    // .forEach(p -> System.out.printf("%-12s $%.2f%n", p.name(), p.price()));

    // ============================================
    // PASO 3: reduce / sum
    // ============================================
    // Calcula el precio total de todos los productos de Electronics
    // Descomenta las siguientes líneas:

    // double electronicTotal = products.stream()
    // .filter(p -> p.category().equals("Electronics"))
    // .mapToDouble(Product::price)
    // .sum();
    // System.out.printf("%nElectronics total: $%.2f%n", electronicTotal);
    // Salida esperada: Electronics total: $2068.00

    // ============================================
    // PASO 4: groupingBy
    // ============================================
    // Agrupa productos por categoría e imprime cuántos hay en cada una
    // Descomenta las siguientes líneas:

    // Map<String, Long> countByCategory = products.stream()
    // .collect(Collectors.groupingBy(Product::category, Collectors.counting()));
    // System.out.println("\n--- Count by category ---");
    // countByCategory.forEach((cat, count) ->
    // System.out.println(cat + ": " + count));

    // ============================================
    // PASO 5: DoubleSummaryStatistics
    // ============================================
    // Obtén estadísticas de precios de todos los productos
    // Descomenta las siguientes líneas:

    // DoubleSummaryStatistics stats = products.stream()
    // .collect(Collectors.summarizingDouble(Product::price));
    // System.out.printf("%nMin: %.2f | Max: %.2f | Avg: %.2f%n",
    // stats.getMin(), stats.getMax(), stats.getAverage());

    // ============================================
    // PASO 6: flatMap
    // ============================================
    // Aplana órdenes de compra y obtén todos los ítems únicos en una lista
    // Descomenta las siguientes líneas:

    // var orders = List.of(
    // new Order("ORD-001", List.of("Laptop", "Keyboard", "Mouse")),
    // new Order("ORD-002", List.of("Phone", "Case")),
    // new Order("ORD-003", List.of("Laptop", "Monitor", "Keyboard"))
    // );
    //
    // List<String> allItems = orders.stream()
    // .flatMap(o -> o.items().stream())
    // .distinct()
    // .sorted()
    // .toList();
    // System.out.println("\nAll unique items: " + allItems);
    // Salida esperada: [Case, Keyboard, Laptop, Monitor, Mouse, Phone]
  }
}
