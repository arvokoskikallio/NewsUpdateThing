import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {

        String finalstring = "<b>\n\n";

        //Ask for the url as input
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter URL of the stats page:");
        String url = myObj.nextLine();

        System.out.println("How many players are on the charts?:");
        int amount = Integer.parseInt(myObj.nextLine());

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(url);
        String[] columns = new String[13];

        finalstring += "\n\n\nThis news update covers from Date 1 to Date 2\n\n\nNew Players:\n\n<font color=lime>\nNew Players here\n</font>\n\n";
        int i;

        finalstring += "\n<font color=red>Top 10</font>\n\n\n";

        for (i = 2; i < amount+2; i++) {

            boolean playerHasAlreadyBeenAdded = false;

            try
            {
                int amountOfPRs = Integer.parseInt(driver.findElement(By.cssSelector("body > center > b > big > small > center > b > table > tbody > tr:nth-child(" + i + ") > td:nth-child(3)")).getText());

                String name = driver.findElement(By.cssSelector("body > center > b > big > small > center > b > table > tbody > tr:nth-child(" + i + ") > td:nth-child(2) > font")).getText();
                String position = driver.findElement(By.cssSelector("body > center > b > big > small > center > b > table > tbody > tr:nth-child(" + i + ") > td:nth-child(1) > font")).getText();

                finalstring += "<font color=yellow>#" + position + " " + name + "</font>\n\n";
                playerHasAlreadyBeenAdded = true;
            }

            catch (NumberFormatException e)
            {
                //these people haven't improved
            }

            try
            {
                Double timesheetChecker = Double.parseDouble(driver.findElement(By.cssSelector("body > center > b > big > small > center > b > table > tbody > tr:nth-child(" + i + ") > td:nth-child(7)")).getText());

            }
            catch (NumberFormatException e)
            {
                if(!playerHasAlreadyBeenAdded)
                {
                    String name = driver.findElement(By.cssSelector("body > center > b > big > small > center > b > table > tbody > tr:nth-child(" + i + ") > td:nth-child(2) > font")).getText();
                    String position = driver.findElement(By.cssSelector("body > center > b > big > small > center > b > table > tbody > tr:nth-child(" + i + ") > td:nth-child(1) > font")).getText();

                    finalstring += "<font color=yellow>#" + position + " " + name + "</font>\n\n";
                }
            }

            if(i == 11)
            {
             finalstring += "\n<font color=red>11-25</font>\n\n\n";
            }

            if(i == 26)
            {
                finalstring += "\n<font color=red>25-50</font>\n\n\n";
            }

            if(i == 51)
            {
                finalstring += "\n<font color=red>51-100</font>\n\n\n";
            }

            if(i == 101)
            {
                finalstring += "\n<font color=red>101-200</font>\n\n\n";
            }

            if(i == 201)
            {
                finalstring += "\n<font color=red>200-</font>\n\n\n";
            }
        }


        driver.close();

        //sig
        finalstring += "Site Records:\n\n\n\nNo Glitch:\n\n\n\n- Arvo</b>";

        StringSelection selection = new StringSelection(finalstring);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
}
