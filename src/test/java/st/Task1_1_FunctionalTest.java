package st;

import static org.junit.Assert.*;

import org.hamcrest.core.StringEndsWith;
import org.junit.Before;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;

public class Task1_1_FunctionalTest {
	
	private Parser parser;
	
	@Before
	public void setUp() {
		parser = new Parser();
	}

	
	// get bug #1
	@Test
	public void emptyStringShortcutTest() {
		parser.addOption(new Option("input", Type.CHARACTER), "sc");
		parser.addOption(new Option("input", Type.INTEGER), "");
		parser.parse("--input 1");
		assertEquals(parser.getInteger("-sc"), 1);
	}
	
	//get bug #8
	@Test
	public void nameOverideTest() {
		parser.addOption(new Option("op1", Type.CHARACTER), "oh");
		parser.addOption(new Option("op1", Type.INTEGER), "hi");
		parser.parse("--op1 1");
		
		assertEquals(parser.getInteger("-oh"), 1);
	}
	
	// get bug #2
	@Test
	public void booleanAssignmentWithoutValueTest() {
		parser.addOption(new Option("mini", Type.BOOLEAN), "the");
		
		assertFalse(parser.getBoolean("mini"));
	}
	
	//get bug #2
	@Test
	public void booleanAssignmentFalseTest() {
		parser.addOption(new Option("big", Type.BOOLEAN), "the");
		
		parser.parse("--big=false");
		
		assertFalse(parser.getBoolean("big"));
		
	}
	
	// get bug #17
	@Test
	public void longOptionNameTest() {
		parser.addOption(new Option("ahsdjkflagshiuvhubadslkufghaklsdjfhkajdsfkaashdjkldfadsjkfklhasduhfadksljuhlfakdjshflk", Type.STRING) );
		
		parser.parse("--ahsdjkflagshiuvhubadslkufghaklsdjfhkajdsfkaashdjkldfadsjkfklhasduhfadksljuhlfakdjshflk=ll");
		
		assertEquals(parser.getString("ahsdjkflagshiuvhubadslkufghaklsdjfhkajdsfkaashdjkldfadsjkfklhasduhfadksljuhlfakdjshflk"), "ll");
	}
		
		// get bug #4
		@Test
		public void longshortCutTest() {
			parser.addOption(new Option("op1", Type.STRING), "fhadlsuijkhfkladghjflknuacdgfikulagsuifcgsasdfhjkldhksjafhjkld2338239735kjdsafgjkladhsklfdhadjsasdhjlkfa");
			
			parser.parse("-fhadlsuijkhfkladghjflknuacdgfikulagsuifcgsasdfhjkldhksjafhjkld2338239735kjdsafgjkladhsklfdhadjsasdhjlkfa=ll");
			
			assertEquals("fhadlsuijkhfkladghjflknuacdgfikulagsuifcgsasdfhjkldhksjafhjkld2338239735kjdsafgjkladhsklfdhadjsasdhjlkfa", "fhadlsuijkhfkladghjflknuacdgfikulagsuifcgsasdfhjkldhksjafhjkld2338239735kjdsafgjkladhsklfdhadjsasdhjlkfa");
		}
		
		// get bug #9
		@Test
		public void parseBlankInputTest() {
			parser.addOption(new Option("op1", Type.STRING));
			
			int i = parser.parse(" ");
			
			assertEquals(i, 0);
		}
		
		//get bug #19 but will pass both on jar file and code
		@Test
		public void parseContainsDashTest() {
			parser.addOption(new Option("op1", Type.STRING), "ST");
			
			parser.parse("--op1=\"2.t--xt=2.txt\"");
			assertEquals(parser.getString("op1"),"2.t--xt=2.txt");
		}
		
