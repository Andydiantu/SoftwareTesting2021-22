package st;

import java.awt.TexturePaint;
import java.awt.desktop.AboutHandler;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.AuthenticationException;
import javax.swing.plaf.basic.BasicGraphicsUtils;

public class Parser {

	private OptionMap optionMap;
	
	public Parser() {
		optionMap = new OptionMap();
	}
	
	public void addOption(Option option, String shortcut) {
		optionMap.store(option, shortcut);
	}
	
	public void addOption(Option option) {
		optionMap.store(option, "");
	}

	public void addAll(String options, String types){

	}

	public void addAll(String options, String types, String shortcuts){

	}

//	public void addAll(String options, String types) {
//		
//		if(options == null || types == null) {
//			throw new IllegalArgumentException("Given input is null");
//		}
//		
//		String[] optionList = options.split("\\s+");
//		String[] typeList = types.split("\\s+");
//
//		if(typeList.length == 0 || optionList.length == 0 ) {
//			throw new IllegalArgumentException("blank string is given");
//		}
//
//		if(optionList[0].isBlank()){
//			throw new IllegalArgumentException("blank string is given");
//		}
//
//		if(!checkTypeList(typeList)) {
//			throw new IllegalArgumentException("Illegal type varaible is given");
//		}
//
//		int optionIndex = 0, typeIndex = 0;
//
//		while(optionIndex < optionList.length) {
//			String[] currOptions = generateOptionList(optionList[optionIndex]);
//
//			if(currOptions == null) {
//				optionIndex++;
//				typeIndex++;
//				continue;
//			}
//
//			int currIndex = 0;
//			Type currType;
//			if(typeIndex < typeList.length) {
//				currType = typeStringToType(typeList[typeIndex]);
//			} else {
//				currType = typeStringToType(typeList[typeList.length-1]);
//			}
//			while(currIndex < currOptions.length) {
//				addOption(new Option(currOptions[currIndex], currType));
//				currIndex++;
//			}
//
//			optionIndex++;
//			typeIndex++;
//		}
//	}
//
//	public void addAll(String options, String shortcuts, String types) {
//		
//		if(options == null || types == null || shortcuts == null) {
//			throw new IllegalArgumentException("Given input is null");
//		}
//		
//		String[] optionList = options.split("\\s+");
//
//		String[] shortcutList =  shortcuts.split("\\s+");
//
//		if(shortcutList.length > 0 && shortcutList[0].isBlank()){
//			shortcutList = new String[0];
//		} else {
//			shortcutList = generateShortcutList(shortcutList);
//		}
//
//		String[] typeList = types.split("\\s+");
//		
//		if(typeList.length == 0 || optionList.length == 0) {
//			throw new IllegalArgumentException("blank string is given");
//		}
//
//		if(optionList[0].isBlank()){
//			throw new IllegalArgumentException("blank string is given");
//		}
//		
//		if(!checkTypeList(typeList)) {
//			throw new IllegalArgumentException("Illegal type varaible is given");
//		}
//		
//		int optionIndex = 0, shortcutIndex = 0, typeIndex = 0;
//		
//		while(optionIndex < optionList.length) {
//			String[] currOptions = generateOptionList(optionList[optionIndex]);
//			
//			if(currOptions == null) {
//				optionIndex++;
//				shortcutIndex++;
//				typeIndex++;
//				continue;
//			}
//			
//			int currIndex = 0;
//			Type currType;
//			if(typeIndex < typeList.length) {
//				currType = typeStringToType(typeList[typeIndex]);
//			} else {
//				currType = typeStringToType(typeList[typeList.length-1]);
//			}
//			while(currIndex < currOptions.length) {
//				if(shortcutIndex < shortcutList.length && shortcutList[shortcutIndex] != null) {
//					addOption(new Option(currOptions[currIndex], currType),shortcutList[shortcutIndex]);
//				} else {
//					addOption(new Option(currOptions[currIndex], currType));
//				}
//				currIndex++;
//				shortcutIndex++;
//			}
//			
//			optionIndex++;
//			typeIndex++;
//		}
//	}
//	
//	private Type typeStringToType(String type) {
//		
//		Type ans;
//		
//		if(type.equals("String")) {
//			ans = Type.STRING;
//		} else if (type.equals("Integer")) {
//			ans = Type.INTEGER;
//		} else if (type.equals("Character")) {
//			ans = Type.CHARACTER;
//		} else {
//			ans = Type.BOOLEAN;
//		}
//		
//		return ans;
//	}
//	
//	private String[] generateOptionList(String option) {
//		List<String> ans = new ArrayList<String>();
//
//		if(Character.isDigit(option.charAt(0))){
//			return null;
//		}
//
//		if(option.contains("-") && checkValidGroup(option)) {
//			for(String opt : groupToList(option)) {
//				ans.add(opt);
//			}
//		} else {
//			Pattern p = Pattern.compile("[A-Za-z0-9_]+");
//	        Matcher m = p.matcher(option);
//	        
//	        if(m.matches()) {
//	        	ans.add(option);
//	        } else {
//	        	return null;
//	        }
//		}
//		
//		return ans.toArray(new String[0]);
//	}
//	
//	private String[] generateShortcutList(String[] shortcuts) {
//		List<String> ans = new ArrayList<String>();
//		
//		
//		for(String shortcut : shortcuts ) {
//			
//			if(Character.isDigit(shortcut.charAt(0))) {
//				ans.add(null);
//				continue;
//			}
//			
//			if(shortcut.contains("-") && checkValidGroup(shortcut)) {
//				for(String st : groupToList(shortcut)) {
//					ans.add(st);
//				}
//			} else {
//				Pattern p = Pattern.compile("[A-Za-z0-9_]+");
//		        Matcher m = p.matcher(shortcut);
//		        
//		        if(m.matches() ) {
//		        	ans.add(shortcut);
//		        } else {
//		        	ans.add(null);
//		        }
//			}
//		}
//		
//		return ans.toArray(new String[0]);
//	}
//	
//	private boolean checkTypeList(String[] typeList) {
//		
//		HashSet<String> typeSet = new HashSet<String>();
//		typeSet.add("String");
//		typeSet.add("Integer");
//		typeSet.add("Character");
//		typeSet.add("Boolean");
//		
//		for(String type : typeList) {
//			if(!typeSet.contains(type)) {
//				return false;
//			}
//		}
//		
//		return true;
//	}
//	
//	private boolean checkValidGroup(String group) {
//		
//		int hyphenCount = 0;
//		for(Character s : group.toCharArray()) {
//			if(s == '-') {
//				hyphenCount++;
//			}
//		}
//		
//		if (hyphenCount != 1) {
//			return false;
//		}
//		
//		int hyphenIndex = group.indexOf('-');
//		
//		String base = group.substring(0, hyphenIndex-1);
//		Pattern p = Pattern.compile("[A-Za-z0-9_]+");
//        Matcher m = p.matcher(base);
//		if(!m.matches()) {
//			return false;
//		}
//		
//		char firstPart = group.charAt(hyphenIndex - 1);
//		
//		// 0 for lowercase, 1 for uppercase, 2 for numbers
//		int rangeType;
//		
//	    if(Character.isLowerCase(firstPart)) {
//	    	rangeType = 0;
//	    } else if (Character.isUpperCase(firstPart)) {
//	    	rangeType = 1;
//	    } else if(Character.isDigit(firstPart)) {
//	    	rangeType = 2;
//	    } else {
//	    	return false;
//	    }
//	    
//	    if(rangeType == 0) {
//	    	if(group.length() != hyphenIndex+2) {
//	    		return false;
//	    	}
//	    	
//	    	if(!Character.isLowerCase(group.charAt(hyphenIndex+1))) {
//	    		return false;
//	    	}
//	    } else if (rangeType == 1) {
//	    	if(group.length() != hyphenIndex+2) {
//	    		return false;
//	    	}
//	    	
//	    	if(!Character.isUpperCase(group.charAt(hyphenIndex+1))) {
//	    		return false;
//	    	}
//	    } else {
//	    	
//	    	String secondPart = group.substring(hyphenIndex+1);
//	    	
//	    	Pattern p2 = Pattern.compile("[0-9]+");
//	    	Matcher m2 = p2.matcher(secondPart);
//	    	
//	    	if(!m2.matches()) {
//	    		return false;
//	    	}
//	    }
//		
//		return true;
//	}
//	
//	private List<String> groupToList(String group) {
//		List<String> ans = new ArrayList<String>();
//		
//		int hyphenIndex = group.indexOf('-');
//		
//		String base = group.substring(0, hyphenIndex-1);
//		
//		char firstPart = group.charAt(hyphenIndex - 1);
//		
//		if(Character.isDigit(firstPart)){
//			int fstBound = Character.getNumericValue(firstPart);
//			
//			int sndBound = Integer.parseInt( group.substring(hyphenIndex+1));
//			
//			if(fstBound < sndBound) {
//				while(fstBound <= sndBound) {
//					ans.add(base + fstBound);
//					fstBound++;
//				}
//			} else {
//				while(sndBound <= fstBound) {
//					ans.add(base + fstBound);
//					fstBound--;
//				}
//			}
//		} else {
//			char fstBound = firstPart;
//			
//			char sndBound = group.charAt(hyphenIndex+1);
//			
//			if(fstBound < sndBound) {
//				while(fstBound <= sndBound) {
//					ans.add(base + fstBound);
//					fstBound++;
//				}
//			} else {
//				while(sndBound <= fstBound) {
//					ans.add(base + fstBound);
//					fstBound--;
//				}
//			}
//		}
//		
//		return ans;
//	}
	
