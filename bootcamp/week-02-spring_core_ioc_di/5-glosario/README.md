# Glosario — Semana 02: Spring Core IoC/DI

Términos clave ordenados alfabéticamente.

---

## A

**ApplicationContext**
Interfaz principal del contenedor de Spring. Extiende `BeanFactory` con soporte para internacionalización, eventos, AOP y más. `AnnotationConfigApplicationContext` y `SpringApplication` lo implementan.

**@Autowired**
Anotación que indica a Spring que debe inyectar una dependencia. En métodos constructor con un único constructor, es opcional desde Spring 4.3. Preferir constructor injection sin `@Autowired`.

---

## B

**Bean**
Objeto gestionado por el contenedor Spring (ApplicationContext). Spring es responsable de su creación, wiring, ciclo de vida y destrucción.

**Bean Factory**
Contenedor básico de Spring que gestiona la creación y wiring de beans. `ApplicationContext` lo extiende con funcionalidad adicional.

**@Bean**
Anotación sobre un método en una clase `@Configuration`. El valor retornado se registra como bean en el ApplicationContext.

---

## C

**@Component**
Anotación base para declarar un bean genérico. Detectado automáticamente por component scan. Base de `@Service`, `@Repository`, `@Controller`.

**Component Scan**
Mecanismo por el que Spring descubre automáticamente clases anotadas con `@Component` y sus derivadas. `@SpringBootApplication` habilita el scan en el paquete base.

**@Configuration**
Clase que declara beans via métodos `@Bean`. El proxy CGLIB garantiza que las llamadas entre métodos `@Bean` retornen el mismo bean (singleton).

**@ConfigurationProperties**
Anotación que vincula propiedades del `application.yml` a campos de un POJO o record tipado. Más robusto que múltiples `@Value`.

**Constructor Injection**
Patrón de DI donde las dependencias se pasan como parámetros del constructor. Recomendado en Spring: permite campos `final`, fácil testing y dependencias explícitas.

---

## D

**Dependency Injection (DI)**
Mecanismo por el que el container entrega las dependencias a un objeto en lugar de que el objeto las instancie. Implementación concreta de IoC.

---

## I

**Inversion of Control (IoC)**
Principio de diseño donde el flujo de control se invierte: el container crea y gestiona los objetos en lugar de que el código lo haga. Spring es el IoC container más popular de Java.

---

## P

**@PostConstruct**
Anotación sobre un método que debe ejecutarse inmediatamente después de que Spring inyecte todas las dependencias del bean. Útil para inicialización.

**@PreDestroy**
Anotación sobre un método que se ejecuta justo antes de que el container destruya el bean. Útil para liberar recursos.

**Prototype Scope**
Escopo de bean donde Spring crea una nueva instancia cada vez que el bean es solicitado. Declarado con `@Scope("prototype")`.

**`@Profile`**
Anotación que activa un bean o configuración solo cuando el perfil especificado está activo (ej. `dev`, `prod`). Se activa con `spring.profiles.active`.

---

## R

**`@Repository`**
Especialización de `@Component` para la capa de acceso a datos. Activa la traducción automática de excepciones de acceso a datos a excepciones de Spring.

---

## S

**Singleton Scope**
Escopo predeterminado de Spring: una sola instancia del bean por ApplicationContext. Todos los que soliciten el bean reciben la misma referencia.

**`@Service`**
Especialización de `@Component` para la capa de negocio. Semánticamente indica que la clase contiene lógica de negocio.

**Stereotype Annotation**
Anotaciones que marcan una clase para que Spring la detecte: `@Component`, `@Service`, `@Repository`, `@Controller`. Todas derivan de `@Component`.

---

## V

**`@Value`**
Inyecta un valor de `application.yml` o variable de entorno en un campo. Sintaxis: `@Value("${property.key:defaultValue}")`.
