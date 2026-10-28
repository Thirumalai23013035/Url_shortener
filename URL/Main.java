import Controller.UrlController;
import Repository.UrlRepository;
import Service.UrlShortCodeGenerator;
import Service.UrlService;

public class Main {

    public static void main(String[] args) {

        
        UrlRepository repository = new UrlRepository();


        UrlShortCodeGenerator generator = new UrlShortCodeGenerator();


        UrlService service = new UrlService(
                repository,
                generator
        );


        UrlController controller = new UrlController(service);

        
        String originalUrl =
                "https://example.com/products/123";

        String shortUrl = controller.createUrl(originalUrl);

        System.out.println("Original URL: " + originalUrl);
        System.out.println("Short URL: " + shortUrl);

        
        String code = shortUrl.substring(
                "https://short.ly/".length()
        );


        String retrievedUrl = controller.getOriginalUrl(code);

        System.out.println("Redirect URL: " + retrievedUrl);
    }
}