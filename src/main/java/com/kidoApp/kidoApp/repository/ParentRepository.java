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
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Parent p WHERE p.phone_no = :phoneNo")
    boolean existsByPhoneNo(@Param("phoneNo") String phoneNo);

    @Query("SELECT p FROM Parent p WHERE p.phone_no = :phoneNumber")
    Parent findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT p.parent_id FROM Parent p WHERE p.phone_no = :phoneNumber")
    String findParentIdByPhoneNumber(String phoneNumber);
}

