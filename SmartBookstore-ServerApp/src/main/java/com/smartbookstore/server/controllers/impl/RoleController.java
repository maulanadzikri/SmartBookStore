package com.smartbookstore.server.controllers.impl;

import com.smartbookstore.server.controllers.GenericController;
import com.smartbookstore.server.model.entity.Role;
import com.smartbookstore.server.services.impl.RoleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController implements GenericController<Role, Integer>{
    private RoleService roleService;
    
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @Override
    @GetMapping
    public List<Role> getAll(){
        return roleService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Role getById(@PathVariable Integer id){
        return roleService.getById(id);
    }

    @Override
    @PostMapping
    public Role create(@RequestBody Role role){
        return roleService.create(role);
    }

    @Override
    @PutMapping("/{id}")
    public Role update(@PathVariable Integer id, @RequestBody Role role){
        return roleService.update(id, role);
    }

    @Override
    @DeleteMapping("/{id}")
    public Role delete(@PathVariable Integer id){
        return roleService.delete(id);
    }
}
