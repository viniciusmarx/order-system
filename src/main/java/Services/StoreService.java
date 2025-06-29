package Services;

import Entities.*;
import Persistence.StorePersistence;
import Repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store getStore() {
        return storeRepository.getStore();
    }

    public void save() {
        storeRepository.save();
    }

    public void registerSupplier(Supplier supplier) {
        storeRepository.getStore().getSuppliers().add(supplier);
        System.out.println("Fornecedor cadastrado com sucesso");
        storeRepository.save();
    }

    public void removeSupplier(Supplier supplier) {
        if (storeRepository.getStore().canDeleteSupplier(supplier)) {
            storeRepository.getStore().getSuppliers().remove(supplier);
            System.out.println("Fornecedor removido com sucesso");
            storeRepository.save();
        }
    }

    public boolean hasSuppliers() {
        return !storeRepository.getStore().getSuppliers().isEmpty();
    }

    public Supplier getSupplierById(int id) {
        return storeRepository.getStore().foundSupplier(id);
    }

    public void registerProduct(Product product) {
        storeRepository.getStore().getProducts().add(product);
        System.out.println("Produto cadastrado com sucesso");
        storeRepository.save();
    }

    public void removeProduct(Product product) {
        storeRepository.getStore().getProducts().remove(product);
        System.out.println("Produto removido com sucesso");
        storeRepository.save();
    }

    public boolean hasProducts() {
        return !storeRepository.getStore().getProducts().isEmpty();
    }

    public Product getProductById(int id) {
        return storeRepository.getStore().foundProduct(id);
    }

    public void addUser(User user) {
        storeRepository.getStore().getUsers().add(user);
        storeRepository.save();
    }

    public void addCustomer(Customer customer) {
        storeRepository.getStore().getUsers().add(customer);
        storeRepository.getStore().getCustomers().add(customer);
        storeRepository.save();
    }

    public void listProducts() {
        System.out.println("\n--- Lista de Produtos ---");
        for (Product product : storeRepository.getStore().getProducts()) {
            System.out.println(product + "\n");
        }
    }

    public void listSuppliers() {
        System.out.println("\n--- Lista de Fornecedores ---");
        for (Supplier s : storeRepository.getStore().getSuppliers()) {
            System.out.println(s.getId() + " - " + s.getName() + " - " + s.getDescription() + "\n");
        }
    }

    public void viewAllStock() {
        System.out.println("--- Estoque Geral ---");

        if (storeRepository.getStore().getProducts().isEmpty()) {
            System.out.println("Nenhum produto cadastrado");
            return;
        }

        for (Product p : storeRepository.getStore().getProducts()) {
            System.out.printf("Id: %d | Nome: %s | Preço: R$%.2f | Quantidade: %d\n",
                    p.getId(), p.getName(), p.getStock().getPrice(), p.getStock().getQuantity());
        }
    }

    public List<Supplier> searchSuppliers(String search) {
        return searchItems(search, storeRepository.getStore().getSuppliers(), Supplier::getId, Supplier::getName, Supplier::getDescription);
    }

    public List<Product> searchProducts(String search) {
        return searchItems(search, storeRepository.getStore().getProducts(), Product::getId, Product::getName, Product::getDescription);
    }

    private <T> List<T> searchItems(String search, List<T> list, Function<T, Integer> idExtractor, Function<T, String> nameExtractor, Function<T, String> descriptionExtractor) {
        List<T> results = new ArrayList<>();

        for (T item : list) {
            if (String.valueOf(idExtractor.apply(item)).equals(search)) {
                results.add(item);
                return results;
            }
        }

        for (T item : list) {
            String name = nameExtractor.apply(item);
            String description = descriptionExtractor.apply(item);

            if (name.toLowerCase().contains(search.toLowerCase()) || description.toLowerCase().contains(search.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }
}
