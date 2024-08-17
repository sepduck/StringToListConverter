package com.converttomapper.service;

import com.converttomapper.dto.GroupDTO;
import com.converttomapper.dto.UserDTO;
import com.converttomapper.model.Groups;
import com.converttomapper.model.Users;
import com.converttomapper.repository.GroupRepository;
import com.converttomapper.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;

    public Groups saveGroup(Groups group, Long userId) {
        // Fetch existing group by ID
        Optional<Groups> existingGroup = groupRepository.findById(group.getGid());

        if (existingGroup.isPresent()) {
            Groups existing = existingGroup.get();

            // Get the current list of user IDs
            List<Long> uidList = existing.getUid();

            // Add the new userId if it's not already in the list
            if (!uidList.contains(userId)) {
                uidList.add(userId);
                existing.setUid(uidList);
            }

            // Save the updated group
            return groupRepository.save(existing);
        } else {
            // If the group doesn't exist, create a new one
            List<Long> uidList = group.getUid();

            if (uidList == null) {
                uidList = new ArrayList<>();
            }

            if (!uidList.contains(userId)) {
                uidList.add(userId);
            }

            group.setUid(uidList);
            return groupRepository.save(group);
        }
    }


    public Optional<Groups> getGroupById(Long groupId) {
        return groupRepository.findById(groupId);
    }

    public void deleteGroupById(Long groupId, Long userId) {
        Optional<Groups> existingGroup = groupRepository.findById(groupId);

        if (existingGroup.isPresent()) {
            Groups group = existingGroup.get();

            List<Long> uidList = group.getUid();

            if (uidList != null && uidList.contains(userId)) {
                uidList.remove(userId);

                if (uidList.isEmpty()) {
                    groupRepository.deleteById(groupId);
                }

                group.setUid(uidList);
                groupRepository.save(group);
            } else {
                throw new EntityNotFoundException("Group not found");
            }
        } else {
            throw new EntityNotFoundException("Group not found");
        }
    }

    public List<UserDTO> getUserDetailsByGroup(Long groupId) {
        Optional<Groups> groupsOptional = groupRepository.findById(groupId);

        if (groupsOptional.isPresent()) {
            Groups group = groupsOptional.get();
            List<Long> userId = group.getUid();

            List<UserDTO> userDetail = new ArrayList<>();

            for (Long uid : userId) {
                Optional<Users> usersOptional = userRepository.findById(uid);
                if (usersOptional.isPresent()) {
                    Users user = usersOptional.get();
                    UserDTO userDTO = new UserDTO(
                            user.getUid(),
                            user.getName(),
                            user.getPrice()
                    );
                    userDetail.add(userDTO);
                }
            }
            return userDetail;
        } else {
            throw new RuntimeException("Group not found");
        }
    }

    public UserDTO getUserDetailsByGroupIdAndUserId(Long groupId, Long userId) {
        Optional<Groups> groupsOptional = groupRepository.findById(groupId);

        if (groupsOptional.isPresent()) {
            List<Long> userIds = groupsOptional.get().getUid();

            if (userIds.contains(userId)) {
                Optional<Users> usersOptional = userRepository.findById(userId);
                if (usersOptional.isPresent()) {
                    Users user = usersOptional.get();
                    return new UserDTO(
                            user.getUid(),
                            user.getName(),
                            user.getPrice()
                    );
                }
            }
        }
        return null;
    }
}
