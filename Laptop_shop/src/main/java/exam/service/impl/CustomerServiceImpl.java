package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.CustomerSeedDto;
import exam.model.entity.Customer;
import exam.model.entity.Town;
import exam.repository.CustomerRepository;
import exam.service.CustomerService;
import exam.service.TownService;
import exam.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String CUSTOMERS_PATH = "src/main/resources/files/json/customers.json";

    private final CustomerRepository customerRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final TownService townService;

    public CustomerServiceImpl(CustomerRepository customerRepository, Gson gson, ModelMapper modelMapper, ValidatorUtil validatorUtil, TownService townService) {
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(Path.of(CUSTOMERS_PATH));
    }

    @Override
    public String importCustomers() throws IOException {
        StringBuilder sb = new StringBuilder();
        CustomerSeedDto[] customerSeedDto = gson.fromJson(readCustomersFileContent(), CustomerSeedDto[].class);

         Arrays.stream(customerSeedDto)
                .filter(customer -> {
                    boolean isValid = validatorUtil.isValid(customer)
                            && !emailIsExist(customer.getEmail());
                    sb.append(isValid ? String.format("Successfully imported Customer %s %s - %s",
                            customer.getFirstName(), customer.getLastName(), customer.getEmail()) :
                            "Invalid Customer");

                    return isValid;
                }).map(customer -> {
                    Customer map = modelMapper.map(customer, Customer.class);
                    Town townByName = townService.findTownByName(customer.getTown().getName());

                    map.setTown(townByName);
                    return map;
                }).forEach(customerRepository::save);

        return sb.toString();
    }

    private boolean emailIsExist(String email) {
        return customerRepository.existsCustomerByEmail(email);
    }
}
