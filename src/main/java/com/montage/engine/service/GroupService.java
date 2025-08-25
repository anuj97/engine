package com.montage.engine.service;

import com.montage.engine.model.Group;
import com.montage.engine.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public Group createGroup(String name, List<String> memberUserIds) {
        Group group = new Group(null, name, memberUserIds);
        return groupRepository.save(group);
    }

    public List<Group> getGroupsForUser(String userId) {
        return groupRepository.findAll().stream()
                .filter(g -> g.getMemberUserIds().contains(userId))
                .toList();
    }
}
