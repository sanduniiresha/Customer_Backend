package lk.gedaratama.backendserver.repository;

import lk.gedaratama.backendserver.model.DeliveryPersonDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kavindya Sewwandi on 5/31/20
 */
@Repository
public interface DeliveryDao extends JpaRepository<DeliveryPersonDetail, Integer> {

    DeliveryPersonDetail findByEmail(String email);

    DeliveryPersonDetail findByUuid(String uuid);

}