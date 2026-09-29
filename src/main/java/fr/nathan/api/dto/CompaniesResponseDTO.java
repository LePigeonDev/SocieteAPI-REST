package fr.nathan.api.dto;

import java.util.List;

public class CompaniesResponseDTO {
    
    private List<CompanyResponseDTO> companies;

    public CompaniesResponseDTO(List<CompanyResponseDTO> companies) {
        this.companies = companies;
    }

    public List<CompanyResponseDTO> getCompanies() {
        return this.companies;
    }
}
