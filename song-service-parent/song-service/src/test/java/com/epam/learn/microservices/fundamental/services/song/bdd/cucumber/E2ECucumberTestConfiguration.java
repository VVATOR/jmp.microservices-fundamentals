package com.epam.learn.microservices.fundamental.services.song.bdd.cucumber;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@Suite
//@IncludeEngines("cucumber")
//@SelectClasspathResource("com/koushick/BDD/Steps")
//@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME,value = "src/test/resources/testcases/searchGoogle.feature")
//@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME,value = "com/koushick/BDD/Steps")
//@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME,value = "@googleSearch")
//@ConfigurationParameter(key = Constants.EXECUTION_DRY_RUN_PROPERTY_NAME,value = "false")
//@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,value = "pretty, html:target/cucumber-report/cucumber.html")


@Configuration
@ComponentScan(basePackages = "com.epam.learn.microservices.fundamental.services.song")
@EnableJpaRepositories({"com.epam.learn.microservices.fundamental.services.song.repositories"})
@EntityScan({"com.epam.learn.microservices.fundamental.services.song.models"})
@EnableAutoConfiguration
public class E2ECucumberTestConfiguration {
    // We can leave it empty unless otherwise needed
}
