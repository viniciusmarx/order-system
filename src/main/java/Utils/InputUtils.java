package Utils;

import java.util.Scanner;
import java.util.function.Consumer;

public class InputUtils {

    public static void promptAndSetString(Scanner sc, String label, Consumer<String> setter) {
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

    public static void promptAndSetInt(Scanner sc, String label, Consumer<Integer> setter) {
        while (true) {
            try {
                System.out.print(label + ": ");
                int input = Integer.parseInt(sc.nextLine());
                setter.accept(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
