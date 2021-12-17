import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Company company = new Company();
        List<Employee> topManager = new ArrayList<>();
        for (int i = 0; i < 180; i++) {
            company.hire(new Operator(30_000));
        }
        for (int i = 0; i < 80; i++) {
            company.hire(new Manager(40_000));
        }
        for (int i = 0; i < 10; i++) {
            topManager.add(new TopManager(50_000, company));
        }
        company.hireAll(topManager);
        List<Employee> resultTop = company.getTopSalaryStaff(10);
        System.out.println("Топ самых больших зарплат компании");
        for (int i = 0; i < resultTop.size(); i++) {
            System.out.println(resultTop.get(i).getMonthSalary());
        }
        System.out.println("\nТоп самых маленьких зарплат компании");
        List<Employee> resultLowest = company.getLowestSalaryStaff(30);
        for (int i = 0; i < resultLowest.size(); i++) {
            System.out.println(resultLowest.get(i).getMonthSalary());
        }
        List<Employee> result = company.getEmployees();
        for (int i = 0; i < result.size() / 2; i++) {
            company.fire(result.get(i));
        }
        System.out.println("\nТоп самых больших зарплат компании после увальнения половины сотрудников");
        List<Employee> resultTop1 = company.getTopSalaryStaff(10);
        for (int i = 0; i < resultTop1.size(); i++) {
            System.out.println(resultTop1.get(i).getMonthSalary());
        }
        System.out.println("\nТоп самых маленьких зарплат компании после увальнения половины сотрудников");
        List<Employee> resultLowest1 = company.getLowestSalaryStaff(30);
        for (int i = 0; i < resultLowest1.size(); i++) {
            System.out.println(resultLowest1.get(i).getMonthSalary());
        }

    }
}