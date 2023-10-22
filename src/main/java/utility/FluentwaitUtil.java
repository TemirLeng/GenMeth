package utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

public class FluentwaitUtil {
public static WebElement getElementWithWait(By by, int duration, int frequency, WebDriver driver) {

	Wait<WebDriver>wait=new FluentWait<WebDriver>(driver).withTimeout(duration, TimeUnit.SECONDS)
							.pollingEvery(frequency, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
	
	WebElement message=wait.until(new Function<WebDriver, WebElement>(){
		public WebElement apply(WebDriver driver) {
			return driver.findElement(by);
		}
	
	});
	return message;
}
}
