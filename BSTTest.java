//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P2 AVL BST
// Files: BST.java, AVL.java, BSTTest.java, AVLTest.java, BSTNode.java, resultsBST.png,
//////////////////// resultsAVL.png
// Course: CS 400 Spring 2019
//
// Author: Alex Do
// Email: ado@wisc.edu
// Lecturer's Name: Deb Deppeler, Lecture 001
// Due Data: 02/24/2019
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: none
// Partner Email: none
// Partner Lecturer's Name: none
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: none
// Online Sources: none
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * This class tests correct order and structure of BST tree after inserting and removing various
 * nodes. Tests for correct all traversal orders of balanced and not-balanced trees.
 * 
 * @author AlexDo
 *
 */
public class BSTTest extends DataStructureADTTest {

  BST<String, String> bst;
  BST<Integer, String> bst2;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    // The setup must initialize this class's instances
    // and the super class instances.
    // Because of the inheritance between the interfaces and classes,
    // we can do this by calling createInstance() and casting to the desired type
    // and assigning that same object reference to the super-class fields.
    dataStructureInstance = bst = createInstance();
    dataStructureInstance2 = bst2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = bst = null;
    dataStructureInstance2 = bst2 = null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADTTest#createInstance()
   */
  @Override
  protected BST<String, String> createInstance() {
    return new BST<String, String>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADTTest#createInstance2()
   */
  @Override
  protected BST<Integer, String> createInstance2() {
    return new BST<Integer, String>();
  }

  /**
   * Test that empty trees still produce a valid but empty traversal list for each of the four
   * traversal orders.
   */
  @Test
  void testBST_001_empty_traversal_orders() {
    try {

      List<String> expectedOrder = new ArrayList<String>();

      // Get the actual traversal order lists for each type
      List<String> inOrder = bst.getInOrderTraversal();
      List<String> preOrder = bst.getPreOrderTraversal();
      List<String> postOrder = bst.getPostOrderTraversal();
      List<String> levelOrder = bst.getLevelOrderTraversal();

      // UNCOMMENT IF DEBUGGING THIS TEST
      // System.out.println(" EXPECTED: "+expectedOrder);
      // System.out.println(" In Order: "+inOrder);
      // System.out.println(" Pre Order: "+preOrder);
      // System.out.println(" Post Order: "+postOrder);
      // System.out.println("Level Order: "+levelOrder);

      // check traversal order against expected
      assertEquals(expectedOrder, inOrder);
      assertEquals(expectedOrder, preOrder);
      assertEquals(expectedOrder, postOrder);
      assertEquals(expectedOrder, levelOrder);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 001: " + e.getMessage());
    }

  }

  /**
   * Test that trees with one key,value pair produce a valid traversal lists for each of the four
   * traversal orders.
   */
  @Test
  void testBST_002_check_traversals_after_insert_one() {

    try {
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10);
      bst2.insert(10, "ten"); // inserted node to be tested
      if (bst2.numKeys() != 1)
        fail("added 10, size should be 1, but was " + bst2.numKeys());

      // get traversal orders
      List<Integer> inOrder = bst2.getInOrderTraversal();
      List<Integer> preOrder = bst2.getPreOrderTraversal();
      List<Integer> postOrder = bst2.getPostOrderTraversal();
      List<Integer> levelOrder = bst2.getLevelOrderTraversal();

      // UNCOMMENT IF DEBUGGING THIS TEST
      // System.out.println(" EXPECTED: "+expectedOrder);
      // System.out.println(" In Order: "+inOrder);
      // System.out.println(" Pre Order: "+preOrder);
      // System.out.println(" Post Order: "+postOrder);
      // System.out.println("Level Order: "+levelOrder);

      // check traversal order against expected
      assertEquals(expectedOrder, inOrder);
      assertEquals(expectedOrder, preOrder);
      assertEquals(expectedOrder, postOrder);
      assertEquals(expectedOrder, levelOrder);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 002: " + e.getMessage());
    }

  }


  /**
   * Test that the in-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: 20-10-30 In-Order traversal order: 10-20-30
   */
  @Test
  void testBST_003_check_inOrder_for_balanced_insert_order() {
    // insert 20-10-30 BALANCED
    try {
      bst2.insert(20, "1st key inserted");
      bst2.insert(10, "2nd key inserted");
      bst2.insert(30, "3rd key inserted");

      // expected inOrder 10 20 30
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(10); // L
      expectedOrder.add(20); // V
      expectedOrder.add(30); // R

      // GET IN-ORDER and check
      List<Integer> actualOrder = bst2.getInOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 003: " + e.getMessage());
    }
  }

