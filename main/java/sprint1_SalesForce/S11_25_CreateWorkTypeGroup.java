package sprint1_SalesForce;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/*Test Steps:
	1. Login to https://login.salesforce.com
	2. Click on the toggle menu button from the left corner
	3. Click View All and click Work Type Groups from App Launcher
	4. Click on the Dropdown icon in the Work Type Groups tab
	5. Click on New Work Type Group
	6. Enter Work Type Group Name as 'Salesforce Automation by Your Name'
	7. Click save and verify Work Type Group Name
Expected Result:
	The Work Type Group is created SuccessfullyStep
	*/
public class S11_25_CreateWorkTypeGroup {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		String workTypeGroupName = "Salesforce Automation by Ela";
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
		
		//6. Enter Work Type Group Name as 'Salesforce Automation by Your Name'
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//label[text()='Work Type Group Name'])/following::input[1]")).sendKeys(workTypeGroupName);
		
		//7. Click save and verify Work Type Group Name
		driver.findElement(By.xpath("//button[text()='Save']")).click();
			//Toast Message Verification
		WebElement toastMessage = driver.findElement(By.xpath("//div[@data-aura-class = 'forceToastMessage' and @aria-label='Success']//a"));
		String wtgName_ToastMessage = toastMessage.getAttribute("title");
		System.out.println("Toast Message Verification\n\""+wtgName_ToastMessage+"\" Work Type Group is created successfully. Success!!!\n");
		Thread.sleep(3000);
			//verify page title
		System.out.println("Title Verification:\nTitle: "+driver.getTitle()+"\n");
			//Verify if created work type group name is as expected
		System.out.println("Name verification on New Work Type Group Page:");
		String createdWorkTypeGroupName = driver.findElement(By.xpath("//lightning-formatted-text[@slot = 'primaryField']")).getText();
		if (createdWorkTypeGroupName.equals(workTypeGroupName))
		{
			System.out.println("Work Type Group Name on page: \"" +workTypeGroupName+"\" as expected.");
		}
		else
		{
			System.out.println("Failed");
		}
	}

}
