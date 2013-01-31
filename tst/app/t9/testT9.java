package app.t9;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class testT9
{
	@Test public void test_false_match()
	{
		T9 t9 = new T9("a");
		
		String word = t9.getMatch( new char[]{'5'} );
		
		assertEquals("", word);
	}
	
	@Test public void test_true_match()
	{
		T9 t9 = new T9("a");
		
		String word = t9.getMatch( new char[]{'2'} );
		
		assertEquals("a", word);
	}
	
	@Test public void test_suggestion()
	{
		T9 t9 = new T9("heila\nciao");
		
		String word = t9.getSuggestion( new char[]{'2','4','2'} );
		
		assertEquals("ciao", word);
	}
	
	@Test public void test_suggestions()
	{
		ArrayList<String> words1 = new ArrayList<String>();
		
		words1.add("bila");
		words1.add("ciao");
		
		T9 t9 = new T9("bila\nciao");
		
		ArrayList<String> words2 = t9.getSuggestions( new char[]{'2'} );
		
		assertEquals(words1, words2);
	}
	
	
	@Test public void test_loading_dictionary() 
	{		
		T9 t9 = new T9( T9.readFile("dictionary.txt") );
		
		assertTrue( 0 < t9.getWords().size() );
	}
}
