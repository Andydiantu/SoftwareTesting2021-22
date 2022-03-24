package st;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Task3_TDD2 {

	Parser parser;
	
	@Before
	public void setUp() {
		parser = new Parser();
	}
	
	@Test
	public void addAllMultiInitlizeTest() {
		parser.addAll("option1 option2 option3", 
				"o1 o2 o3", "String Integer Boolean");
		
		parser.parse( "-o1=test2 -o2=37 -o3=false");
		
		assertEquals(parser.getString("option1"), "test2");
		assertEquals(parser.getInteger("o2"), 37);
		assertFalse(parser.getBoolean("option3"));
	}
	
	@Test
	public void addAllMultiInitlizeExtraSpaceTest() {
		parser.addAll("option1         option2                            option3", 
				"o1            o2                 o3", "String          Integer          Boolean");
		
		parser.parse( "-o1=test2 -o2=37 -o3=false");
		
		assertEquals(parser.getString("option1"), "test2");
		assertEquals(parser.getInteger("o2"), 37);
		assertFalse(parser.getBoolean("option3"));
	}
	
	@Test
	public void addAllMutiInitializeWithoutShortcut() {
		parser.addAll("o1 o2 o3","String Integer Boolean");
		
		parser.parse( "--o1=test2 --o2=37 --o3=false");
		
		assertEquals(parser.getString("o1"), "test2");
		assertEquals(parser.getInteger("o2"), 37);
		assertFalse(parser.getBoolean("o3"));
	}
	
	@Test
	public void addAllMutiInitializeWithoutShortWithExtraSpacecut() {
		parser.addAll("o1              o2                   o3","String               Integer              Boolean");
		
		parser.parse( "--o1=test2 --o2=37 --o3=false");
		
		assertEquals(parser.getString("o1"), "test2");
		assertEquals(parser.getInteger("o2"), 37);
		assertFalse(parser.getBoolean("o3"));
	}
	
	@Test
	public void addAllMoreOptionTest() {
		parser.addAll("option1 option2 option3", 
				"o1 o2", "String Integer Boolean");
		
		parser.parse( "-o1=test2 -o2=37 --option3=false");
		
		assertEquals(parser.getString("o1"), "test2");
		assertEquals(parser.getInteger("o2"), 37);
		assertFalse(parser.getBoolean("option3"));
	}
	
	@Test
	public void addAllEmptyShortcutTest() {
		parser.addAll("o1 o2 o3", "","String Integer Boolean");
		
		parser.parse( "--o1=test2 --o2=37 --o3=false");
		
		assertEquals(parser.getString("o1"), "test2");
		assertEquals(parser.getInteger("o2"), 37);
		assertFalse(parser.getBoolean("o3"));
	}
	
	@Test
	public void addAllMoreShortcutTest() {
		parser.addAll("option1 option2 option3", 
				"o1 o2 o3 o4 o5", "String Integer Boolean");
		
		parser.parse( "-o1=test2 -o2=37 --option3=false");
		
		assertEquals(parser.getString("option1"), "test2");
		assertEquals(parser.getInteger("o2"), 37);
		assertFalse(parser.getBoolean("option3"));
	}
	
	@Test
	public void addAllMoreTypeTest() {
		parser.addAll("option1 option2 option3", 
				"o1 o2 o3 o4 o5", "String Integer Boolean Boolean Boolean Boolean Integer");
		
		parser.parse( "-o1=test2 -o2=37 --option3=false");
		
		assertEquals(parser.getString("option1"), "test2");
		assertEquals(parser.getInteger("o2"), 37);
		assertFalse(parser.getBoolean("option3"));
	}
	
	@Test
	public void addAllLessTypeTest() {
		parser.addAll("option1 option2 option3", 
				"o1 o2 o3", "String");
		
		parser.parse( "-o1=test2 -o2=37 --option3=false");
		
		assertEquals(parser.getString("option1"), "test2");
		assertEquals(parser.getInteger("o2"), 37);
		assertFalse(parser.getBoolean("option3"));
	}
	
	@Test
	public void addAllLessTypeButMoreTest() {
		parser.addAll("option1 option2 option3", 
				"o1 o2 o3", "String Integer");
		
		parser.parse( "-o1=test2 -o2=37 --option3=false");
		
		assertEquals(parser.getString("option1"), "test2");
		assertEquals(parser.getInteger("o2"), 37);
		assertFalse(parser.getBoolean("option3"));
	}
	
	@Test
	public void groupInitNormalTest() {
		parser.addAll("option7-10 optiona-c optionA-C", "o7-19", "Integer String");
		
		parser.parse("--option7=12 -o8=13 --optionA=hello");
		
		assertEquals(parser.getInteger("o7"), 12);
		assertEquals(parser.getInteger("option8"), 13);
		assertEquals(parser.getString("optionA"), "hello");
	}
	
	@Test
	public void groupInitRangeInclusiveTest() {
		parser.addAll("option7-9", "o9-11", "Integer");
		
		parser.parse("--option9=12");
		assertEquals(parser.getInteger("o11"), 12);
	}
	
	@Test
	public void groupInitInvalidGroupTest() {
		parser.addAll("o3-A option3 o8-12B option23 sdfC-d optF-9   ", "Integer Integer Boolean");
		
		parser.parse("--option3=12 --option23=false");
		
		assertEquals(parser.getInteger("option3"), 12);
		assertFalse(parser.getBoolean("option23"));
	}
	
	@Test
	public void groupInitTypeGroupcorrespondTest() {
		parser.addAll("option3-6 option8-9", "Integer String");
		
		parser.parse("--option3=12 --option6=19");
		parser.parse("--option8=3.txt --option9=67.txt");
		
		assertEquals(parser.getInteger("option3"), 12);
		assertEquals(parser.getInteger("option6"), 19);
		assertEquals(parser.getString("option8"), "3.txt");
		assertEquals(parser.getString("option9"), "67.txt");
	}
	
	@Test
	public void groupInitLastCharTest() {
		parser.addAll("option129-11","o12", "String");
		
		parser.parse("--option129=3.txt --option1210=4.txt --option1211=9.txt");
		
		assertEquals(parser.getString("option129"), "3.txt");
		assertEquals(parser.getString("option1210"), "4.txt");
		assertEquals(parser.getString("option1211"), "9.txt");
	}
	
	@Test
	public void groupInitNotEndRangeTest() {
		parser.addAll("o2-4ab option23 oa-B23 oa-cAV", "Integer Boolean");{
		parser.parse("--option23=true");
			
		assertTrue(parser.getBoolean("option23"));
		}
	}
	
	@Test
	public void groupInitDecreaseRangeTest() {
		parser.addAll("option9-7", "o7-5", "Character");
		
		parser.parse("--option9=i --option7=t");
		assertEquals(parser.getCharacter("option9"), 'i');
		assertEquals(parser.getCharacter("option7"), 't');
	}
	
	@Test
	public void groupInitDecreaseLetterRangeTest() {
		parser.addAll("optionA-E", "oc-a", "Character");
		
		parser.parse("--optionA=i --optionE=t");
		assertEquals(parser.getCharacter("oc"), 'i');
		assertEquals(parser.getCharacter("optionE"), 't');
	}
	
	
	
	@Test
	public void groupInitMultiOptionGroupToOneShortcutGroupTest() {
		parser.addAll("option1-2 option3-5", "oA-E", "String Boolean");
		
		parser.parse("-oA=3.txt -oC=true -oE=false");
		assertEquals(parser.getString("option1"), "3.txt");
		assertTrue(parser.getBoolean("oC"));
		assertFalse(parser.getBoolean("oE"));
	}
	
	@Test
	public void groupInitMultiOptionGroupToMultiShortcutGroupTest() {
		parser.addAll("option1-2 option3-5", "oA-C sc32 o1-3", "String Boolean");
		
		parser.parse("-oA=3.txt -oC=true -o1=false");
		assertEquals(parser.getString("option1"), "3.txt");
		assertTrue(parser.getBoolean("oC"));
		assertFalse(parser.getBoolean("o1"));
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void groupInitIvalidTypeTest() {
		parser.addAll("option3, optoin4", "NOTYPE String");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void groupInitIvalidTypeTest2() {
		parser.addAll("option3, optoin4", "o3","Integer INVALID");
	}

}
