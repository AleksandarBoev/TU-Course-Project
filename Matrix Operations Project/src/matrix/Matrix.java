package matrix;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Matrix { // this class will resemble the Math class (it will hold only methods. Instances will not be needed)

    //______________________________________Setting up a matrix START_______________________________________________
    public static int[][] getMatrixFromFile(String filePath, String splitter) throws FileNotFoundException { // WORKS
        try {
            File file = new File(filePath);

            Scanner fileInformer = new Scanner(file, "UTF-8"); //just to get the matrix dimensions
            int rows = 0;
            int cols = 0;
            while (fileInformer.hasNext()) {
                rows++;
                cols = Arrays.stream(fileInformer.nextLine().split(splitter)).mapToInt(Integer::parseInt).toArray().length;
            } // TODO think of a better way of counting columns, what if not every row has the same amount of columns? Use a dynamic matrix?
            fileInformer.close(); // TODO can't I use just one file reader twice?


            Scanner fileReader = new Scanner(file, "UTF-8");
            int[][] matrix = new int[rows][cols];

            int row = 0;
            while (fileReader.hasNext()) {
                matrix[row] = Arrays.stream(fileReader.nextLine().split(splitter)).mapToInt(Integer::parseInt).toArray();
                row++;
            }
            fileReader.close();

            return matrix;

        } catch (Exception e) {
            return null;
        }
        // Possible reasons this method would return null:
        //1) The columns of the rows are not equal (if the last column is the smallest there will not be a thrown exception, but the result would be incorrect)
        //2) The file contains anything else other than integers and the splitter
        //3) The specified file path does not exist
    }

    public static void setBasicValues(int[][] matrix) { //WORKS
        int counter = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                counter++;
                matrix[row][col] = counter;
            }
        }
    }

    public static void typeInValues(int[][] matrix) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.printf("[%d][%d] = ", row, col);
                String userInput = reader.readLine();

                try {
                    matrix[row][col] = Integer.parseInt(userInput);
                } catch (NumberFormatException nfe) {
                    System.out.println("\"" + userInput + "\" is not an integer number. Try again");
                    col--;
                }

            }
        }
    }

    //______________________________________Setting up a matrix END_______________________________________________

    //______________________________________Rows and cols methods START___________________________________________
    public static int[] getRow(int[][] matrix, int row) { //WORKS
        return matrix[row];
    }

    public static int[] getCol(int[][] matrix, int col) { //WORKS
        int[] answer = new int[matrix.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = matrix[i][col];
        }
        return answer;
    }

    public static int getColMax(int[][] matrix, int col) { //WORKS
        int max = Integer.MIN_VALUE;
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row][col] > max) {
                max = matrix[row][col];
            }
        }

        return max;
    }

    public static int getColMaxIndex(int[][] matrix, int col) { // TODO not sure this method should be here
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row][col] > max) {
                max = matrix[row][col];
                maxIndex = row;
            }
        }

        return maxIndex;
    }

    public static int getColMin(int[][] matrix, int col) { //WORKS
        int min = Integer.MAX_VALUE;
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row][col] < min) {
                min = matrix[row][col];
            }
        }

        return min;
    }

    public static int getRowMin(int[][] matrix, int row) { // WORKS
        int min = Integer.MAX_VALUE;
        for (int col = 0; col < matrix[row].length; col++) {
            if (matrix[row][col] < min) {
                min = matrix[row][col];
            }
        }

        return min;
    }

    public static int getRowMax(int[][] matrix, int row) { // WORKS
        int max = Integer.MIN_VALUE;
        for (int col = 0; col < matrix[row].length; col++) {
            if (matrix[row][col] > max) {
                max = matrix[row][col];
            }
        }

        return max;
    }
    //______________________________________Rows and cols methods END___________________________________________

    //______________________________________Printing and saving matrix___________________________________________

    public static int getSum(int[][] matrix) { //WORKS
        int sum = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                sum += matrix[row][col];
            }
        }

        return sum;
    }


    public static void printMatrix(int[][] matrix, String splitter) { // WORKS //TODO make it aligned
        if (matrix == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                sb.append(matrix[row][col]);
                if (col != matrix[row].length - 1) {
                    sb.append(splitter);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void saveMatrixToFile(int[][] matrix, String splitter, String filePath) throws FileNotFoundException, UnsupportedEncodingException {

        File file = new File(filePath);
        PrintStream fileWriter = new PrintStream(filePath, "UTF-8");

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (col != matrix[row].length - 1) {
                    fileWriter.print(matrix[row][col] + splitter);
                } else {
                    fileWriter.print(matrix[row][col]);
                }
            }
            fileWriter.println();
        }
        fileWriter.close();

    }

    //______________________________Matrix operations START___________________________________
    public static int[][] getTransposedMatrix(int[][] matrix) { //WORKS
        int[][] result = new int[matrix[0].length][matrix.length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = matrix[col][row];
            }
        }

        return result;
    }

    public static int multiplyVectors(int[] vector1, int[] vector2) { //WORKS
        if (vector1.length != vector2.length) { // the vectors MUST be the same length
            return Integer.MIN_VALUE;
        }

        int answer = 0;
        for (int i = 0; i < vector1.length; i++) {
            answer += vector1[i] * vector2[i];
        }
        return answer;
    }

    public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) { //WORKS
        if (matrix1[0].length != matrix2.length) { //those are the requirements
            return null;
        }

        int[][] newMatrix = new int[matrix1.length][matrix2[0].length]; // that's how it's done

        for (int matrix1Row = 0; matrix1Row < matrix1.length; matrix1Row++) {
            for (int matrix2Col = 0; matrix2Col < matrix2[0].length; matrix2Col++) {
                newMatrix[matrix1Row][matrix2Col] = multiplyVectors(matrix1[matrix1Row], getCol(matrix2, matrix2Col));
                //purvi red na purva matrica * purva kolona na vtora matrica = chislo purvi red, purva kolona na nova matrica
                //purvi red na purva matrica * vtora kolona na vtora matrica = chislo purvi red, vtora kolona na nova matrica
                // ... vtori red na purva matrica * purva kolona na vtora matrica = chislo vtori red, puvra kolona na nova matrica
            }
        }

        return newMatrix;
    }

    public static void subtractRow(int[][] matrix, int row, int amount) { //WORKS
        for (int col = 0; col < matrix[row].length; col++) {
            matrix[row][col] -= amount;
        }
    }

    public static void subtractCol(int[][] matrix, int col, int amount) { //WORKS
        for (int row = 0; row < matrix.length; row++) {
            matrix[row][col] -= amount;
        }
    }

    //____________________Matrix operations END_____________________________________


    //____________________________Analise current matrix START____________________________

    public static int getMin(int[][] matrix) { //WORKS
        int min = Integer.MAX_VALUE;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] < min) {
                    min = matrix[row][col];
                }
            }
        }

        return min;
    }

    public static int getMax(int[][] matrix) { //WORKS
        int max = Integer.MIN_VALUE;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] < max) {
                    max = matrix[row][col];
                }
            }
        }

        return max;
    }

    //____________________________Analise current matrix END____________________________

    //____________________________More operations_______________________________________


    public static int getRowSum(int[][] matrix, int row) { //works
        int sum = 0;
        for (int col = 0; col < matrix[row].length; col++) {
            sum += matrix[row][col];
        }
        return sum;
    }
    
    public static int[] getRowsSums(int[][] matrix) { //works
        int[] result = new int[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            result[row] = getRowSum(matrix, row);
        }
        return result;
    }

    public static void printRow(int[] rowArray) { //works
        for (int col = 0; col <rowArray.length; col++) {
            System.out.print(rowArray[col] + " ");
        }
        System.out.println();
    }

    public static int getMin(int[] array) { //works
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }

    public static int getMax(int[] array) { //works
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    //____________________________More operations_______________________________________
    //TODO add the following: add number to all matrix elements, same but multiply, sum two matrices, add to row/col
}
