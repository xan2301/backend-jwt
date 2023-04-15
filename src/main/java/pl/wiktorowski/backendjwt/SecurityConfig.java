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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.wiktorowski.backendjwt.user.User;
import pl.wiktorowski.backendjwt.user.UserRepo;

@Configuration

public class SecurityConfig {

    private UserRepo userRepo;
    private JwtTokenFilter jwtTokenFilter;

    public SecurityConfig(UserRepo userRepo, JwtTokenFilter jwtTokenFilter) {
        this.userRepo = userRepo;
        this.jwtTokenFilter = jwtTokenFilter;
    }










    // To refactor

    @EventListener(ApplicationReadyEvent.class)

    public void savaUser() {

        User user1 = new User("p.wiktorowski2@gmail.com", getBcryptPasswordEncoder().encode("qwerty"),"ROLE_USER");
        userRepo.save(user1);

        User user2 = new User("anna@gmail.com", getBcryptPasswordEncoder().encode("qwerty"),"ROLE_ADMIN");
        userRepo.save(user2);



    }

    @Bean

    public UserDetailsService userDetailsService() {
        return username -> userRepo.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User with these email not found" + username));

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

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()

                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/hello").hasRole("ADMIN")

                .anyRequest().authenticated();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}




