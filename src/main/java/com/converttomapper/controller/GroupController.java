package com.converttomapper.controller;

import com.converttomapper.dto.UserDTO;
import com.converttomapper.model.Groups;
import com.converttomapper.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/{id}")
    public Groups addGroup(@RequestBody Groups groups, @PathVariable Long id) {
        return groupService.saveGroup(groups, id);
    }
    @GetMapping("detail/{id}")
    public ResponseEntity<?> getAllGroups(@PathVariable("id") Long id) {
        return ResponseEntity.ok(groupService.getUserDetailsByGroup(id));
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<Groups> getGroupById(@PathVariable Long groupId) {
        return groupService.getGroupById(groupId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{groupId}/{uid}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId, @PathVariable Long uid) {
        groupService.deleteGroupById(groupId, uid);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{groupId}/{userId}")
    public ResponseEntity<UserDTO> getUserDetailsByGroupIdAndUserId(@PathVariable Long groupId,
                                                                    @PathVariable Long userId) {
        UserDTO userDTO = groupService.getUserDetailsByGroupIdAndUserId(groupId, userId);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}
