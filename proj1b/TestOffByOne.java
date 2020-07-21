
import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    @Test
    public void testequalChars() {
        assertTrue(offByOne.equalChars('a','b'));
        assertTrue(offByOne.equalChars('d','c'));
        assertTrue(offByOne.equalChars('%','&'));
        assertFalse(offByOne.equalChars('z','a'));
        assertFalse(offByOne.equalChars('A','b'));

    }


}