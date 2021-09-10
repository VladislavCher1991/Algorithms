package cases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class Huffman {
    public static void main(String[] args) {
        String text = "where there's a will there's a way";
        TreeMap<Character, Integer> freqMap = countFrequency(text);

        ArrayList<CodeTreeNode> list = new ArrayList<>();
        for (Character c : freqMap.keySet()) {
            list.add(new CodeTreeNode(c, freqMap.get(c)));
        }
        CodeTreeNode tree = huffman(list);

        TreeMap<Character, String> codes = new TreeMap<>();
        for (Character c : freqMap.keySet()) {
            codes.put(c, tree.getCodeForCharacter(c, ""));
        }
        System.out.println(codes);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            builder.append(codes.get(text.charAt(i)));
        }
        System.out.println("Parent size: " + text.getBytes().length *8);
        System.out.println("Compressed size: " + builder.length());
        System.out.println("Compressed bytes: " + builder);

        String decoded = huffmanDecode(builder.toString(), tree);
        System.out.println("Decode: " + decoded);
    }

    private static CodeTreeNode huffman(ArrayList<CodeTreeNode> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            CodeTreeNode left = nodes.remove(nodes.size() - 1);
            CodeTreeNode right = nodes.remove(nodes.size() - 1);

            CodeTreeNode parent = new CodeTreeNode(null, right.weight +
                    left.weight, left, right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    public static TreeMap<Character, Integer> countFrequency(String text) {
        TreeMap<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            Integer count = map.get(c);
            map.put(c, count != null ? count + 1 : 1);
        }
        return map;
    }
    private static String huffmanDecode (String encoded, CodeTreeNode tree){
        StringBuilder builder = new StringBuilder();
        CodeTreeNode node = tree;
        for (int i = 0; i < encoded.length(); i++) {
            node = encoded.charAt(i) == '0' ? node.left : node.right;
            if(node.counter != null) {
                builder.append(node.counter);
                node = tree;
            }

        }
        return  builder.toString();
    }

    private static class CodeTreeNode implements Comparable<CodeTreeNode> {

        Character counter;
        int weight;
        CodeTreeNode left;
        CodeTreeNode right;

        public CodeTreeNode(Character counter, int weight, CodeTreeNode left, CodeTreeNode right) {
            this.counter = counter;
            this.weight = weight;
            this.left = left;
            this.right = right;
        }

        public CodeTreeNode(Character counter, int weight) {
            this.counter = counter;
            this.weight = weight;
        }

        @Override
        public int compareTo(CodeTreeNode o) {
            return o.weight - weight;
        }

        public String getCodeForCharacter(Character c, String parentPath) {
            if (counter == c) {
                return parentPath;
            } else {
                if (left != null) {
                    String path = left.getCodeForCharacter(c, parentPath + 0);
                    if (path != null) return path;
                }
                if (right != null) {
                    return right.getCodeForCharacter(c, parentPath + 1);
                }
            }
            return null;
        }
    }
}
