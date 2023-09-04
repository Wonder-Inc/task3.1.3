package ru.itm.restapp.dto;

import java.util.Set;

public class RoleDto {
    private Long id;
    private String title;
    private String description;
    private Set<Long> usersIds;
    
    public RoleDto() {
    }
    
    public RoleDto(Long id, String title, String description, Set<Long> usersIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.usersIds = usersIds;
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
    
    public Set<Long> getUsersIds() {
        return usersIds;
    }
    
    public void setUsersIds(Set<Long> usersIds) {
        this.usersIds = usersIds;
    }
    
    @Override
    public String toString() {
        return "RoleDto{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", usersIds=" + usersIds +
               '}';
    }
}
