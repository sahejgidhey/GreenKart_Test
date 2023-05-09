package GreenKart.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport
{
	
	public static ExtentReports getreports()
	{
		
		String path = System.getProperty("user.dir") + "\\reports\\report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setReportName("GreenKart Automation");
		reporter.config().setDocumentTitle("Test results");
		
		ExtentReports extent = new ExtentReports();
		
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Harsahej singh");
		
		return extent ; 
				
	}
	
}
