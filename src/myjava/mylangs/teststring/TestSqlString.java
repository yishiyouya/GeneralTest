package myjava.mylangs.teststring;

public class TestSqlString {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        clodIdSql();
    }

    public static void clodIdSql(){
        String clOrdId = "D12323434343";
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM FixAlgo_ParentExec WHERE origClOrdId=");
        sb.append("\'"+clOrdId+"\'");
        sb.append(" OR ");
        sb.append(" clOrdId=\'"+clOrdId+"\'");
        sb.append(" ORDER BY occurTime DESC ,  execId DESC ,  origClOrdId DESC");
        String sql = sb.toString();
        System.out.println(sql);
    }
    public static void getCombActionSql(){
        String sql = "select f.* from FutureCombAction f,"
                + " (select acctGroupId, acctid, exchid, regid from Arb_acctGroupDefine a group by acctGroupId, acctid, exchid, regid) a,"
                + " futureInfo b "
                + " where f.acctid=a.acctid and f.exchid=a.exchid and f.regid=a.regid "
                + " and f.exchid=b.exchid and f.stkid=b.stkid ";
        System.out.println(sql);
    }
    
    public static void getAudSql(){
        String regId = "11111";
        String exchId = "0";
        String regAud = "SELECT t.checkFlag FROM registration r, customer c, custType t"
                + " WHERE r.custId = c.custId AND c.custType = t.custType AND r.regId = '"
                + regId
                + "' AND r.exchId = '"
                + exchId
                + "'";
        System.out.println(regAud);
    }
    
    public static void getRegAudSql(){
        String regAud = "SELECT a.serialNum, a.occurTime, a.optId, "
                + "a.action, a.exchId, a.branchid, a.acctId, a.custId, a.regId,"
                + " a.offerRegId, a.regName, a.regType, a.personalId, a.fixFlag,"
                + " a.rePurchaseFixFlag, a.deskId, a.autoShareFlagList, a.autoQuotaFlag,"
                + " a.grantStkTypeList, a.stkTradeChkFlag, a.stkReckChkFlag, a.SIIINo,"
                + " a.bondRegId, a.bondRegType, a.identifyId, a.memo, c.custType, a.regStatus, a.regPermitList,"
                + " a.GEM_RiskSignType, a.GEM_RiskSignDate, a.GEM_RiskTradeDate, a.f_hedgeFlag, c.brokerId ,a.unifiedCode, a.exeOptMode,"
                + " a.BOOKID, a.JPMACCOUNTID "
                + " FROM RegistrationAudit a, customer c";
        System.out.println(regAud);
    }
    
    public static void getAmtSql(){
        String year = "2019";
        StringBuilder sql;
        try {
            long tradeDate =  20190923000000l;
            long preTradeDate = 0l;
                    //BLUtils.getLastTradingDate(TradeConst.exch_SHA, tradeDate, (short) 1);
            sql = new StringBuilder();
            sql.setLength(0);
            sql.append(" SELECT extMagCardId, sysOccurAmt FROM (");
            sql.append(" SELECT NVL(ac.extMagCardId, fd.acctId) as extMagCardId,");
            sql
                .append(" SUM(NVL(ac.exchMortgageAvailableAmt, 0) - NVL(ach.exchMortgageAvailableAmt, 0)) sysOccurAmt");
            sql.append(" FROM FutureCollateralDetail fd, account ac,");
            sql.append(" account" + year + " ach");
            sql.append(" WHERE fd.acctId = ac.acctId");
            sql.append(" AND fd.acctId = ach.acctId(+)");
            sql.append(" AND ac.currencyid = ach.currencyid(+)");
            sql.append(" AND ach.occurtime = " + preTradeDate + "");
            sql.append(" GROUP BY NVL(ac.extMagCardId, fd.acctId)");
            sql.append(" UNION ALL");
            sql
                .append(" SELECT ac.extmagcardId, sum(ac.mortgagechgAmt) sysOccurAmt FROM account ac, globalConstcust gc");
            sql.append(" WHERE ac.extmagcardid = gc.interiorid");
            sql.append(" AND gc.globalconst='ZX_MortgageVirtualAcct'");
            sql.append(" GROUP BY ac.extmagcardid");
            sql.append(" ) sa ORDER BY sa.extmagcardid");
            System.out.println(sql.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void getAccountSql(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT r.custId, r.branchId, r.offerRegId, r.regName,");
        sql.append(" r.deskId, r.regStatus, r.regType, c.creditLevel, c.custType,");
        sql.append(" c.customerFeeId, c.brokerId, a.acctPwd, r.tradePwd, a.extMagCardId,");
        sql.append(" a.creditFundFlag, a.salesId, r.f_hedgeflag, a.acctId,");
        sql.append(" r.optionRegPermitList, a.optionBuyOpenLimitAmt, a.currentAmt,");
        sql.append(" a.tradeFrozenAmt, a.buyTradeFrozenAmt, a.overdraftLimit, a.usableAmt,");
        sql.append(" a.cashMovementAmt, a.closePNL, a.MarginUsedAmt, a.realtimepnl,");
        sql.append(" a.realtimeamt, a.ydMarginUsedAmt, a.commision,");
        sql.append(" a.exceptFrozenAmt, a.premium, a.currencyId, r.exchId, r.regId,");
        sql.append(" a.openFrozMargin, a.extTradePwd, a.optMarginUsedAmt, a.subcreditfundflag,");
        sql.append(" a.CNYFundingGap, a.foreignCurrencyAmt, a.preForeignCurrencyAmt, a.foreignCurrencyId,");
        sql.append(" a.mortgageAvailableAmt, a.mortgageMarginAmt");
        sql.append(" FROM Registration r, Customer c, Account a");
        sql.append(" WHERE r.custId = c.custId");
        sql.append(" AND r.acctId = a.acctId");
        sql.append(" AND r.currencyId = a.currencyId");
        System.out.println(sql.toString());
    }
    
    public static String getExecuteQuerySQL(long lastModifyTime) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT occurTime,fundcode,tacode,fundname,fundstatus,navdate,nav,cumulativenav,splitstatus,splitscale,minmergevol,");
        sql.append("minspitvol,convstatus,fundmanagercode,parentfundcode,memo,mainflag,exchid,isdistributorflag,funddesc,");
        sql.append("currencyid,optid FROM QuotFile_SCD_FUNDINFO"); 
        sql.append(" WHERE lastModifyTime>=").append(lastModifyTime);
        System.out.println(sql);
        return sql.toString();
    }
    
    
    public static void sqlConInstrutKnock() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("SELECT * FROM (");
        buffer.append("SELECT a.instructId, d.exchid, d.stkid, d.orderType, d.regid,");
        buffer.append(" d.instDetailSerialnum, d.orderQty, d.orderAmt,");
        buffer.append(" d.instructType, a.isIncludeFee, d.totalFeeRate,");
        buffer.append(" d.maxBuyAmt, d.rate, d.accuredInterest, NVL(c.totalOrderQty, 0),");
        buffer
            .append(" NVL(c.totalKnockQty, 0) totalKnockQty, NVL(c.totalWithdrawQty, 0),");
        buffer.append(" NVL(c.totalKnockAmt, 0), d.securityType, a.switchOrderFlag");
        buffer.append(" FROM OrderInstruction a,instructionDetail d, intelligentOrder b, ");
        buffer.append("       (SELECT o.instDetailSerialnum,");
        buffer.append("               NVL(SUM(o.orderQty), 0) totalOrderQty,");
        buffer.append("               NVL(SUM(o.knockQty), 0) totalKnockQty, ");
        buffer.append("               NVL(SUM(o.withdrawQty), 0) totalWithdrawQty,");
        buffer.append("               NVL(SUM(o.knockAmt), 0) totalKnockAmt");
        buffer.append("          FROM instructionDetail d, openOrder o  ");
        buffer.append("         WHERE d.instdetailserialnum = o.instdetailserialnum");
        buffer.append("           AND o.withdrawflag = 'F'");
        buffer.append("         GROUP BY o.instDetailSerialnum) c");
        buffer.append(" WHERE a.instructId = d.instructId");
        buffer.append(" AND d.instdetailserialnum = b.instdetailserialnum");
        buffer.append("   AND d.instDetailSerialnum = c.instDetailSerialnum(+)");
        buffer.append("   AND d.securityType = '");
        buffer.append("CS").append("'");
        buffer.append("   AND a.flag = 0");
        buffer.append("   AND a.instructstatus IN (0, 1, 6)");
        buffer.append("   AND d.executestatus = 0 ");
        buffer.append("   AND d.finishtime < 0");
        buffer.append(" ORDER BY d.instDetailSerialnum ");
        buffer.append(" ) WHERE totalKnockQty > 0");
        System.out.println(buffer.toString());
    }
    
    public static void sqlConInstrut() {
        StringBuilder sql = new StringBuilder();

        //如果有指令数量=0的记录为自主委托，则执行数量<>成交数量;否则，指令数量<>成交数量
        sql.append(" AND (instructType = 0");
        sql.append(" AND ((orderQty>0 AND orderQty <> knockQty)");
        //自主委托尚未执行完成
        sql.append(" OR (orderQty=0 AND totalQty <> knockQty)");
        sql.append(" OR (");
        //已设置指令明细执行完成时间，但需要在界面显示一段时间
        //证券汇总方式查询,某一证券明细全部成交的不显示
        //判断是否在指令结束时间点记录市场成交量
        if (false) {
            sql.append(" finishTime > 0");
        } else {
            sql.append(" endExchKnockQty > 0 AND finishTime > 0");

        }

        sql.append(")) OR instructType = 1)");//指令金额

        System.out.println( sql.toString());
    }
    
    public static void sqlConFut() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT C.* ");
        sql.append("FROM (SELECT DISTINCT fr.serialNum, ");
        sql.append("fr.contractNum, ");
        sql.append("fr.knockCode, ");
        sql.append("a.extMagCardId, ");
        sql.append("UPPER(fr.F_ProductId) as productId, ");
        sql.append("fr.exchId, ");
        sql.append("fr.stkId, ");
        sql.append("fr.bsFlag as orderType, ");
        sql.append("fr.knockQty, ");
        sql.append("fr.knockPrice, ");
        sql.append("fr.knockTime, ");
        sql.append("fr.occurtime2, ");
        sql.append("fr.orderQty, ");
        sql.append("fr.knockAmt ");
        sql.append("FROM FutureTradingResult fr, Account a ");
        sql.append("WHERE fr.acctId = a.acctId ");
        sql.append("AND fr.execType IN ('P_FILLED', 'F_FILLED') ");//成交数据
        System.out.println(sql);
        
    }
    
    public static void sqlCon() {
        String sql =
                "SELECT /*+ordered index(f IDX_CHILDORDER_ORDERSTATUS) index(o OPENORDER_INDEX)*/ f.serialNum,"
                    + " f.clOrdId,"
                    + " f.origClOrdId,"
                    + " f.msgType,"
                    + " f.senderCompId,"
                    + " f.FixSecondaryClOrdID,"
                    + " f.batchNum,"
                    + " f.orderStatus,"
                    + " f.exchId,"
                    + " f.stkId,"
                    + " f.currencyId,"
                    + " f.acctId,"
                    + " f.regId,"
                    + " f.orderType,"
                    + " f.orderQty,"
                    + " f.orderPrice,"
                    + " f.FixOrdType,"
                    + " f.contractNum,"
                    + " f.totalKnockQty,"
                    + " f.totalKnockAmt,"
                    + " f.serverIPandPort,"
                    + " f.FixChannelId,"
                    + " f.nextClOrdId,"
                    + " f.FIXexecInst,"
                    + " f.FixTimeInForce,"
                    + " f.FixMaxPriceLevel,"
                    + " f.instDetailSerialNum,"
                    + " f.securityType"
                    + " FROM FixAlgo_ChildOrder f, OpenOrder o"
                    + " WHERE f.msgType = 'D'"
                    + "   AND f.contractNum = o.contractNum"
                    + "   AND o.withdrawFlag = 'F'"
                    + "   AND ((f.totalknockqty < o.knockqty) OR o.withdrawQty > 0)"
                    + "   AND f.serverIPandPort = '";
        System.out.println(sql);
    }
}
