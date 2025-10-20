package br.com.huffman.model;

import java.io.File;

public class Test {
    public static long size(String path){
        File file = new File(path);

        if (file.exists()){
            long size = file.length();
            return size;
        }else {
            System.err.println("O arquivo não foi encontrado - test.size");
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.print("Tamanho do arquivo de entrada : ");
        System.out.println(size("Arquivos/TextoDeEntrada.txt") + " bytes");

        System.out.print("Tamanho do arquivo de saida: ");
        System.out.println(size("Arquivos/ArquivoCodificado") + " bytes");
    }

}
