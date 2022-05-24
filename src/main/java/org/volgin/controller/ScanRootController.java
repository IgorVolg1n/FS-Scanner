package org.volgin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.volgin.service.ScanService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/scan")
public class ScanRootController {

    private final ScanService scanService;

    @Autowired
    public ScanRootController(ScanService scanService) {
        this.scanService = scanService;
    }

    @PostMapping("/path")
    public String scanPath(@RequestBody String path) {
        return scanService.scanPath(path);
    }
}
