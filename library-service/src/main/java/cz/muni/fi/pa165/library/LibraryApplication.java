package cz.muni.fi.pa165.library;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import cz.muni.fi.pa165.library.facade.BookFacadeImpl;
import cz.muni.fi.pa165.library.facade.LoanFacadeImpl;
import cz.muni.fi.pa165.library.facade.UserFacadeImpl;
import cz.muni.fi.pa165.library.repositories.BookRepository;
import cz.muni.fi.pa165.library.repositories.SingleLoanRepository;
import cz.muni.fi.pa165.library.repositories.UserRepository;
import cz.muni.fi.pa165.library.services.BookService;
import cz.muni.fi.pa165.library.services.MappingService;
import cz.muni.fi.pa165.library.services.SingleLoanService;
import cz.muni.fi.pa165.library.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/** @author Martin Páleník 359817 */

@SpringBootApplication
@EnableJpaRepositories
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    public SingleLoanService singleLoanService(SingleLoanRepository singleLoanRepository) {
        return new SingleLoanService(singleLoanRepository);
    }

    @Bean
    public BookService bookService(BookRepository bookRepository) {
        return new BookService(bookRepository);
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public MappingService mappingService(Mapper dozer) {
        return new MappingService(dozer);
    }

    @Bean
    public Mapper mapper() {
        return DozerBeanMapperBuilder.buildDefault();
    }

    @Bean
    public UserFacadeImpl userFacadeImpl(MappingService mappingService, UserService userService) {
        return new UserFacadeImpl(mappingService, userService);
    }

    @Bean
    public BookFacadeImpl bookFacadeImpl(MappingService mappingService, BookService bookService) {
        return new BookFacadeImpl(mappingService, bookService);
    }

    @Bean
    public LoanFacadeImpl loanFacadeImpl(MappingService mappingService, SingleLoanService singleLoanService) {
        return new LoanFacadeImpl(mappingService, singleLoanService);
    }
}