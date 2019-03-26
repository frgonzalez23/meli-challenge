package com.fjgonzalez.meli.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
public class DnaUtils {
	public final static int MIN_UNITS_MUTANT= 2;
	public static Boolean isValidDna(JSONArray dna) {
		int size=0;
		if(dna!=null && dna.size()>=4)
		   size = dna.size();
  		if(size<4)
  		   return false;
 
		String rule = "^(\\[)(\\\"[ATCG]{"+size+"}\\\"){1}(\\,\\\"[ATCG]{"+size+"}\\\"){"+(size-1)+"}(\\])$";	
		return Pattern.matches(rule, dna.toString().replaceAll("\\s", ""));
	}
	public static Boolean isMutant(JSONArray dna) {
		int dnaSize=dna.size();
		int count=0;
		OUTER_LOOP:
		for(int i=0; i<dnaSize; i++)
		{   
			count+=checkDNA((String)dna.get(i));
			if(count>=DnaUtils.MIN_UNITS_MUTANT)
				break OUTER_LOOP;
			StringBuffer vertical=new StringBuffer();
			StringBuffer oblique1=new StringBuffer();
			StringBuffer oblique2=new StringBuffer();
			StringBuffer oblique3=new StringBuffer();
			StringBuffer oblique4=new StringBuffer();
			for(int j=0; j<dnaSize; j++) 
			{
				vertical.append(dna.get(j).toString().charAt(i));
				int ob1j=i+j;
				int ob2i=dnaSize-1-j;
				int ob3j=dnaSize-2-j-i;
				
				if(ob1j<dnaSize)
				{  
				   oblique1.append(dna.get(j).toString().charAt(ob1j));
				   oblique2.append(dna.get(ob2i).toString().charAt(ob1j));
				}  
				if(ob3j>=0)
				{  
				   oblique3.append(dna.get(j).toString().charAt(ob3j));
				   oblique4.append(dna.get(ob2i).toString().charAt(ob3j));
				}
			}
			count+=checkDNA(vertical.toString());
			if(count>=DnaUtils.MIN_UNITS_MUTANT)
				break OUTER_LOOP;
			count+=checkDNA(oblique1.toString());
			if(count>=DnaUtils.MIN_UNITS_MUTANT)
				break OUTER_LOOP;
			count+=checkDNA(oblique2.toString());
			if(count>=DnaUtils.MIN_UNITS_MUTANT)
				break OUTER_LOOP;
			count+=checkDNA(oblique3.toString());
			if(count>=DnaUtils.MIN_UNITS_MUTANT)
				break OUTER_LOOP;
			count+=checkDNA(oblique4.toString());
			if(count>=DnaUtils.MIN_UNITS_MUTANT)
				break OUTER_LOOP;
		}
		if(count>=DnaUtils.MIN_UNITS_MUTANT)
		return true;
		return false;
	}
	private static int checkDNA(String dna) {
		
		String rule="A{4,}|T{4,}|G{4,}|C{4,}";
		
		Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(dna);

        int count = 0;
        while (matcher.find() && count < DnaUtils.MIN_UNITS_MUTANT)
            count++;
		
        return count;
	}
}