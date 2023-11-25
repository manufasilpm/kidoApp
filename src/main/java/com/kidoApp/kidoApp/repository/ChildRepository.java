package com.kidoApp.kidoApp.repository;

import com.kidoApp.kidoApp.model.Child;
import com.kidoApp.kidoApp.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child,Long> {
    boolean existsByChildNameAndParent(String name, Parent parent);

    List<Child> findByParentParentIdAndAppointmentIsNotNull(Long parentId);


}
