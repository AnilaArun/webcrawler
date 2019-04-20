package com.webcrawler.apiproject.model;

import com.webcrawler.apiproject.domain.CustomerProfile;
import com.webcrawler.apiproject.service.CustomerProfileService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.stream.Stream;

public class DbStartupBean {

}
/*@Startup
@Singleton
public class DbStartupBean {
    private final CustomerProfileService customerProfileService;

    @Inject
    public DbStartupBean(CustomerProfileService customerProfileService) {
        this.customerProfileService = customerProfileService;
    }

    @PostConstruct
    private void startup() {
        Stream.of().forEach(name ->
                customerProfileService.addCustomerProfile(new CustomerProfile())
        );
        customerProfileService.getAllCustomerProfiles().forEach(System.out::println);
    }
    @PreDestroy
    private void shutdown() {
        customerProfileService.clear();
    }
}*/
