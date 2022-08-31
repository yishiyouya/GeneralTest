package myjava.mylangs.myclassloader;

import java.net.URL;

import sun.misc.Launcher;

public class MyTestClassloader {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        testClassPathLoad();
    }
    
    
    
    /**
     * 1、JVM加载的核心类
     */
    public static void testJvmLoad() {
        URL[] urls=sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }
    }
    
    /**
     * 2、加载extension
     */
    public static void testExtendLoad() {
        System.out.println(System.getProperty("java.ext.dirs"));
        ClassLoader extensionClassloader=ClassLoader.getSystemClassLoader().getParent();
        System.out.println("the parent of extension classloader : "+extensionClassloader.getParent());
    }
    
    /**
     * 3、加载classpath配置的jar
     */
    public static void testClassPathLoad() {
        String javaClassPath = System.getProperty("java.class.path");
        String[] javaClassPathArr = javaClassPath.split(";");
        for (String javaClass : javaClassPathArr) {
            System.out.println(javaClass);
        }
    }
    
    /**
     * 
     */
    public static void testLuncher() {
        Launcher.getLauncher();
    }
    
    public static void testClassName() {
        ClassLoader loader = MyTestClassloader.class.getClassLoader();
        while (loader != null) {

        System.out.println(loader.toString());

        loader = loader.getParent();
        }
    }

}
