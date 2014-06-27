package co.uk.gymtracker.service;

import co.uk.gymtracker.exceptions.GymUserNotFoundException;
import co.uk.gymtracker.model.GymUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/04/14
 * @project GymTrackerApp
 */
@Service
public class GymUserLoginService extends AbstractGymService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.entry(username);

        GymUser gymUser = dao.findGymUser(username);

        if(gymUser == null) {
            throw new GymUserNotFoundException("User[ " + gymUser.getUsername() + " ] not found.");
        }

        Collection<? extends GrantedAuthority> authorities;

        if(gymUser.getRole().equals("ROLE_USER")) {
            System.out.println("has ROLE_USER");
            authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        } else {
            System.out.println("has ROLE_DENIED");
            authorities = AuthorityUtils.createAuthorityList("ROLE_DENIED");
        }

        logger.exit();

        return new User(gymUser.getUsername(), gymUser.getPassword(), authorities);
    }

}
