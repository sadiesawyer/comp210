package assn04;
import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {
		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element){

		if (_element.compareTo(element) < 0) {
			if (_right.isEmpty()) {
				_right = new NonEmptyBST<>(element);
			}
			else {
			_right = _right.insert(element);
		}
		}
		else if (_element.compareTo(element) > 0) {
			if (_left.isEmpty()) {
				_left = new NonEmptyBST<>(element);
			} else {
				_left = _left.insert(element);
			}
		}

		return this;
	}
	
	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		if (_element.equals(element)) {
			if (_right.isEmpty()) {
				// if the right child is empty, then replace with left (we will eventually get to one of these two steps as we recursively go through the function)
				return _left;
			} else if (_left.isEmpty()) {
				// if the left child is empty, replace with right
				return _right;
				//note: this also accounts for if it has no children, because it will just be replaced with an empty BST
			} else { //if the node has two children
				T successor = _right.findMin();
				//find the successor of the tree and store in T successor
				_element = successor;
				///set the element equal to that
				_right = _right.remove(successor);
				//call the remove function on the successor
			}
		}//use recursion to search for element
		else if (_element.compareTo(element) > 0) {
				_left = _left.remove(element);
			}
		else if (_element.compareTo(element) < 0) {
			_right = _right.remove(element);
			}
			return this;

			}


	
	// TODO: remove all in range (inclusive)
	@Override
	public BST<T> remove_range(T start, T end) {
		// Recursively remove elements from left and right subtrees
		if (!_left.isEmpty()) {
			_left = _left.remove_range(start, end);
		}
		if (!_right.isEmpty()) {
			_right = _right.remove_range(start, end);
		}

		// Remove current node if necessary
		if (start.compareTo(_element) <= 0 && end.compareTo(_element) >= 0) {
			return remove(_element);
		}

		return this;
	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		if (!this.isEmpty()) {
			// Print the value of the current node
			System.out.print(_element + " ");

			// Traverse the current element's left subtree
			_left.printPreOrderTraversal();

			// Traverse the current element's right subtree
			_right.printPreOrderTraversal();


		}
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		if (!this.isEmpty()) {
			_left.printPostOrderTraversal();
			_right.printPostOrderTraversal();
			System.out.print(_element + " ");



	} }

	// The findMin method returns the minimum value in the tree.
	@Override
	public T findMin() {
		if(_left.isEmpty()) {
			return _element;
		}
		return _left.findMin();
	}

	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}

	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
