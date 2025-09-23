package br.com.huffman.model;

import java.util.HashMap;

public class Bnode implements Comparable<Bnode> {
    private char x;
    private int freq;
    private Bnode esq, dir;

    public Bnode(char x, int freq){
        this.x = x;
        this.freq = freq;
        esq = dir = null;
    }

    public int getFreq(){
        return freq;
    }

    public char getX(){
        return x;
    }

    public void setEsq(Bnode esq){
        this.esq = esq;
    }

    public void setDir(Bnode dir){
        this.dir = dir;
    }

    public boolean isLeaf(){
        if (esq == null && dir == null) return true;
        else return false;
    }

    @Override
    public int compareTo(Bnode nextNode){
        return this.freq - nextNode.freq;
    }

    @Override
    public String toString(){
        return "[ x = " + x + " , freq = " + freq + " ]";
    }

    public void print(){

        if (esq != null){
            esq.print();
        }
        if (x == '*') System.out.println(freq);
        else System.out.println(x);
        if (dir != null){
            dir.print();
        }
    }

    public void generateCodesTable(HashMap<Character, String> huffmanCodes, String currentCode){

        if (isLeaf()){
            huffmanCodes.put(x, currentCode);
            return;
        }

        if (esq != null) {
            esq.generateCodesTable(huffmanCodes, currentCode + "0");
        }
        if (dir != null){
            dir.generateCodesTable(huffmanCodes, currentCode + "1");
        }

    }


}
