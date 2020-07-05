package lk.gedaratama.backendserver.repository;

import lk.gedaratama.backendserver.model.PendingShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sashini Tharuka on 5/31/20
 */
@Repository
public interface PendingShopRepository extends JpaRepository<PendingShop, Integer> {

    PendingShop findByEmail(String email);

    PendingShop findByUuid(String uuid);

}