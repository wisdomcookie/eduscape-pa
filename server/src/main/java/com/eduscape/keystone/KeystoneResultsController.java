package com.eduscape.keystone;

import com.eduscape.school.SchoolData;
import com.eduscape.school.SchoolDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/keystone_results")
public class KeystoneResultsController {

    @Autowired
    private SchoolDataRepository schoolDataRepository;

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<SchoolData>> getAll() {
        return new ResponseEntity<>(schoolDataRepository.findAll(), HttpStatus.OK);
    }
}
