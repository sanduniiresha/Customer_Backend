package lk.gedaratama.backendserver.jwt;

import lk.gedaratama.backendserver.model.CustomerDetail;

import lk.gedaratama.backendserver.repository.CustomerDao;

import lk.gedaratama.backendserver.resource.CustomerResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtCustomerDetailService {
    private final CustomerDao customerDao;

    @Autowired
    public JwtCustomerDetailService (CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    private CustomerResource getCustomerResource(CustomerDetail Customer) {
        CustomerResource customerResource = new CustomerResource();
        customerResource.setUsername(Customer.getUsername());
        customerResource.setPassword(Customer.getPassword());
        customerResource.setUuid(Customer.getUuid());
        customerResource.setEmail(Customer.getEmail());
        customerResource.setRole(Customer.getRole());
        return customerResource;
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
