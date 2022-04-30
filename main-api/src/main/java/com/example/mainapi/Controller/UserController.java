package com.example.mainapi.Controller;

import com.example.mainapi.Model.UserModel;
import com.example.mainapi.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController

@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public List<UserModel> getAll(){
        return userService.getAll();
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody UserModel user)
    {
        userService.createUser(user);
    }

    @GetMapping("/getUser/{name}")
    public ResponseEntity<UserModel> getUserByName(@PathVariable(value = "name") String name)
    {
        return userService.getByName(name);
    }

    @PutMapping("/updateInfo/{name}")
    public ResponseEntity<UserModel> updateInfoUser(@PathVariable(value = "name") String name, @RequestBody UserModel user){
        return userService.updateUserInfo(name,user);
    }


    @DeleteMapping("/deleteUser/{name}")
    public ResponseEntity<UserModel> deleteUser(@PathVariable(value = "name") String name){
        return userService.deleteUser(name);
    }

}
