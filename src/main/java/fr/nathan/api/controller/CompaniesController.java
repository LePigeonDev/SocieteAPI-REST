package fr.nathan.api.controller;

import fr.nathan.api.dto.CompanyResponseDTO;
import fr.nathan.api.dto.Patch.CompanyPatchRequestDTO;
import fr.nathan.api.dto.delete.CompanyDeleteResponseDTO;
import fr.nathan.api.service.CompanyService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This class is used to handle company-related requests.
@RestController
@RequestMapping("/v1/api/company")
public class CompaniesController {

    private final CompanyService companyService;

    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }


    // This method is used to get a company's information by its SIREN number.
    @GetMapping("/{siren}")
    public ResponseEntity<CompanyResponseDTO> getCompanyBySiren (
        @PathVariable Long siren
    ) {
        CompanyResponseDTO response = 
                companyService.getCompanyBySiren(siren);
        return ResponseEntity.ok(response);
    }


    // This method is used to get a company's information by its name.
    @GetMapping("/search/{name}")
    public ResponseEntity<CompanyResponseDTO> getCompanyByName (
        @PathVariable String name
    ) {
        CompanyResponseDTO response = 
                companyService.getCompanyByName(name);
        return ResponseEntity.ok(response);
    }


    // This method is used to update a company's information by its SIREN number.
    @PatchMapping("/{siren}")
    public ResponseEntity<CompanyResponseDTO> patchCompany(
            @PathVariable Long siren,
            @RequestBody CompanyPatchRequestDTO request
    ) {

        CompanyResponseDTO response =
                companyService.patchCompany(siren, request);

        return ResponseEntity.ok(response);
    }


    // This method is used to delete a company by its SIREN number.
    @DeleteMapping("/{siren}")
    public ResponseEntity<CompanyDeleteResponseDTO> deleteCompany(
            @PathVariable Long siren
    ) {
            return ResponseEntity.ok(
                companyService.deleteCompany(siren)
            );
    }

}