package com.fsd.coreservices.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableJpaAuditing
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }


//    @Bean
//    // @Profile("test")
//    public FlywayMigrationStrategy cleanMigrateStrategy() {
//        FlywayMigrationStrategy strategy = new FlywayMigrationStrategy() {
//            @Override
//            public void migrate(Flyway flyway) {
//                flyway.clean();
//                flyway.migrate();
//            }
//        };
//
//        return strategy;
//    }
}
