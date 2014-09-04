import java.io.Console;
import java.util.HashMap;
import java.util.Map;


public enum Player {
	COMPUTER("computer"){
		public void play() {
			
		}
	},
	HUMAN("human"){
		public void play() {
			
		}
	};
	
	private static Console c = System.console();
	private String player;
	private static Map<String, Player> players = new HashMap<String, Player>();
	
	static{
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        
        for(Player player : Player.values()) {
        	players.put(player.player, player);
        }
	}

	private Player(String player) {
		this.player = player;
	}
	
	public void play(){
		
	}
}
