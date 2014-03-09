package practica01;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import estructurasdedatos.ALStack;
import estructurasdedatos.Iterator;
import estructurasdedatos.AVLTree.AVLNode;

public class AVLTree<T> extends estructurasdedatos.AVLTree<T> {

	public AVLTree() {
		super();
	}

	public Iterator<T> inOrderIterator() {
		return new TreeInOrderIterator(root);
	}
	
	

	private class TreeInOrderIterator implements Iterator<T> {
		private ALStack<AVLNode<T>> stack = null;
		private AVLNode<T> curr = null;
		private AVLNode<T> lastReturned = null;

		// set expectedModCount to the number of list changes
		// at the time of iterator creation
		private int expectedModCount = modCount;

		// go far left from t, pushing all the nodes with left
		// children on stack
		private AVLNode<T> goFarLeft(AVLNode<T> t) {
			if (t == null)
				return null;
			while (t.left != null) {
				stack.push(t);
				t = t.left;
			}
			return t;
		}

		public TreeInOrderIterator(AVLNode<T> root) {
			stack = new ALStack<AVLNode<T>>();
			curr = goFarLeft(root);
		}

		public boolean hasNext() {
			return curr != null;
		}

		public T current() {
			return lastReturned.nodeValue;
		}

		public T next() {
			// check that the iterator is in a consistent state.
			// throws ConcurrentModificationException if not
			checkIteratorState();

			if (curr == null)
				throw new NoSuchElementException("No elements remaining");

			// capture the value in the node
			T returnValue = (T) curr.nodeValue;

			lastReturned = curr;

			if (!stack.isEmpty() && !curr.equals(stack.peek()))
				// no right subtree there are other nodes
				// to visit. pop the stack
				curr = (AVLNode<T>) stack.peek();
			else if (curr.right != null) { // have a right subtree
				// stack nodes on left subtree
				stack.pop();
				curr = goFarLeft(curr.right);
			} else
				curr = null; // end of tree; set curr to null

			return returnValue;
		}

		public void remove() {
			// no implementation
		}

		// protected so MiniListIteratorImpl class can use it also
		private void checkIteratorState() {
			if (expectedModCount != modCount)
				throw new ConcurrentModificationException(
						"Inconsistent iterator");
		}
	}

}
