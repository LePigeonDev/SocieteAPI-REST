package fr.nathan.api.dto.Patch;

public class CompanyPatchRequestDTO {

    private String firstName;
    private String employeeRange;
    private String status;
    private String lastName;
    private String companyName;
    private String activityCode;


    // firstName
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // EmployeeRange
    public String getEmployeeRange() {
        return this.employeeRange;
    }

    public void setEmployeeRange(String employeeRange) {
        this.employeeRange = employeeRange;
    }

    // Status
    public String getStatus() {
        return this.status;
    }

    public  void setStatus(String status) {
        this.status = status;
    }

    // LastName
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // CompanyName
    public String getCompanyName() {
        return  this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    // ActivityCode 
    public String getActivityCode() {
        return this.activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }
}