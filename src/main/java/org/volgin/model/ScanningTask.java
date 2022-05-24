package org.volgin.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.volgin.dao.FilesRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class ScanningTask implements Runnable {

    ExecutorService executorService;
    File[] filesToScan;
    FilesRepository filesRepository;
    Logger logger = LoggerFactory.getLogger(ScanningTask.class);

    public ScanningTask(ExecutorService executorService, File[] filesToScan, FilesRepository filesRepository) {
        this.executorService = executorService;
        this.filesToScan = filesToScan;
        this.filesRepository = filesRepository;
    }

    @Override
    public void run() {
        logger.debug("Scanning files:");
        for (File file : filesToScan) {
            logger.debug(file.getAbsolutePath());
        }

        List<ScannedFile> scannedFiles = new ArrayList<>();
        for (File file : filesToScan) {
            scannedFiles.add(new ScannedFile(file.getAbsolutePath(), file.isDirectory()));

            if (file.isDirectory()) {
                executorService.submit(new ScanningTask(executorService, file.listFiles(), filesRepository));
            }
        }
        logger.debug("Saving...");
        filesRepository.saveAll(scannedFiles);
    }
}
