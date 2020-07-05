package lk.gedaratama.backendserver.model;

import javax.persistence.*;

/**
 * @author Sashini Tharuka on 6/13/2020.
 */
@Entity
@Table(name = "shop_detail")
public class ShopDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userUuid;
    private String shopName;
    private String location;
    private String businessRegNo;
    private String email;
    private String mobileNo;
    private String shopOwnerName;
    private String nic;

    public ShopDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBusinessRegNo() {
        return businessRegNo;
    }

    public void setBusinessRegNo(String businessRegNo) {
        this.businessRegNo = businessRegNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getShopOwnerName() {
        return shopOwnerName;
    }

    public void setShopOwnerName(String shopOwnerName) {
        this.shopOwnerName = shopOwnerName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "ShopDetail{" +
                "id=" + id +
                ", userUuid='" + userUuid + '\'' +
                ", shopName='" + shopName + '\'' +
                ", location='" + location + '\'' +
                ", businessRegNo='" + businessRegNo + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", shopOwnerName='" + shopOwnerName + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}
