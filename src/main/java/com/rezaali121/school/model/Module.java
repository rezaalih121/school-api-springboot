package com.rezaali121.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.rezaali121.school.view.ModuleView;
import com.rezaali121.school.view.UserView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({UserView.class , ModuleView.class})
    private Integer id;
    @JsonView({UserView.class , ModuleView.class})
    private String name;
    @JsonView({UserView.class , ModuleView.class})
    private String description;

    // @JsonIgnore by using this we can prevent the infinite json serialisation loop but in this case you wont get user info when getting a module
    @ManyToMany(mappedBy = "moduleList")
    @JsonView( ModuleView.class) // in this way we are asking to show the user info only when I am asking for module info not when I am getting user info
    private Set<User> userList = new HashSet<>();

}
