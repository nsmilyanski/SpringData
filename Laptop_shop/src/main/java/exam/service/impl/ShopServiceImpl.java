package exam.service.impl;

import exam.model.dto.ShopRootSeedDto;
import exam.model.entity.Shop;
import exam.model.entity.Town;
import exam.repository.ShopRepository;
import exam.service.ShopService;
import exam.service.TownService;
import exam.util.ValidatorUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {
    private static final String SHOP_PATH = "src/main/resources/files/xml/shops.xml";

    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidatorUtil validatorUtil;
    private final TownService townService;

    public ShopServiceImpl(ShopRepository shopRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidatorUtil validatorUtil, TownService townService) {
        this.shopRepository = shopRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(Path.of(SHOP_PATH));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        xmlParser.fromFile(SHOP_PATH, ShopRootSeedDto.class)
                .getShopSeedDtoSet()
                .stream()
                .filter(shop -> {
                    boolean isValid = validatorUtil.isValid(shop)
                            && !isNameExist(shop.getName());

                    sb.append(isValid ? String.format("Successfully imported Shop %s - %d ", shop.getName(),
                                    shop.getShopArea()) : "Invalid shop")
                            .append(System.lineSeparator());
                    return isValid;
                }).map(shop -> {
                    Shop map = modelMapper.map(shop, Shop.class);
                    Town townByName = townService.findTownByName(shop.getTown().getName());
                    map.setTown(townByName);
                    return map;
                }).forEach(shopRepository::save);

        return sb.toString();
    }

    @Override
    public Shop findShopBuyName(String name) {
        return shopRepository.findShopByName(name);
    }

    private boolean isNameExist(String name) {
        return shopRepository.existsShopByName(name);
    }
}
