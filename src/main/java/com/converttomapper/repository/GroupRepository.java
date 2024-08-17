package com.converttomapper.repository;

import com.converttomapper.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Groups, Long> {
    @Query(value = "SELECT * FROM group_user g WHERE :userId MEMBER OF g.uid", nativeQuery = true)
    List<Groups> findGroupsByUserId(@Param("userId") Long userId);
}
