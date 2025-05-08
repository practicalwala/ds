import java.rmi.Naming;
public class AddServer{
        public static void main(String[] args){
        try{
            AddServerImpl addServerImpl = new AddServerImpl();
            Naming.rebind("//localhost/AddServer",addServerImpl);
            System.out.println("Server is Running.....");
        }
        catch(Exception e){
            System.out.println("exception in main:"+ e.getMessage());
            e.printStackTrace();
        }
    }
}