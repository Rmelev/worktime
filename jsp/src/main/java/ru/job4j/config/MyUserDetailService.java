package ru.job4j.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.repository.UserDAO;
import ru.job4j.models.Role;
import ru.job4j.models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UserDetailsService implementation.
 */
@Service
public class MyUserDetailService implements UserDetailsService {
    /**
     * user DAO.
     */
    private final UserDAO userDAO;

    /**
     * Constructor.
     * @param userDAO - user DAO.
     */
    @Autowired
    public MyUserDetailService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * load users parameters.
     * @param s - user's login.
     * @return - security formatted user.
     * @throws UsernameNotFoundException - exc.
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User client = userDAO.getByLogin(s);
        if (client == null) {
            throw new UsernameNotFoundException("Bad credentials");
        }
        List<GrantedAuthority> authorities = buildUserAuthority(Collections.singletonList(client.getRole()));
        return new org.springframework.security.core.userdetails.User(client.getLogin(),
                client.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }

    /**
     * Filling list of authorities with roles.
     * @param roles roles to fill.
     * @return list of authorities.
     */
    private List<GrantedAuthority> buildUserAuthority(List<Role> roles) {
        List<GrantedAuthority> setAuths = new ArrayList<>();
        for (Role role : roles) {
            System.out.println(role.getName());
            setAuths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return setAuths;
    }
}