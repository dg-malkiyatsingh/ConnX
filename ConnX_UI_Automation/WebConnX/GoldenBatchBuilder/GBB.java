package GoldenBatchBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GBB {
	
	WebDriver driver = null;
	WebDriverWait wait = null;
	public ExtentReports extent;
	
	@Before
	public void reportsetup(){
		this.extent = new ExtentReports("D://ConnX.html", true);
		extent.config().documentTitle("ConnX Suite").reportName("Summary :").reportHeadline("ConnX Automation Report").insertCustomStyles(".test { border:2px solid #444; }");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 10);
	}
	
	@Given("^open browser and Go to URL \"(.*?)\"$")
	public void OpenBrowserURL(String URL) throws Throwable {
		driver.get(URL);
		
	
	}

	@And("^Provide vaild credentials$")
	public void LoginConnX(DataTable Logincredentials) throws Throwable {
		
		List<List<String>> credential= Logincredentials.raw();
		driver.findElement(By.id("email")).sendKeys(credential.get(1).get(1));
		driver.findElement(By.id("password")).sendKeys(credential.get(2).get(1));
		driver.findElement(By.xpath( "//button[contains(text(),'Login')]")).click();
	}

	@When("^Click on GBB tab$")
	public void ClickGBBTab() throws Throwable {
		driver.findElement(By.xpath( "/html/body/div[3]/div/div/ul/li[4]/a")).click();
	}

	@Then("^Click on GB template$")
	public void clickGBTemplate() throws Throwable {
		driver.findElement(By.xpath( "//span[contains(text(), 'New GB Template')]")).click();
	}

	@And("^Insert/update record$")
	public void CreateGBTemplate(DataTable TemplateRecord) throws Throwable {
		
		List<List<String>> TempRec = TemplateRecord.raw();
		
		driver.findElement(By.id("name")).sendKeys(TempRec.get(1).get(1)); 
		driver.findElement(By.xpath("//*[@id='select2-chosen-1']")).click();
		driver.findElement(By.xpath("//div[@id='select2-drop']//*[contains(text(),'"+TempRec.get(2).get(1)+"')]")).click();

	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//span[@id='select2-chosen-2']")).click();
		driver.findElement(By.xpath("//div[@id='select2-drop']//*[contains(text(),'"+TempRec.get(3).get(1)+"')]")).click();
		
		driver.findElement(By.xpath("//span[@id='select2-chosen-3']")).click();
		driver.findElement(By.xpath("//div[@id='select2-drop']//*[contains(text(),'"+TempRec.get(4).get(1)+"')]")).click();
		
		driver.findElement(By.xpath("//input[@id='total_duration']")).click();
		driver.findElement(By.xpath("//input[@id='total_duration']")).sendKeys(""+TempRec.get(5).get(1)+"");
		
		driver.findElement(By.xpath("//span[@id='select2-chosen-4']")).click();
		driver.findElement(By.xpath("//ul[@id='select2-results-4']//*[contains(text(),'"+TempRec.get(6).get(1)+"')]")).click();
	
		if(TempRec.get(7).get(1).equals("True")){
		driver.findElement(By.id("enabled")).click();
		}else{
			System.out.println("");
		}
		driver.findElement(By.xpath("//*[contains(text(),'Submit')]")).click();
		
		
	}

	@Then("^validate record message$")
	public void ValidateTemplateAdded() throws Throwable {
		
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']//div[@class='toast-title']")));
		
		String MessageTitle=driver.findElement(By.xpath("//div[@id='toast-container']//div[@class='toast-title']")).getText();
		
		if(MessageTitle.equalsIgnoreCase("Success")){
												
			String SuccessMessage=driver.findElement(By.xpath("//div[@id='toast-container']//div[@class='toast-message']")).getText();
			System.out.println("Success :"+SuccessMessage);
											
		}else if(MessageTitle.equalsIgnoreCase("Error")){
		
				String FailedMessage=driver.findElement(By.xpath("//div[@id='toast-container']//div[@class='toast-message']")).getText();
				System.out.println("Error :"+FailedMessage);
		
		}
		
		
	}
	

	@Then("^Search/Delete \"(.*?)\"$")
	public void search(String SearchParameter) throws Throwable {

		
		String[] Search=SearchParameter.split("-");
		
				if(Search[0].trim().equalsIgnoreCase("Search") || Search[0].trim().equalsIgnoreCase("Delete")){
				if(Search[1].trim().equals("Name")){
					
					driver.findElement(By.xpath("//input[@name='name']")).sendKeys(Search[2].trim());	
				
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Search')]")));
					driver.findElement(By.xpath("//*[contains(text(),'Search')]")).click();
					
				    Thread.sleep(3000);
					List<WebElement> Searchcount= driver.findElements(By.xpath("//table[@id='datatable_ajax']/tbody/tr"));
					//System.out.println("size of result"+Searchcount.size());
					if(Searchcount.size()==1){
						
						if(Search[0].trim().equalsIgnoreCase("Search")){
							String Template_Name=driver.findElement(By.xpath("//table[@id='datatable_ajax']/tbody/tr/td[2]")).getText();
									if(Template_Name.equals(Search[2].trim())){
									}else{	
									Assert.fail("Template Name Not Matched");
									}
							}else{
								
								driver.findElement(By.xpath("//table[@id='datatable_ajax']/tbody/tr/td[9]/a[2]")).click();
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='connx_delete_confirm']//button[@id='connx_delete_confirm_model']")));	
								driver.findElement(By.xpath("//div[@id='connx_delete_confirm']//button[@id='connx_delete_confirm_model']")).click();
								
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']//div[@class='toast-title']")));
								String MessageTitle=driver.findElement(By.xpath("//div[@id='toast-container']//div[@class='toast-title']")).getText();
								
								if(MessageTitle.equalsIgnoreCase("Success")){
																		
									String SuccessMessage=driver.findElement(By.xpath("//div[@id='toast-container']//div[@class='toast-message']")).getText();
									System.out.println("Success :"+SuccessMessage);
																	
								}else if(MessageTitle.equalsIgnoreCase("Error")){
								
										String FailedMessage=driver.findElement(By.xpath("//div[@id='toast-container']//div[@class='toast-message']")).getText();
										System.out.println("Error :"+FailedMessage);
								
								}
																
							}
					
					}else{
						Assert.fail("Template Count Not Matched");
					}
				}
		
				}
		
	
	}

	
	@Then("^Delete \"(.*?)\"$")
	public void Delete(String SearchParameter) throws Throwable {
		
		
	}

	@And("^logout ConnX$")
	public void logout_ConnX() throws Throwable {
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='dropdown dropdown-user']//span")));
		driver.findElement(By.xpath("//li[@class='dropdown dropdown-user']//span")).click();
		//Thread.sleep(3000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dropdown-menu dropdown-menu-default']//li[4]//i")));
		driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-default']//li[4]//i")).click();

	}

	@Then("^close the browser$")
	public void close_the_browser() throws Throwable {
		//driver.close();
		driver.quit();
		
	}
}
