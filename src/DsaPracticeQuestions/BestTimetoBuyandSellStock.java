package DsaPracticeQuestions;

public class BestTimetoBuyandSellStock {

    public int maxProfit(int[] prices) { // Just visualize the prices as graph and dry run this algo
        int min = Integer.MAX_VALUE;
        int maxProfit = Integer.MIN_VALUE;

        for (int i = 0; i < prices.length; i++) {
            if (min > prices[i]) {
                min = prices[i];
            }
            int currProfit = prices[i] - min;
            maxProfit = Math.max(currProfit, maxProfit);
        }
        return maxProfit;
    }

}
