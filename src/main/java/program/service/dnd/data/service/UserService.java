package program.service.dnd.data.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import program.service.dnd.configuration.security.JwtUtils;
import program.service.dnd.data.entity.User;
import program.service.dnd.data.repository.UserRepository;
import program.service.dnd.util.UserDetailsImpl;

@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Autowired
    public UserService(UserRepository userRepository, JwtUtils jwtUtils){
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()
                        -> new UsernameNotFoundException
                        ("user Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public User getUserFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = jwtUtils.getUserNameFromJwtToken(token);
        return userRepository.findByUsername(username).get();
    }

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }
}