package com.smartbookstore.server.controllers.impl;

import com.smartbookstore.server.controllers.GenericController;
import com.smartbookstore.server.model.entity.UserDetail;
import com.smartbookstore.server.services.impl.UserDetailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/userdetail")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class UserDetailController implements GenericController<UserDetail, Integer>{
    private UserDetailService userdetailService;
    
    public UserDetailController(UserDetailService userdetailService){
        this.userdetailService = userdetailService;
    }

    @Override
    @GetMapping
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    public List<UserDetail> getAll(){
        return userdetailService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    public UserDetail getById(@PathVariable Integer id){
        return userdetailService.getById(id);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    public UserDetail create(@RequestBody UserDetail userdetail){
        return userdetailService.create(userdetail);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('UPDATE_ADMIN', 'UPDATE_USER')")
    public UserDetail update(@PathVariable Integer id, @RequestBody UserDetail userdetail){
        return userdetailService.update(id, userdetail);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    public UserDetail delete(@PathVariable Integer id){
        return userdetailService.delete(id);
    }
}
