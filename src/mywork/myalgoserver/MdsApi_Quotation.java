package mywork.myalgoserver;


/**
 * ֤ȯ������Ϣ
 */
public class MdsApi_Quotation {
	public String qsource;
    /**
     * ���ܲ�����
     */
    public String uuid;
    private long apitime[] = new long[2];//api��ʼ�ͽ���ʱ��
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
     * �г�
     */
    public String exchid;
    /**
     * ֤ȯ����
     */
    public String stkid;
    /**
     * ֤ȯ����
     */
    public String stkname;
    /**
     * ����ʱ�䣬��ʽhhmmss 
     */
    public long occurtime;
    /**
     * ��1-10�۸�
     */
    public double buyprice[] = new double[10];
    /**
     * ��1-10�۸�
     */
    public double sellprice[] = new double[10];
    /**
     * ��1-10����
     */
    public long buyqty[] = new long[10];
    /**
     * ��1-10����
     */
    public long sellqty[] = new long[10];
    /**
     * �ɽ��۸�
     */
    public double knockprice;
    /**
     * �ɽ�����
     */
    public long knockqty;
    /**
     * ������
     */
    public long totalbuyqty;
    /**
     * ������
     */
    public long totalsellqty;
    /**
     * ��Ȩƽ�����
     */
    public double buyavgprice;
    /**
     * ��Ȩƽ������
     */
    public double sellavgprice;
    /**
     * �������
     */
    public long totalbuyqtydiff;
    /**
     * ��������
     */
    public long totalsellqtydiff;
    /**
     * ί��
     */
    public long volumediff;
    /**
     * ί��
     */
    public double volumerate;
    /**
     * ��С�����۲�
     */
    public double diffprice;
    /**
     * �ǵ���
     */
    public double diffrate;
    /**
     * iopv ��ֵ��ֵ
     */
    public double iopv;
    /**
     * �ɽ�����
     */
    public long tradescount;
    /**
     *  ���콻�����ܳɽ�����
     */
    public long exchtotalknockqty;
    /**
     * �ɽ����
     */
    public double knockamt;
    /**
     * ���콻�����ܳɽ����
     */
    public double exchtotalknockamt;
    /**
     * ����
     */
    public long innervolume;
    /**
     * ����
     */
    public long outervolume;
    /**
     * ������
     */
    public long notdefinevolume;
    /**
     *  ��������
     */
    public String tradeproperties;
    /**
     * ����������߼�
     */
    public double highprice;
    /**
     * ����������ͼ�
     */
    public double lowprice;
    /**
     * �г��ֲ���
     */
    public int openposition;
    /**
     * �ֲ�
     */
    public long positdiff;
    /**
     * ��Ȩƽ�����
     */
    public double buyautocostprice;
    /**
     * ��Ȩƽ������
     */
    public double sellautocostprice;
    /**
     * �ɽ�ʱ��
     */
    public long knocktime;
    /**
     * ��Ȩƽ����(5λС��)
     */
    public double knockavgprice;
    /**
     * ��ӯ��
     */
    public double priceearningratio;
    /**
     * �о���
     */
    public double pbv;
    /**
     * ������
     */
    public double turnoverrate;
    /**
     * ���
     */
    public double dailyamplituderate;
    /**
     * ����ֵ
     */
    public double capitalization;
    /**
     * ԭʼ�ɽ��۸�
     */
    public double oriknockprice;
    /**
     * �յ�����ʱ�䣬��ʽyyyymmddhhmmssfff
     */
    public long quotreceivetime;
    /**
     *   ��Ȩ��Լ״̬
     */
    public String tradingphasestatus;
    /**
     * �г�״̬��Ϣ
     */
    public String stkorderstatus;

    /**
     * ���캯��, exchid, stkid����һֻ��Ʊ
     */
    //trade conditional code
    public String tradeconditionalcode;
    public double iap = 0; //����ɽ��۸�
    public long iav = 0;//����ɽ�����
    public double lasttradeprice = 0; //�ɽ��۸�
    public long lasttradeqty = 0;//�ɽ�����
    // ��һί�ж��� 50
    public int[] buyorders;

    // ��һί�ж��� 50
    public int[] sellorders;
}
