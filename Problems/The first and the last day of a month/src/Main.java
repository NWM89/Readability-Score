import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String year = scanner.nextLine();
        String month = scanner.nextLine();
        if (month.length() < 2) {
            month = "0" + month;
        }
        LocalDate localDate = LocalDate.parse(year + "-" + month + "-01");
        System.out.println(year + "-" + month + "-01 " +
                year + "-" + month + "-" + localDate.lengthOfMonth());
    }
}