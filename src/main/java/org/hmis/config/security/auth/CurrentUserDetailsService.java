package org.hmis.config.security.auth;

import org.hmis.core.domain.User;
import org.hmis.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CurrentUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userService.getUserByUserName(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User with username=%s was not found", username)));
        return new CurrentUser(user);
    }
}
