public class Student {

    private String name;
    private int age;
    private String courses;

    public Student(String name, int age, String courses) {
        this.name = name;
        this.age = age;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCourses() {
        return courses;
    }
}
