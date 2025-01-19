package com.smartbookstore.client.controllers.rest;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.smartbookstore.client.models.Publisher;
import com.smartbookstore.client.services.PublisherService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/publisher")
public class RestPublisherController {
    private PublisherService publisherService;

    @GetMapping
    public List<Publisher> getAll(){
        return publisherService.getAll();
    }

    @GetMapping("/{id}")
    public Publisher getById(@PathVariable Integer id){
        return publisherService.getById(id);
    }

    @PostMapping
    public Publisher create(@RequestBody Publisher publisher) {
        return publisherService.create(publisher);
    }

    @PutMapping("/{id}")
    public Publisher update(@PathVariable Integer id, @RequestBody Publisher publisher) {
        return publisherService.update(id, publisher);
    }

    @DeleteMapping("/{id}")
    public Publisher delete(@PathVariable Integer id) {
        return publisherService.delete(id);
    }
}
