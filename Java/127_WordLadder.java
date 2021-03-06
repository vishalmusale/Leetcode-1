Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence 
from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
UPDATE (2017/1/20):
The wordList parameter had been changed to a list of strings (instead of a set of strings).
Please reload the code definition to get the latest changes.

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0 || !wordList.contains(endWord)){
            return 0;
        }
        if (beginWord.equals(endWord)){
            return 1;
        }
        int result = 1;
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(beginWord);
        set.add(beginWord);
        
        while (!queue.isEmpty()){
            int size = queue.size();
            result++;
            for (int i = 0; i < size; i++){
                String str = queue.poll();
                for(String word : getNextWord(wordList, str)){
                    if (word.equals(endWord)){
                        return result;
                    }
                    if (!set.contains(word)){
                        queue.offer(word);
                        set.add(word);
                    }
                }
            }
        }
        return 0;
    }
    
    private List<String> getNextWord(List<String> wordList, String str){
        Set<String> set = new HashSet<>(wordList); //convert list to set so that contains function takes O(1) instead of O(n)
        List<String> result = new ArrayList<>();
        for (int i = 0; i < str.length(); i++){
            for (char j = 'a'; j <= 'z'; j++){
                if (j != str.charAt(i)){
                   String word = replace(str, j, i);
                   if (set.contains(word)){
                       result.add(word);
                   }
                }
            }
        }
        return result;
    }
    private String replace(String str, char j, int i){
        char[] charArray = str.toCharArray();
        charArray[i] = j;
      //  return String.valueOf(charArray);
        return new String(charArray);
    }
}


Combined to one function
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0 || !wordList.contains(endWord)){
            return 0;
        }
        if (beginWord.equals(endWord)){
            return 1;
        }
        int result = 1;
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(beginWord);
        set.add(beginWord);
        Set<String> wordSet = new HashSet<>(wordList);
        
        while (!queue.isEmpty()){
            int size = queue.size();
            result++;
            for (int k = 0; k < size; k++){
                String str = queue.poll();
                for (int i = 0; i < str.length(); i++){
                    char[] charArray = str.toCharArray();
                    for (char j = 'a'; j <= 'z'; j++){
                        if (str.charAt(i) == j){
                            continue;
                        }
                        charArray[i] = j;
                        String word = new String(charArray);
                        if (word.equals(endWord)){
                            return result;
                        }
                        if (wordSet.contains(word) && !set.contains(word)){
                            queue.offer(word);
                            set.add(word);
                        }
                    }
                }
            }
        }
        return 0;
    }
}

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        queue.offer(beginWord);
        seen.add(beginWord);
        Set<String> wordSet = new HashSet<>(wordList);
        int res = 0;
        while (!queue.isEmpty()){
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++){
                String curr = queue.poll();
                if (curr.equals(endWord)){
                    return res;
                }
                char[] charArray = curr.toCharArray();
                for (int j = 0; j < curr.length(); j++){
                    for (char ch = 'a'; ch <= 'z'; ch++){
                        char c = charArray[j];
                        if (ch != c){
                            charArray[j] = ch;
                            String str = new String(charArray);
                            if (!seen.contains(str) && wordSet.contains(str)){
                                queue.offer(str);
                                seen.add(str);
                            }
                            charArray[j] = c; 
                        }
                    }
                }
            }
        }
        return 0;
    }
}


Better:
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int res = 0;
        Set<String> set = new HashSet<>();
        for (String s : wordList){
            set.add(s);
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        queue.offer(beginWord);
        seen.add(beginWord);
        while (!queue.isEmpty()){
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++){
                String str = queue.poll();
                if (endWord.equals(str)){
                    return res;
                }
                char[] charArr = str.toCharArray();
                for (int j = 0; j < str.length(); j++){
                    char c = charArr[j];
                    for (char k = 'a'; k <= 'z'; k++){
                        if (c == k){
                            continue;
                        }
                        charArr[j] = k;
                        String cand = new String(charArr);
                        if (!seen.contains(cand) && set.contains(cand)){
                            seen.add(cand);
                            queue.offer(cand);
                        }
                    }
                    charArr[j] = c;
                }
            }
        }
        return 0;
    }
}
