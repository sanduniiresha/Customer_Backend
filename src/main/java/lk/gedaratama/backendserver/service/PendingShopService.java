package lk.gedaratama.backendserver.service;

import lk.gedaratama.backendserver.model.PendingShop;
import lk.gedaratama.backendserver.model.ShopDetail;
import lk.gedaratama.backendserver.model.User;
import lk.gedaratama.backendserver.repository.PendingShopRepository;
import lk.gedaratama.backendserver.resource.PendingShopResource;
import lk.gedaratama.backendserver.util.GedaratamaParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sashini Tharuka on 6/2/2020.
 */
@Service
public class PendingShopService {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ShopDetailService shopDetailService;

    @Autowired
    private PendingShopRepository pendingShopRepository;

    public PendingShop savePendingShop(PendingShopResource pendingShopResource) {
        PendingShop pendingShop = new PendingShop();
        pendingShop.setShopName(pendingShopResource.getShop_name());
        pendingShop.setLocation(pendingShopResource.getLocation());
        pendingShop.setBusinessRegNo(pendingShopResource.getBusinessRegNo());
        pendingShop.setEmail(pendingShopResource.getEmail());
        pendingShop.setMobileNo(pendingShopResource.getMobile_no());
        pendingShop.setShopOwnerName(pendingShopResource.getShop_owner_name());
        pendingShop.setNic(pendingShopResource.getNic());
        pendingShop.setPassword(bcryptEncoder.encode(pendingShopResource.getPassword()));
        pendingShop.setUuid(GedaratamaParam.getUuid());
        pendingShop.setPublished(false);


        return pendingShopRepository.save(pendingShop);
    }

    //Todo add isPublished boolean and published_time
    public List<PendingShopResource> getUnpublishedShopList() {
        List<PendingShopResource> pendingShopResourceList = new ArrayList<>();
        pendingShopRepository.findAll().forEach(pendingShop -> {
            PendingShopResource pendingShopResource = new PendingShopResource();
            pendingShopResource.setBusinessRegNo(pendingShop.getBusinessRegNo());
            pendingShopResource.setEmail(pendingShop.getEmail());
            pendingShopResource.setLocation(pendingShop.getLocation());
            pendingShopResource.setMobile_no(pendingShop.getMobileNo());
            pendingShopResource.setNic(pendingShop.getNic());
            pendingShopResource.setShop_name(pendingShop.getShopName());
            pendingShopResource.setShop_owner_name(pendingShop.getShopOwnerName());
            pendingShopResource.setUuid(pendingShop.getUuid());
            pendingShopResourceList.add(pendingShopResource);
        });

        return pendingShopResourceList;
    }

    public void acceptShopPendingRequest(String uuid) {
        PendingShop pendingShop = pendingShopRepository.findByUuid(uuid);
        User user = userDetailsService.registerShopUser(pendingShop);

        if (user == null) {
            //todo error handling
        }
        ShopDetail shopDetail = new ShopDetail();
        shopDetail.setBusinessRegNo(pendingShop.getBusinessRegNo());
        shopDetail.setEmail(pendingShop.getEmail());
        shopDetail.setLocation(pendingShop.getLocation());
        shopDetail.setMobileNo(pendingShop.getMobileNo());
        shopDetail.setNic(pendingShop.getNic());
        shopDetail.setShopName(pendingShop.getShopName());
        shopDetail.setShopOwnerName(pendingShop.getShopOwnerName());
        shopDetail.setUserUuid(user.getUuid());

        ShopDetail shopDetail1 = shopDetailService.saveShopDetail(shopDetail);

        if (shopDetail1 == null) {
            //todo error handling
        }
    }

}
