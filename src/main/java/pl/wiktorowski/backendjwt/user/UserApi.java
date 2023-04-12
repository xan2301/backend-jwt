package pl.wiktorowski.backendjwt.user;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserApi {

    private final AuthenticationManager authorizationManager;

    public UserApi(AuthenticationManager authorizationManager) {
        this.authorizationManager = authorizationManager;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> getJwt(@RequestBody AuthRequest authRequest) {


        try {
            Authentication authenticate = authorizationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            User user = (User) authenticate.getPrincipal();


            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withSubject(user.getUsername())
                    .withIssuer("Przemo")
                    .sign(algorithm);




            AuthResponse authResponse = new AuthResponse(user.getUsername(), token);
            return ResponseEntity.ok(authResponse);




        } catch (UsernameNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
    }

}
