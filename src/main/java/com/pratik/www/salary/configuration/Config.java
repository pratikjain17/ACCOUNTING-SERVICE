package com.pratik.www.salary.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class Config extends AbstractCouchbaseConfiguration {

    @Autowired
    private ApplicationContext context;

    @Override
    public String getConnectionString() {
        return "couchbase://127.0.0.1";
    }

    @Override
    public String getUserName() {
        return "Pratik";
    }

    @Override
    public String getPassword() {
        return "pratik";
    }

    @Override
    public String getBucketName() {
        return "salary";
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

//    @Override
//    public void configureRepositoryOperationsMapping(RepositoryOperationsMapping mapping) {
//        try {
//            mapping.mapEntity(EmployeeLeaveEntity.class,  getCouchBaseTemplate("employeeleave"));
//            mapping.mapEntity(SalaryEntity.class,  getCouchBaseTemplate("salary"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private CouchbaseTemplate getCouchBaseTemplate(String bucketName) throws Exception {
//        CouchbaseTemplate couchbaseTemplate = new CouchbaseTemplate(couchbaseClientFactory(bucketName),
//                mappingCouchbaseConverter(couchbaseMappingContext(customConversions()),
//                        new CouchbaseCustomConversions(Collections.emptyList())));
//        couchbaseTemplate.setApplicationContext(context);
//
//        return couchbaseTemplate;
//    }
//
//    private CouchbaseClientFactory couchbaseClientFactory(String bucketName){
//        return new SimpleCouchbaseClientFactory(couchbaseCluster(couchbaseClusterEnvironment()), bucketName, this.getScopeName());
//    }
}


