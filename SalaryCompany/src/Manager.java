public class Manager implements Employee {

    private final int MAX_SALES = 140_000;
    private final int MIN_SALES = 115_000;
    private int salary;
    private int sales = (int) (Math.random() * (MAX_SALES - MIN_SALES) + 1) + MIN_SALES;

    public Manager(int salary) {
        this.salary = salary;
    }

    @Override
    public int getMonthSalary() {
        return (int) (salary + 0.05 * sales);
    }

    public int getSales() {
        return sales;
    }
}
