package org.example.Web2.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @CollectionTable(name="test", joinColumns = @JoinColumn(name="authorId"))
    private Long userId;

    private String userPassword;

    private String username;

    private Integer userRating;

    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "authorId")
    private Set<Test> tests;

    public Set<Role> getRoles() {
        return roles;
    }

    public User() { }

    public User(String user_name, String user_password, Integer user_rating, Role role) {
        this.userPassword = user_password;
        this.username = user_name;
        this.userRating = user_rating;
        this.roles = new HashSet<>();
        setRoles(role);
    }

    public void setRoles(Role role) {
        this.roles.add(role);
    }

    public void setRoles(Set<Role> role) {
        this.roles = role;
    }

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public boolean isBanned() {
        return roles.contains(Role.BANNED);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long user_id) {
        this.userId = user_id;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String user_password) {
        this.userPassword = user_password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user_name) {
        this.username = user_name;
    }

    public Integer getUserRating() {
        return userRating;
    }

    public void setUserRating(Integer user_rating) {
        this.userRating = user_rating;
    }

    public void upUserRating() {
        this.userRating++;
    }

    public void downUserRating() {
        this.userRating--;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return getUserPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !roles.contains(Role.BANNED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}