package br.com.huffman.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Binary {
    private ArrayList<Character> lettersArray = new ArrayList();
    private ArrayList<Integer> frequencyArray = new ArrayList();
    private String huffmanCodes;

    public static void txtToBinary(ArrayList<Character> lettersArray, ArrayList<Integer> frequencyArray, String outputFileName, String huffmanCodes) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(outputFileName))) {
            out.writeInt(lettersArray.size());

            for (int i = 0; i < lettersArray.size(); ++i) {
                out.writeByte((byte) lettersArray.get(i).charValue());
                out.writeInt((Integer) frequencyArray.get(i));
            }

            int totalBits = huffmanCodes.length();
            out.writeInt(totalBits);
            byte[] compresed = bitsToBytes(totalBits, huffmanCodes);
            out.write(compresed);
        }

    }

    public static byte[] bitsToBytes(int totalBits, String huffmanCodes) {
        int byteCount = (totalBits + 7) / 8;
        byte[] bytes = new byte[byteCount];

        for (int i = 0; i < totalBits; ++i) {
            int byteIndex = i / 8;
            int bitIndex = 7 - i % 8;
            if (huffmanCodes.charAt(i) == '1') {
                bytes[byteIndex] = (byte) (bytes[byteIndex] | 1 << bitIndex);
            }
        }

        return bytes;
    }

    public void binaryToTxt(String inputFile) throws IOException {
        try (DataInputStream in = new DataInputStream(new FileInputStream(inputFile))) {
            int freqTableSize = in.readInt();

            for (int i = 0; i < freqTableSize; ++i) {
                this.lettersArray.add((char) (in.readByte() & 255));
                this.frequencyArray.add(in.readInt());
            }

            int totalBits = in.readInt();
            int byteCount = (totalBits + 7) / 8;
            byte[] bytes = new byte[byteCount];
            in.readFully(bytes);
            StringBuilder auxHuffmanCodes = new StringBuilder();

            for (int i = 0; i < byteCount; ++i) {
                String bits = String.format("%8s", Integer.toBinaryString(bytes[i] & 255)).replace(' ', '0');
                auxHuffmanCodes.append(bits);
            }

            this.huffmanCodes = auxHuffmanCodes.substring(0, totalBits);
            System.out.println("Bits compactados:");
            System.out.println(this.huffmanCodes);
        }
    }

    public ArrayList<Character> getLettersArray() {
        return lettersArray;
    }

    public ArrayList<Integer> getFrequencyArray() {
        return frequencyArray;
    }

    public String getHuffmanCodes() {
        return huffmanCodes;
    }

    public void stringToTxt(String outText, String outputFileName) throws IOException {
        Files.writeString(Paths.get(outputFileName), outText);
    }
}
