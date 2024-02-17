package data;

import testing.TestCase;
import testing.TestResult;

public class InputValidationUtilTest extends TestCase {
	public InputValidationUtilTest() {
		super(InputValidationUtilTest.class.getName());
	}

	public TestResult testFlagPotentialMismatches() {
		TestResult testResult = new TestResult("testFlagPotentialMismatches");
		String input =  "the money or other benefits lost whn pusuing a particular couse of action instead of a mutually-exclusive alternative";
		String correct = "the money or other benefits lost when pursuing a particular course of action instead of a mutually-exclusive alternative";		
		
		String mismatch = InputValidationUtil.flagPotentialMismatches(correct, input);
		
		if (mismatch.compareTo("when")==0) {
			logger.info("Word matches expected value");
			testResult.setTestSuccessful(true);
		} else {
			logger.info("Mismatch gives: \""+mismatch+"\" instead of expected \"when\"");
			testResult.setTestSuccessful(false);
		}
		
		return testResult;
	}
	
	public TestResult testFlagPotentialMismatchesWithSurrounding() {
		TestResult testResult = new TestResult("testFlagPotentialMismatchesWithSurrounding");
		String input =  "the money or other benefits lost whn pusuing a particular couse of action instead of a mutually-exclusive alternative";
		String correct = "the money or other benefits lost when pursuing a particular course of action instead of a mutually-exclusive alternative";		
		
		MismatchedSentenceWord mismatchedSentenceWord = InputValidationUtil.flagPotentialMismatchesWithSurrounding(correct, input);
		
	   int index = mismatchedSentenceWord.getLocation();
	   
	   logger.info("String index substring: "+input.substring(index,index+7));
		
		if (mismatchedSentenceWord.getWord().compareTo("when")==0 &&
				mismatchedSentenceWord.getWordBefore().compareTo("lost")==0 &&
				mismatchedSentenceWord.getWordAfter().compareTo("pursuing")==0 &&
				input.substring(index,index+7).compareTo("whn pus")==0) {
			logSuccess("Word matches expected value: "+mismatchedSentenceWord.toString());
			testResult.setTestSuccessful(true);
		} else {
			logFailure("Mismatch gives: \""+mismatchedSentenceWord.toString()+"\" instead of expected \"lost when pursuing\"");
			testResult.setTestSuccessful(false);
		}
		
		return testResult;
	}
	
	public TestResult testGetSingleSpacedSentence() {
		TestResult testResult = new TestResult("testFlagPotentialMismatches");
		String input =  "A   sentence with extra   spaces is   not ideally correct.";
		String correct = "A sentence with extra spaces is not ideally correct.";
		
		String computedInFlexibleWay = InputValidationUtil.getSingleSpacedSentence(correct);
		if (computedInFlexibleWay.compareTo(correct)==0) {
			logSuccess("Strings match after getSingleSpacedSentence run on test input");
			testResult.setTestSuccessful(true);
		} else {
			logFailure("Sentences don't match after getSingleSpacedSentence run on input\n"
					+"Computed valued: "+computedInFlexibleWay+"\n"
					+"Original: "+input+"\n"
					+"Correct: "+correct);
			   
			testResult.setTestSuccessful(false);
		}
		
		return testResult;		
	}
	
	

	public static void main(String args[]) {
		InputValidationUtilTest test = new InputValidationUtilTest();
		TestResult result1 = test.testFlagPotentialMismatches();
		TestResult result2 = test.testGetSingleSpacedSentence();
		TestResult result3 = test.testFlagPotentialMismatchesWithSurrounding();
	}
}
