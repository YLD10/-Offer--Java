package top.yld10.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {

    }
    
    
    
    // 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
    // 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
    // 判断数组中是否含有该整数。
    public boolean Find(int target, int [][] array) {
        int len = array.length;
        int j = array[0].length - 1;
        
        // 从右上角开始, target 较小则左移, 较大则下移
        for (int i = 0; i < len && j >= 0; ) {
            if (target == array[i][j]) {
                return true;
            }else if (target < array[i][j]) {
                -- j;
            }else {
                ++ i;
            }
        }
        return false;
    }
    
    
    
    // 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.
    // 则经过替换之后的字符串为We%20Are%20Happy。
    public String replaceSpace(StringBuffer str) {
        int blankCount = 0;
        
        // 先找出空格数
        for (int i = 0; i < str.length(); ++ i) {
            if (' ' == str.charAt(i)) {
                ++ blankCount;
            }
        }
        
        // 计算替换后的字符串长度
        char[] cStr = new char[str.length() + blankCount * 2];
        int j = cStr.length - 1;
        
        // 对空格进行替换, 非空格直接保留
        for (int i = str.length()-1; i >= 0; -- i) {
            if (' ' == str.charAt(i)) {
                cStr[j--] = '0';
                cStr[j--] = '2';
                cStr[j--] = '%';
            }else {
                cStr[j--] = str.charAt(i);
            }
        }
        
        return String.valueOf(cStr);
    }
    
    
    
    // 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    // 递归版, 逆序输出链表值
    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        // 未至链尾, 继续递归
        if (listNode != null) {
            printListFromTailToHead(listNode.next);
            arrayList.add(listNode.val);
        }
        return arrayList;
    }
    
    
    
    // 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
    // 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    // 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length != in.length) {
            System.out.println("length error !");
            return null;
        }
        
        // 长度为 1 时表明已经可以直接构建结点了
        if (1 == pre.length) {
            return new TreeNode(pre[0]);
        }
        
        int root = pre[0], rootInIndex = 0;
        int leftLength = 0, rightLength = 0;
        TreeNode tree = new TreeNode(root);
        
        // 在前序中找出根结点, 并记录下位置
        for (int i = 0; i < in.length; ++ i) {
            if (in[i] == root) {
                rootInIndex = i;
                break;
            }
            ++ leftLength;
        }
        rightLength = in.length - leftLength - 1;
        
        // 构建左子树
        if (leftLength > 0) {
            tree.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, leftLength+1), Arrays.copyOfRange(in, 0, leftLength));
        }else {
            tree.left = null;
        }
        // 构建右子树
        if (rightLength > 0) {
            tree.right = reConstructBinaryTree(Arrays.copyOfRange(pre, leftLength+1, pre.length), Arrays.copyOfRange(in, rootInIndex+1, in.length));
        }else {
            tree.right = null;
        }
        
        return tree;
    }
    
    
    
    // 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
    class Queue {
        Stack<Integer> stack1 = new Stack<Integer>(); // 队尾
        Stack<Integer> stack2 = new Stack<Integer>(); // 队头
        
        public void push(int node) {
            stack1.push(node);
        }
        
        public int pop() {
            // 队头为空, 从队尾搬数据
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            
            if (stack2.isEmpty()) {
                System.out.println("Empty !");
                return -1;
            }else {
                return stack2.pop();
            }
        }
    }
    
    
    
    // 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 
    // 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。 
    // 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 
    // NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
    // 递归版
    public int minNumberInRotateArrayRecursive(int[] array) {
        if (null == array) {
            throw new NullPointerException();
        }
        
        if (0 == array.length) {
            return 0;
        }
        
        if (1 == array.length) {
            return array[0];
        }
        
        int min = 0, left = 0, right = 0;
        int midIndex = array.length / 2;
        
        if (array[midIndex] < array[midIndex-1]) {
            min = array[midIndex];
        }else {
            if (midIndex + 1 < array.length) {
                if (array[midIndex] > array[midIndex+1]) {
                    min = array[midIndex+1];
                }else {
                    left = minNumberInRotateArray(Arrays.copyOfRange(array, 0, midIndex));
                    right = minNumberInRotateArray(Arrays.copyOfRange(array, midIndex+1, array.length));
                    
                    min = (left > right) ? right : left;
                }
            }else {
                min = array[0];
            }
        }
        
        return min;
    }
    // 非递归
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }

        int left = 0;
        int right = array.length - 1;
        int middle = 0;

        while (array[left]>=array[right]) {
            if(right-left==1) {
                middle = right;
                break;
            }
            middle = left + (right - left) / 2;
            if (array[middle] >= array[left]) {
                left = middle;
            }
            if (array[middle] <= array[right]) {
                right = middle;
            }
        }

        return array[middle];
    }

    
    
    // 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
    // n<=39
    public int Fibonacci(int n){
        if (0 > n) {
            throw new NumberFormatException("n cann't be negative");
        }
        
        if (0 == n) {
            return 0;
        }
        
        if (1 == n) {
            return 1;
        }
        
        int pre = 0, nex = 1;
        int result = 0;
        
        // 0 1 1 2 3 当 pre = 1, nex = 2 时, result = 3
        while (n > 1) {
            result = pre + nex;
            pre = nex;
            nex = result;
            -- n;
        }
        
        return result;
    }
    
    
    // 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
    // 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
    public int JumpFloor(int target) {
        if (target < 0) {
            throw new NumberFormatException("target cann't be negative");
        }
        
        if (1 == target) {
            return 1;
        }
        
        if (2 == target) {
            return 2;
        }
        
        // 两种情况, 第一次跳 1 级台阶或者第一次跳 2 级台阶
        int count = JumpFloor(target - 1);
        count += JumpFloor(target - 2);

        return count;
    }
    
    
    
    // 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
    // 求该青蛙跳上一个n级的台阶总共有多少种跳法。
    public int JumpFloorII(int target) {
        if (target < 0) {
            throw new NumberFormatException("target cann't be negative");
        }
        
        if (1 == target) {
            return 1;
        }
        
        if (2 == target) {
            return 2;
        }
        
        // 最后总能一次性跳上全部台阶
        int count = 1;
        
        // target 种情况, 第一次跳 1 级台阶或者第一次跳 2 级台阶或者第一次跳 3 级台阶或者...或者 n-1 级台阶
        while (target > 1) {
            count += JumpFloorII(-- target);
        }

        return count;
    }
    
    
    
    // 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
    // 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
    public int RectCover(int target) {
        if (target < 0) {
            throw new NumberFormatException("target cann't be negative");
        }
        
        if (0 == target) {
            return 0;
        }
        
        if (1 == target) {
            return 1;
        }
        
        if (2 == target) {
            return 2;
        }
        
        // 从左上角开始, 两种情况, 先用 1 个 2*1 矩形竖着填充或者先用 2 个 2*1 矩形填充 2*2 正方形
        int count = RectCover(target - 1);
        count += RectCover(target - 2);
        
        return count;
    }

    
    
    // 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
    public int NumberOf1(int n) {
        int count = 0;
        
        while (n != 0) {
            ++ count;
            n &= n - 1;
        }
        
        return count;
    }
    
    
    
    // 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
    public double Power(double base, int exponent) {
        if (0 == exponent) {
            return 1;
        }
        
        if (1 == exponent) {
            return base;
        }
        
        if (-1 == exponent) {
            if (doubleCompareWithZero(base)) {
               throw new ArithmeticException("Divide by zero !"); 
            }else {
                return 1 / base;
            }
        }
        
        int posExponent = exponent;
        if (exponent < 0) {
            posExponent = -exponent;
        }
        
        int half = posExponent / 2;
        double result = base;
        
        // 快速乘幂
        while (half > 0) {
            half /= 2;
            result *= result;
        }

        // 奇数还需要乘多一次
        if ((posExponent & 1) != 0) {
            result *= base;
        }
        
        if (exponent < 0) {
            result = 1 / result;
        }
        
        return result;
    }
    // 判断 double 数据是否很接近 0
    public boolean doubleCompareWithZero(double d) {
        if (Math.abs(d - 0) < 0.0000001) {
            return true;
        }
        
        return false;
    }
    
    
    
    // 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
    // 使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，
    // 并保证奇数和奇数，偶数和偶数之间的相对位置不变.
    // 空间换时间
    public void reOrderArray(int[] array) {
       if (null == array) {
           throw new NullPointerException("array is null !");
       }
       
       int[] arrayCopy = Arrays.copyOf(array, array.length);
       int odd = 0;
       
       // 奇数先安排位置
       for (int i = 0; i < arrayCopy.length; ++ i) {
           if ((arrayCopy[i] & 1) != 0) {
               array[odd++] = arrayCopy[i];
           }
       }
       
       // 偶数接着后面
       for (int i = 0; i < arrayCopy.length; ++ i) {
           if ((arrayCopy[i] & 1) == 0) {
               array[odd++] = arrayCopy[i];
           }
       }
    }

    
    
    
    
}
