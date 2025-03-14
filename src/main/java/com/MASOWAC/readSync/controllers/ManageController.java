package com.MASOWAC.readSync.controllers;

import com.MASOWAC.readSync.services.ManageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manages")
public class ManageController {
    private final ManageService manageService;

    public ManageController(ManageService manageService){
        this.manageService =manageService;
    }
}
