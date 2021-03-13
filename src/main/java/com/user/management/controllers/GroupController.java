package com.user.management.controllers;

import com.user.management.models.Group;
import com.user.management.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/abm/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    //@CrossOrigin(origins = "http://localhost:1197/api/abm/groups/findAll")
    @GetMapping("/findAll")
    public ResponseEntity findAll(){
            return ResponseEntity.ok(groupService.findAll());
    }
}
