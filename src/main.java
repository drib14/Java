import java.util.Scanner;
import OOP.Human;
import OOP.Employee;
import OOP.Developer;
import OOP.Supervisor;
import OOP.Payable;

/**
 * ============================================================================
 * OBJECT-ORIENTED PROGRAMMING (OOP) - THE 4 PILLARS DEMONSTRATION SYSTEM
 * ============================================================================
 * 
 * This program demonstrates the 4 fundamental pillars of OOP in Java:
 * 
 * 1. ENCAPSULATION : Data hiding (private fields) and safe access via Getters/Setters.
 * 2. INHERITANCE   : Code reusability via parent-child class hierarchy (extends).
 * 3. POLYMORPHISM  : "Many forms" via Method Overriding (runtime) and Overloading (compile-time).
 * 4. ABSTRACTION   : Hiding complex implementation details using Abstract Classes and Interfaces.
 * ============================================================================
 */
public class main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        printHeader();

        // ====================================================================
        // PILLAR 1: ENCAPSULATION
        // ====================================================================
        System.out.println("\n=======================================================");
        System.out.println(">>> PILLAR 1: ENCAPSULATION (Data Protection & Validation)");
        System.out.println("=======================================================");
        System.out.println("Encapsulation hides sensitive object variables using 'private'.");
        System.out.println("Access is only granted through public Getters and Setters that validate data.");
        System.out.println();

        // Creating a Developer object
        Developer dev = new Developer("Drib Ramirez", 22, "Filipino", "DEV-101", 3500.0, "Java");

        System.out.println("1. Reading data safely using Getters:");
        System.out.println("   Developer Name: " + dev.getFullName());
        System.out.println("   Developer Age : " + dev.getAge());
        System.out.println("   Primary Tech  : " + dev.getPrimaryLanguage());

        System.out.println("\n2. Testing Setter validation with an INVALID age (-5):");
        // dev.age = -5; // COMPILER ERROR! Field 'age' is private and cannot be touched directly.
        dev.setAge(-5);  // Setter catches invalid value and applies safety fallback
        System.out.println("   Age after invalid attempt: " + dev.getAge() + " (Protected by validation!)");

        System.out.println("\n3. Testing Setter with a VALID age (23):");
        dev.setAge(23);
        System.out.println("   Updated Age: " + dev.getAge());


        // ====================================================================
        // PILLAR 2: INHERITANCE
        // ====================================================================
        System.out.println("\n=======================================================");
        System.out.println(">>> PILLAR 2: INHERITANCE (Code Reuse & Hierarchy)");
        System.out.println("=======================================================");
        System.out.println("Hierarchy: Human (Grandparent) -> Employee (Parent) -> Supervisor (Child)");
        System.out.println("Supervisor reuses fullName, age, nationality, and sleep() without rewriting them!");
        System.out.println();

        Supervisor sup = new Supervisor("Maria Santos", 29, "Filipino", "SUP-202", 4200.0, 12);

        System.out.println("1. Method inherited directly from Grandparent (Human):");
        System.out.print("   ");
        sup.sleep(); // Inherited from Human!

        System.out.println("\n2. Method extended by Child with super.introduce():");
        sup.introduce();


        // ====================================================================
        // PILLAR 3: POLYMORPHISM
        // ====================================================================
        System.out.println("\n=======================================================");
        System.out.println(">>> PILLAR 3: POLYMORPHISM (\"Many Forms\")");
        System.out.println("=======================================================");
        
        System.out.println("[A] RUNTIME POLYMORPHISM (Method Overriding):");
        System.out.println("We put different employee types in a single Employee[] array.");
        System.out.println("When we call emp.work(), each executes its OWN unique version!\n");

        Employee[] team = { dev, sup };

        for (Employee emp : team) {
            System.out.println("Working as " + emp.getClass().getSimpleName() + ":");
            emp.work();                 // Calls Developer.work() or Supervisor.work() dynamically!
            emp.performDailyRoutine();  // Calls Developer.performDailyRoutine() or Supervisor.performDailyRoutine()!
            System.out.println();
        }

        System.out.println("[B] COMPILE-TIME POLYMORPHISM (Method Overloading):");
        System.out.println("Methods share the SAME name, but have DIFFERENT parameters.\n");

        System.out.println("Developer overloaded work() calls:");
        dev.work();                          // Overload 0: Default
        dev.work("Authentication Microservice"); // Overload 1: String parameter
        dev.work(8);                         // Overload 2: int parameter

        System.out.println("\nSupervisor overloaded conductMeeting() calls:");
        sup.conductMeeting();                // Overload 0: Default
        sup.conductMeeting("Q3 KPI Reviews"); // Overload 1: String parameter
        sup.conductMeeting("Escalation Protocol", 45); // Overload 2: String + int parameters


        // ====================================================================
        // PILLAR 4: ABSTRACTION
        // ====================================================================
        System.out.println("\n=======================================================");
        System.out.println(">>> PILLAR 4: ABSTRACTION (Hiding Complexity & Contracts)");
        System.out.println("=======================================================");
        System.out.println("1. Abstract Classes:");
        System.out.println("   - You CANNOT instantiate 'new Human()' or 'new Employee()'.");
        System.out.println("   - They are conceptual blueprints requiring concrete subclasses.");
        System.out.println();
        System.out.println("2. Interface Contract (Payable):");
        System.out.println("   - Any class implementing Payable guarantees calculatePay() & printPayslip().");
        System.out.println();

        Payable[] payrollList = { dev, sup };

        for (Payable payableItem : payrollList) {
            // Polymorphic interface call
            payableItem.printPayslip();
        }


        // ====================================================================
        // INTERACTIVE SYSTEM: TRY IT YOURSELF!
        // ====================================================================
        System.out.println("\n=======================================================");
        System.out.println(">>> INTERACTIVE SECTION: CREATE YOUR OWN OOP OBJECT");
        System.out.println("=======================================================");
        System.out.print("Would you like to create your own custom employee? (y/n): ");

        if (scan.hasNextLine()) {
            String choice = scan.nextLine().trim();

            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                createCustomEmployee(scan);
            } else {
                System.out.println("Skipping custom registration. Showcase complete!");
            }
        }

        System.out.println("\n=======================================================");
        System.out.println("           OOP 4-PILLAR SHOWCASE FINISHED!             ");
        System.out.println("=======================================================");
        scan.close();
    }

    /**
     * Interactive helper allowing the user to create either a Developer or a Supervisor
     * and see all 4 pillars applied to their own input!
     */
    private static void createCustomEmployee(Scanner scan) {
        try {
            System.out.println("\nSelect Role to create:");
            System.out.println("  [1] Developer");
            System.out.println("  [2] Supervisor (e.g. BPO Supervisor)");
            System.out.print("Enter choice (1 or 2): ");
            int roleChoice = Integer.parseInt(scan.nextLine().trim());

            System.out.print("Enter Full Name: ");
            String name = scan.nextLine();

            System.out.print("Enter Age: ");
            int age = Integer.parseInt(scan.nextLine().trim());

            System.out.print("Enter Nationality: ");
            String nationality = scan.nextLine();

            System.out.print("Enter Base Salary: ");
            double salary = Double.parseDouble(scan.nextLine().trim());

            Employee customEmp = null;

            if (roleChoice == 1) {
                System.out.print("Enter Primary Programming Language: ");
                String lang = scan.nextLine();
                customEmp = new Developer(name, age, nationality, "DEV-CUSTOM", salary, lang);
            } else {
                System.out.print("Enter Team Size to supervise: ");
                int teamSize = Integer.parseInt(scan.nextLine().trim());
                customEmp = new Supervisor(name, age, nationality, "SUP-CUSTOM", salary, teamSize);
            }

            System.out.println("\n--- YOUR CREATED OBJECT IN ACTION ---");
            System.out.println("[Inheritance & Encapsulation]");
            customEmp.introduce();

            System.out.println("\n[Runtime Polymorphism]");
            customEmp.work();
            customEmp.performDailyRoutine();

            System.out.println("\n[Abstraction via Payable Interface]");
            customEmp.printPayslip();

        } catch (Exception e) {
            System.out.println("Invalid input entered. Returning to main menu.");
        }
    }

    private static void printHeader() {
        System.out.println("*******************************************************");
        System.out.println("*      JAVA OOP 4 PILLARS - COMPLETE DEMO SYSTEM      *");
        System.out.println("*******************************************************");
        System.out.println("*  1. Encapsulation : Protecting and validating data  *");
        System.out.println("*  2. Inheritance   : Code reuse across hierarchies   *");
        System.out.println("*  3. Polymorphism  : Overriding and Overloading      *");
        System.out.println("*  4. Abstraction   : Abstract Classes & Interfaces   *");
        System.out.println("*******************************************************");
    }
}