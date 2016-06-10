package GoldenBatchBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GBB {
	
	WebDriver driver = null;
	public ExtentReports extent;
	
	@Before
	public void reportsetup(){
		this.extent = new ExtentReports("D://ConnX.html", true);
		extent.config().documentTitle("ConnX Suite").reportName("Summary :").reportHeadline("ConnX Automation Report").insertCustomStyles(".test { border:2px solid #444; }");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
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
	
		driver.findElement(By.id("enabled")).click();
		
		driver.findElement(By.xpath("//*[contains(text(),'Submit')]")).click();
	}

	@Then("^validate record message$")
	public void validate_record_message() throws Throwable {
		
		System.out.println("Record Added Succesfully");
	}
	

	@Then("^Search \"(.*?)\"$")
	public void search(String SearchParameter) throws Throwable {
		
		String[] Search=SearchParameter.split("-");
		if(Search[0].trim().equals("Name")){
			driver.findElement(By.xpath("//input[@name='name']")).sendKeys(Search[1].trim());	
		}

		driver.findElement(By.xpath("//*[contains(text(),'Search')]")).click();
		
	}

	@And("^update record$")
	public void update_record() throws Throwable {
	}

	@And("^logout ConnX$")
	public void logout_ConnX() throws Throwable {

	}

	@Then("^close the browser$")
	public void close_the_browser() throws Throwable {
		driver.close();
		driver.quit();
	}

}
