package LeetCode;

public class leetcode_palindromePartitioningII
{
	/**
	 * palindrome-partitioning-ii
	 * Given a string s, partition s such that every substring of the partition is a palindrome.
	 * Return the minimum cuts needed for a palindrome partitioning of s.
	 * For example, given s ="aab",Return 1 since the palindrome partitioning["aa","b"]could be produced using 1 cut.
	 */
	/**
	 * 动态规划的题，最主要就是写出状态转移方程
	 * 状态转移，其实就是怎么把一个大的状态表示为两个或者多个已知的状态
	 * 以设f[i][j]为最小的切点数，那么有：
	 * 1、s[i][j]为回文字符串，则f[i][j] = 0;
	 * 2、增加一个切点p，将s[i][j]切割为两端s[i][p]、s[p+1][j],则f[i][j] = f[i][p]+f[p+1][j]
	 * 所谓的状态转移方程就是上面的式子
	 *
	 * 接着来看看怎么组织程序，先看看状态转移的思路：
	 * 以"aab"为例，"aab"明显不是回文串
	 * 所以 f("aab") = min[ (f("a")+f("ab"), (f("aa")+f("b")) ] + 1;
	 * f("a") = 0;
	 * f("ab") = f("a")+f("b") +1  = 0+0+1 = 1;
	 * f("aa") = 0;
	 * f("b") = 0;
	 * 即f("aab") = 1;
	 *
	 * 这是一个递归调用的过程，计算f("ab")需要先计算f("a")、f("b")
	 * 用递归实现动态规划，在思路上是最简单的，大部分的题目都可以用这种方式解决
	 *
	 * 但是有一些数据变态的题目，加上测试机子给的堆栈太小，这种递归的算法很容易就爆栈了
	 * 我们需要用我们的聪明智慧，把递归的程序写为非递归的。
	 * 把解题思路从下往上看，假设我们先求出来了f("a")，f("b")
	 * 那么我们可以求出f("aa"),f("ab")
	 * 接着我们就可以得出答案f("aab")了
	 * 在这个过程中，我们需要牺牲空间（f[1000][1000]）代替堆栈记录递归调用的返回值
	 * 而且这种方式有个好处，就是可以减少计算量
	 * 比如计算f("aab")时需要计算f("aa")+f("b")
	 * 计算f("ab")事需要计算f("a")+f("b")
	 * 这里就计算了两次f("b");
	 * 在第一次计算f("b")之后,将f("b")记录下来，可以减少一次计算量
	 * 动态规划本质上是暴力搜索，只不过咋这个暴力搜索的过程中，减少了不必要的计算，这样就提升了算法解决问题的速度
	 * 在一些题目中，你还可以根据题目减少某些分支的计算
	 * 比如只要判断这个字符串是回文串，就可以直接返回0，不需要一步步计算其中的子序列
	 */
/**
   * 1.dp[i]表示当前i到len-1这段的最小分割数
   * 2.dp[i]=min{dp[j+1]+1}（i=<j<len）其中str[i..j]必须是回文、
   * 3.p[i][j]=true表示str[i..j]是回文
   * 4.p[i][j]=s.charAt(i)==s.charAt(j) && (j-i<2||p[i+1][j-1])
   */
  public int minCut(String s) {
      int []dp = new int[s.length()+1];
      boolean [][]p = new boolean[s.length()][s.length()];
      dp[s.length()] = -1;//确保dp[s.length()-1]=0
      for(int i=s.length()-1;i>=0;i--){
          dp[i]=Integer.MAX_VALUE;
          for(int j=i;j<s.length();j++){
              if(s.charAt(i)==s.charAt(j) && (j-i<2||p[i+1][j-1])){
                  p[i][j]=true;
                  dp[i]=Math.min(dp[i],dp[j+1]+1);
              }
          }
      }
      return dp[0];
  }
}