  /**
   * Test that the pre-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: c-b-d-e-a Pre-Order traversal order: c-b-a-d-e
   */
  @Test
  void testBST_004_check_preOrder_for_balanced_insert_order() {
    try {
      // inserted nodes to test
      bst.insert("c", "1st key inserted");
      bst.insert("b", "2nd key inserted");
      bst.insert("d", "3rd key inserted");
      bst.insert("e", "4th key inserted");
      bst.insert("a", "5th key inserted");

      // list of expected order
      List<String> expectedOrder = new ArrayList<String>();
      expectedOrder.add("c");
      expectedOrder.add("b");
      expectedOrder.add("a");
      expectedOrder.add("d");
      expectedOrder.add("e");

      List<String> actualOrder = bst.getPreOrderTraversal();

      // check preorder traversal against expected
      assertEquals(expectedOrder, actualOrder);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 004: " + e.getMessage());
    }



  }

  /**
   * Test that the post-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: c-b-d-e-a Post-Order traversal order: a-b-e-d-c
   */
  @Test
  void testBST_005_check_postOrder_for_balanced_insert_order() {
    try {
      // insert nodes to test
      bst.insert("c", "1st key inserted");
      bst.insert("b", "2nd key inserted");
      bst.insert("d", "3rd key inserted");
      bst.insert("e", "4th key inserted");
      bst.insert("a", "5th key inserted");

      // list of expected order
      List<String> expectedOrder = new ArrayList<String>();
      expectedOrder.add("a");
      expectedOrder.add("b");
      expectedOrder.add("e");
      expectedOrder.add("d");
      expectedOrder.add("c");

      List<String> actualOrder = bst.getPostOrderTraversal();

      // check postorder traversal against expected
      assertEquals(expectedOrder, actualOrder);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 005: " + e.getMessage());
    }


  }

  /**
   * Test that the level-order traversal order is correct if the items are entered in a way that
   * creates a balanced BST
   * 
   * Insert order: c-b-d-e-a Level-Order traversal order: c-b-d-e-a
   */
  @Test
  void testBST_006_check_levelOrder_for_balanced_insert_order() {
    try {
      // inserted nodes to be tested
      bst.insert("c", "1st key inserted");
      bst.insert("b", "2nd key inserted");
      bst.insert("d", "3rd key inserted");
      bst.insert("e", "4th key inserted");
      bst.insert("a", "5th key inserted");

      // list of expected order
      List<String> expectedOrder = new ArrayList<String>();
      expectedOrder.add("c");
      expectedOrder.add("b");
      expectedOrder.add("d");
      expectedOrder.add("a");
      expectedOrder.add("e");

      List<String> actualOrder = bst.getLevelOrderTraversal();

      // check level order traversal against expected
      assertEquals(expectedOrder, actualOrder);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 006: " + e.getMessage());
    }

  }

  /**
   * Test that the in-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 12-9-7-14-8-3-2-10 In-Order traversal order: 2-3-7-8-9-10-12-14
   */
  @Test
  void testBST_007_check_inOrder_for_not_balanced_insert_order() {

    try {
      // insert nodes to be tested
      bst2.insert(12, "1st key inserted");
      bst2.insert(9, "2nd key inserted");
      bst2.insert(7, "3rd key inserted");
      bst2.insert(14, "4th key inserted");
      bst2.insert(8, "5th key inserted");
      bst2.insert(3, "6th key inserted");
      bst2.insert(2, "7th key inserted");
      bst2.insert(10, "8th key inserted");

      // list of expected order
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(2);
      expectedOrder.add(3);
      expectedOrder.add(7);
      expectedOrder.add(8);
      expectedOrder.add(9);
      expectedOrder.add(10);
      expectedOrder.add(12);
      expectedOrder.add(14);

      // GET IN-ORDER and check
      List<Integer> actualOrder = bst2.getInOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 007: " + e.getMessage());
    }


  }

