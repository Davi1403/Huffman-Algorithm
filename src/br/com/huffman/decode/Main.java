package br.com.huffman.decode;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // -- NOME DO ARQUIVO DE ENTRADA --

        System.out.println("Caminho do arquivo a ser descodificado : ");
        //String inputPath = scanner.next();
        String inputPath = "Arquivos/ArquivoCodificado";

        // -- NOME DO AQUIVO DE SAIDA --

        System.out.println("Nome do arquivo de saída : ");
        //String outputPath = scanner.next();
        String outputPath = "Arquivos/TextoDescodificado";

        //

        Decode h = new Decode(inputPath);
        h.decode(outputPath);



    }
}
