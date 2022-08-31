package mywork.myinterface;



public class GeneralHuman implements Human{

    private GeneralHuman() {
        
    }
    
    private static Human instance = new GeneralHuman();
    
    public static Human getInstance(){
        return instance;
    }
    
    public static void setInstance(Human human){
        GeneralHuman.instance = human;
    }
    
    public void act() throws Exception {
        return;
    }
    
}
