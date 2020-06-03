package cz.muni.fi.pa165.library.sampleData;

import cz.muni.fi.pa165.library.services.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import javax.annotation.PostConstruct;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 *
 * Loads initial data to DB
 */
@Configuration
@Import(MappingService.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165.library.sampleData")
public class SampleDataLoader {

    @Autowired
    SampleData sampleData;

    @PostConstruct
    public void dataLoading() {
        sampleData.loadData();
    }
}
