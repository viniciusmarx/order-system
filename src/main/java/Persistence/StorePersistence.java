package Persistence;

import Entities.Product;
import Entities.Store;
import Entities.Supplier;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class StorePersistence {
    private static final String FILE_PATH = "store_data.json";
    private static final String TEMP_FILE_PATH = "store_data_temp.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void save(Store store) {
        File tempFile = new File(TEMP_FILE_PATH);
        File finalFile = new File(FILE_PATH);

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(tempFile, store);
            if (finalFile.exists() && !finalFile.delete()) {
                throw new IOException("Ocorreu algum erro ao tentar substituir o arquivo original");
            }
            if (!tempFile.renameTo(finalFile)) {
                throw new IOException("Erro ao renomear o arquivo temporário");
            }
            System.out.println("Dados da loja salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados da loja: " + e.getMessage());
        }
    }

    public static Store load() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try {
                Store store = mapper.readValue(file, Store.class);
                UpdateAllNextIds(store);
                return store;
            } catch (IOException e) {
                System.out.println("Erro ao carregar dados da loja: " + e.getMessage());
                System.exit(1);
            }
        }
        System.out.println("Arquivo de dados não encontrado");
        System.exit(1);
        return null;
    }

    private static void UpdateAllNextIds(Store store) {
        updateNextSupplierId(store);
        updateNextProductId(store);
    }

    private static void updateNextSupplierId(Store store) {
        int maxId = store.getSuppliers().stream()
                .mapToInt(Supplier::getId)
                .max()
                .orElse(0);
        Supplier.setNextId(maxId + 1);
    }

    private static void updateNextProductId(Store store) {
        int maxId = store.getProducts().stream()
                .mapToInt(Product::getId)
                .max()
                .orElse(0);
        Product.setNextId(maxId + 1);
    }
}
