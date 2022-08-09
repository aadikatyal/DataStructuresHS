import java.util.ArrayList;
import java.util.Random;

public class TreeNodeSetTask
{
    TreeSet treeSet;

    public TreeNodeSetTask()
    {
        treeSet = new TreeSet();
        System.out.print("Values: ");
        for (int i = 0; i < 10; i++)
        {
            int x;
            do
            {xw
                x = (int)(Math.random()*100) + 1;
            } while (treeSet.contains(x));
            System.out.print(x + " ");
            treeSet.add(x);
        }

        System.out.println("\nSize: " + treeSet.size() + "\n");

        System.out.println("PreOrder Traversal:");
        TreeSet preOrderSet = new TreeSet();
        String preOrderString = treeSet.preOrderString().replaceAll("\\[", "").replaceAll("\\]", "");
        String[] preArr = preOrderString.split(", ");
        for (int i = 0; i < preArr.length; i++)
        {
            int x = Integer.parseInt(preArr[i]);
            preOrderSet.add(x);
        }

        System.out.println("PreOrder: " + preOrderSet.preOrderString());
        System.out.println("InOrder: " + preOrderSet.inOrderString());
        System.out.println("PostOrder: " + preOrderSet.postOrderString());

        System.out.println("\nInOrder Traversal:");
        TreeSet inOrderSet = new TreeSet();
        String inOrderString = treeSet.inOrderString().replaceAll("\\[", "").replaceAll("\\]", "");
        String[] inArr = inOrderString.split(", ");
        for (int i = 0; i < inArr.length; i++)
        {
            int x = Integer.parseInt(inArr[i]);
            inOrderSet.add(x);
        }

        System.out.println("PreOrder: " + inOrderSet.preOrderString());
        System.out.println("InOrder: " + inOrderSet.inOrderString());
        System.out.println("PostOrder: " + inOrderSet.postOrderString());
        System.out.println("Observation: PreOrder and PostOrder outputs are the same; same InOrder as previous traversal.");

        System.out.println("\nPostOrder Traversal:");
        TreeSet postOrderSet = new TreeSet();
        String postOrderString = treeSet.postOrderString().replaceAll("\\[", "").replaceAll("\\]", "");
        String[] postArr = postOrderString.split(", ");

        for (int i = 0; i < postArr.length; i++)
        {
            int x = Integer.parseInt(postArr[i]);
            postOrderSet.add(x);
        }

        System.out.println("PreOrder: " + postOrderSet.preOrderString());
        System.out.println("InOrder: " + postOrderSet.inOrderString());
        System.out.println("PostOrder: " + postOrderSet.postOrderString());
        System.out.println("Observation: Same InOrder as previous traversal.");

        System.out.print("\nLetters: ");
        TreeSet letterTree = new TreeSet();
        for (int i = 0; i < 20; i++)
        {
            Random r = new Random();
            char c = (char) (r.nextInt(26) + 'a');
            System.out.print(c + " ");
            letterTree.add(c);
        }

        System.out.println("\nPreOrder: " + letterTree.preOrderString());
        System.out.println("InOrder: " + letterTree.inOrderString());
        System.out.println("PostOrder: " + letterTree.postOrderString());

        for (int i = 1; i < 4; i++)
        {
            System.out.println("\nRight Rotation " + i + ":");
            letterTree.rotateRight();

            System.out.println("PreOrder: " + letterTree.preOrderString());
            System.out.println("InOrder: " + letterTree.inOrderString());
            System.out.println("PostOrder: " + letterTree.postOrderString());
        }

        for (int i = 1; i < 4; i++)
        {
            System.out.println("\nLeft Rotation " + i + ":");
            letterTree.rotateLeft();
            System.out.println("PreOrder: " + letterTree.preOrderString());
            System.out.println("InOrder: " + letterTree.inOrderString());
            System.out.println("PostOrder: " + letterTree.postOrderString());
        }

        TreeSet intSet = new TreeSet();
        ArrayList<Integer> intList = new ArrayList<>();
        System.out.print("\nIntegers: ");
        for (int i = 0; i < 10; i++)
        {
            int x;
            do
            {
                x = (int)(Math.random()*100) + 1;
            } while (intSet.contains(x));
            System.out.print(x + " ");
            intList.add(x);
            intSet.add(x);
        }

        int removeIndex = (int)(Math.random() * intList.size());
        System.out.println("\nRemove Index: " + removeIndex + "; Remove Value: " + intList.get(removeIndex));
        int num = intList.remove(removeIndex);
        intSet.remove(num);
        System.out.println("PreOrder: " + intSet.preOrderString());
        System.out.println("InOrder: " + intSet.inOrderString());
        System.out.println("PostOrder: " + intSet.postOrderString());
    }

    public static void main(String[] args)
    {
        new TreeNodeSetTask();
    }
}