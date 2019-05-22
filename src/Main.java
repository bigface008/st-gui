import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Main {

    public static final String aur_reg_url = "https://aur.archlinux.org/register/";
    public static final String main_url = "https://www.archlinux.org";

    public static void main(String[] args) {
        // Open Firefox.
        System.setProperty("webdriver.firefox.bin", "D:\\program\\Mozilla Firefox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver", "D:\\program\\webdrivers\\geckodriver-v0.24.0-win64\\geckodriver.exe");
        WebDriver firefox_driver = new FirefoxDriver();

        testBasicFunction(firefox_driver);
        // testLink(firefox_driver);

        firefox_driver.close();
    }

    private static void testBasicFunction(WebDriver driver) {
        driver.get(aur_reg_url);
        printCurrentWeb(driver);

        testTextarea(driver);
        testCheckbox(driver);
        testButton(driver);
        testSelect(driver);

        driver.get(main_url);
        printCurrentWeb(driver);

        testLayerLocation(driver);
        testForm(driver);
    }

    // private static void testLink(WebDriver driver) {

    // }

    private static void printCurrentWeb(WebDriver driver) {
        String title = driver.getTitle();
        String current_url = driver.getCurrentUrl();
        System.out.println("Now in " + current_url + " " + title);
    }

    private static void logTestStart(String name) {
        System.out.println("Test of " + name + " started.");
    }

    private static void logTestSuccess(String name) {
        System.out.println("Test of " + name + " succeeded.");
    }

    private static void logTestFailure(String name) {
        System.out.println("Test of " + name + " failed.");
    }

    /* 1. Test of text field / textarea.*/
    private static void testTextarea(WebDriver driver) {
        String name = "textarea";
        logTestStart(name);
        WebElement element = driver.findElement(By.id("id_ssh"));
        String test_str = "Test for text field / textarea.";
        element.sendKeys(test_str);
        if (element.getAttribute("value").equals(test_str)) {
            logTestSuccess(name);
        } else {
            logTestFailure(name);
        }
    }

    /* 2. Test of checkbox. */
    private static void testCheckbox(WebDriver driver) {
        String name = "checkbox";
        logTestStart(name);
        WebElement element = driver.findElement(By.id("id_hide"));
        element.click();
        if (element.isSelected()) {
            logTestSuccess(name);
        } else {
            logTestFailure(name);
        }
    }

    /* 3. Test of button. */
    private static void testButton(WebDriver driver) {
        String name = "button";
        logTestStart(name);
        List<WebElement> elements = driver.findElements(By.className("button"));
        for (WebElement element : elements) {
            if (element.getAttribute("type").equals("reset")) {
                element.click();
                if (element.isEnabled()) {
                    logTestSuccess(name);
                    return;
                } else {
                    logTestFailure(name);
                    return;
                }
            } else {
                continue;
            }
        }
    }

    /* 4. Test of form. */
    private static void testForm(WebDriver driver) {
        String name = "form";
        logTestStart(name);
        WebElement element = driver.findElement(By.id("pkgsearch-form"));
        element.submit();
        while (!(driver.getCurrentUrl().equals("https://www.archlinux.org/packages/?q=")))
            ;
        logTestSuccess(name);
    }

    /* 5. Test of layer location. */
    private static void testLayerLocation(WebDriver driver) {
        String name = "layer location";
        logTestStart(name);
        WebElement news = driver.findElement(By.id("news"));
        List<WebElement> elements = news.findElements(By.tagName("p"));
        for (WebElement element : elements) {
            System.out.println(element.getText());
        }
        logTestSuccess(name);
    }

    /* 6. Test of selection. */
    private static void testSelect(WebDriver driver) {
        String name = "select";
        logTestStart(name);
        WebElement element = driver.findElement(By.id("id_setlang"));
        Select select = new Select(element);
        select.selectByVisibleText("简体中文");
        List<WebElement> elements = select.getOptions();
        for (WebElement option : elements) {
            System.out.print(option.getAttribute("value") + " ");
        }
        System.out.println();
        logTestSuccess(name);
    }
}
