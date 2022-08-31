package mywork.myinterface;

public class TestRealClass {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        GeneralHuman.setInstance(new Peter());
        Human hMan = GeneralHuman.getInstance();
        try {
            hMan.act();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
