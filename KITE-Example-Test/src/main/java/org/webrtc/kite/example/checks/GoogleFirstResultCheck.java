package org.webrtc.kite.example.checks;

import org.webrtc.kite.example.pages.GoogleResultPage;
import io.cosmosoftware.kite.exception.KiteTestException;
import io.cosmosoftware.kite.report.Reporter;
import io.cosmosoftware.kite.report.Status;
import io.cosmosoftware.kite.steps.TestCheck;
import org.openqa.selenium.WebDriver;

import static io.cosmosoftware.kite.util.ReportUtils.saveScreenshotPNG;

public class GoogleFirstResultCheck extends TestCheck {
  final GoogleResultPage searchPage = new GoogleResultPage(this.webDriver);
  final String EXPECTED_RESULT = "CoSMo Software: RTC Experts";
  
  public GoogleFirstResultCheck(WebDriver webDriver) {
    super(webDriver);
  }
  
  @Override
  public String stepDescription() {
    return "Open first result on Google result page and verify the page title";
  }
  
  @Override
  protected void step() throws KiteTestException {
    searchPage.openFirstResult();
    String found = webDriver.getTitle().trim();
    if (!found.equalsIgnoreCase(EXPECTED_RESULT)){
      throw new KiteTestException("The title of the first Google result was not correct: \n" +
        "Expected: " + EXPECTED_RESULT + " but found " + found, Status.FAILED);
    }
    Reporter.getInstance().screenshotAttachment(report, saveScreenshotPNG(webDriver));
  }
}
