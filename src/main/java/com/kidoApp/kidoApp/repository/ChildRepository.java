package com.kidoApp.kidoApp.repository;

import com.kidoApp.kidoApp.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child,Long> {

    @Query("SELECT s  FROM Child s WHERE s.parent_id = :parent_id")
    List<Child> findByParentId(String parent_id);
}
