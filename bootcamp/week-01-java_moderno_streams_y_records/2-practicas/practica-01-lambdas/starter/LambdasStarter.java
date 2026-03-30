import java.util.List;
import java.util.function.*;

/**
 * Práctica 01 — Lambdas y Functional Interfaces
 *
 * Instrucciones: descomenta cada sección en orden.
 * Ejecuta y verifica la salida esperada antes de continuar.
 */
public class LambdasStarter {

  public static void main(String[] args) {

    // ============================================
    // PASO 2: Predicate básico
    // ============================================
    // Un Predicate<T> recibe T y retorna boolean.
    // Descomenta las siguientes líneas:

    // Predicate<String> isEmail = s -> s.contains("@");
    // System.out.println(isEmail.test("user@example.com")); // true
    // System.out.println(isEmail.test("notanemail")); // false

    // ============================================
    // PASO 3: Function — transformaciones
    // ============================================
    // Function<T, R> transforma T en R.
    // Descomenta las siguientes líneas:

    // Function<String, Integer> toLength = s -> s.length();
    // System.out.println(toLength.apply("Hello")); // 5
    // System.out.println(toLength.apply("Morning")); // 7
    //
    // Function<String, String> toUpper = String::toUpperCase;
    // System.out.println(toUpper.apply("hello")); // HELLO

    // ============================================
    // PASO 4: Consumer — efecto lateral
    // ============================================
    // Consumer<T> recibe T y retorna void.
    // Descomenta las siguientes líneas:

    // Consumer<String> logger = msg -> System.out.println("[LOG] " + msg);
    // logger.accept("Starting process"); // [LOG] Starting process
    // logger.accept("Item processed"); // [LOG] Item processed

    // ============================================
    // PASO 5: Supplier — creación lazy
    // ============================================
    // Supplier<T> no recibe parámetros y retorna T.
    // Descomenta las siguientes líneas:

    // Supplier<List<String>> newList = () -> new java.util.ArrayList<>();
    // var list = newList.get();
    // list.add("Spring");
    // list.add("Boot");
    // System.out.println(list); // [Spring, Boot]

    // ============================================
    // PASO 6: Composición de Predicates
    // ============================================
    // Los predicates se componen con .and(), .or(), .negate()
    // Descomenta las siguientes líneas:

    // Predicate<String> notEmpty = s -> !s.isEmpty();
    // Predicate<String> longStr = s -> s.length() > 5;
    // Predicate<String> valid = notEmpty.and(longStr);
    //
    // var items = List.of("hi", "hello world", "", "spring", "bootcamp");
    // items.stream()
    // .filter(valid)
    // .forEach(System.out::println);
    // Salida esperada: hello world
    // bootcamp

    // ============================================
    // PASO 7: Referencias a métodos
    // ============================================
    // Reemplaza cada lambda por su referencia equivalente (::)
    // Descomenta Y ajusta las siguientes líneas:

    // Function<String, String> trim1 = s -> s.trim(); // reemplazar
    // Function<String, String> trim2 = String::trim; // correcto ✅
    //
    // Function<String, Integer> len1 = s -> s.length(); // reemplazar
    // Function<String, Integer> len2 = String::length; // correcto ✅
    //
    // Consumer<Object> print1 = o -> System.out.println(o); // reemplazar
    // Consumer<Object> print2 = System.out::println; // correcto ✅
    //
    // System.out.println(trim2.apply(" spaces ")); // spaces
    // System.out.println(len2.apply("Spring")); // 6
    // print2.accept("Done!"); // Done!
  }
}
