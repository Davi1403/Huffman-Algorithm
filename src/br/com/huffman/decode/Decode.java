package br.com.huffman.decode;

import br.com.huffman.model.Binary;
import br.com.huffman.model.Btree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Decode {
    private ArrayList<Character> lettersArray = new ArrayList();
    private ArrayList<Integer> frequencyArray = new ArrayList();
    private String huffmanCodes;
    Binary file = new Binary();

    public Decode(String inputPath) throws IOException {
        file.binaryToTxt(inputPath);

        this.lettersArray = file.getLettersArray();
        this.frequencyArray = file.getFrequencyArray();
        this.huffmanCodes = file.getHuffmanCodes();
    }

    public void decode(String outputPath) throws IOException {

        // -- GERA A ARVORE DE HUDFFMAN NA CLASSE BTREE --
        Btree tree = new Btree();
        tree.createHuffmanTree(lettersArray, frequencyArray);

        // -- TABELA DE CODIGOS DE HUFFMAN " A = 0 B = 01 ... --
        HashMap<Character, String> huffmanCodesTable = tree.generateCodesTable();

        // -- INVERTER TEBELA DE CODIGOS

        HashMap<String, Character> huffmanCodesTableInverted = new HashMap<>();
        for (HashMap.Entry<Character, String> entry : huffmanCodesTable.entrySet()){
            huffmanCodesTableInverted.put(entry.getValue(), entry.getKey());
        }

        // -- DECODIFICA

        StringBuilder auxString = new StringBuilder(); // TEXTO AUX, ADD UM 0/1 A CADA LOOP, ATÉ ACHAR ESSE CODIGO ANA TABELA
        StringBuilder outText = new StringBuilder(); // TEXTO DECODIFICADO
        for (int i = 0 ; i < huffmanCodes.length() ; i++){
            auxString.append(huffmanCodes.charAt(i));
            if (huffmanCodesTableInverted.containsKey(auxString.toString())){
                outText.append(huffmanCodesTableInverted.get(auxString.toString())); // SE AUXSTRING EXISTIR NA TABELA, ADICIONA O VALOR A SAIDA E LIMPA A STRING
                auxString.setLength(0);
            }
        }
        System.out.println(outText);
        file.stringToTxt(outText.toString(), outputPath);
    }
}
