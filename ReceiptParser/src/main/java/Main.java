public class Main {

    public static final String pathMovementsCsv = "resource/movementList.csv";

    public static void main(String[] args) {
        Movements movements = new Movements(pathMovementsCsv);
        System.out.println("Сумма расходов: " + movements.getExpenseSum() + "руб.");
        System.out.println("Сумма доходов: " + movements.getIncomeSum() + "руб.");
        movements.getPriceExpenses();

    }
}
