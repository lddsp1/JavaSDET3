package test_web.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class ContactPage extends WebBasePage {
    By addMember=By.linkText("添加成员");
    By username=By.name("username");
    By delete=By.linkText("删除");
    By addMenu = By.xpath("//i[@class='member_colLeft_top_addBtn']/parent::a");

    public ContactPage(RemoteWebDriver driver) {
        super(driver);
    }

    public ContactPage addMember(String username, String acctid, String mobile) {
        //todo:
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        new WebDriverWait(MainPage.driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(addMember));
//        //todo: 就算可点击，仍然有一定的概率是点击不成功的
//        new WebDriverWait(MainPage.driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(addMember));


        while(driver.findElements(this.username).size()==0){
            click(addMember);
        }

//        driver.findElement(this.username).sendKeys(username);
//        driver.findElement(By.name("acctid")).sendKeys(acctid);
//        driver.findElement(By.name("mobile")).sendKeys(mobile);
//        driver.findElement(By.cssSelector(".js_btn_save")).click();
        sendKeys(this.username, username);
        sendKeys(By.name("acctid"), acctid);
        sendKeys(By.name("mobile"), mobile);
        click(By.cssSelector(".js_btn_save"));
        return this;
    }

    public ContactPage search(String keyword){
        click(By.xpath("//a[text()='组织架构']"));
        sendKeys(By.id("memberSearchInput"), keyword);
//        driver.findElement(By.id("memberSearchInput")).sendKeys(keyword);
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(delete));
        return this;
    }

    public String getUserName(){
        return driver.findElement(By.cssSelector(".member_display_cover_detail_name")).getText();
    }

    public String getTagName(){
        return driver.findElement(By.cssSelector(".js_search_item_name")).getText();
    }

    public String getdepartName(){
        return driver.findElement(By.xpath("//li[@class='ww_searchResult_item_Curr']/a")).getText();
    }


    public ContactPage delete(){
        click(delete);
        click(By.linkText("确认"));
        click(By.id("clearMemberSearchInput"));
//        driver.findElement(delete).click();
//        driver.findElement(By.linkText("确认")).click();
//        driver.findElement(By.id("clearMemberSearchInput")).click();
        return this;

    }

    public ContactPage importFromFile(URL path){
        //todo:
        System.out.println(path.getPath());

        String path_utf="";
        try {
            path_utf=URLDecoder.decode(path.getFile(), "UTF-8");
            System.out.println(path_utf);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        click(By.cssSelector(".ww_operationBar:nth-child(1) .ww_btn_PartDropdown_left"));
        click(By.linkText("文件导入"));
//        click(By.name("file"));
//        sendKeys(By.name("file"), "C:\\fakepath\\通讯录批量导入模板.xlsx");
        upload(By.name("file"), path_utf);
//        driver.findElement(By.name("file")).sendKeys("/Users/seveniruby/projects/Java3/src/main/resources/通讯录批量导入模板.xlsx");
//        sendKeys(By.name("file"), "C:\\fakepath\\通讯录批量导入模板.xlsx");
        click(By.linkText("导入"));
        click(By.linkText("完成"));

        return this;
    }

    public ContactPage addDepart(String partName, String blTo){
        String xpath = "//div[@class='inputDlg_item']//a[text()=\""+blTo+"\"]";
           click(addMenu);
       // click(By.linkText("添加部门"));

     //   while (driver.findElementsByLinkText("添加部门").size()==0)
         //   click(addMenu);
        click(By.cssSelector(".js_create_party"));
        sendKeys(By.name("name"),partName);
        click(By.cssSelector("div.inputDlg_item:nth-child(3) > a"));
        click(By.xpath(xpath));
        //sendKeys(By.cssSelector("div.inputDlg_item:nth-child(3) > a"), blTo);
        ///html/body/div[3]/div/div[2]/div/form/div[3]/div/div/ul/li/a
        click(By.xpath("//a[@d_ck='submit']"));
        return this;
    }

    /**
     * 添加标签tag
     * @param tagName
     * @param useName
     * @return
     */
    public ContactPage addTag(String tagName, String useName){
        click(By.linkText("标签"));
        while (driver.findElements(By.xpath("//*[text()='标签名称 ']")).size()==0){
            click(By.cssSelector(".member_colLeft_top_addBtnWrap"));
        }
        sendKeys(By.name("name"), tagName);
        click(By.cssSelector("form a.qui_btn"));
        click(By.xpath("//a[text()='"+useName+"']"));
        click(By.linkText("确定"));
        return this;
    }
}
