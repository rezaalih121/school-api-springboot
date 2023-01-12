package com.rezaali121.school.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.rezaali121.school.view.ModuleView;
import com.rezaali121.school.view.UserView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

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
}