  /**
   * Test that the pre-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 12-9-7-14-8-3-2-10 Pre-Order traversal order: 12-9-7-3-2-8-10-14
   */
  @Test
  void testBST_008_check_preOrder_for_not_balanced_insert_order() {

    try {
      // insert nodes to test
      bst2.insert(12, "1st key inserted");
      bst2.insert(9, "2nd key inserted");
      bst2.insert(7, "3rd key inserted");
      bst2.insert(14, "4th key inserted");
      bst2.insert(8, "5th key inserted");
      bst2.insert(3, "6th key inserted");
      bst2.insert(2, "7th key inserted");
      bst2.insert(10, "8th key inserted");

      // expected preOrder 12 9 7 3 2 8 10 14
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(12);
      expectedOrder.add(9);
      expectedOrder.add(7);
      expectedOrder.add(3);
      expectedOrder.add(2);
      expectedOrder.add(8);
      expectedOrder.add(10);
      expectedOrder.add(14);

      // GET IN-ORDER and check
      List<Integer> actualOrder = bst2.getPreOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 008: " + e.getMessage());
    }

  }

  /**
   * Test that the post-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 12-9-7-14-8-3-2-10 Post-Order traversal order: 30-20-10
   */
  @Test
  void testBST_009_check_postOrder_for_not_balanced_insert_order() {

    try {
      // inserted nodes to test
      bst2.insert(12, "1st key inserted");
      bst2.insert(9, "2nd key inserted");
      bst2.insert(7, "3rd key inserted");
      bst2.insert(14, "4th key inserted");
      bst2.insert(8, "5th key inserted");
      bst2.insert(3, "6th key inserted");
      bst2.insert(2, "7th key inserted");
      bst2.insert(10, "8th key inserted");

      // list of expected order
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(2);
      expectedOrder.add(3);
      expectedOrder.add(8);
      expectedOrder.add(7);
      expectedOrder.add(10);
      expectedOrder.add(9);
      expectedOrder.add(14);
      expectedOrder.add(12);

      List<Integer> actualOrder = bst2.getPostOrderTraversal();

      // check postorder traversal against expected
      assertEquals(expectedOrder, actualOrder);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 009: " + e.getMessage());
    }

  }

  /**
   * Test that the level-order traversal order is correct if the items are entered in a way that
   * creates an un-balanced BST
   * 
   * Insert order: 12-9-7-14-8-3-2-10 Level-Order traversal order: 12-9-14-7-10-3-8-2
   */
  @Test
  void testBST_010_check_levelOrder_for_not_balanced_insert_order() {

    try {
      // test inserts
      bst2.insert(12, "1st key inserted");
      bst2.insert(9, "2nd key inserted");
      bst2.insert(7, "3rd key inserted");
      bst2.insert(14, "4th key inserted");
      bst2.insert(8, "5th key inserted");
      bst2.insert(3, "6th key inserted");
      bst2.insert(2, "7th key inserted");
      bst2.insert(10, "8th key inserted");

      // expected levelOrder = 12 9 14 7 10 3 8 2
      List<Integer> expectedOrder = new ArrayList<Integer>();
      expectedOrder.add(12);
      expectedOrder.add(9);
      expectedOrder.add(14);
      expectedOrder.add(7);
      expectedOrder.add(10);
      expectedOrder.add(3);
      expectedOrder.add(8);
      expectedOrder.add(2);

      // GET IN-ORDER and check
      List<Integer> actualOrder = bst2.getLevelOrderTraversal();
      assertEquals(expectedOrder, actualOrder);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 010: " + e.getMessage());
    }


  }

  /**
   * Tests to see if root with null left child is properly removed.
   */

  @Test
  void testBST_011_remove_root_with_one_child() {

    try {
      // test inserts
      bst2.insert(5, null);
      bst2.insert(9, null);
      bst2.insert(7, null);
      bst2.insert(4, null);
      bst2.insert(8, null);
      bst2.insert(3, null);
      bst2.insert(2, null);
      bst2.insert(6, null);
      bst2.insert(1, null);

      // test removals
      bst2.remove(bst2.root.key);
      bst2.remove(bst2.root.key);
      bst2.remove(bst2.root.key);
      bst2.remove(bst2.root.key);
      bst2.remove(bst2.root.key);

      List<Integer> expected = new ArrayList<Integer>();
      // expected level order traversal
      expected.add(9);
      expected.add(7);
      expected.add(6);
      expected.add(8);

      List<Integer> list = bst2.getLevelOrderTraversal();

      assertEquals(expected, list);
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 011: " + e.getMessage());
    }
  }

