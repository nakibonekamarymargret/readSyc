package com.MASOWAC.readSync.controllers;

import com.MASOWAC.readSync.repository.ReportsRepository;
import com.MASOWAC.readSync.services.ReportsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportsController {
    private final ReportsService reportsService;

    public ReportsController(ReportsService reportsService){
        this.reportsService= reportsService;
    }
}
