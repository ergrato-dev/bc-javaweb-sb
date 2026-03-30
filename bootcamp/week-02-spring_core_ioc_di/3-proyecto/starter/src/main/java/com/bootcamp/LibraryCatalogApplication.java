package com.bootcamp;

import com.bootcamp.config.LibraryProperties;
import com.bootcamp.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan  // enables LibraryProperties binding
public class LibraryCatalogApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraryCatalogApplication.class, args);
  }

  // Demo runner — called after all beans are initialized
  @Bean
  CommandLineRunner demo(BookService bookService, LibraryProperties props) {
    return args -> {
      System.out.println("=== " + props.name() + " ===");

      // TODO: (optional demo) Print all books sorted by title
      // bookService.getAllBooksSortedByTitle().forEach(b ->
      //   System.out.println(" - [" + b.category() + "] " + b.title() + " by " + b.author()));

      // TODO: (optional demo) Print catalog summary
      // var summary = bookService.getCatalogSummary();
      // System.out.println("Total: " + summary.totalBooks() +
      //   " | Available: " + summary.availableCount() +
      //   " | Categories: " + summary.categories());
    };
  }
}

