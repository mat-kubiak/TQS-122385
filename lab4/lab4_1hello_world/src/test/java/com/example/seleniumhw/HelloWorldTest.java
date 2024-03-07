package com.example.seleniumhw;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
class HelloWorldTest {

    // the text matched is from boost c++ library site, on the right side panel below the 'welcome' section
    @Test
    void test(ChromeDriver driver) {
        driver.get("https://www.boost.org/");

        assertThat(driver.findElement(By.id("boost-section-menu")).getText()).contains("Introduction");
    }

}
