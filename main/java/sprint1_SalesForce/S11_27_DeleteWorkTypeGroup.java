package sprint1_SalesForce;


import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.NoSuchElementException;


/*Test Steps:
1. 	Login to https://login.salesforce.com 
2.	Click on the toggle menu button from the left corner
3.	Click View All and click Work Type Groups from App Launcher
4.	Click on the Work Type Group tab 
5.	Search the Work Type Group 'Salesforce Automation by Your Name'
6.	Click on the Dropdown icon and Select Delete
7.	Click on the Delete option in the displayed popup window.
8.	Verify Whether Work Type group is Deleted using Work Type Group Name

Expected Result: The Work Type Group is deleted Successfully  */

public class S11_27_DeleteWorkTypeGroup {

	public static void main(String[] args) throws InterruptedException {
				
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		//Actions actions = new Actions(driver);
		
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
		
		WebElement workTypeGroup_link = driver.findElement(By.xpath("//a[@data-label='Work Type Groups']"));
		driver.executeScript("arguments[0].scrollIntoView();", workTypeGroup_link);
        driver.executeScript("arguments[0].click();", workTypeGroup_link);
		
		//4. Click on the Work Type Group tab
		
		Thread.sleep(3000);
		WebElement workTypeGroup_listViewLink = driver.findElement(By.xpath("(//span[contains(text(),'Work Type Groups')])[1]"));
		driver.executeScript("arguments[0].click();", workTypeGroup_listViewLink);
		
		//5. Search the Work Type Group 'Salesforce Automation by Your Name'
		
		Thread.sleep(3000);
		WebElement searchWorkTypeGroup = driver.findElement(By.xpath("//input[@placeholder ='Search this list...']"));
		searchWorkTypeGroup.sendKeys(workTypeGroupName);
		searchWorkTypeGroup.sendKeys(Keys.ENTER);
				
		//6. Click on the Dropdown icon and Select Delete
		try {
			WebElement rowDropdown = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.xpath("((//table/tbody/tr//a[@title='"+workTypeGroupName+"'])/ancestor :: tr)//td//a[@role = 'button']"))));
			rowDropdown.click();
		} catch (StaleElementReferenceException e){
			System.out.println("StaleElementException occurred. Refreshing the page.");
			driver.navigate().refresh();
			WebElement rowDropdown = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.xpath("((//table/tbody/tr//a[@title='"+workTypeGroupName+"'])/ancestor :: tr)//td//a[@role = 'button']"))));
			rowDropdown.click();
		}
		
		WebElement editInMenu = driver.findElement(By.xpath("//div[@role='menu']//div[text()='Delete']"));
		driver.executeScript("arguments[0].click();", editInMenu);
		
		//7.	Click on the Delete option in the displayed popup window.
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type = 'button' and @title = 'Delete']")).click();
		
		//8.	Verify Whether Work Type group is Deleted using Work Type Group Name
				//Toast Message Verification
		WebElement toastMessageElement = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']"));
		String toastMessage = toastMessageElement.getText();
		System.out.println("\nThe toast message after Delete Action:\n"+toastMessage);
				
				//search and verify if there exists no Work Type Group in that name.
		//refreshing the page after delete.
		driver.navigate().refresh();
		System.out.println("\nThe page after Work Type Group delete action:\n"+driver.getTitle());
		
		//search for the deleted Work Type Group
		Thread.sleep(2000);
		WebElement searchWorkTypeGroup2 = driver.findElement(By.xpath("//input[@placeholder ='Search this list...']"));
		searchWorkTypeGroup2.sendKeys(workTypeGroupName);
		searchWorkTypeGroup2.sendKeys(Keys.ENTER);
		
		Thread.sleep(2000); //the page to load the results
		try {
			WebElement rowWorkTypeGroup = driver.findElement(By.xpath("//table/tbody/tr//a[@title='Salesforce Automation by Ela']"));
			if(rowWorkTypeGroup.isDisplayed())
			{ 
				System.out.println("The Work Type Group \""+workTypeGroupName+"\" is not deleted, Try again");
			}
			else
			{
				String results = driver.findElement(By.xpath("//span[@part ='formatted-rich-text']")).getText();		
				if(results.contains("No items to display"))
				{
				System.out.println("\nThe Work Type Group \""+workTypeGroupName+"\" is deleted successfully.");
				}
				else
				System.out.println("Unable to verify if the Work Type Group \""+workTypeGroupName+"\" is deleted, please verify manually");
		
			}
		}
		catch (NoSuchElementException e){
			System.out.println("\nThe Work Type Group Name search for \""+workTypeGroupName+"\" returned no such element.");
			String results = driver.findElement(By.xpath("//span[@part ='formatted-rich-text']")).getText();		
			if(results.contains("No items to display"))
			{
				System.out.println("\nThe Work Type Group \""+workTypeGroupName+"\" is deleted successfully.");
			}
			else
				System.out.println("Unable to verify if the Work Type Group \""+workTypeGroupName+"\" is deleted, please verify manually");
		
		}	
					
			
	}

}
