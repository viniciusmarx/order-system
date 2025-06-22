package Services;

import Entities.Address;
import Utils.InputUtils;

import java.util.Scanner;

public class AddressFactory {

    public static Address createAddress(Scanner sc) {
        Address address = new Address();
        while (true) {
            try {
                System.out.println("- Endereço -");
                InputUtils.promptAndSetString(sc, "Rua", address::setStreet);
                InputUtils.promptAndSetInt(sc, "Número", address::setNumber);
                InputUtils.promptAndSetString(sc, "Complemento", address::setComplement);
                InputUtils.promptAndSetString(sc, "CEP", address::setZipCode);
                InputUtils.promptAndSetString(sc, "Bairro", address::setNeighborhood);
                InputUtils.promptAndSetString(sc, "Cidade", address::setCity);
                InputUtils.promptAndSetString(sc, "Estado", address::setState);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return address;
    }
}
