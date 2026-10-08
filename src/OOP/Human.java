package OOP;

public class Human {
    public String fullName;
    public int age;
    public String occupation;
    public String nationality;

    public Human(String fullName, int age, String occupation, String nationality) {
        this.fullName = fullName;
        this.age = age;
        this.occupation = occupation;
        this.nationality = nationality;
    }

    public void introduce() {
        System.out.println("Hello my name is " + fullName + " I am " + age + " years old and I work as a "
                + occupation + " and I am " + nationality);
    }

}