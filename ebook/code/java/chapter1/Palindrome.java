/**
 * Check weather a string is a palindrome
 * @author WangTaoTheTonic
 */
public class Palindrome
{
	// Solution 1, from sides to middle
	public static boolean isPalindromeV1(String str)
	{
		if(null == str || 0 == str.length())
		{
			return false;
		}
		
		int length = str.length();
		if(1 == length)
		{
			return true;
		}
		
		for(int leftFlag = 0, rightFlag = length - 1 ; leftFlag < rightFlag; leftFlag ++, rightFlag --)
		{
			if(str.charAt(leftFlag) != str.charAt(rightFlag))
				return false;
		}
		
		return true;
	}
	
	// Solution 2, from middle to sides
	public static boolean isPalindromeV2(String str)
	{
		if(null == str || 0 == str.length())
		{
			return false;
		}
		
		int length = str.length();
		if(1 == length)
		{
			return true;
		}
		
		int leftFlag = 0;
		int rightFlag = length;
		leftFlag = length / 2 - 1;
		if(0 == length % 2)
		{
			rightFlag = leftFlag + 1;
		}
		else
		{
			rightFlag = leftFlag + 2;
		}
		
		for( ; leftFlag >= 0; leftFlag --, rightFlag ++)
		{
			if(str.charAt(leftFlag) != str.charAt(rightFlag))
				return false;
		}
		
		return true;
	}



//https://articles.leetcode.com/longest-palindromic-substring-part-ii/#comment-34709
//求最长子回文字符串的长度
    String preProcess(String s) {
        int n = s.length();
        if (n == 0) return "^$";
        String ret = "^";
        for (int i = 0; i < n; i++)
            ret += "#" + s.substring(i, 1);

        ret += "#$";
        return ret;
    }
    String longestPalindrome(String s) {
        String T = preProcess(s);
        int n = T.length();
        int[] P = new int[n];
        int id = 0, mx = 0;
        for (int i = 1; i < n-1; i++) {
            int j = 2*id-i; // equals to j = id - (i-id)

            P[i] = (mx > i) ? Math.min(mx-i, P[j]) : 0;//在上述的c预言者赋值为1，

            // Attempt to expand palindrome centered at i
            while (T.valueOf(i + 1 + P[i]).equals(T.valueOf(i - 1 - P[i])))//这里判断以i为中心的字符串，向两侧延伸，判断是否相同，相同则++
                P[i]++;

            // If palindrome centered at i expand past R,
            // adjust center based on expanded palindrome.
            if (i + P[i] > mx) {
                id = i;
                mx = i + P[i];
            }
        }

        // Find the maximum element in P.
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n-1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }

        return s.substring((centerIndex - 1 - maxLen)/2, maxLen);
    }
}
