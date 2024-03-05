package org.hospital.user;

import java.util.*;


import org.hospital.model.Doctermodel;
import org.hospital.service.DocterService;

import org.hospital.repository.*;

public class AddminApplicationApp {

	public static void main(String[] args)throws Exception {
		DocterService dv =new DocterService();
		
		DocterRepository docRepo=new  DocterRepository();
		
		do
		{
			
			System.out.println(" case 1: Add New Doctor /View /Search /Delete ");
			System.out.println(" case 2: Add New Patient /View/Search/Delete");
			System.out.println("Enter your choice");
			Scanner xyz = new Scanner(System.in);
			int choice = xyz.nextInt();
			switch(choice)
			{
			
			case 1:
				xyz.nextLine();
				System.out.println("enter Doctername speality Degination");
				String docterName=xyz.nextLine();
				String speality=xyz.nextLine();
				String Degination=xyz.nextLine();
				Doctermodel model =new Doctermodel();
				model.setDoctername(docterName);
				model.setSpeciality(speality);
				model.setDesgination(Degination);
				
				int result =dv.addDocter(model);
				
//				if(result==1)
//				{
//					System.out.println("Docter added sucessfully");
//				}
//				else
//				{
//					System.out.println("some problem is there ");
//				}
				

				System.out.println((result == 1) ? " Docter added sucessfully.": (result == -1) ? "Docter already present" : " some problem is there...");
				break;
			
			case 2:
				System.out.println("All Doctor list");
				List<Doctermodel> list=dv.getAllExams();
				for(Doctermodel o:list) {
					System.out.println(o.getDoctername()+"\t"+o.getSpeciality()+"\t"+o.getDesgination());
				}
				break;
				
				default:
					System.out.println("worng choice");
		
			}
			
		}
		while(true);
		

	}

}
