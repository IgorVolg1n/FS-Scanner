package org.volgin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.volgin.service.ScanService;

@SpringBootApplication
public class ScannerMain implements ApplicationRunner {

    private final ScanService scanService;
    Logger logger = LoggerFactory.getLogger(ScannerMain.class);

    @Autowired
    public ScannerMain(ScanService scanService) {
        this.scanService = scanService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ScannerMain.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        scanService.clearScannedFiles();

        String[] sourceArgs = args.getSourceArgs();
        if(sourceArgs.length!=0){
            logger.info(String.format("Initial scan from the root: %s", sourceArgs[0]));
            scanService.scanPath(sourceArgs[0]);
        }
    }
}