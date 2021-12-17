public class Operator implements Employee {

    private int salary;

    public Operator(int salary) {
        this.salary = (int) (Math.random() * salary + salary);
    }

    @Override
    public int getMonthSalary() {
        return  salary;
    }
}
