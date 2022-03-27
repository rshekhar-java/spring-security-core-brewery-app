package com.rs.springsecurity.security.permissions;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * created by rs 3/27/2022.
 */
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('brewery.read')")
public @interface BreweryReadPermission {
}
