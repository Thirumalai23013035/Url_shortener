package Service;

import java.security.SecureRandom;

public class UrlShortCodeGenerator {

    private final static String Characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private final static int char_Length = 7;

    private final SecureRandom Random = new SecureRandom();

    public String generateCode()
    {
        StringBuilder code = new StringBuilder();

        for(int i=0;i<char_Length;i++)
        {
            int index = Random.nextInt(Characters.length());
            code.append(Characters.charAt(index));
        }

        return code.toString();
    }
    
}
