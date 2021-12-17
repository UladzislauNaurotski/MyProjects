public interface Employee extends Comparable<Employee> {

    int getMonthSalary();

     default int compareTo(Employee o) {
        return this.getMonthSalary() - o.getMonthSalary();
    }

}
