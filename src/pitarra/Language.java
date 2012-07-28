package pitarra;

public final class Language {
	protected static boolean langEng = true;
	
	protected final static String menuNewBegEng = "New Beginner Game";
	protected final static String menuNewTradEng = "New Traditional Game";
	protected final static String menuSoundEng = "Sound";
	protected final static String menuExitEng = "Exit";
	protected final static String menuInstructionEng = "Instruction";
	protected final static String menuResetGameEng = "Reset Game";
	protected final static String menuAboutEng = "About";
	protected final static String menuLanguageEng = "Languages";
	protected final static String menuFileEng = "File";
	protected final static String menuGameEng = "Game";
	protected final static String menuHelpEng = "Help";
	protected final static String menuEnglishEng = "English";
	protected final static String menuSpanishEng = "Español";
	protected final static String menuMusicEng = "Music";
	protected final static String playerStringEng = "Player";
	protected final static String playerStringACEng = "PLAYER";
	protected final static String pieceCountTextEng = " Left";
	protected final static String pieceLostTextEng = " lost";
	
	protected final static String menuNewBegSpa = "Nuevo Juego de Principiante";
	protected final static String menuNewTradSpa = "Nuevo Juego de Tradicional";
	protected final static String menuSoundSpa = "Sonido";
	protected final static String menuExitSpa = "Salida";
	protected final static String menuInstructionSpa = "Instrucción";
	protected final static String menuResetGameSpa = "Restablecer Juego";
	protected final static String menuAboutSpa = "Referencia";
	protected final static String menuLanguageSpa = "Lengua";
	protected final static String menuFileSpa = "Ficha";
	protected final static String menuGameSpa = "Juego";
	protected final static String menuHelpSpa = "Ayuda";
	protected final static String menuEnglishSpa = "English";
	protected final static String menuSpanishSpa = "Español";
	protected final static String menuMusicSpa = "Música";
	protected final static String playerStringSpa = "Jugador";
	protected final static String playerStringACSpa = "JUGADOR";
	protected final static String pieceCountTextSpa = " izquierda";
	protected final static String pieceLostTextSpa = " perdido";
	
	public static String playerString = playerStringEng;
	public static String playerStringAC = playerStringACEng;
	public static String pieceCountText = pieceCountTextEng;
	public static String pieceLostText = pieceLostTextEng;
	
	public void setPlayerString(String st){
		playerString = st;
	}
	
	public void setPlayerStringAC(String st){
		playerStringAC = st;
	}
	
	private Language(){
	}
}
