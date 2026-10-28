package Controller;

import Service.UrlService;

public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    public String createUrl(String originalUrl) {
        return urlService.createUrl(originalUrl);
    }

    public String getOriginalUrl(String code) {
        return urlService.getOriginalUrl(code);
    }
}