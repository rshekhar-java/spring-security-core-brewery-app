package com.rs.springsecurity.domain.security;

import com.rs.springsecurity.domain.Customer;
import lombok.*;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * created by rs 3/23/2022.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Users implements UserDetails, CredentialsContainer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String username;
    private String password;


    @Singular
    @ManyToMany(cascade={CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
        joinColumns={@JoinColumn(name="USER_ID",referencedColumnName = "ID")},
            inverseJoinColumns ={@JoinColumn(name="ROLE_ID",referencedColumnName="ID")})
    private Set<Role> roles;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @Transient
    private Set<Authority> authorities;

    public Set<GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(Role::getAuthorities)
                .flatMap(Set::stream)
                .map(authority -> {return new SimpleGrantedAuthority(authority.getPermission());
                })
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Builder.Default
    private Boolean accountNonExpired = true;
    @Builder.Default
    private Boolean accountNonLocked = true;
    @Builder.Default
    private Boolean credentialsNonExpired = true;
    @Builder.Default
    private Boolean enabled = true;

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
