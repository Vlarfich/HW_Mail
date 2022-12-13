import com.solvd.mail.myLinkedList.MyLinkedList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Testing {

    @Test
    public void test1() {
        MyLinkedList<String> expected = new MyLinkedList<>("");
        expected.add("HI");
        expected.addElement("Vlad");
        expected.add(null);
        MyLinkedList<String> actual = new MyLinkedList<>("", "HI", "Vlad", null);
    }
}
