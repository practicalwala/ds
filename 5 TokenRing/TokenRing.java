import java.util.*;
class TokenRing{
    public static void main(String[]args){

        int token = 0;
        int ch = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("enter the no of nodes: ");
        int n = sc.nextInt();

        System.out.println();
        for(int i=0;i<n;i++){
            System.out.print(i+"-");
        }
        System.out.println("0");

        do{
           System.out.print("enter sender: ");
           int s = sc.nextInt();
           
           System.out.print("enter receiver: ");
           int r = sc.nextInt();

           System.out.print("enter data: ");
           int data = sc.nextInt();

           System.out.println("token passing: ");
           for(int i=token ,j=token; (i%n)!=s;i++,j=(j+1)%n){
                System.out.print((i%n)+"->");
           }
           System.out.println(s);

           System.out.println("sender "+s +" sending data "+data);

           for(int i=s+1;(i%n)!=r;i++){
                System.out.println("data "+data +" forwarded by "+ i%n
                );
           }
           System.out.println("data "+data +" received at "+r+" receiver");
           token = s;
           
           do{
                System.out.println("do you want to send again? yes(1) no(0)");
                try{
                    ch=sc.nextInt();
                }catch(InputMismatchException e){
                    System.out.println("Wrong input enter 1 or 0");
                    sc.next();
                    ch=-1;
                }
           }while(ch!=0&&ch!=1);


        }while(ch==1);
    }
}