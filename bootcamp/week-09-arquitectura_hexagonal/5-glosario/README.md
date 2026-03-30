# Glosario — Semana 09: Arquitectura Hexagonal

---

## A

**Adaptador Primario (Primary/Driving Adapter)**
Adaptador de entrada que inicia las interacciones con el dominio. Ejemplos: REST Controller, CLI, Tests. Implementa o llama a los puertos de entrada.

**Adaptador Secundario (Secondary/Driven Adapter)**
Adaptador de salida que el dominio dirige. Ejemplos: JPA Repository, Email Service, Cache. Implementa los puertos de salida.

---

## C

**Caso de Uso (Use Case)**
Representa una funcionalidad del sistema desde la perspectiva del usuario. En hexagonal, es una interfaz de puerto de entrada y su implementación en el paquete `application/usecase/`.

---

## D

**DDD (Domain-Driven Design)**
Metodología de diseño de software que centra el modelo en el dominio del negocio. Introduce conceptos como Entidades, Value Objects, Agregados, Repositorios y Servicios de Dominio.

**Dominio (Domain)**
Núcleo de la arquitectura hexagonal. Contiene las entidades, los Value Objects y las reglas de negocio. No depende de ningún framework ni tecnología externa.

---

## E

**Entidad de Dominio (Domain Entity)**
Objeto con identidad propia (ID) y ciclo de vida. Tiene comportamiento rico que expresa reglas de negocio. Diferente de la entidad JPA que solo mapea a la BD.

---

## H

**Hexagonal Architecture**
Propuesta por Alistair Cockburn ("Ports and Adapters"). Objetivo: el dominio sea independiente de cualquier tecnología. Todo lo que entra o sale del dominio pasa por una interfaz (puerto).

---

## P

**Puerto de Entrada (Input Port)**
Interfaz que define los casos de uso disponibles para el exterior. Los adaptadores primarios (Controller) llaman estos métodos.

**Puerto de Salida (Output Port)**
Interfaz que define lo que el dominio necesita del exterior (persistencia, servicios externos). Los adaptadores secundarios (JPA) implementan estos métodos.

---

## V

**Value Object**
Objeto sin identidad, definido por sus atributos. Inmutable. Ejemplos: `Money`, `Address`, `EmailAddress`. En Java 21, ideal implementarlo con `record`.
