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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Assert;
import static org.junit.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


/**
 * This class tests correct order and structure of AVL tree after inserting and removing various
 * nodes; tests for proper rebalancing and rotations of tree.
 * 
 * 
 * IMPORTANT NOTE: SEVERAL BSTTest METHODS ARE EXPECTED TO FAIL FOR A SELF-BALANCING AVL TREE. THUS
 * THESE METHODS ARE OVERRIDEN BELOW: 1. testBST_008 2. testBST_009 3. testBST_010 4. testBST_011 5.
 * testBST_014
 * 
 * 
 * @author AlexDo
 *
 */

// @SuppressWarnings("rawtypes")
public class AVLTest extends BSTTest {

  AVL<String, String> avl;
  AVL<Integer, String> avl2;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = bst = avl = createInstance();
    dataStructureInstance2 = bst2 = avl2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    avl = null;
    avl2 = null;
  }


  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADTTest#createInstance()
   */
  @Override
  protected AVL<String, String> createInstance() {
    return new AVL<String, String>();
  }


  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADTTest#createInstance2()
   */
  @Override
  protected AVL<Integer, String> createInstance2() {
    return new AVL<Integer, String>();
  }

  /**
   * Insert three values in sorted order and then check the root, left, and right keys to see if
   * rebalancing occurred.
   */
  @Test
  void testAVL_001_insert_sorted_order_simple() {
    try {
      // insert nodes to test
      avl2.insert(10, "10");
      if (!avl2.getKeyAtRoot().equals(10)) // root should be 10
        fail("avl insert at root does not work");

      avl2.insert(20, "20");
      if (!avl2.getKeyOfRightChildOf(10).equals(20))
        fail("avl insert to right child of root does not work");

      avl2.insert(30, "30");
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 001: " + e.getMessage());
    }
  }

  /**
   * Insert three values in reverse sorted order and then check the root, left, and right keys to
   * see if rebalancing occurred in the other direction.
   */
  @Test
  void testAVL_002_insert_reversed_sorted_order_simple() {
    try {
      // insert nodes to test
      avl2.insert(30, "30");
      if (!avl2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      avl2.insert(20, "20");
      if (!avl2.getKeyOfLeftChildOf(30).equals(20))
        fail("avl insert to right child of root does not work");

      avl2.insert(10, "10");
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 002: " + e.getMessage());
    }

  }

  /**
   * Insert three values so that a right-left rotation is needed to fix the balance.
   * 
   * Example: 10-30-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testAVL_003_insert_smallest_largest_middle_order_simple() {
    try {
      // insert nodes to test
      avl2.insert(10, "10");
      if (!avl2.getKeyAtRoot().equals(10))
        fail("avl insert at root does not work");

      avl2.insert(30, "30");
      if (!avl2.getKeyOfRightChildOf(10).equals(30))
        fail("avl insert to right child of root does not work");

      avl2.insert(20, "20");
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // If rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 003: " + e.getMessage());
    }
  }

  /**
   * Insert three values so that a left-right rotation is needed to fix the balance.
   * 
   * Example: 30-10-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testAVL_004_insert_largest_smallest_middle_order_simple() {
    try {
      // insert nodes to test
      avl2.insert(30, "30");
      if (!avl2.getKeyAtRoot().equals(30))
        fail("avl insert at root does not work");

      avl2.insert(10, "10");
      if (!avl2.getKeyOfLeftChildOf(30).equals(10))
        fail("avl insert to right child of root does not work");

      avl2.insert(20, "20");
      Integer k = avl2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(avl2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(avl2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(avl2.getKeyOfRightChildOf(20), new Integer(30));
    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 004: " + e.getMessage());
    }

  }

  /**
   * Tests if insertion of many keys in order properly rotates and rebalances. Height of the AVL
   * tree should also reflect proper rotations.
   * 
   */
  @Test
  void testAVL_005_insert_large_right_heavy_tree() {
    try {
      // insert nodes to test
      avl.insert("a", null);
      avl.insert("b", null);
      avl.insert("c", null);
      avl.insert("d", null);
      avl.insert("e", null);
      avl.insert("f", null);
      avl.insert("g", null);

      if (!avl.getKeyAtRoot().equals("d")) { // root should be null after all insertions
        fail("avl root should be 'd' but is " + avl.getKeyAtRoot());
      } else if (avl.getHeight() != 3) {
        fail("avl tree height should be 3 but is " + avl.getHeight());
      }

      ArrayList<String> list = new ArrayList<String>(); // list to check against
      // expected level order
      list.add("d");
      list.add("b");
      list.add("f");
      list.add("a");
      list.add("c");
      list.add("e");
      list.add("g");

      List<String> check = avl.getLevelOrderTraversal();

      // check level order traversal against expected
      assertEquals(list, check);


    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 005: " + e.getMessage());
    }


  }

  /**
   * Tests if insertion of many keys in reverse-order properly rotates and rebalances. Height of the
   * AVL tree should also reflect proper rotations.
   * 
   */
  @Test
  void testAVL_006_insert_large_left_heavy_tree() {
    try {
      // insert nodes to test
      avl.insert("g", null);
      avl.insert("f", null);
      avl.insert("e", null);
      avl.insert("d", null);
      avl.insert("c", null);
      avl.insert("b", null);
      avl.insert("a", null);

      if (!avl.getKeyAtRoot().equals("d")) { // root should be null after all insertions
        fail("avl root should be 'd' but is " + avl.getKeyAtRoot());
      } else if (avl.getHeight() != 3) {
        fail("avl tree height should be 3 but is " + avl.getHeight());
      }

      ArrayList<String> list = new ArrayList<String>(); // list to check against
      // expected level order
      list.add("d");
      list.add("b");
      list.add("f");
      list.add("a");
      list.add("c");
      list.add("e");
      list.add("g");

      List<String> check = avl.getLevelOrderTraversal();

      // check level order traversal against expected
      assertEquals(list, check);


    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 006: " + e.getMessage());
    }


  }

  /**
   * Tests to see if inserting many keys and getting from those keys still work.
   */

  @Test
  void testAVL_007_insert100_get_from_100() {
    try {
      // insert middle to end of 100 to test
      for (int i = 50; i < 100; i++) {
        avl2.insert(i, "test");
      }
      // insert beginning to middle of 100 to test
      for (int i = 0; i < 50; i++) {
        avl2.insert(i, "test0");
      }


      if (!avl2.get(21).equals("test0")) { // should get node of key 21
        fail("Unable to get an expected inserted node");
      } else if (avl2.contains(400)) { // should not get node of key 400
        fail("Node of 400 should not be in tree");
      }


    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 007: " + e.getMessage());
    }

  }

  /**
   * Tests to see if AVL tree after inserting 9 keys, removing 2 rebalances correctly.
   */
  @Test
  void testAVL_008_insert9_remove2_checkbalance() {
    try {
      // inserted nodes to test
      avl2.insert(5, null);
      avl2.insert(9, null);
      avl2.insert(7, null);
      avl2.insert(4, null);
      avl2.insert(8, null);
      avl2.insert(3, null);
      avl2.insert(2, null);
      avl2.insert(6, null);
      avl2.insert(1, null);

      // root should be 7 after all nodes are inserted
      if (!avl2.getKeyAtRoot().equals(7)) {
        fail("root should be 7 but is " + avl2.getKeyAtRoot());
      }

      // remove nodes to test rebalancing
      avl2.remove(2);
      avl2.remove(4);

      // expected list of inorder traversal
      ArrayList<Integer> list = new ArrayList<Integer>();
      list.add(7);
      list.add(3);
      list.add(9);
      list.add(1);
      list.add(5);
      list.add(8);
      list.add(6);

      List<Integer> check = avl2.getLevelOrderTraversal();

      // check level order traversal against expected
      assertEquals(list, check);

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 008: " + e.getMessage());
    }

  }

  /**
   * Test various exceptions DuplicateKeyException, IllegalNullKeyException, KeyNotFoundException by
   * inserting and removing nodes.
   */
  @Test
  void testAVL_009_test_exceptions() {
    try {
      // insert nodes to test
      avl.insert("a", null);
      avl.insert("b", null);
      avl.insert("c", null);
      avl.insert("a", null); // duplicate key
      fail("DuplicateKeyException should have been thrown");
    } catch (DuplicateKeyException e) {

    } catch (IllegalNullKeyException e) {
      fail("IllegalNullKeyException should not have been thrown");
    }

    try {
      avl.insert(null, null); // null key
      fail("IllegalNullKeyException should have been thrown");
    } catch (IllegalNullKeyException e) {

    } catch (DuplicateKeyException e) {

    }

    try {
      avl.remove("z"); // z should not exist
      fail("KeyNotFoundException should have been thrown");
    } catch (KeyNotFoundException e) {

    } catch (IllegalNullKeyException e) {
      fail("IllegalNullKeyException should have been thrown");
    }

  }

  /**
   * THESE TESTS BELOW ARE EXPECTED TO FAIL DUE TO THE SELF-BALANCING OF AVL TREE. THEY ARE
   * OVERRIDEN IN ORDER TO AVOID FAILURES FOR TESTS ON AN AVL TREE. PLEASE NOTE THAT THESE TESTS DO
   * NOT NECESSARILY REFLECT THE RESULTS FOR A REGULAR BST TREE, USE BSTTest.java TO TEST REGULAR
   * BST TREE.
   */
  @Test
  void testBST_008_check_preOrder_for_not_balanced_insert_order() {}

  @Test
  void testBST_009_check_postOrder_for_not_balanced_insert_order() {}

  @Test
  void testBST_010_check_levelOrder_for_not_balanced_insert_order() {}

  @Test
  void testBST_011_remove_root_with_one_child() {}

  @Test
  void testBST_014_remove_leaf_remove_one_child_remove_two_children() {}


}
