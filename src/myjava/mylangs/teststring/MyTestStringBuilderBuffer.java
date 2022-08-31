package myjava.mylangs.teststring;


public class MyTestStringBuilderBuffer {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testAppendOrderedInfo();
    }


    public static void testAppendOrderedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("aa");
        System.out.println(sb.toString());
        appendOrderedInfo(sb);
        System.out.println(sb.toString());
    }
    
    /**
     * 追加stringbuilder
     */
    public static void appendOrderedInfo(StringBuilder sb) {
        sb.append("hh");
    }
    
    /**
     * 对比多线程线程 Builder Buffer性能。 
     */
    public static void compareMultiBuilderBuffer() {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            int num = 500000;
            long res = testMultiStrinBuilder(num) - testMultiStrinBuffer(num);
            if (res < 0) {
                count++;
                System.out.println(res);
            }
        }
        System.out.println(count);
    }
    
    
    /**
     * 对比单线程 Builder Buffer性能。 
     */
    public static void compareBuilderBuffer() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            int num = 1000000;
            long res = testStrinBuilder(num) - testStrinBuffer(num);
            if (res < 0) {
                count++;
                System.out.println(res);
            }
        }
        System.out.println(count);
    }
    
    public static long testStrinBuilder(int num) {
        long start = System.nanoTime();
        for (int i = 0; i < num; i++) {
            
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append("public static void main(String[] args) {");
            sBuilder.append("public static void main(String[] args) {");
            sBuilder.append("public static void main(String[] args) {");
            sBuilder.toString();
        }
        long cost = System.nanoTime() - start;
        System.out.println(num + " StrinBuilder:" + cost);
        return cost;
    }
    
    public static long testStrinBuffer(int num) {
        long start = System.nanoTime();
        for (int i = 0; i < num; i++) {
            
            StringBuffer sBuilder = new StringBuffer();
            sBuilder.append("public static void main(String[] args) {");
            sBuilder.append("public static void main(String[] args) {");
            sBuilder.append("public static void main(String[] args) {");
            sBuilder.toString();
        }
        long cost = System.nanoTime() - start;
        System.out.println(num + " StrinBuffer:" + cost);
        return cost;
    }
    
    public static long testMultiStrinBuilder(int num) {
        long start = System.nanoTime();
        for (int j = 0; j < 5; j++) {
            new Thread(new Runnable() {
                @Override
                public synchronized void run() {
                    for (int i = 0; i < 10; i++) {
                        StringBuilder sBuilder = new StringBuilder();
                        sBuilder.append("public static void main(String[] args) {");
                        sBuilder.append("public static void main(String[] args) {");
                        sBuilder.append("public static void main(String[] args) {");
                        sBuilder.toString();
                    }
                }
            }).start();
        }
        long cost = System.nanoTime() - start;
        System.out.println(num + " StrinBuilder+synchronized:" + cost);
        return cost;
    }
    
    public static long testMultiStrinBuffer(int num) {
        long start = System.nanoTime();
        for (int j = 0; j < 5; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        StringBuffer sBuilder = new StringBuffer();
                        sBuilder.append("public static void main(String[] args) {");
                        sBuilder.append("public static void main(String[] args) {");
                        sBuilder.append("public static void main(String[] args) {");
                        sBuilder.toString();
                    }
                }
            }).start();
        }
        long cost = System.nanoTime() - start;
        System.out.println(num + " StrinBuffer+multiThread:" + cost);
        return cost;
    }
}
