import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;

public class SmokeTest {
    public static WebDriver driver = null;
    Map<String,String> testurls;
    DataDeliver data;

        @Before
        public void init(){
            System.setProperty("webdriver.chrome.driver","D:\\projects\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            /* when run with split seleniumtestpages only jar */
            String baseUrl = "http://localhost:4567/";
            driver.get(baseUrl);

            data = new DataDeliver();
            testurls = data.getUrls();
        }

        @Test
        public void testTitle(){
            String expectTitle = "Test Pages For Automating";
            String actualTitle = "";
            String baseUrl = "http://localhost:4567/";

            actualTitle = driver.getTitle();
            String currentUrl = driver.getCurrentUrl();
            Assert.assertEquals(currentUrl,baseUrl);
            Assert.assertEquals(actualTitle,expectTitle);
        }

        @Test
        public void testNavigation(){
            for (Map.Entry<String,String> urlPair : testurls.entrySet()){
                driver.navigate().to(urlPair.getKey());
                Assert.assertTrue(driver.getPageSource().contains(urlPair.getValue()));
            }
        }

        @Test
        public void testResources(){
            // TODO test links in the About and Credits
        }

        @Test
        public void testAddAttribute(){
            driver.navigate().to("http://localhost:4567/styled/attributes-test.html");
            driver.findElement(By.xpath("//button[contains(text(),'Add Another Attribute')]")).click();
            WebElement elem = driver.findElement(By.xpath("//button[contains(text(),'Add Another Attribute')]"));
            String attr = elem.getAttribute("nid");
            Assert.assertNotEquals("",attr);
        }

        @Test
        public void testCalculator(){
            driver.navigate().to("http://localhost:4567/styled/calculator");

            /* Test addition */
            WebElement n1 = driver.findElement(By.id("number1"));
            n1.sendKeys("2");
            WebElement n2 = driver.findElement(By.id("number2"));
            n2.sendKeys("2");
            driver.findElement(By.xpath("//button[contains(text(),'Calculate')]")).click();
            WebElement sum = driver.findElement(By.id("answer"));
            Assert.assertEquals(4,sum.getAttribute("body"));

            /* TODO test subtraction */

            /* TODO test multiplication */

            /* TODO test division */
        }

        @Test
        public void testCanvasScribblePage(){
            //TODO
        }

        @Test
        public void testFrames(){
            //TODO
        }

        @After
        public void closeDriver() {
            driver.close();
        }
}
