package com.standard.Jpa;

import com.standard.entity.Standard;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.beans.Transient;
import java.util.List;

@Repository
public interface StandardJpa extends JpaRepository<Standard, Long>, JpaSpecificationExecutor<Standard> {


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO standards (content, document_name, industry, standard_category) VALUES (?1,?2,?3,?4)",nativeQuery = true)
    void Save(String content, String documentName, String industry, String standardCategory);

    @Query(value = "select * from standards",nativeQuery = true)
    List<Standard> getAllStandards();



    @Query(value = "select * from standards where document_name like CONCAT('%',?1,'%')",nativeQuery = true)
    List<Standard> getStandardsByDocumentName(String name);

    @Query(value = "select * from standards where content like CONCAT('%',?1,'%')",nativeQuery = true)
    List<Standard> getStandardsByContent(String content);

    @Query(value = "select * from standards where industry like CONCAT('%',?1,'%')",nativeQuery = true)
    List<Standard> getStandardsByIndustry(String industry);

    @Query(value = "select * from standards where standard_category like CONCAT('%',?1,'%')",nativeQuery = true)
    List<Standard> getStandardsByStandardCategory(String category);


}
