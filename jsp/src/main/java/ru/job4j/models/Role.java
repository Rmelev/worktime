package ru.job4j.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * roles.
 */
@Entity
@Table(name = "roles")
public class Role {
    /**
     * id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    /**
     * name.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Constructor.
     */
    public Role() {
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
     * @return - name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name - name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param o - object.
     * @return - true, if equals.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return id == role.id
                && Objects.equals(name, role.name);
    }

    /**
     * @return - hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    /**
     * @return - to string.
     */
    @Override
    public String toString() {
        return "Role{"
                + "id=" + id
                + ", name='" + name + '\'' + '}';
    }
}
