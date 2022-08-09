public class TreeSet<E extends Comparable<E>>
{
    private E root
    private int size;
    private String str;

    public TreeSet()
    {
        size = 0;
    }

    public void add(E value)
    {
        if(size == 0)
        {
            root = value;
        }

        if(!str.contains(String.valueOf(value)))
        {
            size++;
            
        }
    }

    public class TreeNode<E exrends Comparable<E>>
    {
        private E value;
        private TreeNode<E> left, right;

        public TreeNode(E e)
        {
            left = null;
            right = null;
            value = e;
        }

        public TreeNode<E> getRight()
        {
            return right;
        }

        public TreeNode<E> getLeft()
        {
            return left;
        }

        public void setRight(TreeNode<E> node)
        {
            right = node;
        }

        public void setLeft(TreeNode<E> node)
        {
            left = node;
        }

        public E getValue()
        {
            return value;
        }

        public String toString()
        {
            System.out.println(value);
        }
    }
}