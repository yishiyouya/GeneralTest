package mywork.MySingle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MyReflectSingleTon {
    
    public static void main(String[] args){
        reflectDestroy();
        reflectEnumCanNotDestroy();
    }
    
    /**
     * ·´ÉäÆÆ»µµ¥Àý
     */
    @SuppressWarnings("rawtypes")
    public static void reflectDestroy(){
        SingleInstance instanceOne = SingleInstance.getInstance();
        
        SingleInstance instanceTwo = null;
        
        try {
            Constructor[] constructors = SingleInstance.class.getDeclaredConstructors();
            
            for (Constructor constructor : constructors) {
                constructor.setAccessible(true);
                instanceTwo = (SingleInstance) constructor.newInstance();
                break;
            }
            
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println(instanceOne.hashCode());
        System.out.println(instanceTwo.hashCode());
        
    }
    
    
    /**
     * Ã¶¾Ù
     */
    @SuppressWarnings("rawtypes")
    public static void reflectEnumCanNotDestroy(){
        MyEnumSingleInstance instanceOne = MyEnumSingleInstance.INSTANCE;
        
        MyEnumSingleInstance instanceTwo = null;
        
        try {
            Constructor[] constructors = MyEnumSingleInstance.class.getDeclaredConstructors();
            
            for (Constructor constructor : constructors) {
                constructor.setAccessible(true);
                instanceTwo = (MyEnumSingleInstance) constructor.newInstance();
                break;
            }
            
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        System.out.println(instanceOne.hashCode());
        System.out.println(instanceTwo.hashCode());
        
    }
    
}
