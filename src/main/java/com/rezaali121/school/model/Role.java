package com.rezaali121.school.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.rezaali121.school.view.ModuleView;
import com.rezaali121.school.view.RoleView;
import com.rezaali121.school.view.UserView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
// ================= Manage by role list many to many ================ we do not need this class in last method
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({UserView.class , ModuleView.class}) // this means when I want to get user info , I need the its role's info
    private Integer id;
    @JsonView({UserView.class , ModuleView.class})
    private String name;



//    @ManyToMany(mappedBy = "roleList")
//    @JsonView( RoleView.class) // in this way we are asking to show the user info only when I am asking for module info not when I am getting user info
//    private Set<User> userList = new HashSet<>();
}
