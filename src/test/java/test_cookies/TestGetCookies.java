package test_cookies;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class TestGetCookies {
    static WebDriver driver;

    @BeforeAll
    static void init(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress","127.0.0.1:9920");
        driver = new ChromeDriver(options);
    }

    @Test
    void testcookies(){
        BufferedWriter bufferedWriter = null;
        Set<Cookie> cookies = driver.manage().getCookies();
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/cookies.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            for(Cookie cookie:cookies){
                bufferedWriter.write(cookie.getDomain()+";");
                bufferedWriter.write(cookie.getName()+";");
                bufferedWriter.write(cookie.getValue()+";");
                bufferedWriter.write(String.valueOf(cookie.getExpiry())+";");
                bufferedWriter.write(cookie.getPath()+";");
                bufferedWriter.write(String.valueOf(cookie.isSecure()));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
