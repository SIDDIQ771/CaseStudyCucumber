package stepDef;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage {
	
	WebDriver driver;
	Properties prop;
	Actions actions;
	ExtentReports reports;
	ExtentSparkReporter spark;
    ExtentTest extentTest;
    WebDriverWait wait;

	@Given("login into app")
	public void login_into_app()
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.demoblaze.com/");
		driver.findElement(By.cssSelector("#login2")).click();
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.findElement(By.cssSelector("#loginusername")).sendKeys("Esaki1");
		driver.findElement(By.cssSelector("#loginpassword")).sendKeys("Muthu");
		driver.findElement(By.cssSelector("button[onclick='logIn()']")).click();
	    
	}

	@When("Add an item to cart")
	public void add_an_item_to_cart(DataTable dataTable)
	{
	    List<Map<String,String>> data = dataTable.asMaps(); 
		for(int i=0;i<data.size();i++) 
		{

			String category =data.get(i).get("productCategory"); 
			String name =data.get(i).get("productName"); 
			driver.findElement(By.partialLinkText(category)).click(); 
			driver.findElement(By.partialLinkText(name)).click();
			driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]")).click();
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert(); 
			alert.accept();
			driver.findElement(By.xpath("(//ul/li//a)[1]")).click();
		}
	   
	}
	@Then("items must be added to cart")
	public void items_must_be_added_to_cart(String categories, String item1) 
	{
		
		    extentTest=reports.createTest("ClickMultipleItems");
 	   
			driver.findElement(By.xpath("//li/a[contains(text(),'Home')]")).click();
			
			String strpath="//a[text()='"+categories+"']";
			
			driver.findElement(By.xpath(strpath)).click();
			
			driver.findElement(By.partialLinkText(item1)).click();
			
			driver.findElement(By.xpath("//div/a[@class='btn btn-success btn-lg']")).click();
			
			Alert alert=driver.switchTo().alert();
			
			System.out.println(item1+","+alert.getText());	    
	}
	

	@When("item must be available in cart")
	public void item_must_be_available_in_cart() 
	{
        extentTest=reports.createTest("selectitem");
	    
	    driver.findElement(By.xpath("//h4/a[contains(text(),'Samsung galaxy s6')]")).click();
	    
	    driver.findElement(By.xpath("//div/a[@onclick='addToCart(1)']")).click();
	    
	    Alert alert=driver.switchTo().alert();
	    alert.accept();

		
	   
	}
	@Then("Place Order")
	public void place_order() 
	{
		
		   extentTest=reports.createTest("placeorder");
		   driver.findElement(By.cssSelector("button[data-toggle='modal']")).click(); 
		   driver.findElement(By.cssSelector("#name")).sendKeys("name");
		   driver.findElement(By.cssSelector("#country")).sendKeys("country"); 
		   driver.findElement(By.cssSelector("#city")).sendKeys("city");
		   driver.findElement(By.cssSelector("#card")).sendKeys("card");
		   driver.findElement(By.cssSelector("#month")).sendKeys("month"); 
		   driver.findElement(By.cssSelector("#year")).sendKeys("year"); 
		   driver.findElement(By.cssSelector("button[onclick='purchaseOrder()']")).click(); 
		   driver.findElement(By.cssSelector("button[tabindex='1']")).click();
		   WebElement Postpuschase = driver.findElement(By.xpath("//h2[contains(text(),'Thank You')]"));
		   Assert.assertEquals(Postpuschase.getText(),"Thank You for your Order");
		   driver.findElement(By.cssSelector("button[tabindex='1']")).click();
	    
	}
//	@Then("Purchase items")
//	public void purchase_items() throws CsvValidationException, IOException 
//	{
//		
//			  String path=System.getProperty("user.dir")+"//src//test//resources//TestData//MultiItem.csv";
//			  String[] cols;
//			  CSVReader reader = new CSVReader(new FileReader(path));
//			  ArrayList<Object> dataList=new ArrayList<Object>();
//			  while((cols=reader.readNext())!=null)
//			  {
//				  Object[] record={cols[0]};
//				  dataList.add(record);
//			  }
//			  return;
//			  
//	}


}
