package utilidades;

public class SimpleABB<T> {

	private Node<T> root;
	private int size;

	public SimpleABB() {
		size = 0;
	}

	@SuppressWarnings("unchecked")
	public boolean add(T element) {
		if (!this.contains(element)) {
			size ++;
			Node<T> toAdd = new Node<T>(element);
			if (root == null){
				root = toAdd;
				return true;
			}else {
				@SuppressWarnings("rawtypes")
				Comparable elm = (Comparable) element;
				Node<T> cur = root;
				do {
					if (elm.compareTo(cur.element) > 0) {
						if (cur.right == null) {
							cur.right = toAdd;
							return true;
						} else
							cur = cur.right;
					} else {
						if (cur.left == null) {
							cur.left = toAdd;
							return true;
						} else
							cur = cur.left;
					}
				} while (true);
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private boolean contains(T element) {
		if(size == 0) return false;
		Node<T> cur = root;
		@SuppressWarnings("rawtypes")
		Comparable elm = (Comparable) element;
		do {
			if(elm.compareTo(cur.element) == 0) return true;
			else if (elm.compareTo(cur.element) > 0) {
				if (cur.right == null) {
					return false;
				} else
					cur = cur.right;
			} else {
				if (cur.left == null) {
					return false;
				} else
					cur = cur.left;
			}
		} while (true);
	}
	
	@SuppressWarnings("unchecked")
	public T get(T element){
		Node<T> cur = root;
		@SuppressWarnings("rawtypes")
		Comparable elm = (Comparable) element;
		do {
			if(elm.compareTo(cur.element) == 0) return cur.element;
			else if (elm.compareTo(cur.element) > 0) {
				if (cur.right == null) {
					return null;
				} else
					cur = cur.right;
			} else {
				if (cur.left == null) {
					return null;
				} else
					cur = cur.left;
			}
		} while (true);
	}

	@SuppressWarnings("hiding")
	private class Node<T> {

		public T element;
		public Node<T> right;
		public Node<T> left;

		public Node(T element) {
			this.element = element;
		}

	}

}
