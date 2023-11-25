package com.kidoApp.kidoApp.repository;
import com.kidoApp.kidoApp.model.Hospital;
import com.kidoApp.kidoApp.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent,Long> {
    Parent findByPhoneNoContaining(String phoneNumber);
    boolean existsByPhoneNo(String phoneNumber);
    Parent findByPhoneNo(String phoneNo);

    Parent findParentByParentId(Long id);

}

