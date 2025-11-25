package solutions;

public enum Level {
    // 宣告NORMAL, VIP兩個成員, 為NORMAL指定利率為0.01,VIP為0.02
    NORMAL(0.01), VIP(0.02);

    // 宣告利率變數
    private double rate;

    // 宣告private constructor, 接收利率參數
    private Level(double rate) {
        this.rate = rate;
    }

    // 宣告傳回利率的方法
    public double getRate() {
        return rate;
    }
}
