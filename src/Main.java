import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Store store = new Store();

        Scanner sc = new Scanner(System.in);

        int option;
        User user = null;

        do {
            System.out.println("\n--- Bem-vindo ao sistema de loja ---");
            System.out.println("1 - Login");
            System.out.println("2 - Criar nova conta");
            System.out.println("0 - Sair");
            System.out.println("Escolha: ");
            option = Integer.parseInt(sc.nextLine());

            switch (option){
                case 1 -> user = store.login(sc);
                case 2 -> store.createNewAccount(sc);
                case 0 -> {
                    System.out.println("Saindo...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida");
            }
        } while (option != 0 && user == null);

        if(user instanceof Customer){
            Customer c = (Customer) user;
            store.menuCustomer(sc, c);
        } else {
            store.menuAdmin(sc);
        }
        sc.close();
    }
}