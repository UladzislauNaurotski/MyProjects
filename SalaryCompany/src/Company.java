import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {

    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    public int getIncome() {
        int income = 0;
        for (Employee e : employees) {
            if (e instanceof Manager) {
                income += ((Manager) e).getSales();
            }
        }
        return income;
    }

    public void hire(Employee employee) {
        employees.add(employee);
    }

    public void hireAll(List<Employee> employees) {
        for (Employee e : employees) {
            hire(e);
        }
    }

    public void fire(Employee employee) {
        employees.remove(employee);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        if (count < 0){
            return Collections.emptyList();
        }
        if (count > employees.size()){
            count = employees.size();
        }
        Collections.sort(employees);
        return employees.subList(0, count);

    }
    public List<Employee> getTopSalaryStaff(int count) {
        if (count < 0){
            return Collections.emptyList();
        }
        if (count > employees.size()){
            count = employees.size();
        }
        Collections.sort(employees);
        Collections.reverse(employees);
        return employees.subList(0, count);
    }


}
