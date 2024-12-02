package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.User;
import com.example.lab10.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        if(userService.addUser(user)){
        return ResponseEntity.status(200).body(new ApiResponse("User added"));}
        return ResponseEntity.status(400).body(new ApiResponse("Email is already exist"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id , @RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        char result = userService.updateUser(id,user).charAt(0);
        return switch (result){
            case 'U' -> ResponseEntity.status(400).body(new ApiResponse("User not found"));
            case 'T'-> ResponseEntity.status(200).body(new ApiResponse("User updated"));
            default -> ResponseEntity.status(400).body(new ApiResponse("Email already in use."));
        };


    }
@DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        if(userService.deleteUser(id)){
            return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User not found"));

    }


}//end class
