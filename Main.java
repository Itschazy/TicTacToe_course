package tictactoe;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] input = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        parseField(input);
        printField();
        char player = 'X';
        int x = 0;
        int y = 0;
        while (true) {

            while (true) {
                System.out.println("Enter the coordinates:");
                String numbers = sc.nextLine();
                String[] splitNumbers = numbers.split(" ");
                try {
                    x = Integer.parseInt(splitNumbers[0]);
                    y = Integer.parseInt(splitNumbers[1]);
                } catch (NumberFormatException ne) {
                    System.out.println("You should enter numbers!");
                } catch (IndexOutOfBoundsException ne) {
                    continue;
                }
                if (x <= 3 && x >= 1 && y <= 3 && y >= 1) {
                    int i = Math.abs(x - 1);
                    int j = Math.abs(3 - y);
                    if (field[j][i] != ' ') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        field[j][i] = player;
                        player = (char) ('§' - player);
                        break;
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            }

            printField();
            if (!Check()) {
                break;
            }
        }
    }

    protected static void borders() {
        System.out.println("---------");
    }


    public static boolean Check() {
        int count = 0;
        int count_X = 0;
        int count_O = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' ') {
                    count++;
                } else if (field[i][j] == 'X') {
                    count_X++;
                } else if (field[i][j] == 'O') {
                    count_O++;
                }
            }
        }
        boolean result_X = winChecker('X');
        boolean result_O = winChecker('O');

        if (!result_X && !result_O && count_X + count_O == 9) {
            System.out.println("Draw");
        } else if (result_O && !result_X) {
            System.out.println("O wins");
        } else if (result_X && !result_O) {
            System.out.println("X wins");
        } else if (count_X + count_O < 9 && (Math.abs(count_X - count_O) <= 1 || Math.abs(count_O - count_X) <= 1) && !result_O && !result_X) {
            //System.out.println("Game not finished");
            return true;
        } else if (result_O && result_X || Math.abs(count_X - count_O) > 1 || Math.abs(count_O - count_X) > 1) {
            System.out.println("Impossible");
        }
        return false;
    }
    public static void printField(){
        borders();
        for (int i = 0; i < 3; i ++) {
            System.out.println(String.format("| %c %c %c |", field[i][0], field[i][1], field[i][2]));
        }
        borders();
    }

    public static boolean winChecker(char j) {

        // СТРОКИ И СТОЛБЦЫ
        for (int i = 0; i < 3; i++){
            if ( field[i][0] == j && field[i][1] == j && field[i][2] == j){
                return true;
            }
            if ( field[0][i] == j && field[1][i] == j && field[2][i] == j){
                return true;
            }
        }

        // ДИАГОНАЛИ

        if (field[0][0] == j && field[1][1] == j && field[2][2] == j) {
            return true;
        } else if (field[0][2] == j && field[1][1] == j && field[2][0] == j) {
            return true;
        }
        return false;
    }

    static char[][] field = new char[3][3];
    public static void parseField(char[] input){
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = input[k];
                k++;
            }
        }
    }
}