package org.caskol.warcraft_database.services;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.caskol.warcraft_database.models.Role;
import org.caskol.warcraft_database.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Setter
@Service
@Transactional(readOnly = true)
public class RoleService {
    private final RoleRepository roleRepository;
    public Optional<Role> getById(int id)
    {
        return roleRepository.findById(id);
    }
    @Transactional(readOnly = false)
    public void save(Role role)
    {
        roleRepository.save(role);
    }
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        roleRepository.deleteById(id);
    }
    public List<Role> getAll()
    {
        return roleRepository.findAll();
    }
}
