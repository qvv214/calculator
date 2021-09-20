import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Handler handler = new Handler();
        Scanner sc = new Scanner(System.in);
        
        String inputString = sc.nextLine().replaceAll("\\s", "");

        try {
            handler.setReversePolishNotation(inputString);
            String reversePolishNotation = handler.getReversePolishNotation();
            System.out.println(handler.calculations(reversePolishNotation));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
