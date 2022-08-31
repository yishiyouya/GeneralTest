package myapp.myframework;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * ת���ն���Ϣ������
 *
 */
public class TerminalInfoUtils {

    /**
     *  * 
     */
    private static String PUNC_ASTERISK = "*";
    
    /**
     *  :
     */
    private static String PUNC_COLON = ":";
    
    /**
     *  ^
     */
    private static String PUNC_SHARP_CORNER = "^";
    
    /**
     *  ;
     */
    private static String PUNC_SEMI_COLON = ";";
    
    //custMap  globalConstCust ���� 
    private static Map<String, String> custMap = new HashMap<String, String>();
    
    private static String terminalPara = "TerminalInfoTransferPara";
    
    public static void main(String[] args) throws Exception {
        
        try {
            
            TerminalInfoUtils.getInstance().transTerminalInfo();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    private TerminalInfoUtils(){
        
    }
    
    private static class TerminalInfoUtilsHandler {
        private static TerminalInfoUtils instance = new TerminalInfoUtils();
    }
    
    public static TerminalInfoUtils getInstance(){
        return TerminalInfoUtilsHandler.instance;
    }
    
    public void transTerminalInfo() throws Exception {
        
        //��ʼ�� globalConstCust�� TerminalInfoTransferPara ����
        
        String transKind = "2";
        //String terTypeCotent = "PC:IIP*^IPORT*^LIP*^MAC*^HD*^PCN^CPU^PI^VOL";
        String terTypeCotent = "PC:IIP*^IPORT*^LIP*^MAC*^HD*^CPU^PI^VOL";
        String connector = ":";
        String separator = ";";
        String extSym = "@";
        String newConnector = "#";
        String newSeparator = "=";
        String mustSupCotent = "NA";
        String terRepCotent = "Unknown:NA^[*]:NA^[*:NA^*]:NA^(*:NA^";
        
        System.out.println("\n ת������:" + transKind +
            "\n �ն����ͼ���ʶ��Ϣ:" + terTypeCotent +
            "\n ���ӷ�:" + connector +
            "\n �ָ���:" + separator +
            "\n ��չ��Ϣ��ʼ����:" + extSym +
            "\n �����ӷ�:" + newConnector +
            "\n �·ָ���:" + newSeparator +
            "\n �ز��������:" + mustSupCotent +
            "\n �ն���Ϣ�滻����:" + terRepCotent);
        if (isTransTerminalInfo(transKind, terTypeCotent, connector, separator, extSym, newConnector, newSeparator, mustSupCotent, terRepCotent)) {
            transTerminalInfo(transKind, 
                connector, separator, extSym, newConnector, 
                newSeparator, terRepCotent, mustSupCotent, 
                terTypeCotent);
            
        }
        
        //ʹ�ú���� globalConstCust�� TerminalInfoTransferPara ����
        if (null != custMap && custMap.size() > 0) {
            custMap.clear();
        }
        
    }
    
    /**
     * ����������������ɺ������жϣ�������� �٢ڢۢܢ� and ( �� or �� or �� or �� )�������������ֻ��ն���Ϣ��ת�������߼���
     * �� globalConstcust���д���GLOBALCONST='TerminalInfoTransferPara'����INTERIORID='ת������'�ļ�¼���Ҹü�¼��CONSTDISPLAYNAMEΪ1��2��
     * �� globalConstcust���д���GLOBALCONST='TerminalInfoTransferPara'����INTERIORID='�ն����ͼ���ʶ��Ϣ'�ļ�¼���Ҹü�¼��CONSTDISPLAYNAME���Խ������ն����ͺͱ�ʶ��Ϣ��
     * �� globalConstcust���д���GLOBALCONST='TerminalInfoTransferPara'����INTERIORID='�ָ���'�ļ�¼���Ҹü�¼��CONSTDISPLAYNAME��Ϊ�գ�ע��ȥ�ո񣩣�
     * �� globalConstcust���д���GLOBALCONST='TerminalInfoTransferPara'����INTERIORID='���ӷ�'�ļ�¼���Ҹü�¼��CONSTDISPLAYNAME��Ϊ�գ�ע��ȥ�ո񣩣�
     * �� globalConstcust���д���GLOBALCONST='TerminalInfoTransferPara'����INTERIORID='��չ��Ϣ��ʼ����'�ļ�¼���Ҹü�¼��CONSTDISPLAYNAME��Ϊ�գ�ע��ȥ�ո񣩣�
     * �� globalConstcust���д���GLOBALCONST='TerminalInfoTransferPara'����INTERIORID='�·ָ���'�ļ�¼���Ҹü�¼��CONSTDISPLAYNAME��Ϊ�գ�ע��ȥ�ո񣩣�
     * �� globalConstcust���д���GLOBALCONST='TerminalInfoTransferPara'����INTERIORID='�����ӷ�'�ļ�¼���Ҹü�¼��CONSTDISPLAYNAME��Ϊ�գ�ע��ȥ�ո񣩣�
     * �� globalConstcust���д���GLOBALCONST='TerminalInfoTransferPara'����INTERIORID='�ز��������'�ļ�¼���Ҹü�¼��CONSTDISPLAYNAME��Ϊ�գ�ע��ȥ�ո񣩣�
     * �� globalConstcust���д���GLOBALCONST='TerminalInfoTransferPara'����INTERIORID='�ն���Ϣ�滻����'�ļ�¼���Ҹü�¼��CONSTDISPLAYNAME��Ϊ�գ�ע��ȥ�ո񣩣�
     * @throws Exception 
     */
    public boolean isTransTerminalInfo(String transKind, String terTypeCotent, String connector, 
        String separator, String extSym, String newConnector, String newSeparator, 
        String mustSupCotent, String terRepCotent) throws Exception {
        boolean doTrans = false;
        boolean terTypeAndInfoCheck = false;
        
        if (isValidPara(terTypeCotent)) {
            String[] terTypeAndInfoArr = terTypeCotent.split(":");
            if (isValidPara(terTypeCotent)) {
                if (null != terTypeAndInfoArr
                        && terTypeAndInfoArr.length > 1
                        && terTypeAndInfoArr[0].length() > 0
                        && terTypeAndInfoArr[1].length() > 0) {
                    terTypeAndInfoCheck = true;
                }
            }
        }
        if (("1".equals(transKind) || "2".equals(transKind))
            && terTypeAndInfoCheck
            && isValidPara(connector)
            && isValidPara(separator)
            && isValidPara(extSym)
            && (isValidPara(newSeparator)
                || isValidPara(newConnector)
                || isValidPara(mustSupCotent)
                || isValidPara(terRepCotent))) {
            doTrans = true;
        }
        return doTrans;
    }
    
    /**
     * �ն���Ϣת���߼�
     * @return
     * @throws SQLException 
     * @throws AppException 
     */
    public void transTerminalInfo(String transKind, 
        String connector, String separator, String extSym, String newConnector, 
        String newSeparator, String terRepCotent, String mustSupCotent, 
        String terTypeCotent) {
        try {
            //1.��ȡ��ǰ�������ն���Ϣ
            List<TerminalInfoHis> terminalList = new ArrayList<TerminalInfoHis>();
            terminalList = getTerminalInfoByDate();
            
            String terminalInfo = "";
            String oldTerminalInfo = "";
            if (null != terminalList && terminalList.size() > 0) {
                for (TerminalInfoHis terminalInfoHis : terminalList) {
                    try {
                        //0.ÿ�����»�ȡ������Ϣ
                        transKind = "1";
                        terTypeCotent = "PC:IIP*^LIP*^MAC*^IPORT*^HD*^PCN^CPU^PI^VOL";
                        connector = ":";
                        separator = ";";
                        extSym = "@";
                        newConnector = "=";
                        newSeparator = "";
                        mustSupCotent = "NA";
                        terRepCotent = "";
                        
                        terminalInfo = terminalInfoHis.getTerminalInfo();
                        if (isValidPara(terminalInfo)) {
                            String newTerminalInfo = terminalInfo;
                            //1.���� ��ת�����ࡱ�� ת���ն���Ϣ
                            /*
                             * |MAC=1831BF4BC3AB
                             * |LIP=192.168.118.107
                             * |insideInfo=PC;IIP:NA;LIP:192.168.118.107;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL;PCN:XXJSB-ZHUANGSHANG;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4
                             * |outsideInfo=PC;IIP:NA;LIP:192.168.4.140;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL;PCN:ZENGJIANHUA;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4
             
                             * ת������Ϊ1����ת������Ϊ��PC;IIP:NA;LIP:192.168.118.107;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL;PCN:XXJSB-ZHUANGSHANG;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4
                             * ת������Ϊ2����ת������Ϊ��PC;IIP:NA;LIP:192.168.4.140;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL;PCN:ZENGJIANHUA;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4 
                             */
                            int idx = 0;
                            String sepStr = "";
                            if ("1".equals(transKind)) {
                                sepStr = "insideInfo";
                            } else if ("2".equals(transKind)) {
                                sepStr = "outsideInfo";
                            }
                            
                            idx = terminalInfo.indexOf(sepStr);
                            int aIdx = terminalInfo.indexOf("|", idx) < 0 ? terminalInfo.length() : terminalInfo.indexOf("|", idx);
                            
                            if (idx > -1 && aIdx > (idx + sepStr.length() + 1) && sepStr.length() > 0) {
                                
                                newTerminalInfo =
                                        terminalInfo.substring(idx + sepStr.length() + 1, aIdx);
                                
                                System.out.println("\n ==== 2. newTerminalInfo: " + newTerminalInfo);
                                /*
                                 * 2.���ת��������ն����Ͳ��ڡ��ն����͡��У���ת���ü�¼�����������
                                 * �ն����͵Ļ�ȡ�߼������ݡ��ָ��������ָ�ת�����󣬵�1�����ַ������ն����ͣ�
                                 * �ж��ն���Ϣ�Ƿ��ԡ��ն����ͼ���ʶ��Ϣ����ǰ��ͷ
                                 * terTypeCotent
                                 * PC:IIP*^IPORT*^LIP*^MAC*^HD*^PCN^CPU^PI^VOL;OH:IIP*^IPORT*^LIP*^MAC*^HD*^PCN^CPU^PI^VOL 
                                 */
                                String[] transedTypeArr = existTransedType(terTypeCotent, newTerminalInfo);
                                String transedType = transedTypeArr[0];
                                System.out.println("\n ==== 2." + transedTypeArr[0] + " " + transedTypeArr[1]);
                                if (isNull(transedType)) {
                                    continue;
                                } else {
                                    terTypeCotent = existTransedType(terTypeCotent, newTerminalInfo)[1];
                                    //ת��ǰ���֣�PC;IIP:NA;LIP:192.168.4.140;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL;PCN:ZENGJIANHUA;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4
                                    oldTerminalInfo = newTerminalInfo;
                                }
                                
                                /*
                                 * 2-6-1. ������� @����ȡ�������׷�ӵ�ĩβ
                                 */
                                String origNewTerminalInfo = newTerminalInfo;
                                String extendInfo = ""; 
                                if (isValidPara(extSym) && newTerminalInfo.indexOf(extSym) > -1) {
                                    extendInfo = newTerminalInfo.substring(newTerminalInfo.indexOf(extSym) + extSym.length(), newTerminalInfo.length());
                                    newTerminalInfo = newTerminalInfo.substring(0, newTerminalInfo.indexOf(extSym));
                                    System.out.println("\n ===== 2-6-1: extendInfo��" + extendInfo);
                                    System.out.println("\n ===== 2-6-1: newTerminalInfo��" + newTerminalInfo);
                                }
                                
                                /*
                                 * 2-3-1.����terTypeCotent��ȥ����ͷPC:, ��Ϊ��PC;��ͷ
                                 */
                                if (terTypeCotent.indexOf(":") > -1) {
                                    terTypeCotent = terTypeCotent.replace(":", separator);
                                }
                                
                                /*
                                 * 2-4-2.����terTypeCotent��ȥ����ͷPC:
                                 */
                                if (terTypeCotent.indexOf(separator) > -1) {
                                    terTypeCotent = terTypeCotent.substring(terTypeCotent.indexOf(separator)+separator.length(), terTypeCotent.length());
                                }
                                
                                /*
                                 * 2-3-2. ת���ն���Ϣ�Ƿ����newConnector newSeparator
                                 */
                                System.out.println("\n ==== 2-3. newTerminalInfo: "+newTerminalInfo);
                                newTerminalInfo = transSepCon(
                                    newTerminalInfo,
                                    terTypeCotent,
                                    connector,
                                    newConnector,
                                    separator,
                                    newSeparator
                                    );
                                
                                /*
                                 * 2-4-3-start. ����separator, ͷ��ȥ��PC+separator
                                 */
                                if (isValidPara(separator)) {
                                    newTerminalInfo = newTerminalInfo.substring(newTerminalInfo.indexOf(separator)+separator.length(), newTerminalInfo.length());
                                }
                                
                                /*
                                 * 2-4-4-start ��ȡ������û�еģ��ն���Ϣ�еĶ�������
                                 */
                                System.out.println("\n =====2-4-4��" + newTerminalInfo);
                                Map<String, String> termInfoMap = new HashMap<String, String>();
                                String notExistConfig = getNotExistConfig(termInfoMap,
                                    mustSupCotent,
                                    extSym,
                                    newTerminalInfo,
                                    separator,
                                    connector,
                                    terTypeCotent,
                                    transedType);
                                System.out.println("\n =====2-4-4��" + termInfoMap);
                                
                                /*
                                 * 3.
                                 * ������ն���Ϣ�滻���ݡ���Ϊ�գ�ע��ȥ�ո񣬽����ɶ�������жϣ���
                                 * ���ж�ת�������е�ÿ�������ն���Ϣ�Ƿ��롾�滻ǰ���ݡ�һ�£��������ͨ���*������Ҫ֧��ģ��ƥ�䣩��
                                 * ��һ�£�����Ҫ�滻�ɡ��滻�����ݡ��������ն���Ϣ�Ļ�ȡ�߼���
                                 * �� �����ӷ����ͺ�����1�����ָ�����֮������ݣ�
                                 * �� ��������ӷ�������û�С��ָ�������ȡ�á����ӷ����ͺ�����1������չ��Ϣ��ʼ���š�֮������ݣ�
                                 * �� ��������ӷ�������û�С��ָ������͡���չ��Ϣ��ʼ���š�����ȡ�á����ӷ���������������ݣ�
                                 */
                                System.out.println("\n ==== 3. " + newTerminalInfo);
                                newTerminalInfo = transTerRepCotent(termInfoMap, extSym, newTerminalInfo
                                                    , terRepCotent, separator, connector);
                                
                                /*
                                 * 4. ������ز�������ݡ���Ϊ�գ�ע��ȥ�ո񣩣�����ݡ��ָ������͡����ӷ��������ƴ���ϱ�ʶΪ�ز���ġ���ʶ��Ϣ������Ҫȥ��*�ţ����磺';IIP:'�����⡾�ն����ͼ���ʶ��Ϣ���������ö��飬Ӧȡ��ǰת�������Ӧ����һ�飩���ж�ת���������Ƿ���ڸ��ַ�����
                                 * �� ��������ڣ�����ݡ���ʶ��Ϣ���и�����ʶ��Ϣ��˳���ڶ�Ӧλ�ò����ϡ��ָ�����������ʶ��Ϣ���������ӷ��������ز�������ݡ������磺;IIP=NA���������õġ���ʶ��Ϣ��˳������뱻ת�����ն���Ϣ˳��һ�£���˲���λ�ð������߼�ȷ�ϣ�
                                 *      a) ���������ı�ʶ��ϢΪ��1������ֱ�Ӳ��䵽��1��λ�ã�
                                 *      b) �����ڱ�ת�����ն���Ϣ�в���ǰ1����ʶ��Ϣ��λ�ã�����ܹ��鵽���򲹳䵽������λ�ã���������ǰȡ1����ʶ��Ϣ���²��ң����δ���ֱ���ҵ�Ϊֹ�����ǰ��ı�ʶ��Ϣ��δ���ҵ�����ֱ�Ӳ��䵽��1λ��
                                 * �� ������ڡ��ң���һ���ַ�Ϊ���ָ��������ߡ���չ��Ϣ��ʼ���š������ߺ������ַ�������ֱ���ں��油���ϡ��ز�������ݡ���
                                 */
                                System.out.println("\n ===== 4: " + newTerminalInfo);
                                newTerminalInfo = transMustSupCotent(termInfoMap, mustSupCotent, newTerminalInfo
                                                    ,separator, connector, terTypeCotent);
                                /*
                                 * 2-4-3-end.ͷ����PC+separator
                                 */
                                newTerminalInfo = transedType + separator + newTerminalInfo;
                                
                                /*
                                 * 5. ����������ӷ�����Ϊ�գ�ע��ȥ�ո񣩣�����ݡ��ָ������͡����ӷ��������ƴ���ϡ���ʶ��Ϣ��������Ǳز����Ҫȥ��*�ţ����磺';IIP:'��
                                 * ���⡾�ն����ͼ���ʶ��Ϣ���������ö��飬Ӧȡ��ǰת�������Ӧ����һ�飩���ж�ת���������Ƿ���ڸ��ַ�����
                                 * ������ڣ������еġ����ӷ����滻Ϊ�������ӷ�����
                                 */
                                System.out.println("\n ===== 5: " + newTerminalInfo);
                                newTerminalInfo = transConnector(newTerminalInfo, terTypeCotent, separator, connector, newConnector);
                                
                                /*
                                 * 6. ������·ָ�������Ϊ�գ�ע��ȥ�ո񣩣�����ݡ��ָ����������ƴ���ϡ���ʶ��Ϣ��������Ǳز����Ҫȥ��*�ţ����磺';IIP'��
                                 * ���⡾�ն����ͼ���ʶ��Ϣ���������ö��飬Ӧȡ��ǰת�������Ӧ����һ�飩���ж�ת���������Ƿ���ڸ��ַ�����
                                 * ������ڣ������еġ��ָ������滻Ϊ���·ָ�������
                                 */
                                System.out.println("\n ===== 6: " + newTerminalInfo);
                                newTerminalInfo = transSeparator(newTerminalInfo, terTypeCotent, separator, newSeparator);
                                
                                /*
                                 * 2-6-0-end ��ȡ������û�еģ��ն���Ϣ�еĶ�������
                                 */
                                System.out.println("\n ===== 2-6-0: " + notExistConfig);
                                if (isValidPara(notExistConfig)) {
                                    newTerminalInfo += notExistConfig;
                                }
                                
                                /*
                                 * 2-6-1.ƴ��@��֮����չ��Ϣ
                                 */
                                if (isValidPara(extendInfo) && origNewTerminalInfo.endsWith(extSym + extendInfo)) {
                                    if (newTerminalInfo.endsWith(extSym)) {
                                        newTerminalInfo = newTerminalInfo + extendInfo;
                                    }else{
                                        newTerminalInfo = newTerminalInfo + extSym + extendInfo;
                                    }
                                }
                                
                                /*
                                 * 7. �����ֻ��ն���Ϣ����ת���ģ�����Ҫ��ת��ǰ���ֻ��ն���Ϣд�뵽��Ӧ��¼��origTerminalInfo�ֶ��ϡ�
                                 */
                                System.out.println("\n ===== 7: " + newTerminalInfo);
                                syncTerminalInfo(terminalInfo, newTerminalInfo, terminalInfoHis, oldTerminalInfo);
                                
                            } else {
                                System.out.println("ת��������ն����Ͳ��ڡ��ն����͡��У���ת���ü�¼");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(terminalInfoHis.toString() + e);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("transTerminalInfo �ն���Ϣת�� error: " + e);
        }
    }
    
    /**
     * �ж��ն���Ϣ�Ƿ��ԡ��ն����ͼ���ʶ��Ϣ����ǰ��ͷ
     * terTypeCotent
     * PC:IIP*^IPORT*^LIP*^MAC*^HD*^PCN^CPU^PI^VOL;OH:IIP*^IPORT*^LIP*^MAC*^HD*^PCN^CPU^PI^VOL 
     * @param terTypeCotent
     * @param transedType
     * @param newTerminalInfo
     * @return
     */
    public String[] existTransedType(String terTypeCotent, String newTerminalInfo) {
        String[] terType = new String[2];
        String transResType = "";
        String transedType = "";
        boolean containType = false;
        StringTokenizer st = new StringTokenizer(terTypeCotent.trim(), ";");
        while (st.hasMoreTokens()) {
            String ct = st.nextToken();
            if (ct.indexOf(":") > -1) {
                transedType = ct.substring(0, ct.indexOf(":"));
                if (isValidPara(transedType) && newTerminalInfo.startsWith(transedType)) {
                    containType = true;
                    terTypeCotent = ct;
                    transResType = transedType;
                    break;
                }
            }
        }
        if (containType) {
            terType[0] = containType ? transResType : "";
            terType[1] = terTypeCotent;
        }
        return terType;
    }
    
    /**
     * 2-3. ת���ն���Ϣ�Ƿ����newConnector newSeparator��������תΪ connector separator
     * @param newTerminalInfo
     * @param terTypeCotent
     * @param connector
     * @param newConnector
     * @param separator
     * @param newSeparator
     * @return
     */
    public String transSepCon(String newTerminalInfo, String terTypeCotent
        ,String connector, String newConnector
        ,String separator, String newSeparator) {
        
        if (isValidPara(newTerminalInfo) && (isValidPara(newConnector))) {
            /*
             * �� ����������ӷ�����Ϊ�գ�ע��ȥ�ո񣩣�����ݡ��ָ������͡����ӷ��������ƴ���ϡ���ʶ��Ϣ��������Ǳز����Ҫȥ��*�ţ����磺';IIP:'��
             * ���⡾�ն����ͼ���ʶ��Ϣ���������ö��飬Ӧȡ��ǰת�������Ӧ����һ�飩���ж�ת���������Ƿ���ڸ��ַ�����
             * ������ڣ������еġ����ӷ����滻Ϊ�������ӷ�����
             */
            if (newTerminalInfo.indexOf(newConnector) > -1) {
                
                newTerminalInfo = transConnector(newTerminalInfo, terTypeCotent, separator, newConnector, connector);
                System.out.println("\n ==== 2-3. connector: "+newTerminalInfo);
            }
        }
        if (isValidPara(newTerminalInfo) && isValidPara(newSeparator)) {
            /*
             * �� ������·ָ�������Ϊ�գ�ע��ȥ�ո񣩣�����ݡ��ָ����������ƴ���ϡ���ʶ��Ϣ��������Ǳز����Ҫȥ��*�ţ����磺';IIP'��
             * ���⡾�ն����ͼ���ʶ��Ϣ���������ö��飬Ӧȡ��ǰת�������Ӧ����һ�飩���ж�ת���������Ƿ���ڸ��ַ�����
             * ������ڣ������еġ��ָ������滻Ϊ���·ָ�������
             */
            if (newTerminalInfo.indexOf(newConnector) > -1) {
                newTerminalInfo = transSeparator(newTerminalInfo, terTypeCotent, newSeparator, separator);
                System.out.println("\n ==== 2-3. separator: "+newTerminalInfo);
            }
        }
        return newTerminalInfo;
    }
    
    /**
     * �·ָ��Ϊ�գ����� �ն���Ϣ�����·ָ��
     * @param newTerminalInfo
     * @param terTypeCotent
     * @param connector
     * @param newConnector
     * @param separator
     * @param newSeparator
     * @return
     */
    public boolean transSeparator(String newTerminalInfo,
        String terTypeCotent,
        String connector,
        String newConnector,
        String separator,
        String newSeparator) {
        
        boolean transSep = true;
        if (isNull(newSeparator) || (isValidPara(separator) && newTerminalInfo
                .indexOf(separator) < 0)) {
            transSep = false;
        }
        return transSep;
    }
    
    /**
     * �����ӷ�Ϊ�գ����� �ն���Ϣ���������ӷ�
     * @param newTerminalInfo
     * @param terTypeCotent
     * @param connector
     * @param newConnector
     * @param separator
     * @param newSeparator
     * @return
     */
    
    public boolean transConnector(String newTerminalInfo,
        String terTypeCotent,
        String connector,
        String newConnector,
        String separator,
        String newSeparator) {
        
        boolean transSep = true;
        if (isNull(newConnector) && (isValidPara(connector) && newTerminalInfo
                        .indexOf(connector) < 0)) {
            transSep = false;
        }
        return transSep;
    }
    
    
    /**
     * 
     * 3. ������ն���Ϣ�滻���ݡ���Ϊ�գ�ע��ȥ�ո񣬽����ɶ�������жϣ���
     * ���ж�ת�������е�ÿ�������ն���Ϣ�Ƿ��롾�滻ǰ���ݡ�һ�£��������ͨ���*������Ҫ֧��ģ��ƥ�䣩��
     * ��һ�£�����Ҫ�滻�ɡ��滻�����ݡ��������ն���Ϣ�Ļ�ȡ�߼���
     * �� �����ӷ����ͺ�����1�����ָ�����֮������ݣ�
     * �� ��������ӷ�������û�С��ָ�������ȡ�á����ӷ����ͺ�����1������չ��Ϣ��ʼ���š�֮������ݣ�
     * �� ��������ӷ�������û�С��ָ������͡���չ��Ϣ��ʼ���š�����ȡ�á����ӷ���������������ݣ�
     * @return
     */
    public String transTerRepCotent(Map<String, String> termInfoMap,
        String extSym, 
        String newTerminalInfo,
        String terRepCotent,
        String separator,
        String connector) {
        if (isValidPara(terRepCotent)) {
            if (isValidPara(separator)) {
                List<String> termList = new ArrayList<String>();
                Map<String, String> termMap = new LinkedHashMap<String, String>();
                for (Entry<String, String> termEntry : termInfoMap.entrySet()) {
                    termMap.put(termEntry.getKey(), termEntry.getValue());
                }
                StringTokenizer st = new StringTokenizer(terRepCotent.trim(), "^");
                while (st.hasMoreTokens()) {
                    String ct = st.nextToken();
                    if (ct.indexOf(":") > -1) {
                        String before = ct.substring(0, ct.indexOf(":"));
                        String after = ct.substring(ct.indexOf(":")+1, ct.length());
                        for (Entry<String, String> termEntry : termMap.entrySet()) {
                            String termVal = termEntry.getValue();
                            String start = "";
                            String end = "";
                            boolean matched = true;
                            if (before.indexOf("*") > -1) {
                                start = before.substring(0, before.indexOf("*"));
                                end = before.substring(before.indexOf("*") + 1, before.length());
                                if (isValidPara(start)) {
                                    matched = matched && termVal.startsWith(start);
                                }
                                if (isValidPara(end)) {
                                    matched = matched && termVal.endsWith(end);
                                }
                                
                                if (matched) {
                                    termMap.put(termEntry.getKey(), after);
                                    termInfoMap.put(termEntry.getKey(), after);
                                }
                            } else {
                                if (isValidPara(termVal) && termVal.equals(before)) {
                                    termMap.put(termEntry.getKey(), after);
                                    termInfoMap.put(termEntry.getKey(), after);
                                }
                            }
                            
                        }
                    }
                }
                System.out.println("\n ==== 3. termMap�� "+termMap);
                for (Entry<String, String> termEntry : termMap.entrySet()) {
                    if (isValidPara(termEntry.getValue())) {
                        termList.add(termEntry.getKey() + connector + termEntry.getValue());
                    } else {
                        termList.add(termEntry.getKey());
                    }
                }
                System.out.println("\n ==== 3. termList�� "+termList);
                
                newTerminalInfo = String.join(separator, termList);
                if (null != termMap) {
                    termMap.clear();
                }
                if (null != termList) {
                    termList.clear();
                }
            }
        }
        return newTerminalInfo;
    }
    
    /**
     * 2-4-4. �����ڵ����ã���ȡ�������ת�����ӷ����ָ�������ƴ�ӵ��ն���Ϣ����չ��Ϣǰ
     * @param mustSupCotent
     * @param extSym
     * @param newTerminalInfo
     * @param separator
     * @param connector
     * @param terTypeCotent
     * @param transedType
     * @return
     */
    public String getNotExistConfig(
        Map<String, String> termInfoMap,
        String mustSupCotent,
        String extSym,
        String newTerminalInfo,
        String separator,
        String connector,
        String terTypeCotent,
        String transedType) {
        
        String notExistConfig = "";
        List<String> termList = new ArrayList<String>();
        Map<String, String> termMap = new LinkedHashMap<String, String>();
        //�ն���Ϣ����
        Map<String, String> termTempMap = new LinkedHashMap<String, String>();

        /*
        * ��� @123��ĩβ���滻��������ĩβ
        * PC;CPU:234;IIP:192.168.3.254;LIP:192.168.3.27;MAC:74D435BFA458;HD:@123
        * PC;IIP:192.168.3.254;IPORT:NA;LIP:192.168.3.27;MAC:74D435BFA458;HD:NA;CPU:234@123
        */
        if (isValidPara(separator)) {
            String key = "";
            String value = "";
            StringTokenizer termSt = new StringTokenizer(newTerminalInfo, separator);
            while (termSt.hasMoreTokens()) {
                String term = termSt.nextToken();
                termList.add(term);
                if (term.lastIndexOf(connector) > -1) {
                    key = term.substring(0, term.lastIndexOf(connector));
                    value = term.substring(
                        term.lastIndexOf(connector) + connector.length(),
                        term.length());
                    
                } else {
                    key = term;
                    value = "";
                }
                termTempMap.put(key, value);
                
                termInfoMap.put(key, value);
            }
            
            //PC:IIP*^IPORT*^LIP*^MAC*^HD*^PCN^CPU^PI^VOL
            //PC;IIP:NA;LIP:192.168.4.140;MAC:1831BF4BC3AB;HD@TF655AY91GHRVL;PCN:ZENGJIANHUA;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4
            StringTokenizer st = new StringTokenizer(terTypeCotent.trim(), PUNC_SHARP_CORNER);
            while (st.hasMoreTokens()) {
                String ct = st.nextToken();
                if (ct.indexOf(PUNC_ASTERISK) > -1) {
                    ct = ct.replace(PUNC_ASTERISK, "");
                }
                if (termTempMap.containsKey(ct)) {
                    termTempMap.remove(ct);
                }
            }
            //�������ӷ��������ӷ�����δ���õ�ѡ��
            for (Entry<String, String> entryTerm : termTempMap.entrySet()) {
                notExistConfig += separator + entryTerm.getKey();
                if (isValidPara(entryTerm.getKey())) {
                    notExistConfig += connector + entryTerm.getValue();
                }
            }
            System.out.println("\n ===== 2-4-4. " + termList);
            System.out.println("\n ===== 2-4-4. " + termMap);
            System.out.println("\n ===== 2-4-4. " + termInfoMap);
            System.out.println("\n ===== 2-4-4. " + notExistConfig);
        }
        if (null != termMap) {
            termMap.clear();
        }
        if (null != termList) {
            termList.clear();
        }
        return notExistConfig;
    }
    
    /**
     * 
     * 4. ������ز�������ݡ���Ϊ�գ�ע��ȥ�ո񣩣�����ݡ��ָ������͡����ӷ��������ƴ���ϱ�ʶΪ�ز���ġ���ʶ��Ϣ������Ҫȥ��*�ţ����磺';IIP:'�����⡾�ն����ͼ���ʶ��Ϣ���������ö��飬Ӧȡ��ǰת�������Ӧ����һ�飩���ж�ת���������Ƿ���ڸ��ַ�����
     * �� ��������ڣ�����ݡ���ʶ��Ϣ���и�����ʶ��Ϣ��˳���ڶ�Ӧλ�ò����ϡ��ָ�����������ʶ��Ϣ���������ӷ��������ز�������ݡ������磺;IIP=NA���������õġ���ʶ��Ϣ��˳������뱻ת�����ն���Ϣ˳��һ�£���˲���λ�ð������߼�ȷ�ϣ�
     *      a) ���������ı�ʶ��ϢΪ��1������ֱ�Ӳ��䵽��1��λ�ã�
     *      b) �����ڱ�ת�����ն���Ϣ�в���ǰ1����ʶ��Ϣ��λ�ã�����ܹ��鵽���򲹳䵽������λ�ã���������ǰȡ1����ʶ��Ϣ���²��ң����δ���ֱ���ҵ�Ϊֹ�����ǰ��ı�ʶ��Ϣ��δ���ҵ�����ֱ�Ӳ��䵽��1λ��
     * �� ������ڡ��ң���һ���ַ�Ϊ���ָ��������ߡ���չ��Ϣ��ʼ���š������ߺ������ַ�������ֱ���ں��油���ϡ��ز�������ݡ���
     * @param mustSupCotent
     * @param extSym
     * @param newTerminalInfo
     * @param separator
     * @param connector
     * @param terTypeCotent
     * @param transedType
     */
    public String transMustSupCotent(Map<String, String> termInfoMap,
        String mustSupCotent,
        String newTerminalInfo,
        String separator,
        String connector,
        String terTypeCotent) {
        
        List<String> terTypeCotentList = new ArrayList<String>();
        List<String> termFinalList = new ArrayList<String>();
        Map<String, String> termMap = new HashMap<String, String>();
            
        if (isValidPara(separator)) {
            terTypeCotentList = new ArrayList<String>();
            termFinalList = new ArrayList<String>();
            for (Entry<String, String> termEntry : termInfoMap.entrySet()) {
                termMap.put(termEntry.getKey(), termEntry.getValue());
            }
            System.out.println("\n ===== 4: " + termMap);
            //PC:IIP*^IPORT*^LIP*^MAC*^HD*^PCN^CPU^PI^VOL
            //PC;IIP:NA;LIP:192.168.4.140;MAC:1831BF4BC3AB;HD@TF655AY91GHRVL;PCN:ZENGJIANHUA;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4
            StringTokenizer st = new StringTokenizer(terTypeCotent.trim(), "^");
            while (st.hasMoreTokens()) {
                String ct = st.nextToken();
                if (ct.indexOf("*") > -1) {
                    ct = ct.replace("*", "");
                    if (isNull(termMap.get(ct))) {
                        termMap.put(ct, mustSupCotent);
                    }
                }
                terTypeCotentList.add(ct);
            }
            for (int i = 0; i < terTypeCotentList.size(); i++) {
                if (termMap.containsKey(terTypeCotentList.get(i))) {
                    termFinalList.add(terTypeCotentList.get(i)+connector+termMap.get(terTypeCotentList.get(i)));
                }
            }
            newTerminalInfo = String.join(separator, termFinalList);
            System.out.println("\n ===== 4. "+terTypeCotentList);
            System.out.println("\n ===== 4. "+termFinalList);
            System.out.println("\n ===== 4. "+termMap);
            System.out.println("\n ===== 4. "+newTerminalInfo);
        }
        if(null != termMap){
            termMap.clear();    
        }
        if(null != terTypeCotentList){
            terTypeCotentList.clear();  
        }
        if(null != termFinalList){
            termFinalList.clear();  
        }
        return newTerminalInfo;
    }
    
    /**
     * 5. ת�����ӷ�
     * @param newTerminalInfo
     * @param terTypeCotent
     * @param separator
     * @param connector
     * @param newConnector
     */
    public String transConnector(String newTerminalInfo, String terTypeCotent, String separator, String connector, String newConnector) {
        /*
         * �� ����������ӷ�����Ϊ�գ�ע��ȥ�ո񣩣�����ݡ��ָ������͡����ӷ��������ƴ���ϡ���ʶ��Ϣ��������Ǳز����Ҫȥ��*�ţ����磺';IIP:'��
         * ���⡾�ն����ͼ���ʶ��Ϣ���������ö��飬Ӧȡ��ǰת�������Ӧ����һ�飩���ж�ת���������Ƿ���ڸ��ַ�����
         * ������ڣ������еġ����ӷ����滻Ϊ�������ӷ�����
         */
        if (isValidPara(newConnector)) {
            if (newConnector.equals(connector)) {
                return newTerminalInfo;
            }
            StringTokenizer st = new StringTokenizer(terTypeCotent.trim(), "^");
            while (st.hasMoreTokens()) {
                String ct = st.nextToken();
                if (ct.indexOf("*") > -1) {
                    ct = ct.replace("*", "");
                }
                String oldCt = separator + ct + connector;
                String newCt = separator + ct + newConnector;
                if (newTerminalInfo.indexOf(oldCt) > -1) {
                    newTerminalInfo = newTerminalInfo.replace(oldCt, newCt);
                }
            }
        }
        return newTerminalInfo;
    }
    
    /**
     * 6. ת���ָ���
     * @param newTerminalInfo
     * @param terTypeCotent
     * @param separator
     * @param newSeparator
     */
    public String transSeparator(String newTerminalInfo, String terTypeCotent, String separator, String newSeparator) {
        /*
         * �� ������·ָ�������Ϊ�գ�ע��ȥ�ո񣩣�����ݡ��ָ����������ƴ���ϡ���ʶ��Ϣ��������Ǳز����Ҫȥ��*�ţ����磺';IIP'��
         * ���⡾�ն����ͼ���ʶ��Ϣ���������ö��飬Ӧȡ��ǰת�������Ӧ����һ�飩���ж�ת���������Ƿ���ڸ��ַ�����
         * ������ڣ������еġ��ָ������滻Ϊ���·ָ�������
         */
        if (isValidPara(newSeparator)) {
            if (newSeparator.equals(separator)) {
                return newTerminalInfo;
            }
            StringTokenizer st = new StringTokenizer(terTypeCotent.trim(), "^");
            while (st.hasMoreTokens()) {
                String ct = st.nextToken();
                if (ct.indexOf("*") > -1) {
                    ct = ct.replace("*", "");
                }
                String oldCt = separator + ct;
                String newCt = newSeparator + ct;
                if (newTerminalInfo.indexOf(oldCt) > -1) {
                    newTerminalInfo = newTerminalInfo.replace(oldCt, newCt);
                }
            }
        }
        return newTerminalInfo;
    }
    
    /**
     * 7. �����ֻ��ն���Ϣ����ת���ģ�����Ҫ��ת��ǰ���ֻ��ն���Ϣд�뵽��Ӧ��¼��origTerminalInfo�ֶ��ϡ�
     * @param newTerminalInfo
     * @param terTypeCotent
     * @param separator
     * @param newSeparator
     */
    public void syncTerminalInfo(String terminalInfo, String newTerminalInfo, TerminalInfoHis terminalInfoHis, String oldTerminalInfo) {
        
        if (isValidPara(oldTerminalInfo) && !oldTerminalInfo.equals(newTerminalInfo)) {
            //ԭ �ն���Ϣ
            terminalInfoHis.setOrigTerminalInfo(terminalInfo);
            //ת���� �ն���Ϣ
            terminalInfo = terminalInfo.replace(oldTerminalInfo, newTerminalInfo);
            System.out.println("old: " + oldTerminalInfo);
            System.out.println("new: " + newTerminalInfo);
            terminalInfoHis.setTerminalInfo(terminalInfo);
            //���ɾ��
            //updateNewTerminalInfo(transMng, year, terminalInfoHis);
        }
    }

    /**
     * �����ꡢ��һ�����գ�ȡ��ʷ��TERMINALINFO �� terminalInfo
     * @param year
     * @param date
     * @return
     */
    public List<TerminalInfoHis> getTerminalInfoByDate() {
        long occurtime = 0l;
        String terminalId = null;
        String terminalInfo = null;
        List<TerminalInfoHis> terminalList = new ArrayList<TerminalInfoHis>();
//        terminalList.add(new TerminalInfoHis(20200905000000l, "ab", "|MAC=1831BF4BC3AB|LIP=192.168.118.107|insideInfo=PC;IIP:NA;LIP:192.168.118.107;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL;PCN:XXJSB-ZHUANGSHANG;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4|outsideInfo=PC;IIP:192.168.4.254;MAC:FCAA14915DDD;LIP:192.168.4.38;HD:WD-WCC6Y7CJUCLZ;CPU:BFEBFBFF000306C3;VOL:test@23"));
//        terminalList.add(new TerminalInfoHis(20200905000000l, "ab", "|MAC=1831BF4BC3AB|LIP=192.168.118.107|insideInfo=PC;IIP:NA;LIP:192.168.118.107;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL;PCN:XXJSB-ZHUANGSHANG;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4|outsideInfo=PC;IIP:192.168.4.254;MAC:FCAA14915DDD;LIP:192.168.4.38;CPU:BFEBFBFF000306C3;HD:test@23"));
//        terminalList.add(new TerminalInfoHis(20200905000000l, "ab", "|MAC=1831BF4BC3AB|LIP=192.168.118.107|insideInfo=PC;MAC:Unknown;HD:;PCN:prod-188;FOX:[333];ABC:test;CPU:A9060300FFFBEBBF;PI:,EXT3,1756.69;VOL:@python;v109"));
//        //0907
//        terminalList.add(new TerminalInfoHis(20200905000000l, "ab", "|MAC=1831BF4BC3AB|LIP=192.168.118.107|insideInfo=PC;IIP:192.168.4.254;LIP:192.168.4.71;MAC:FCAA14C510E9;HD:WD-WCC6Y1UV9CS7;PCN:LIANGWENXIU;CPU:BFEBFBFF000306C3;PI:C,NTFS,120.00;VOL:C0383181@CTS;V8.8.0.0;LIANGWENXIU��չ��Ϣ"));
//        terminalList.add(new TerminalInfoHis(20200905000000l, "ab", "|MAC=1831BF4BC3AB|LIP=192.168.118.107|insideInfo=PC;IIP:192.168.4.254;IPORT:2000;LIP:192.168.4.114;MAC:FCAA14C51081;HD:WD-WCC6Y1UV9CS7;PCN:LIANGWENXIU;CPU:BFEBFBFF000306C3;PI:C,NTFS,120.00;VOL:C0383181"));
        terminalList.add(new TerminalInfoHis(20200905000000l, "ab", "|MAC=1831BF4BC3AB|LIP=192.168.118.107|insideInfo=null"));
        return terminalList;
    }
    
    /**
     * ��ʷ�ն���Ϣ�ڲ��࣬�����첽DB�õ�
     * @author chin
     *
     */
    public class TerminalInfoHis {
        private long occurTime;
        private String terminalId;
        private String terminalInfo;
        private String origTerminalInfo;
        
        private TerminalInfoHis(long occurTime, String terminalId, String terminalInfo) {
            this.occurTime = occurTime;
            this.terminalId = terminalId;
            this.terminalInfo = terminalInfo;
        }

        public long getOccurTime() {
            return occurTime;
        }

        public void setOccurTime(long occurTime) {
            this.occurTime = occurTime;
        }

        public String getTerminalId() {
            return terminalId;
        }

        public void setTerminalId(String terminalId) {
            this.terminalId = terminalId;
        }

        public String getTerminalInfo() {
            return terminalInfo;
        }

        public void setTerminalInfo(String terminalInfo) {
            this.terminalInfo = terminalInfo;
        }

        public String getOrigTerminalInfo() {
            return origTerminalInfo;
        }

        public void setOrigTerminalInfo(String origTerminalInfo) {
            this.origTerminalInfo = origTerminalInfo;
        }

        
        @Override
        public String toString() {
            return "TerminalInfoHis [occurTime="
                + occurTime
                + ", terminalId="
                + terminalId
                + ", terminalInfo="
                + terminalInfo
                + ", origTerminalInfo="
                + origTerminalInfo
                + "]\n";
        }
        
    }

    public boolean isValidPara(String para){
        if (para == null || para.equals(""))
            return false;
        else
            return true;
    }
    
    public boolean isNull(String para){
        if (para == null || para.equals(""))
            return true;
        else
            return false;
    }
    
    
    
}
