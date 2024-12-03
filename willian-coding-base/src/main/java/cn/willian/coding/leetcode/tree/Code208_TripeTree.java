package cn.willian.coding.leetcode.tree;

/**
 * 前缀树
 * 
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @since 2024-12-01 22:06:36
 */
// 208. 实现前缀树
// https://leetcode.cn/problems/implement-trie-prefix-tree/description
@SuppressWarnings("all")
public class Code208_TripeTree {

    TripeNode root;

    public Code208_TripeTree() {
        this.root = new TripeNode();
    }

    public static void main(String[] args) {

        Code208_TripeTree tree = new Code208_TripeTree();
        String[] words = {"app", "apple", "abc", "apple", "bcd", "edf"};
        for (String word : words) {
            tree.insert(word);
        }
        int res = tree.search("apple");
        int count = tree.prefixNumber("app");
        System.out.println(res);
        System.out.println(count);
    }

    public void insert(String word) {

        if (word == null || word.isEmpty()) {
            return;
        }
        char[] chars = word.toCharArray();
        TripeNode node = root;
        node.pass++;
        int index;
        for (char c : chars) {
            index = c - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TripeNode();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end++;
    }

    public void delete(String word) {

        if (search(word) == 0) {
            return;
        }
        char[] chars = word.toCharArray();
        TripeNode node = root;
        node.pass--;
        int index;
        for (char c : chars) {
            index = c - 'a';
            if (--node.nexts[index].pass == 0) {
                node.nexts[index] = null;
                return;
            }
            node = node.nexts[index];
        }
        node.end--;
    }

    // 查询加入过几次这个单词
    public int search(String word) {

        if (word == null || word.isEmpty()) {
            return 0;
        }
        char[] chars = word.toCharArray();
        TripeNode node = root;
        int index;
        for (char c : chars) {
            index = c - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.end;
    }

    // 查询多少以指定字符为前缀
    public int prefixNumber(String prefix) {

        if (prefix == null || prefix.isEmpty()) {
            return 0;
        }
        char[] chars = prefix.toCharArray();
        TripeNode node = root;
        int index;
        for (char c : chars) {
            index = c - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.pass;
    }

    public static class TripeNode {

        private final TripeNode[] nexts;
        /**
         * 字符经过的节点个数
         */
        private int pass;
        /**
         * 字符结尾的位置
         */
        private int end;

        public TripeNode() {
            nexts = new TripeNode[26];
        }
    }
}
