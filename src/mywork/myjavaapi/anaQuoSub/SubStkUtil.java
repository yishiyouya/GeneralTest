package mywork.myjavaapi.anaQuoSub;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

public class SubStkUtil {
    public static boolean isSubStkIds(String stkId){
        if (null == stkId || "".equals(stkId)) {
            return false;
        }
        stkId = stkId.trim();
        Map<String, String> subStk = new HashedMap();
        if (subStk.size() < 1) {
            subStk.put("000058", "a");
            subStk.put("000056", "a");
            subStk.put("000055", "a");
            subStk.put("000612", "a");
            subStk.put("000733", "a");
            subStk.put("000732", "a");
            subStk.put("000610", "a");
            subStk.put("000731", "a");
            subStk.put("000059", "a");
            subStk.put("000616", "a");
            subStk.put("000615", "a");
            subStk.put("000736", "a");
            subStk.put("000735", "a");
            subStk.put("000613", "a");
            subStk.put("000619", "a");
            subStk.put("000739", "a");
            subStk.put("000617", "a");
            subStk.put("000738", "a");
            subStk.put("000061", "a");
            subStk.put("000060", "a");
            subStk.put("000065", "a");
            subStk.put("000063", "a");
            subStk.put("000062", "a");
            subStk.put("000046", "a");
            subStk.put("000045", "a");
            subStk.put("000166", "a");
            subStk.put("000601", "a");
            subStk.put("000722", "a");
            subStk.put("000721", "a");
            subStk.put("000600", "a");
            subStk.put("000049", "a");
            subStk.put("000720", "a");
            subStk.put("000605", "a");
            subStk.put("000726", "a");
            subStk.put("000725", "a");
            subStk.put("000603", "a");
            subStk.put("000723", "a");
            subStk.put("000609", "a");
            subStk.put("000729", "a");
            subStk.put("000608", "a");
            subStk.put("000607", "a");
            subStk.put("000728", "a");
            subStk.put("000727", "a");
            subStk.put("000606", "a");
            subStk.put("000050", "a");
            subStk.put("000630", "a");
            subStk.put("000751", "a");
            subStk.put("000750", "a");
            subStk.put("000078", "a");
            subStk.put("000513", "a");
            subStk.put("000755", "a");
            subStk.put("000633", "a");
            subStk.put("000632", "a");
            subStk.put("000753", "a");
            subStk.put("000631", "a");
            subStk.put("000510", "a");
            subStk.put("000517", "a");
            subStk.put("000638", "a");
            subStk.put("000759", "a");
            subStk.put("000637", "a");
            subStk.put("000758", "a");
            subStk.put("000516", "a");
            subStk.put("000636", "a");
            subStk.put("000757", "a");
            subStk.put("000635", "a");
            subStk.put("000756", "a");
            subStk.put("000514", "a");
            subStk.put("000519", "a");
            subStk.put("000639", "a");
            subStk.put("000518", "a");
            subStk.put("000069", "a");
            subStk.put("000068", "a");
            subStk.put("000066", "a");
            subStk.put("000502", "a");
            subStk.put("000623", "a");
            subStk.put("000622", "a");
            subStk.put("000501", "a");
            subStk.put("000620", "a");
            subStk.put("000506", "a");
            subStk.put("000627", "a");
            subStk.put("000626", "a");
            subStk.put("000505", "a");
            subStk.put("000625", "a");
            subStk.put("000503", "a");
            subStk.put("000509", "a");
            subStk.put("000629", "a");
            subStk.put("000628", "a");
            subStk.put("000507", "a");
            subStk.put("000070", "a");
            subStk.put("000014", "a");
            subStk.put("000498", "a");
            subStk.put("000012", "a");
            subStk.put("000011", "a");
            subStk.put("000017", "a");
            subStk.put("000016", "a");
            subStk.put("000019", "a");
            subStk.put("000021", "a");
            subStk.put("000020", "a");
            subStk.put("000002", "a");
            subStk.put("000001", "a");
            subStk.put("000007", "a");
            subStk.put("000006", "a");
            subStk.put("000005", "a");
            subStk.put("000004", "a");
            subStk.put("000488", "a");
            subStk.put("000009", "a");
            subStk.put("000800", "a");
            subStk.put("000008", "a");
            subStk.put("000036", "a");
            subStk.put("000157", "a");
            subStk.put("000035", "a");
            subStk.put("000156", "a");
            subStk.put("000034", "a");
            subStk.put("000155", "a");
            subStk.put("000711", "a");
            subStk.put("000710", "a");
            subStk.put("000039", "a");
            subStk.put("000038", "a");
            subStk.put("000159", "a");
            subStk.put("000037", "a");
            subStk.put("000158", "a");
            subStk.put("000715", "a");
            subStk.put("000713", "a");
            subStk.put("000712", "a");
            subStk.put("000719", "a");
            subStk.put("000718", "a");
            subStk.put("000717", "a");
            subStk.put("000716", "a");
            subStk.put("000043", "a");
            subStk.put("000042", "a");
            subStk.put("000040", "a");
            subStk.put("000025", "a");
            subStk.put("000023", "a");
            subStk.put("000029", "a");
            subStk.put("000700", "a");
            subStk.put("000028", "a");
            subStk.put("000027", "a");
            subStk.put("000026", "a");
            subStk.put("000703", "a");
            subStk.put("000702", "a");
            subStk.put("000701", "a");
            subStk.put("000708", "a");
            subStk.put("000705", "a");
            subStk.put("000709", "a");
            subStk.put("000032", "a");
            subStk.put("000153", "a");
            subStk.put("000031", "a");
            subStk.put("000030", "a");
            subStk.put("000151", "a");
            subStk.put("000150", "a");
            subStk.put("000333", "a");
            subStk.put("000695", "a");
            subStk.put("000573", "a");
            subStk.put("000698", "a");
            subStk.put("000697", "a");
            subStk.put("000576", "a");
            subStk.put("000338", "a");
            subStk.put("000582", "a");
            subStk.put("000581", "a");
            subStk.put("000564", "a");
            subStk.put("000685", "a");
            subStk.put("000563", "a");
            subStk.put("000683", "a");
            subStk.put("000682", "a");
            subStk.put("000561", "a");
            subStk.put("000568", "a");
            subStk.put("000688", "a");
            subStk.put("000567", "a");
            subStk.put("000566", "a");
            subStk.put("000687", "a");
            subStk.put("000686", "a");
            subStk.put("000565", "a");
            subStk.put("000692", "a");
            subStk.put("000691", "a");
            subStk.put("000570", "a");
            subStk.put("000690", "a");
            subStk.put("000597", "a");
            subStk.put("000596", "a");
            subStk.put("000595", "a");
            subStk.put("000599", "a");
            subStk.put("000598", "a");
            subStk.put("000586", "a");
            subStk.put("000100", "a");
            subStk.put("000584", "a");
            subStk.put("000589", "a");
            subStk.put("000587", "a");
            subStk.put("000593", "a");
            subStk.put("000592", "a");
            subStk.put("000591", "a");
            subStk.put("000590", "a");
            subStk.put("000531", "a");
            subStk.put("000652", "a");
            subStk.put("000651", "a");
            subStk.put("000530", "a");
            subStk.put("000650", "a");
            subStk.put("000099", "a");
            subStk.put("000656", "a");
            subStk.put("000777", "a");
            subStk.put("000655", "a");
            subStk.put("000776", "a");
            subStk.put("000413", "a");
            subStk.put("000534", "a");
            subStk.put("000533", "a");
            subStk.put("000411", "a");
            subStk.put("000532", "a");
            subStk.put("000539", "a");
            subStk.put("000659", "a");
            subStk.put("000417", "a");
            subStk.put("000538", "a");
            subStk.put("000416", "a");
            subStk.put("000537", "a");
            subStk.put("000779", "a");
            subStk.put("000657", "a");
            subStk.put("000778", "a");
            subStk.put("000415", "a");
            subStk.put("000536", "a");
            subStk.put("000419", "a");
            subStk.put("000780", "a");
            subStk.put("000520", "a");
            subStk.put("000762", "a");
            subStk.put("000761", "a");
            subStk.put("000089", "a");
            subStk.put("000088", "a");
            subStk.put("000403", "a");
            subStk.put("000524", "a");
            subStk.put("000766", "a");
            subStk.put("000402", "a");
            subStk.put("000523", "a");
            subStk.put("000401", "a");
            subStk.put("000400", "a");
            subStk.put("000521", "a");
            subStk.put("000407", "a");
            subStk.put("000528", "a");
            subStk.put("000526", "a");
            subStk.put("000768", "a");
            subStk.put("000767", "a");
            subStk.put("000404", "a");
            subStk.put("000525", "a");
            subStk.put("000408", "a");
            subStk.put("000529", "a");
            subStk.put("000090", "a");
            subStk.put("000096", "a");
            subStk.put("000553", "a");
            subStk.put("000795", "a");
            subStk.put("000673", "a");
            subStk.put("000552", "a");
            subStk.put("000430", "a");
            subStk.put("000551", "a");
            subStk.put("000672", "a");
            subStk.put("000793", "a");
            subStk.put("000671", "a");
            subStk.put("000550", "a");
            subStk.put("000557", "a");
            subStk.put("000678", "a");
            subStk.put("000799", "a");
            subStk.put("000677", "a");
            subStk.put("000798", "a");
            subStk.put("000555", "a");
            subStk.put("000676", "a");
            subStk.put("000797", "a");
            subStk.put("000796", "a");
            subStk.put("000554", "a");
            subStk.put("000559", "a");
            subStk.put("000679", "a");
            subStk.put("000558", "a");
            subStk.put("000560", "a");
            subStk.put("000681", "a");
            subStk.put("000680", "a");
            subStk.put("000421", "a");
            subStk.put("000663", "a");
            subStk.put("000662", "a");
            subStk.put("000783", "a");
            subStk.put("000420", "a");
            subStk.put("000541", "a");
            subStk.put("000540", "a");
            subStk.put("000661", "a");
            subStk.put("000782", "a");
            subStk.put("000425", "a");
            subStk.put("000546", "a");
            subStk.put("000667", "a");
            subStk.put("000788", "a");
            subStk.put("000666", "a");
            subStk.put("000545", "a");
            subStk.put("000423", "a");
            subStk.put("000544", "a");
            subStk.put("000665", "a");
            subStk.put("000786", "a");
            subStk.put("000785", "a");
            subStk.put("000301", "a");
            subStk.put("000543", "a");
            subStk.put("000429", "a");
            subStk.put("000428", "a");
            subStk.put("000548", "a");
            subStk.put("000669", "a");
            subStk.put("000668", "a");
            subStk.put("000789", "a");
            subStk.put("000426", "a");
            subStk.put("000547", "a");
            subStk.put("000791", "a");
            subStk.put("000790", "a");
        }
        return subStk.getOrDefault(stkId, "").length() > 0 ? true : false;
    }
}
