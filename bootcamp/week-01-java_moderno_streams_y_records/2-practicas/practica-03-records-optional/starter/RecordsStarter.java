import java.util.*;

/**
 * Práctica 03 — Records, var y Optional
 *
 * Instrucciones: descomenta cada sección en orden.
 */
public class RecordsStarter {

  // ============================================
  // Records — definidos a nivel de clase
  // ============================================

  // Descomenta para PASO 1:
  // record Point(double x, double y) {
  // // Método de instancia en un record
  // double distanceTo(Point other) {
  // var dx = this.x - other.x;
  // var dy = this.y - other.y;
  // return Math.sqrt(dx * dx + dy * dy);
  // }
  // }

  // Descomenta para PASO 2:
  // record Temperature(double celsius) {
  // // Compact constructor — valida el valor
  // public Temperature {
  // if (celsius < -273.15)
  // throw new IllegalArgumentException(
  // "Temperature below absolute zero: " + celsius);
  // }
  //
  // // Método de conversión
  // double toFahrenheit() {
  // return celsius * 9 / 5 + 32;
  // }
  // }

  // Descomenta para PASO 5:
  // record Person(Long id, String name, String email) {}

  public static void main(String[] args) {

    // ============================================
    // PASO 1: Record básico
    // ============================================
    // Crea dos puntos y calcula la distancia entre ellos
    // Descomenta las siguientes líneas:

    // var p1 = new Point(0.0, 0.0);
    // var p2 = new Point(3.0, 4.0);
    // System.out.println("p1: " + p1); // Point[x=0.0, y=0.0]
    // System.out.println("p2: " + p2); // Point[x=3.0, y=4.0]
    // System.out.println("Distance: " + p1.distanceTo(p2)); // 5.0
    // System.out.println("Equal: " + p1.equals(p2)); // false

    // ============================================
    // PASO 2: Compact constructor con validación
    // ============================================
    // Crea temperaturas válidas e inválidas
    // Descomenta las siguientes líneas:

    // var boiling = new Temperature(100.0);
    // System.out.println("Boiling in F: " + boiling.toFahrenheit()); // 212.0
    //
    // // Esto debe lanzar IllegalArgumentException:
    // try {
    // var invalid = new Temperature(-300.0); // below absolute zero
    // } catch (IllegalArgumentException e) {
    // System.out.println("Caught: " + e.getMessage());
    // }

    // ============================================
    // PASO 3: var en contextos apropiados
    // ============================================
    // Observa uso correcto e incorrecto de var
    // Descomenta las siguientes líneas:

    // var greeting = "Hello, Java 21!"; // tipo: String
    // var number = 42; // tipo: int
    // var decimal = 3.14; // tipo: double
    // var names = new ArrayList<String>(); // tipo: ArrayList<String>
    // names.add("Spring");
    // names.add("Boot");
    //
    // System.out.println(greeting);
    // System.out.println("Number: " + number + ", Decimal: " + decimal);
    // System.out.println("Names: " + names);
    //
    // // var en bucle for-each
    // for (var name : names) {
    // System.out.println("- " + name.toUpperCase());
    // }

    // ============================================
    // PASO 4: Optional.ofNullable y map
    // ============================================
    // Descomenta las siguientes líneas:

    // String rawValue = null; // cambia a "spring" para ver el otro comportamiento
    //
    // Optional<String> maybe = Optional.ofNullable(rawValue);
    // String result = maybe
    // .map(String::trim)
    // .map(String::toUpperCase)
    // .orElse("DEFAULT");
    // System.out.println("Result: " + result); // DEFAULT (cuando rawValue es null)

    // ============================================
    // PASO 5: orElseThrow en búsquedas
    // ============================================
    // Descomenta las siguientes líneas:

    // var people = List.of(
    // new Person(1L, "Alice", "alice@example.com"),
    // new Person(2L, "Bob", "bob@example.com")
    // );
    //
    // Long searchId = 1L; // cambia a 99L para ver el error
    //
    // try {
    // Person found = people.stream()
    // .filter(p -> p.id().equals(searchId))
    // .findFirst()
    // .orElseThrow(() -> new NoSuchElementException("Person not found: " +
    // searchId));
    // System.out.println("Found: " + found.name());
    // } catch (NoSuchElementException e) {
    // System.out.println("Error: " + e.getMessage());
    // }

    // ============================================
    // PASO 6: ifPresentOrElse
    // ============================================
    // Descomenta las siguientes líneas:

    // Optional<String> maybeCity = Optional.ofNullable(null); // cambia a
    // Optional.of("Bogotá")
    //
    // maybeCity.ifPresentOrElse(
    // city -> System.out.println("City: " + city),
    // () -> System.out.println("No city provided")
    // );
  }
}
