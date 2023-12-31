package com.kidoApp.kidoApp.repository;

import com.kidoApp.kidoApp.model.Child;
import com.kidoApp.kidoApp.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChildRepository extends JpaRepository<Child,Long> {
    boolean existsByChildNameAndParent(String name, Parent parent);

    List<Child> findByParentParentIdAndAppointmentsIsNotNull(Long parentId);


    Integer countByParent_ParentId(Long parentId);


    Optional<Child> findByChildIdAndAppointmentsIdIsNotNull(Long childId);

    @Query("SELECT c.parent.email FROM Child c WHERE c.childId = :childId")
    String findParentEmailByChildId(@Param("childId") Long childId);
}
