package org.tbounsiar;

import io.quarkiverse.cucumber.CucumberOptions;
import io.quarkiverse.cucumber.CucumberQuarkusTest;

@CucumberOptions(
        glue = {"org.tbounsiar"},
        plugin = {"json:report/index.json", "html:report/index.html"},
        features = "classpath:feature"
)
public class CucumberBddTest extends CucumberQuarkusTest {

    public static void main(String[] args) {
        runMain(CucumberBddTest.class, args);
    }

}
