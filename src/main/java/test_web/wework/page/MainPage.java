package test_web.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class MainPage extends WebBasePage {

    public MainPage() {
        super();
//        System.setProperty("webdriver.gecko.driver", "/Users/seveniruby/ke/java_3/selenium/drivers/geckodriver");

        String url = "https://work.weixin.qq.com/wework_admin/frame";
        BufferedReader bufferedReader = null;
        driver.get(url);
        try {
            String  path_cook = URLDecoder.decode(this.getClass().getResource("/cookies.txt").getFile(),"utf-8");
            FileReader fileReader = new FileReader(path_cook);
            bufferedReader = new BufferedReader(fileReader);
            String readl;
            while ((readl = bufferedReader.readLine()) != null){
              //  System.out.println(readl);
                StringTokenizer stt = new StringTokenizer(readl,";");
                String domain = stt.nextToken();
                String name = stt.nextToken();
                String value = stt.nextToken();
                //  value = "222";
                String beforE = stt.nextToken();
                Date expiry = null;
                if(!beforE.equals("null")){
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    expiry = sdf.parse(beforE);
                }
                String path = stt.nextToken();
                boolean isSecure = Boolean.parseBoolean(stt.nextToken());
                Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                driver.manage().addCookie(cookie);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        driver.get(url);
    }



    public ContactPage toContact() {
        //todo:
        click(By.cssSelector("#menu_contacts"));
//        driver.findElement(By.cssSelector("#menu_contacts")).click();
        return new ContactPage(driver);
    }


}
