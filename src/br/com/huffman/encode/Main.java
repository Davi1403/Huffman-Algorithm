package br.com.huffman.encode;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // -- NOME DO ARQUIVO DE ENTRADA --

        System.out.println("Caminho do arquivo a ser codificado : ");
        String inputPath = scanner.next();
        //String inputPath = "Arquivos/TextoDeEntrada.txt";

        // -- NOME DO AQUIVO DE SAIDA --

        System.out.println("Nome do arquivo de saída : ");
        String outputPath = scanner.next();
        //String outputPath = "Arquivos/ArquivoCodificado";

        Encode h = new Encode(inputPath);

        try {
            h.encode(outputPath);
            System.out.println("Arquivo compactado com sucesso para : " + outputPath);
        }catch (IOException e){
            System.err.println("Ocorreu um erro ao compactar o arquivo : " + e.getMessage());
        }
    }
}
