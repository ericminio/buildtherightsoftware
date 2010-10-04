

public class Mastermind {

	public String givenTheSecret;
	public String whenPlayerPlays;

	public boolean doesHeWin() {
		if (whenPlayerPlays.equalsIgnoreCase(givenTheSecret)) {
			return true;
		}
		return false;
	}
	
}
