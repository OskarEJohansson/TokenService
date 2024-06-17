package dev.oskarjohansson.projektarbetev2.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("secrets")
public record SecretsConfiguration(String dbUri ) {
}
