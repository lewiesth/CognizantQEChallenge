package Utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Utilities.TestcaseSetup.driver;

public class CommonTestSteps {
    public static void addTodo(String todoToAdd) {
//        Enter tasks into input field and hitting enter key
        driver.findElement(By.id("todo-input")).sendKeys(todoToAdd);
        driver.findElement(By.id("todo-input")).sendKeys(Keys.RETURN);
    }
    public static List<WebElement> getTodoList() {
//        Getting list of task from current view
        WebElement listOfTodo=driver.findElement(By.xpath("//ul[@class='todo-list']"));
        return listOfTodo.findElements(By.xpath("//li[@data-testid='todo-item']"));
    }
    public static void deleteTodoTask(WebElement elementToDelete) {
//        Click on delete single task
        Actions action = new Actions(driver);
        action.moveToElement(elementToDelete.findElement(By.xpath("div/label[@data-testid='todo-item-label']"))).perform();
        elementToDelete.findElement(By.xpath("div/button[@class='destroy']")).click();
    }
    public static String getFirstValueFromExcel() throws IOException {
//        Reads only first data from first column, ignoring header
        XSSFSheet sheet=readFromExcel();
        return sheet.getRow(1).getCell(0).getStringCellValue();
    }
    public static List<String> getAllValuesFromExcel() throws IOException {
//        Reads all values from first column, ignoring header
        XSSFSheet sheet=readFromExcel();
        List<String> getAllColData=new ArrayList<>();
        int rowNum=0;
        for(Row r:sheet) {
            if(rowNum==0) {
                rowNum++;
                continue;
            }
            Cell c=r.getCell(0);
            getAllColData.add(c.getStringCellValue());
        }
        return getAllColData;
    }
    public static XSSFSheet readFromExcel() throws IOException {
        String dir = System.getProperty("user.dir");
//        Opens excel and gets sheet
        FileInputStream fs = new FileInputStream(dir+"\\src\\test\\java\\TestData\\TestData for Todo Application.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        fs.close();
        return sheet;
    }
}
