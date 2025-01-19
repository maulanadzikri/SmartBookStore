package com.smartbookstore.server.services.impl;

import com.smartbookstore.server.model.entity.Author;
import com.smartbookstore.server.repositories.AuthorRepository;
import com.smartbookstore.server.services.GenericService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthorService implements GenericService<Author, Integer> {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAll(){
        return authorRepository.findAll();
    }

    @Override
    public Author getById(Integer id) {
        return authorRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found!!!")
            );
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(Integer id, Author author) {
        getById(id);
        author.setId(id);
        return authorRepository.save(author);
    }

    @Override
    public Author delete(Integer id) {
        Author author = getById(id);
        authorRepository.delete(author);
        return author;
    }
}
