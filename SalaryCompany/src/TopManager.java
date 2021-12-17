public class TopManager implements Employee {

    private int salary;
    private final int MIN_INCOME = 10_000_000;
    private Company company;

    public TopManager(int salary, Company company) {
        this.salary = (int) (Math.random() * salary + salary);
        this.company = company;
    }

    @Override
    public int getMonthSalary() {
        if(company.getIncome() >= MIN_INCOME){
            return (int) (salary * 2.5);
        }
        return salary;
    }
}
