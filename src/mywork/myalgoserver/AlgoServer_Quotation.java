package mywork.myalgoserver;


/**
 * �ֻ����ڻ��������
 * @author Administrator
 *
 */
public class AlgoServer_Quotation {

    //�ֻ����ڻ���������
    public long occurtime = -1; //ʱ��(��Ϊ��ʷ�����е�ʱ���)
    public String exchid; //�г�
    public String stkid; //֤ȯ����
    public String stkname; //֤ȯ����
    public double closeprice; //�������̼�
    public double openprice; //���տ��̼�
    public double newprice; //���¼�
    public double highprice; //��߼�
    public double lowprice; //��ͼ�
    public double maxorderprice; //ί�м۸����� 
    public double minorderprice; //ί�м۸����� 
    public String closeflag = ""; //ͣ�Ʊ�־
    public double orderpriceunit;//�۸�λ
    public int lowbuyqtylimit; // ������������
    public int lowsellqtylimit; // ������������
    public int highbuyqtylimit;// ������������
    public int highsellqtylimit;// ������������
    
    //�ֻ���������
    public long knockqty; //�ɽ�����
    public double knockmoney; //�ɽ����
    public double sellprice1; //����һ
    public double sellprice2; //���۶�
    public double sellprice3; //������
    public double sellprice4; //������
    public double sellprice5; //������
    public double buyprice1; //���һ
    public double buyprice2; //��۶�
    public double buyprice3; //�����
    public double buyprice4; //�����
    public double buyprice5; //�����
    public long sellqty1; //����һ
    public long sellqty2; //������
    public long sellqty3; //������
    public long sellqty4; //������
    public long sellqty5; //������
    public long buyqty1; //����һ
    public long buyqty2; //������
    public long buyqty3; //������
    public long buyqty4; //������
    public long buyqty5; //������
    public double change; //�۸�䶯
    public double changepecent; //�۸�䶯��
    public String stkidprefix; //֤ȯ���ǰ׺
    public String tradetimeflag; //��Ʒ���׽׶�
    public String pausetradestatus; //��ͣ���ױ�־
    public String passcreditcashstk; //���ʽ���״̬
    public String passcreditsharestk; //��ȯ����״̬
    public String otherbusinessmark; //����ҵ��״̬
    public String changetime; //������ʱ��

    public double mhightprice; //����������߼�
    public double mlowprice; //����������ͼ�
    public double mbeginprice; //�������鿪ʼ��
    public double mendprice; //�������������
    public long tickknockqty; //���γɽ�����
    public long knocktime;//�ɽ�ʱ��

    // ��������
    public String type;

    //�ڻ���������
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

    // ������Ϣ, �����ݿ�洢
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

    //�����Ԥ��
    public double estsettlementprice;

    //ÿ������ 
    public int qtyperhand;

    //���׵�λ
    public int tradeunit;

    public String stktype;

    public int deliveryunit;

    //��ʼ��
    public double beginprice;

    //������
    public double endprice;
    
    // ���
    public int sn;
    
    //�յ�����ʱ�䣬��ʽyyyymmddhhmmssfff
    public long quotreceivetime;

    // �ƴ��� ����ʱ��С���� ������С200�� �������� 200 + n * minbuyqtytimes
    public int minbuyqtytimes = 1;
}
