package com.webcrawler.apiproject.init;

import com.webcrawler.apiproject.dao.CustomerProfileDAO;
import com.webcrawler.apiproject.domain.CustomerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Component
//@EnableJpaRepositories(basePackages="com.webcrawler.apiproject.dao", entityManagerFactoryRef="CustomerProfileDAO")
public class DataInit implements ApplicationRunner {

    private CustomerProfileDAO customerProfileDAO;

    @Autowired
    LocalSessionFactoryBean localSessionFactoryBean;
    //private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public DataInit(CustomerProfileDAO customerProfileDAO) {
        this.customerProfileDAO = customerProfileDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long count = customerProfileDAO.count();

        if (count == 0) {
            CustomerProfile customerProfile = new CustomerProfile();
            customerProfile.setCustomerEmail("test@test.com");
            /*customerProfile.setFullName("John");

            Date d1 = df.parse("1980-12-20");
            customerProfile.setDateOfBirth(d1);*/
            //
            /*Person p2 = new Person();

            p2.setFullName("Smith");
            Date d2 = df.parse("1985-11-11");
            p2.setDateOfBirth(d2);*/

            customerProfileDAO.save(customerProfile);
            //customerProfileDAO.save(p2);
        }
    }

}
