package javaapplication34;
import java.util.*;
import javax.swing.tree.*;

public class JavaApplication34 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();     
        String[] temp = text.split(",");
        int n = temp.length;
        int[] data = new int[n];
        for(int i=0; i<n; i++)
            data[i] = Integer.parseInt(temp[i].trim());                
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(data[0]);
        DefaultMutableTreeNode p = root;
        for (int i = 1; i < data.length; i++) {
            while (true) {
                if (p.getChildCount() == 0) {
                    if (data[i] < Integer.parseInt(p.toString())) {
                        p.add(new DefaultMutableTreeNode(data[i]));
                        p.add(new DefaultMutableTreeNode(0));
                    } else {
                        p.add(new DefaultMutableTreeNode(0));
                        p.add(new DefaultMutableTreeNode(data[i]));
                    }        
                    break;
                }
                else if (p.getChildCount() == 2) {
                    if (data[i] < Integer.parseInt(p.toString())) {
                        if (Integer.parseInt(p.getChildAt(0).toString()) == 0) {
                            p.getNextNode().setUserObject(data[i]);
                            break;
                        }
                        p = p.getNextNode();

                    } else {
                        if (Integer.parseInt(p.getChildAt(1).toString()) == 0) {
                            p.getNextNode().getNextSibling().setUserObject(data[i]);
                            break;
                        }
                        p = p.getNextNode().getNextSibling();
                    }
                }
            }
            p = root;
        }
        System.out.println(root.getDepth()+" "+n);
        boolean avl = true;
        String str = "BF: ";
        String str2 ="";
        String str3 ="";
        int oldbf = 0;
        Enumeration<DefaultMutableTreeNode> enum1 = (Enumeration<DefaultMutableTreeNode>)(Object)root.breadthFirstEnumeration();
        while (enum1.hasMoreElements()) {
            DefaultMutableTreeNode node = enum1.nextElement();
            int bf = 0;
            if (node.isLeaf() && Integer.parseInt(node.toString()) != 0) {
                str += node.toString()+"("+bf+")"+", ";
            }
            if (!node.isLeaf() && Integer.parseInt(node.toString()) != 0) {
                DefaultMutableTreeNode x = (DefaultMutableTreeNode) node.getChildAt(0); // left subtree
                DefaultMutableTreeNode y = (DefaultMutableTreeNode) node.getChildAt(1); // right subtree
                oldbf=bf;
                bf = 0;
                if (Integer.parseInt(x.toString()) != 0) {
                    bf = x.getDepth() + 1;
                }
                if (Integer.parseInt(y.toString()) != 0) {
                    bf -= y.getDepth() + 1;
                }
                str += node.toString()+"("+bf+")"+", ";
                if(bf>1){
                    str2+="L";
                    DefaultMutableTreeNode xi = (DefaultMutableTreeNode) x.getChildAt(0);
                    if(Integer.parseInt(x.toString()) < Integer.parseInt(xi.toString())){
                        str2+="L";
                        str3+=node.toString();
                    }
                    else if(Integer.parseInt(x.toString()) > Integer.parseInt(xi.toString())){
                        str2+="R";
                        str3+=x.toString()+", "+node.toString();
                    }
                }
                else if(bf<-1){
                    str2+="R";
                    DefaultMutableTreeNode yi = (DefaultMutableTreeNode) y.getChildAt(0);
                    if(Integer.parseInt(y.toString()) < Integer.parseInt(yi.toString())){
                        str2+="R";
                        str3+=node.toString();
                    }
                    else if(Integer.parseInt(y.toString()) > Integer.parseInt(yi.toString())){
                        str2+="L";
                        str3+=y.toString()+", "+node.toString();
                    }
                }
            }
        }
        System.out.println(str.substring(0,str.length()-2));
        if(!str2.equals(""))
            System.out.println(str2+":"+str3);
    }

}
