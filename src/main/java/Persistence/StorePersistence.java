package Persistence;

import Entities.Store;
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
                return mapper.readValue(file, Store.class);
            } catch (IOException e) {
                System.out.println("Erro ao carregar dados da loja: " + e.getMessage());
                System.exit(1);
            }
        }
        System.out.println("Arquivo de dados não encontrado");
        System.exit(1);
        return null;
    }
}
