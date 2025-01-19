package com.smartbookstore.server.services.impl;

import com.smartbookstore.server.model.entity.Publisher;
import com.smartbookstore.server.repositories.PublisherRepository;
import com.smartbookstore.server.services.GenericService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PublisherService implements GenericService<Publisher, Integer> {
    private PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository){
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Publisher> getAll(){
        return publisherRepository.findAll();
    }

    @Override
    public Publisher getById(Integer id) {
        return publisherRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Publisher not found!!!")
            );
    }

    @Override
    public Publisher create(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher update(Integer id, Publisher publisher) {
        getById(id);
        publisher.setId(id);
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher delete(Integer id) {
        Publisher publisher = getById(id);
        publisherRepository.delete(publisher);
        return publisher;
    }
}
