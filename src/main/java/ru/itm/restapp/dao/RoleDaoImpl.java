package ru.itm.restapp.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.itm.restapp.model.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {
    
    @Autowired
    EntityManager entityManager;
    
    @Override
    public Role findRoleByTitle(String title) {
        TypedQuery<Role> query = entityManager.createQuery(
                "select r from Role r where r.title = :title", Role.class);
        return query.setParameter("title", title).getSingleResult();
    }
    
    @Override
    public Set<Role> findRolesById(List<Long> ids) {
        TypedQuery<Role> query = entityManager.createQuery("select r from Role r where r.id in :role", Role.class);
        query.setParameter("role", ids);
        return new HashSet<>(query.getResultList());
    }
}
