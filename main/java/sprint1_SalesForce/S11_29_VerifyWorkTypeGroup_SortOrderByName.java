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


/*Test Steps:
1.	Login to https://login.salesforce.com. 
2.	Click on the toggle menu button from the left corner
3.	Click View All and click Work Type Groups from App Launcher
4.	Click on the Work Type Group tab 
5.	Click the sort arrow in the Work Type Group Name
6.	Verify the Work Type Group displayed in ascending order by Work Type Group Name.

Expected Result: Work Type Group should be displayed in ascending order by Work Type Group Name.
*/

public class S11_29_VerifyWorkTypeGroup_SortOrderByName {

	public static void main(String[] args) throws InterruptedException {
				
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		//Actions actions = new Actions(driver);
		
		//String workTypeGroupName = "Salesforce Automation by Ela";
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
/*		
		//5. Click the sort arrow in the Work Type Group Name
		// Scroll until no new rows are loaded
            while (currentRowCount > previousRowCount) {
                // Scroll to the bottom of the table to load more rows
                js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", table);
                
                // Wait for new rows to load
                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("tr"), currentRowCount));
                
                // Update row counts
                previousRowCount = currentRowCount;
                rows = table.findElements(By.tagName("tr"));
                currentRowCount = rows.size();
            }
		
		
		Thread.sleep(3000);
		WebElement searchWorkTypeGroup = driver.findElement(By.xpath("//input[@placeholder ='Search this list...']"));
		searchWorkTypeGroup.sendKeys(workTypeGroupName);
		searchWorkTypeGroup.sendKeys(Keys.ENTER);
		
		
		//6. Click on the Dropdown icon and Select Edit
		try {
			WebElement rowDropdown = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.xpath("((//table/tbody/tr//a[@title='"+workTypeGroupName+"'])/ancestor :: tr)//td//a[@role = 'button']"))));
			rowDropdown.click();
		} catch (StaleElementReferenceException e){
			System.out.println("StaleElementException occurred. Refreshing the page.");
			driver.navigate().refresh();
			WebElement rowDropdown = wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.xpath("((//table/tbody/tr//a[@title='"+workTypeGroupName+"'])/ancestor :: tr)//td//a[@role = 'button']"))));
			rowDropdown.click();
		}
		
		WebElement editInMenu = driver.findElement(By.xpath("//div[@role='menu']//div[text()='Edit']"));
		driver.executeScript("arguments[0].click();", editInMenu);
		
		//7.	Enter Description as 'Automation'
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//label[text()='Description'])//following :: textarea")).sendKeys("Automation");
		
		
		//8.	Select Group Type as 'Capacity'
		driver.findElement(By.xpath(("//label[text()='Group Type']/following::div[1]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//lightning-base-combobox-item//span[@class='slds-truncate' and contains(text(),'Capacity')]"))).click();
		Thread.sleep(2000);
		String selectedGroupType = driver.findElement(By.xpath("//button/span[@class='slds-truncate']")).getText();
		
		if(selectedGroupType.equalsIgnoreCase("Capacity"))
			System.out.println("Capacity Group Type is selected");
		else
			System.out.println(selectedGroupType + " is selected as Group Type");
		
		//9.	Click on Save
		
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
		//10.	Click on 'Salesforce Automation by Your Name'and Verify Description as 'Automation'
			//Toast Message Verification
		WebElement toastMessageElement = driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']"));
		String toastMessage = toastMessageElement.getText();
		System.out.println("\nThe toast message after Edit & Save:\n"+toastMessage);
		
			//Description Verification
		driver.findElement(By.xpath("//th//a[@title='"+workTypeGroupName+"']")).click();
		String description = (driver.findElement(By.xpath("(//span[text()='Description'])[2]/following::lightning-formatted-text[@slot='outputField'][1]"))).getText();
		System.out.println("\nThe text in \"Description\" field after edit and save: "+description);
*/		
	}

}
