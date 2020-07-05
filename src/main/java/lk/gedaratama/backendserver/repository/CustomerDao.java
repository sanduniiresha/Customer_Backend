package lk.gedaratama.backendserver.repository;

import lk.gedaratama.backendserver.model.CustomerDetail;
import lk.gedaratama.backendserver.model.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<CustomerDetail, Integer> {

    CustomerDetail findByEmail(String email);

    CustomerDetail findByUuid(String uuid);

}
