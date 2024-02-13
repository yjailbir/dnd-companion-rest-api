package program.service.dnd.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import program.service.dnd.configuration.security.JwtUtils;
import program.service.dnd.data.dto.AuthRequestDto;
import program.service.dnd.data.dto.AuthResponseDto;
import program.service.dnd.data.entity.User;
import program.service.dnd.data.service.UserService;
import program.service.dnd.util.UserDetailsImpl;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            UserService userService,
            PasswordEncoder passwordEncoder,
            JwtUtils jwtUtils
    ){
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.encoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser
            (@RequestBody AuthRequestDto loginRequest) {

        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken
                        (loginRequest.getUsername(),
                                loginRequest.getPassword()));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl)
                authentication.getPrincipal();

        return ResponseEntity
                .ok(new AuthResponseDto(jwt, userDetails.getId(),
                        userDetails.getUsername()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser
            (@RequestBody AuthRequestDto signUpRequest) {

        if (userService.existsByUsername(signUpRequest
                .getUsername())) {

            Map<String, String> map = new HashMap<>();
            map.put("error", "username is already taken!");
            return ResponseEntity.badRequest().body(map);
        }

        // Create new user account
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));

        userService.save(user);

        Map<String, String> map = new HashMap<>();
        map.put("message", "user registered successfully");
        return ResponseEntity.ok(map);
    }
}