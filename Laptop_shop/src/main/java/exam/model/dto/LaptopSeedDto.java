package exam.model.dto;

import com.google.gson.annotations.Expose;
import exam.model.enums.Warranty;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class LaptopSeedDto {

    @Expose
    @Size(min = 8)
    @NotBlank
    private String macAddress;

    @Expose
    @Positive
    private double cpuSpeed;

    @Expose
    @Min(8)
    @Max(128)
    private int rum;

    @Expose
    @Min(128)
    @Max(1024)
    private int storage;

    @Expose
    @Size(min = 10)
    private String description;

    @Expose
    @Positive
    @NotNull
    private BigDecimal price;

    @Expose
    @NotNull
    private Warranty warrantyType;

    @Expose
    private ShopNameDto shop;


    public LaptopSeedDto() {
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

    public ShopNameDto getShop() {
        return shop;
    }

    public void setShop(ShopNameDto shop) {
        this.shop = shop;
    }
}
