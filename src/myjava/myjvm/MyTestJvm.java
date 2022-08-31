package myjava.myjvm;

public class MyTestJvm {
    
    public static void main(String[] args) {
        getJvmArgs();
    }
    
    public static void getJvmArgs() {
        Runtime runtime = Runtime.getRuntime();
        StringBuffer sbBuffer = new StringBuffer();
        sbBuffer.append("free:"+ (int)runtime.freeMemory() / 1024 / 1024);
        sbBuffer.append("\n");
        sbBuffer.append("total:"+ (int)runtime.totalMemory() / 1024 / 1024);
        sbBuffer.append("\n");
        sbBuffer.append("runTime toString:"+ runtime.toString());
        System.out.println(sbBuffer.toString());
    }
    
    
}
