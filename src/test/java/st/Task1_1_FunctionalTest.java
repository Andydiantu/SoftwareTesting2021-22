package st;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;

public class Task1_1_FunctionalTest {
	
	private Parser parser;
	
	@Before
	public void setUp() {
		parser = new Parser();
	}

	@Test
	public void emptyStringShortcutTest() {
		parser.addOption(new Option("input", Type.STRING), "");
	}
	
	@Test
	public void emptyStringNameTest() {
		parser.addOption(new Option("2  f", Type.STRING), "oz");
	}
	
	@Test
	public void nameOverideTest() {
		parser.addOption(new Option("op1", Type.STRING), "oh");
		parser.parse("--op1 2.txt");
		parser.addOption(new Option("op1", Type.STRING), "hi");
		parser.parse("--op1 3.txt");
		
		assertEquals(parser.getString("-oh"), "2.txt");
		//assertEquals(parser.getString("op1"), "3.txt");
		
		
	}
	
	@Test
	public void nameCaseSensitiveTest() {
		parser.addOption(new Option("oP1", Type.STRING), "hi");
		parser.addOption(new Option("op1", Type.STRING), "hi");
	}
	
	@Test
	public void sameNameAndShortCutTest() {
		parser.addOption(new Option("oP1", Type.STRING), "op1");
		parser.addOption(new Option("op1", Type.STRING), "hi");
		}
	
	
	
	

}
