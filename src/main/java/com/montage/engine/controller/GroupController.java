package com.montage.engine.controller;

import com.montage.engine.dto.GroupRequestDTO;
import com.montage.engine.model.Group;
import com.montage.engine.service.GroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class GroupController {
  @Autowired private GroupService groupService;

  @PostMapping
  public ResponseEntity<Group> createGroup(@RequestBody GroupRequestDTO request) {
    Group group = groupService.createGroup(request.getName(), request.getMemberUserIds());
    return ResponseEntity.ok(group);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Group>> getGroupsForUser(@PathVariable String userId) {
    return ResponseEntity.ok(groupService.getGroupsForUser(userId));
  }
}
