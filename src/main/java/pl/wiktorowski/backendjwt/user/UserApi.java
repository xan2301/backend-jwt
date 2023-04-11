package pl.wiktorowski.backendjwt.user;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    public String getJwt(@RequestBody AuthRequest authRequest){


        Authentication authenticate = authorizationManager.authenticate(

                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        User user = (User) authenticate.getPrincipal();

        System.out.println(user);





        return "";
    }

}
