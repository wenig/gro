package org.battlehack.gro.jackson;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: aakhmerov
 * Date: 5/19/13
 * Time: 8:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class IgnoringObjectMapperProvider implements ContextResolver<ObjectMapper> {
    @Override
    public ObjectMapper getContext(Class<?> type) {

        ObjectMapper result = new ObjectMapper();

        result.registerModule(new Hibernate4Module());

//      TODO: move date format to global configuration
        result.setDateFormat(new SimpleDateFormat("dd.MM.yyyy:HH:mm:ss"));
        result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        result.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, false);

        result.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        result.configure(SerializationFeature.CLOSE_CLOSEABLE.WRITE_ENUMS_USING_TO_STRING, false);
        return result;
    }
}