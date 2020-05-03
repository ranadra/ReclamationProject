package tn.veganet.reclamation.security;
import com.sun.research.ws.wadl.HTTPMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import springfox.documentation.service.ApiKey;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
            // other public endpoints of your API may be appended to this array
    };
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN","USER").and()
                .withUser("user").password("{noop}1234").roles("USER");*/
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
        .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
            http.formLogin();
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/**").permitAll();

        http.authorizeRequests().antMatchers(HttpMethod.POST,"/login/**").permitAll().and();
            http.authorizeRequests().antMatchers(HttpMethod.GET,"/demandes/**").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.POST,"/addFonc/**").permitAll()
                    .antMatchers(HttpMethod.POST,"/register/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/users/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/fonctionnalites/**").permitAll()
                                .antMatchers(HttpMethod.GET,"/username/**").permitAll();


        http.authorizeRequests().antMatchers(HttpMethod.POST,"/add/**").permitAll()
                .antMatchers(HttpMethod.POST,"/upload/**").permitAll();
        ;
        http.authorizeRequests().
                antMatchers(AUTH_WHITELIST).permitAll();
                http.authorizeRequests().antMatchers("/error").permitAll();
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JWTAuthentificationFilter(authenticationManager())) ;
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
            }


}
