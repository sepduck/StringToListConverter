package com.converttomapper.dto;

import com.converttomapper.model.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class GroupDTO {
    private Long gid;
    private List<UserDTO> userDTOS;

    public GroupDTO(Long gid, List<UserDTO> users) {
        this.gid = gid;
        this.userDTOS = users;
    }
}
