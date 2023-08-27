package ru.itm.restapp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(name = "roles")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Role implements GrantedAuthority {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    
    @Column(name = "description")
    private String description;
    
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id"), foreignKey = @ForeignKey(name = "FK_ROLES_USERS"),
            inverseJoinColumns = @JoinColumn(name = "user_id"), inverseForeignKey = @ForeignKey(name = "FK_USERS_ROLES"))
    private Set<User> users;
    
    public Role() {
    }
    
    public Role(Long id) {
        this.id = id;
    }
    
    public Role(Long id, String title) {
        this(id);
        this.title = title;
    }
    
    public Role(Long id, String title, String description, Set<User> users) {
        this(id, title);
        this.description = description;
        this.users = users;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Set<User> getUsers() {
        return users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }
    
    @Override
    public String getAuthority() {
        return getTitle();
    }
    
    @Override
    public String toString() {
        return "Role{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return id.equals(role.id);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
