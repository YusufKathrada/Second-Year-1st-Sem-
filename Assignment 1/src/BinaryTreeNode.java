// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman

public class BinaryTreeNode<dataType>
{
   dataType data;
   BinaryTreeNode<dataType> left;
   BinaryTreeNode<dataType> right;
   
   public BinaryTreeNode ( dataType d, BinaryTreeNode<dataType> l, BinaryTreeNode<dataType> r )
   {
      data = d;
      left = l;
      right = r;
   }
   
   BinaryTreeNode<dataType> getLeft () { return left; }
   BinaryTreeNode<dataType> getRight () { return right; }
   
   //public String toString()
   //{
     // String x = .data.getData();
     // System.out.println(x);
     // return x;
  
//}

   public String toString()      // Added toString() in order to print data from node
   {
      return data.toString();    // data is of type Entry which invokes the toString() from Entry
   }
}