package pl.polsl.take.footballleague;

import javax.print.attribute.standard.Media;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {
    public static final String DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_JSON+";charset=UTF-8";
}
