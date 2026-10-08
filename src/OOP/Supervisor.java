package OOP;

/**
 * ============================================================================
 * PILLAR 3: POLYMORPHISM & PILLAR 2: INHERITANCE
 * ============================================================================
 * 
 * 1. INHERITANCE:
 *    - 'Supervisor' extends 'Employee' ("Supervisor IS-A Employee IS-A Human").
 *    - Inherits all core personal and employee properties.
 * 
 * 2. RUNTIME POLYMORPHISM (Method Overriding):
 *    - Implements its own specialized behavior for:
 *      * work()
 *      * performDailyRoutine()
 *      * calculatePay() (Adds leadership allowance based on team size)
 * 
 * 3. COMPILE-TIME POLYMORPHISM (Method Overloading):
 *    - Multiple methods named 'conductMeeting' with different parameters:
 *      * conductMeeting()
 *      * conductMeeting(String agenda)
 *      * conductMeeting(String agenda, int durationMinutes)
 * ============================================================================
 */
public class Supervisor extends Employee {

    // Encapsulated Supervisor-specific field
    private int teamSize;

    public Supervisor(String fullName, int age, String nationality, 
                      String employeeId, double baseSalary, int teamSize) {
        super(fullName, age, nationality, employeeId, "BPO Operations", baseSalary);
        setTeamSize(teamSize);
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        if (teamSize < 0) {
            System.out.println("  [Validation Warning] Team size cannot be negative! Setting to 0.");
            this.teamSize = 0;
        } else {
            this.teamSize = teamSize;
        }
    }

    // ------------------------------------------------------------------------
    // [RUNTIME POLYMORPHISM] Method Overriding
    // ------------------------------------------------------------------------
    @Override
    public void work() {
        System.out.println("  [Supervisor Duty] " + getFullName() + " is monitoring shift queues and coaching a team of " 
                + teamSize + " agents.");
    }

    @Override
    public void performDailyRoutine() {
        System.out.println("  [Supervisor Routine] " + getFullName() + ": Floor check -> Shift huddle -> 1-on-1 coaching -> Quality audits.");
    }

    @Override
    public double calculatePay() {
        // Supervisors receive a leadership allowance of $50 per team member managed
        double leadershipAllowance = teamSize * 50.0;
        return getBaseSalary() + leadershipAllowance;
    }

    // ------------------------------------------------------------------------
    // [COMPILE-TIME POLYMORPHISM] Method Overloading
    // Same method name 'conductMeeting', different signatures!
    // ------------------------------------------------------------------------
    
    // Overload 1: Default general meeting
    public void conductMeeting() {
        System.out.println("  [Overloaded conductMeeting()] " + getFullName() 
                + " is hosting the general daily shift huddle.");
    }

    // Overload 2: Meeting with a specific agenda
    public void conductMeeting(String agenda) {
        System.out.println("  [Overloaded conductMeeting(agenda)] " + getFullName() 
                + " called a meeting on topic: \"" + agenda + "\".");
    }

    // Overload 3: Meeting with agenda and duration
    public void conductMeeting(String agenda, int durationMinutes) {
        System.out.println("  [Overloaded conductMeeting(agenda, duration)] " + getFullName() 
                + " is running a " + durationMinutes + "-minute strategy session regarding: \"" 
                + agenda + "\".");
    }
}
