package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.Entities.AccountEntity;
import com.sintraqos.portfolioproject.Repositories.AccountRepository;
import com.sintraqos.portfolioproject.Statics.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebService implements UserDetailsService{

    @Autowired
    private AccountRepository accountRepository; // Injects the UserRepo for accessing user data

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Pages
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/register")
                        .permitAll()
                )

//                .csrf(AbstractHttpConfigurer::disable)
//                .httpBasic(Customizer.withDefaults())
//                // Authorize
//                .authorizeHttpRequests(
//                        authorizeRequest -> authorizeRequest
//                                .requestMatchers("/test/user").hasAuthority("user")
//                                .requestMatchers("/test/admin").hasAuthority("admin")
//                                .anyRequest().permitAll()
//                ).formLogin(Customizer.withDefaults())

                // Logout
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("test")
                        .password("test")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieves user details by username from the database
        return accountRepository.findByUsername(username);
    }

    public String create(String username, String eMail, String password) {
        // Encodes the password and creates a new User object

        Console.writeLine("Incoming Register!");

        AccountEntity user = (AccountEntity) User.builder()
                .username(username)
                //.password(new BCryptPasswordEncoder().encode(password)) // Encrypts the password
                .password(password) // Encrypts the password
                .authorities("USER") // Assigns default authority
                .build();

        //TODO: read up on how to set custom variables for the UserDetails
        user.setEMail(eMail);

        // Saves the new user to the database
        accountRepository.save(user);

        return "Registration Was Successful!"; // Returns a success message
    }
}