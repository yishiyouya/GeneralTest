package mywork.mylog4j2.mymdslog;

public class MDSAPILOG {
    public static void log(String data, boolean isFullLog) {
        AsyncLogThread.getInstance().log(data, isFullLog);
    }

    public static void log(String data) {
        AsyncLogThread.getInstance().log(data);
    }

    public static void warn(String data) {
        AsyncLogThread.getInstance().warn(data);
    }

    public static void error(String data) {
        AsyncLogThread.getInstance().error(data);
    }

    public static void debug(String data) {
        AsyncLogThread.getInstance().debug(data);
    }

    public static void info(String data) {
        AsyncLogThread.getInstance().info(data);
    }

    public static void preff(String data) {
        PerformanceLogThread.getInstance().log(data);
    }

    public static void preff(String data, boolean isFullLog) {
        AsyncLogThread.getInstance().log(data);
        PerformanceLogThread.getInstance().log(data, isFullLog);
    }

    public static void error(String data, Throwable e) {
        AsyncLogThread.getInstance().error(data, e);
    }

    public static boolean isDebugEnabled() {
        return PerformanceLogThread.getInstance().isDebugEnabled();
    }
}
