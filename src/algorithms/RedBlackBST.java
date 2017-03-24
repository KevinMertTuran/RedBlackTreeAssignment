package algorithms;

import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tobias
 */
public class RedBlackBST<k, v> implements MapInterFace<k, v> {

    private RedBlackNode<k, v> root;
    private Comparator<k> comp;
    private int count;

    public RedBlackBST(Comparator<k> comp) {
        this.root = null;
        this.comp = comp;
        this.count = 0;
    }

    private RedBlackNode<k, v> insert(k data, v value, RedBlackNode<k, v> h) {
        if (h == null) {
            count ++;
            return new RedBlackNode<>(data, value);
        }
        
        int c = comp.compare(data, h.getData());
        
        if (c < 0) {
            h.setLeft(insert(data, value, h.getLeft()));
        } else if (c > 0) {
            h.setRight(insert(data, value, h.getRight()));
        } else {
            h.setData(data);
        }
        //Now for the rotating
        if (isRed(h.getRight()) && !isRed(h.getLeft())) {
            h = rotateLeft(h);
        }

        if (isRed(h.getLeft()) && isRed(h.getLeft().getLeft())) {
            h = rotateRight(h);
        }

        if (isRed(h.getLeft()) && isRed(h.getRight())) {
            flipColors(h);
        }
        return h;
    }

    private boolean isRed(RedBlackNode<k, v> node)
    {
        if(node == null) return false;
        return node.isRed();
    }
    
    
    private RedBlackNode<k,v> rotateLeft(RedBlackNode<k,v> h)
    {
        RedBlackNode<k,v> tmp = h.getRight();
        h.setRight(tmp.getLeft());
        tmp.setLeft(h);
        tmp.setIsRed(h.isRed());
        h.setIsRed(true);
        return tmp;
    }
    
    private RedBlackNode<k,v> rotateRight(RedBlackNode<k,v> h)
    {
        RedBlackNode<k,v> tmp = h.getLeft();
        h.setLeft(tmp.getRight());
        tmp.setRight(h);
        tmp.setIsRed(h.isRed());
        h.setIsRed(true);
        return tmp;
    }
    
    private void flipColors(RedBlackNode<k,v> h)
    {
        h.getLeft().setIsRed(false);
        h.getRight().setIsRed(false);
        h.setIsRed(true);
    }
    
    @Override
    public void put(k key, v value) {
        if(key == null)
            throw new NullPointerException("data should not be null");
        this.root = insert(key, value, root);
        this.root.setIsRed(false);
    }
    
    private v getMethod(k key, RedBlackNode<k,v> root){
        if(root == null)
            return null;
        int c = comp.compare(key, root.getData());
        
        if(c == 0){
            return root.getValue();
        } 
        else if(c < 0){
            return getMethod(key, root.getLeft());
        }
        else {
            return getMethod(key, root.getRight());
        }
    }

    @Override
    public v get(k key) {
        return getMethod(key, root);
    }

    @Override
    public int size() {
        return count;
    }

}