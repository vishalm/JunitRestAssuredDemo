package com.featureAmazone.steps;

/**
 * Created by vishal on 9/9/16.
 */
import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "src/test/resources/features/",
        monochrome = true,
        tags = { "@RegressionTests" },
        format = { "pretty", "html:target/cucumber",
        "json:target/cucumber.json" })
public class RunCukesTest {

}
