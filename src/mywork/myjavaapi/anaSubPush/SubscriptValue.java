package mywork.myjavaapi.anaSubPush;

/**
 * 2020-01-03 09:30:54.253->MessageId = ID:RC7-ZYJY-APP3-14422-1578003332223-1:1:1:21:1
¡¾Message pros¡¿
  senderSubId='010000510088'
  clientSerialNum='450013'
  routerId='CATS_S_RID'
  clientId='all'
  acctId='010000510088'
¡¾Message data¡¿
  clientId=0 serialNum=103070302000003475 state=0 time=2020-01-03 09:30:54.251
  head: type=0, len=419, MAC="            ", checkSum="0", flags=0, serial="0", funcCode="Q0800030"
  request:
  response:
    field_0:  recordcnt=1,  compressflag=1,  
    field_1:  exchid="1",  stkid="002315",  stkname="½¹µã¿Æ¼¼",  knockqty=0,  contractnum="61002160",  totalwithdrawqty=0,  orderqty=100,  serialnum=803092,  acctid="010000510088",  regid="0899046205",  knockprice=0.0,  knockamt=0.0,  knocktime="0",  ordertype="0S",  stktype="A0",  tradetype="A0",  orderprice=22.66,  reckoningamt=0.0,  fullknockamt=0.0,  accuredinterestamt=0.0,  accuredinterest=0.0,  excherrorcode="1",  returnflag=0,  occurtime="20200103093051",  batchnum=-1,  forderserialnum=803092,  closepnl=0.0,  openusedmarginamt=0.0,  offsetmarginamt=0.0,  ordersource=0,  ordersourceid=0,  premium=0.0,  commision=0.0,  actionflag="NEW",  tradefrozenamt=0.0,  ordertime="20200103093051",  totalknockqty=0,  totalknockamt=0.0,  appserverip="172.22.137.23",  actiontime=20200103093051,  clientid="tanggeng-tg",  tradedate="0",  marginusedamt=0.0,  completeflag=0,  stksum=0,  clordid="61002160",  optid="83874",  internalbizmark="TS",  instdetailserialnum=-1,  
 * @author admin
 *
 */
public class SubscriptValue extends SubPushValue{
    
    private String routerId;
    private String clientId;
    
    
    public SubscriptValue() {
        super();
    }

    public SubscriptValue(SubPushValue spVal, String routerId, String clientId) {
        super(spVal);
        this.routerId = routerId;
        this.clientId = clientId;
    }
    
    public String getRouterId() {
        return routerId;
    }
    public void setRouterId(String routerId) {
        this.routerId = routerId;
    }
    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    @Override
    public String toString() {
        return "SubscriptValue [routerId=" + routerId + ", clientId=" + clientId + "]";
    }
}
