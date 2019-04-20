package com.webcrawler.apiproject.init;

import com.webcrawler.apiproject.dao.CustomerProfileDAO;
import com.webcrawler.apiproject.dao.FlightInformationDAO;
import com.webcrawler.apiproject.domain.CustomerProfile;
import com.webcrawler.apiproject.domain.FlightInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
public class DataInit implements ApplicationRunner {

    private CustomerProfileDAO customerProfileDAO;
    private FlightInformationDAO flightInformationDAO;

    @Autowired
    public DataInit(CustomerProfileDAO customerProfileDAO, FlightInformationDAO flightInformationDAO) {
        this.customerProfileDAO = customerProfileDAO;
        this.flightInformationDAO = flightInformationDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = customerProfileDAO.count();

        if (count == 0) {
            CustomerProfile customerProfile = new CustomerProfile();
            FlightInformation flightInformation = new FlightInformation();
            flightInformation.setFlightNumber("AB123");
            customerProfile.setCustomerEmail("test@test.com");

            customerProfileDAO.save(customerProfile);
            flightInformationDAO.save(flightInformation);
        }
    }
}

