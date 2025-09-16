package data_structures.io.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
	//Método que lê valores inteiros de um CSV
    public static List<Integer> rea(String fileName) {
        List<Integer> values = new ArrayList<>();
        
		// Abre o arquivo e já garante que será fechado automaticamente
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
			
			//
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                try {
                    values.add(Integer.parseInt(line));
                } catch (NumberFormatException e) {
					// Se a linha não for um número válido, mostra mensagem e ignora
                    System.out.println("Linha inválida ignorada: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo '" + fileName + "': " + e.getMessage());
        }
		//Retorna a lista com os números lidos.
        return values;

    }
}
