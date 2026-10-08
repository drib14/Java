import java.util.*;
import OOP.Human;

public class main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Human drib = new Human(null, 0, null, null);

        System.out.print("Enter your full name: ");
        drib.fullName = scan.nextLine();

        System.out.print("Enter your age: ");
        drib.age = scan.nextInt();

        scan.nextLine();

        System.out.print("Enter your occupation: ");
        drib.occupation = scan.nextLine();

        System.out.print("Enter your nationality: ");
        drib.nationality = scan.nextLine();

        drib.introduce();

        scan.close();
    }
}