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
 * 转换终端信息工具类
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
    
    //custMap  globalConstCust 缓存 
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
        
        //初始化 globalConstCust表 TerminalInfoTransferPara 缓存
        
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
        
        System.out.println("\n 转换种类:" + transKind +
            "\n 终端类型及标识信息:" + terTypeCotent +
            "\n 连接符:" + connector +
            "\n 分隔符:" + separator +
            "\n 扩展信息起始符号:" + extSym +
            "\n 新连接符:" + newConnector +
            "\n 新分隔符:" + newSeparator +
            "\n 必采项补充内容:" + mustSupCotent +
            "\n 终端信息替换内容:" + terRepCotent);
        if (isTransTerminalInfo(transKind, terTypeCotent, connector, separator, extSym, newConnector, newSeparator, mustSupCotent, terRepCotent)) {
            transTerminalInfo(transKind, 
                connector, separator, extSym, newConnector, 
                newSeparator, terRepCotent, mustSupCotent, 
                terTypeCotent);
            
        }
        
        //使用后，清除 globalConstCust表 TerminalInfoTransferPara 缓存
        if (null != custMap && custMap.size() > 0) {
            custMap.clear();
        }
        
    }
    
    /**
     * 在昨日数据整理完成后增加判断，如果满足 ①②③④⑤ and ( ⑥ or ⑦ or ⑧ or ⑨ )条件，则增加现货终端信息的转换处理逻辑：
     * ① globalConstcust表中存在GLOBALCONST='TerminalInfoTransferPara'，且INTERIORID='转换种类'的记录，且该记录的CONSTDISPLAYNAME为1或2；
     * ② globalConstcust表中存在GLOBALCONST='TerminalInfoTransferPara'，且INTERIORID='终端类型及标识信息'的记录，且该记录的CONSTDISPLAYNAME可以解析出终端类型和标识信息；
     * ③ globalConstcust表中存在GLOBALCONST='TerminalInfoTransferPara'，且INTERIORID='分隔符'的记录，且该记录的CONSTDISPLAYNAME不为空（注意去空格）；
     * ④ globalConstcust表中存在GLOBALCONST='TerminalInfoTransferPara'，且INTERIORID='连接符'的记录，且该记录的CONSTDISPLAYNAME不为空（注意去空格）；
     * ⑤ globalConstcust表中存在GLOBALCONST='TerminalInfoTransferPara'，且INTERIORID='扩展信息起始符号'的记录，且该记录的CONSTDISPLAYNAME不为空（注意去空格）；
     * ⑥ globalConstcust表中存在GLOBALCONST='TerminalInfoTransferPara'，且INTERIORID='新分隔符'的记录，且该记录的CONSTDISPLAYNAME不为空（注意去空格）；
     * ⑦ globalConstcust表中存在GLOBALCONST='TerminalInfoTransferPara'，且INTERIORID='新连接符'的记录，且该记录的CONSTDISPLAYNAME不为空（注意去空格）；
     * ⑧ globalConstcust表中存在GLOBALCONST='TerminalInfoTransferPara'，且INTERIORID='必采项补充内容'的记录，且该记录的CONSTDISPLAYNAME不为空（注意去空格）；
     * ⑨ globalConstcust表中存在GLOBALCONST='TerminalInfoTransferPara'，且INTERIORID='终端信息替换内容'的记录，且该记录的CONSTDISPLAYNAME不为空（注意去空格）；
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
     * 终端信息转换逻辑
     * @return
     * @throws SQLException 
     * @throws AppException 
     */
    public void transTerminalInfo(String transKind, 
        String connector, String separator, String extSym, String newConnector, 
        String newSeparator, String terRepCotent, String mustSupCotent, 
        String terTypeCotent) {
        try {
            //1.获取当前清算日终端信息
            List<TerminalInfoHis> terminalList = new ArrayList<TerminalInfoHis>();
            terminalList = getTerminalInfoByDate();
            
            String terminalInfo = "";
            String oldTerminalInfo = "";
            if (null != terminalList && terminalList.size() > 0) {
                for (TerminalInfoHis terminalInfoHis : terminalList) {
                    try {
                        //0.每次重新获取配置信息
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
                            //1.按照 “转换种类”， 转换终端信息
                            /*
                             * |MAC=1831BF4BC3AB
                             * |LIP=192.168.118.107
                             * |insideInfo=PC;IIP:NA;LIP:192.168.118.107;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL;PCN:XXJSB-ZHUANGSHANG;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4
                             * |outsideInfo=PC;IIP:NA;LIP:192.168.4.140;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL;PCN:ZENGJIANHUA;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4
             
                             * 转换种类为1，则转换对象为：PC;IIP:NA;LIP:192.168.118.107;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL;PCN:XXJSB-ZHUANGSHANG;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4
                             * 转换种类为2，则转换对象为：PC;IIP:NA;LIP:192.168.4.140;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL;PCN:ZENGJIANHUA;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4 
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
                                 * 2.如果转换对象的终端类型不在【终端类型】中，则不转换该记录，否则继续。
                                 * 终端类型的获取逻辑：根据【分隔符】，分割转换对象，第1个子字符串即终端类型；
                                 * 判断终端信息是否以【终端类型及标识信息】：前开头
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
                                    //转换前部分：PC;IIP:NA;LIP:192.168.4.140;MAC:1831BF4BC3AB;HD:TF655AY91GHRVL;PCN:ZENGJIANHUA;CPU:BFEBFBFF00040651;PI:C^NTFS^99G;VOL:0004-2CC4
                                    oldTerminalInfo = newTerminalInfo;
                                }
                                
                                /*
                                 * 2-6-1. 如果包含 @，截取出，最后追加到末尾
                                 */
                                String origNewTerminalInfo = newTerminalInfo;
                                String extendInfo = ""; 
                                if (isValidPara(extSym) && newTerminalInfo.indexOf(extSym) > -1) {
                                    extendInfo = newTerminalInfo.substring(newTerminalInfo.indexOf(extSym) + extSym.length(), newTerminalInfo.length());
                                    newTerminalInfo = newTerminalInfo.substring(0, newTerminalInfo.indexOf(extSym));
                                    System.out.println("\n ===== 2-6-1: extendInfo：" + extendInfo);
                                    System.out.println("\n ===== 2-6-1: newTerminalInfo：" + newTerminalInfo);
                                }
                                
                                /*
                                 * 2-3-1.处理terTypeCotent，去掉开头PC:, 改为以PC;开头
                                 */
                                if (terTypeCotent.indexOf(":") > -1) {
                                    terTypeCotent = terTypeCotent.replace(":", separator);
                                }
                                
                                /*
                                 * 2-4-2.处理terTypeCotent，去掉开头PC:
                                 */
                                if (terTypeCotent.indexOf(separator) > -1) {
                                    terTypeCotent = terTypeCotent.substring(terTypeCotent.indexOf(separator)+separator.length(), terTypeCotent.length());
                                }
                                
                                /*
                                 * 2-3-2. 转换终端信息是否包含newConnector newSeparator
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
                                 * 2-4-3-start. 更新separator, 头部去掉PC+separator
                                 */
                                if (isValidPara(separator)) {
                                    newTerminalInfo = newTerminalInfo.substring(newTerminalInfo.indexOf(separator)+separator.length(), newTerminalInfo.length());
                                }
                                
                                /*
                                 * 2-4-4-start 获取配置中没有的，终端信息有的多余配置
                                 */
                                System.out.println("\n =====2-4-4：" + newTerminalInfo);
                                Map<String, String> termInfoMap = new HashMap<String, String>();
                                String notExistConfig = getNotExistConfig(termInfoMap,
                                    mustSupCotent,
                                    extSym,
                                    newTerminalInfo,
                                    separator,
                                    connector,
                                    terTypeCotent,
                                    transedType);
                                System.out.println("\n =====2-4-4：" + termInfoMap);
                                
                                /*
                                 * 3.
                                 * 如果【终端信息替换内容】不为空（注意去空格，解析成多组进行判断），
                                 * 则判断转换对象中的每个具体终端信息是否与【替换前内容】一致（如果包含通配符*，则需要支持模糊匹配），
                                 * 若一致，则需要替换成【替换后内容】。具体终端信息的获取逻辑：
                                 * · 【连接符】和后续第1个【分隔符】之间的内容；
                                 * · 如果【连接符】后面没有【分隔符】，取该【连接符】和后续第1个【扩展信息起始符号】之间的内容；
                                 * · 如果【连接符】后面没有【分隔符】和【扩展信息起始符号】，则取该【连接符】后面的所有内容；
                                 */
                                System.out.println("\n ==== 3. " + newTerminalInfo);
                                newTerminalInfo = transTerRepCotent(termInfoMap, extSym, newTerminalInfo
                                                    , terRepCotent, separator, connector);
                                
                                /*
                                 * 4. 如果【必采项补充内容】不为空（注意去空格），则根据【分隔符】和【连接符】，逐个拼接上标识为必采项的【标识信息】（需要去掉*号，例如：';IIP:'；另外【终端类型及标识信息】允许设置多组，应取当前转换对象对应的那一组），判断转换对象中是否存在该字符串：
                                 * · 如果不存在，则根据【标识信息】中各个标识信息的顺序，在对应位置补充上【分隔符】、【标识信息】、【连接符】、【必采项补充内容】，例如：;IIP=NA；由于设置的【标识信息】顺序可能与被转换的终端信息顺序不一致，因此补充位置按以下逻辑确认：
                                 *      a) 如果待补充的标识信息为第1个，则直接补充到第1个位置；
                                 *      b) 否则，在被转换的终端信息中查找前1个标识信息的位置：如果能够查到，则补充到其后面的位置；否则再往前取1个标识信息重新查找，依次处理，直到找到为止。如果前面的标识信息均未查找到，则直接补充到第1位。
                                 * · 如果存在、且（后一个字符为【分隔符】或者【扩展信息起始符号】，或者后面无字符），则直接在后面补充上【必采项补充内容】；
                                 */
                                System.out.println("\n ===== 4: " + newTerminalInfo);
                                newTerminalInfo = transMustSupCotent(termInfoMap, mustSupCotent, newTerminalInfo
                                                    ,separator, connector, terTypeCotent);
                                /*
                                 * 2-4-3-end.头部加PC+separator
                                 */
                                newTerminalInfo = transedType + separator + newTerminalInfo;
                                
                                /*
                                 * 5. 如果【新连接符】不为空（注意去空格），则根据【分隔符】和【连接符】，逐个拼接上【标识信息】（如果是必采项，需要去掉*号，例如：';IIP:'；
                                 * 另外【终端类型及标识信息】允许设置多组，应取当前转换对象对应的那一组），判断转换对象中是否存在该字符串：
                                 * 如果存在，则将其中的【连接符】替换为【新连接符】；
                                 */
                                System.out.println("\n ===== 5: " + newTerminalInfo);
                                newTerminalInfo = transConnector(newTerminalInfo, terTypeCotent, separator, connector, newConnector);
                                
                                /*
                                 * 6. 如果【新分隔符】不为空（注意去空格），则根据【分隔符】，逐个拼接上【标识信息】（如果是必采项，需要去掉*号，例如：';IIP'；
                                 * 另外【终端类型及标识信息】允许设置多组，应取当前转换对象对应的那一组），判断转换对象中是否存在该字符串：
                                 * 如果存在，则将其中的【分隔符】替换为【新分隔符】。
                                 */
                                System.out.println("\n ===== 6: " + newTerminalInfo);
                                newTerminalInfo = transSeparator(newTerminalInfo, terTypeCotent, separator, newSeparator);
                                
                                /*
                                 * 2-6-0-end 获取配置中没有的，终端信息有的多余配置
                                 */
                                System.out.println("\n ===== 2-6-0: " + notExistConfig);
                                if (isValidPara(notExistConfig)) {
                                    newTerminalInfo += notExistConfig;
                                }
                                
                                /*
                                 * 2-6-1.拼接@及之后扩展信息
                                 */
                                if (isValidPara(extendInfo) && origNewTerminalInfo.endsWith(extSym + extendInfo)) {
                                    if (newTerminalInfo.endsWith(extSym)) {
                                        newTerminalInfo = newTerminalInfo + extendInfo;
                                    }else{
                                        newTerminalInfo = newTerminalInfo + extSym + extendInfo;
                                    }
                                }
                                
                                /*
                                 * 7. 凡是现货终端信息发生转换的，均需要将转换前的现货终端信息写入到对应记录的origTerminalInfo字段上。
                                 */
                                System.out.println("\n ===== 7: " + newTerminalInfo);
                                syncTerminalInfo(terminalInfo, newTerminalInfo, terminalInfoHis, oldTerminalInfo);
                                
                            } else {
                                System.out.println("转换对象的终端类型不在【终端类型】中，则不转换该记录");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(terminalInfoHis.toString() + e);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("transTerminalInfo 终端信息转换 error: " + e);
        }
    }
    
    /**
     * 判断终端信息是否以【终端类型及标识信息】：前开头
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
     * 2-3. 转换终端信息是否包含newConnector newSeparator，包含则转为 connector separator
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
             * ⑤ 如果【新连接符】不为空（注意去空格），则根据【分隔符】和【连接符】，逐个拼接上【标识信息】（如果是必采项，需要去掉*号，例如：';IIP:'；
             * 另外【终端类型及标识信息】允许设置多组，应取当前转换对象对应的那一组），判断转换对象中是否存在该字符串：
             * 如果存在，则将其中的【连接符】替换为【新连接符】；
             */
            if (newTerminalInfo.indexOf(newConnector) > -1) {
                
                newTerminalInfo = transConnector(newTerminalInfo, terTypeCotent, separator, newConnector, connector);
                System.out.println("\n ==== 2-3. connector: "+newTerminalInfo);
            }
        }
        if (isValidPara(newTerminalInfo) && isValidPara(newSeparator)) {
            /*
             * ⑥ 如果【新分隔符】不为空（注意去空格），则根据【分隔符】，逐个拼接上【标识信息】（如果是必采项，需要去掉*号，例如：';IIP'；
             * 另外【终端类型及标识信息】允许设置多组，应取当前转换对象对应的那一组），判断转换对象中是否存在该字符串：
             * 如果存在，则将其中的【分隔符】替换为【新分隔符】。
             */
            if (newTerminalInfo.indexOf(newConnector) > -1) {
                newTerminalInfo = transSeparator(newTerminalInfo, terTypeCotent, newSeparator, separator);
                System.out.println("\n ==== 2-3. separator: "+newTerminalInfo);
            }
        }
        return newTerminalInfo;
    }
    
    /**
     * 新分割符为空，或者 终端信息包含新分割符
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
     * 新连接符为空，或者 终端信息包含新连接符
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
     * 3. 如果【终端信息替换内容】不为空（注意去空格，解析成多组进行判断），
     * 则判断转换对象中的每个具体终端信息是否与【替换前内容】一致（如果包含通配符*，则需要支持模糊匹配），
     * 若一致，则需要替换成【替换后内容】。具体终端信息的获取逻辑：
     * · 【连接符】和后续第1个【分隔符】之间的内容；
     * · 如果【连接符】后面没有【分隔符】，取该【连接符】和后续第1个【扩展信息起始符号】之间的内容；
     * · 如果【连接符】后面没有【分隔符】和【扩展信息起始符号】，则取该【连接符】后面的所有内容；
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
                System.out.println("\n ==== 3. termMap： "+termMap);
                for (Entry<String, String> termEntry : termMap.entrySet()) {
                    if (isValidPara(termEntry.getValue())) {
                        termList.add(termEntry.getKey() + connector + termEntry.getValue());
                    } else {
                        termList.add(termEntry.getKey());
                    }
                }
                System.out.println("\n ==== 3. termList： "+termList);
                
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
     * 2-4-4. 不存在的配置，截取出，最后转换连接符、分隔符后再拼接到终端信息后，扩展信息前
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
        //终端信息拷贝
        Map<String, String> termTempMap = new LinkedHashMap<String, String>();

        /*
        * 如果 @123在末尾，替换后依旧在末尾
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
            //按新连接符，新连接符补充未配置的选项
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
     * 4. 如果【必采项补充内容】不为空（注意去空格），则根据【分隔符】和【连接符】，逐个拼接上标识为必采项的【标识信息】（需要去掉*号，例如：';IIP:'；另外【终端类型及标识信息】允许设置多组，应取当前转换对象对应的那一组），判断转换对象中是否存在该字符串：
     * · 如果不存在，则根据【标识信息】中各个标识信息的顺序，在对应位置补充上【分隔符】、【标识信息】、【连接符】、【必采项补充内容】，例如：;IIP=NA；由于设置的【标识信息】顺序可能与被转换的终端信息顺序不一致，因此补充位置按以下逻辑确认：
     *      a) 如果待补充的标识信息为第1个，则直接补充到第1个位置；
     *      b) 否则，在被转换的终端信息中查找前1个标识信息的位置：如果能够查到，则补充到其后面的位置；否则再往前取1个标识信息重新查找，依次处理，直到找到为止。如果前面的标识信息均未查找到，则直接补充到第1位。
     * · 如果存在、且（后一个字符为【分隔符】或者【扩展信息起始符号】，或者后面无字符），则直接在后面补充上【必采项补充内容】；
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
     * 5. 转换连接符
     * @param newTerminalInfo
     * @param terTypeCotent
     * @param separator
     * @param connector
     * @param newConnector
     */
    public String transConnector(String newTerminalInfo, String terTypeCotent, String separator, String connector, String newConnector) {
        /*
         * ⑤ 如果【新连接符】不为空（注意去空格），则根据【分隔符】和【连接符】，逐个拼接上【标识信息】（如果是必采项，需要去掉*号，例如：';IIP:'；
         * 另外【终端类型及标识信息】允许设置多组，应取当前转换对象对应的那一组），判断转换对象中是否存在该字符串：
         * 如果存在，则将其中的【连接符】替换为【新连接符】；
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
     * 6. 转换分隔符
     * @param newTerminalInfo
     * @param terTypeCotent
     * @param separator
     * @param newSeparator
     */
    public String transSeparator(String newTerminalInfo, String terTypeCotent, String separator, String newSeparator) {
        /*
         * ⑥ 如果【新分隔符】不为空（注意去空格），则根据【分隔符】，逐个拼接上【标识信息】（如果是必采项，需要去掉*号，例如：';IIP'；
         * 另外【终端类型及标识信息】允许设置多组，应取当前转换对象对应的那一组），判断转换对象中是否存在该字符串：
         * 如果存在，则将其中的【分隔符】替换为【新分隔符】。
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
     * 7. 凡是现货终端信息发生转换的，均需要将转换前的现货终端信息写入到对应记录的origTerminalInfo字段上。
     * @param newTerminalInfo
     * @param terTypeCotent
     * @param separator
     * @param newSeparator
     */
    public void syncTerminalInfo(String terminalInfo, String newTerminalInfo, TerminalInfoHis terminalInfoHis, String oldTerminalInfo) {
        
        if (isValidPara(oldTerminalInfo) && !oldTerminalInfo.equals(newTerminalInfo)) {
            //原 终端信息
            terminalInfoHis.setOrigTerminalInfo(terminalInfo);
            //转换后 终端信息
            terminalInfo = terminalInfo.replace(oldTerminalInfo, newTerminalInfo);
            System.out.println("old: " + oldTerminalInfo);
            System.out.println("new: " + newTerminalInfo);
            terminalInfoHis.setTerminalInfo(terminalInfo);
            //测后删除
            //updateNewTerminalInfo(transMng, year, terminalInfoHis);
        }
    }

    /**
     * 根据年、上一交易日，取历史表TERMINALINFO 的 terminalInfo
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
//        terminalList.add(new TerminalInfoHis(20200905000000l, "ab", "|MAC=1831BF4BC3AB|LIP=192.168.118.107|insideInfo=PC;IIP:192.168.4.254;LIP:192.168.4.71;MAC:FCAA14C510E9;HD:WD-WCC6Y1UV9CS7;PCN:LIANGWENXIU;CPU:BFEBFBFF000306C3;PI:C,NTFS,120.00;VOL:C0383181@CTS;V8.8.0.0;LIANGWENXIU扩展信息"));
//        terminalList.add(new TerminalInfoHis(20200905000000l, "ab", "|MAC=1831BF4BC3AB|LIP=192.168.118.107|insideInfo=PC;IIP:192.168.4.254;IPORT:2000;LIP:192.168.4.114;MAC:FCAA14C51081;HD:WD-WCC6Y1UV9CS7;PCN:LIANGWENXIU;CPU:BFEBFBFF000306C3;PI:C,NTFS,120.00;VOL:C0383181"));
        terminalList.add(new TerminalInfoHis(20200905000000l, "ab", "|MAC=1831BF4BC3AB|LIP=192.168.118.107|insideInfo=null"));
        return terminalList;
    }
    
    /**
     * 历史终端信息内部类，用来异步DB用的
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
