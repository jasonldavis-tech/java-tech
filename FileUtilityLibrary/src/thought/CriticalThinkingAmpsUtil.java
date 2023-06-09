package thought;

import java.util.ArrayList;
import java.util.Arrays;

import random.RandomUtil;

// Notable Philosopher Inspirations from Plato, Socrates, Aristotle, Rene Descarte, David Hume, Immanuel Kant
public class CriticalThinkingAmpsUtil {
	private static ArrayList<String> criticalThinkingAmps;
	
	public static ArrayList<String> getCriticalThinkingAmps() {
		if (null != criticalThinkingAmps) {
			return criticalThinkingAmps;
		}
		
		String arrayOfCriticalThinkingAmps[] = {
			"Disaster Risk Reduction",
			"Risk Mitigation Strategies",
			"Power Efficiency",
			"Directed Power",
			"Time",
			"Resources",
			"Questions",
			"Linguistics",
			"Miscommunication",
			"Precision and Accuracy",
			"Shortest Path",
			"Limits",
			"Linear",
			"Exponential",
			"Directional Max Profit",
			"Trajectories",
			"Perception",
			"Comprehension",
			"Health",
			"Knowns upsold to Unknowns",
			"Unknowns upsold to Knowns",
			"Forms - Aristotle",
			"\"Allegory of the Cave\" - Plato",
			"\"I think therefore I am\" - Rene Descarte",
			"\"To be is to be perceived\" - David Hume",
			"The Problem of Evil",
			"Rate of Change",
			"Gravity",
			"Magnetism",
			"Impedance",
			"Resistance",
			"Induction",
			"Potential Energy",
			"Kinetic Energy",
			"Momentum",
			"Orbits",
			"Atmosphere",
			"Pressure",
			"Imagination",
			"Creative Problem Solving",
			"Practical",
			"Law",
			"Ethics",
			"Justice",
			"Fair",
			"Equality",
			"Inequality",
			"Story",
			"Experience",
			"Customer Service",
			"Liquidity",
			"Gross Domestic Product",
			"Macroeconomics",
			"Microeconomics",
			"Sociology",
			"Psychology",
			"Research Ethics",
			"Reaction Time",
			"Latency",
			"Activation Energy",
			"Lift",
			"Drag",
			"Surface Area",
			"Multiple Dimensions",
			"4th Dimension",
			"Point of View",
			"Frame of Reference",
			"Low Pressure",
			"High Pressure",
			"Stress",
			"Anxiety",
			"Osmosis",
			"Fast Choice",
			"Delayed Right Choice",
			"Sales",
			"Public Relations",
			"Corporate Image",
			"Cover",
			"Camouflage",
			"Flags",
			"Puns",
			"Metaphors",
			"Morally Right",
			"Morally Wrong",
			"Trust",
			"Distrust",
			"Respect",
			"Disrespect",
			"Encouragement",
			"Discouragement",
			"Puzzles",
			"Riddles",
			"Practical Problem Solving",
			"Theoretical Problem Solving",
			"Hypotheticals",
			"Scientific Method",
			"Strong Hypothesis",
			"Weak Hypothesis",
			"Truth as Relative",
			"Velocity",
			"Acceleration",
			"Force",
			"Thermodynamics",
			"Entropy",
			"Enthalpy",
			"Heat transfer",
			"Conductivity",
			"Motion",
			"Oxygen",
			"Water",
			"Fire",
			"Combustion",
			"Insulation",
			"Reversive Process",
			"Irreversible Process",
			"Fuel",
			"Green Energy",
			"Solar Power",
			"Wind Power",
			"Hydroelectric Power",
			"Geothermal Power",
			"Closed Loop Systems",
			"Feedback",
			"Open Loop Systems",
			"Fault Tolerance",
			"Availability",
			"Reliability",
			"Maintenance",
			"GCFI Outlets",
			"Fuses",
			"Tube Amps",
			"Accessibility",
			"Small Contributions Appreciated",
			"Small Contributions Respected",
			"Shear Stregth",
			"Compression",
			"Compressive Forces",
			"Acronyms",
			"Obfuscated Truth",
			"Obfuscation as Value Creation",
			"Interesting",
			"Charming",
			"Tone",
			"Mood",
			"Symbolism",
			"Allegory",
			"Measurement",
			"Adequate Support",
			"Inadequate Support",
			"Volume",
			"Shape",
			"Associations",
			"Memory",
			"Chain Thougts",
			"Projections",
			"Interpolation",
			"Rounding",
			"Bugs",
			"Imperfect System upsold to Perfect in Very Non Ideal Ways",
			"Defense Law",
			"Villain Level Contrast Victims",
			"Communism",
			"Capitalism",
			"Socialism",
			"Health Care",
			"Dental Care",
			"Respect",
			"Validation",
			"Appreciation",
			"Variety",
			"Variance",
			"Statistical Anomaly",
			"Flood Zone",
			"Active Volcano",
			"Normal",
			"Thunderstorm",
			"Tornado",
			"Hurricane",
			"Typhoon",
			"Flood",
			"Drought",
			"Rain",
			"Snow",
			"Hail",
			"Blizzard",
			"Teams",
			"Competition",
			"Greed",
			"Buy In",
			"Harder to divest",
			"Logic",
			"Logical Fallacies",
			"Irrational",
			"Illogical",
			"Probabilities",
			"Slippery Slope",
			"Equivocation",
			"False Dichotomy",
			"Red Herring",
			"Plot",
			"Useful Twists",
			"Theme",
			"Relevance",
			"Expert",
			"Weakest Link",
			"Tangled Cords",
			"Don't push against a brick wall",
			"Useful Work",
			"Joules",
			"Energy",
			"Watts",
			"Distance",
			"Scientific Notation",
			"Scale",
			"Compare and Contrast",
			"Categorize and Classify",
			"Precedent",
			"Historic Context",
			"Context Clues",
			"Pattern Matching",
			"Equal and Opposite Forces",
			"Schrodinger's Cat",
			"Superposition",
			"Quantum Entanglement",
			"Speed of Light",
			"Speed of Electricity (Not the speed of light)",
			"Speed of Sound",
			"Speed of Heat Conductivity",
			"BTUs",
			"Drilling Water Wells",
			"Contamination",
			"Disease",
			"Pestilence",
			"Mosquitos",
			"Sea Shells",
			"Mangos",
			"Oranges",
			"Apples",
			"Pineapples",
			"Cocunut",
			"Cantelope",
			"Plumbs",
			"Peaches",
			"Chips",
			"Fries",
			"Cake",
			"Cheesecake",
			"Bagels",
			"Coffee",
			"Soda",
			"Carbon Dioxide",
			"Carbon Monoxide",
			"Carbon Monoxide Detector",
			"Nitrogen",
			"Carbon",
			"Atom",
			"Proton",
			"Electron",
			"Neutron",
			"Radiation",
			"Valence",
			"Excited State",
			"Hope",
			"Possible",
			"Impossible",
			"Value",
			"Value Creation",
			"Lab Safety",
			"Research Ethics",
			"Reducing Child Labor",
			"Subset Profits from Systemic Changes and Additions",
			"Reasonable",
			"Unreasonable",
			"Anger Management",
			"Communicated Effectively",
			"Feedback Respected",
			"Pain",
			"Ability to Change Path, Direction",
			"Challenged in Positive Direction",
			"Positive Momentum",
			"Real Change",
			"Initial Conditions"
		};
		
		criticalThinkingAmps = new ArrayList<>(Arrays.asList(arrayOfCriticalThinkingAmps));
		
		return criticalThinkingAmps;
	}
	
	public static String getRandomCriticalThinkingAmp() {
		String criticalThinkingAmp = RandomUtil.getRandomElementInListOfStrings(getCriticalThinkingAmps());
		return criticalThinkingAmp;
	}
}


