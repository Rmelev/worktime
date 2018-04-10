package ru.job4j.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

/**
 * car.
 */
@Entity
@Table(name = "users")
public class User {
    /**
     * id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    /**
     * login.
     */
    @Column(name = "login", nullable = false)
    private String login;
    /**
     * password.
     */
    @Column(name = "password", nullable = false)
    private String password;
    /**
     * email.
     */
    @Column(name = "email")
    private String email;

    /**
     * role.
     */
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    /**
     * default constructor.
     */
    public User() {
    }
    /**
     * @return - id.
     */
    public int getId() {
        return id;
    }
    /**
     * @param id - id.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return - login.
     */
    public String getLogin() {
        return login;
    }
    /**
     * @param login - login.
     */
    public void setLogin(String login) {
        this.login = login;
    }
    /**
     * @return - password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password - password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return - email.
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email - email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return - role.
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role - role.
     */
    public void setRole(Role role) {
        this.role = role;
    }


    /**
     * @param o - obj.
     * @return - true, if equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(email, user.email);
    }
    /**
     * @return - hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email);
    }
    /**
     * @return - string representation.
     */
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", email='" + email + '\''
                + ", role=" + role
                + '}';
    }
}
