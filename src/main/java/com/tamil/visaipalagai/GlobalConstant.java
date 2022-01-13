package com.tamil.visaipalagai;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Hashtable;

public class GlobalConstant {
	
	protected final static String[] FN_KEY_OFF_TEXT = new String[] {"`", "௧", "௨","௩", "௪", "௫", "௬", "௭", "௮","௯", "0","🗘 மீள்"};
	
	protected final static String[] FN_KEY_OFF_VK = new String[] { "0xC0", "0xBE7", "0xBE8", "0xBE9", "0xBEA", "0xBEB", "0xBEC","0xBED", "0xBEE", "0xBEF", "0x30", "0x27F3"};

	protected final static String[] FN_KEY_ON_TEXT = new String[] { "Esc", "1", "2", "3", "4", "5", "6", "7", "8","9","0","🗘 மீள்" };

	protected final static String[] FN_KEY_ON_VK = new String[] { "0x1B", "0x01", "0x02", "0x03", "0x04", "0x05", "0x06","0x07", "0x08", "0x09", "0x00","0x27F3" };

	protected final static String[] UYIR_KEY_ON_TEXT = new String[] { "அ", "ஆ", "இ", "ஈ", "உ", "ஊ", "எ", "ஏ", "ஐ", "ஒ", "ஓ", "ஔ"};
	
	protected final static String[] UYIR_KEY_ON_VK = new String[] { "0xB85", "0xB86", "0xB87", "0xB88", "0xB89", "0xB8A", "0xB8E", "0xB8F", "0xB90", "0xB92", "0xB93", "0xB94" };
	
	protected final static String[] UYIR_KEY_OFF_TEXT = new String[] { "்", "ா", "ீ", "ீ", "ு", "ூ", "ெ","ே", "ை", "ொ", "ோ", "ௌ"};
	
	protected final static String[] UYIR_KEY_OFF_KA_TEXT = new String[] { "க்","கா","கி","கீ","கு","கூ","கெ","கே","கை","கொ","கோ","கௌ"};
	
	protected final static String[] UYIR_KEY_OFF_NGA_TEXT = new String[] {"ங்","ஙா","ஙி","ஙீ","ஙு","ஙூ","ஙெ","ஙே","ஙை","ஙொ","ஙோ","ஙௌ"};
	
	protected final static String[] UYIR_KEY_OFF_SA_TEXT = new String[] { "ச்","சா","சி","சீ","சு","சூ","செ","சே","சை","சொ","சோ","சௌ"};
	
	protected final static String[] UYIR_KEY_OFF_NYA_TEXT = new String[] {"ஞ்","ஞா","ஞி","ஞீ","ஞு","ஞூ","ஞெ","ஞே","ஞை","ஞொ","ஞோ","ஞௌ"};
	
	protected final static String[] UYIR_KEY_OFF_TA_TEXT = new String[] {"ட்","டா","டி","டீ","டு","டூ","டெ","டே","டை","டொ","டோ","டௌ"};
	
	protected final static String[] UYIR_KEY_OFF_NA_TEXT = new String[] {"ண்","ணா","ணி","ணீ","ணு","ணூ","ணெ","ணே","ணை","ணொ","ணோ","ணௌ"};
	
	protected final static String[] UYIR_KEY_OFF_PA_TEXT = new String[] {"ப்","பா","பி","பீ","பு","பூ","பெ","பே","பை","பொ","போ","பௌ"};

	protected final static String[] UYIR_KEY_OFF_THA_TEXT = new String[] {"த்","தா","தி","தீ","து","தூ","தெ","தே","தை","தொ","தோ","தௌ"};

	protected final static String[] UYIR_KEY_OFF_NHA_TEXT = new String[] {"ந்","நா","நி","நீ","நு","நூ","நெ","நே","நை","நொ","நோ","நௌ"};

	protected final static String[] UYIR_KEY_OFF_MA_TEXT = new String[] {"ம்","மா","மி","மீ","மு","மூ","மெ","மே","மை","மொ","மோ","மௌ"};
	
