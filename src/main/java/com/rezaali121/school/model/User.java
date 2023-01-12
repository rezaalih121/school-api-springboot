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

    @ManyToOne
    @JsonView({UserView.class}) // when we get a module we dont need to get the roles because it is already there
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "user_module",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "module_id")
    )
    @JsonView(UserView.class)
    private Set<Module> moduleList = new HashSet<>();
}
