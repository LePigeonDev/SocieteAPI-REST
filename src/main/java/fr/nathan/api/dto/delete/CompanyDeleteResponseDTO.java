package fr.nathan.api.dto.delete;

public class CompanyDeleteResponseDTO {
    
    private Long siren;
    private String message;

    public CompanyDeleteResponseDTO(Long siren, String message)
    {
        this.siren = siren;
        this.message = message;
    }

    // Siren
    public Long getSiren() {
        return this.siren;
    }

    // message
    public String getMessage() {
        return this.message;
    }
}
