import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        for (int i = 0; i < inputString.length() - 1; i++) {
            if (inputString.charAt(i) != inputString.charAt(i + 1) - 1) {
                System.out.println("false");
                System.exit(0);
            } else {
                continue;
            }
        }
        System.out.println("true");
    }
}