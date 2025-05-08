import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class AddServerImpl extends UnicastRemoteObject implements AddServerIntf{
    public AddServerImpl() throws RemoteException{
        super();
    }

    public double add(double d1,double d2) throws RemoteException{
        return d1+d2;
    }
}