package com.example.app.service.impl;

import com.example.app.entity.User;
import com.example.app.repository.UserRepository;
import com.example.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        user.setCreatedBy("Admin");
        user.setCreatedDate(new Date());
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    @Override
    public User updateUser(Long id, User user) {
        Optional<User> resultUser = userRepository.findById(id);
        if(resultUser.isPresent()){
            resultUser.get().setFullName(user.getFullName());
            resultUser.get().setTC(user.getTC());
            resultUser.get().setDiagnosis(user.getDiagnosis());
            resultUser.get().setDetail(user.getDetail());
            resultUser.get().setUpdateAt(new Date());
//            resultUser.get().setImage(user.getImage());
            resultUser.get().setUpdateBy("Admin");
            return userRepository.save(resultUser.get());
        }
        return null;
    }

    @Override
    public Boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
