package com.montage.engine.controller;

import com.montage.engine.model.Group;
import com.montage.engine.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody GroupRequest request) {
        Group group = groupService.createGroup(request.getName(), request.getMemberUserIds());
        return ResponseEntity.ok(group);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Group>> getGroupsForUser(@PathVariable String userId) {
        return ResponseEntity.ok(groupService.getGroupsForUser(userId));
    }

    public static class GroupRequest {
        private String name;
        private List<String> memberUserIds;
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public List<String> getMemberUserIds() { return memberUserIds; }
        public void setMemberUserIds(List<String> memberUserIds) { this.memberUserIds = memberUserIds; }
    }
}
