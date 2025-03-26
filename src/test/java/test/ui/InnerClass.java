package test.ui;

import keff.example.utils.ThreadUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class InnerClass extends BaseUiTests {

    @Test
    public void testCase() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        driver.getTitle();
        Assert.assertEquals(driver.getTitle(), "Web form");
        Assert.assertEquals(driver.getTitle(), "Web form123");
    }
}
