package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.LaptopSeedDto;
import exam.model.entity.Laptop;
import exam.model.entity.Shop;
import exam.repository.LaptopRepository;
import exam.service.LaptopService;
import exam.service.ShopService;
import exam.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class LaptopServiceImpl implements LaptopService {

    private static final String LAPTOP_PATH = "D:\\Users\\admin\\Desktop\\Skelet\\Laptop_shop\\src\\main\\resources\\files\\json\\laptops.json";

    private final LaptopRepository laptopRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidatorUtil validatorUtil;
    private final ShopService shopService;

    public LaptopServiceImpl(LaptopRepository laptopRepository, ModelMapper modelMapper, Gson gson, ValidatorUtil validatorUtil, ShopService shopService) {
        this.laptopRepository = laptopRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validatorUtil = validatorUtil;
        this.shopService = shopService;
    }

    @Override
    public boolean areImported() {
        return laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(Path.of(LAPTOP_PATH));
    }

    @Override
    public String importLaptops() throws IOException {
        StringBuilder sb = new StringBuilder();

        LaptopSeedDto[] laptopSeedDtos = gson.fromJson(this.readLaptopsFileContent(), LaptopSeedDto[].class);

        Arrays.stream(laptopSeedDtos)
                .filter(laptop -> {
                    boolean isValid = validatorUtil.isValid(laptop);

                    sb.append(isValid ? String.format("Successfully imported Laptop %s - %.2f - %d - %d",
                            laptop.getMacAddress(), laptop.getCpuSpeed(), laptop.getRum(), laptop.getStorage())
                            : "Invalid Laptop");

                    return isValid;
                }).map(laptop -> {
                    Laptop map = modelMapper.map(laptop, Laptop.class);
                    Shop shopBuyName = shopService.findShopBuyName(laptop.getShop().getName());

                    map.setShop(shopBuyName);
                    return map;
                }).forEach(laptopRepository::save);
        return sb.toString();
    }

    @Override
    public String exportBestLaptops() {
        StringBuilder sb = new StringBuilder();
        for (Laptop laptop : laptopRepository.findAllOrderByCpuSpeedDesc()) {
            sb.append(laptop).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
