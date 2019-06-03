public class TestBST extends BSTTest {


      // the return type must be the name of the data structure class you are testing
  
      @Override
      protected BST<String, String> createInstance() {
          return new BST<String, String>();
      }
      
      @Override
      protected BST<Integer, String> createInstance2() {
          return new BST<Integer, String>();
      }

  }

