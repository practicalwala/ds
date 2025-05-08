//export MPJ_HOME=/home/mpj
//javac -cp $MPJ_HOME/lib/mpj.jar DistributedSum.java
//$MPJ_HOME/bin/mpjrun.sh -np 4 DistributedSum

import mpi.*;
public class DistributedSum{
    public static void main(String[] args) throws MPIException{
        
        MPI.Init(args);
         
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int arr[] = new int[]{1,2,3,4,5,6,7,8,9,10};
        int localSum = 0;
        int []recvBuffer = new int[1];
        int startIndex = rank*(arr.length/size);
        int endIndex = (rank == size-1)?arr.length:(rank+1)*(arr.length/size);
         for(int i=startIndex;i<endIndex;i++){
            localSum = localSum+arr[i];
         }
         System.out.println("Process "+rank+" intermediate sum: "+localSum);

         MPI.COMM_WORLD.Reduce(new int[]{localSum},0,recvBuffer,0,1,MPI.INT,MPI.SUM,0);
         if(rank==0){
            System.out.println(" Final Sum: "+recvBuffer[0]);
         }

         MPI.Finalize();
    }
}
