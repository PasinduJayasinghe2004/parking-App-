import java.util.ArrayList;
import java.util.Scanner;

public class Practice {
    private static int[][] parkingStructure=null;
    private static  final ArrayList<Owner>owners=new ArrayList<>();
    private static final String[] ZoneName={"Zone A","Zone B"};

    public static void addZone(){
        Scanner input =new Scanner(System.in);

        System.out.println("Enter number of slots");
        int slots=input.nextInt();

        System.out.println("enter Zone letter:");
        String ZoneLabel=input.nextLine();

        int[][] newStructure=new int[parkingStructure.length+1][];
        String[] newZoneLabel=new String[ZoneName.length+1];

        for(int i=0;i<parkingStructure.length;i++){
            newStructure[i]=parkingStructure[i];
            newZoneLabel[i]=ZoneName[i];
        }
        newStructure[parkingStructure.length]=new int[slots];
        parkingStructure=newStructure;
        System.out.println("Succe");



    }
    public static void saveFile(){

        for(int i=0;i<parkingStructure.length;i++) {
            String zone = ZoneName.length > i ? ZoneName[i] : "zone" + (char) ('A' + i);
            file.write(zone+":")

            for (int slots : parkingStructure[i]) {
                file.write((slots==0?"[]":"[X]")+" ");

            }
        }
        for(Owner own:owners){
            file.write("Name: "+own.getName()+);
        }
    }
}
