import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

/**
 * Práctica 01 — IoC y Constructor Injection
 *
 * Este es un proyecto Spring Boot simplificado en un solo archivo
 * para observar cómo Spring gestiona los beans.
 *
 * Instrucciones: descomenta cada sección en orden.
 */
@SpringBootApplication
public class LibraryApp implements CommandLineRunner {

  // Spring inyecta BookService en el constructor de LibraryApp
  // Descomenta cuando tengas STEPS 1 y 2 completos:
  // private final BookService bookService;
  //
  // public LibraryApp(BookService bookService) {
  // this.bookService = bookService;
  // }

  public static void main(String[] args) {
    SpringApplication.run(LibraryApp.class, args);
  }

  @Override
  public void run(String... args) {
    // Descomenta cuando tengas todos los pasos:
    // bookService.listBooks().forEach(System.out::println);
    System.out.println("App started — uncomment steps to see beans in action");
  }

  // ============================================
  // STEP 1: Repository bean
  // Descomenta las siguientes clases:
  // ============================================

  // @Repository
  // static class BookRepository {
  //
  // public BookRepository() {
  // System.out.println("BookRepository created");
  // }
  //
  // public List<String> findAll() {
  // return List.of("Effective Java", "Clean Code", "The Pragmatic Programmer");
  // }
  // }

  // ============================================
  // STEP 2: Service bean con constructor injection
  // Descomenta las siguientes clases:
  // ============================================

  // @Service
  // static class BookService {
  // private final BookRepository repository;
  //
  // // Constructor injection — Spring lo detecta automáticamente
  // public BookService(BookRepository repository) {
  // this.repository = repository;
  // System.out.println("BookService created with BookRepository");
  // }
  //
  // public List<String> listBooks() {
  // return repository.findAll();
  // }
  // }

  // ============================================
  // STEP 3: Bean de tercero via @Configuration + @Bean
  // Descomenta las siguientes clases:
  // ============================================

  // interface Formatter {
  // String format(String text);
  // }
  //
  // static class CsvFormatter implements Formatter {
  // @Override
  // public String format(String text) {
  // return "CSV: " + text.replace(" ", ",");
  // }
  // }
  //
  // @Configuration
  // static class AppConfig {
  // @Bean
  // public Formatter bookFormatter() {
  // System.out.println("Creating CsvFormatter bean");
  // return new CsvFormatter();
  // }
  // }

  // ============================================
  // STEP 4: Inyectar múltiples dependencias en Service
  // Reemplaza el BookService del STEP 2 con este:
  // ============================================

  // @Service
  // static class BookService {
  // private final BookRepository repository;
  // private final Formatter formatter;
  //
  // // Dos dependencias inyectadas — basta con agregar el parámetro
  // public BookService(BookRepository repository, Formatter formatter) {
  // this.repository = repository;
  // this.formatter = formatter;
  // System.out.println("BookService created with BookRepository and
  // CsvFormatter");
  // }
  //
  // public List<String> listBooks() {
  // return repository.findAll()
  // .stream()
  // .map(b -> formatter.format(b))
  // .toList();
  // }
  // }
}
