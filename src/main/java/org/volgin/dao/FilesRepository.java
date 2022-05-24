package org.volgin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.volgin.model.ScannedFile;

import java.util.List;

@Repository
public interface FilesRepository extends JpaRepository<ScannedFile, Long> {

    @Query(value = "SELECT file_path FROM fs_scanner.files WHERE file_path LIKE %:pathPart% LIMIT 10", nativeQuery = true)
    List<String> getFilesByString(@Param("pathPart") String pathPart);
}