	protected final static String[] UYIR_KEY_OFF_YA_TEXT = new String[] {"ய்","யா","யி","யீ","யு","யூ","யெ","யே","யை","யொ","யோ","யௌ"};
	
	protected final static String[] UYIR_KEY_OFF_RA_TEXT = new String[] {"ர்","ரா","ரி","ரீ","ரு","ரூ","ரெ","ரே","ரை","ரொ","ரோ","ரௌ"};
	
	protected final static String[] UYIR_KEY_OFF_LA_TEXT = new String[] {"ல்","லா","லி","லீ","லு","லூ","லெ","லே","லை","லொ","லோ","லௌ"};
	
	protected final static String[] UYIR_KEY_OFF_VA_TEXT = new String[] {"வ்","வா","வி","வீ","வு","வூ","வெ","வே","வை","வொ","வோ","வௌ"};
	
	protected final static String[] UYIR_KEY_OFF_ZHA_TEXT = new String[] {"ழ்","ழா","ழி","ழீ","ழு","ழூ","ழெ","ழே","ழை","ழொ","ழோ","ழௌ"};
	
	protected final static String[] UYIR_KEY_OFF_LLA_TEXT = new String[] {"ள்","ளா","ளி","ளீ","ளு","ளூ","ளே","ளொ","ளை","ளொ","ளோ","ளௌ"};
	
	protected final static String[] UYIR_KEY_OFF_RRA_TEXT = new String[] {"ற்","றா","றி","றீ","று","றூ","றெ","றே","றை","றொ","றோ","றௌ"};
	
	protected final static String[] UYIR_KEY_OFF_NNA_TEXT = new String[] {"ன்","னா","னி","னீ","னு","னூ","னெ","னே","னை","னொ","னோ","னௌ"};
	
	protected final static String[] UYIR_KEY_OFF_JA_TEXT = new String[] { "ஜ்","ஜா","ஜி","ஜீ","ஜு","ஜூ","ஜெ","ஜே","ஜை","ஜொ","ஜோ","ஜௌ"};
	
	protected final static String[] UYIR_KEY_OFF_HA_TEXT = new String[] { "ஹ்","ஹா","ஹி","ஹீ","ஹு","ஹூ","ஹெ","ஹே","ஹை","ஹொ","ஹோ","ஹௌ"};
	
	protected final static String[] UYIR_KEY_OFF_SSA_TEXT = new String[] { "ஸ்","ஸா","ஸி","ஸீ","ஸு","ஸூ","ஸெ","ஸே","ஸை","ஸொ","ஸோ","ஸௌ"};
	
	protected final static String[] UYIR_KEY_OFF_SHA_TEXT = new String[] { "ஷ்","ஷா","ஷி","ஷீ","ஷு","ஷூ","ஷெ","ஷே","ஷை","ஷொ","ஷோ","ஷௌ"};
	
	protected final static String[] UYIR_KEY_OFF_STH_TEXT	=	new String[] {"க்ஷ்","க்ஷா","க்ஷி","க்ஷீ","க்ஷு","க்ஷூ","க்ஷெ","க்ஷே","க்ஷை","க்ஷொ","க்ஷோ","க்ஷெள"};
	
	protected final static String[] UYIR_KEY_OFF_SRI_TEXT	=	new String[] {"க்ஷ்","க்ஷா","க்ஷி","க்ஷீ","க்ஷு","க்ஷூ","க்ஷெ","க்ஷே","க்ஷை","க்ஷொ","க்ஷோ","க்ஷெள"};
	
	protected final static String[] UYIR_KEY_OFF_VK = new String[] { "0xBCD", "0xBBE", "0xBBF", "0xBC0", "0xBC1", "0xBC2", "0xBC6",
			"0xBC7", "0xBC8", "0xBCA", "0xBCB", "0xBCC"};
	
	final static Hashtable<Integer, Character> ht = new Hashtable<>();
	
