package org.hyeonjoon.climb_pot.domain.account.details;

import lombok.RequiredArgsConstructor;
import org.hyeonjoon.climb_pot.domain.account.user.domain.User;
import org.hyeonjoon.climb_pot.domain.account.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user, List.of());
    }
}
