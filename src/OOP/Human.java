package OOP;

/**
 * ============================================================================
 * PILLAR 1: ENCAPSULATION & PILLAR 4: ABSTRACTION
 * ============================================================================
 * 
 * 1. ENCAPSULATION (Data Protection):
 *    - Fields are declared 'private' to hide internal data from outside interference.
 *    - Access is provided safely through 'public' Getters and Setters.
 *    - Setters perform validation to keep the object in a valid, safe state
 *      (e.g., age cannot be negative, name cannot be blank).
 * 
 * 2. ABSTRACTION (Concept Blueprint):
 *    - 'Human' is declared 'abstract'. A generic "Human" cannot be instantiated
 *      directly with 'new Human()' because it is an abstract concept.
 *    - Contains an abstract method 'performDailyRoutine()' that child classes
 *      MUST implement according to their specific lifestyle/job.
 * ============================================================================
 */
public abstract class Human {

    // ------------------------------------------------------------------------
    // [ENCAPSULATION] Private variables (Hidden from external direct access)
    // ------------------------------------------------------------------------
    private String fullName;
    private int age;
    private String nationality;

    // Constructor: Sets up initial state using setters for validation
    public Human(String fullName, int age, String nationality) {
        setFullName(fullName);
        setAge(age);
        setNationality(nationality);
    }

    // ------------------------------------------------------------------------
    // [ENCAPSULATION] Getters and Setters with input validation
    // ------------------------------------------------------------------------
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            this.fullName = "Unknown Human";
        } else {
            this.fullName = fullName.trim();
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        // Validation rule: age cannot be zero or negative
        if (age <= 0) {
            System.out.println("  [Validation Warning] Age (" + age + ") is invalid! Defaulting to 18.");
            this.age = 18;
        } else {
            this.age = age;
        }
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        if (nationality == null || nationality.trim().isEmpty()) {
            this.nationality = "Filipino";
        } else {
            this.nationality = nationality.trim();
        }
    }

    // ------------------------------------------------------------------------
    // Concrete Method: Shared behavior inherited by all humans
    // ------------------------------------------------------------------------
    public void introduce() {
        System.out.println("Hello, my name is " + fullName + ". I am " + age 
                + " years old and I am " + nationality + ".");
    }

    public void sleep() {
        System.out.println(fullName + " is sleeping for 8 hours to stay healthy.");
    }

    // ------------------------------------------------------------------------
    // [ABSTRACTION] Abstract Method: No body ({})
    // Each specific human role (Developer, Supervisor, etc.) must define this!
    // ------------------------------------------------------------------------
    public abstract void performDailyRoutine();
}