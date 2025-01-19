package com.smartbookstore.server.services.impl;

import com.smartbookstore.server.model.entity.UserDetail;
import com.smartbookstore.server.repositories.UserDetailRepository;
import com.smartbookstore.server.services.GenericService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserDetailService implements GenericService<UserDetail, Integer> {
    private UserDetailRepository userDetailRepository;

    public UserDetailService(UserDetailRepository userDetailRepository){
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public List<UserDetail> getAll(){
        return userDetailRepository.findAll();
    }

    @Override
    public UserDetail getById(Integer id) {
        return userDetailRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "UserDetail not found!!!")
            );
    }

    @Override
    public UserDetail create(UserDetail userDetail) {
        return userDetailRepository.save(userDetail);
    }

    @Override
    public UserDetail update(Integer id, UserDetail userDetail) {
        getById(id);
        userDetail.setId(id);
        return userDetailRepository.save(userDetail);
    }

    @Override
    public UserDetail delete(Integer id) {
        UserDetail userDetail = getById(id);
        userDetailRepository.delete(userDetail);
        return userDetail;
    }
}
