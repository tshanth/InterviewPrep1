package Tries;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SearchPalindromeWord {
    /*
        Trie Node : - This should have boolean to say end of word.
                    - Also Array of TrieNodes to hold all characters . eiher go with 26 if ONLY lower case. else go with 256 to allow all chars.
                    - Initialize TrieNode Children array to above size
     */
        public static int ALPHABET_SIZE = 26;
        public static class TrieNode{
            boolean isEndOfWord;
            Tries.BuildAndSearchTriesForWordSet.TrieNode[] children;
            public TrieNode(){
                this.isEndOfWord=false;
                this.children = new Tries.BuildAndSearchTriesForWordSet.TrieNode[ALPHABET_SIZE];
            }
        }

        Tries.BuildAndSearchTriesForWordSet.TrieNode root = new Tries.BuildAndSearchTriesForWordSet.TrieNode();
        public void insertWords(List<String> words){
            for(String word: words){
                insertEachWord(word);
            }
        }

        public void insertEachWord(String w){
            Tries.BuildAndSearchTriesForWordSet.TrieNode t = root;
            for(char c : w.toCharArray()){
                int index ;
                index = c - 'a';

                if(t.children[index] == null){
                    t.children[index] = new Tries.BuildAndSearchTriesForWordSet.TrieNode();
                }
                t = t.children[index];
            }
            t.isEndOfWord = true;
        }

        public Boolean searchWord(String w){
            Tries.BuildAndSearchTriesForWordSet.TrieNode t = root;
            for(char c: w.toCharArray()){
                int idx ;
                idx = c - 'a';
                if(t.children[idx]==null){
                    return false;
                }
                t=t.children[idx];
            }
            if(t.isEndOfWord) return true;
            return false;
        }

        public Boolean searchPalindrome(String wrd){
            String reverse = new StringBuilder(wrd).reverse().toString();
            Boolean prefixExists = checkPrefix(reverse);
            if(prefixExists){
                isRemainingPalin(reverse);
            }
            return false;
        }

        public Boolean checkPrefix(String s){
            Tries.BuildAndSearchTriesForWordSet.TrieNode t = root;
            for(char c: s.toCharArray()){
                int idx = c-'a';
                if(t.children[idx]==null){
                    return false;
                }
                t=t.children[idx];
            }
            return true;
        }
        public Boolean isRemainingPalin(String s){
            Tries.BuildAndSearchTriesForWordSet.TrieNode t = root;
            StringBuilder sb = new StringBuilder();
            int i=0;
            for(char c: s.toCharArray()){
                i++;
                int idx = c-'a';
                if(t.children[idx] == null){
                    return false;
                }
                t=t.children[idx];
            }
            appendAndDecide(t,s,new StringBuilder());
            if(list.size() == 0){
                return false;
            }
            return true;
        }

        public static List<String> list = new ArrayList<>();

        public String appendAndDecide(Tries.BuildAndSearchTriesForWordSet.TrieNode t, String origStr, StringBuilder sb){
            if(t.isEndOfWord){
                if(isPalindrome(sb.toString())){
                    return sb.toString();
                } else {
                    return "";
                }
            }
            for(int i =0; i<ALPHABET_SIZE; i++) {
                if (t.children[i] != null) {
                    sb.append((char) (i + 'a'));
                    String retVal = appendAndDecide(t.children[i],origStr, sb);
                    if(!retVal.isBlank())
                        list.add(origStr+retVal+(new StringBuilder(origStr).reverse()));
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
            return "";
        }

        public boolean isPalindrome(String s){
            int len = s.length();
            int origLen = len;
            int i=0;
            while( i<len ) {
                if(s.charAt(i) != s.charAt(len-1)){
                    return false;
                }
                i++;len--;
            }

            return true;

        }
        public static void main(String[] args) {
            Tries.BuildAndSearchTriesForWordSet triesSet = new Tries.BuildAndSearchTriesForWordSet();
            triesSet.insertWords(Arrays.asList("word","war","troo"));
            System.out.println(triesSet.searchWord("true"));
            System.out.println(triesSet.searchPalindrome("rt"));
            System.out.println("List of Palindromes are " + list.toString());
            System.out.println(triesSet.isPalindrome("woow"));
        }
}
