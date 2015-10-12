package com.rev.soundex;

public class Soundex {
	 	
	  public static final char[] MAP = {
	    //A  B   C   D   E   F   G   H   I   J   K   L   M
	    'a','b','c','d','a','e','f','a','a','f','c','g','h',
	    //N  O   P   Q   R   S   T   U   V   W   X   Y   Z
	    'i','a','j','c','k','c','c','a','l','a','m','a','f'  	  };
	 
	  /** Convert the given String to its Soundex code.
	   * returns a single string if primary is set to true whereas multiple codes are returned if primary is set to false.
	   */
	  
	  public static String soundex(String s) {
	 
	    s = s.toLowerCase(); 
	    String t = replaceString(s);
	    String result = "";
	    char c, prev = '?';
	    boolean start = false;
	 
	    for (int i=0; i < t.length(); i++) 
	    {
	    	c = t.charAt(i);
	    	if (c>='a' && c<='z' && c != prev) {
		        prev = c;
		        // First char is installed unchanged, for sorting.
		        if (i==0 || start)
		        	result += c;
		        else {
		          if(c=='t'){
		        	  if(i==t.length()-1)
		        		  result += 't';
		        	  else if(t.charAt(i+1)<'a' || t.charAt(i+1)>'z')
		        		  result += 't';
		        	  else
		        		  result += 'c';
		          }
		          else if(c=='g'){
		        	  if(i==t.length()-1)
		        		  result += 'n';
		        	  else if(t.charAt(i+1)<'a' || t.charAt(i+1)>'z')
		        		  result += 'n';
		        	  else
		        		  result += 'f';
		          }
		          else{
			          char m = MAP[c-'a'];
			          if (m != 'a')
			        	  result += m;
			          if (m == 'a' && (i == t.length() - 1) || ( i < t.length()-1 && (t.charAt(i + 1) < 'a' || t.charAt(i + 1) > 'z'))) {
							if (c == 'a')
								result += 'p';
							else if (c == 'i' || c == 'y')
								result += 'q';
							else if (c == 'u' || c == 'w')
								result += 'r';
							else if (c == 'o')
								result += 's';
							else if(c == 'h')
								result += 'u';
							else if(c == 'e')
								result += 'v';
			          }
			          else if(m == 'a' && result.charAt(result.length()-1)!='a')
			        	  result += 'a';
		          }
		        }
		        start = false;
	    	}
	    	else if(c<'a' || c>'z'){
	    		
	    		if(!((c == 0x20) && (prev == c)))
	    			result += c;
	    		prev = c;
	    		start = true;	
	    	}
	    }
	    if (result.length() == 0)
	      return null;
	    for (int i=result.length(); i<4; i++)
	    	result += '0';
	    
	    return result;
	   
	  }
	  
	  
	  public static String replaceString(String str){
	  
		  str = str.replaceAll("bh", "v");
		  str = str.replaceAll("cksh", "x");
		  str = str.replaceAll("cks", "x");
		  str = str.replaceAll("ck", "k");
		  str = str.replaceAll("cs", "x");
		  str = str.replaceAll("dg", "j");
		  str = str.replaceAll("hri", "ri");
		  str = str.replaceAll("ksh", "x");
		  str = str.replaceAll("ks", "x");
		  str = str.replaceAll("oo", "u");
		  str = str.replaceAll("ph", "f");
		  str = str.replaceAll("q", "k");
		  str = str.replaceAll("yu", "u");
		  str = str.replaceAll("ee", "i");
		  str = str.replaceAll("ii", "i");
		  str = str.replaceAll("uu", "u");
		  str = str.replaceAll("aa", "a");
		  
		  return str;
	  }
	


	/** main */
	  public static void main(String[] args) {
	    String[] names = 
	    {
	    		"plus",
	    	    "police",
	    	    "polos",
	    	    "plate",
	    	    "micromax",
	    	    "maikromaiks",
	    	    "space",
	    	    "soaps",
	    	    "spice",
	    	    "shapes",
	    	    "blaa",
	    	    "bla"
	    };
	    for (int i = 0; i< names.length; i++){
	    	String st = Soundex.soundex(names[i]);
	    	System.out.println(st + "(" + names[i]+")");
	    }
	  }
	}