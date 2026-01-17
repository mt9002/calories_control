package calories_control.features.auth.domain;

import calories_control.features.auth.infra.user.Role;

public class User {

    private Long id;
    private String name;
    private String email;
    private String hash;
    private Role role;

    public User(String name, String email, String hash, Role role) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.hash = hash;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setPassword(String hash) {
        this.hash = hash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
