//javac *.java
//rmic AddServerImpl
//rmiregistry
//java AddServer
//java AddClient 127.0.0.1 10 20

import java.rmi.Naming;

public class AddClient{
    public static void main(String[] args) {
        if(args.length<3){
            System.out.println("usage: java AddClient <server_ip> <num1> <num2>");
        }
        try{
            String addServer = "//"+args[0]+"/AddServer";
            AddServerIntf addServerIntf = (AddServerIntf)Naming.lookup(addServer);

            System.out.println("first no is: "+args[1]);
            System.out.println("second no is: "+args[2]);
            double d1 = Double.parseDouble(args[1]);
            double d2 = Double.parseDouble(args[2]);

            System.out.println("the sum is: "+addServerIntf.add(d1, d2));
        }
        catch(Exception e){
            System.out.println("Exception in main: "+e.getMessage());
            e.printStackTrace();
        }
    }
        
}
