package sprint1_SalesForce;


import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/*Test Steps:
Login to https://login.salesforce.com 
2.	Click on the toggle menu button from the left corner
3.	Click View All and click Work Type Groups from App Launcher
4.	Click on the Dropdown icon in the Work Type Groups tab
5.	Click on New Work Type Group
6.	Enter Description as 'Automation'.
7.	Select Group Type as 'Capacity'
8.	Click on Save
9.	Verify the Alert message (Complete this field) displayed for Work Type Group Name

Expected Result: Complete this field message should be displayed for Work Type Group Name field

*/
public class S11_28_CreateWorkTypeGroup_WithoutMandatoryFields {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		String userName = "bootcamp_2024@testleaf.com";
		String password = "Bootcamp@123";
		
		//1. Login to https://login.salesforce.com
		
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);		
		driver.findElement(By.id("Login")).click();
		
		//2. Click on the toggle menu button from the left corner
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		
		
		//3. Click View All and click Work Type Groups from App Launcher
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='View All']"))).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Work Type Groups");
		
		Thread.sleep(2000);
		WebElement button = driver.findElement(By.xpath("//a[@data-label='Work Type Groups']"));
		new Actions(driver).moveToElement(button).click().build().perform();
		
		//4. Click on the Dropdown icon in the Work Type Groups tab
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//span[contains(text(),'Work Type Groups')])[1]/following::a[1]")).click();
		
		//5. Click on New Work Type Group
		
		Thread.sleep(3000);
		WebElement newWorkTypeGroup  = driver.findElement(By.xpath("(//span[text()='New Work Type Group'])/ancestor::a"));
		new Actions(driver).moveToElement(newWorkTypeGroup).click().build().perform();
		
		//6. Enter Description as 'Automation'.
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//label[text()='Description'])//following :: textarea")).sendKeys("Automation");
		
		//7.	Select Group Type as 'Capacity'
		driver.findElement(By.xpath(("//label[text()='Group Type']/following::div[1]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//lightning-base-combobox-item//span[@class='slds-truncate' and contains(text(),'Capacity')]"))).click();
		Thread.sleep(2000);
		String selectedGroupType = driver.findElement(By.xpath("//button/span[@class='slds-truncate']")).getText();
		
		if(selectedGroupType.equalsIgnoreCase("Capacity"))
			System.out.println("Capacity Group Type is selected");
		else
			System.out.println(selectedGroupType + " is selected as Group Type");
		
		//8.	Click on Save
		
		//7. Click save and verify Work Type Group Name
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
			//verify the Error pop-up
		try{
			WebElement errorPopUp = driver.findElement(By.xpath("//div[@role='dialog']//h2[text()='We hit a snag.']"));
			if(errorPopUp.isDisplayed())
			{
				List<WebElement> errorFields = driver
						.findElements(By.xpath("(//div/Strong[text()='Review the following fields'])/following::a"));
				for (int i = 0; i <= (errorFields.size()) - 1; i++) {
					String fieldName = errorFields.get(i).getText();
					if (fieldName.equalsIgnoreCase("Work Type Group Name")) {
						driver.executeScript("arguments[0].click();", errorFields.get(i));
						break;
					}
			}
			String errorMessage = driver.findElement(By.xpath("(//label[text()='Work Type Group Name'])//following::div[@part='help-text']")).getText();
			System.out.println("\nThe Work Type Group has the below error message:\n"+errorMessage);
			}
		} catch(Exception e) {
			System.out.println(e+" No error pop up displayed!!!");
		}
		
	}

}
