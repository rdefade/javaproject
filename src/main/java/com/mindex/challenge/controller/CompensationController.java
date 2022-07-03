package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CompensationController {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    @PostMapping("/compensation")
    public ResponseEntity<Compensation> create(@RequestBody Compensation compensation){
        LOG.debug("Create Compensation for: " + compensation.getEmployee().getEmployeeId());

        try {
            Compensation newCompensation = compensationService.create(compensation);
            return ResponseEntity.status(HttpStatus.CREATED).body(compensation);
        }
        catch(Exception ex) {
            //Need to implement better exception handing
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
        }

    }

    @GetMapping("/compensation/{id}")
    public ResponseEntity<Compensation> getCompensation(@PathVariable String id) {
        LOG.debug("Retrieve Compensation for: " + id);
        Compensation compensation = compensationService.read(id);
        if(compensation != null){
            return ResponseEntity.status(HttpStatus.OK).body(compensation);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
