package lk.gedaratama.backendserver.jwt;

import lk.gedaratama.backendserver.model.User;
import lk.gedaratama.backendserver.repository.UserRepository;
import lk.gedaratama.backendserver.resource.UserCustomDetail;
import lk.gedaratama.backendserver.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *  @author Sashini Tharuka on 5/31/2020.
 */


@Service
public class JwtUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public JwtUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserResource getUserResource(User user) {
        UserResource userResource = new UserResource();
        userResource.setUsername(user.getUsername());
        userResource.setPassword(user.getPassword());
        userResource.setUuid(user.getUuid());
        userResource.setEmail(user.getEmail());
        userResource.setRole(user.getRole());
        return userResource;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User byEmailAndActive = userRepository.findByEmail(email);
        if (byEmailAndActive == null) {
            throw new UsernameNotFoundException("Email not found.");
        }
        return new UserCustomDetail(getUserResource(byEmailAndActive));
    }
}
