package org.caskol.warcraft_database.api.v1.services.implementations;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.caskol.warcraft_database.api.v1.dto.RoleDTO;
import org.caskol.warcraft_database.api.v1.repositories.RoleRepository;
import org.caskol.warcraft_database.api.v1.services.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Setter
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    public Optional<RoleDTO> getById(int id)
    {
        return null;
        //return roleRepository.findById(id);
    }
    @Transactional(readOnly = false)
    public void save(RoleDTO roleDTO)
    {
        //roleRepository.save(role);
    }
    @Transactional(readOnly = false)
    public void delete(int id)
    {
        roleRepository.deleteById(id);
    }
    public List<RoleDTO> getAll()
    {
        return null;
        //return roleRepository.findAll();
    }
}
