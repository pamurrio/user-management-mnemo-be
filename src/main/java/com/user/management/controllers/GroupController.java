package com.user.management.controllers;

import com.user.management.dto.UserDTO;
import com.user.management.models.Group;
import com.user.management.models.User;
import com.user.management.services.GroupService;
import com.user.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/abm/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Group>> findAll(){
            return ResponseEntity.ok(groupService.findAll());
    }
}
