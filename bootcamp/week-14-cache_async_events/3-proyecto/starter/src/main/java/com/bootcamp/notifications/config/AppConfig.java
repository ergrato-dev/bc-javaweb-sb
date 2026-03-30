package com.bootcamp.notifications.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * AsyncConfig — Configuración de ThreadPool para @Async y Caffeine para cache
 *
 * Caffeine es el cache backend en memoria más recomendado para desarrollo:
 * - Soporta TTL (time-to-live)
 * - Soporta máximo de entradas
 * - Compatible con Spring Cache abstraction
 */
@Configuration
public class AppConfig {

    @Bean
    public Executor taskExecutor() {
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);    // threads siempre activos
        executor.setMaxPoolSize(20);    // máximo en pico de carga
        executor.setQueueCapacity(100); // cola antes de crear nuevos threads
        executor.setThreadNamePrefix("async-");
        executor.initialize();
        return executor;
    }

    @Bean
    public CacheManager cacheManager() {
        var manager = new CaffeineCacheManager();
        manager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES) // TTL de 5 minutos
                .maximumSize(500));                     // máximo 500 entradas
        return manager;
    }
}
