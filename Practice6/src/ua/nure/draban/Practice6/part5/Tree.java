package ua.nure.draban.Practice6.part5;

public class Tree<E extends Comparable<E>> {
	
	private static final String INDENT = "   ";

	private Node<E> root = null;
    private int count;

    public boolean add(E e) {
        if (e == null) {
            return false;
        } else if (root == null) {
            root = new Node<>(e);
            count++;
            return true;
        }
        boolean result = addInner(root, e);
        if (result) {
            count++;
        }
        return result;
    }

    public boolean remove(E element) {
        if (element == null || count == 0) {
            return false;
        } else if (count == 1 && root.value.compareTo(element) == 0) {
            count = 0;
            root = null;
        }
        boolean result = removeInner(root, element);
        if (result) {
            count--;
        }
        return result;
	}

	public void add(E[] elements) {
		for (E iter : elements) {
			add(iter);
		}
	}

	public void print() {
		if (root != null) {
            printInner(root);
        }
	}

    private boolean addInner(Node<E> root, E e) {
        boolean result = false;
        int compareTo = root.value.compareTo(e);
        if (compareTo > 0) {
            if (root.getLeft() == null) {
                Node<E> newNode = new Node<>(e);
                newNode.setHead(root);
                root.setLeft(newNode);
                result = true;
            } else {
                result = addInner(root.getLeft(), e);
            }
        } else if (compareTo < 0) {
            if (root.getRight() == null) {
                Node<E> newNode = new Node<>(e);
                newNode.setHead(root);
                root.setRight(newNode);
                result = true;
            } else {
                result = addInner(root.getRight(), e);
            }
        } else {
            result = false;
        }
        return result;
    }

    private boolean removeInner(Node<E> root, E e) {
        boolean result = false;
        int compareTo = root.value.compareTo(e);
        if (compareTo > 0) {
            if (root.getLeft() != null) {
                result = removeInner(root.getLeft(), e);
            }
        } else if (compareTo < 0) {
            if (root.getRight() != null) {
                result = removeInner(root.getRight(), e);
            }
        } else {
            int temp = 0;
            if (root.getHead() != null) {
                temp = e.compareTo(root.getHead().value);
            }
            if (root.getRight() == null) { // если нет правого сына
                if (temp < 0) {
                    if (root.getLeft() != null) {
                        root.getHead().setLeft(root.getLeft());
                        root.getLeft().setHead(root.getHead());
                    } else {
                        root.getHead().setLeft(null);
                    }
                } else if (temp > 0){
                    if (root.getLeft() != null) {
                        root.getHead().setRight(root.getLeft());
                        root.getLeft().setHead(root.getHead());
                    } else {
                        root.getHead().setRight(null);
                    }
                }
                result = true;
            } else if (root.getRight().getLeft() == null) { // если у правого сына нет левого сына
                if (temp < 0) {
                    root.getHead().setLeft(root.getRight());
                } else if (temp > 0){
                    root.getHead().setRight(root.getRight());
                }
                root.getRight().setHead(root.getHead());
                if (root.getLeft() != null) {
                    root.getLeft().setHead(root.getRight());
                    root.getRight().setLeft(root.getLeft());
                }
                result = true;
            } else if (root.getRight().getLeft() != null){  // если у правого сына есть левый сын
                Node<E> newHead = findLeftElement(root.getRight());
                if (newHead.getRight() != null) {
                    newHead.getHead().setLeft(newHead.getRight());
                } else {
                    newHead.getHead().setLeft(null);
                }

                if (root.getHead() != null) {
                    root.getHead().setRight(newHead);
                    newHead.setHead(root.getHead());

                    root.getRight().setHead(newHead);
                    newHead.setRight(root.getRight());
                } else if (root.getHead() == null){
                    this.root = new Node<>(newHead.value);
                    this.root.setLeft(root.getLeft());
                    this.root.setRight(root.getRight());
                }
                result = true;
            }
        }
        return result;
    }

    private void printInner(Node<E> root) {
        if (root.getLeft() != null) {
            printInner(root.getLeft());
        }
        System.out.print(root.value + " ");
        if (root.getRight() != null) {
            printInner(root.getRight());
        }
    }

    private Node<E> findLeftElement(Node<E> root) {
        Node<E> result = root;
        if (root.getLeft() != null) {
            result = findLeftElement(root.getLeft());
        }
        return result;
    }
	
	private static class Node<E> {
        Node<E> head = null;
		Node<E> left = null;
        Node<E> right = null;
        E value = null;

        public Node(E value) {
            this.value = value;
        }

        public Node<E> getHead() {
            return head;
        }

        public void setHead(Node<E> head) {
            this.head = head;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }
    }
}
