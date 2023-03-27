package story;

import java.util.ArrayList;
import java.util.Arrays;

public class AndButThereforeEvent {
	private ArrayList<String> ands;
	private ArrayList<String> buts;
	private ArrayList<String> therefores;
	private ArrayList<String> andButThereforeEvents;
	
	public ArrayList<String> getAndButThereforeEvents() {
		if (null != andButThereforeEvents) {
			return andButThereforeEvents;
		}
		
		andButThereforeEvents = new ArrayList<>();
		for (int i=0; i<10; i++) {
			andButThereforeEvents.add(getAndButThereforeEvent());
		}
		
		return andButThereforeEvents;
	}
	
	public String getAndButThereforeEvent() {
		String and = RandomUtil.getRandomElementInListOfStrings(getAnds())+" ";
		String but = RandomUtil.getRandomElementInListOfStrings(getButs())+" ";
		String therefore = RandomUtil.getRandomElementInListOfStrings(getTherefores());
		String andButThereforeEvent = andButTherefore(and, but, therefore);
		return andButThereforeEvent;
	}
	
	
	public ArrayList<String> getAnds() {
		if (null != ands) {
			return ands;
		}
		
		String arrayOfAnds[] = { 
			"Dramatic Dialogue with subtext", "diffuse a bomb", "fight a [monster]",
			"[Hero] gets on motorcycle", "[Hero] gets in supercar", "[Hero] gets in jet",
			"[Hero] gets in spaceship", "[Hero] gets on horse", "[Hero] gets on train",
			"[Hero] gets in bus", "Comet cuts across the sky", "There is a solar eclipse",
			"[Hero] is attacked by [Monster]", "[Hero] is Inspired and Encouraged by [side character]",
			"[Hero] is given assistance from [side character]", 
			"Narration highlights allegory that amplifies key details worthy of memory"					
		};
		
		
		ands = new ArrayList<>(Arrays.asList(arrayOfAnds));
		
		return ands;
	}
	
	public ArrayList<String> getButs() {
		if (null != buts) {
			return buts;
		}
		
		String arrayOfButs[] = { 
			"[Side character] shows up", "[monster] shows up", "it rains", "they are delayed", 
			"they get there early", "[monster] challenges [Hero] with hurtful scary words"
		};
		
		
		buts = new ArrayList<>(Arrays.asList(arrayOfButs));
		
		return buts;
	}
	
	public ArrayList<String> getTherefores() {
		if (null != therefores) {
			return therefores;
		}
		
		String arrayOfTherefores[] = { 
			"they travel to a new area", "they decide to learn more", "they fight the [monster]", 
			"they seek assistance with [side character]"
		};
		
		
		therefores = new ArrayList<>(Arrays.asList(arrayOfTherefores));
		
		return therefores;
	}		
	
	public static String andButTherefore(String and, String but, String therefore) {
		String combinedString = and+" but "+but+" therefore "+therefore;
		return combinedString;
	}
}
