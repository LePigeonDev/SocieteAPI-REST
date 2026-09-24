package fr.nathan.api.service;

import fr.nathan.api.entity.Company;
import fr.nathan.api.repository.CompanyRepository;
import fr.nathan.api.dto.CompanyResponseDTO;
import fr.nathan.api.dto.Patch.CompanyPatchRequestDTO;
import fr.nathan.api.dto.delete.CompanyDeleteResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyResponseDTO getCompanyBySiren(Long siren) {
        Company company = companyOK(siren);

        return new CompanyResponseDTO(
                company.getSiren(),
                company.getFirstName(),
                company.getEmployeeRange(),
                company.getStatus(),
                company.getLastName(),
                company.getCompanyName(),
                company.getActivityCode()
        );
    }

    public CompanyResponseDTO getCompanyByName(String name) {
        Company company = companyRepository.findByCompanyName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Company not found"
                ));

        return new CompanyResponseDTO(
                company.getSiren(),
                company.getFirstName(),
                company.getEmployeeRange(),
                company.getStatus(),
                company.getLastName(),
                company.getCompanyName(),
                company.getActivityCode()
        );
    }

    public CompanyDeleteResponseDTO deleteCompany(Long siren) {
        Company company = companyOK(siren);

        companyRepository.delete(company);

        return new CompanyDeleteResponseDTO(
            siren, 
            "Company deleted successfully"
        );
    }

    public CompanyResponseDTO patchCompany(
            Long siren,
            CompanyPatchRequestDTO request
    ) {

        Company company = companyOK(siren);

        if (request.getCompanyName() != null) {
            company.setCompanyName(request.getCompanyName());
        }

        if (request.getStatus() != null) {
            company.setStatus(request.getStatus());
        }

        if (request.getActivityCode() != null) {
            company.setActivityCode(request.getActivityCode());
        }

        if (request.getEmployeeRange() != null) {
            company.setEmployeeRange(request.getEmployeeRange());
        }

        Company updatedCompany = companyRepository.save(company);

        return new CompanyResponseDTO(
                updatedCompany.getSiren(),
                updatedCompany.getFirstName(),
                updatedCompany.getEmployeeRange(),
                updatedCompany.getStatus(),
                updatedCompany.getLastName(),
                updatedCompany.getCompanyName(),
                updatedCompany.getActivityCode()
        );
    }

    private Company companyOK(Long siren) {
        return companyRepository.findById(siren)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Company not found"
                ));
    }
}