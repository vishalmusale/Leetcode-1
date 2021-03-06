Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.

The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards) for which the price of the stock was less than or equal to today's price.

For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans would be [1, 1, 1, 2, 1, 4, 6].

 

Example 1:

Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
Output: [null,1,1,1,2,1,4,6]
Explanation: 
First, S = StockSpanner() is initialized.  Then:
S.next(100) is called and returns 1,
S.next(80) is called and returns 1,
S.next(60) is called and returns 1,
S.next(70) is called and returns 2,
S.next(60) is called and returns 1,
S.next(75) is called and returns 4,
S.next(85) is called and returns 6.

Note that (for example) S.next(75) returned 4, because the last 4 prices
(including today's price of 75) were less than or equal to today's price.
 

Note:

Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.
There will be at most 10000 calls to StockSpanner.next per test case.
There will be at most 150000 calls to StockSpanner.next across all test cases.
The total time limit for this problem has been reduced by 75% for C++, and 50% for all other languages.

Method 1: Brute Force TLE
Time compleixty of next: O(n)
class StockSpanner {
    List<Integer> list;
    public StockSpanner() {
        list = new ArrayList<>();
    }
    
    public int next(int price) {
        int count = 1;
        int i = list.size() - 1;
        while (i >= 0){
            if (list.get(i) <= price){
                count++;
            }else{
                break;
            }
            i--;
        }
        list.add(price);
        return count;
    }
}

Method 2: Monotonic decreasing stack
Time compleixty of next : O(1)
class StockSpanner {
    Stack<int[]> stack;
    public StockSpanner() {
        stack = new Stack<>();
    }
    
    public int next(int price) {
        if (stack.isEmpty()){
            stack.push(new int[]{price, 1});
            return 1;
        }
        int[] curr = stack.peek();
        if (price < curr[0]){
            stack.push(new int[]{price, 1});
            return 1;
        }else{
            int val = 1;
            while (!stack.isEmpty() && curr[0] <= price){
                stack.pop();
                val += curr[1];
                if (!stack.isEmpty()){
                    curr = stack.peek();
                }
            }
            stack.push(new int[]{price, val});
            return val;
        }

    }
}

Shorter version
class StockSpanner {
    Stack<int[]> stack;
    public StockSpanner() {
        stack = new Stack<>();
    }  
    public int next(int price) {
        int val = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price){
            val += stack.pop()[1];
        }
        stack.push(new int[]{price, val});
        return val;
    }
}
