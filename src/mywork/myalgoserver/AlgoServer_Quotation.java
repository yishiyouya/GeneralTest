package mywork.myalgoserver;


/**
 * 现货、期货行情对象
 * @author Administrator
 *
 */
public class AlgoServer_Quotation {

    //现货、期货公用属性
    public long occurtime = -1; //时间(作为历史行情中的时间戳)
    public String exchid; //市场
    public String stkid; //证券代码
    public String stkname; //证券名称
    public double closeprice; //昨日收盘价
    public double openprice; //今日开盘价
    public double newprice; //最新价
    public double highprice; //最高价
    public double lowprice; //最低价
    public double maxorderprice; //委托价格上限 
    public double minorderprice; //委托价格下限 
    public String closeflag = ""; //停牌标志
    public double orderpriceunit;//价格档位
    public int lowbuyqtylimit; // 买入数量下限
    public int lowsellqtylimit; // 卖出数量下限
    public int highbuyqtylimit;// 买入数量上限
    public int highsellqtylimit;// 卖出数量上限
    
    //现货行情属性
    public long knockqty; //成交数量
    public double knockmoney; //成交金额
    public double sellprice1; //卖价一
    public double sellprice2; //卖价二
    public double sellprice3; //卖价三
    public double sellprice4; //卖价四
    public double sellprice5; //卖价五
    public double buyprice1; //买价一
    public double buyprice2; //买价二
    public double buyprice3; //买价三
    public double buyprice4; //买价四
    public double buyprice5; //买价五
    public long sellqty1; //卖量一
    public long sellqty2; //卖量二
    public long sellqty3; //卖量三
    public long sellqty4; //卖量四
    public long sellqty5; //卖量五
    public long buyqty1; //买量一
    public long buyqty2; //买量二
    public long buyqty3; //买量三
    public long buyqty4; //买量四
    public long buyqty5; //买量五
    public double change; //价格变动
    public double changepecent; //价格变动率
    public String stkidprefix; //证券简称前缀
    public String tradetimeflag; //产品交易阶段
    public String pausetradestatus; //暂停交易标志
    public String passcreditcashstk; //融资交易状态
    public String passcreditsharestk; //融券交易状态
    public String otherbusinessmark; //其他业务状态
    public String changetime; //行情变更时间

    public double mhightprice; //分钟行情最高价
    public double mlowprice; //分钟行情最低价
    public double mbeginprice; //分钟行情开始价
    public double mendprice; //分钟行情结束价
    public long tickknockqty; //本次成交数量
    public long knocktime;//成交时间

    // 交易类型
    public String type;

    //期货行情属性
    public String f_productid;
    public String settlegrp;
    public long settleid;
    public String f_productclass;
    public String productgrp;
    public String productcode;

    public String stkstatus;
    public String basicexchid;
    public String basicstkid;
    public int contracttimes;
    public String deliverytype;
    public int deliveryyear;
    public int deliverymonth;
    public long listdate;
    public long firsttrddate;
    public long lasttrddate;
    public long maturedate;
    public long lastsettledate;
    public long deliverydate;
    public String stkorderstatus;

    public int maxlimitorderqty = 0;
    public int minlimitorderqty = 0;
    public int maxmarketorderqty = 0;
    public int minmarketorderqty = 0;

    public double uppercent = 0;
    public double downpercent = 0;

    public double presettlementprice;
    public double precloseprice;
    public int preopenposition = 0;

    public double highestprice;
    public double lowestprice;
    public long exchtotalknockqty = 0;
    public double exchtotalknockamt = 0;
    public int openposition = 0;

    public double settlementprice;
    public double predelta;
    public double delta;
    public long lastmodifytime;
    public int mseconds;
    public long lastmodifytimeold;
    public String isincode;
    public String isinname;

    // 行情信息, 非数据库存储
    public double buy1;
    public String buyamt1;
    public double sell1;
    public String sellamt1;

    public double buy2;
    public String buyamt2;
    public double sell2;
    public String sellamt2;

    public double buy3;
    public String buyamt3;
    public double sell3;
    public String sellamt3;

    public double buy4;
    public String buyamt4;
    public double sell4;
    public String sellamt4;

    public double buy5;
    public String buyamt5;
    public double sell5;
    public String sellamt5;

    //结算价预计
    public double estsettlementprice;

    //每手数量 
    public int qtyperhand;

    //交易单位
    public int tradeunit;

    public String stktype;

    public int deliveryunit;

    //开始价
    public double beginprice;

    //结束价
    public double endprice;
    
    // 序号
    public int sn;
    
    //收到行情时间，格式yyyymmddhhmmssfff
    public long quotreceivetime;

    // 科创板 买入时最小数量 买入最小200股 可以买入 200 + n * minbuyqtytimes
    public int minbuyqtytimes = 1;
}
