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
            for (int i = 0 ; i < myString.length() ; i++){
                char letter = myString.charAt(i);
                if (lettersArray.contains(letter)) {
                    int index = lettersArray.indexOf(letter);
                    frequencyArray.set(index, frequencyArray.get(index) + 1); // SE A LETRA JÁ ESTIVER NO ARRAY, ICREMENTA 1 (INDEX DE ONDE ESTÁ A LETRA NO ARRAY LETRAS, O VALOR É INCREMENTADO NO ARRAY DE FREQUENCIA
                    //System.out.println("+1 no Index = " +  lettersArray.indexOf(letter));
                }
                else {
                    lettersArray.add(letter); // SE NÃO, ADICIONA A LETRA NO ARRAY DE LETRAS E 1 NO ARRAY DE FREQUENCIA
                    frequencyArray.add(1);
                    //System.out.println(letter + " entrou na lista");
                    //System.out.println( "mais um em freq");
                    //System.out.println(
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
