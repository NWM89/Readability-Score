import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        String[] outputString = inputString.split(" ");
        int max = 0;
        int indx = 0;
        for (int i = 0; i < outputString.length; i++) {
            if (outputString[i].length() > max) {
                max = outputString[i].length();
                indx = i;
                
            }
        }
        System.out.println(outputString[indx]);
    }
}