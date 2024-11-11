//package demo.kun.uz.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//
//@Configuration
//public class SpringSecurityConfig_ {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        return authenticationProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeHttpRequests(auth -> auth
//                .requestMatchers("/auth/registration", "/auth/login").permitAll()
//                .requestMatchers("/profile").hasRole("ADMIN")
//                .requestMatchers("/profile/create", "/profile/update").hasRole("ADMIN")
//                .requestMatchers("/profile/detail/update").hasAnyRole("ADMIN", "USER", "MODERATOR")
//                .requestMatchers("/article-type/**", "/region/**", "/category/**").hasRole("ADMIN")
//                .requestMatchers("post","/post/**").permitAll()
//                .anyRequest().authenticated()
//        );
//
//        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.cors(Customizer.withDefaults());
//
//
//        http.cors(httpSecurityCorsConfigurer -> {
//            CorsConfiguration configuration=new CorsConfiguration();
//            configuration.setAllowedOriginPatterns(Arrays.asList("*"));
//            configuration.setAllowedMethods(Arrays.asList("*"));
//            configuration.setAllowedHeaders(Arrays.asList("*"));
//            UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
//
//            source.registerCorsConfiguration("/**",configuration);
//            httpSecurityCorsConfigurer.configurationSource(source);
//        });
//
//        return http.build();
//    }
//}
//
