package lk.gedaratama.backendserver.model;

import javax.persistence.*;

/**
 * @author Sashini Tharuka on 6/2/2020.
 */


@Entity
@Table(name = "pending_shop")
public class PendingShop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String uuid;
    private String shopName;
    private String location;
    private String businessRegNo;
    private String email;
    private String mobileNo;
    private String shopOwnerName;
    private String nic;
    private String password;
    private boolean isPublished;

    public PendingShop() {
    }

    public PendingShop(String uuid, String shopName, String location, String businessRegNo, String email, String mobileNo, String shopOwnerName, String nic, String password, boolean isPublished) {
        this.uuid = uuid;
        this.shopName = shopName;
        this.location = location;
        this.businessRegNo = businessRegNo;
        this.email = email;
        this.mobileNo = mobileNo;
        this.shopOwnerName = shopOwnerName;
        this.nic = nic;
        this.password = password;
        this.isPublished = isPublished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    @Override
    public String toString() {
        return "PendingShop{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", shopName='" + shopName + '\'' +
                ", location='" + location + '\'' +
                ", businessRegNo='" + businessRegNo + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", shopOwnerName='" + shopOwnerName + '\'' +
                ", nic='" + nic + '\'' +
                ", password='" + password + '\'' +
                ", isPublished=" + isPublished +
                '}';
    }
}
