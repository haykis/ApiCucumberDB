package stepdefinitions;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;


public class Hooks {
    static int stepCount;

    public Hooks() {
    }
    @Before
    public void setup() {

    }

    @After
    public void teardown(Scenario scenario) {
        System.out.println("tearDown calisti.");
        // ekran görüntüsü almak için bu sabit kodu kullanabiliriz.
        // eğer senaryonun sonucu FAILED ise ekran görüntüsünü rapora ekleyebilirsin.
        byte[] picture;
        if (scenario.isFailed()) {
            picture = (byte[])((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(picture, "image/png", "failed" + scenario.getName());
        } else {
            picture = (byte[])((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(picture, "image/png", "passed" + scenario.getName());
        }

        Driver.closeDriver();
    }

    @AfterStep //method sayisi
    public void makeSlowRunning() throws InterruptedException {
        Driver.waitBasic(2);
        ++stepCount;
        System.out.println(stepCount + ". STEP");
    }
}
