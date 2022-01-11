import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ConvertPDA {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter path to test file: ");
            String pathToTest = in.nextLine();
            Scanner inputFile = new Scanner(new FileReader(pathToTest));
            ArrayList<String> rules = new ArrayList<String>(0);
            while (inputFile.hasNext()) {
                rules.add(inputFile.nextLine());
            }
            PDA pda = PDAReader.readPDA(rules);
            CFG cfg = pda.toCFG();
            cfg.printCFG();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}