  /**
   * Tests to see if inserting 5 and then removing 1, does contains(), getHeight(), and numKeys() in
   * the BST hold true as expected.
   */
  @Test
  void testBST_012_insert5_remove1_contains() {
    try {
      // inserted nodes to test
      bst2.insert(20, null);
      bst2.insert(30, null);
      bst2.insert(10, null);
      bst2.insert(5, null);
      bst2.insert(3, null);

      // remove node to test
      bst2.remove(20);

      // 20 should no longer be in BST
      if (bst2.contains(20)) {
        fail("should not have 20");
      } else if (bst2.getHeight() != 3) { // height of tree should be 3
        fail("height should be 3");
      } else if (bst2.numKeys() != 4) { // numKeys should be 4
        fail("there should be 4 keys in bst");
      }

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 012: " + e.getMessage());
    }
  }

  /**
   * Tests to see if inserting many nodes still can get a key inserted back.
   */
  @Test
  void testBST_013_insert100_remove2_get_from_98() {
    try {
      // insert from middle to end of 100 to test
      for (int i = 50; i < 100; i++) {
        bst2.insert(i, "Test");
      }
      // insert from beginning to middle of 100 to test
      for (int i = 0; i < 49; i++) {
        bst2.insert(i, "Test2");
      }

      bst2.remove(98);
      bst2.remove(97);

      // check contains
      if (bst2.contains(98)) {
        fail("BST should no longer contain key of 98");
      } else if (bst2.contains(97)) {
        fail("BST should no longer contain key of 97");
      }

      // check gets after many inserts
      if (!bst2.get(31).equals("Test2")) {
        fail("BST should get a value of 'Test2' from key with 31 but instead returned "
            + bst2.get(31));
      }

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 013: " + e.getMessage());
    }
  }

  /**
   * Test various remove cases: remove one right and left child, remove leaf, remove node with two
   * children.
   */
  @Test
  void testBST_014_remove_leaf_remove_one_child_remove_two_children() {
    try {
      // inserted nodes to test
      bst2.insert(15, null);
      bst2.insert(11, null);
      bst2.insert(6, null);
      bst2.insert(13, null);
      bst2.insert(12, null);
      bst2.insert(21, null);
      bst2.insert(17, null);
      bst2.insert(18, null);
      bst2.insert(7, null);
      bst2.insert(8, null);

      // remove one right child
      bst2.remove(6);

      // new left key should be 7
      if (!bst2.getKeyOfLeftChildOf(11).equals(7)) {
        fail("6 should have been removed from BST");
      }

      // remove one left child
      bst2.remove(21);

      if (!bst2.getKeyOfRightChildOf(15).equals(17)) {
        fail("21 should have been removed from BST");
      }

      // remove leaf
      bst2.remove(12);

      if (bst2.getKeyOfLeftChildOf(13) != null) {
        fail("12 should have been removed from BST");
      }

      // remove leaf with two children
      bst2.remove(11);

      if (!bst2.getKeyOfLeftChildOf(15).equals(8)) {
        fail("11 should have been removed from BST");
      }

      // expected order traversal
      ArrayList<Integer> list = new ArrayList<Integer>();
      list.add(15);
      list.add(8);
      list.add(17);
      list.add(7);
      list.add(13);
      list.add(18);

      List<Integer> expected = bst2.getLevelOrderTraversal();

      // check against expected level order traversal
      assertEquals(expected, list);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 014: " + e.getMessage());
    }
  }

  /**
   * Test if getting the in-order predecessor is correct.
   */
  @Test
  void testBST_015_test_in_order_predecessor() {
    try {
      // inserted nodes to test
      bst2.insert(15, null);
      bst2.insert(11, null);
      bst2.insert(6, null);
      bst2.insert(13, null);
      bst2.insert(12, null);
      bst2.insert(21, null);
      bst2.insert(17, null);
      bst2.insert(18, null);
      bst2.insert(7, null);
      bst2.insert(8, null);

      // remove root, inorder predecessor should be 13
      bst2.remove(15);

      if (!bst2.getKeyAtRoot().equals(13)) {
        fail("New root should be 13, inorder predecessor retrieval unsucessful");
      }


    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception 015: " + e.getMessage());
    }
  }



}
