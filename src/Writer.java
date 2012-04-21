/**
 * @(#)Writer.java

 *
 *
 * @author
 * @version 1.00 2011/3/29
 */

import java.io.*;

public class Writer {
	
	private static PrintWriter file;

    public static void writeToJavaFile(String[][] run, String[][] scan, String[][] bullet, String[][] wall) {
    	try{
    		FileWriter fw=new FileWriter("D:\\DD_Robot.java"/*robocode\\robots\\discoveryDay\\Robot.java"*/);
    		file=new PrintWriter(fw);
    		file.println("package discoveryDay;\n\nimport robocode.*;\n\n");
    		file.println("public class DD_Robot extends JuniorRobot{\n\n\tpublic void mModelRun() {\n\t\t\n\n\t\t\twhile(true) {");
    		
    		
    		
    		Writer.insertComment("Robot Main Loop");
    		//file.println("\t\t\t\tturnRadarRight(Double.POSITIVE_INFINITY);");
    		
    		
    		for(int i=0;!run[i][0].equals("");i++){
    			file.println("\t\t\t\t" + run[i][0]+"("+run[i][1]+");");
    		}
    		
    		file.println("\t\t\t}\n\t}\n");
    		Writer.insertComment("onScannedRobot: What to do when you see another robot");
    		file.println("\tpublic void onScannedRobot() {");
    		
    		for(int i=0;!scan[i][0].equals("");i++){
    			file.println("\t\t\t\t" + scan[i][0]+"("+scan[i][1]+");");
    		}
    		
    		file.println("\t}\n\n");
    		Writer.insertComment("onHitByBullet: What to do when you're hit by a mModelBullet");
    		file.println("\tpublic void onHitByBullet() {");
    		
    		for(int i=0;!bullet[i][0].equals("");i++){
    			file.println("\t\t\t\t" + bullet[i][0]+"("+bullet[i][1]+");");
    		}
    		
    		
    		file.println("\t}\n\n");
    		Writer.insertComment("onHitWall: What to do when you hit a mModelWall");
    		file.println("\tpublic void onHitWall() {");
    		
    		for(int i=0;!wall[i][0].equals("");i++){
    			file.println("\t\t\t\t" + wall[i][0]+"("+wall[i][1]+");");
    		}
    		
    		file.println("\t}\n}");
    		file.close();

    	} catch(IOException ioe) {
    		ioe.printStackTrace();
    	}
    }

	private static void insertComment(String comment) {
		
		file.println("\t//" + comment + "\n");
		
	}


}