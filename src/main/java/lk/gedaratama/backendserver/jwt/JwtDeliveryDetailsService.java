package lk.gedaratama.backendserver.jwt;
import lk.gedaratama.backendserver.repository.DeliveryDao;
import lk.gedaratama.backendserver.model.DeliveryPersonDetail;
import lk.gedaratama.backendserver.resource.UserCustomDetail;
import lk.gedaratama.backendserver.resource.DeliveryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *  @author Kavindya Sewwandi on 7/1/2020.
 */


@Service

public class JwtDeliveryDetailsService {
    private final DeliveryDao deliveryDao;

    @Autowired
    public JwtDeliveryDetailsService (DeliveryDao deliveryDao) {
        this.deliveryDao = deliveryDao;
    }

    private DeliveryResource getDeliveryResource(DeliveryPersonDetail delivery) {
        DeliveryResource deliveryResource = new DeliveryResource();
        deliveryResource.setUsername(delivery.getUsername());
        deliveryResource.setPassword(delivery.getPassword());
        deliveryResource.setUuid(delivery.getUuid());
        deliveryResource.setEmail(delivery.getEmail());
        deliveryResource.setRole(delivery.getRole());
        return deliveryResource;
    }

   /* @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        delivery byEmailAndActive = deliveryDao.findByEmail(email);
        if (byEmailAndActive == null) {
            throw new UsernameNotFoundException("Email not found.");
        }
        return new UserCustomDetail(getUserResource(byEmailAndActive));
    }*/

}
