package lotto.model;

import lotto.global.LottoRank;

import java.util.Map;

public class LottoResultFormatter {
    
    private static final String STATISTICS = "당첨 통계 \n-------";
    private static final String TOTAL_PROFIT_RATE_PREFIX = "총 수익률은 ";
    private static final String TOTAL_PROFIT_RATE_SUFFIX = "%입니다.";
    private static String resultMessage(String prizeText, int count) {
        return prizeText+" - "+count+"개";
    }
    
    public static String formatLottoResult(int money, Map<LottoRank, Integer> lottoResult) {
        float sum = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(STATISTICS+"\n");
        for(LottoRank rank : LottoRank.values()){
            sb.append(resultMessage(rank.getPrizeText(),lottoResult.get(rank))+"\n");
            sum += rank.getPrize()*lottoResult.get(rank);
        }
        sb.append(TOTAL_PROFIT_RATE_PREFIX);
        sb.append(round(sum/money*100,2));
        sb.append(TOTAL_PROFIT_RATE_SUFFIX);
        return sb.toString();
    }

    private static float round(float x, int point) {
        float scale = (float) Math.pow(10, point);
        return Math.round(x * scale) / scale;
    }

}
