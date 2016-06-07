package GoldenBatchBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GoldenBatchBuilder {

	public ExtentReports extent;
	public WebDriver driver;
	public WebDriverWait wait;
	
	@BeforeClass
	public void Reportsetup(){
		this.extent = new ExtentReports("D://ConnX.html", true);
		extent.config().documentTitle("ConnX Suite").reportName("Summary :").reportHeadline("ConnX Automation Report").insertCustomStyles(".test { border:2px solid #444; }");
		this.driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 5);
		
	}
	
	  
	@AfterClass
	public void ReportClose(){
		
		this.driver.quit();
		extent.flush();
	}
	
	
   @Test
   public void ConnXloadpage() throws InterruptedException {
 	  ExtentTest Loadpage = extent.startTest("ConnXloadpage").assignCategory("Login Page");
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	    driver.get("http://connxdev.appsandbox.tk/");
	    String Title = driver.getTitle();
	    
	    if(Title.equalsIgnoreCase("Login ConnX")){
	    	
	    	Loadpage.log(LogStatus.INFO, "Title Matched");
			Loadpage.log(LogStatus.PASS, "Pass");
			
	    }else{
	    	
	    	Loadpage.log(LogStatus.FAIL, "Title Not Matched");
	      	extent.endTest(Loadpage);
	      	extent.flush();
	    	Assert.assertTrue(Title.contains("DgSecure"));
	    }
	    	   	
	    extent.endTest(Loadpage);
		  
  }
  
  
   @Test (dependsOnMethods="ConnXloadpage")
 public void ConnXvalidUsername() throws InterruptedException {
   	
   	ExtentTest chkusername = extent.startTest("ConnXvalidUsername").assignCategory("Login Page");
      	String Uname_id= "email";
   	WebElement username;
   	
   	try{
   		username=driver.findElement(By.name(Uname_id));
   		chkusername.log(LogStatus.INFO, "Get Username ID");
   		username.sendKeys("admin@connx.tk");;
   	    chkusername.log(LogStatus.PASS, "Input Username");
   	    extent.endTest(chkusername);
   		
   	}catch (Exception e){
   		
   		  chkusername.log(LogStatus.FAIL, "Unable to findout username ID");
   		 
   		  extent.endTest(chkusername);
   		  extent.flush();
      		  Assert.assertTrue(driver.findElement(By.id(Uname_id)).isDisplayed());
   		
   	}
   	
   	
		
 }
   
   
 @Test (dependsOnMethods="ConnXvalidUsername")
 public void ConnXvalidPassword() {
	  ExtentTest chkpassword = extent.startTest("ConnXvalidPassword").assignCategory("Login Page");
	  
  	String password_id= "password";
 	WebElement password;
 	
 	try{
 		password=driver.findElement(By.name(password_id));
 		chkpassword.log(LogStatus.INFO, "Get pasword ID");
 		password.sendKeys("123456");
 		chkpassword.log(LogStatus.PASS, "Input password");
 		
 	}catch (Exception e){
 		
 		  chkpassword.log(LogStatus.FAIL, "Unable to findout password ID");
/* 		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
 		
 		try {
			FileUtils.copyFile(scrFile, new File("D://logintst.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 		  chkpassword.addScreenCapture("D://logintst.png");*/
 		  extent.endTest(chkpassword);
 		  extent.flush();
    	  Assert.assertTrue(driver.findElement(By.id(password_id)).isDisplayed());
 		
 	}
 	
 	extent.endTest(chkpassword);
	  
	  	    
 }
 
 @Test (dependsOnMethods="ConnXvalidPassword")
 public void ConnXvalidatelogin(){
	  
	 ExtentTest chklogin = extent.startTest("ConnXvalidatelogin").assignCategory("Login Page");
     String login_xpath="//button[contains(text(),'Login')]";
     WebElement login_btn;
     
     try{
   	  login_btn=driver.findElement(By.xpath(login_xpath));
   	  chklogin.log(LogStatus.INFO, "Get login btn Xpath");
   		login_btn.click();
   		chklogin.log(LogStatus.PASS, "Click on Login Btn");
   		  		
   		
   	}catch (Exception e){
   		
   		  chklogin.log(LogStatus.FAIL, "Unable to findout login btn Xpath");
   		 
   		  extent.endTest(chklogin);
   		  extent.flush();
      	      Assert.assertTrue(driver.findElement(By.xpath(login_xpath)).isDisplayed());
   		
   	}
        	
    	extent.endTest(chklogin);
  }
  
  
 @Test (dependsOnMethods="ConnXvalidatelogin")
 public void ConnXGBBTab(){
	  
	 ExtentTest GBBTab = extent.startTest("ConnXGBBTab").assignCategory("Golden Batch Builder");
     String GBBTab_xpath="//html/body/div[3]/div/div/ul/li[4]/a";
     WebElement login_btn;
     
     try{
   	    login_btn=driver.findElement(By.xpath(GBBTab_xpath));
   	 GBBTab.log(LogStatus.INFO, "Get Golden Batch Builder Tab Xpath");
   		login_btn.click();
   		GBBTab.log(LogStatus.PASS, "Click on Golden Batch Builder Tab");
   		  		
   		
   	}catch (Exception e){
   		
   		GBBTab.log(LogStatus.FAIL, "Unable to findout Golden Batch Builder Tab Xpath");
   		 
   		  extent.endTest(GBBTab);
   		  extent.flush();
      	      Assert.assertTrue(driver.findElement(By.xpath(GBBTab_xpath)).isDisplayed());
   		
   	}
        	
    	extent.endTest(GBBTab);
  }
	
	
 @Test (dependsOnMethods="ConnXGBBTab")
 public void ConnXAddGBBTemp(){
	  
	 ExtentTest AddGBBTab = extent.startTest("ConnXAddGBBTemp").assignCategory("Golden Batch Builder");
     String AddGBBTab_xpath="//span[contains(text(), 'New GB Template')]";
     WebElement login_btn;
     
     try{
   	    login_btn=driver.findElement(By.xpath(AddGBBTab_xpath));
        AddGBBTab.log(LogStatus.INFO, "Get Golden Batch Builder Tab Xpath");
   		login_btn.click();
   		AddGBBTab.log(LogStatus.PASS, "Click on Add GB Template Button");
   		  		
   		
   	}catch (Exception e){
   		
   		AddGBBTab.log(LogStatus.FAIL, "Unable to findout Golden Batch Builder Tab Xpath");
   		 
   		  extent.endTest(AddGBBTab);
   		  extent.flush();
      	      Assert.assertTrue(driver.findElement(By.xpath(AddGBBTab_xpath)).isDisplayed());
   		
   	}
        	
    	extent.endTest(AddGBBTab);
  }
 
 
 @Test (dependsOnMethods="ConnXAddGBBTemp")
 public void ConnXAddGBBTempName(){
	  
	 ExtentTest AddGBBNme = extent.startTest("ConnXGBBTemplate_Name ").assignCategory("Golden Batch Builder");
     String AddGBBName_id="name";
     WebElement login_btn;
     
     try{
   	    login_btn=driver.findElement(By.id(AddGBBName_id));
   	 AddGBBNme.log(LogStatus.INFO, "Get Name Id");
   		login_btn.sendKeys("Terry");
   		AddGBBNme.log(LogStatus.PASS, "Write Name");
   		  		
   		
   	}catch (Exception e){
   		
   		AddGBBNme.log(LogStatus.FAIL, "Unable to findout Name Id");
   		 
   		  extent.endTest(AddGBBNme);
   		  extent.flush();
      	      Assert.assertTrue(driver.findElement(By.id(AddGBBName_id)).isDisplayed());
   		
   	}
        	
    	extent.endTest(AddGBBNme);
  }
 
 @Test (dependsOnMethods="ConnXAddGBBTempName")
 public void ConnXAddGBBTempBU(){
	  
	 ExtentTest AddGBBTemp = extent.startTest("ConnXAddGBBTemp_BusinessUnit").assignCategory("Golden Batch Builder");
     String AddGBBTempBUDropdown_Xpath="//*[@id='select2-chosen-1']";
     String AddGBBTempBU_Xpath="//div[@id='select2-drop']//*[contains(text(),'Downstream')]";
     WebElement GBBTempDropdown_btn;
     WebElement GBBTempBU_btn;
     try{
    	    GBBTempDropdown_btn=driver.findElement(By.xpath(AddGBBTempBUDropdown_Xpath));
    	    GBBTempDropdown_btn.click();
    	    AddGBBTemp.log(LogStatus.INFO, "Click on Business Unit Dropdown");
 		
		 		try{
		 			GBBTempBU_btn=driver.findElement(By.xpath(AddGBBTempBU_Xpath));
		 			GBBTempBU_btn.click();
		 			AddGBBTemp.log(LogStatus.INFO, "Select Field under Dropdown");
		 			AddGBBTemp.log(LogStatus.PASS, "Field Found and Select");
		 			extent.endTest(AddGBBTemp);
		 		}catch(Exception e){
		 		
		 		      AddGBBTemp.log(LogStatus.FAIL, "Unable to select provided Name");
		    /*		  extent.endTest(AddGBBTemp);
		    		  extent.flush();*/
		       	      Assert.assertTrue(driver.findElement(By.id(AddGBBTempBU_Xpath)).isDisplayed());
		 		 }
 		
    	}catch (Exception e){
    		
    		  AddGBBTemp.log(LogStatus.FAIL, "Unable to findout Dropdown box");
    		  extent.endTest(AddGBBTemp);
    		  extent.flush();
       	      Assert.assertTrue(driver.findElement(By.id(AddGBBTempBUDropdown_Xpath)).isDisplayed());
    		
    	}
        	
    	
  }
 
 @Test (dependsOnMethods="ConnXAddGBBTempBU")
 public void ConnXAddGBBTempP(){
	  
	 ExtentTest AddGBBTemp = extent.startTest("ConnXAddGBBTemp_Product").assignCategory("Golden Batch Builder");
     String AddGBBTempPDropdown_Xpath="//span[@id='select2-chosen-2']";
     String AddGBBTempP_Xpath="//div[@id='select2-drop']//*[contains(text(),'PR1')]";
     WebElement GBBTempDropdown_btn;
     WebElement GBBTempBU_btn;
     try{
    	 Thread.sleep(5000);
    	    GBBTempDropdown_btn=driver.findElement(By.xpath(AddGBBTempPDropdown_Xpath));
    	    GBBTempDropdown_btn.click();
    	    AddGBBTemp.log(LogStatus.INFO, "Click on Product Dropdown");
 		
		 		try{
		 			GBBTempBU_btn=driver.findElement(By.xpath(AddGBBTempP_Xpath));
		 			GBBTempBU_btn.click();
		 			AddGBBTemp.log(LogStatus.INFO, "Select Field under Dropdown");
		 			AddGBBTemp.log(LogStatus.PASS, "Field Found and Select");
		 			extent.endTest(AddGBBTemp);
		 		}catch(Exception e){
		 		
		 		      AddGBBTemp.log(LogStatus.FAIL, "Unable to select Product Name");
		    /*		  extent.endTest(AddGBBTemp);
		    		  extent.flush();*/
		       	      Assert.assertTrue(driver.findElement(By.xpath(AddGBBTempP_Xpath)).isDisplayed());
		 		 }
 		
    	}catch (Exception e){
    		
    		  AddGBBTemp.log(LogStatus.FAIL, "Unable to findout Dropdown box");
    		  extent.endTest(AddGBBTemp);
    		  extent.flush();
       	      Assert.assertTrue(driver.findElement(By.xpath(AddGBBTempPDropdown_Xpath)).isDisplayed());
    		
    	}
        	
    	
  }
 
 
 @Test (dependsOnMethods="ConnXAddGBBTempP")
 public void ConnXAddGBBTempPS(){
	  
	 ExtentTest AddGBBTemp = extent.startTest("ConnXAddGBBTemp_ProcessStage").assignCategory("Golden Batch Builder");
     String AddGBBTempPSDropdown_Xpath="//span[@id='select2-chosen-3']";
     String AddGBBTempPS_Xpath="//div[@id='select2-drop']//*[contains(text(),'Capture PR1')]";
     WebElement GBBTempDropdown_btn;
     WebElement GBBTempBU_btn;
     try{
    	    GBBTempDropdown_btn=driver.findElement(By.xpath(AddGBBTempPSDropdown_Xpath));
    	    GBBTempDropdown_btn.click();
    	    AddGBBTemp.log(LogStatus.INFO, "Click on Product Dropdown");
 		
		 		try{
		 			GBBTempBU_btn=driver.findElement(By.xpath(AddGBBTempPS_Xpath));
		 			GBBTempBU_btn.click();
		 			AddGBBTemp.log(LogStatus.INFO, "Select Product Stage");
		 			AddGBBTemp.log(LogStatus.PASS, "Product Stage Found and Select");
		 			extent.endTest(AddGBBTemp);
		 		}catch(Exception e){
		 		
		 		      AddGBBTemp.log(LogStatus.FAIL, "Unable to select Product Stage");
		 		     extent.endTest(AddGBBTemp);
		    		  extent.flush();
		    		  //Assert.assertTrue(driver.findElement(By.xpath(AddGBBTempPS_Xpath)).isSelected());
		    		  Assert.fail("Unable to select Product Stage");
		 		 }
 		
    	}catch (Exception e){
    		
    		  AddGBBTemp.log(LogStatus.FAIL, "Unable to findout Dropdown box");
    		  extent.endTest(AddGBBTemp);
    		  extent.flush();
       	      //Assert.assertTrue(driver.findElement(By.xpath(AddGBBTempPSDropdown_Xpath)).isDisplayed());
       	     Assert.fail("Unable to findout Dropdown box");
    		
    	}
        	
    	
  }
 
 @Test (dependsOnMethods="ConnXAddGBBTempPS")
 public void ConnXAddGBBTempTD(){
	  
	 ExtentTest AddGBBTemp = extent.startTest("ConnXAddGBBTemp_TimeDuration").assignCategory("Golden Batch Builder");
     String AddGBBTempPSDropdown_Xpath="//input[@id='total_duration']";
     WebElement GBBTempBU_btn;
     try{
    	 GBBTempBU_btn=driver.findElement(By.xpath(AddGBBTempPSDropdown_Xpath));
    	 GBBTempBU_btn.sendKeys("40");
    	    AddGBBTemp.log(LogStatus.INFO, "Get Time Duration Text Box ");
    	    AddGBBTemp.log(LogStatus.PASS, "Entered Time Duration ");
    	    extent.endTest(AddGBBTemp);
		}catch (Exception e){
    		
    		  AddGBBTemp.log(LogStatus.FAIL, "Unable to findout Time Duration TextBox");
    		  extent.endTest(AddGBBTemp);
    		  extent.flush();
       	      Assert.assertTrue(driver.findElement(By.xpath(AddGBBTempPSDropdown_Xpath)).isDisplayed());
    		
    	}
        	
    	
  }
 
 
 @Test (dependsOnMethods="ConnXAddGBBTempTD")
 public void ConnXAddGBBTempSD(){
	  
	 ExtentTest AddGBBTemp = extent.startTest("ConnXAddGBBTemp_StepDuration").assignCategory("Golden Batch Builder");
     String AddGBBTempSDDropdown_Xpath="//span[@id='select2-chosen-4']";
     String AddGBBTempSD_Xpath="//ul[@id='select2-results-4']//*[contains(text(),'30 min')]";
     WebElement GBBTempDropdown_btn;
     WebElement GBBTempBU_btn;
     try{
    	    GBBTempDropdown_btn=driver.findElement(By.xpath(AddGBBTempSDDropdown_Xpath));
    	    GBBTempDropdown_btn.click();
    	    AddGBBTemp.log(LogStatus.INFO, "Click on Step Duration Dropdown");
 		
		 		try{
		 			GBBTempBU_btn=driver.findElement(By.xpath(AddGBBTempSD_Xpath));
		 			GBBTempBU_btn.click();
		 			AddGBBTemp.log(LogStatus.INFO, "Get Step Duration Time");
		 			AddGBBTemp.log(LogStatus.PASS, "Select Step Duration");
		 			extent.endTest(AddGBBTemp);
		 		}catch(Exception e){
		 		
		 		      AddGBBTemp.log(LogStatus.FAIL, "Step Duration Time not found");
		 		      extent.endTest(AddGBBTemp);
		    		  extent.flush();
		 		     Assert.fail("Unable to Step Duration  Dropdown box");
		 		 }
 		
    	}catch (Exception e){
    		
    		  AddGBBTemp.log(LogStatus.FAIL, "Unable to Step Duration  Dropdown box");
    		  extent.endTest(AddGBBTemp);
    		  extent.flush();
    		  Assert.fail("Unable to Step Duration  Dropdown box");
    		
    	}
  }
 
 
 @Test (dependsOnMethods="ConnXAddGBBTempSD")
 public void ConnXAddGBBTempEnb(){
	  
	 ExtentTest AddGBBTemp = extent.startTest("ConnXAddGBBTempEnable").assignCategory("Golden Batch Builder");
     String AddGBBTempEnb_id="enabled";
     WebElement GBBTempBU_btn;
     try{
    	 GBBTempBU_btn=driver.findElement(By.id(AddGBBTempEnb_id));
    	 GBBTempBU_btn.click();
    	    AddGBBTemp.log(LogStatus.INFO, "Get Checkbox ID ");
    	    AddGBBTemp.log(LogStatus.PASS, "Enable Checkbox Checked ");
    	    extent.endTest(AddGBBTemp);
		}catch (Exception e){
    		
    		  AddGBBTemp.log(LogStatus.FAIL, "Unable to found Checkbox Id");
    		  extent.endTest(AddGBBTemp);
    		  extent.flush();
       	      Assert.fail("Unable to found Checkbox Id");
    		
    	}
        	
    	
  }
 
 
 @Test (dependsOnMethods="ConnXAddGBBTempEnb")
 public void ConnXAddGBBTempSubmit(){
	  
	 ExtentTest AddGBBTemp = extent.startTest("ConnXAddGBBTempSubmit").assignCategory("Golden Batch Builder");
     String AddGBBTempSubmit_xpath="//button[contains(text(),Submit)]";
     WebElement GBBTempBU_btn;
     try{
    	 GBBTempBU_btn=driver.findElement(By.xpath(AddGBBTempSubmit_xpath));
    	 GBBTempBU_btn.click();
    	    AddGBBTemp.log(LogStatus.INFO, "Get Submit Button Xpath");
    	    AddGBBTemp.log(LogStatus.PASS, "Click on Submitt Button ");
    	    extent.endTest(AddGBBTemp);
		}catch (Exception e){
    		
    		  AddGBBTemp.log(LogStatus.FAIL, "Unable to found Submit Button Xpath");
    		  extent.endTest(AddGBBTemp);
    		  extent.flush();
       	          Assert.fail("Unable to found Submit Button Xpath");
    		
    	}
        	
    	
  }
 
}

