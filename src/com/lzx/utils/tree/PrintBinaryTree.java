package com.lzx.utils.tree;

import com.lzx.utils.OtherUtils;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZX
 * @code @create 2022-10-23 10:36:06
 * 二叉树的打印类
 *      用法 :
 *          要打印的树的结点必须实现 BTNode 接口
 *          初始化本类时传入树的头结点
 *          调用 print() 即可打印二叉树
 *      说明 :
 *          只适合打印 "密集" 的二叉树, 即宽度大而高度小, 二叉树的高度最好不超过 6 层
 * 待改进 :
 *      getLeftmostNode()
 *          二叉树最左边的结点 "过右" 时, 左边打印太多的空格
 */
public class PrintBinaryTree{
    // 数的根节点
    public BTNode root;

    // 每个元素打印的最大长度
    private int maxLength = 0;
    // 存储二叉树的二维数组
    private List<List<Pair<BTNode, Integer>>> lists;
    // 最大层数
    private int level;
    // 每一层开始元素的位置
    private String[] startBlank;
    // 元素之间的间隔
    private String[] separatorBlank;


    public PrintBinaryTree(BTNode root) {
        this.root = root;
    }

    public void print(){
        print(true);
    }
    public void print(boolean printInfo){
        if (root == null){
            System.out.println("tree is empty");
            return;
        }

        System.out.println("********************** PrintBinaryTree **********************");
        // 初始化参数
        init();
        // 遍历每一层
        for (int i = 0; i < level; i++) {

            List<Pair<BTNode, Integer>> nodes = lists.get(i);
            Pair<BTNode, Integer> pair = nodes.get(0);
            BTNode node;
            StringBuilder printNode = new StringBuilder();

            // 当前结点的 id
            int id = pair.getValue();
            // 当前二叉树对应的完全二叉树 中 当前层的第一个元素 的 id
            int startId = (int) Math.pow(2, i);
            // lastId 记录当前行上一个元素的 id, 第一个元素的 lastId 应为 startId
            int lastId = startId;


            // 输出每一层开头的空格
            printNode.append(startBlank[i]);

            // 输出结点
            if (id != lastId){
                // 如果需要的话, 虚拟一个 第一个元素
                printNode.append(OtherUtils.getBlank(maxLength));
            }
            for (int j = 0; j < nodes.size(); j++) {
                pair = nodes.get(j);
                node = pair.getKey();
                id = pair.getValue();
                // 处理当前结点和上一个结点之间的间隔
                for (int k = 0; k < id - lastId; k++) {
                    printNode.append(separatorBlank[i]);
                }
                printNode.append(OtherUtils.getBlank( (id-lastId-1) * maxLength ));
                // 打印当前结点
                printNode.append(printNode(node));
                // 更新 lastId
                lastId = id;
            }

            // 输出连接符
            if (separatorBlank[i].length() > 1 && i > 0 && i < level-1){
                // 当 当前层的间隔大于 1, 且不是第一层时或最后一层
                StringBuilder printConnector = new StringBuilder(OtherUtils.getBlank(printNode.length()));
                int start;
                int end;
                // 连接符打印在 (父元素中点 和 当前元素左边界或右边界) 的中点
                int mid;
                for (int j = 0; j < nodes.size(); j++) {
                    pair = nodes.get(j);
                    id = pair.getValue();

                    // 父子结点之间 加入 "/" "\" 表达父子关系
                    if (id % 2 == 0){
                        // 左儿子,  id 为偶数
                        start = startBlank[i].length() + (id - startId) * (maxLength + separatorBlank[i].length());
                        end = start + maxLength + separatorBlank[i].length() / 2;
                        mid = start + ((end-start)>>1);
                        if (mid >= printConnector.length()){
                            printConnector.append(OtherUtils.getBlank(mid-printConnector.length()+1));
                        }
                        printConnector.replace(mid, mid+1, "/");
                    }else {
                        // 右儿子,  id 为奇数,
                        start =
                                startBlank[i].length() +
                                (id - 1 - startId) * (maxLength + separatorBlank[i].length()) +
                                maxLength + separatorBlank[i].length() / 2;   // 等于它左兄弟的end
                        end = start + maxLength + separatorBlank[i].length() / 2;
                        mid = start + ((end-start)>>1);
                        if (mid >= printConnector.length()){
                            printConnector.append(OtherUtils.getBlank(mid-printConnector.length()+1));
                        }
                        printConnector.replace(mid, mid+1, "\\");
                    }
                }
                // 真正打印连接符
                System.out.println(printConnector);
            }

            // 真正打印结点
            System.out.println(printNode);
        }
        // 打印结点详细信息
        if (printInfo){
            for (int i = 0; i < level; i++) {
                List<Pair<BTNode, Integer>> nodes = lists.get(i);
                System.out.println("*********** level " + (i+1) + " ***********");
                for (Pair<BTNode, Integer> pair : nodes) {
                    BTNode node = pair.getKey();
                    Integer id = pair.getValue();
                    System.out.print("node = ");
                    if (node.getLeft() != null){
                        System.out.print(node.getLeft().getValue() + " <- ");
                    }
                    System.out.print(node.getValue());
                    if (node.getRight() != null) {
                        System.out.print(" -> " + node.getRight().getValue());
                    }
                    System.out.println("  | id = " + id);
                }
            }
        }
    }

