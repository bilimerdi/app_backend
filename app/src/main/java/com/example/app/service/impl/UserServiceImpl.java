package com.example.app.service.impl;

import com.example.app.dto.UserDto;
import com.example.app.entity.FileResponse;
import com.example.app.entity.User;
import com.example.app.repository.UserRepository;
import com.example.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private FileResponse fileResponse;

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

    @Override
    public List<UserDto> getTC(String TC) {
        List<User> users = userRepository.findByTC(TC);
        List<UserDto> userDtos = users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public List<UserDto> getName(String fullName) {
        List<User> users = userRepository.findByFullName(fullName);
        List<UserDto> userDtos = users.stream()
                .map(user -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public List<UserDto> getUsersSortedByDateDesc() {
        List<User> sortedUsers = userRepository.findAllByOrderByCreatedDateDesc();
        List<UserDto> sortedDtos = sortedUsers.stream()
                .map(user -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
        return sortedDtos;
    }

    @Override
    public List<UserDto> getUsersSortedByDateAsc() {
        List<User> sortedUsers = userRepository.findAllByOrderByCreatedDateAsc();
        List<UserDto> sortedDtos = sortedUsers.stream()
                .map(user -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
        return sortedDtos;
    }

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String name = String.valueOf(FileResponse.getCounter()) + ".jpg";
        String filePath = path + File.separator + name;

        File f = new File(path);
        if (!f.exists()){
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));
        return name;
    }


}
