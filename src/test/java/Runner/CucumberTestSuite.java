package Runner;

import Utilities.PropertyReader;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "src/main/resources/features/ContactList.feature",
        glue = "stepdefinitions"
)
public class CucumberTestSuite extends PropertyReader
{

}
