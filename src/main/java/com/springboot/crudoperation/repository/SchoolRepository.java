package com.springboot.crudoperation.repository;

import com.springboot.crudoperation.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School,Long> {
    Optional<School> findByIdAndIsDeleted(Long id, int isDeleted);

    @Query("SELECT s FROM School s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%',:searchText,'%')) OR LOWER(s.address) LIKE LOWER(CONCAT('%',:searchText,'%'))")
    List<School> findSchoolBySearchText(@Param("searchText") String searchText);

    @Query("SELECT s FROM School s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%',:searchText,'%')) OR LOWER(s.address) LIKE LOWER(CONCAT('%',:searchText,'%'))")
    Page<School> findSchoolBySearchTextWithPagination(@Param("searchText") String searchText, Pageable pageable);

    Page<School> findAll(Pageable pageable);
}
