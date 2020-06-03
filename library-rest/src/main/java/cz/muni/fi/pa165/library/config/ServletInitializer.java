package cz.muni.fi.pa165.library.config;

import cz.muni.fi.pa165.library.LibraryApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Martin Páleník 359817
 * */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LibraryApplication.class);
    }

}
