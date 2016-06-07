package ConnXLogin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ValidateLogin {

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
  
  

 
}