    // 获取 lists 等变量的值
    private void init(){
        lists = levelOrderTraversal();
        level = lists.size();
        startBlank = new String[level];
        separatorBlank = new String[level];

        // 最后一层元素的 开始位置 为顶格
        int startBlankNum = 0;
        // 最后一层元素的 间隔 为 元素的最大长度
        int separatorBlankNum = maxLength;
        // 逐层计算
        for (int i = level; i >= 1; i--) {
            // getBlank() 的作用为生成指定长度的空格
            startBlank[i-1] = OtherUtils.getBlank(startBlankNum);
            separatorBlank[i-1] = OtherUtils.getBlank(separatorBlankNum);

            // 上一层元素的开始位置 = 当前层元素的开始位置 + 元素的最大长度/2 + 当前元素间隔/2
            startBlankNum = startBlankNum + (maxLength + separatorBlankNum)/2;

            // 上一层元素的间隔 = 当前层间隔*2 + 元素最大长度
            separatorBlankNum = separatorBlankNum * 2 + maxLength;
        }
    }

    // 层次遍历将整颗树添加进一个二维 list 中
    // list 的元素 是一个 pair, 存储 当前结点 以及当前结点在 当前二叉树对应的完全二叉树 中的位置编号
    private List<List<Pair<BTNode, Integer>>> levelOrderTraversal(){
        List<List<Pair<BTNode, Integer>>> list = new ArrayList<>();
        levelOrderTraversal(root, list, 1, 1);
        return list;
    }
    private void levelOrderTraversal(BTNode node, List<List<Pair<BTNode, Integer>>> list, int level, int id){

        // 前序遍历二叉树
        // 对二叉树的每一层的元素而言, 在前序遍历中是有序的
        // node 为当前遍历到的元素, level 为 node 所处的层, id 为 node 在 当前二叉树对应的完全二叉树 中的位置编号
        if (list.size() < level){
            list.add(new ArrayList<>());
        }
        // 将当前结点添加到 list 的 level 层中
        Pair<BTNode, Integer> pair = new Pair<>(node, id);
        list.get(level-1).add(pair);
        maxLength = Math.max(maxLength, node.getValue().length());

        if (node.getLeft() != null){
            levelOrderTraversal(node.getLeft(), list, level+1, id*2);
        }
        if (node.getRight() != null){
            levelOrderTraversal(node.getRight(), list, level+1, id*2 + 1);
        }
    }

    // 计算一个节点要打印的字符串, 长度一定为 maxLength
    private String printNode(BTNode node){
        String val = node.getValue();
        if (val.length() >= maxLength){
            return val.substring(0, maxLength);
        }else {
            int start = (maxLength - val.length()) / 2;
            int end = maxLength - start - val.length();
            return OtherUtils.getBlank(start) + val + OtherUtils.getBlank(end);
        }
    }

    // 获取二叉树最左边的元素, 防止二叉树最左边的结点 "过右" 时, 左边打印太多的空格
    private void getLeftmostNode(){

    }
}
