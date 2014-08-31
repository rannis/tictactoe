import java.util.HashMap;
import java.util.Map;


public enum NoughtOrCross {
	NOUGHT("o"),
	CROSS("x"),
	INVALID_PIECE();
	
	private String noughtOrCross;
	private static Map<String, NoughtOrCross> gamePieces = new HashMap<String, NoughtOrCross>();
	
	static{
		for(NoughtOrCross value : NoughtOrCross.values()) {
			gamePieces.put(value.noughtOrCross, value);
		}
	}
	
	private NoughtOrCross(String noughtOrCross) {
		this.noughtOrCross = noughtOrCross;
	}
	
	private NoughtOrCross() {
		
	}
	
	public static NoughtOrCross fromString(String value) {
		if(value.equals("x")) return CROSS;
		if(value.equals("o")) {
			return NOUGHT;
		}else {
			return INVALID_PIECE;
		}
	}
	
	public static String fromObject(NoughtOrCross noughtOrCross) {
		return noughtOrCross.noughtOrCross;
	}
}
