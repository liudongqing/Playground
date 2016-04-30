package com.wuyouz.playground;

import java.util.HashSet;
import java.util.Set;

/**
 * The answer to a google interview quiz
 * http://decuqa.tumblr.com/post/140214864156/palindromes-google-interview
 * <p>
 * Created by dqliu on 4/20/16.
 */

public class Palindromes {

    private static Set<String> findPalindromes(String origin) {
        System.out.println("check : " + origin);
        Set<String> result = new HashSet<>();

        if (origin == null || origin.length() == 0) {
            return result;
        }

        if (origin.length() == 1) {
            System.out.println("add : " + origin);
            result.add(origin);
            return result;
        }

        StringBuffer buf = new StringBuffer();
        buf.append(origin.charAt(0));
        String subStr = buf.toString();
        if (!result.contains(subStr)) {
            System.out.println("add : " + subStr);
            result.add(subStr);
        }
        for (int i = 1; i < origin.length(); i++) {
            buf.append(origin.charAt(i));
            if (origin.charAt(i) == origin.charAt(0)) {
                boolean isPalindrom = true;
                for (int k = 1; k <= (buf.length() - 2) / 2; k++) {
                    if (buf.charAt(k) != buf.charAt(buf.length() - 1 - k)) {
                        isPalindrom = false;
                        break;
                    }
                }
                if (isPalindrom) {
                    subStr = buf.toString();
                    if (!result.contains(subStr)) {
                        System.out.println("add : " + subStr);
                        result.add(subStr);
                    }
                }
            }
        }
        return result;
    }

    private static Set<String> findPalindromesSmart(String origin) {
        Set<String> result = new HashSet<>();

        if (origin == null || origin.length() == 0) {
            return result;
        }

        for (int i = 0; i < origin.length(); i++) {
            String sub = origin.substring(i, i + 1);
            if (!result.contains(sub)) {
                result.add(sub);
            }
            //Odd
            int before = i - 1;
            int after = i + 1;
            while (before >= 0 && after < origin.length()) {
                if (origin.charAt(before) == origin.charAt(after)) {
                    sub = origin.substring(before, after + 1);
                    if (!result.contains(sub)) {
                        result.add(sub);
                    }
                    before--;
                    after++;
                } else {
                    break;
                }
            }

            //Even
            before = i;
            after = i + 1;
            while (before >= 0 && after < origin.length()) {
                if (origin.charAt(before) == origin.charAt(after)) {
                    sub = origin.substring(before, after + 1);
                    if (!result.contains(sub)) {
                        result.add(sub);
                    }
                    before--;
                    after++;
                } else {
                    break;
                }
            }

        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println(findPalindromesSmart("aabaddac"));
    }

}
