import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Movements movements = new Movements("C:\\java_basics\\09_FilesAndNetwork\\homework_9.3\\src\\test\\resources\\movementListFloat.csv");
//
//        List<String> lines = null;
//        try {
//            lines = Files.readAllLines(Paths.get("C:\\Users\\Павел\\Documents\\Skillbox\\java_basics\\09_FilesAndNetwork\\homework_9.3\\src\\test\\resources\\movementList.csv"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 1; i < lines.size(); i++) {
//            movements.calculateData(lines.get(i));
        movements.getIncomeSum();
//        movements.getExpenseSum();
//        System.out.println("_____________________________");
//        movements.printData();
    }
}
