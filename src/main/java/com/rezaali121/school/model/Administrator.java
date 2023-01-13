package com.rezaali121.school.model;

import javax.persistence.Entity;

@Entity
public class Administrator extends User{

    private boolean superAdmin;

}
