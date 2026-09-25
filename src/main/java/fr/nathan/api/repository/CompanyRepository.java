package fr.nathan.api.repository;

import fr.nathan.api.entity.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

// This interface is used to define the repository for the Company entity.
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByCompanyName(String companyName);

    List<Company> findByActivityCode(String activityCode);

}

