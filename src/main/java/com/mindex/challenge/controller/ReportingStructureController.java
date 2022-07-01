package com.mindex.challenge.controller;

import com.mindex.challenge.reports.ReportingStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportStructureController.class);

    @GetMapping("/reportingstructure/{id}")
    public ReportingStructure getReportingStructure(@PathVariable String id){
        return null;
    }

}
