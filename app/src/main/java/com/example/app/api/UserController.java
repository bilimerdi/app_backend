package com.example.app.api;

import com.example.app.dto.UserDto;
import com.example.app.entity.FileResponse;
import com.example.app.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.lang.IllegalArgumentException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Value("${project.image}")
    private String path;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(@RequestParam(value = "image", required = false)MultipartFile image) throws IOException {

        String fileName= null;


        if (image == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            fileName = this.userService.uploadImage(path, image);
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(fileName), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new FileResponse(fileName), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto resultUser = userService.createUser(user);
        return ResponseEntity.ok(resultUser);
    }

    @GetMapping("/getAll")
    public ResponseEntity <List<UserDto>> getUsers(){
        List<UserDto> users= userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        UserDto user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getByAssistant/{assistant}")
    public ResponseEntity <List<UserDto>> getAssistant(@PathVariable("assistant") String assistant){
        List<UserDto> user = userService.getAssistant(assistant);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/getByTc/{TC}")
    public ResponseEntity <List<UserDto>> getTc(@PathVariable("TC") String TC){
        List<UserDto> user = userService.getTC(TC);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getByFullname/{fullName}")
    public ResponseEntity <List<UserDto>> getName(@PathVariable("fullName") String fullName){
        List<UserDto> user = userService.getName(fullName);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/sortByDateDesc")
    public ResponseEntity<List<UserDto>> getUsersSortedByDateDesc() {
        List<UserDto> userList = userService.getUsersSortedByDateDesc();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/sortByDateAsc")
    public ResponseEntity<List<UserDto>> getUsersSortedByDateAsc() {
        List<UserDto> userList = userService.getUsersSortedByDateAsc();
        return ResponseEntity.ok(userList);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id,@RequestBody  UserDto user){
        UserDto resultUser = userService.updateUser(id,user);
        return ResponseEntity.ok(resultUser);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id){
        Boolean status = userService.deleteUser(id);
        return ResponseEntity.ok(status);
    }


}
