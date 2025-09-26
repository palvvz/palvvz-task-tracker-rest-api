package palvvz;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "main")
public record MainProperties(
        String endpoint,
        int timeout,
        boolean enabled
) {}
