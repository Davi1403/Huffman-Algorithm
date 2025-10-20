package br.com.huffman.encode;

import br.com.huffman.model.Binary;
import br.com.huffman.model.Btree;

import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.util.ArrayList;
    import java.util.HashMap;

    public class Encode {

        private final ArrayList<Character> lettersArray = new ArrayList<Character>();
        private final ArrayList<Integer> frequencyArray = new ArrayList<Integer>();
        private String myString;
        private StringBuilder huffmanCodes = new StringBuilder();


        public Encode(String inputPath){

            /* -- CONTRUTOR --
                RECEBE O NOME DO ARQUIVO PARA LEITURA E GRAVA SEU CONTEUDO NA VAR myString
             */

            String myString = "";

            try {
                Path path = Paths.get(inputPath);
                myString = Files.readString(path);
            } catch (IOException e) {
                System.err.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
            }

            this.myString = myString;
        }

        public void createTable(String myString){

            // -- LOOP QUE PERCORRE TODA A STTRING DE ENTRADA

            for (int i = 0 ; i < myString.length() ; i++){
                char letter = myString.charAt(i);

                // -- SE A LETRA JÁ EXISTIR NA TABELA --
                if (lettersArray.contains(letter)) {
                    int index = lettersArray.indexOf(letter);                   // ICREMENTA 1 (INDEX DE ONDE ESTÁ A LETRA NO ARRAY LETRAS,
                    frequencyArray.set(index, frequencyArray.get(index) + 1);   //  O VALOR É INCREMENTADO NO ARRAY DE FREQUENCIA
                }
                else {
                    lettersArray.add(letter); // SE NÃO, ADICIONA A LETRA NO ARRAY
                    frequencyArray.add(1);    // E ADD 1 NA TABELA DE FREQUÊNCIA
                }
            }
        }

        public void encode(String outputPath) throws IOException{

            // -- CRIA A TABELA DE FREQUÊNCIA
            createTable(myString);

            // -- GERA A ARVORE DE HUDFFMAN NA CLASSE BTREE --

            Btree tree = new Btree();
            tree.createHuffmanTree(lettersArray, frequencyArray);

            // -- TABELA DE CODIGOS DE HUFFMAN " A = 0 B = 01 ... --
            HashMap<Character, String> huffmanCodesTable = tree.generateCodesTable();
            //System.out.println(huffmanCodesTable);

            // -- STRING COM A SEQUENCIA CODIFICADA --
            for (int i = 0; i < myString.length() ; i++) {
                char c = myString.charAt(i);
                huffmanCodes.append(huffmanCodesTable.get(c));
            }

            Binary.txtToBinary(lettersArray, frequencyArray, outputPath, huffmanCodes.toString());
        }
    }
