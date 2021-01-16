package ru.mrSlyRedFox.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mrSlyRedFox.persist.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