	public boolean optionExists(String key) {
		return optionMap.optionExists(key);
	}
	
	public boolean shortcutExists(String key) {
		return optionMap.shortcutExists(key);
	}
	
	public boolean optionOrShortcutExists(String key) {
		return optionMap.optionOrShortcutExists(key);
	}
	
	public int getInteger(String optionName) {
		String value = getString(optionName);
		Type type = getType(optionName);
		int result;
		switch (type) {
			case STRING:
			case INTEGER:
				try {
					result = Integer.parseInt(value);
				} catch (Exception e) {
			        try {
			            new BigInteger(value);
			        } catch (Exception e1) {
			        }
			        result = 0;
			    }
				break;
			case BOOLEAN:
				result = getBoolean(optionName) ? 1 : 0;
				break;
			case CHARACTER:
				result = (int) getCharacter(optionName);
				break;
			default:
				result = 0;
		}
		return result;
	}
	
	public boolean getBoolean(String optionName) {
		String value = getString(optionName);
		return !(value.toLowerCase().equals("false") || value.equals("0") || value.equals(""));
	}
	
	public String getString(String optionName) {
		return optionMap.getValue(optionName);
	}
	
	public char getCharacter(String optionName) {
		String value = getString(optionName);
		return value.equals("") ? '\0' :  value.charAt(0);
	}
	
