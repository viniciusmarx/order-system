import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MySystem.addUser(new User("Admin", "admin@loja.com", "123", User.Role.ADMIN));

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
                case 1 -> user = MySystem.login(sc);
                case 2 -> MySystem.createNewAccount(sc);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }
        } while (option != 0 && user == null);

        if (user != null){
            if(user.getRole() == User.Role.ADMIN){
                MySystem.menuAdmin(sc);
            } else {
                MySystem.menuCustomer(sc, user);
            }
        }

        sc.close();
    }

}