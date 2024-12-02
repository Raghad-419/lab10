package com.example.lab10.Service;

import com.example.lab10.Model.User;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Boolean addUser(User user){
        for(User user1: userRepository.findAll()){
            if(user1.getEmail().equals(user.getEmail())){
                return false;
            }
        }
        userRepository.save(user);
        return true;
    }

    public String updateUser(Integer id , User user){
        User oldUser = userRepository.getById(id);
        if(oldUser==null){
            return "User Not found";
        }

        for(User user1: userRepository.findAll()){
            if(user1.getEmail().equals(user.getEmail())){
                return "Exist";
            }
        }

        oldUser.setAge(user.getAge());
        oldUser.setEmail(user.getEmail());
        oldUser.setRole(user.getRole());
        oldUser.setPassword(user.getPassword());
        oldUser.setName(user.getName());

        userRepository.save(oldUser);
        return "True";

    }


    public Boolean deleteUser(Integer id){
        User user =userRepository.getById(id);
        if(user==null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }


}
