package mywork.myapiserver;

import java.util.HashMap;
import java.util.Map;

public class MyApiServer {/*

    private static final String inPath = "F:/File/编程实践/申万宏源python返回的成交回报慢/20200313/pyth/";
    public static void main(String[] args) {
        testTradingResultOutGoing();
    }
    
    public static void anayFunc() {
        System.out.println("begain analyze comm_server*.log");
        long start = System.currentTimeMillis();
        anayFunc();
        long stop = System.currentTimeMillis();
        System.out.println("take time: "+(stop-start)+"ms");
        
        String outF = inPath.substring(0, inPath.lastIndexOf("/")+1)+"out.log";
        //String func = "\"msgType\":16006"+ApiServerFileUtil.sep+"\"stkId\":\"512590\"";
        String func = "\"msgType\":15001"+ApiServerFileUtil.sep+"\"stkId\":\"512590\"";
        
        List<File> fileList = ApiServerFileUtil.getFiles(inPath, "comm_server");
        ApiServerFileUtil.searchFunc(fileList, outF, func);
        System.out.println("analyze over!");
    }
    
    public static void testTradingResultOutGoing() {
        Map<String,Object> dataMap = new HashMap<String, Object>();
        TradingResultOutGoing outGoing =new TradingResultOutGoing();
        outGoing.setReturnType(2);
        TradingResultOutGoing confirmOutGoing = new TradingResultOutGoing(outGoing);
        //TradingResultOutGoing confirmOutGoing = outGoing;
        
        confirmOutGoing.setReturnType(ClientAPIServerConst.RETURN_TYPE_CONFIRM);
        confirmOutGoing.setKnockQty(0);
        confirmOutGoing.setKnockAmt(0.0);
        confirmOutGoing.setKnockTime("0");
        confirmOutGoing.setKnockCode("");
        dataMap.put("subKnockInfo", confirmOutGoing);
        System.out.println(confirmOutGoing.hashCode());
        System.out.println(outGoing.hashCode());
        System.out.println(outGoing.getReturnType());
        System.out.println(confirmOutGoing.getReturnType());
        System.out.println(dataMap);
    }
    
    
*/}
