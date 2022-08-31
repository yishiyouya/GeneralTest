package mywork.myalgoserver;


/**
 * 证券行情信息
 */
public class MdsApi_Quotation {
	public String qsource;
    /**
     * 性能测试用
     */
    public String uuid;
    private long apitime[] = new long[2];//api开始和结束时间
    public long receivedtime;
    public long processtime;
    public long parsetime;
    public long updatecachetime;
	public int sequenceno;
    /**
     * ticksn
     */
    public int ticksequenceno;
    public double delta;
    public long lastmodifytime;
    public int mseconds;
    /**
     * 市场
     */
    public String exchid;
    /**
     * 证券代码
     */
    public String stkid;
    /**
     * 证券名称
     */
    public String stkname;
    /**
     * 发生时间，格式hhmmss 
     */
    public long occurtime;
    /**
     * 买1-10价格
     */
    public double buyprice[] = new double[10];
    /**
     * 卖1-10价格
     */
    public double sellprice[] = new double[10];
    /**
     * 买1-10数量
     */
    public long buyqty[] = new long[10];
    /**
     * 卖1-10数量
     */
    public long sellqty[] = new long[10];
    /**
     * 成交价格
     */
    public double knockprice;
    /**
     * 成交数量
     */
    public long knockqty;
    /**
     * 总买量
     */
    public long totalbuyqty;
    /**
     * 总卖量
     */
    public long totalsellqty;
    /**
     * 加权平均买价
     */
    public double buyavgprice;
    /**
     * 加权平均卖价
     */
    public double sellavgprice;
    /**
     * 总买差量
     */
    public long totalbuyqtydiff;
    /**
     * 总卖差量
     */
    public long totalsellqtydiff;
    /**
     * 委差
     */
    public long volumediff;
    /**
     * 委比
     */
    public double volumerate;
    /**
     * 最小报单价差
     */
    public double diffprice;
    /**
     * 涨跌幅
     */
    public double diffrate;
    /**
     * iopv 净值估值
     */
    public double iopv;
    /**
     * 成交笔数
     */
    public long tradescount;
    /**
     *  当天交易所总成交数量
     */
    public long exchtotalknockqty;
    /**
     * 成交金额
     */
    public double knockamt;
    /**
     * 当天交易所总成交金额
     */
    public double exchtotalknockamt;
    /**
     * 内盘
     */
    public long innervolume;
    /**
     * 外盘
     */
    public long outervolume;
    /**
     * 不定盘
     */
    public long notdefinevolume;
    /**
     *  买卖性质
     */
    public String tradeproperties;
    /**
     * 分钟行情最高价
     */
    public double highprice;
    /**
     * 分钟行情最低价
     */
    public double lowprice;
    /**
     * 市场持仓量
     */
    public int openposition;
    /**
     * 仓差
     */
    public long positdiff;
    /**
     * 加权平均买价
     */
    public double buyautocostprice;
    /**
     * 加权平均卖价
     */
    public double sellautocostprice;
    /**
     * 成交时间
     */
    public long knocktime;
    /**
     * 加权平均价(5位小数)
     */
    public double knockavgprice;
    /**
     * 市盈率
     */
    public double priceearningratio;
    /**
     * 市净率
     */
    public double pbv;
    /**
     * 换手率
     */
    public double turnoverrate;
    /**
     * 振幅
     */
    public double dailyamplituderate;
    /**
     * 总市值
     */
    public double capitalization;
    /**
     * 原始成交价格
     */
    public double oriknockprice;
    /**
     * 收到行情时间，格式yyyymmddhhmmssfff
     */
    public long quotreceivetime;
    /**
     *   期权合约状态
     */
    public String tradingphasestatus;
    /**
     * 市场状态信息
     */
    public String stkorderstatus;

    /**
     * 构造函数, exchid, stkid决定一只股票
     */
    //trade conditional code
    public String tradeconditionalcode;
    public double iap = 0; //虚拟成交价格
    public long iav = 0;//虚拟成交数量
    public double lasttradeprice = 0; //成交价格
    public long lasttradeqty = 0;//成交数量
    // 买一委托队列 50
    public int[] buyorders;

    // 卖一委托队列 50
    public int[] sellorders;
}
