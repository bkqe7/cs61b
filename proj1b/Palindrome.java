public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new LinkedListDeque<Character>();
        for(int i = 0; i<word.length();i += 1) {
            Character item = word.charAt(i);
            wordDeque.addLast(item);
        }
        return wordDeque;

    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque);

    }
    private boolean isPalindromeHelper(Deque<Character> wordDeque) {
        if(wordDeque.size()==0 || wordDeque.size() == 1) {
            return true;
        }
        Character first = wordDeque.removeFirst();
        Character last = wordDeque.removeLast();
        return (first==last) && isPalindromeHelper(wordDeque);

    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque,cc);

    }

    private boolean isPalindromeHelper(Deque<Character> wordDeque,CharacterComparator cc) {
        if(wordDeque.size()==0 || wordDeque.size() == 1) {
            return true;
        }
        Character first = wordDeque.removeFirst();
        Character last = wordDeque.removeLast();
        return (cc.equalChars(first,last)) && isPalindromeHelper(wordDeque,cc);

    }



}
