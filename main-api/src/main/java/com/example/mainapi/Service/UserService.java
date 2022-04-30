package com.example.mainapi.Service;

import com.example.mainapi.Model.UserModel;
import com.example.mainapi.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public List<UserModel> getAll(){
        return userRepository.findAll();
    }

    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user)
    {
        Optional<UserModel> userExist = userRepository.findByName(user.getName());

        if(!userExist.isPresent())
        {
            userRepository.save(user);
            return new ResponseEntity<UserModel>(HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    public ResponseEntity<UserModel> getByName(String nameUser){
        Optional<UserModel> user = userRepository.findByName(nameUser);

        if(user.isPresent()){
            return new ResponseEntity<UserModel>(user.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<UserModel> updateUserInfo(String name,@RequestBody UserModel user)
    {
        Optional<UserModel> oldUser = userRepository.findByName(name);

        if(oldUser.isPresent())
        {
            UserModel newUser = oldUser.get();
            newUser.setName(user.getName());
            newUser.setPassword(user.getPassword());
            userRepository.save(newUser);
            return new ResponseEntity<UserModel>(newUser, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }


    public ResponseEntity<UserModel> deleteUser(String name)
    {
        Optional<UserModel> user = userRepository.findByName(name);

        if(user.isPresent())
        {
            userRepository.delete(user.get());
            return new ResponseEntity<UserModel>(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
