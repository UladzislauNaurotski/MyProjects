import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movements {

    private List<TransactItem> movements;

    public Movements(String pathMovementsCsv) {
        movements = new ArrayList();
        try {
            List<String> lines = Files.readAllLines(Paths.get(pathMovementsCsv));
            for (int i = 1; i < lines.size(); i++) {
                String[] fragments = lines.get(i).split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
                if (fragments.length != 8) {
                    continue;
                }
                NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
                String regex = "\"";
                Pattern pattern = Pattern.compile("(?:[A-Z]{3,})([\\w \\\\\\/><]*\\b)(?:\\s{2})");
                Matcher marcher = pattern.matcher(fragments[5]);
                String fragmentsNameCompany = "";
                while (marcher.find()) {
                    fragmentsNameCompany = fragments[5].substring(marcher.start(), marcher.end());
                }
                movements.add(new TransactItem(fragments[0], fragments[1], fragments[2],
                        new SimpleDateFormat("dd.MM.yyyy").parse(fragments[3]),
                        fragments[4], fragmentsNameCompany,
                        format.parse(fragments[6].replaceAll(regex, "")).doubleValue(),
                        format.parse(fragments[7].replaceAll(regex, "")).doubleValue()));
            }
            System.out.println("Размер листа " + movements.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPriceExpenses() {
        List<TransactItem> movementsCopy = movements;
        for (int i = 0; i < movementsCopy.size(); i++) {
            double expenses = movementsCopy.get(i).getExpenses();
            for (int j = i + 1; j < movementsCopy.size(); j++) {
                if (movementsCopy.get(i).getNameCompany().equals(movementsCopy.get(j).getNameCompany())) {
                    expenses += movementsCopy.get(j).getExpenses();
                    movementsCopy.remove(j);
                }
            }
            System.out.println(i + " >> Название компании: " + movementsCopy.get(i).getNameCompany() +
                    " Суммарные расходы: " + expenses);
        }
    }

    public double getIncomeSum() {
        return movements.stream().map(e -> e.getIncome())
                .reduce((e1, e2) -> e1 + e2).get();
    }

    public double getExpenseSum() {
        return movements.stream().map(e -> e.getExpenses())
                .reduce((e1, e2) -> e1 + e2).get();
    }
}