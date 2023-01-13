package com.rezaali121.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.rezaali121.school.view.ModuleView;
import com.rezaali121.school.view.UserView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @JsonView({UserView.class , ModuleView.class})
    private Integer id;
    @JsonView({UserView.class , ModuleView.class})
    @Column(unique = true , nullable = false)
    private String email;
    private String password;

    /*
     @ManyToOne
    @JsonView({UserView.class}) // when we get a module we dont need to get the roles because it is already there
    private Role role;
    @JsonView({UserView.class , ModuleView.class})
    private boolean admin;
    */

    // fetch by default is lazy that means it will not load the many to many data but here we changed it and now it will load all roles
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonView(UserView.class)
    private Set<Role> roleList = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "user_module",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "module_id")
    )
    @JsonView(UserView.class)
    private Set<Module> moduleList = new HashSet<>();

}
