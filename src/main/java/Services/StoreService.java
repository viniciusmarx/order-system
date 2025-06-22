package Services;

import Entities.*;
import Persistence.StorePersistence;

import java.util.List;
import java.util.function.Function;

public class StoreService {
    private final Store store;

    public StoreService(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return store;
    }

    public void save() {
        StorePersistence.save(store);
    }

    public void registerSupplier(Supplier supplier) {
        store.getSuppliers().add(supplier);
        System.out.println("Fornecedor cadastrado com sucesso");
        save();
    }

    public void removeSupplier(Supplier supplier) {
        if (store.canDeleteSupplier(supplier)) {
            store.getSuppliers().remove(supplier);
            System.out.println("Fornecedor removido com sucesso");
            save();
        }
    }

    public boolean hasSuppliers() {
        return !store.getSuppliers().isEmpty();
    }

    public Supplier findSupplierById(int id) {
        return store.foundSupplier(id);
    }

    public void registerProduct(Product product) {
        store.getProducts().add(product);
        System.out.println("Produto cadastrado com sucesso");
        save();
    }

    public void removeProduct(Product product) {
        store.getProducts().remove(product);
        System.out.println("Produto removido com sucesso");
        save();
    }

    public boolean hasProducts() {
        return !store.getProducts().isEmpty();
    }

    public Product findProductById(int id) {
        return store.foundProduct(id);
    }

    public void addUser(User user) {
        store.getUsers().add(user);
        save();
    }

    public void addCustomer(Customer customer) {
        store.getUsers().add(customer);
        store.getCustomers().add(customer);
        save();
    }

    public void listProducts() {
        System.out.println("--- Lista de Produtos ---");
        for (Product product : store.getProducts()) {
            System.out.println(product);
        }
    }

    public void listSuppliers() {
        System.out.println("--- Lista de Fornecedores ---");
        for (Supplier s : store.getSuppliers()) {
            System.out.println(s.getId() + " - " + s.getName() + " - " + s.getDescription());
        }
    }

    public void viewAllStock() {
        System.out.println("--- Estoque Geral ---");

        if (store.getProducts().isEmpty()) {
            System.out.println("Nenhum produto cadastrado");
            return;
        }

        for (Product p : store.getProducts()) {
            System.out.printf("Id: %d | Nome: %s | Preço: R$%.2f | Quantidade: %d\n",
                    p.getId(), p.getName(), p.getStock().getPrice(), p.getStock().getQuantity());
        }
    }

    public void showProductByName(String search) {
        showByName(search, store.getProducts(), Product::getName, Product::getId, "produto");
    }

    public void showSupplierByName(String search) {
        showByName(search, store.getSuppliers(), Supplier::getName, Supplier::getId, "fornecedor");
    }

    private <T> void showByName(String search, List<T> list, Function<T, String> nameExtractor, Function<T, Integer> idExtractor, String entityType) {
        boolean found = false;
        for (T item : list) {
            String name = nameExtractor.apply(item);
            if (name != null && name.toLowerCase().contains(search.toLowerCase())) {
                System.out.println("\n" + item);
                found = true;
            }

            String id = idExtractor.apply(item).toString();
            if (id.equals(search)) {
                System.out.println("\n" + item);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(entityType + " não encontrado");
        }
    }

}
