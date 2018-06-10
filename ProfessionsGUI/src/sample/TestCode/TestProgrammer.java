package sample.TestCode;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import sample.Entity.Programmer;

public class TestProgrammer {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner scanner = new Scanner(System.in);

        Programmer programmer = new Programmer("Aleksandar", "Boev", "111111111111");

        programmer.getProgrammingLanguages().add("Pascal");
        programmer.getProgrammingLanguages().add("C#");
        programmer.getProgrammingLanguages().add("Java");
        programmer.getProgrammingLanguages().add("JavaScript");
        System.out.println(programmer.getProgrammingLanguagesAsString());

        programmer.getWebsiteUserName().put("hackerrank.com", "MrCoder");
        programmer.getWebsiteUserName().put("github.com", "MrCoder");
        programmer.getWebsiteUserName().put("softuni.bg", "Coder109");

        System.out.println(programmer.getWebsiteUserNameAsString());

        //hot to get the address: right click on text file --> Copy path --> paste
        // copy relative path doesn't work for some reason
        programmer.saveToFile("src\\sample\\TextFiles\\Programmer.txt");
        //main ends here
    }
}
