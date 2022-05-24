package org.volgin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.volgin.dao.FilesRepository;
import org.volgin.exceptions.InvalidPathScanException;
import org.volgin.model.ApplicationProperties;
import org.volgin.model.ScannedFile;
import org.volgin.model.ScanningTask;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ScanService {

    private final FilesRepository filesRepository;
    private final ExecutorService executorService;
    Logger logger = LoggerFactory.getLogger(ScanService.class);

    @Autowired
    public ScanService(ApplicationProperties properties,
                       FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
        this.executorService = Executors.newFixedThreadPool(properties.getWorkerThreadsCount());
    }

    public String scanPath(String path) {
        if (!isValidPath(path)) {
            throw new InvalidPathScanException(path);
        }
        clearScannedFiles();

        logger.info(String.format("Started a scan from %s", path));
        File root = new File(path);
        filesRepository.save(new ScannedFile(root.getAbsolutePath(), root.isDirectory()));

        if (root.isDirectory()) {
            executorService.submit(new ScanningTask(executorService, root.listFiles(), filesRepository));
        }

        long count = filesRepository.count();
        boolean scanningFinished = false;
        while (!scanningFinished) {
            try {
                Thread.sleep(2000L);
                long newCount = filesRepository.count();
                if (count == newCount) {
                    scanningFinished = true;
                } else {
                    count = newCount;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return String.valueOf(count);
    }

    public void clearScannedFiles() {
        filesRepository.deleteAll();
        logger.info("Database cleaned up");
    }

    private boolean isValidPath(String path) {
        try {
            Path resultingPath = Paths.get(path);
            if (!resultingPath.isAbsolute()) {
                throw new InvalidPathScanException(String.format("Path \"%s\" should be absolute", path));
            }
        } catch (InvalidPathException | NullPointerException | InvalidPathScanException ex) {
            logger.error(String.format("Provided path \"%s\" is invalid: %s"), path, ex.getMessage());
            return false;
        }
        return true;
    }
}
