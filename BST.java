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
import java.util.ArrayList; // allowed for creating traversal lists
import java.util.LinkedList;
import java.util.List; // required for returning List<K>
import java.util.Queue;

/**
 * Class defining a Binary Search Tree containing nodes of Key, Value pairs.
 * 
 * @author AlexDo
 *
 * @param <K> key of node
 * @param <V> value of node
 */
public class BST<K extends Comparable<K>, V> implements BSTADT<K, V> {

  // Tip: Use protected fields so that they may be inherited by AVL
  protected BSTNode<K, V> root; // node instance for the root of BST
  protected int numKeys; // number of keys in BST

  // Must have a public, default no-arg constructor
  /**
   * Public, default no-argument constructor initializing the root of the BST to null and setting
   * number of keys in tree to 0.
   */
  public BST() {
    this.root = null;
    this.numKeys = 0;
  }

  /**
   * Updates the height and balance factor of each node as it is inserted/removed from a BST.
   * Especially used to rebalance the node in an AVL tree.
   * 
   * @param node
   */
  protected void update(BSTNode<K, V> node) {
    // updates the height of each child subtree
    int leftHeight = (node.left == null) ? -1 : node.left.height;
    int rightHeight = (node.right == null) ? -1 : node.right.height;

    // height is the greatest between left height and right height
    node.height = 1 + Math.max(leftHeight, rightHeight);

    // updates balance factor given new heights of each child subtree
    node.balanceFactor = leftHeight - rightHeight;

  }

  /**
   * Returns the keys of the data structure in pre-order traversal order. In the case of binary
   * search trees, the order is: V L R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in pre-order
   */
  @Override
  public List<K> getPreOrderTraversal() {
    ArrayList<K> list = new ArrayList<K>(); // list to return preorder traversal
    xOrderHelper(list, this.root, 1); // 1 for preorder
    return list;
  }

  /**
   * Returns the keys of the data structure in post-order traversal order. In the case of binary
   * search trees, the order is: L R V
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in post-order
   */
  @Override
  public List<K> getPostOrderTraversal() {
    ArrayList<K> list = new ArrayList<K>(); // list to return postorder traversal
    xOrderHelper(list, this.root, 3); // 3 for postorder
    return list;
  }

  /**
   * Returns the keys of the data structure in level-order traversal order.
   * 
   * The root is first in the list, then the keys found in the next level down, and so on.
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in level-order
   */
  @Override
  public List<K> getLevelOrderTraversal() {
    ArrayList<K> list = new ArrayList<K>(); // list to return levelorder traversal
    if (this.root == null) {
      return list; // empty list
    }

    // queue implemented level order traversal
    Queue<BSTNode<K, V>> queue = new LinkedList<BSTNode<K, V>>();
    queue.add(this.root); // add the root to start

    while (true) {
      int keyCount = queue.size(); // initial size of the level to add
      if (keyCount == 0)
        break;

      while (keyCount > 0) {
        BSTNode<K, V> current = queue.poll(); // add level nodes into queue
        list.add(current.key); // add nodes into list
        // add other level nodes into queue while children aren't null
        if (current.left != null) {
          queue.add(current.left);
        }
        if (current.right != null) {
          queue.add(current.right);
        }
        keyCount--;
      }
    }

    // list contains all the nodes of BST
    return list;

  }

  /**
   * Returns the keys of the data structure in sorted order. In the case of binary search trees, the
   * visit order is: L V R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in-order
   */
  @Override
  public List<K> getInOrderTraversal() {
    ArrayList<K> list = new ArrayList<K>(); // list to return inorder traversal
    xOrderHelper(list, this.root, 2); // 2 for inorder
    return list;
  }

  /**
   * Recursive traversal order helper depending on traversal type. 1 - preorder, 2 - inorder, 3 -
   * postorder.
   * 
   * @param list - updates reference to list object called from respective traversal method
   * @param current
   * @param x
   */
  private void xOrderHelper(List<K> list, BSTNode<K, V> current, int x) {
    if (current == null) {
      return;
    }

    if (x == 1) { // preorder traversal
      list.add(current.key);
      xOrderHelper(list, current.left, 1);
      xOrderHelper(list, current.right, 1);
    } else if (x == 2) { // inorder traversal
      xOrderHelper(list, current.left, 2);
      list.add(current.key);
      xOrderHelper(list, current.right, 2);
    } else if (x == 3) { // postorder traversal
      xOrderHelper(list, current.left, 3);
      xOrderHelper(list, current.right, 3);
      list.add(current.key);
    }

  }

