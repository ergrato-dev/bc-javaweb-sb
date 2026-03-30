package com.bootcamp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Typed configuration for the Library Catalog application.
 *
 * Bound from application.yml prefix "library".
 */
@ConfigurationProperties(prefix = "library")
public record LibraryProperties(
        String name,
        int maxBooksPerUser,
        List<String> allowedCategories
) {}
