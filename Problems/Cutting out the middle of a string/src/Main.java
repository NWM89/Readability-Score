import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        StringBuilder stringBuilder = new StringBuilder();
        if (inputString.length() % 2 == 0) {
            stringBuilder.append(inputString, 0, inputString.length() / 2 - 1);
            stringBuilder.append(inputString.substring(inputString.length() / 2 + 1));
        } else {
            stringBuilder.append(inputString, 0, Math.round(inputString.length()  / 2));
            stringBuilder.append(inputString.substring(Math.round(inputString.length() / 2)  + 1));
        }
        System.out.println(stringBuilder.toString());
    }
}