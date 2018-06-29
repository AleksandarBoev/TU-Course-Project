package matrix;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;
import java.util.Arrays;
//import java.util.regex.Matcher;

public class TestMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String saveTo = "src\\textFiles\\save.txt";

        int[][] matrix = new int[2][2];
        Matrix.typeInValues(matrix); // allows user to type in the values
//        Matrix.setBasicValues(matrix); // sets basic values (for testing purposes)
        System.out.println("The matrix you have entered: ");
        Matrix.printMatrix(matrix, " "); //prints the matrix on the console
        Matrix.saveMatrixToFile(matrix, " | ", saveTo); // saves matrix to a specified textFile

        String loadFrom = "src\\textFiles\\load.txt";
        int[][] matrixFromFile = Matrix.getMatrixFromFile(loadFrom, " ");
        System.out.println("The matrix, loaded from the file: ");
        Matrix.printMatrix(matrixFromFile, " ");

        System.out.println("Sum of all elements from first matrix: " + Matrix.getSum(matrix)); //sum of all elements of matrix
        int[] rowsSums = Matrix.getRowsSums(matrix); //sums of all rows of a matrix, saved in an array
        System.out.print("Sums of rows ot the matrix: ");
        System.out.println(Arrays.toString(rowsSums));
        System.out.println();
        System.out.println("Biggest element in the array: " + Matrix.getMax(rowsSums));
        System.out.println("Smallest element in the array: " + Matrix.getMin(rowsSums));


        //main ends here
    }
}
