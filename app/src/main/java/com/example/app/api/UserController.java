package com.example.app.api;

import com.example.app.dto.UserDto;
import com.example.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")


//@CrossOrigin(origins = "http://localhost:3000")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
