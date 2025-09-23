package br.com.huffman.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Btree {
    private Bnode root;

    public void setRoot(Bnode root) {
        this.root = root;
    }

    public void print(){
        if (root == null) System.out.println("Empty Tree");
        else root.print();
    }

    // --METODO AUXILIAR : SOMENTE PARA VIZUALIZAR A PRIORIDADE DA QUEUE
    public void printByPriority(PriorityQueue<Bnode> priorityQueue){
        PriorityQueue<Bnode> printQueue = new PriorityQueue<>(priorityQueue);

        System.out.print("[ ");
        while (!printQueue.isEmpty()) System.out.print(printQueue.poll());
        System.out.println(" ]");
    }

    public void createHuffmanTree(ArrayList<Character> lettersArray, ArrayList<Integer> frequencyArray){

        // -- TABELA DE FREQUÊNCIA --

        PriorityQueue<Bnode> priorityQueue = new PriorityQueue<>(); // Armazena os Nodes e os ordena em ordem de prioridade (MENOR FREQ)

        // Adiciona os nodes a fila, e também adiciona o carácter e frequência ao node
        for (int i = 0; i < lettersArray.size() ; i++){
            priorityQueue.add(new Bnode(lettersArray.get(i), frequencyArray.get(i)));
        }
        //printByPriority(priorityQueue);

        /* -- NODES INTERNOS --

            O LOOP PEGA OS DOIS NODES COM MAIOR PRIORIDADE (A - B), REMOVE ELES DA LISTA,
            CRIAR UM NODE FATHER COM A SOMA DA FREQUENCIA E ADICIONA ESSE NOVO NODE NA
            LISTA. OBS : A RAIZ DA ARVORE SERA O ULTIMO NODE DA LISTA.
        */

        while (priorityQueue.size() > 1){
            Bnode a = priorityQueue.poll(); // poll(), retorna o elemento de maior peioridade, e o remove da lista
            Bnode b = priorityQueue.poll(); // como repito o poll(), agora vale para o de segunda maior prioridade
            Bnode father = new Bnode('*', a.getFreq() + b.getFreq());
            father.setEsq(a);
            father.setDir(b);
            priorityQueue.add(father);
            //printByPriority(priorityQueue);
        }

        /* -- CRIAR TREE --

           TODOS OS NODES JÁ ESTÃO PROTOS, SOMENTE DEFINIMOS QUE A RAIZ É O ULTIMO ELEMENTO DA QUEUE
        */
        setRoot(priorityQueue.peek());
        //print();
    }

    public HashMap<Character, String> generateCodesTable(){
        HashMap<Character, String> huffmanCodes = new HashMap<>();

        if (root == null){
            System.out.println("Empty Tree");
            return huffmanCodes;
        }

        if (root.isLeaf()) {
            huffmanCodes.put(root.getX(), "0");
            return huffmanCodes;
        }

        root.generateCodesTable(huffmanCodes, "");

        return huffmanCodes;

    }


}
