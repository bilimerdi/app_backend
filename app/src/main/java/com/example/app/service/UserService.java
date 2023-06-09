package com.example.app.service;

import com.example.app.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    List<UserDto> getUsers();
    UserDto getUser(Long id);
    UserDto updateUser(Long id,UserDto user);
    Boolean deleteUser(Long id);

    List<UserDto> getAssistant(String assistant);
    List<UserDto> getTC(String TC);
    List<UserDto> getName(String fullName);
    List<UserDto> getUsersSortedByDateDesc();
    List<UserDto> getUsersSortedByDateAsc();
    String uploadImage(String path, MultipartFile file) throws IOException;


}
