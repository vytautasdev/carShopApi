package com.vytautasdev.api;

import com.vytautasdev.api.domain.dao.CarRepository;
import com.vytautasdev.api.domain.dao.OwnerRepository;
import com.vytautasdev.api.domain.dao.UserRepository;
import com.vytautasdev.api.domain.entity.Car;
import com.vytautasdev.api.domain.entity.Owner;
import com.vytautasdev.api.domain.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import java.util.Arrays;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {

    private static final Logger LOGGER = LogManager.getLogger(ApiApplication.class);
    private final CarRepository repository;
    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;

    public ApiApplication(CarRepository repository, OwnerRepository ownerRepository, UserRepository userRepository) {
        this.repository = repository;
        this.ownerRepository = ownerRepository;
        this.userRepository = userRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        var owner1 = new Owner("John", "Johnson");
        var owner2 = new Owner("Mary", "Robinson");
        ownerRepository.saveAll(Arrays.asList(owner1, owner2));

        var car1 = new Car("Ford", "Mustang", "Red", "ADF-1121", 2021, 59000, owner1);
        var car2 = new Car("Nissan", "Leaf", "White", "SSJ-3002", 2019, 29000, owner2);
        var car3 = new Car("Toyota", "Prius", "Silver", "KKO-0212", 2020, 39000, owner2);

        repository.saveAll(Arrays.asList(car1, car2, car3));

        for (var item : repository.findAll()) {
            LOGGER.info(item.getBrand() + " " + item.getModel());
        }

        // Username: user, password: user
        userRepository.save(new User("user", "$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue", "USER"));
        // Username: admin, password: admin
        userRepository.save(new User("admin", "$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW", " ADMIN"));
    }
}
