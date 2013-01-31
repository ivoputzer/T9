package app.t9;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class T9
{
	HashMap<Character,String> keys = new HashMap<Character,String>();	
	
	private String dictionary;

	public T9 ( String dictionary ) 
	{
		this.dictionary = dictionary;
		
		this.keys.put( '1' , "[.,!?,;]{1}");
		this.keys.put( '2' , "[abc]{1}");
		this.keys.put( '3' , "[def]{1}"); 
		this.keys.put( '4' , "[ghi]{1}");
		this.keys.put( '5' , "[jkl]{1}");
		this.keys.put( '6' , "[mno]{1}");
		this.keys.put( '7' , "[pqrs]{1}");
		this.keys.put( '8' , "[tuv]{1}");
		this.keys.put( '9' , "[wxyz]{1}");
		
	}
	
	public ArrayList<String> getWords()
	{
		ArrayList<String> words = new ArrayList<String>();

    	Pattern p = Pattern.compile("^\\w$", Pattern.MULTILINE);
    	
        Matcher m = p.matcher(this.dictionary); 
        
        while( m.find() ) words.add( m.group() );
		
		return words;
	}
	
	private String getPattern( char[] digits )
	{
		String pattern = "";
		
		for ( int i = 0; i < digits.length; pattern += this.keys.get( digits[i++] ) );
		
		return pattern;
	}
	
	/* exactly matching words */
		
	public String getMatch( char[] digits )
	{		
    	Pattern p = Pattern.compile("^(" + this.getPattern(digits) + ")$", Pattern.MULTILINE);
		
		Matcher m = p.matcher(this.dictionary);
    	    	        
        return m.find() ? m.group() : "";
	}
	
	public ArrayList<String> getMatches( char[] digits )
	{
		ArrayList<String> matches = new ArrayList<String>();
		
    	Pattern p = Pattern.compile("^(" + this.getPattern(digits) + ")$", Pattern.MULTILINE);
    	    	
        Matcher m = p.matcher(this.dictionary); 
        
        while( m.find() ) matches.add( m.group() );
        
		return matches;
	}

	/* words starting with */
	
	public String getSuggestion( char[] digits )
	{		
    	Pattern p = Pattern.compile("^(" + this.getPattern(digits) + ".*)$", Pattern.MULTILINE);
		
		Matcher m = p.matcher(this.dictionary);
    	    	        
        return m.find() ? m.group() : "";
	}
	
	public ArrayList<String> getSuggestions(char[] digits)
	{
		ArrayList<String> suggestions = new ArrayList<String>();
		
    	Pattern p = Pattern.compile("^(" + this.getPattern(digits) + ".*)$", Pattern.MULTILINE);
    	    	
        Matcher m = p.matcher(this.dictionary); 
        
        while( m.find() ) suggestions.add( m.group() );
        
		return suggestions;
	}
	
	public static String readFile(String path) // static method helper for loading file contents > string
	{
		try {
			return new Scanner( new File(path), "UTF-8" ).useDelimiter("\\A").next().toLowerCase();
			
		} catch (Exception e) { throw new RuntimeException("Unable to read the provided file!"); }
	}
}
