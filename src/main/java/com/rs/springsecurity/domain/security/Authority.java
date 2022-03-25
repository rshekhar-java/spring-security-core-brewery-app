package com.rs.springsecurity.domain.security;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * created by rs 3/23/2022.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String permission;

    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles;
}
