package co.uk.gymtracker.service;

import co.uk.gymtracker.dao.GymUserDao;
import co.uk.gymtracker.model.GymUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/04/14
 * @project GymTrackerApp
 */
public class GymUserService implements UserDetailsService {

    @Autowired
    private GymUserDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        GymUser gymUser = dao.findGymUser(username);

        Collection<? extends GrantedAuthority> authorities = null;

        if(gymUser.getRole().equals("ROLE_USER")) {
            System.out.println("has ROLE_USER");
            authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        } else {
            System.out.println("has ROLE_DENIED");
            authorities = AuthorityUtils.createAuthorityList("ROLE_DENIED");
        }

        return new User(gymUser.getUsername(), gymUser.getPassword(), authorities);
    }

}
