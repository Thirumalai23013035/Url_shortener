package Repository;

import java.util.HashMap;
import java.util.Map;

import model.UrlMapping;

public class UrlRepository {

    private final Map<String,UrlMapping>db = new HashMap<>();

    public void save(UrlMapping urlMapping)
    {
        String code = urlMapping.getShortCode();

        if(db.containsKey(code))
        {
            throw new IllegalArgumentException("Code already exists");
        }

        db.put(code,urlMapping);
    }


    public UrlMapping findByShortCode(String code)
    {
        return db.get(code);
    }

    
    
}
