package mywork.myjavaapi.anaSubPush;

/**
 * 2019-12-25 10:26:04->MessageId = ID:WIN-6NNPR76FF91-55110-1577233652085-1:1:1:9:3451
【Message pros】
  clientId='ALL'
  routerId='python'
  acctId='100000000515'
  clientSerialNum='154718'
  senderSubId='1036185258721'
【Message data】
  clientId=0 serialNum=1225082717002273917 state=0 time=2019-12-25 10:26:04.931
  head: type=0, len=471, MAC="            ", checkSum="0", flags=0, serial="0", funcCode="Q0800030"
  request:
    field_0:  recordcnt=1,  compressflag=1,  
    field_1:  exchid="0",  stkid="510330",  stkname="华夏300",  knockqty=150000,  contractnum="9000026810",  totalwithdrawqty=150000,  orderqty=150000,  serialnum=171949,  acctid="100000000515",  regid="D899900376",  knockprice=4.055,  knockamt=608250.0,  knocktime="20191225102605",  knockcode="9000026810",  ordertype="B",  stktype="A8",  tradetype="A0",  orderprice=4.055,  reckoningamt=608277.37,  fullknockamt=608250.0,  accuredinterestamt=0.0,  accuredinterest=0.0,  excherrorcode="0",  memo="委托合法",  tradingresulttype="101",  returnflag=3,  occurtime="20191225102541",  batchnum=-1,  forderserialnum=171910,  closepnl=0.0,  openusedmarginamt=0.0,  offsetmarginamt=0.0,  ordersource=0,  ordersourceid=0,  premium=0.0,  commision=0.0,  actionflag="NEW",  tradefrozenamt=0.0,  ordertime="20191225102541",  totalknockqty=0,  totalknockamt=0.0,  appserverip="10.36.18.91",  actiontime=20191225102605,  tradedate="0",  marginusedamt=0.0,  completeflag=0,  stksum=0,  optid="10103",  internalbizmark="TB",  instdetailserialnum=-1,  
 *
 */

public class SubPushValue {
    
    private String acctId;
    private String clientSerialNum;
    private String senderSubId;
    private String messageData;
    
    public SubPushValue() {
        super();
        // TODO Auto-generated constructor stub
    }

    public SubPushValue(SubPushValue spVal) {
        super();
        this.acctId = spVal.getAcctId();
        this.clientSerialNum = spVal.getClientSerialNum();
        this.senderSubId = spVal.getSenderSubId();
        this.messageData = spVal.getMessageData();
    }
    
    public SubPushValue(String acctId, String clientSerialNum, String senderSubId,
        String messageData) {
        super();
        this.acctId = acctId;
        this.clientSerialNum = clientSerialNum;
        this.senderSubId = senderSubId;
        this.messageData = messageData;
    }
    
    public String getAcctId() {
        return acctId;
    }
    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }
    public String getClientSerialNum() {
        return clientSerialNum;
    }
    public void setClientSerialNum(String clientSerialNum) {
        this.clientSerialNum = clientSerialNum;
    }
    public String getSenderSubId() {
        return senderSubId;
    }
    public void setSenderSubId(String senderSubId) {
        this.senderSubId = senderSubId;
    }
    public String getMessageData() {
        return messageData;
    }
    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }
    @Override
    public String toString() {
        return "SubPushValue [acctId="
            + acctId
            + ", clientSerialNum="
            + clientSerialNum
            + ", senderSubId="
            + senderSubId
            + ", messageData="
            + messageData
            + "]";
    }
}
