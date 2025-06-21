package Services;

import Entities.Address;

import java.util.Scanner;

public class AddressFactory {

    public static Address createAddress(Scanner sc) {
        Address address = new Address();
        while (true) {
            try {
                System.out.println("- Endereço -");
                System.out.print("Rua: ");
                address.setStreet(sc.nextLine());
                System.out.print("Número: ");
                address.setNumber(Integer.parseInt(sc.nextLine()));
                System.out.print("Complemento: ");
                address.setComplement(sc.nextLine());
                System.out.print("CEP: ");
                address.setZipCode(sc.nextLine());
                System.out.print("Cidade: ");
                address.setCity(sc.nextLine());
                System.out.print("Bairro: ");
                address.setNeighborhood(sc.nextLine());
                System.out.print("Estado: ");
                address.setState(sc.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return address;
    }
}
