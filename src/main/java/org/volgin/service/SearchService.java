package org.volgin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.volgin.dao.FilesRepository;

import java.util.List;

@Service
public class SearchService {

    private final FilesRepository filesRepository;

    @Autowired
    public SearchService(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    public List<String> getFilesByString(String pathPart) {
        return filesRepository.getFilesByString(pathPart);
    }
}
