package com.company.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

// This code may have some bugs :)

public class Graph<E> {

    private HashMap<E, LinkedList<E>> map = new HashMap<>();

    public Graph() {

    }

    public void addVertex(E vertex) {
        map.put(vertex, new LinkedList<>());
    }

    public void addEdge(E source, E destination, boolean biDerctional) {
        if (!map.containsKey(source)) {
            addVertex(source);
        }
        if (!map.containsKey(destination)) {
            addVertex(destination);
        }
        map.get(source).add(destination);
        if (biDerctional) {
            map.get(destination).add(source);
        }
    }

    public int vertexCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int counter = 0;
        for (E key : map.keySet()) {
            counter += map.get(key).size();
        }
        return counter;
    }

    public boolean containsVertex(E vertex) {
        return map.containsKey(vertex);
    }

    public boolean hasDirectedEdge(E vertex1, E vertex2) {
        return map.get(vertex1).contains(vertex2);
    }

    public boolean hasUndirectedEdge(E vertex1, E vertex2) {
        return map.get(vertex1).contains(vertex2) || map.get(vertex2).contains(vertex1);
    }

    public String DFS() {
        return DFS(map.keySet().iterator().next());
    }

    public String DFS(E start) {
        if (!map.containsKey(start)) {
            return null;
        }
        String result = "";
        HashSet<E> seen = new HashSet<>();
        Stack<E> stk = new Stack<>();
        stk.push(start);
        while (!stk.empty()) {
            E temp = stk.pop();
            if (!seen.contains(temp)) {
                seen.add(temp);
                result += temp + " ";
                stk = pushAll(stk, map.get(temp));
            }
        }
        return result;
    }   

    private Stack<E> pushAll(Stack<E> stk, List<E> list) {
        for (int i = 0; i < list.size(); i++) {
            stk.push(list.get(i));
        }
        return stk;
    }

    public String BFS() {
        return BFS(map.keySet().iterator().next());
    }

    public String BFS(E start) {
        if (!map.containsKey(start)) {
            return null;
        }
        String s = "";
        HashSet<E> seen = new HashSet<>();
        Queue<E> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            E temp = q.poll();
            if (!seen.contains(temp)) {
                seen.add(temp);
                s += temp + " ";
                q = enqueueAll(q, map.get(temp));
            }
        }
        return s;
    }    

    public ArrayList<ArrayList<E>> allPaths(E start, E destination) {
        if (!map.containsKey(start) || !map.containsKey(destination)) {
            return null;
        }      
        Queue<ArrayList<E>> q = new LinkedList<>();
        ArrayList<E> temp = new ArrayList<>(), temp2;
        ArrayList<ArrayList<E>> paths = new ArrayList<>();
        temp.add(start);
        q.add(temp);
        while (!q.isEmpty()) {
            temp = q.poll();
            E last = temp.get(temp.size() - 1);
            if (last.equals(destination)) {
                paths.add(temp);
            } else{                     
                for (int i = 0; i < map.get(last).size(); i++) {
                    temp2 = new ArrayList<>(temp);
                    if (!temp2.contains(map.get(last).get(i))) {
                        temp2.add(map.get(last).get(i));
                        q.add(temp2);                        
                    }
                }
            }
        }
        return paths;
    }

    public ArrayList<E> shortestPath(E start, E destination) {
        if (!map.containsKey(start) || !map.containsKey(destination)) {
            return null;
        }
        if (destination.equals(start)) {
            return null;
        }        
        return getShortestInAList(allPaths(start, destination));
    }

    public int numOfVertices() {
        Stack<E> stk = new Stack<>();
        stk.push(map.keySet().iterator().next());
        HashSet<E> seen = new HashSet<>();
        while (!stk.isEmpty()) {
            E temp = stk.pop();
            if (!seen.contains(temp)) {
                seen.add(temp);
                stk = pushAll(stk, map.get(temp));
            }
        }
        return seen.size();
    }

    private Queue<E> enqueueAll(Queue<E> q, List<E> list) {
        for (E e : list) {
            q.add(e);
        }
        return q;
    }

    public boolean isTherePath(E start, E destimation) {
        if (!map.containsKey(start) || !map.containsKey(destimation)) {
            return false;
        }
        HashSet<E> seen = new HashSet<>();
        Stack<E> stk = new Stack<>();
        stk.push(start);
        while (!stk.isEmpty()) {
            E temp = stk.pop();
            if (!seen.contains(temp)) {
                if (temp.equals(destimation)) {
                    return true;
                }
                seen.add(temp);
                stk = pushAll(stk, map.get(temp));
            }
        }
        return false;
    }

    private ArrayList<E> getShortestInAList(ArrayList<ArrayList<E>> lists) {
        if (lists.isEmpty()) {
            return null;
        }
        ArrayList<E> minList = lists.get(0);
        for (int i = 0; i < lists.size(); i++) {
            if(minList.size() > lists.get(i).size())
                minList = lists.get(i);
        }
        return minList;
    }

    @Override
    public String toString() {
        String s = "";
        for (E key : map.keySet()) {
            s += key + " " + map.get(key).toString() + "\n";
        }
        return s;
    }

}
