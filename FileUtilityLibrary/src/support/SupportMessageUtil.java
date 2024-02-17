package support;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

import color.ColorUtil;
import encouragement.EncouragementUtil;
import encouragement.HumorUtil;
import file.HtmlUtil;
import random.RandomUtil;
import thought.CriticalThinkingAmpsUtil;

public class SupportMessageUtil {
	private static ArrayList<String> supportMessageTypes;
	
	public static ArrayList<String> getSupportMessageTypes() {
		if (null != supportMessageTypes) {
			return supportMessageTypes;
		}
		
		String arrayOfSupportMessageTypes[] = {
		    "Protection from Antisemitism",
			"Glass of Water",
			"Water Bottles",
			"Rescue Blankets",
			"Habitat for Humanity",
			"Human Rights",
			"Geneva Convention",
			"Time Management",
			"Criminal Defense Law",
			"Diversity Training",
			"Reduced Cognitive Biases Training",
			"Reduced Villain Level Contrast Training",
			"Disaster Risk Reduction",
			"Clean Water Support",
			"Water Well Drilling",
			"Water Desalination Plants",
			"Cancer Research",
			"Improved Hospitals",
			"Improved Medical Tech",
			"Improved Research Ethics",
			"Improved Medical Research",
			"Ethics",
			"Critical Thinking",
			"Problem Solving",
			"Reduced Miscommunication",
			"Linguistics Training",
			"Improved Network Throughput and Reach",
			"Improved Fire Codes",
			"Improved Architectural Blueprints",
			"Improved Chemical Showers for Labs",
			"Improved Microscopes",
			"Improved Telescopes",
			"Improved Optics",
			"Power Efficiency",
			"Improved Battery Power",
			"Electrical Safety",
			"Defensive Driving",
			"Motorcycle Helmets",
			"Seat Belts",
			"Child Car Safety",
			"Reduced Child Labor",
			"Reduced Oppression",
			"More Peace",
			"More Peacemaking",
			"More Peacekeeping",
			"More Peace building",
			"Improved Math Support",
			"Improved English Support",
			"Improved Science Support",
			"Encouragement",
			"Validation",
			"Respect",
			"Teacher Training",
			"Teacher Appreciation",
			"Improved Learning",
			"Throughput",
			"Focus",
			"Less Burning Buildings",
			"Carbon Monoxide Detectors",
			"Improved International Relations",
			"Improved Magnify Studio",
			"Protection of the Elderly",
			"Protection of Widows",
			"Protection of Veterans",
			"Protection of the Disabled",
			"Protection of the Mentally Ill",
			"Protection of the Oppressed",
			"Protection of those unable to Protect Themselves",
			"Protection of Children",
			"Protection of those that have been silenced",
			"Protection of those that have had their support robbed",
			"Protection of those that have been devalued and dehumanized by society",
			"Protection of the Persecuted",
			"Improved Humanization"
		};
		
		
		supportMessageTypes = new ArrayList<>(Arrays.asList(arrayOfSupportMessageTypes));
		
		return supportMessageTypes;
	}
	
	public static String getRandomSupportMessageType() {
		String supportMessageType = RandomUtil.getRandomElementInListOfStrings(getSupportMessageTypes());
		return supportMessageType;
	}
	
	public static String generateSupport() {
		final StringWriter supportWriter = new StringWriter();
		final StringWriter encouragementWriter = new StringWriter();
		
		for (int i=0; i<2; i++) {
			
			ArrayList<String> encouragementPhrases = RandomUtil.getRandomList(EncouragementUtil.getEncouragingPhrases(), 15);
			final StringWriter encouragementParagraphWriter = new StringWriter();
			encouragementPhrases.forEach((phrase) -> {
				encouragementParagraphWriter.append(phrase+", ");
			});
			String encouragementParagraph = encouragementParagraphWriter.toString();
			encouragementParagraph=encouragementParagraph.substring(0,encouragementParagraph.length()-2);
			encouragementParagraph+=".";
			
			encouragementWriter.append(ColorUtil.encloseStringInParagraphOfRandomColor(encouragementParagraph)+"\n\n");					
		}
		
		supportWriter.append(encouragementWriter.toString());
		
		
		final StringWriter supportMessageWriter = new StringWriter();
		
		for (int i=0; i<10; i++) {
			ArrayList<String> supportMessageList = RandomUtil.getRandomList(SupportMessageUtil.getSupportMessageTypes(), 25);

			supportMessageWriter.append(ColorUtil.encloseStringInParagraphOfRandomColor(ListToFormattedStringUtil.listToFormattedString(supportMessageList)));
			supportMessageWriter.append("\n\n");
		}
		
		supportWriter.append(HtmlUtil.createHtmlParagraphsFromEndlines(supportMessageWriter.toString()));
		
		for (int i=0; i<10; i++) {
			supportWriter.append(ColorUtil.encloseStringInParagraphOfRandomColor(HumorUtil.ruleOfThreeGenerator()));
			supportWriter.append("\n\n");
		}
		
		final StringWriter criticalThoughtAmpWriter = new StringWriter();
		
		for (int i=0; i<10; i++) {
			ArrayList<String> criticalThinkingAmpList = RandomUtil.getRandomList(CriticalThinkingAmpsUtil.getCriticalThinkingAmps(), 25);
			
			criticalThoughtAmpWriter.append(
					ColorUtil.encloseStringInParagraphOfRandomColor(
					ListToFormattedStringUtil.listToFormattedString(criticalThinkingAmpList)));
			criticalThoughtAmpWriter.append("\n\n");
		}
		
		supportWriter.append(criticalThoughtAmpWriter.toString());		
		
		return supportWriter.toString();
	}
}