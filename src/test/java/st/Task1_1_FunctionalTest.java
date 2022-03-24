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
		parser.addOption(new Option("input", Type.STRING), "");
	}
	
	@Test
	public void emptyStringNameTest() {
		parser.addOption(new Option("f23", Type.STRING), "oz");
	}
	
	//get bug #8
	@Test
	public void nameOverideTest() {
		parser.addOption(new Option("op1", Type.STRING), "oh");
		parser.parse("--op1 2.txt");
		parser.addOption(new Option("op1", Type.STRING), "hi");
		parser.parse("--op1 3.txt");
		
		assertEquals(parser.getString("-oh"), "3.txt");
		//assertEquals(parser.getString("op1"), "3.txt");
		
		
	}
	
	@Test
	public void nameCaseSensitiveTest() {
		parser.addOption(new Option("oP1", Type.STRING), "hi");
		parser.addOption(new Option("op1", Type.STRING), "hi");
	}
	
	@Test
	public void sameNameAndShortCutWithUnderscoreTest() {
		parser.addOption(new Option("op2", Type.STRING), "op1");
		parser.addOption(new Option("op____________________________________________1", Type.STRING), "hi");
		
		parser.parse("--op2 1.txt" );
		parser.parse("--op____________________________________________1 2.txt" );
		
		assertEquals(parser.getString("op____________________________________________1"),"2.txt");
		assertEquals(parser.getString("op2"),"1.txt");
		}
	
	@Test
	public void shortCutAssignmentTest() {
		parser.addOption(new Option("op2", Type.STRING), "op1");
	
		parser.parse("-op1 2.txt" );
		
		assertEquals(parser.getString("op1"),"2.txt");
		}
	
	@Test
	public void praseNoValueSpecifyTest() {
		parser.addOption(new Option("op5", Type.STRING), "op3");
		
		parser.parse("--op5=");
	}
	
	@Test
	public void parseWithBothSingleAndDoubleQuoteTest() {
		parser.addOption(new Option("op5", Type.STRING), "op3");
		
		parser.parse("--op5=\'hdjskalfh\"");
		
		assertEquals(parser.getString("op5"), "\'hdjskalfh\"");
		parser.parse("--op5=\"hdjskalfh\'");
		assertEquals(parser.getString("op5"), "\"hdjskalfh\'");
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
	
	@Test
	public void boolean0AssignmentTest() {
		parser.addOption(new Option("big", Type.BOOLEAN), "the");
		
		parser.parse("--big=0");
		
		assertFalse(parser.getBoolean("big"));
	}
	
	@Test (expected = RuntimeException.class)
	public void lookupOptionByNameNotExistTest() {
		parser.addOption(new Option("op1", Type.STRING), "sc" );
		
	    parser.getInteger("op134");
	}
	
	@Test
	public void differentOptionTypeTest() {
		parser.addOption(new Option("op1", Type.STRING));
		parser.addOption(new Option("op2", Type.INTEGER));
		parser.addOption(new Option("op3", Type.BOOLEAN));
		parser.addOption(new Option("op4", Type.CHARACTER));

		
		parser.parse("--op1=false");
		parser.parse("--op2=false");
		parser.parse("--op3=false");
		parser.parse("--op4=false");
		
		assertEquals(parser.getString("op1"), "false");
		assertEquals(parser.getString("op2"), "false");
		assertEquals(parser.getBoolean("op3"), false);
		assertEquals(parser.getString("op4"), "false");
	}
	
	// get bug #17
	@Test
	public void longOptionNameTest() {
		parser.addOption(new Option("ahsdjkflagshiuvhubadslkufghaklsdjfhkajdsfkaashdjkldfadsjkfklhasduhfadksljuhlfakdjshflk", Type.STRING) );
		
		parser.parse("--ahsdjkflagshiuvhubadslkufghaklsdjfhkajdsfkaashdjkldfadsjkfklhasduhfadksljuhlfakdjshflk=ll");
		
		assertEquals(parser.getString("ahsdjkflagshiuvhubadslkufghaklsdjfhkajdsfkaashdjkldfadsjkfklhasduhfadksljuhlfakdjshflk"), "ll");
	}
	
	
		@Test
		public void longOptionNamewithShortCutTest() {
			parser.addOption(new Option("ahsdjkflagshiuvhubadslkufghaklsdjfhkajdsfkaashdjkldfadsjkfklhasduhfadksljuhlfakdjshflk", Type.STRING), "st2" );
			
			parser.parse("-st2=ll");
			
			assertEquals(parser.getString("st2"), "ll");
		}
		
		
		// get bug #4
		@Test
		public void longshortCutTest() {
			parser.addOption(new Option("op1", Type.STRING), "fhadlsuijkhfkladghjflknuacdgfikulagsuifcgsasdfhjkldhksjafhjkld2338239735kjdsafgjkladhsklfdhadjsasdhjlkfa");
			
			parser.parse("-fhadlsuijkhfkladghjflknuacdgfikulagsuifcgsasdfhjkldhksjafhjkld2338239735kjdsafgjkladhsklfdhadjsasdhjlkfa=ll");
			
			assertEquals("fhadlsuijkhfkladghjflknuacdgfikulagsuifcgsasdfhjkldhksjafhjkld2338239735kjdsafgjkladhsklfdhadjsasdhjlkfa", "fhadlsuijkhfkladghjflknuacdgfikulagsuifcgsasdfhjkldhksjafhjkld2338239735kjdsafgjkladhsklfdhadjsasdhjlkfa");
		}
		
		
		@Test
		public void parseNullInputTest() {
			parser.addOption(new Option("op1", Type.STRING));
			
			parser.parse(null);
		}
		
		@Test
		public void parseEmptyInputTest() {
			parser.addOption(new Option("op1", Type.STRING));
			
			parser.parse("");
		}
		
		// get bug #9
		@Test
		public void parseBlankInputTest() {
			parser.addOption(new Option("op1", Type.STRING));
			
			parser.parse(" ");
		}
		
		// get bug #9
		@Test
		public void parseManyBlankInputTest() {
			parser.addOption(new Option("op1", Type.STRING));
			
			parser.parse("                                                                            ");
		}
		
		@Test
		public void spaceParseTest() {
			parser.addOption(new Option("op1", Type.STRING), "st");
			
			parser.parse("--op1 1.txt");
			assertEquals(parser.getString("op1"), "1.txt");
		}
		
		
		@Test
		public void equalSignParseTest() {
			parser.addOption(new Option("op1", Type.STRING), "st");
			
			parser.parse("--op1=1.txt");
			assertEquals(parser.getString("op1"), "1.txt");
		}
		
		@Test
		public void spaceParseShortCutTest() {
			parser.addOption(new Option("op1", Type.STRING), "st");
			
			parser.parse("-st 1.txt");
			assertEquals(parser.getString("st"), "1.txt");
		}
		
		
		@Test
		public void equalSignParseShortCutTest() {
			parser.addOption(new Option("op1", Type.STRING), "st");
			
			parser.parse("-st=1.txt");
			assertEquals(parser.getString("st"), "1.txt");
		}
		
		@Test
		public void spaceParseWithQuotTest() {
			parser.addOption(new Option("op1", Type.STRING), "st");
			
			parser.parse("--op1=\"1.txt\"");
			assertEquals(parser.getString("op1"), "1.txt");
		}
		
		@Test
		public void spaceParseWithSingleQuotTest() {
			parser.addOption(new Option("op1", Type.STRING), "st");
			
			parser.parse("--op1=\'1.txt\'");
			assertEquals(parser.getString("op1"), "1.txt");
		}
		
		@Test
		public void spaceParseWithSingleQuotShortCutTest() {
			parser.addOption(new Option("op1", Type.STRING), "st");
			
			parser.parse("-st=\'1.txt\'");
			assertEquals(parser.getString("st"), "1.txt");
		}
		
		@Test
		public void spaceParseWithDoubleQuotShortCutTest() {
			parser.addOption(new Option("op1", Type.STRING), "st");
			
			parser.parse("-st=\"1.txt\"");
			assertEquals(parser.getString("st"), "1.txt");
		}
		
		//get bug #19
		@Test
		public void parseContainsDashTest() {
			parser.addOption(new Option("op1", Type.STRING), "ST");
			
			parser.parse("--op1=\"2.txt--\"");
			assertEquals(parser.getString("op1"),"2.txt--");
		}
		
		//get bug #13
		@Test
		public void spaceParseWithDoubleQuotAndQuotInValueTest() {
			parser.addOption(new Option("op1", Type.STRING), "st");
			
			parser.parse("-st=\'value=\"1.txt\"\'");
			assertEquals(parser.getString("st"), "value=\"1.txt\"");
		}
		
		@Test
		public void spaceParseWithDoubleQuotAndQuotInValueReverseTest() {
			parser.addOption(new Option("op1", Type.STRING), "st");
			
			parser.parse("-st=\"value=\'1.txt\'\"");
			assertEquals(parser.getString("st"), "value=\'1.txt\'");
		}
		
		
		//get bug #20
		@Test
		public void optionNameWithManyspaceTest() {
			parser.addOption(new Option("op1", Type.INTEGER), "st");
			parser.addOption(new Option("op", Type.INTEGER), "st");

			parser.parse("--op             1 jkadsl");
			
		}
		
		@Test
		public void multipleAssignmentParseTest() {
			parser.addOption(new Option("op1", Type.STRING), "st");
			
			parser.parse("--op1=1.txt");
			assertEquals(parser.getString("st"), "1.txt");
			
			parser.parse("--op1=andy.doc");
			assertEquals(parser.getString("op1"), "andy.doc");
			
			parser.parse("-st=andy3.doc");
			assertEquals(parser.getString("op1"), "andy3.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
			
			parser.parse("-st=andy5.doc");
			assertEquals(parser.getString("st"), "andy5.doc");
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
		
		@Test
		public void multiAssignmentinSingleParseTest() {
			parser.addOption(new Option("op1", Type.STRING), "sc" );
			parser.addOption(new Option("op2", Type.STRING), "sc2" );
			parser.addOption(new Option("op3", Type.STRING), "sc3" );
			parser.addOption(new Option("op4", Type.STRING), "sc4" );
			parser.addOption(new Option("op5", Type.STRING), "sc5" );
			
			parser.parse("--op1=1.txt --op2=2.txt -sc3=3.txt --op4=4.txt -sc5=5.txt");
			assertEquals(parser.getString("op1"), "1.txt");
			assertEquals(parser.getString("op2"), "2.txt");
			assertEquals(parser.getString("op3"), "3.txt");
			assertEquals(parser.getString("op4"), "4.txt");
			assertEquals(parser.getString("op5"), "5.txt");
		}
		
		@Test
		public void getASCIIIntFromCharTest() {
			parser.addOption(new Option("op1", Type.CHARACTER), "sc" );
			
			parser.parse("--op1=f");
			
			assertEquals(parser.getCharacter("op1"), 'f');
			assertEquals(parser.getInteger("op1"), 102);
			
		}
		
		@Test
		public void getASCIIIntFromCharSpaceTest() {
			parser.addOption(new Option("op1", Type.CHARACTER), "sc" );
			
			parser.parse("--op1=\'  \'");
			
			assertEquals(parser.getCharacter("op1"), ' ');
			assertEquals(parser.getInteger("op1"), 32);
		}
		
		@Test
		public void keyWordValueTest() {
			parser.addOption(new Option("op1",Type.STRING), "sc");
			
			parser.parse("--op1={D_QUOTE}");
			assertEquals(parser.getString("op1"), "\"");
			
			parser.parse("--op1={S_QUOTE}");
			assertEquals(parser.getString("op1"), "\'");
			
			parser.parse("--op1={SPACE}");
			assertEquals(parser.getString("op1"), " ");

			parser.parse("--op1={DASH}");
			assertEquals(parser.getString("op1"), "-");
			
			parser.parse("--op1={EQUALS}");
			assertEquals(parser.getString("op1"), "=");
			
		}
		
		
		@Test
		public void nameShortcutSameNameRetrieveValueTest() {
			parser.addOption(new Option("op1", Type.STRING), "sc");
			
			parser.addOption(new Option("sc", Type.STRING), "sc2");
			
			parser.parse("--sc=2.txt");
			parser.parse("-sc=3.txt");
			
			assertEquals(parser.getString("sc"),"2.txt");
		}
		
		@Test(expected = RuntimeException.class)
		public void optionNotDefineExceptionTest() {
			parser.getString("sdffdsf");
		}
		
		@Test
		public void onlyCheckOptionValueTest() {
			
			parser.addOption(new Option("op1", Type.STRING), "sc");
			parser.addOption(new Option("sc", Type.STRING), "sc2");
			
			parser.parse("--sc=2.txt");
			parser.parse("-sc=3.txt");
			
			assertEquals(parser.getString("--sc"), "2.txt");
			assertEquals(parser.getString("-sc"), "3.txt");
		}
		
		@Test
		public void testGetStringEdgeValue() {
			parser.addOption(new Option("op1", Type.STRING), "sc");
			parser.parse("--op1=\"\"");
			assertEquals(parser.getString("op1"), "");
			parser.parse("--op1=\" \"");
			assertEquals(parser.getString("op1"), " ");
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
		
		
		
		
		@Test
		public void testGetLongString() {
			parser.addOption(new Option("op1", Type.STRING), "sc");
			parser.parse("--op1=djsahkfladhjksfladkhsjfadlhsjkfhajldsfyadsncfyiugqenwyufngquygbvckuenfghkuleszkfgyjuknds");
			assertEquals(parser.getString("op1"), "djsahkfladhjksfladkhsjfadlhsjkfhajldsfyadsncfyiugqenwyufngquygbvckuenfghkuleszkfgyjuknds");
			parser.parse("--op1=\"                                                                      \"");
			assertEquals(parser.getString("op1"), "                                                                      ");
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
		
		
		@Test
		public void largeIntValueRetrieveTest() {
			parser.addOption(new Option("op1", Type.INTEGER), "sc");
			parser.parse("--op1=2147483647412");
			assertEquals(parser.getInteger("op1"), 0);
		}
		
		//get bug #15
		@Test
		public void largeIntValueRetrieveFromStringTest() {
			parser.addOption(new Option("op1", Type.STRING), "sc");
			parser.parse("--op1=21474836474123");
			assertEquals(parser.getInteger("op1"), 0);
		}
		
		@Test
		public void largeIntValueRetrieveFromCharTest() {
			parser.addOption(new Option("op1", Type.CHARACTER), "sc");
			parser.parse("--op1=1234567890");
			assertEquals(parser.getInteger("op1"), 49);
		}
		
		 //get bug #3
		@Test
		public void intValueRetrieveTest() {
			parser.addOption(new Option("op1", Type.BOOLEAN), "sc");
			parser.parse("--op1=123334557");
			
			assertEquals(parser.getInteger("op1"), 1);
		}
		
		@Test
		public void getIntegerBooleanFalseTest() {
			parser.addOption(new Option("op1", Type.BOOLEAN));
			
			parser.parse("--op1=false");
			
			assertEquals(parser.getInteger("op1"), 0);
		}
		
		
		
		
		
		
		
		@Test
		public void nameAndShortcutExistTest() {
			parser.addOption(new Option("op12", Type.INTEGER), "sc12");
			parser.addOption(new Option("op13", Type.CHARACTER), "sc13");
			parser.addOption(new Option("sc12", Type.CHARACTER), "sc13");
			
			assertTrue(parser.shortcutExists("sc12"));
			assertTrue(parser.optionExists("op12"));
			
			assertFalse(parser.shortcutExists("sc17"));
			assertFalse(parser.optionExists("op20"));
			
			assertTrue(parser.optionOrShortcutExists("sc12"));
			assertFalse(parser.optionOrShortcutExists("op34"));
			assertTrue(parser.optionOrShortcutExists("sc13"));
			
		}
		
		
		
	@Test
	public void normalReplaceTest() {
		parser.addOption(new Option("op1", Type.STRING),"newSC");
		
		parser.parse("--op1=oldstuff");
		
		parser.replace("op1", "old", "new");
		assertEquals(parser.getString("op1"),"newstuff" );
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
	
	@Test
	public void normalReplaceWithNotMatchingPatternTest() {
		parser.addOption(new Option("op1", Type.STRING),"newSC");
		
		parser.parse("--op1=olstuff");
		
		parser.replace("op1", "old", "new");
		assertEquals(parser.getString("op1"),"olstuff" );
	}
	
	@Test
	public void normalReplaceWithBlankTest() {
		parser.addOption(new Option("op1", Type.STRING),"newSC");
		
		parser.parse("--op1=\"\"");
		
		parser.replace("op1", "old", "new");
		assertEquals(parser.getString("op1"),"" );
	}
	
	@Test
	public void replaceOriginalValueNullTest() {
		parser.addOption(new Option("op1", Type.STRING),"newSC");
		
		parser.parse("--op1=oldstuff");
		
		parser.replace("op1", "old", "new");
		assertEquals(parser.getString("op1"),"newstuff" );
	}
	
	@Test
	public void normalReplaceWithSpaceTest() {
		parser.addOption(new Option("op1", Type.STRING),"newSC");
		
		parser.parse("--op1=\"o                          ld\"");
		
		parser.replace("op1", "o                          ld", "new");
		assertEquals(parser.getString("op1"),"new" );
	}
	
	@Test
	public void normalReplaceNullValueTest() {
		parser.addOption(new Option("op1", Type.STRING),"newSC");
		
		
		parser.replace("op1", "old", "new");
		assertEquals(parser.getString("op1"),"" );
	}
	
	
	@Test
	public void normalReplaceWithDashTest() {
		parser.addOption(new Option("op1", Type.STRING),"newSC");
		
		parser.parse("--op1=oldstuff");
		
		parser.replace("op1", "old", "ne-w");
		assertEquals(parser.getString("op1"),"ne-wstuff" );
	}

	// get bug #18
	@Test
	public void normalReplaceVariableSpaceTest() {
		parser.addOption(new Option("op1", Type.STRING),"newSC");
		
		parser.parse("--op1=oldstuff");
		
		parser.replace("op1                    ", "old", "ne-w");
		assertEquals(parser.getString("op1"),"ne-wstuff" );
	}
	
	
	@Test
	public void replaceNotExistTest() {
		parser.addOption(new Option("op23333", Type.STRING), "sc");
		
		parser.parse("--op23333=oldVIEW");
		
		parser.replace("sc", "old", "new");
		assertEquals(parser.getString("sc"), "newVIEW");
	}
	
	@Test
	public void setShortCutNormalTest() {
		parser.addOption(new Option("op1", Type.STRING));
		
		parser.parse("--op1=2.txt");
		parser.setShortcut("op1", "sc");
		
		assertEquals(parser.getString("sc"), "2.txt");
	}
	
	@Test
	public void setShortCutWithLongSCTest() {
		parser.addOption(new Option("op1", Type.STRING));
		
		parser.parse("--op1=2.txt");
		parser.setShortcut("op1", "scahdslfkjasiuoeufhaljkshdfklasfaffagfsdgs");
		
		assertEquals(parser.getString("scahdslfkjasiuoeufhaljkshdfklasfaffagfsdgs"), "2.txt");
	
	}
	
	@Test
	public void setShortCutNotExistNameTest() {

		parser.setShortcut("op1", "sc");
	}
	
	// get bug #8
	@Test
	public void setShortCutMultipleAssginmentTest() {
		parser.addOption(new Option("op1", Type.STRING));
		
		parser.parse("--op1=2.txt");
		parser.setShortcut("op1", "sc");
		parser.setShortcut("op1", "sc123");
		parser.setShortcut("op1", "sc345");
		
		assertEquals(parser.getString("sc"), "2.txt");
		assertEquals(parser.getString("sc123"), "2.txt");
		assertEquals(parser.getString("sc345"), "2.txt");
		
		parser.addOption(new Option("op1", Type.STRING));
		parser.parse("--op1=70.txt");
		
		assertEquals(parser.getString("sc"), "70.txt");
		assertEquals(parser.getString("sc123"), "70.txt");
		assertEquals(parser.getString("sc345"), "70.txt");
	}
	
	@Test
	public void optionGetAndSetNameTest() {
		Option option = new Option("op1", Type.STRING);
		
		assertEquals(option.getName(), "op1");
		
		option.setName("op3");
		assertEquals(option.getName(), "op3");
		
	}
	
	@Test
	public void optionGetAndSetTypeTest() {
		Option option = new Option("op1", Type.STRING);
		
		assertEquals(option.getType(), Type.STRING);
		
		option.setType(Type.INTEGER);
		assertEquals(option.getType(), Type.INTEGER);
		
	}

	@Test
	public void optionGetAndSetValueTest() {
		Option option = new Option("op1", Type.STRING);
		
		option.setValue("2.txt");
		assertEquals(option.getValue(), "2.txt");
		
		option.setValue("91.txt");
		assertEquals(option.getValue(), "91.txt");
		
	}
	
	@Test
	public void optionEqualTest() {
		Option option = new Option("op1", Type.STRING);
		Option option2 = new Option("op1", Type.STRING);
		
		
		assertTrue(option.equals(option2));
	}
	
	// Get bug #6
	@Test
	public void optionNotEqualTest() {
		Option option = new Option("op1", Type.STRING);
		Option option2 = new Option("op2", Type.STRING);
		
		
		assertFalse(option.equals(option2));
	}
	
	@Test
	public void sameOptionTest() {
		Option option = new Option("op1", Type.STRING);
		
		assertTrue(option.equals(option));
	}
	
	@Test
	public void nullOptionTest() {
		Option option = new Option("op1", Type.STRING);
		Option option2 = new Option(null, Type.BOOLEAN);
		assertFalse(equals(option2));
	}
	
	@Test
	public void optionToStringTest() {
		Option option = new Option("op1", Type.STRING);
		
		option.setValue("1.txt");
		
		assertEquals(option.toString(), "Option[name:op1, value:1.txt, type:STRING]");
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void notypeStoreTest() {
		parser.addOption(new Option("op1", Type.NOTYPE), "sc");
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addOptionNullNameTest() {
		parser.addOption(new Option(null, Type.STRING));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addOptionEmptyNameTest() {
		parser.addOption(new Option("", Type.STRING));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addOptionNotValidNameTest() {
		parser.addOption(new Option("#$%^^&*", Type.STRING));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addOptionNotValidShortCutTest() {
		parser.addOption(new Option("op1", Type.STRING),null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addOptionNotValidShortCutTest2() {
		parser.addOption(new Option("op1", Type.STRING),"$#%^^&**(&");
	}
	
	@Test
	public void parserToStringTest() {
		parser.addOption(new Option("op1", Type.STRING), "sc");
		assertEquals(parser.toString(),"Options Map: \n"
				+ "{op1=Option[name:op1, value:, type:STRING]}\n"
				+ "Shortcuts Map:\n"
				+ "{sc=Option[name:op1, value:, type:STRING]}" );
	}
	
	
	
	
	
	
	
		
		
	
	
	
	
	
	
	
	
	

}
