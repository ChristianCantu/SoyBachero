package baches.jperez.soy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by crimelabpc4 on 10/03/15.
 */
public class token {

    private String token;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The token
     */
    public String getToken() {
        return  token ;
    }

    /**
     *
     * @param token
     * The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}