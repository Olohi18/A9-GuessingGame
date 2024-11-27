import org.junit.Test;
import org.junit.Assert;

public class DecisionTreeTest {
    DecisionTree dTree1 = new DecisionTree("A");
    DecisionTree dTree3 = new DecisionTree("C");
    DecisionTree dTree5 = new DecisionTree("E");
    DecisionTree dTree7 = new DecisionTree("G");
    DecisionTree dRoot2 = new DecisionTree("B", dTree1, dTree3);
    DecisionTree dRoot3 = new DecisionTree("F", dTree5, dTree7);
    DecisionTree dRoot1 = new DecisionTree("D", dRoot2, dRoot3);
    DecisionTree dEmptyRoot = null; 


    @Test
    public void test_basic_tree(){
        Assert.assertTrue(!(BinaryTree.isEmpty(dRoot1) && BinaryTree.isEmpty(dRoot2) && BinaryTree.isEmpty(dRoot3)));
        Assert.assertTrue((BinaryTree.isEmpty(dEmptyRoot)));
        Assert.assertTrue(dTree1.getData() == "A");
        Assert.assertTrue(dRoot2.getData() == "B");
        Assert.assertTrue(dTree3.getData() == "C");
        Assert.assertTrue(dTree5.getData() == "E");
        Assert.assertTrue(dRoot1.getData() == "D");
        Assert.assertTrue(dRoot3.getData() == "F");
        Assert.assertTrue(dRoot1.getLeft().getLeft() == dTree1);
        Assert.assertEquals(dRoot1.getLeft().getRight(), dTree3);
        Assert.assertEquals(dRoot1.getRight().getRight(), dTree7);
        Assert.assertEquals(dRoot1.getRight().getLeft(), dTree5);
        Assert.assertEquals(dRoot1.getLeft().getLeft().getLeft(), null);
        Assert.assertEquals(dEmptyRoot, null);
    }
}
