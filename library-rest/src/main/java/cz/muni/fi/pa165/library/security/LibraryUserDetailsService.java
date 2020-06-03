package cz.muni.fi.pa165.library.security;

import cz.muni.fi.pa165.library.dto.UserDTO;
import cz.muni.fi.pa165.library.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Arrays;

/**
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 *
 * custom implementation of loading user-specific data during authentication
 */
public class LibraryUserDetailsService implements UserDetailsService {

    @Autowired
    private UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDTO user = userFacade.findByEmail(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if (user.isLibrarian()) {
            return new User(user.getEmail(), user.getPasswordHash(), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER")));
        }
        return new User(user.getEmail(), user.getPasswordHash(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

}
