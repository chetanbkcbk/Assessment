package com.tek.acs.controller;

import com.tek.acs.data.models.entity.User;
import com.tek.acs.service.UserService;
import com.tek.acs.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<ResponseStructure> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<ResponseStructure> findAssessmentByUserId(@PathVariable  String userid)
    {
        return userService.findAssessmentByUserId(userid);
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<ResponseStructure> deleteByUserId(@PathVariable  String userid)
    {
        return userService.deleteByUserId(userid);
    }

}


