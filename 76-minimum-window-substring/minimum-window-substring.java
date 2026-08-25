import java.util.HashMap;
import java.util.Map;

class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        Map<Character, Integer> targetCounts = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetCounts.put(c, targetCounts.getOrDefault(c, 0) + 1);
        }

        int requiredMatches = targetCounts.size();
        int formedMatches = 0;

        Map<Character, Integer> windowCounts = new HashMap<>();

        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        int left = 0;
        int right = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

            if (targetCounts.containsKey(c) && 
                windowCounts.get(c).intValue() == targetCounts.get(c).intValue()) {
                formedMatches++;
            }

            while (left <= right && formedMatches == requiredMatches) {
                c = s.charAt(left);

                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                windowCounts.put(c, windowCounts.get(c) - 1);
                if (targetCounts.containsKey(c) && 
                    windowCounts.get(c).compareTo(targetCounts.get(c)) < 0) {
                    formedMatches--;
                }

                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}