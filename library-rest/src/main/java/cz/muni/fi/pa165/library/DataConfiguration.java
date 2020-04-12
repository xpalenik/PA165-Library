package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Role;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.repositories.BookRepository;
import cz.muni.fi.pa165.library.repositories.RoleRepository;
import cz.muni.fi.pa165.library.repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 12.04.2020
 */
@Configuration
public class DataConfiguration {

    @Bean
    public ApplicationRunner bookInitializer(BookRepository bookRepository) {
        return args -> bookRepository.saveAll(List.of(
                new Book("Animal Farm", "George Orwell"),
                new Book("1984", "George Orwell"),
                new Book("Ostře sledované vlaky", "Bohumil Hrabal")
        ));
    }

    @Bean
    public ApplicationRunner userInitializer(UserRepository userRepository, RoleRepository roleRepository) {
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");
        roleRepository.saveAll(List.of(
                roleUser,
                roleAdmin
        ));
        return args -> userRepository.saveAll(List.of(
                new User("John", "Smith", "john.smith@email.cz", "john1234", Collections.singletonList(roleUser)),
                new User("Peter", "Griffin", "peter.griffin@gmail.com", "password", List.of(roleUser, roleAdmin))
        ));
    }
}
