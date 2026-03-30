# Práctica 01 — Spring Cache con @Cacheable y @CacheEvict

## Objetivos

- Configurar Spring Cache con backend en memoria (dev)
- Aplicar `@Cacheable`, `@CacheEvict` y `@CachePut` en un servicio
- Verificar el comportamiento del caché con logs

## Instrucciones

### Paso 1: Habilitar el caché

Abre `starter/CacheApp.java` y descomenta la sección **PASO 1**.

Verás que `@EnableCaching` activa el soporte de caché en Spring Boot y el `application.yml` ya tiene `spring.cache.type: simple` configurado.

### Paso 2: Aplicar @Cacheable en findById

Descomenta la sección **PASO 2** en `starter/CacheApp.java`.

El log `"Consultando BD..."` debe aparecer SOLO la primera vez que llames a `findProduct(1L)`. Las siguientes llamadas con el mismo ID devuelven el resultado cacheado sin ejecutar el método.

### Paso 3: Invalidar el caché con @CacheEvict

Descomenta la sección **PASO 3**. Llama a `updateProduct(1L, ...)` y observa que la siguiente llamada a `findProduct(1L)` vuelve a ejecutar el método (el caché fue invalidado).

### Paso 4: Actualizar el caché con @CachePut

Descomenta la sección **PASO 4**. A diferencia de `@Cacheable`, `@CachePut` siempre ejecuta el método pero actualiza la entrada en caché.

## Verificación

Ejecuta la aplicación y revisa los logs. Deberías ver:
```
Consultando BD para producto 1     ← SOLO aparece una vez por ID
Invalidando caché para producto 1  ← aparece al actualizar
```
