package com.lzx.hsp.dataStructures.linkedList.singleLinkedList;


/**
 * @author LZX
 * @code @create 2022-07-25 14:53
 * 单向链表 带头节点
 */
public class SingleLinkedList{

    private final SingleLinkedListNode head = new SingleLinkedListNode(0, "", "");
    //添加节点到链表尾部
    public void add(SingleLinkedListNode node){
        SingleLinkedListNode temp = head;
        //找到 next 域为null的节点
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
    }
    //按顺序添加节点
    public void addOrderByNo(SingleLinkedListNode node){
        // 把node插入 temp 和 temp.next 之间
        SingleLinkedListNode temp = head;
        // 找到正确的 next 位置
        while (true){
            // temp.next 的四种状况决定操作
            if (temp.next == null){
                break;
            }
            if (temp.next.no == node.no){
                System.out.println("节点已经存在");
                return;
            }
            if (node.no < temp.next.no){
                break;
            }
            temp = temp.next;
        }

        node.next = temp.next;
        temp.next = node;
    }
    //根据新节点的编号进行修改
    public void update(SingleLinkedListNode newNode){
        if (head.next == null){
            System.out.println("linkedList is empty");
            return;
        }
        //temp 为要找的节点, 若没找到则为空
        SingleLinkedListNode temp = head.next;
        while (temp != null && temp.no != newNode.no){
            temp = temp.next;
        }
        if (temp == null){
            System.out.println("no such node");
            return;
        }
        temp.name = newNode.name;
        temp.nickName = newNode.nickName;
    }
    //根据 no 删除节点
    public void delete(int nodeNo){
        if (head.next == null){
            System.out.println("linkedList is empty");
            return;
        }
        //temp 为要找的节点, 若没找到则为空
        SingleLinkedListNode temp = head.next;
        SingleLinkedListNode tempLast = head;
        while (temp != null && temp.no != nodeNo){
            tempLast = temp;
            temp = temp.next;
        }
        if (temp == null){
            System.out.println("no such node");
            return;
        }
        tempLast.next = temp.next;
    }
    //打印链表
    public void list(){
        System.out.println("*************************************");
        if (head.next == null){
            System.out.println("linkedList is empty");
        }else {
            //确定一定会打印一次, 使用do while
            SingleLinkedListNode temp = head.next;
            do {
                System.out.println(temp);
                temp = temp.next;
            }while (temp != null);
        }
    }


    //获取链表有效节点的个数
    public int getLength(){
        SingleLinkedListNode temp = head.next;
        int i = 0;
        while (temp != null){
            i++;
            temp = temp.next;
        }
        return i;
    }

    //查找单链表中的正数第 k 个结点
    public SingleLinkedListNode findIndexNode(int index){
        if (index < 1 || head.next == null){
            return null;
        }
        SingleLinkedListNode temp = head;
        for (int i = 1; i <= index; i++) {
            temp = temp.next;
            if (temp == null){
                break;
            }
        }
        return temp;
    }
    //查找单链表中的倒数第 k 个结点
    public SingleLinkedListNode findLastIndexNode(int lastIndex){
        int length = this.getLength();
        if (lastIndex < 1 || lastIndex > length){
            return null;
        }
        return findIndexNode(length - lastIndex + 1);
    }
    //单链表的反转
    public void rollbackList(){
        if (getLength() < 2){
            return;
        }
        SingleLinkedListNode temp1 = head.next;
        SingleLinkedListNode temp2 = temp1.next;
        SingleLinkedListNode temp3 = temp2.next;
        temp1.next = null;
        // 约定 t1 已经 完成操作
        while (true){
            //完成t2的操作
            temp2.next = temp1;
            //三个指针移动
            if (temp3 == null){
                break;
            }
            temp1 = temp2;
            temp2 = temp3;
            temp3 = temp3.next;
        }
        head.next = temp2;
    }
    //单链表的反转, 对上面方法的改进, 用head.next 替代 temp1
    public void rollbackList2(){
        if (getLength() < 2){
            return;
        }
        SingleLinkedListNode temp2 = head.next.next;
        SingleLinkedListNode temp3 = temp2.next;
        head.next.next = null;
        // 约定 t1 已经 完成操作
        while (true){
            //完成t2的操作
            temp2.next = head.next;
            //三个指针移动
            if (temp3 == null){
                break;
            }
            head.next = temp2;
            temp2 = temp3;
            temp3 = temp3.next;
        }
        head.next = temp2;
    }
}
