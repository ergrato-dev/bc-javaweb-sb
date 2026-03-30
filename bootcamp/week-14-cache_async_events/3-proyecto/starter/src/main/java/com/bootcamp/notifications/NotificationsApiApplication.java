package com.bootcamp.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching // activa Spring Cache (@Cacheable, @CacheEvict, @CachePut)
@EnableAsync // activa @Async para métodos asíncronos
@EnableScheduling // activa @Scheduled para tareas periódicas
public class NotificationsApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(NotificationsApiApplication.class, args);
  }
}
