package lk.gedaratama.backendserver.controller;


import lk.gedaratama.backendserver.resource.PendingShopResource;
import lk.gedaratama.backendserver.response.MessageResponse;
import lk.gedaratama.backendserver.service.PendingShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sashini Tharuka on 6/2/2020.
 */


@RestController
@RequestMapping("/pending_shops")
@CrossOrigin
public class PendingShopController {

    private final PendingShopService pendingShopService;

    @Autowired
    public PendingShopController(PendingShopService pendingShopService) {
        this.pendingShopService = pendingShopService;
    }


    @PostMapping
    public ResponseEntity<?> savePendingShop(@RequestBody PendingShopResource pendingShopResource) {
        pendingShopService.savePendingShop(pendingShopResource);
        return ResponseEntity.ok(new MessageResponse("Your Request under review,confirmation email will send to you once approved."));
    }

    @GetMapping(value = "/un_published")
    public ResponseEntity<?> getUnpublishedShopList() {
        List<PendingShopResource> pendingShopResourceList = pendingShopService.getUnpublishedShopList();
        return ResponseEntity.ok(pendingShopResourceList);
    }

    @PostMapping(value = "/{uuid}")
    public ResponseEntity<?> acceptShopPendingRequest(@PathVariable String uuid) {
        pendingShopService.acceptShopPendingRequest(uuid);
        return ResponseEntity.ok(new MessageResponse("Success"));
    }


}
