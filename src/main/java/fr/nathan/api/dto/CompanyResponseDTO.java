package fr.nathan.api.dto;

public class CompanyResponseDTO {

    private Long siren;
    private String firstName;
    private String employeeRange;
    private String status;
    private String lastName;
    private String companyName;
    private String activityCode;


    public CompanyResponseDTO(
            Long siren,
            String firstName,
            String employeeRange,
            String status,
            String lastName,
            String companyName,
            String activityCode
    ) {
        this.siren = siren;
        this.firstName = firstName;
        this.employeeRange = employeeRange;
        this.status = status;
        this.lastName = lastName;
        this.companyName = companyName;
        this.activityCode = activityCode;
    }

    // Siren
    public Long getSiren() {
        return this.siren;
    }

    // firstName
    public String getFirstName() {
        return this.firstName;
    }

    // EmployeeRange
    public String getEmployeeRange() {
        return this.employeeRange;
    }

    // Status
    public String getStatus() {
        return this.status;
    }

    // LastName
    public String getLastName() {
        return this.lastName;
    }

    // CompanyName
    public String getCompanyName() {
        return  this.companyName;
    }

    // ActivityCode 
    public String getActivityCode() {
        return this.activityCode;
    }

}