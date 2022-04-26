package exam.model.entity;

import exam.model.enums.Warranty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "laptops")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mac_address", unique = true, nullable = false)
    private String macAddress;


    @Column(name = "cpu_speed")
    private double cpuSpeed;


    private int rum;

    private int storage;

    @Column(nullable = false, columnDefinition = "text")
    private String description;


    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "warranty_type")
    @Enumerated
    private Warranty warrantyType;

    @ManyToOne
    private Shop shop;

    public Laptop() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public double getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(double cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public int getRum() {
        return rum;
    }

    public void setRum(int rum) {
        this.rum = rum;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Warranty getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(Warranty warrantyType) {
        this.warrantyType = warrantyType;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return String.format("""
                Laptop - %s
                *Cpu speed - %.2f
                **Ram - %d
                ***Storage - %d
                ****Price - %s
                #Shop name - %s
                ##Town - %s
                                
                """, this.macAddress, this.cpuSpeed, this.rum, this.storage, this.price,
                this.shop.getName(), this.shop.getTown().getName());
    }
}
