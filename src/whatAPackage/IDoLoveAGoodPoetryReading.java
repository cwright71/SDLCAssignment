package whatAPackage;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;




public class IDoLoveAGoodPoetryReading {
	public static void main(String[] args)
	{
		//*Hashmap because uh...Yeah. HashMaps are easy to stuff things in, and check if the key is already there.
		HashMap<String, Integer> imTheMap = new HashMap<String, Integer>();
		
		boolean startfound = false;
		String teststring = "<h1 class=\"center\">";
		try {
		    List<String> lines = Files.readAllLines(Paths.get("1065-h.htm"));

		    for (String var: lines)
		    {
		    	//Find the title, they used h1. Probably could have just used contains here, but eh. Fun.
				if(var.equals(teststring))
					startfound = true;

				if(startfound == true)
				{
					//Finds the end of the poem, and deletes the line, then breaks out of the loops. Why? Because I wanted to.
					if(var.contains("END OF THE PROJECT"))
						{
							var = "";
							break;
						}
					
					//Kill all the html tags
					var = var.replaceAll("\\<.*?\\>", "");
					//doesn't like reading emdashes, so I nuked them. All of them.
					var = var.replaceAll("&mdash", "");

					//stupid symbols, you aren't words!
					String words[] = var.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");
					
					for(String word: words)
					{
						if(!word.isEmpty())
						{
							//line prints every word line by line :D, just to make sure other garbled nonsense isn't in there. Commented out for final code.
							//System.out.println(word);
							if(imTheMap.containsKey(word)) 
							{
								imTheMap.put(word, imTheMap.get(word)+1);						
							}
							else
							{
								imTheMap.put(word,1);
							}
						}
					}

				}
		    }
		    
		}
		//Yay exception handling... 
		catch (IOException ex) 
		{
		    ex.printStackTrace();
		}
		
		//Turn the hashmap into a list, because collections.
		List<Map.Entry<String, Integer> > hashList = new LinkedList<Map.Entry<String, Integer> >(imTheMap.entrySet());
		//Do a heckin sort.
		Collections.sort(hashList, new Comparator<Map.Entry<String, Integer> > ()
				{
					public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2)
					{
						return (obj1.getValue()).compareTo(obj2.getValue());
					}
				}
				);
		
		//Reverse, reverse! 5 hops this time! (really just swaps the order of the hash list so that it's biggest first. 
		Collections.reverse(hashList);
		//prints the top 10, though can be modified to print more or less :D 
		for(int i = 0; i < 10; i++)
		{
			System.out.println(hashList.get(i));
		}
				
	}
}
