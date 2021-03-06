Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space.

click to show clarification.

Clarification:
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.

Method:
public class Solution {
    public String reverseWords(String s) {
        String[] words = s.trim().split(" +"); // + is regular expression, means at least one space
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
}

public class Solution {
    public String reverseWords(String s) {
        if (s == null || s.trim().length() == 0){
            return s.trim();
        }
        String[] arr = s.trim().split("\\s+");
        int n = arr.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++){
            StringBuilder temp = new StringBuilder(arr[i]);
            sb.append(temp.reverse());
            sb.append(" ");
        }
        return sb.reverse().toString().trim();
    }
}
