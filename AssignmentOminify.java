package advanceSekenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AssignmentOminify {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       Actions action = new Actions(driver);
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).submit();
		WebElement pim = driver.findElement(By.xpath("//span[text()='PIM']"));
		action.moveToElement(pim).click().perform();
		
		   String[][] employeeData = {
		            {"sonam", "ray"},
		            {"asish", "ray"},
		            {"bhull", "ray"}
		        };

		        for (String[] employee : employeeData) {
		        	 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add Employee"))).click();
		            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName"))).sendKeys(employee[0]);
		            driver.findElement(By.name("lastName")).sendKeys(employee[1]);
		            
		            driver.findElement(By.xpath("//button[@type='submit']")).click();

		            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Personal Details']")));
		
		            WebElement pimAgain = driver.findElement(By.xpath("//span[text()='PIM']"));
		            action.moveToElement(pimAgain).click().perform();
		        }
		        driver.findElement(By.linkText("Employee List")).click();
		        Thread.sleep(2000); 
		        for (String[] employee : employeeData) {
		            String fullName = employee[0] + " " + employee[1];
		
		            WebElement nameSearch = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
		            nameSearch.clear();
		            nameSearch.sendKeys(fullName);
		            driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
		
		            Thread.sleep(2000); // wait for table to update
		
		            List<WebElement> names = driver.findElements(By.xpath("//div[@class='oxd-table-card']//div[contains(text(),'" + fullName + "')]"));
		            if (!names.isEmpty()) {
		                System.out.println(fullName + " -Name Verified ");
		            } else {
		                System.out.println(fullName + "  Not Found ");
		            }
		        }
		        
		            
		    		WebElement dashboard = driver.findElement(By.xpath("//span[text()='Dashboard']"));
		    		
		    		action.moveToElement(dashboard).click().perform();
		    		driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
		    		driver.findElement(By.linkText("Logout")).click();

		driver.quit();
		
	}

}


