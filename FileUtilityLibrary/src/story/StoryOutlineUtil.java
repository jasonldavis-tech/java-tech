package story;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.DoubleStream;

public class StoryOutlineUtil {
	private static Logger logger = Logger.getLogger(StoryOutlineUtil.class.toString());
	
	public static String generateStoryOutline() {
		    final StringWriter storyOutline = new StringWriter();
		
			RandomGenerator randomGenerator = RandomGeneratorFactory.getDefault().create();
			DoubleStream doubleStream = randomGenerator.doubles(10);
			
		    logger.fine("Random THEME - "+ThemeUtil.getRandomTheme());
		    storyOutline.write("Random THEME - "+ThemeUtil.getRandomTheme()+"\n");		    
		    logger.fine("Random PLOT - "+PlotUtil.getRandomPlot());
		    storyOutline.write("Random PLOT - "+PlotUtil.getRandomPlot()+"\n");
			
			ArrayList<String> literaryDevices = LiteraryDevicesUtils.getLiteraryDevices();
			
			final StringWriter logStringWriter = new StringWriter();
			
			doubleStream.forEach((random) -> {
				int value = Double.valueOf(literaryDevices.size()*random).intValue();
				logStringWriter.append(literaryDevices.get(value)+" ");
			});
			
			storyOutline.write(logStringWriter.toString()+"\n");
			logger.fine(logStringWriter.toString());
			storyOutline.write("Random Villain "+VillainUtil.getRandomVillain()+"\n");
			logger.fine("Random Villain "+VillainUtil.getRandomVillain());
			
			ArrayList<String> randomVillains = RandomUtil.getRandomList(VillainUtil.getVillains(), 20);
			
			final StringWriter villainStringWriter = new StringWriter();
			
			randomVillains.forEach((villain) -> {
				villainStringWriter.append(villain+" ");
			});
			
			storyOutline.write(villainStringWriter.toString()+"\n");
			logger.fine(villainStringWriter.toString());
			
			final StringWriter descriptivePhraseWriter = new StringWriter();
			
			for (int i=0; i<10; i++) {
				descriptivePhraseWriter.append(DescriptiveWritingUtil.getDescriptivePhrase()+'\n');
			}
			
			
			storyOutline.write("\nDescriptive Writing Amps\n");
		    logger.fine("Descriptive Writing Amps");
			storyOutline.write(descriptivePhraseWriter.toString()+"\n");
			logger.fine(descriptivePhraseWriter.toString());
			storyOutline.write("Random Hero - "+HeroUtil.getRandomHero()+"\n");
			logger.fine("Random Hero - "+HeroUtil.getRandomHero());
			
			AndButThereforeEvent andButThereforeEventSet = new AndButThereforeEvent();
			final StringWriter andButThereforeWriter = new StringWriter();
			ArrayList<String> andButThereforeEventList = andButThereforeEventSet.getAndButThereforeEvents();
			andButThereforeEventList.forEach((event) -> {
				andButThereforeWriter.append(event+'\n');
			});
			
			logger.fine("And But Therefore Event Sets");
			storyOutline.write("And But Therefore Event Sets\n");
			logger.fine(andButThereforeWriter.toString());
			storyOutline.write(andButThereforeWriter.toString());
			return storyOutline.toString();
	}
}
