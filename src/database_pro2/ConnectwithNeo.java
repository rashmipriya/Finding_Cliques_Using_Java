package database_pro2;

import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class ConnectwithNeo {
    @SuppressWarnings("resource")
	public static Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();
	public static void main(String[] args) throws Exception {
    	
    	Map<Integer, String> list = new LinkedHashMap<Integer, String>();
		//Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();
    	int increment = 1; String sender="";int number_of_cliques=0;
    	URL oracle = new URL("http://www.cs.uky.edu/~marek/dataset");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null){
        	int trackingthesender =0;
        	List<String> receivers = new ArrayList<String>();
            Scanner input = new Scanner(inputLine);
	        while(input.hasNext()){
	        	String token = input.next();
	        	if(trackingthesender==0){
	        		sender = token;
	        		if(list.containsValue(sender)){
	        			trackingthesender++;
	        		}
	        		else{
	        		list.put(increment,sender);
	        		increment++;
	        		trackingthesender++;
	        		}
	        	}		
	        	else{
	        		 if(list.containsValue(token)){
	        			 receivers.add(token);
	        		}
	        		else{
	        			list.put(increment,token);
	        			increment++;
	        			receivers.add(token);
	        		}
	        	}
	        	map.put(sender, receivers);
	        }
        } 
        in.close();
        for(Map.Entry<String, List<String>> entry : map.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());    		
	    }
        System.out.println("List begins");
        for(Map.Entry<Integer, String> entry : list.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());    		
	    }
    	for(int i=1; i<=list.size(); i++){
    		for(int j=i+1; j<=list.size(); j++){
    			for(int k=j+1; k<=list.size(); k++){
    				//System.out.println(list.get(i) +" " + list.get(j) + " " +list.get(k));
    				Map<Integer, List<String>> newlist = new LinkedHashMap<Integer, List<String>>();
    		    	List<String> helplist = new ArrayList<String>();
    				Collections.addAll(helplist, list.get(i), list.get(j), list.get(k));
    				newlist.put(i+j+k, helplist);
    				for(Map.Entry<Integer,List<String>> entry : newlist.entrySet()){
    	                  System.out.println(entry.getKey() + " " + entry.getValue());    		
    	    	    }
    				String str = clique(list.get(i), list.get(j), list.get(k));
    				if(str== "forms a clique."){
    				System.out.println(" The nodes " + list.get(i) + " " + " " + list.get(j) + " " + list.get(k) + " " + str);
    				number_of_cliques++;
    				}
    				
    			}
    		}
    	}
    	System.out.println("Number of cliques " + number_of_cliques);
    	}
    
      public static String clique(String person_1, String person_2, String person_3){
    	  List<String> linkededges_person1 = new ArrayList<String>();
    	  linkededges_person1 = map.get(person_1);
    	  List<String> linkededges_person2 = new ArrayList<String>();
    	  linkededges_person2 = map.get(person_2);
    	  List<String> linkededges_person3 = new ArrayList<String>();
    	  linkededges_person3 = map.get(person_3);
    	  if(linkededges_person1.contains(person_2) && linkededges_person1.contains(person_3) && linkededges_person2.contains(person_1) && linkededges_person2.contains(person_3) && linkededges_person3.contains(person_1) && linkededges_person3.contains(person_2))
    	  return "forms a clique.";
    	  else
    	  return "does not form a clique.";
      }
        
    }   	