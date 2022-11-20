package com.lzx.utils.tree;

import com.lzx.utils.NumberUtils;

import java.lang.reflect.Constructor;

/**
 * @author LZX
 * @code @create 2022-10-23 10:07:55
 */
public class RandomBTUtils {
    private BTNode root;
    private int minNumOfNodes;
    private int maxNumOfNodes;
    private int minValue;
    private int maxValue;
    private int maxLevel;
    private int numOfNodes = 1;

    public RandomBTUtils(BTNode root, int minNumOfNodes, int maxNumOfNodes, int minValue, int maxValue, int maxLevel) {
        this.root = root;
        this.minNumOfNodes = minNumOfNodes;
        this.maxNumOfNodes = maxNumOfNodes;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.maxLevel = maxLevel;
    }

    public BTNode generateRandomBT(){
        if (root == null || maxNumOfNodes < 1){
            return root;
        }
        if (root.getLeft() != null || root.getRight() != null){
            System.out.println("root is illegal");
            return root;
        }
        if (minNumOfNodes > Math.pow(2, maxLevel)-1){
            System.out.println("root is illegal, minNumOfNodes too big");
            return root;
        }
        while (numOfNodes < minNumOfNodes){
            generateRandomBT(root, 1);
        }
        return root;
    }
    private void generateRandomBT(BTNode root, int level) {
        if (numOfNodes >= maxNumOfNodes || level >= maxLevel){
            return;
        }
        if (root.getLeft() == null){
            if (Math.random() < 0.5){
                try {
                    Constructor<? extends BTNode> constructor = root.getClass().getDeclaredConstructor();
                    BTNode left = constructor.newInstance();
                    left.setValue(NumberUtils.getRandomInt(minValue, maxValue));
                    root.setLeft(left);
                    numOfNodes++;
                    generateRandomBT(left, level+1);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }else {
            generateRandomBT(root.getLeft(), level+1);
        }

        if (root.getRight() == null){
            if (Math.random() < 0.5){
                try {
                    Constructor<? extends BTNode> constructor = root.getClass().getDeclaredConstructor();
                    BTNode right = constructor.newInstance();
                    right.setValue(NumberUtils.getRandomInt(minValue, maxValue));
                    root.setRight(right);
                    numOfNodes++;
                    generateRandomBT(right, level+1);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }else {
            generateRandomBT(root.getRight(), level+1);
        }
    }
}
