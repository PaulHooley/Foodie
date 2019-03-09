import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ingredients {
    public WebDriver driver;

    // Adds all the information to the ingredients.txt document
    public String browserLaunch(String URL){
        // Accesses the firefox-developer version installed
        String localuser = System.getProperty("user.name");
        System.setProperty("webdriver.firefox.bin", "/var/local/" + localuser + "/local/bin/firefox-developer");
        System.setProperty("webdriver.gecko.driver", "/var/local/" + localuser + "/local/bin/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        WebDriver driver = new FirefoxDriver(options);
        driver = new Augmenter().augment(driver);
        driver.get(URL);
        StringBuilder text = new StringBuilder();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[4]/div/div/div[3]/div/h1")));
        text.append("\n");
        text.append("Tags: ");
        // Cant access each tag if they do / don't exist other than a series of
        // statements
        if (driver.findElements(By.cssSelector("img[alt='Dairy Free']")).size() > 0) {
            text.append("Dairy Free ");
        }
        if (driver.findElements(By.cssSelector("img[alt='Gluten Free']")).size() > 0) {
            text.append("Gluten Free ");
        }
        if (driver.findElements(By.cssSelector("img[alt='Easy prep']")).size() > 0) {
            text.append("Easy prep ");
        }
        if (driver.findElements(By.cssSelector("img[alt='One pot']")).size() > 0) {
            text.append("One pot wonder ");
        }
        if (driver.findElements(By.cssSelector("img[alt='Clean15']")).size() > 0) {
            text.append("Clean15 (Low Carb) ");
        }
        if (driver.findElements(By.cssSelector("img[alt='20 minutes']")).size() > 0) {
            text.append("20 Minutes ");
        }
        if (driver.findElements(By.cssSelector("img[alt='Spicy']")).size() > 0) {
            text.append("Spicy ");
        }
        text.append("\n");
        text.append(
                "Title: " + driver.findElement(By.xpath("/html/body/div[4]/div[4]/div/div/div[3]/div/h1")).getText());
        text.append("\n");
        text.append("URL: " + URL);
        text.append("\n");
        text.append("Servings: "
                + driver.findElement(By.xpath("/html/body/div[4]/div[4]/div/div/div[2]/div[2]/div[2]/h3")).getText());
        text.append("\n");
        text.append("Calories: "
                + driver.findElement(By.xpath("/html/body/div[4]/div[4]/div/div/div[2]/div[2]/div[3]/h3")).getText());
        text.append("\n");
        // Gets Ingredients
        text.append(driver.findElement(By.xpath("/html/body/div[4]/div[4]/div/div/div[4]/div[4]/div[2]")).getText());
        text.append("\n");
        text.append(":end");
        text.append("\n");

        driver.close();
        return text.toString();

    }

    public WebElement waitUntilVisible(By ByStr) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(ByStr));
    }
}
