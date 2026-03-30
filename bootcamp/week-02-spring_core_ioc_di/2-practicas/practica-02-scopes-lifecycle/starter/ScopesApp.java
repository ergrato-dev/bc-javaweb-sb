import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Práctica 02 — Bean Scopes y Ciclo de Vida
 *
 * Instrucciones: descomenta cada sección en orden.
 */
@SpringBootApplication
public class ScopesApp implements CommandLineRunner {

  private final ApplicationContext context;

  public ScopesApp(ApplicationContext context) {
    this.context = context;
  }

  public static void main(String[] args) {
    SpringApplication.run(ScopesApp.class, args);
  }

  @Override
  public void run(String... args) {

    // ============================================
    // STEP 1: Singleton — misma instancia
    // Descomenta las siguientes líneas:
    // ============================================

    // var svc1 = context.getBean(UserService.class);
    // var svc2 = context.getBean(UserService.class);
    // System.out.println("Singleton: same instance = " + (svc1 == svc2)); // true

    // ============================================
    // STEP 4: Prototype — instancia diferente cada vez
    // Descomenta las siguientes líneas:
    // ============================================

    // var rb1 = context.getBean(ReportBuilder.class);
    // var rb2 = context.getBean(ReportBuilder.class);
    // System.out.println("Prototype: same instance = " + (rb1 == rb2)); // false
  }

  // ============================================
  // STEP 2 + 3: Singleton con @PostConstruct y @PreDestroy
  // Descomenta las siguientes líneas:
  // ============================================

  // @Service
  // static class UserService {
  //
  // public UserService() {
  // System.out.println("[CONSTRUCTOR] UserService created");
  // }
  //
  // @PostConstruct
  // void init() {
  // // Se ejecuta DESPUÉS de constructor e inyección de dependencias
  // System.out.println("[INIT] UserService initialized — loading default data");
  // }
  //
  // @PreDestroy
  // void destroy() {
  // // Se ejecuta ANTES de que el container cierre
  // System.out.println("[DESTROY] UserService shutting down — releasing
  // resources");
  // }
  // }

  // ============================================
  // STEP 4: Prototype — nueva instancia por getBean()
  // Descomenta las siguientes líneas:
  // ============================================

  // @Component
  // @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  // static class ReportBuilder {
  //
  // public ReportBuilder() {
  // System.out.println("[CONSTRUCTOR] New ReportBuilder instance: " +
  // this.hashCode());
  // }
  //
  // public String build() {
  // return "Report from instance " + this.hashCode();
  // }
  // }
}
