package com.smartbookstore.server.services.impl;

import com.smartbookstore.server.model.entity.Role;
import com.smartbookstore.server.repositories.RoleRepository;
import com.smartbookstore.server.services.GenericService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RoleService implements GenericService<Role, Integer> {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    @Override
    public Role getById(Integer id) {
        return roleRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found!!!")
            );
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Integer id, Role role) {
        getById(id);
        role.setId(id);
        return roleRepository.save(role);
    }

    @Override
    public Role delete(Integer id) {
        Role role = getById(id);
        roleRepository.delete(role);
        return role;
    }
}
