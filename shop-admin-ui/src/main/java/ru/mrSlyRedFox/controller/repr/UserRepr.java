package ru.mrSlyRedFox.controller.repr;

import java.util.Set;

import javax.validation.constraints.NotEmpty;

import ru.mrSlyRedFox.persist.model.Role;
import ru.mrSlyRedFox.persist.model.User;

public class UserRepr {

    private Long user_id;

    @NotEmpty
    private String user_name;

    @NotEmpty
    private String password;

    private String first_name;

    private String last_name;

    private String email;

    private Set<Role> roles;

    public UserRepr() {
    }

    public UserRepr(User user) {
        this.user_id = user.getId();
        this.user_name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.first_name = user.getFirstName();
        this.last_name = user.getLast_name();
        this.roles = user.getRoles();
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserRepr{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
