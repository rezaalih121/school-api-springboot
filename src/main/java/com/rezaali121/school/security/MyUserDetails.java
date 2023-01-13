package com.rezaali121.school.security;

import com.rezaali121.school.model.Role;
import com.rezaali121.school.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class MyUserDetails implements UserDetails {

    private User user;

    public MyUserDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authoritiesList = new ArrayList<>();
        for(Role role : this.user.getRoleList()){
            authoritiesList.add(
                    new SimpleGrantedAuthority(role.getName())
            );
        }
        authoritiesList.add(
                new SimpleGrantedAuthority("ROLE_USER")
        );


        // ------------------ developed with Many To One getRole() and with just one boolean isAdmin
//        Collection<GrantedAuthority> authoritiesList = new ArrayList<>();
//        authoritiesList.add(
//                new SimpleGrantedAuthority(
//                        //user.getRole() == null ? "ROLE_USER" : user.getRole().getName()
//                        user.isAdmin() ? "ROLE_ADMIN" : "ROLE_USER"
//                )
//        );

        return authoritiesList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
