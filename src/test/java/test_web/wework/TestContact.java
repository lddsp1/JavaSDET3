package test_web.wework;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import test_web.wework.page.ContactPage;
import test_web.wework.page.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestContact {
    static MainPage main;
    static ContactPage contact;
    @BeforeAll
    static void beforeAll(){
        main=new MainPage();
        contact=main.toContact();
    }
    @Test
    void testAddMember(){
        String username=contact.addMember("3", "3", "15600534763").search("3").getUserName();
        assertEquals(username, "3");
    }

    @Test
    void testSearch(){
        contact.search("3").delete();
    }

    @Test
    void testImportFromFile(){
        //todo: 中文名
        contact.importFromFile(this.getClass().getResource("/通讯录批量导入模板.xlsx"));

    }

    @Test
    void adddepart(){
        String depart = contact.addDepart("Python测开班","霍格沃兹测试学院学习").search("Python测开班").getdepartName();
        assertEquals(depart, "Python测开班");
    }

    @Test
    void addTag(){
        String tag = contact.addTag("内务部", "所有管理员").search("内务部").getTagName();
        assertEquals(tag, "内务部");
    }

    @AfterAll
    static void afterAll(){
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        contact.quit();
    }

}
