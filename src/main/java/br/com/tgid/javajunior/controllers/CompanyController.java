package br.com.tgid.javajunior.controllers;

import br.com.tgid.javajunior.models.Company;
import br.com.tgid.javajunior.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/company")
public class CompanyController {
    @Autowired
    private CompanyService service;

    @PostMapping(value="/add")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company createdCompany = service.create(company);

        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

}
