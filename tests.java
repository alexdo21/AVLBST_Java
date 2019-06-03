import static org.junit.jupiter.api.Assertions.fail;
import java.util.List;

public class tests {
  public static void main(String[] args) throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    BST<Integer, String> a = new AVL<Integer, String>();
//    
//    
//      a.insert(5, null);
//      a.insert(9, null);
//      a.insert(7, null);
//      a.insert(4, null);
//      a.insert(8, null);
//      a.insert(3, null);
//      a.insert(2, null);
//      a.insert(6, null);
//      a.insert(1, null);
//      
//      List<Integer> list = a.getLevelOrderTraversal();
//      
//      for (Integer c: list) {
//        System.out.print(c + " ");
//      }
//      
//      System.out.println();
//      
//      a.remove(a.root.key);
//      a.remove(a.root.key);
//      a.remove(a.root.key);
//      a.remove(a.root.key);
//      System.out.println(a.root.left);
//      System.out.println(a.remove(a.root.key));
//      
//      List<Integer> list1 = a.getLevelOrderTraversal();
//      
//      for (Integer c: list1) {
//        System.out.print(c + " ");
//      }
//    
//    AVL<Integer, String> and = new AVL<Integer, String>();
//    
//    and.insert(10, null);
//    and.insert(20, null);
//    and.insert(30, null);
//    System.out.println(and.contains(30));
//    System.out.println(and.getKeyOfRightChildOf(and.getKeyAtRoot()));
//    
//    for (Integer a:and.getLevelOrderTraversal()) {
//      System.out.print(a + " ");
//    }
    try {
      a.insert(25, null);
      a.insert(76, null);
      a.insert(17, null);
      a.insert(63, null);
      a.insert(91, null);
      a.insert(46, null);
      a.insert(45, null);
      a.insert(93, null);
      a.insert(31, null);
      a.insert(77, null);
      
      System.out.println(a.getPreOrderTraversal());
      System.out.println(a.getPostOrderTraversal());
      System.out.println(a.getLevelOrderTraversal());
      
      a.remove(63);
      System.out.println(a.getLevelOrderTraversal());
      System.out.println(a.getPostOrderTraversal());

    } catch (Exception e) {
      e.printStackTrace();
      //fail("Unexpected exception 004: " + e.getMessage());
    }
     
  }
}
