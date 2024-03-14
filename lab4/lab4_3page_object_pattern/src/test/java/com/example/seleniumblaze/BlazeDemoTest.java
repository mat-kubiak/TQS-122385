package com.example.seleniumblaze;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

class HomePage {

  private static String PAGE_URL="https://blazedemo.com/";

  private WebDriver driver;
  @FindBy(name="fromPort")
  private WebElement from;

  @FindBy(name="toPort")
  private WebElement to;

  public HomePage(WebDriver driver) {
    this.driver = driver;
    driver.get(PAGE_URL);
    PageFactory.initElements(driver, this);
  }

  public void setAirports(String origin, String destination) {
    from.findElement(By.xpath("//option[. = '" + origin + "']")).click();
    from.findElement(By.xpath("//option[. = '" + destination + "']")).click();
  }

  public FlightPage chooseFlight(int index) {
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(" + index + ") .btn")).click();
    return new FlightPage(driver);
  }
}

class FlightPage {
  private WebDriver driver;

  @FindBy(id="inputName")
  private WebElement nameField;

  @FindBy(id="address")
  private WebElement addressField;

  @FindBy(id="city")
  private WebElement cityField;

  @FindBy(id="state")
  private WebElement stateField;

  @FindBy(id="zipCode")
  private WebElement zipCodeField;

  @FindBy(id="cardType")
  private WebElement cardTypeField;

  @FindBy(id="creditCardNumber")
  private WebElement cardNumberField;

  @FindBy(id="creditCardMonth")
  private WebElement cardMonthField;

  @FindBy(id="creditCardYear")
  private WebElement cardYearField;

  @FindBy(id="nameOnCard")
  private WebElement cardNameField;

  @FindBy(id="rememberMe")
  private WebElement rememberMeButtom;

  @FindBy(css=".btn-primary")
  private WebElement confirmButton;

  public FlightPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void setPersonalDetails(String name, String address, String city, String state, String zipCode) {
    nameField.sendKeys(name);
    addressField.sendKeys(address);
    cityField.sendKeys(city);
    stateField.sendKeys(state);
    zipCodeField.sendKeys(zipCode);
  }

  public void setCardDetails(String type, String number, String month, String year, String name) {
    cardTypeField.findElement(By.xpath("//option[. = '" + type + "']")).click();
    cardNumberField.sendKeys(number);
    cardMonthField.sendKeys(month);
    cardYearField.sendKeys(year);
    cardNameField.sendKeys(name);
  }

  public void setRememberMe() {
    rememberMeButtom.click();
  }

  public ConfirmPage confirm() {
    confirmButton.click();
    return new ConfirmPage(driver);
  }

}

class ConfirmPage {

  private WebDriver driver;

  @FindBy(css="tr:nth-child(2) > td:nth-child(2)")
  private WebElement status;
  @FindBy(css="tr:nth-child(3) > td:nth-child(2)")
  private WebElement amount;
  @FindBy(css="tr:nth-child(4) > td:nth-child(2)")
  private WebElement cardNumber;
  @FindBy(css="tr:nth-child(5) > td:nth-child(2)")
  private WebElement expiration;
  @FindBy(css="tr:nth-child(6) > td:nth-child(2)")
  private WebElement authCode;

  public ConfirmPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public String getStatus() {
    return status.getText();
  }

  public String getAmount() {
    return amount.getText();
  }

  public String getCardNumber() {
    return cardNumber.getText();
  }

  public String getExpiration() {
    return expiration.getText();
  }

  public String getAuthCode() {
    return authCode.getText();
  }

  public String getTitle() {
    return driver.getTitle();
  }
}


@ExtendWith(SeleniumJupiter.class)
public class BlazeDemoTest {

  @Test
  public void testLogging(WebDriver driver) {

    HomePage homePage = new HomePage(driver);
    homePage.setAirports("Mexico City", "Berlin");

    FlightPage flightPage = homePage.chooseFlight(3);
    flightPage.setPersonalDetails("John Lennon", "Arkham, 13th avenue 10", "Arkham", "Mississipi", "12345");
    flightPage.setCardDetails("American Express", "12345678901234567", "06", "1984", "John Lennon");
    flightPage.setRememberMe();

    ConfirmPage confirmPage = flightPage.confirm();
    assertThat(confirmPage.getStatus(), is("PendingCapture"));
    assertThat(confirmPage.getAmount(), is("555 USD"));
    assertThat(confirmPage.getCardNumber(), is("xxxxxxxxxxxx1111"));
    assertThat(confirmPage.getExpiration(), is("11 /2018"));
    assertThat(confirmPage.getAuthCode(), is("888888"));
    assertThat(confirmPage.getTitle(), is("BlazeDemo Confirmation"));
  }
}
