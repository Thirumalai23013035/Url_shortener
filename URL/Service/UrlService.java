package Service;

import model.UrlMapping;
import Repository.UrlRepository;

import java.net.URI;
import java.time.Instant;

public class UrlService {

    private final UrlRepository repository;
    private final UrlShortCodeGenerator generator;

    public UrlService(UrlRepository repository,
                      UrlShortCodeGenerator generator) {
        this.repository = repository;
        this.generator = generator;
    }

    public String createUrl(String originalUrl) {

        
        validateUrl(originalUrl);

        
        for (int attempt = 0; attempt < 5; attempt++) {

            String code = generator.generateCode();

            
            UrlMapping mapping = new UrlMapping();

            mapping.setShortCode(code);
            mapping.setOriginalUrl(originalUrl);
            mapping.setCreatedAt(Instant.now());
            mapping.setExpiresAt(null);

            
            try {
                repository.save(mapping);

            
                return "https://short.ly/" + code;

            } catch (IllegalArgumentException e) {
                
            }
        }

        throw new IllegalStateException(
                "Unable to generate a unique short code");
    }

    public String getOriginalUrl(String code) {

        UrlMapping mapping = repository.findByShortCode(code);

        if (mapping == null) {
            throw new IllegalArgumentException(
                    "Short URL not found");
        }

        if (mapping.isExpired()) {
            throw new IllegalStateException(
                    "Short URL has expired");
        }

        String url = mapping.getOriginalUrl();

        return url;
    }

    private void validateUrl(String url) {

        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException(
                    "URL cannot be empty");
        }

        try {
            URI uri = URI.create(url);
            String scheme = uri.getScheme();

            if (uri.getHost() == null
                    || scheme == null
                    || !(scheme.equalsIgnoreCase("http")
                    || scheme.equalsIgnoreCase("https"))) {

                throw new IllegalArgumentException(
                        "Enter a valid HTTP or HTTPS URL");
            }

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid URL", e);
        }
    }
}