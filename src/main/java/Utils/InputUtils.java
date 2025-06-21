package Utils;

import java.util.Scanner;
import java.util.function.Consumer;

public class InputUtils {

    public static void promptAndSet(Scanner sc, String label, Consumer<String> setter){
        while (true) {
            try {
                System.out.print(label + ": ");
                String input = sc.nextLine();
                setter.accept(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
