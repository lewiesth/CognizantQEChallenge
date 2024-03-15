package TestSuites;

import Utilities.TestcaseSetup;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import Utilities.CommonTestSteps;
import org.openqa.selenium.interactions.Actions;
import io.qameta.allure.Description;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionalTests extends TestcaseSetup {
    @Test
    @Tag("Regression")
    @DisplayName("Add single task")
    @Description("Test to add task to todo application")
    @Epic("Epic-001")
    @Feature("Core feature")
    public void TC001_addSingleTodoTest() throws IOException {
        String todo=CommonTestSteps.getFirstValueFromExcel();

        CommonTestSteps.addTodo(todo);
        List<WebElement> todoAsList=CommonTestSteps.getTodoList();

        String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);

        assertEquals(todo,todoAsList.getFirst().findElement(By.xpath("div/label[@data-testid='todo-item-label']")).getText(),"Actual todo task do not match!");
    }
    @Test
    @Tag("Regression")
    @Tag("Smoke")
    @DisplayName("Add multiple tasks")
    @Description("Test to add multiple tasks to todo application")
    @Epic("Epic-001")
    @Feature("Core feature")
    public void TC002_addMultipleTodoTest() throws IOException {
        List<String> todoToAddList=CommonTestSteps.getAllValuesFromExcel();
        for(String valueToAdd:todoToAddList)
            CommonTestSteps.addTodo(valueToAdd);

        List<WebElement> todoAsList=CommonTestSteps.getTodoList();
//      Check that task displayed is the same as input, and ordered accordingly
        for(int i=0;i<todoAsList.size();i++) {
            assertEquals(todoToAddList.get(i),todoAsList.get(i).findElement(By.xpath("div/label[@data-testid='todo-item-label']")).getText(),"Actual todo task do not match!");
        }
    }
    @Test
    @Tag("Regression")
    @Tag("Smoke")
    @DisplayName("Edit task from list")
    @Description("Test to edit task in todo application")
    @Epic("Epic-001")
    @Feature("Core feature")
    public void TC003_editTaskTest() throws InterruptedException, IOException {
        String edittedMessage=" EdittedAddonTodo";
        List<String> todoToAddList=CommonTestSteps.getAllValuesFromExcel();
        for(String valueToAdd:todoToAddList)
            CommonTestSteps.addTodo(valueToAdd);

        List<WebElement> todoAsList=CommonTestSteps.getTodoList();
        WebElement elementToEdit=todoAsList.getFirst().findElement(By.xpath("div/label[@data-testid='todo-item-label']"));

        Actions action = new Actions(driver);
        action.doubleClick(elementToEdit).perform();

        driver.findElement(By.xpath("//li/div/div[@class='input-container']/input")).sendKeys(edittedMessage);
        driver.findElement(By.xpath("//li/div/div[@class='input-container']/input")).sendKeys(Keys.RETURN);

        todoAsList=CommonTestSteps.getTodoList();
        assertTrue(todoAsList.getFirst().findElement(By.xpath("div/label[@data-testid='todo-item-label']")).getText().contains(edittedMessage),"Actual todo task do not match!");
    }
    @Test
    @Tag("Regression")
    @Tag("Smoke")
    @DisplayName("Delete single task")
    @Description("Test to delete task in todo application")
    @Epic("Epic-001")
    @Feature("Core feature")
    public void TC004_deleteSingleTaskTest() throws IOException {
        List<String> todoToAddList=CommonTestSteps.getAllValuesFromExcel();
        for(String valueToAdd:todoToAddList)
            CommonTestSteps.addTodo(valueToAdd);

        List<WebElement> todoAsList=CommonTestSteps.getTodoList();
        CommonTestSteps.deleteTodoTask(todoAsList.get(1));

        assertEquals(2,CommonTestSteps.getTodoList().size(),"Number of tasks do not match expected!");
    }
    @Test
    @Tag("Regression")
    @DisplayName("Todo counter verification")
    @Description("Test to verify functionality of task counter in todo application")
    @Epic("Epic-001")
    @Feature("Auxiliary feature")
    public void TC005_todoCountTest() throws IOException {
        List<String> todoToAddList=CommonTestSteps.getAllValuesFromExcel();
        for(String valueToAdd:todoToAddList)
            CommonTestSteps.addTodo(valueToAdd);
//      Checking that task counter works
        List<WebElement> todoAsList=CommonTestSteps.getTodoList();
        assertEquals(todoAsList.size()+" items left!",driver.findElement(By.xpath("//span[@class='todo-count']")).getText(),"Number of tasks do not match!");
//      Checking that task counter works when delete task
        CommonTestSteps.deleteTodoTask(todoAsList.get(1));
        assertEquals(todoAsList.size()-1+" items left!",driver.findElement(By.xpath("//span[@class='todo-count']")).getText(),"Number of tasks do not match!");
//      Checking that task counter works when marking task as done
        todoAsList = CommonTestSteps.getTodoList();
        todoAsList.getFirst().findElement(By.xpath("div/input[@class='toggle']")).click();
        assertEquals(todoAsList.size()-1+" item left!",driver.findElement(By.xpath("//span[@class='todo-count']")).getText(),"Number of tasks do not match!");
    }
    @Test
    @Tag("Regression")
    @Tag("Smoke")
    @DisplayName("Filter task by state")
    @Description("Testing the filter functionality in todo application")
    @Epic("Epic-001")
    @Feature("Auxiliary feature")
    public void TC006_filterOptionsTest() throws IOException {
        List<String> todoToAddList=CommonTestSteps.getAllValuesFromExcel();
        for(String valueToAdd:todoToAddList)
            CommonTestSteps.addTodo(valueToAdd);

        List<WebElement> todoAsList=CommonTestSteps.getTodoList();
        todoAsList.getFirst().findElement(By.xpath("div/input[@class='toggle']")).click();

        driver.findElement(By.xpath("//a[@href='#/active']")).click();
        todoAsList=CommonTestSteps.getTodoList();
        assertEquals(2,todoAsList.size(),"Number of tasks do not match!");

        driver.findElement(By.xpath("//a[@href='#/completed']")).click();
        todoAsList=CommonTestSteps.getTodoList();
        assertEquals(1,todoAsList.size(),"Number of tasks do not match!");
    }
    @Test
    @Tag("Regression")
    @Tag("Smoke")
    @DisplayName("Delete all completed task verification")
    @Description("Test deletion of completed task button in todo application")
    @Epic("Epic-001")
    @Feature("Auxiliary feature")
    public void TC007_clearCompletedTest() throws IOException {
        List<String> todoToAddList=CommonTestSteps.getAllValuesFromExcel();
        for(String valueToAdd:todoToAddList)
            CommonTestSteps.addTodo(valueToAdd);

        List<WebElement> todoAsList=CommonTestSteps.getTodoList();
        todoAsList.getFirst().findElement(By.xpath("div/input[@class='toggle']")).click();

        driver.findElement(By.xpath("//button[@class='clear-completed']")).click();
        todoAsList=CommonTestSteps.getTodoList();
        assertEquals(2,todoAsList.size(),"Number of tasks do not match!");
    }
    @Test
    @Tag("Regression")
    @Tag("Smoke")
    @DisplayName("Mark all tasks as completed")
    @Description("Test toggle to mark all tasks as completed in todo application")
    @Epic("Epic-001")
    @Feature("Auxiliary feature")
    public void TC008_markAllAsCompletedTest() throws IOException {
        List<String> todoToAddList=CommonTestSteps.getAllValuesFromExcel();
        for(String valueToAdd:todoToAddList)
            CommonTestSteps.addTodo(valueToAdd);

        driver.findElement(By.xpath("//input[@class='toggle-all']")).click();
        assertEquals("0 items left!",driver.findElement(By.xpath("//span[@class='todo-count']")).getText(),"There are still tasks left in counter!");
    }
}

//TODO
// Write a test plan/document
// Add readme
// Push to git
