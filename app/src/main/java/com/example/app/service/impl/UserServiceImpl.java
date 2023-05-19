package com.example.app.service.impl;

import com.example.app.dto.UserDto;
import com.example.app.entity.User;
import com.example.app.repository.UserRepository;
import com.example.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto,User.class);
        user.setCreatedBy("Admin");
        user.setCreatedDate(new Date());
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> dtos = users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public UserDto getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return modelMapper.map(user.get(),UserDto.class);
        }
        return null;
    }

    @Override
    public UserDto updateUser(Long id, UserDto user) {
        Optional<User> resultUser = userRepository.findById(id);
        if(resultUser.isPresent()){
            resultUser.get().setFullName(user.getFullName());
            resultUser.get().setTC(user.getTC());
            resultUser.get().setDiagnosis(user.getDiagnosis());
            resultUser.get().setDetail(user.getDetail());
            resultUser.get().setUpdateAt(new Date());
            resultUser.get().setAssistant(user.getAssistant());
//            resultUser.get().setImage(user.getImage());
            resultUser.get().setUpdateBy("Admin");
            return modelMapper.map(userRepository.save(resultUser.get()),UserDto.class);
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

        @Override
        public List<UserDto> getAssistant(String assistant) {
        List<User> users = userRepository.findByAssistant(assistant);
        List<UserDto> userDtos = users.stream()
                .map(user -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
        return userDtos;
        }

}
