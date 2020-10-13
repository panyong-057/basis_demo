package com.example.algorithm.tree;

public class RBTest {


    /**
     * 左旋
     * @author Alfred
     * @param x 输入结点
     */
    private void leftRotated(RBTreeNode x){
        RBTreeNode y = x.getRight();
        //x的右孩子y不能是NIL_T，如果是的话，直接返回。
        if(y == NIL_T){
            return;
        }
        //将y的左子树变为x的右子树
        //设置x的右子树
        x.setRight(y.getLeft());
        //设置y的右子树的父结点为x
        if(y.getLeft() != NIL_T){
            y.getLeft().setParent(x);
        }
        //将x的父结点设置为y的父结点
        y.setParent(x.getParent());
        //如果x是根结点，则更换根结点
        if(x.getParent() == NIL_T){
            rootNode = y;
        }else if(x == x.getParent().getLeft()){
            //如果x是其父结点的左孩子，则将y设为其父结点的左孩子
            x.getParent().setLeft(y);
        }else{
            //如果x是其父结点的右孩子，则将y设为其父结点的右孩子
            x.getParent().setRight(y);
        }
        //y的左孩子为x
        y.setLeft(x);
        //x的父结点为y
        x.setParent(y);
    }

    /**
     * 右旋
     * @author Alfred
     * @param y 输入结点
     */
    private void rightRotated(RBTreeNode y){
        RBTreeNode x = y.getLeft();
        //y的左孩子x不能是NIL_T，如果是的话，直接返回。
        if(x == NIL_T){
            return;
        }
        //将x的右子树变为y的左子树
        //设置y的左子树
        y.setLeft(x.getRight());
        //设置x的右子树的父结点为y
        if(x.getRight() != NIL_T){
            x.getRight().setParent(y);
        }
        //将y的父结点设置为x的父结点
        x.setParent(y.getParent());
        //如果y是根结点，则更换根结点
        if(y.getParent() == NIL_T){
            rootNode = x;
        }else if(y == y.getParent().getLeft()){
            //如果y是其父结点的左孩子，则将x设为其父结点的左孩子
            y.getParent().setLeft(x);
        }else{
            //如果y是其父结点的右孩子，则将x设为其父结点的右孩子
            y.getParent().setRight(x);
        }
        //x的右孩子为y
        x.setRight(y);
        //y的父结点为x
        y.setParent(x);
    }


    /**
     * 插入操作
     * @author Alfred
     * @param k
     */
    public void treeInsert(int k){
        RBTreeNode z = new RBTreeNode(k, NodeColor.RED);
        RBTreeNode y = NIL_T;
        RBTreeNode x = rootNode;
        //与二叉查找树的插入过程类似l
        while(x != NIL_T){
            y = x;
            if(z.getKey() < x.getKey()){
                x = x.getLeft();
            }else{
                x = x.getRight();
            }
        }
        z.setParent(y);
        if(y == NIL_T){
            rootNode = z;
        }else if(z.getKey() < y.getKey()){
            y.setLeft(z);
        }else{
            y.setRight(z);
        }
        z.setLeft(NIL_T);
        z.setRight(NIL_T);
        //进行修复
        rbInsertFixUp(z);
    }



    /**
     * 修复插入操作引起的不满足的红黑性质
     * @author Alfred
     * @param z 要修复的结点
     */
    private void rbInsertFixUp(RBTreeNode z){
        RBTreeNode y = null;
        while(z.getParent().getColor() == NodeColor.RED){
            //如果z的父结点是z的祖父结点的左孩子
            if(z.getParent() == z.getParent().getParent().getLeft()){
                y = z.getParent().getParent().getRight();
                //情况1)，z的叔叔y的颜色是红色的。
                if(y.getColor() == NodeColor.RED){
                    z.getParent().setColor(NodeColor.BLACK);
                    y.setColor(NodeColor.BLACK);
                    z.getParent().getParent().setColor(NodeColor.RED);
                    z = z.getParent().getParent();
                }else if(z == z.getParent().getRight()){
                    //情况2),z的叔叔y的颜色是黑色的，且z是其父结点的右孩子
                    z = z.getParent();
                    leftRotated(z);
                    //情况2)经过左旋之后变为情况3),z的叔叔y的颜色是黑色的，且z是其父结点的左孩子
                    z.getParent().setColor(NodeColor.BLACK);
                    z.getParent().getParent().setColor(NodeColor.RED);
                    rightRotated(z.getParent().getParent());
                }
            }else{
                //与上面情况类似。
                y = z.getParent().getParent().getLeft();
                if(y.getColor() == NodeColor.RED){
                    z.getParent().setColor(NodeColor.BLACK);
                    y.setColor(NodeColor.BLACK);
                    z.getParent().getParent().setColor(NodeColor.RED);
                    z = z.getParent().getParent();
                }else if(z == z.getParent().getLeft()){
                    z = z.getParent();
                    rightRotated(z);
                    z.getParent().setColor(NodeColor.BLACK);
                    z.getParent().getParent().setColor(NodeColor.RED);
                    leftRotated(z.getParent().getParent());
                }

            }
        }
        //修复性质2)
        rootNode.setColor(NodeColor.BLACK);
    }

}
