
// File: Experiment.java
// Description:
// HEYYYYYY :)
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import cern.jet.random.engine.*;
import simModel.*;

// Main Method: Experiments
//
public class Experiment1 {
	public static void main(String[] args) {
		int NUMRUNS = 10;
		double startTime = 0.0;
		// Simulate for one week, for the purposes of experiment validation
		// experimental end time will be computed in Experiment2
		double endTime=(24*60)*7 ; 

		Seeds[] sds = new Seeds[NUMRUNS];
		OfficeRepair officeRepair; // Simulation object

		// Get a set of uncorrelated seeds
		RandomSeedGenerator rsg = new RandomSeedGenerator();
		for (int i = 0; i < NUMRUNS; i++)
			sds[i] = new Seeds(rsg);
		

		int numEmpT12 = 8 ;
		int numEmpAll = 8 ;

		try {
			FileOutputStream file = new FileOutputStream(numEmpT12+"_NumALL_"+numEmpAll+".txt");
			System.setOut(new PrintStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File file = new File(numEmpT12+"_NumALL_"+numEmpAll+".txt");
		
		FileWriter fw = null;
		PrintWriter pw ; 
		try {
			fw = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw = new PrintWriter(fw);
		pw.println("--- Final Output --- \nnumEmployeeT12: "+ numEmpT12+"\tnumEmployeeALL: "+ numEmpAll);
		
		
		for (int i = 0; i < NUMRUNS; i++) {
			officeRepair = new OfficeRepair(startTime, sds[i], true, numEmpT12, numEmpAll);
			officeRepair.setTimef(endTime);
			officeRepair.runSimulation();
	         System.out.println("Simulation Done time to output the results");
	          // compute scalar output
	         System.out.println("\n/*****************************/");
	         System.out.println("Satisfaction LevelT12: "+(Math.round(officeRepair.getSatisfactionLevelT12() * 100)+"%"));
	         System.out.println("Satisfaction LevelT34: "+(Math.round(officeRepair.getSatisfactionLevelT34() * 100)+"%"));
	         System.out.println("Satisfaction LevelALL: "+(Math.round(officeRepair.getSatisfactionLevelAll() * 100)+"%"));
	         System.out.println("Overtime Costs: " + "$"+officeRepair.getOverTimeCost());
	         System.out.println("Average Daily Costs: "+ "$" +officeRepair.getAverageDailyCost());
	         System.out.println("/*****************************/\n");
	         System.out.println("Terminated " + (i + 1) + " cases");
	         
	         pw.println("\n/*****************************/");
	         pw.println("Satisfaction LevelT12: "+(Math.round(officeRepair.getSatisfactionLevelT12() * 100)+"%"));
	         pw.println("Satisfaction LevelT34: "+(Math.round(officeRepair.getSatisfactionLevelT34() * 100)+"%"));
	         pw.println("Satisfaction LevelALL: "+(Math.round(officeRepair.getSatisfactionLevelAll() * 100)+"%"));
	         pw.println("Overtime Costs: " + "$"+officeRepair.getOverTimeCost());
	         pw.println("Average Daily Costs: "+ "$" +officeRepair.getAverageDailyCost());
	         pw.println("/*****************************/\n");
	         pw.println("Terminated " + (i + 1) + " cases");
	         
	       }
		pw.close();	 
			
		}
		
	
}
