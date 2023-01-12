package com.rezaali121.school.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.util.Date;

@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {
// here we use a datasource that is already exists thanks to spring boot
    @Autowired
    private DataSource dataSource;

    // comes from MyUserDetailsService
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* authentication in memory
        auth.inMemoryAuthentication()
                .withUser("rezaali121")
                .password("121121")
                .roles("USER")
                .and()
                .withUser("ADMIN")
                .password("121121")
                .roles("ADMINISTRATOR");

         */

        /* authentication using database QUERY

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, password , 1 FROM user WHERE email = ?")
                .authoritiesByUsernameQuery("SELECT email, COALESCE(name,'ROLE_USER') " +
                        " FROM user u " +
                        " LEFT JOIN role r ON u.role_id = r.id" +
                        " WHERE email = ?");

         */
        // authentication using model system
        auth.userDetailsService(myUserDetailsService);
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable().cors().and()
                .authorizeRequests()
                .antMatchers("/connection").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/redact/**").hasAnyRole("USER" , "ADMIN","EDITOR")
                .antMatchers("/**").hasAnyRole("USER" , "ADMIN","EDITOR");
                // removed form login to start developing JWT authentication
                //.and().formLogin();
    }

    // TDOO check if you can solve the problem of Youtube-clone with this function
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();

        //return (PasswordEncoder) Encryptors.text("121121", "q1w2e3r4t5y6u7i8");
        //return new BCryptPasswordEncoder();
    }

}
