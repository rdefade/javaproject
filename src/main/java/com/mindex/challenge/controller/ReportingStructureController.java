package com.mindex.challenge.controller;

import com.mindex.challenge.reports.ReportingStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @GetMapping("/reporting-structure/{id}")
    public ReportingStructure reportingStructure(@PathVariable String id){
        return null;
    }

}
