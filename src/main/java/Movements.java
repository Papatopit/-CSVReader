import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Movements {
    private double income = 0;
    private double expense = 0;
    private final Map<String, Double> expenseList = new HashMap<>();
    private final Path filePath;
    private List<String> lines;

    public Movements(String pathMovementsCsv) {

        filePath = Paths.get(pathMovementsCsv);
        lines = new ArrayList<>();
    }

    private List<String> getMovementsLines() {

        if (lines.isEmpty()) {
            try {
                lines = Files.readAllLines(filePath);
                lines.remove(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }

    public void calculateData(String input) {
        String[] columns = input.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

       double incomeTmp = Double.parseDouble(columns[6].replaceAll(",", ".").replace("\"", ""));
       double expenseTmp = Double.parseDouble(columns[7].replaceAll(",", ".").replace("\"", ""));
        if (incomeTmp == 0) {
            calculateExpense(columns[5], expenseTmp);
        } else {
            income += incomeTmp;
        }
    }

    private void calculateExpense(String expenseName, double expense) {
        this.expense += expense;
        String[] transaction = expenseName.trim().split(" {3,}");
        String[] temp = transaction[1].split("/");
        String[] suppliesTemp = temp[temp.length - 1].split("\\\\");
        String supplies = suppliesTemp[suppliesTemp.length - 1];
        if (!expenseList.containsKey(supplies)) {
            expenseList.put(supplies, expense);
        } else {
            double sum = expenseList.get(supplies);
            sum += expense;
            expenseList.put(supplies.trim(), sum);
        }
    }

    public double getExpenseSum() {

        for (int i = 0; i < getMovementsLines().size(); i++) {
            calculateData(getMovementsLines().get(i));
        }
        System.out.printf("ОБЩИЙ РАСХОД: %.2f руб", expense);
        return expense;
    }

    public double getIncomeSum() {
        for (int i = 0; i < getMovementsLines().size(); i++) {
            calculateData(getMovementsLines().get(i));
        }
        System.out.printf("ОБЩИЕ ПОСТУПЛЕНИЕ НА СЧЕТ: %.2f руб\n", income);
        return income;

    }

    public void printData() {
        System.out.println("ДВИЖЕНИЕ ДЕНЕЖНЫХ СРЕДСТВ");
        System.out.printf("ОБЩИЕ ПОСТУПЛЕНИЕ НА СЧЕТ: %.2f руб\n", income);
        System.out.println("--------------------------------------------------");
        System.out.println("ТАБЛИЦА РАСХОДОВ:");
        for (String supplies : expenseList.keySet()) {
            System.out.printf("%-30s -----> %.2f %-10s\n", supplies, expenseList.get(supplies), "руб");
        }
        System.out.println("--------------------------------------------------");
        System.out.printf("ОБЩИЙ РАСХОД: %.2f руб", expense);
    }
}