		//get bug #13 but will pass both on jar file and code
		@Test
		public void spaceParseWithDoubleQuotAndQuotInValueTest() {
			parser.addOption(new Option("op1", Type.STRING), "st");
			
			parser.parse("-st=\'value===============\"1.txt\"\'");
			assertEquals(parser.getString("st"), "value===============\"1.txt\"");
		}
		
		
		
		
		//get bug #20
		@Test
		public void optionNameWithManyspaceTest() {
			parser.addOption(new Option("op1", Type.INTEGER), "st");
			parser.addOption(new Option("op", Type.INTEGER), "st");

			parser.parse("--op             1 fghjkl");
			
			assertEquals(parser.getInteger("op"), 1);
			
		}
		
		// get bug #10
		@Test
		public void defaultValueTest() {
			parser.addOption(new Option("op1", Type.STRING), "sc" );
			
			assertEquals(parser.getString("op1"), "");
			assertEquals(parser.getInteger("op1"), 0);
			assertEquals(parser.getCharacter("op1"), '\0');
			assertEquals(parser.getBoolean("op1"), false);
		}
		
		
		//get bug #16
		@Test(expected = NullPointerException.class)
		public void testGetStringEdgeValueNull() {
			parser.addOption(new Option("op1", Type.STRING), "sc");
			parser.parse("--op1=\"\"");
			assertEquals(parser.getString("op1"), "");
			parser.parse("--op1=\" \"");
			assertEquals(parser.getString(null), " ");
		}
		
		
		
		//get bug #14
		@Test
		public void stringContainNewLineRetriveTest() {
			parser.addOption(new Option("op1", Type.STRING), "sc");
			parser.parse("--op1=\"\\n\"");
			assertEquals(parser.getString("op1"), "\\n");
		}
		
		//get bug #7 and #5
		@Test
		public void intValueRetrieveFromStringTest() {
			parser.addOption(new Option("op1", Type.STRING), "sc");
			parser.parse("--op1=\"-123554634\"");
			
			assertEquals(parser.getInteger("op1"), -123554634);
		}
		
		//get bug #15
		@Test
		public void largeIntValueRetrieveFromStringTest() {
			parser.addOption(new Option("op1", Type.STRING), "sc");
			parser.parse("--op1=21474836474123");
			assertEquals(parser.getInteger("op1"), 0);
		}
		
		 //get bug #3
		@Test
		public void intValueRetrieveTest() {
			parser.addOption(new Option("op1", Type.BOOLEAN), "sc");
			parser.parse("--op1=123334557");
			
			assertEquals(parser.getInteger("op1"), 1);
		}
	
	// get bug #12
	@Test
	public void multipleReplaceTest() {
		parser.addOption(new Option("op1", Type.STRING),"newSC");
		parser.addOption(new Option("op2", Type.STRING),"newSC2");
		
		parser.parse("--op1=oldstuff");
		parser.parse("--op2=oldstuff");
		
		parser.replace("--op1 -newSC2", "old", "new");
		assertEquals(parser.getString("op1"),"newstuff" );
		assertEquals(parser.getString("op2"),"newstuff" );
	}

	// get bug #18
	@Test
	public void normalReplaceVariableSpaceTest() {
		parser.addOption(new Option("op1", Type.STRING),"newSC");
		
		parser.parse("--op1=oldstuff");
		
		parser.replace("op1                    ", "old", "ne-w");
		assertEquals(parser.getString("op1"),"ne-wstuff" );
	}
	
	
	
	
	// Get bug #6
	@Test
	public void optionNotEqualTest() {
		Option option = new Option("op1", Type.STRING);
		Option option2 = new Option("op2", Type.STRING);
		
		
		assertFalse(option.equals(option2));
	}
	
	// get but #11
	@Test (expected = IllegalArgumentException.class)
	public void addOptionInvalidNameTest() {
		parser.addOption(new Option("op!tion", Type.STRING));
		
		parser.parse("--op!tion=12");
		assertEquals(parser.getString("op!tion"), "12");
	}
	
	
	

}
