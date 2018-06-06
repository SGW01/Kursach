package sgw.kursach;

public class Candidate {
    String name, email;
    int age;
    String range1, range2;

    public Candidate(String name, String email, int age, String range1, String range2) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.range1 = range1;
        this.range2 = range2;
    }

    public int getAge() {
        return age;
    }

    public String getRange1() {
        return range1 != null ? range1 : "";
    }

    public String getRange2() {
        return range2 != null ? range2 : "";
    }


    public String getEmail() {
        return email != null ? email : "";
    }

    public String getName() {
        return name != null ? name : "";
    }
}