    protected static final int TM_KEYMAATRU = 10227; // Uyir Maatru - Reload Decimal keycode
    protected static final int TM_UNICODE_START = 2944; // Tamil unicode start - Decimal
    protected static final int TM_UNICODE_END = 3071; // Tamil unicode end - Decimal
    protected static final int SYM_UNICODE_START = 33; // Symbols unicode end - Decimal
    protected static final int SYM_UNICODE_END = 46; // Symbols unicode end - Decimal
    protected static final int SYM4_UNICODE_START = 41; // Symbols unicode end - Decimal
    protected static final int SYM4_UNICODE_END = 47; // Symbols unicode end - Decimal 
    protected static final int SYM1_UNICODE_START = 58; // Symbols unicode end - Decimal
    protected static final int SYM1_UNICODE_END = 64; // Symbols unicode end - Decimal
    
    protected static final int SYM2_UNICODE_START = 91; // Symbols unicode end - Decimal
    protected static final int SYM2_UNICODE_END = 96; // Symbols unicode end - Decimal
    
    protected static final int SYM3_UNICODE_START = 123; // Symbols unicode end - Decimal
    protected static final int SYM3_UNICODE_END = 126; // Symbols unicode end - Decimal
    
    protected static final int TM_UNICODE_KEYSTH = 999999; // Tamil unicode Keysth - Decimal 
    protected static final int TM_UNICODE_KEYSRI = 999995; // Tamil Sri
    
    private final static Color SELECTED_COLOR = new Color(-15304792);
    private final static Color DEFAULT_KEY_COLOR = new Color(255,255,255); 
    private final static Color DEFAULT_KEY_FG_COLOR = java.awt.Color.black;
    private final static Color DEFAULT_PANEL_BG_COLOR = new Color(40,40,40);
    private final static Color DEFAULT_MOUSE_ENTER_COLOR = new Color(0,204,255);
    private final static Color DEFAULT_EMO_ENTER_COLOR = new Color(235,236,240);
    


	protected static final Dimension SYM_EMOJI_MAX_SIZE = new Dimension(40, 40); // SYM_EMOJI Max Size
	protected static final Dimension SYM_EMOJI_MIN_SIZE = new Dimension(40, 40); // SYM_EMOJI Min Size
    protected static final Dimension SYM_EMOJI_PRF_SIZE = new Dimension(40, 40); // SYM_EMOJI Prf Size


    
	public static String[] getFnKeyOffText() {
		return FN_KEY_OFF_TEXT;
	}
	public static int getTmKeymaatru() {
		return TM_KEYMAATRU;
	}
	public static int getTmUnicodeStart() {
		return TM_UNICODE_START;
	}
	public static int getTmUnicodeEnd() {
		return TM_UNICODE_END;
	}
	public static int getTmUnicodeKeysth() {
		return TM_UNICODE_KEYSTH;
	}
	public static Color getSelectedColor() {
		return SELECTED_COLOR;
	}
	public static Color getDefaultKeyFgColor() {
		return DEFAULT_KEY_FG_COLOR;
	}
	public static Color getDefaultPanelBgColor() {
		return DEFAULT_PANEL_BG_COLOR;
	}
	public static Color getDefaultKeyColor() {
		// TODO Auto-generated method stub
		return DEFAULT_KEY_COLOR;
	}

	public static Color getDefaultMouseEnterColor() {
		return DEFAULT_MOUSE_ENTER_COLOR;
	}
	
    public static Color getDefaultEmoEnterColor() {
		return DEFAULT_EMO_ENTER_COLOR;
	}
	
	public static Dimension getSymEmojiMaxSize() {
		return SYM_EMOJI_MAX_SIZE;
	}

	public static Dimension getSymEmojiMinSize() {
		return SYM_EMOJI_MIN_SIZE;
	}

	public static Dimension getSymEmojiPrfSize() {
		return SYM_EMOJI_PRF_SIZE;
	}
	
}
