package Menus;

import java.util.Scanner;

public class MainMenu {
    public static int show(Scanner sc) {
        try {
            System.out.println("\n--- Bem-vindo ao sistema de loja ---");
            System.out.println("1 - Login");
            System.out.println("2 - Criar nova conta");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite um número");
            return -1;
        }
    }
}
