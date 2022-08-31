package myapp.myqfii;

import java.util.HashMap;
import java.util.Map;

/**
 * FixAmendUtil
 *
 */
public class MyFixAmendUtil {
    
    
    
    //RICCode��ӦcompId
    private static Map<String, String> ricMap = new HashMap<String, String>();
    
    
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            getCompIdAccordRICCode("S2", "IF");
        }
        System.out.println(System.currentTimeMillis() - start);
        
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            anylGetCompIdAccordRICCode("S2", "IF");
        }
        System.out.println(System.currentTimeMillis() - start1);
    }
    
    /**
     * ����RICCode��ҵ�����ȡcompId��Ʒ���룻
     * �޶�Ӧ���򷵻�Ĭ��compId��
     * @param subRiccode
     * @param defCompId
     * @return
     * @throws GlobalParaNotExistedException
     */
    public static String getCompIdAccordRICCode(String subRiccode, String defCompId){
        String futureRICCodeMappingRelation = "CIC=IC^CIH=IH^CTF=TF^CFT=T^SCP=sc^ISC=sc^IOE=i^CTS=TS^S1=s11^S2=s22";
        //���Ի�ȡһ�Σ���ȡ���������ȫ�ֲ���529��ricMap�У�
        //�ڶ����ܻ�ȡ���򷵻أ����򷵻�Ĭ��ֵ��
        if (null == ricMap.get(subRiccode) || "".equals(ricMap.get(subRiccode))) {
            ricMap.clear();
            if (futureRICCodeMappingRelation.indexOf("^") > -1) {
                String[] futureRICCodeMappingRelationArr = futureRICCodeMappingRelation.split("\\^");
                for (String futRICC : futureRICCodeMappingRelationArr) {
                    if (futRICC.indexOf("=") > -1
                            && futRICC.split("=")[0].toUpperCase().equals(subRiccode)) {
                        ricMap.put(subRiccode, futRICC.split("=")[1]);
                    }
                }
            } else {
                if (futureRICCodeMappingRelation.indexOf("=") > -1
                        && futureRICCodeMappingRelation.split("=")[0].toUpperCase().equals(subRiccode)) {
                    ricMap.put(subRiccode, futureRICCodeMappingRelation.split("=")[1]);
                }
            }
        }
        return ricMap.get(subRiccode) != null ? ricMap.get(subRiccode) : defCompId;
    }
    
    
    public static String anylGetCompIdAccordRICCode(String subRiccode, String defCompId){
        String futureRICCodeMappingRelation = "CIC=IC^CIH=IH^CTF=TF^CFT=T^SCP=sc^ISC=sc^IOE=i^CTS=TS^S1=s11^S2=s22";
        //���Ի�ȡһ�Σ���ȡ���������ȫ�ֲ���529��ricMap�У�
        //�ڶ����ܻ�ȡ���򷵻أ����򷵻�Ĭ��ֵ��
        if (futureRICCodeMappingRelation.indexOf("^") > -1) {
            String[] futureRICCodeMappingRelationArr = futureRICCodeMappingRelation.split("\\^");
            for (String futRICC : futureRICCodeMappingRelationArr) {
                if (futRICC.indexOf("=") > -1
                        && futRICC.split("=")[0].toUpperCase().equals(subRiccode)) {
                    return futRICC.split("=")[1];
                }
            }
        } else {
            if (futureRICCodeMappingRelation.indexOf("=") > -1
                    && futureRICCodeMappingRelation.split("=")[0].toUpperCase().equals(subRiccode)) {
                return futureRICCodeMappingRelation.split("=")[1];
            }
        }
        return defCompId;
    }
}