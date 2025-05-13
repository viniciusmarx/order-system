//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MySystem.addUser(new User("Admin", "admin@loja.com", "123", User.Role.ADMIN));

        User logado;
        do{
            logado = MySystem.login(sc);
        } while (logado == null);

        if(logado.getRole() == User.Role.ADMIN){
            MySystem.menuAdmin(sc);
        } else {
            MySystem.menuCustomer(sc);
        }

        sc.close();
    }

}