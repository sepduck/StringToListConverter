package com.converttomapper.model;

import com.converttomapper.StringToListConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "group_user")
public class Groups {
    @Id
    private Long gid;
    @Convert(converter = StringToListConverter.class)
    private List<Long> uid;
}


