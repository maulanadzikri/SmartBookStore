package com.smartbookstore.server.controllers.impl;

import com.smartbookstore.server.controllers.GenericController;
import com.smartbookstore.server.model.entity.Publisher;
import com.smartbookstore.server.services.impl.PublisherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/publisher")
@PreAuthorize("hasRole('ADMIN')")
public class PublisherController implements GenericController<Publisher, Integer>{
    private PublisherService publisherService;
    
    public PublisherController(PublisherService publisherService){
        this.publisherService = publisherService;
    }

    @Override
    @GetMapping
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    public List<Publisher> getAll(){
        return publisherService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    public Publisher getById(@PathVariable Integer id){
        return publisherService.getById(id);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    public Publisher create(@RequestBody Publisher publisher){
        return publisherService.create(publisher);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    public Publisher update(@PathVariable Integer id, @RequestBody Publisher publisher){
        return publisherService.update(id, publisher);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    public Publisher delete(@PathVariable Integer id){
        return publisherService.delete(id);
    }
}
