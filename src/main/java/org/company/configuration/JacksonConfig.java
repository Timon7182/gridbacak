package org.company.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class JacksonConfig implements ContextResolver<ObjectMapper> {

    private static ObjectMapper objectMapper;

    public JacksonConfig(){
        createObjectMapper();
    }

    private static void createObjectMapper() {
        objectMapper= new ObjectMapper()
                //how can we use it
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .registerModule(new JavaTimeModule());

    }
    public static synchronized ObjectMapper getObjectMapper(){
        if (objectMapper==null)
            createObjectMapper();
        return objectMapper;
    }

    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        return objectMapper;
    }
}