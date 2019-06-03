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

/**
 * Class defining a BST that automatically maintains its balance using AVL rotations. Instance of
 * nodes in the methods in this class are called node to differentiate from current in BST.java.
 * 
 * @author AlexDo
 *
 * @param <K> key of node
 * @param <V> value of node
 */
public class AVL<K extends Comparable<K>, V> extends BST<K, V> {

  /**
   * No argument constructor that initializes all protected fields in BST.java, root is null,
   * numKeys is 0.
   */
  public AVL() {
    super();
  }

  /**
   * Private rebalance helper method for insert and remove in the AVL tree. 4 possible cases: 1. if
   * node is right heavy, left rotation 2. if node is right heavy and is slightly left heavy,
   * right-left rotation 3. if node is left heavy, right rotation 4. if node is left heavy and is
   * slightly right heavy, left-right rotation
   * 
   * @param node
   * @return
   */

  private BSTNode<K, V> rebalance(BSTNode<K, V> node) {
    // left subtree heavy
    if (node.balanceFactor == 2) {
      // left node right heavy
      if (node.left.balanceFactor <= 0) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
      } else { // left left heavy
        return rightRotation(node);
      }
    } else if (node.balanceFactor == -2) { // right subtree heavy
      // right node left heavy
      if (node.right.balanceFactor >= 0) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
      } else { // right right heavy
        return leftRotation(node);
      }
    }
    return node;
  }

  /**
   * Left rotates the rooted node with its right child.
   * 
   * @param node
   * @return the new rooted node
   */
  private BSTNode<K, V> leftRotation(BSTNode<K, V> node) {
    BSTNode<K, V> newParent = node.right; // new parent node to swap
    node.right = newParent.left; // should be null
    newParent.left = node; // swaps new parent and node to left child of new parent
    update(node); // update the changed node's height
    update(newParent); // update the new parent's height

    return newParent;
  }

  /**
   * Right rotates the rooted node with its left child.
   * 
   * @param node
   * @return the new rooted node
   */
  private BSTNode<K, V> rightRotation(BSTNode<K, V> node) {
    BSTNode<K, V> newParent = node.left; // new parent node to swap
    node.left = newParent.right; // should be null
    newParent.right = node; // swaps new parent and node to right child of new parent
    update(node); // update the changed node's height
    update(newParent); // update the new parent's height

    return newParent;
  }

  /**
   * Overrides insert method of BST to account for auto rebalancing of AVL tree.
   * 
   * @param key - key to be inserted, value - value of key
   * @throws IllegalNullKeyException, DuplicateKeyException
   */

  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    this.root = insertAVL(this.root, key, value);
    this.numKeys++; // increment keys in AVL tree
  }

  /**
   * Recursive insert helper method for an AVL tree, updates height, balance factor and rebalances
   * the node inserted.
   * 
   * @param node
   * @param key
   * @param value
   * @return the node inserted after rebalancing and updating
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   */
  private BSTNode<K, V> insertAVL(BSTNode<K, V> node, K key, V value)
      throws IllegalNullKeyException, DuplicateKeyException {
    // key can't be null
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    // if no nodes currently in AVL tree
    if (node == null) {
      return new BSTNode<K, V>(key, value, null, null);
    }

    // traverse left if key is less than node
    if (key.compareTo(node.key) < 0) {
      node.left = insertAVL(node.left, key, value);
    } else if (key.compareTo(node.key) > 0) { // traverse right if key is greater than node
      node.right = insertAVL(node.right, key, value);
    } else { // key already exists
      throw new DuplicateKeyException();
    }

    update(node);
    return rebalance(node);
  }

  /**
   * Overrides remove method of BST to account for auto rebalancing of AVL tree.
   * 
   * @param key - key to be removed
   * @throws IllegalNullKeyException, KeyNotFoundException
   * @return true if key has been properly removed from tree
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    removeAVL(this.root, key); // remove the node of specified key
    // if tree no longer contains key, then it has been removed properly
    if (!this.contains(key)) {
      this.numKeys--; // decrement keys in tree
      return true;
    } else {
      return false;
    }
  }

  /**
   * Recursive remove helper method for an AVL tree, updates height, balance factor and rebalances
   * the position of the node removed.
   * 
   * @param node
   * @param key
   * @return the node removed after updating and rebalancing node
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   */
  private BSTNode<K, V> removeAVL(BSTNode<K, V> node, K key)
      throws IllegalNullKeyException, KeyNotFoundException {
    // key is null
    if (key == null) {
      throw new IllegalNullKeyException();
    } else if (!this.contains(key)) { // key isn't in tree
      throw new KeyNotFoundException();
    }

    // if key is less than node, traverse left
    if (key.compareTo(node.key) < 0) {
      node.left = removeAVL(node.left, key);
    } else if (key.compareTo(node.key) > 0) { // if key is greater than node, traverse right
      node.right = removeAVL(node.right, key);
    } else {
      // if deleting root with one child, non-null root child becomes the new root
      if (node == this.root) {
        if (node.left == null) {
          this.root = node.right;
          return node;
        } else if (node.right == null) {
          this.root = node.left;
          return node;
        }
      }
      // node has one child
      if (node.left == null) {
        return node.right;
      } else if (node.right == null) {
        return node.left;
      } else { // node has two children
        // swap key and value of node to be deleted and the inorder predecessor
        K temp = greatestLeft(node.left); // greatestLeft() is accessible to AVL class
        node.value = get(temp);
        node.key = temp;
        // remove swapped node
        node.left = removeAVL(node.left, node.key);
      }
    }

    update(node);
    return rebalance(node);

  }


}
