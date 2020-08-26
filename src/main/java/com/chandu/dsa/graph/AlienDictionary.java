package com.chandu.dsa.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AlienDictionary {
    private int vertexCount;
    private ArrayList<ArrayList<Integer>> adj;

    AlienDictionary(int vertexCount){
        this.vertexCount = vertexCount;
        adj = new ArrayList<>(vertexCount);
        for (int i=0; i<vertexCount; i++)
            adj.add(new ArrayList<>());
    }

    public static void main(String[] args) {
        String[] words = {"caa", "aaa", "aab"};
        printOrder(words, 3);

        String[] words2 = {"baa", "abcd", "abca", "cab", "cad" };
        printOrder(words2, 4);

        String[] words3 = {"aba", "bba", "aaa"};
        printOrder(words3, 3);
    }

    private void addEdge(int source, int destination){
        adj.get(source).add(destination);
    }

    //Time Complexity: O(N + K)
    //Space Complexity: O(K)
    //where K = number of characters
    //where N = number of words
    private static void printOrder(String[] words, int V){
        AlienDictionary obj = new AlienDictionary(V);
        obj.createGraph(words);
        int[] indegree = new int[V];
        for (int i=0; i<V; i++){
            for (int j : obj.adj.get(i))
                indegree[j]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0; i<V; i++){
            if (indegree[i] == 0)
                queue.add(i);
        }
        int u;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()){
            u = queue.poll();
            sb.append((char)('a' + u));
            for (int j : obj.adj.get(u)){
                if(--indegree[j] == 0)
                    queue.add(j);
            }
            count++;
        }
        if(count != V){
            System.out.println("Ordering is not possible");
            return;
        }
        System.out.println(sb.toString());
    }

    private void createGraph(String[] words){
        for(int i=0; i< words.length-1; i++){
            String word1 = words[i];
            String word2 = words[i+1];
            int minLength = Math.min(word1.length(), word2.length());
            for (int j=0; j<minLength; j++){
                if(word1.charAt(j) != word2.charAt(j)){
                    addEdge(word1.charAt(j) -'a' , word2.charAt(j)-'a');
                    break;
                }
            }
        }
    }
}