	public void setShortcut(String optionName, String shortcutName) {
		optionMap.setShortcut(optionName, shortcutName);
	}
	
	public void replace(String variables, String pattern, String value) {
			
		variables = variables.replaceAll("\\s+", " ");
		
		String[] varsArray = variables.split(" ");
		
		for (int i = 0; i < varsArray.length; ++i) {
			String varName = varsArray[i];
			String var = (getString(varName));
			var = var.replace(pattern, value);
			if(varName.startsWith("--")) {
				String varNameNoDash = varName.substring(2);
				if (optionMap.optionExists(varNameNoDash)) {
					optionMap.setValueWithOptionName(varNameNoDash, var);
				}
			} else if (varName.startsWith("-")) {
				String varNameNoDash = varName.substring(1);
				if (optionMap.shortcutExists(varNameNoDash)) {
					optionMap.setValueWithOptionShortcut(varNameNoDash, var);
				} 
			} else {
				if (optionMap.optionExists(varName)) {
					optionMap.setValueWithOptionName(varName, var);
				}
				if (optionMap.shortcutExists(varName)) {
					optionMap.setValueWithOptionShortcut(varName, var);
				} 
			}

		}
	}
	
	private List<CustomPair> findMatches(String text, String regex) {
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(text);
	    // Check all occurrences
	    List<CustomPair> pairs = new ArrayList<CustomPair>();
	    while (matcher.find()) {
	    	CustomPair pair = new CustomPair(matcher.start(), matcher.end());
	    	pairs.add(pair);
	    }
	    return pairs;
	}
	
	
	public int parse(String commandLineOptions) {
		if (commandLineOptions == null) {
			return -1;
		}
		int length = commandLineOptions.length();
		if (length == 0) {
			return -2;
		}	
		
		List<CustomPair> singleQuotePairs = findMatches(commandLineOptions, "(?<=\')(.*?)(?=\')");
		List<CustomPair> doubleQuote = findMatches(commandLineOptions, "(?<=\")(.*?)(?=\")");
		List<CustomPair> assignPairs = findMatches(commandLineOptions, "(?<=\\=)(.*?)(?=[\\s]|$)");
		
		
		for (CustomPair pair : singleQuotePairs) {
			String cmd = commandLineOptions.substring(pair.getX(), pair.getY());
			cmd = cmd.replaceAll("\"", "{D_QUOTE}").
					  replaceAll(" ", "{SPACE}").
					  replaceAll("-", "{DASH}").
					  replaceAll("=", "{EQUALS}");
	    	
	    	commandLineOptions = commandLineOptions.replace(commandLineOptions.substring(pair.getX(),pair.getY()), cmd);
		}
		
		for (CustomPair pair : doubleQuote) {
			String cmd = commandLineOptions.substring(pair.getX(), pair.getY());
			cmd = cmd.replaceAll("\'", "{S_QUOTE}").
					  replaceAll(" ", "{SPACE}").
					  replaceAll("-", "{DASH}").
					  replaceAll("=", "{EQUALS}");
			
	    	commandLineOptions = commandLineOptions.replace(commandLineOptions.substring(pair.getX(),pair.getY()), cmd);	
		}
		
		for (CustomPair pair : assignPairs) {
			String cmd = commandLineOptions.substring(pair.getX(), pair.getY());
			cmd = cmd.replaceAll("\"", "{D_QUOTE}").
					  replaceAll("\'", "{S_QUOTE}").
					  replaceAll("-", "{DASH}");
	    	commandLineOptions = commandLineOptions.replace(commandLineOptions.substring(pair.getX(),pair.getY()), cmd);	
		}

		commandLineOptions = commandLineOptions.replaceAll("--", "-+").replaceAll("\\s+", " ");


		String[] elements = commandLineOptions.split("-");
		
		
		for (int i = 0; i < elements.length; ++i) {
			String entry = elements[i];
			
			if(entry.isBlank()) {
				continue;
			}

			String[] entrySplit = entry.split("[\\s=]", 2);
			
			boolean isKeyOption = entry.startsWith("+");
			String key = entrySplit[0];
			key = isKeyOption ? key.substring(1) : key;
			String value = "";
			if(entrySplit.length > 1 && !entrySplit[1].isBlank()) {
				String valueWithNoise = entrySplit[1].trim();
				value = valueWithNoise.split(" ")[0];
			}
			
			// Explicitly convert boolean.
			if (getType(key) == Type.BOOLEAN && (value.toLowerCase().equals("false") || value.equals("0"))) {
				value = "";
			}
			
			value = value.replace("{S_QUOTE}", "\'").
						  replace("{D_QUOTE}", "\"").
						  replace("{SPACE}", " ").
						  replace("{DASH}", "-").
						  replace("{EQUALS}", "=");
			
			
			boolean isUnescapedValueInQuotes = (value.startsWith("\'") && value.endsWith("\'")) ||
					(value.startsWith("\"") && value.endsWith("\""));
			
			value = value.length() > 1 && isUnescapedValueInQuotes ? value.substring(1, value.length() - 1) : value;
			
			if(isKeyOption) {
				optionMap.setValueWithOptionName(key, value);
			} else {
				optionMap.setValueWithOptionShortcut(key, value);
				
			}			
		}

		return 0;
		
	}

	
	private Type getType(String option) {
		Type type = optionMap.getType(option);
		return type;
	}
	
	@Override
	public String toString() {
		return optionMap.toString();
	}

	
	private class CustomPair {
		
		CustomPair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	    private int x;
	    private int y;
	    
	    public int getX() {
	    	return this.x;
	    }
	    
	    public int getY() {
	    	return this.y;
	    }
	}
}
