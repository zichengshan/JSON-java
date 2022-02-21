package org.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Node<T> {
    private List<Node> children = new ArrayList<>();
    private Node<T> parent = null;
    private T data = null;
    private String key = null;

    public Node(T data) {
        this.data = data;
    }
    public void setKey(String key){
        this.key = key;
    }
    public String getKey(){
        return key;
    }

    public Node(T data, Node<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    public void addChild(Node child) {
        if(child ==null) return;
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(T data) {
        Node<T> newChild = new Node<>(data);
        this.addChild(newChild);
    }

    public void addChildren(List<Node> children) {
        for(Node t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public void removeParent() {
        this.parent = null;
    }
    public Stream<Node> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    public Spliterator<Node> spliterator() {
        return new NodeSpliterator(this);
    }
    class NodeSpliterator implements Spliterator<Node> {
        private final Node root;
        private Node tree;

        NodeSpliterator(Node t) {
            root = tree = t;
        }

        @Override
        public int characteristics() {
            return Spliterator.DISTINCT | Spliterator.IMMUTABLE | Spliterator.NONNULL;
        }

        @Override
        public long estimateSize() {
            return Long.MAX_VALUE;
        }



        @Override
        public boolean tryAdvance(Consumer<? super Node> action) {
            Node current = tree;
//            JSONObject data =(JSONObject) current.getData();
            action.accept(current);
            List<Node<T>> child = tree.getChildren();
            for(Node<T> node : child){
                tree = node;
                if(tree != null) {
                    tryAdvance(action);
                }
            }
            tree = current;
            if (tree == root)
                return false;
            else
                return true;
        }

        @Override
        public Spliterator<Node> trySplit() {
            return null;
        }
    }
}