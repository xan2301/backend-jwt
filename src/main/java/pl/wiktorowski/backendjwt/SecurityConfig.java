package pl.wiktorowski.backendjwt;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.wiktorowski.backendjwt.user.User;
import pl.wiktorowski.backendjwt.user.UserRepo;

@Configuration

public class SecurityConfig {

    private UserRepo userRepo;

    public SecurityConfig(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    // To refactor

    @EventListener(ApplicationReadyEvent.class)

    public void savaUser() {

        User user = new User("p.wiktorowski2@gmail.com", getBcryptPasswordEncoder().encode("qwerty"));
        userRepo.save(user);

    }

    @Bean

    public UserDetailsService userDetailsService()

    {
        return username -> userRepo.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User with these email not found"));

    }


    @Bean

    public PasswordEncoder getBcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean

    public AuthenticationManager authorizationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeRequests((autz) -> autz

                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}




