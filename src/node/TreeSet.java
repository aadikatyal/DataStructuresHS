public class TreeSet<E extends Comparable<E>>
{
    TreeNode<E> root;
    int size;
    String str;

    public TreeSet()
    {
        root = null;
        size = 0;
        str = "";
    }

    public int size()
    {
        return size;
    }

    public TreeNode<E> getRoot()
    {
        return root;
    }

    public void add(E value)
    {
        TreeNode<E> newNode = new TreeNode<E>(value);
        if (root == null)
        {
            root = newNode;
            size++;
        }
        else add(root, value);
    }
    public void add(TreeNode<E> root,E value)
    {
        TreeNode<E> currentNode = root;
        TreeNode<E> newNode = new TreeNode<E>(value);
        if(currentNode.getValue().equals(value))
            return;

        if (value.compareTo(currentNode.getValue()) < 0)
        {
            if (currentNode.getLeft() == null)
            {
                currentNode.setLeft(newNode);
                size++;
                return;
            }
            else add(currentNode.getLeft(),value);
        }
        else
        {
            if (currentNode.getRight() == null)
            {
                currentNode.setRight(newNode);
                size++;
                return;
            }
            else add(currentNode.getRight(),value);
        }
    }

    public void remove(E value)
    {
        if (root == null)
        {
            return;
        }
        if (contains(root, value))
        {
            if (root.getLeft() == null && root.getRight() == null)
            {
                root = null;
                size = 0;
                return;
            }
            else
            {
                size--;
                root = remove(root, value);
            }
        }
    }
    public TreeNode<E> remove(TreeNode<E> root, E value)
    {
        TreeNode<E> currentNode = root;
        if (currentNode.getValue().equals(value))
        {
            if (currentNode.getRight() == null && currentNode.getLeft() == null)
            {
                currentNode = null;
            }
            else if (currentNode.getRight() == null)
            {
                currentNode = currentNode.getLeft();
            }
            else if (currentNode.getLeft() == null)
            {
                currentNode = currentNode.getRight();
            }
        }
        else if (value.compareTo(currentNode.getValue()) < 0)
        {
            currentNode.setLeft(remove(currentNode.getLeft(), value));
        }
        else if (value.compareTo(currentNode.getValue()) > 0)
        {
            currentNode.setRight(remove(currentNode.getRight(), value));
        }

        return currentNode;
    }

    public boolean contains(E value)
    {
        return contains(root, value);
    }
    
    public boolean contains(TreeNode<E> root, E value)
    {
        TreeNode<E> currentNode = root;
        if (currentNode == null)
        {
            return false;
        }
        if (currentNode.getValue().equals(value))
        {
            return true;
        }
        if (value.compareTo(currentNode.getValue()) < 0)
        {
            return contains(currentNode.getLeft(), value);
        }
        return contains(currentNode.getRight(), value);
    }

    public void inOrder(TreeNode<E> node)
    {
        if (node != null) {
            inOrder(node.getLeft());
            str += node.getValue() + ", ";
            inOrder(node.getRight());
        }
    }
    public String inOrderString()
    {
        if (size == 0)
        {
            return "[]";
        }
        str = "[";
        inOrder(root);
        return str.substring(0, str.length()-2)+"]";
    }

    public void preOrder(TreeNode<E> node)
    {
        if (node != null) {
            str += node.getValue() + ", ";
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }
    public String preOrderString()
    {
        if (size == 0) {
            return "[]";
        }
        str = "[";
        preOrder(root);
        return str.substring(0, str.length()-2)+"]";
    }

    public void postOrder(TreeNode<E> node)
    {
        if (node != null)
        {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            str += node.getValue() + ", ";
        }
    }
    public String postOrderString()
    {
        if (size == 0)
        {
            return "[]";
        }
        str = "[";
        postOrder(root);
        return str.substring(0, str.length()-2)+"]";
    }

    public void rotateLeft()
    {
        rotateLeft(root);
    }
    public void rotateLeft(TreeNode<E> node)
    {
        if(node != null && node.getRight() != null)
        {
            TreeNode<E> tempNode = node.getRight();
            node.setRight(tempNode.getLeft());
            tempNode.setLeft(node);
            node = tempNode;
        }
    }

    public void rotateRight()
    {
        rotateRight(root);
    }
    
    private void rotateRight(TreeNode<E> node)
    {
        if(node != null && node.getLeft() != null)
        {
            TreeNode<E> tempNode = node.getLeft();
            node.setLeft(tempNode.getRight());
            tempNode.setRight(node);
            node = tempNode;
        }
    }

    public class TreeNode<E extends Comparable<E>>
    {
        E value;
        TreeNode<E> leftChild;
        TreeNode<E> rightChild;

        public TreeNode(E value)
        {
            this.value = value;
            leftChild = null;
            rightChild = null;
        }

        public TreeNode<E> getRight()
        {
            return rightChild;
        }

        public TreeNode<E> getLeft()
        {
            return leftChild;
        }

        public void setRight(TreeNode<E> node)
        {
            rightChild = node;
        }

        public void setLeft(TreeNode<E> node)
        {
            leftChild = node;
        }

        public E getValue()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return (String) value;
        }
    }
}