  /**
   * Add the key,value pair to the data structure and increase the number of keys. If key is null,
   * throw IllegalNullKeyException; If key is already in data structure, throw
   * DuplicateKeyException();
   * 
   * @param key - key to be inserted, value - value of key to be inserted
   * @throws IllegalNullKeyException, DupliacteKeyException
   * 
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    this.root = insertBST(this.root, key, value);
    this.numKeys++; // increment numKeys in BST

  }

  /**
   * Recursive insert helper to insert node into BST. Updates the height and balance factor of the
   * node to be inserted,
   * 
   * @param current
   * @param key
   * @param value
   * @return the node to be inserted
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   */
  private BSTNode<K, V> insertBST(BSTNode<K, V> current, K key, V value)
      throws IllegalNullKeyException, DuplicateKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    // if tree is currently empty
    if (current == null) {
      return new BSTNode<K, V>(key, value, null, null);
    }

    // if key is less than current's key, traverse left subtree
    if (key.compareTo(current.key) < 0) {
      current.left = insertBST(current.left, key, value);
    } else if (key.compareTo(current.key) > 0) { // key is greater than current's key
      current.right = insertBST(current.right, key, value);
    } else { // key already exists
      throw new DuplicateKeyException();
    }

    update(current);
    return current;
  }


  /**
   * If key is found, remove the key,value pair from the data structure and decrease num keys. If
   * key is null, throw IllegalNullKeyException If key is not found, throw KeyNotFoundException().
   * 
   * @param key - key to be removed
   * @throws IllegalNullKeyException, KeyNotFoundException
   * @return true if key is found and deleted
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    removeBST(this.root, key); // remove the BST
    // removal is successful if the BST no longer contains the key
    if (!this.contains(key)) {
      // decrement numkeys of BST
      this.numKeys--;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Recursive remove helper to remove a node from the BST, updates the height of the place the node
   * was removed and updates the balance factor.
   * 
   * @param current
   * @param key
   * @return the node to be removed
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   */

  private BSTNode<K, V> removeBST(BSTNode<K, V> current, K key)
      throws IllegalNullKeyException, KeyNotFoundException {
    // key is null
    if (key == null) {
      throw new IllegalNullKeyException();
    } else if (!this.contains(key)) {
      throw new KeyNotFoundException();
    }

    // key is less than current node, traverse left
    if (key.compareTo(current.key) < 0) {
      current.left = removeBST(current.left, key);
    } else if (key.compareTo(current.key) > 0) { // key is greater than current node, traverse right
      current.right = removeBST(current.right, key);
    } else { // key is found in BST
      // if deleting root with one child, non-null root child becomes the new root
      if (current == this.root) {
        if (current.left == null) {
          this.root = current.right;
          return current;
        } else if (current.right == null) {
          this.root = current.left;
          return current;
        }
      }
      // node has one child
      if (current.left == null) {
        return current.right;
      } else if (current.right == null) {
        return current.left;
      } else { // node has two children
        // swap key and value of node to be deleted and inorder predecessor
        K temp = greatestLeft(current.left);
        current.value = get(temp);
        current.key = temp;
        // remove swapped node
        current.left = removeBST(current.left, current.key);
      }
    }

    update(current);
    return current;
  }

  /**
   * Protected accessible recursive helper method to find the inorder predecessor of the current
   * node for BST and AVL.
   * 
   * @param current
   * @return
   */
  protected K greatestLeft(BSTNode<K, V> current) {
    // key is found
    if (current.right == null) {
      return current.key;
    } else {
      // traverse down left subtree moving right
      return greatestLeft(current.right);
    }
  }


  /**
   * Returns the value associated with the specified key Does not remove key or decrease number of
   * keys If key is null, throw IllegalNullKeyException If key is not found, throw
   * KeyNotFoundException().
   * 
   * @params key - key to get from BST
   * @throws IllegalNullKeyException, KeyNotFoundException
   * @return V - the value of the key found
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    return getHelper(root, key).value;
  }

  /**
   * Recursive get helper method to find and return the specified node in the BST.
   * 
   * @param current
   * @param key
   * @return the node to get
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   */
  private BSTNode<K, V> getHelper(BSTNode<K, V> current, K key)
      throws IllegalNullKeyException, KeyNotFoundException {
    // key can't be null
    if (key == null) {
      throw new IllegalNullKeyException();
    } else if (current.left == null && current.right == null && !current.key.equals(key)) {
      // if traversal has reached a leaf and the leaf isn't equal to the specified key
      throw new KeyNotFoundException();
    }

    // traverse BST, if key is found
    if (current.key.equals(key)) {
      return current;
    } else if (key.compareTo(current.key) > 0) { // if key is greater, traverse right
      current = getHelper(current.right, key);
    } else { // if key is less, traverse left
      current = getHelper(current.left, key);
    }

    return current;
  }

  /**
   * Returns true if the key is in the data structure If key is null, throw IllegalNullKeyException
   * Returns false if key is not null and is not present
   * 
   * @param key - key to check in BST
   * @throws IllegalNullKeyException
   * @return true if key is found in the BST
   */
  @Override
  public boolean contains(K key) throws IllegalNullKeyException {
    // checks if key is no longer in the BST
    if (containsHelper(this.root, key) != null) {
      return true;
    } else {
      return false;
    }

  }

  /**
   * Recursive contains helper
   * 
   * @param current
   * @param key
   * @return
   * @throws IllegalNullKeyException
   */
  private BSTNode<K, V> containsHelper(BSTNode<K, V> current, K key)
      throws IllegalNullKeyException {
    // if tree is empty
    if (current == null) {
      return null;
    }
    // key can't be null
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    // found key
    if (current.key.equals(key)) {
      return current;
    }

    // recurse down right or left subtree depending on key comparison to current
    if (key.compareTo(current.key) < 0) {
      return containsHelper(current.left, key);
    } else {
      return containsHelper(current.right, key);
    }
  }

  /**
   * Returns the number of key,value pairs in the data structure.
   * 
   * @return the number of keys in BST
   */
  @Override
  public int numKeys() {
    return this.numKeys;
  }

  /**
   * Returns the key that is in the root node of this BST. If root is null, returns null.
   * 
   * @return key found at root node, or null
   */
  @Override
  public K getKeyAtRoot() {
    return this.root.key;
  }

  /**
   * Tries to find a node with a key that matches the specified key. If a matching node is found, it
   * returns the returns the key that is in the left child. If the left child of the found node is
   * null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the left child of the found key
   * 
   * @throws IllegalNullKeyException if key argument is null
   * @throws KeyNotFoundException if key is not found in this BST
   */
  @Override
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // no null keys
    if (key == null) {
      throw new IllegalNullKeyException();
    } else if (!this.contains(key)) { // key doesn't exist
      throw new KeyNotFoundException();
    } else { // retrieve node
      BSTNode<K, V> node = getHelper(this.root, key);
      // left child of node found is null
      if (node.left == null) {
        return null;
      } else {
        return node.left.key;
      }

    }

  }

  /**
   * Tries to find a node with a key that matches the specified key. If a matching node is found, it
   * returns the returns the key that is in the right child. If the right child of the found node is
   * null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the right child of the found key
   * 
   * @throws IllegalNullKeyException if key is null
   * @throws KeyNotFoundException if key is not found in this BST
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // no null keys
    if (key == null) {
      throw new IllegalNullKeyException();
    } else if (!this.contains(key)) { // key doesn't exist
      throw new KeyNotFoundException();
    } else { // retrieve node
      BSTNode<K, V> node = getHelper(this.root, key);
      // right child of node found is null
      if (node.right == null) {
        return null;
      } else {
        return node.right.key;
      }
    }
  }

  /**
   * Returns the height of this BST. H is defined as the number of levels in the tree.
   * 
   * If root is null, return 0 If root is a leaf, return 1 Else return 1 + max( height(root.left),
   * height(root.right) )
   * 
   * Examples: A BST with no keys, has a height of zero (0). A BST with one key, has a height of one
   * (1). A BST with two keys, has a height of two (2). A BST with three keys, can be balanced with
   * a height of two(2) or it may be linear with a height of three (3) ... and so on for tree with
   * other heights
   * 
   * @return the number of levels that contain keys in this BINARY SEARCH TREE
   */
  @Override
  public int getHeight() {
    return heightHelper(this.root);
  }

  /**
   * Recursive height helper method
   * 
   * @param current
   * @return the height of the BST
   */
  private int heightHelper(BSTNode<K, V> current) {
    // tree is empty, or no more nodes in subtree
    if (current == null) {
      return 0;
    }

    // calculate the height of each subtree from the root
    int leftHeight = heightHelper(current.left);
    int rightHeight = heightHelper(current.right);

    // height is the greater of the subtrees
    if (leftHeight > rightHeight) {
      return leftHeight + 1;
    } else {
      return rightHeight + 1;
    }

  }



}
