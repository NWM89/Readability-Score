import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String year = scanner.next();
        String day = scanner.next();
        LocalDate localDate = LocalDate.parse(year + "-01-01");
        localDate = localDate.plusDays(Integer.parseInt(day) - 1);
        System.out.println(localDate.getDayOfMonth() == localDate.lengthOfMonth());
    }
}