package ru.itm.restapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itm.restapp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
