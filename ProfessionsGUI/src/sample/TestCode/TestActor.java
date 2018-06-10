package sample.TestCode;

import sample.Entity.Actor;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TestActor {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner scanner = new Scanner(System.in);

        Actor actor = new Actor("Sandra", "Bullock", "222222222222");
        actor.addMovieDate("Some boring comedy", "10/10/2010");
        actor.addMovieDate("Some boring drama", "11/10/2012");
        actor.setNetWorth(1000.32);
        actor.setAtLeastOneOscar(true);

        System.out.println(actor.getAllInformation());
        actor.saveInformation("src\\sample\\TextFiles\\Actor.txt");

        //main ends here
    }
}
