package lk.gedaratama.backendserver.service;

import lk.gedaratama.backendserver.model.ShopDetail;
import lk.gedaratama.backendserver.repository.ShopDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sashini Tharuka on 6/13/2020.
 */
@Service
public class ShopDetailService {

    @Autowired
    private ShopDetailRepository shopDetailRepository;

    public ShopDetail saveShopDetail(ShopDetail shopDetail) {
        return shopDetailRepository.save(shopDetail);
    }

}
