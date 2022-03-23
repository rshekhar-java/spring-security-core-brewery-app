package com.rs.springsecurity.domain.security;

import javax.persistence.*;
import java.util.Set;

/**
 * created by rs 3/23/2022.
 */
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String role;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;
}
