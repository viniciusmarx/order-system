package Services;

import Entities.Customer;
import Entities.Store;
import Entities.User;
import Utils.InputUtils;

import java.util.Scanner;

public class AuthService {
    private final StoreService storeService;

    public AuthService(StoreService storeService) {
        this.storeService = storeService;
    }

    public User login(Scanner sc) {
        System.out.println("--- LOGIN ---");
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String password = sc.nextLine();

        for (User user : storeService.getStore().getUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                System.out.println("Bem-vindo(a), " + user.getName());
                return user;
            }
        }
        System.out.println("Email ou senha inválidos");
        return null;
    }

    public void createNewAccount(Scanner sc) {
        System.out.println("--- Cadastro de Novo Usuário ---");
        int role;

        while (true) {
            System.out.println("- Tipo de conta - ");
            System.out.println("1 - Admin");
            System.out.println("2 - Cliente");
            System.out.println("3 - Voltar");
            try {
                role = Integer.parseInt(sc.nextLine());
                if (role == 1 || role == 2 || role == 3) {
                    break;
                } else {
                    System.out.println("Opção inválida. Digite 1, 2 ou 3");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido");
            }
        }

        if (role == 3) return;

        switch (role) {
            case 1 -> {
                User user = new User();
                InputUtils.promptAndSetString(sc, "Nome", user::setName);
                while (true) {
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    if (storeService.getStore().isEmailRegistered(email)) {
                        System.out.println("Este email já está cadastrado");
                        System.out.println("Digite 1 para tentar outro email ou qualquer tecla para voltar para o login");
                        String choice = sc.nextLine().trim().toLowerCase();
                        if (choice.equals("1")) {
                            return;
                        } else {
                            continue;
                        }
                    }
                    try {
                        user.setEmail(email);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                InputUtils.promptAndSetString(sc, "Senha", user::setPassword);

                storeService.addUser(user);
            }
            case 2 -> {
                Customer customer = new Customer();
                InputUtils.promptAndSetString(sc, "Nome", customer::setName);
                while (true) {
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    if (storeService.getStore().isEmailRegistered(email)) {
                        continue;
                    }
                    try {
                        customer.setEmail(email);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                InputUtils.promptAndSetString(sc, "Senha", customer::setPassword);
                InputUtils.promptAndSetString(sc, "Telefone", customer::setPhoneNumber);
                InputUtils.promptAndSetString(sc, "Cartão de crédito", customer::setCreditCard);
                customer.setAddress(AddressFactory.createAddress(sc));

                storeService.addCustomer(customer);
            }
        }
        System.out.println("Conta de " + (role == 1 ? "admin" : "cliente") + " criada com sucesso!");
    }
}
