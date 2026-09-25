package fr.nathan.api.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

// This class is used to represent a company entity in the database.
@Entity 
@Table(name = "mytable")
public class Company {
    
    @Id
    @Column(name = "siren", nullable = false)
    private Long siren;

    @Column(name = "date_creation_unite_legale")
    private LocalDate createdAt;

    @Column(name = "prenom_unite_legale", length = 20)
    private String firstName;

    @Column(name = "tranche_effectifs_unite_legale", nullable = false, length = 2)
    private String employeeRange;

    @Column(name = "etat_administratif_unite_legale", nullable = false, length = 1) 
    private String status;

    @Column(name = "nom_unite_legale", length = 29)
    private String lastName;

    @Column(name = "denomination_unite_legale", length = 115)
    private String companyName;

    @Column(name = "activite_principale_unite_legale", length = 6)
    private String activityCode;

    public Company() {}

    public Company(Long siren, LocalDate createdAt, String firstName, 
                        String employeeRange, String status, String lastname, 
                            String companyName, String activityCode) 
    {
        this.siren          = siren;
        this.createdAt      = createdAt;
        this.firstName      = firstName;
        this.employeeRange  = employeeRange;
        this.status         = status;
        this.lastName       = lastname;
        this.companyName    = companyName;
        this.activityCode   = activityCode;
    }

    // Siren
    public Long getSiren() {
        return this.siren;
    }

    public void setSiren(long siren) {
        this.siren = siren;
    }

    // CreatedAt
    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

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