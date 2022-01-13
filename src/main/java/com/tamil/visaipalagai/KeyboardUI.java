/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.tamil.visaipalagai;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import static java.awt.event.KeyEvent.VK_CONTROL;
import static java.awt.event.KeyEvent.VK_SHIFT;
import static java.awt.event.KeyEvent.VK_V;

/**
 *
*/
public class KeyboardUI extends javax.swing.JFrame {

	/*
	 * 
	 */
	private static final long serialVersionUID = -985520492082395082L;

	Border blackline = BorderFactory.createLineBorder(Color.GRAY);
	private static boolean IS_TOGGLE_UYIR_KEY_PRESSED = false;
	private final ArrayList<JLabel> alphabetKeys;
	private final ArrayList<JLabel> functionKeys;
	private final ArrayList<JLabel> uyirKeys;
	/*
	 * Creates new GUI form KeyboardUI
	 */
	
	public KeyboardUI() {
		initComponents();
		Keyboard.initialize_hashtable();
		
		alphabetKeys = new ArrayList<>();
		functionKeys = new ArrayList<>();
		uyirKeys = new ArrayList<>();

		initKeys();
		setIcon();
		setLocationToBottom();
	}

	private void initKeys() {
		for (Component component : this.alphabetPanel.getComponents()) {
			try {
				JLabel key = (JLabel) component;

				String name = key.getText();

				if (name.length() == 1) {
					alphabetKeys.add(key);
				}
				if (name == "அ" || name ==  "ஆ" || name ==  "இ" || name ==  "ஈ" || name ==  "உ" || name == "ஊ"|| name ==  "எ"|| name == "ஏ"|| name ==  "ஐ"|| name == "ஒ" || name ==  "ஓ" || name ==  "ஔ") {
					uyirKeys.add(key);
				}
				
			} catch (ClassCastException ex) {

			}
		}

		for (Component component : this.functionPanel.getComponents()) {
			try {
				JLabel key = (JLabel) component;

				functionKeys.add(key);
			} catch (ClassCastException ex) {
			}
		}
		functionKeys.remove(keybackspace);
		functionKeys.remove(keyt_del);
	}

	private void setIcon() {
		setIconImage((new ImageIcon(getClass().getResource("/icons/vk.png"))).getImage());
	}

	// <editor-fold defaultstate="collapsed" desc="GUI Helper functions">
	/**
	 * Sets the default location of the Onscreen Keyboard on bottom of the screen
	 * just above the taskbar.
	 */
	private void setLocationToBottom() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets inset = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());

		int workingScreen = screenSize.height - inset.bottom;

		int width = (screenSize.width - getWidth()) / 2; // Positions horizontally to middle of screen
		int height = workingScreen - getHeight(); // Positions vertically just above the taskbar

		setLocation(width, height);
	}
	
	private void pressAndReleaseKey() {
		Keyboard.pressKey(VK_CONTROL);
	    Keyboard.pressKey(VK_V);
	    Keyboard.releaseKey(VK_CONTROL);
	    Keyboard.releaseKey(VK_V);		
	}
	
	/**
	 * Performs a key press event on the specific key. Sends a key pressed event to
	 * the current foreground application.
	 * 
	 * @param evt
	 */
	private void keyPressed(MouseEvent evt) {
		JLabel key = (JLabel) evt.getSource();
		key.setBackground(GlobalConstant.getSelectedColor());
	}

	/**
	 * Releases a key that was pressed. Sends a key release event to the current
	 * foreground application.
	 * 
	 * @param evt
	 */
	private void keyReleased(MouseEvent evt) {
		JLabel key = (JLabel) evt.getSource();
		key.setBackground(GlobalConstant.getDefaultKeyColor());
	}

	/**
	 * Performs a mouse click event on the key. Mouse click generally send a key
	 * associated with the source of the event object to the currently focusable
	 * foreground application.
	 * 
	 * @param evt
	 */
	private void keyClicked(MouseEvent evt) {
		JLabel key = (JLabel) evt.getSource(); // Source clicked key.
		int keycode = Integer.decode(key.getName()); // Virtual key code associated with the key
		boolean specialkey = false;
		/**
		 * Check for special keys(e.g. CapsLock, NumLock, Shift key). Perform special
		 * actions on special keys click such as pressing, or releasing a pressed key.
		 */
		switch (keycode) {
		case VK_SHIFT:
			if (Keyboard.isShiftPressed())
				Keyboard.releaseKey(VK_SHIFT);
			else
				Keyboard.pressKey(VK_SHIFT);
			specialkey = true;
			break;
		case VK_CONTROL:
			if (Keyboard.isCtrlPressed())
				Keyboard.releaseKey(VK_CONTROL);
			else
				Keyboard.pressKey(VK_CONTROL);
			specialkey = true;
			break;
		case 2965:
		case 2969:
		case 2970:
		case 2972:
		case 2974:
		case 2975:
		case 2979:
		case 2980:
		case 2984:
		case 2985:
		case 2986:
		case 2990:
		case 2991:
		case 2992:
		case 2993:
		case 2994:
		case 2995:
		case 2996:
		case 2997:
		case 2999:
		case 3000:
		case 3001:
		case 999999:
			IS_TOGGLE_UYIR_KEY_PRESSED = true;
			updateUyirKeys(keycode);
			break;
		case 3006:
		case 3007:
		case 3008:
		case 3009:
		case 3010:
		case 3014:
		case 3015:
		case 3016:
		case 3018:
		case 3019:
		case 3020:
		case 3021:
			IS_TOGGLE_UYIR_KEY_PRESSED = false;
			updateUyirKeys(keycode);
			break;
		case 10227:
			IS_TOGGLE_UYIR_KEY_PRESSED = false;
			updateUyirKeys(keycode);
			break;
		}

		if (!specialkey) {
			Keyboard.typeKey(keycode);
			Keyboard.releaseAllSpecialKeys();
		}

		updateAlphabeticKeys();
		//updateFunctionKeys();
		updateSpecialKeys();
	}

	/**
	 * Performs a hover effect on the key button.
	 * 
	 * @param evt
	 */
	private void keyMouseEntered(MouseEvent evt) {
		JLabel source = (JLabel) evt.getSource();

		/**
		 * Check if the current key is a special key or not. Perform no hover effect if
		 * special keys(e.g. CapsLock, NumLock, Shift etc) keys are pressed.
		 */
		int keycode = Integer.decode(source.getName());
		switch (keycode) {
		case VK_SHIFT:
			if (Keyboard.isShiftPressed())
				return;
			break;
		case VK_CONTROL:
			if (Keyboard.isCtrlPressed())
				return;
			break;
		}

		source.setBackground(GlobalConstant.getDefaultMouseEnterColor());
	}
	
	private void emoMouseEntered(MouseEvent evt) {
		JLabel source = (JLabel) evt.getSource();
		source.setBackground(GlobalConstant.getDefaultEmoEnterColor());
	}
	
	private void emoMouseExited(MouseEvent evt) {
		JLabel source = (JLabel) evt.getSource();
		source.setBackground(GlobalConstant.getDefaultKeyColor());
	}


	/**
	 * Performs a hover effect on the key button.
	 * 
	 * @param evt
	 */
	private void keyMouseExited(MouseEvent evt) {
		JLabel source = (JLabel) evt.getSource();

		/**
		 * Check if the current key is special key or not. Perform no hover effect if
		 * the special keys(e.g. CapsLock, NumLock, Shift key etc) are pressed.
		 */
		int keycode = Integer.decode(source.getName());
		switch (keycode) {
		case VK_SHIFT:
			if (Keyboard.isShiftPressed())
				return;
			break;
		case VK_CONTROL:
			if (Keyboard.isCtrlPressed())
				return;
			break;
		}

		source.setBackground(GlobalConstant.getDefaultKeyColor());
	}

	/**
	 * Changes the text of the alphabetic keys. This function to be called on every
	 * shift or caps key press or release. This function changes the text of the
	 * alphabetic keys to CAPS and to small according to the current status of the
	 * Caps lock key and Shift key.
	 */
	private void updateUyirKeys(int meikeycode) {
		int i = 0;
		for (JLabel key : uyirKeys) {
			if (IS_TOGGLE_UYIR_KEY_PRESSED) {
				switch(meikeycode) {
				case 2965:
					key.setText(GlobalConstant.UYIR_KEY_OFF_KA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2969:
					key.setText(GlobalConstant.UYIR_KEY_OFF_NGA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2970:
					key.setText(GlobalConstant.UYIR_KEY_OFF_SA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2972:
					key.setText(GlobalConstant.UYIR_KEY_OFF_JA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2974:
					key.setText(GlobalConstant.UYIR_KEY_OFF_NYA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2975:
					key.setText(GlobalConstant.UYIR_KEY_OFF_TA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2979:
					key.setText(GlobalConstant.UYIR_KEY_OFF_NA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2980:
					key.setText(GlobalConstant.UYIR_KEY_OFF_THA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2984:
					key.setText(GlobalConstant.UYIR_KEY_OFF_NHA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2985:
					key.setText(GlobalConstant.UYIR_KEY_OFF_NNA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2986:
					key.setText(GlobalConstant.UYIR_KEY_OFF_PA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2990:
					key.setText(GlobalConstant.UYIR_KEY_OFF_MA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2991:
					key.setText(GlobalConstant.UYIR_KEY_OFF_YA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2992:
					key.setText(GlobalConstant.UYIR_KEY_OFF_RA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2993:
					key.setText(GlobalConstant.UYIR_KEY_OFF_RRA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2994:
					key.setText(GlobalConstant.UYIR_KEY_OFF_LA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2995:
					key.setText(GlobalConstant.UYIR_KEY_OFF_LLA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2996:
					key.setText(GlobalConstant.UYIR_KEY_OFF_ZHA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2997:
					key.setText(GlobalConstant.UYIR_KEY_OFF_VA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 2999:
					key.setText(GlobalConstant.UYIR_KEY_OFF_SHA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 3000:
					key.setText(GlobalConstant.UYIR_KEY_OFF_SSA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				case 3001:
					key.setText(GlobalConstant.UYIR_KEY_OFF_HA_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;		
				case 999999:
					key.setText(GlobalConstant.UYIR_KEY_OFF_STH_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				default:
					key.setText(GlobalConstant.UYIR_KEY_OFF_TEXT[i]);
					key.setName(GlobalConstant.UYIR_KEY_OFF_VK[i]);
					break;
				}
			} else {
				key.setText(GlobalConstant.UYIR_KEY_ON_TEXT[i]);
				key.setName(GlobalConstant.UYIR_KEY_ON_VK[i]);
			}

			i++;
		}
	}

	/**
	 * Changes the text of the alphabetic keys. This function to be called on every
	 * shift or caps key press or release. This function changes the text of the
	 * alphabetic keys to CAPS and to small according to the current status of the
	 * Caps lock key and Shift key.
	 */
	private void updateAlphabeticKeys() {
		boolean caps = (Keyboard.isShiftPressed() && !Keyboard.isCapsLockOn())
				|| (!Keyboard.isShiftPressed() && Keyboard.isCapsLockOn());

		for (JLabel key : alphabetKeys) {
			if (caps)
				key.setText(key.getText().toUpperCase());
			else
				key.setText(key.getText().toLowerCase());
		}
	}

	/**
	 * Changes the current state of the special keys(e.g. CapsLock, NumLock, Shift
	 * etc). Sets whether the special keys pressed or not. If the special key is
	 * pressed then blue colored keys are shown else normal keys.
	 */
	private void updateSpecialKeys() {

		if (Keyboard.isShiftPressed()) {
			keyshift.setBackground(GlobalConstant.getSelectedColor());
		} else {
			keyshift.setBackground(GlobalConstant.getDefaultKeyColor());
		}

		if (Keyboard.isCtrlPressed()) {
			keyctrl1.setBackground(GlobalConstant.getSelectedColor());
			keyctrl2.setBackground(GlobalConstant.getSelectedColor());
		} else {
			keyctrl1.setBackground(GlobalConstant.getDefaultKeyColor());
			keyctrl2.setBackground(GlobalConstant.getDefaultKeyColor());
		}

		if (Keyboard.isWinPressed())
			keywin.setBackground(GlobalConstant.getSelectedColor());
		else
			keywin.setBackground(GlobalConstant.getDefaultKeyColor());
		
		if(Keyboard.isMaatruPressed() )
			keynp_maatru.setBackground(GlobalConstant.getSelectedColor());
			

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	private void initComponents() {

		mainPanel = new javax.swing.JPanel();
		symbolsPanel = new javax.swing.JPanel();
		functionPanel = new javax.swing.JPanel();
		numpadPanel = new javax.swing.JPanel();
		bottomctrlPanel = new javax.swing.JPanel();
		leftctrlPanel = new javax.swing.JPanel();
		rightctrlPanel = new javax.swing.JPanel();
		emojiPanel = new javax.swing.JPanel();
		classicPanel = new javax.swing.JPanel();
		keyt_numalt = new javax.swing.JLabel();
		keynum7 = new javax.swing.JLabel();
		keynum8 = new javax.swing.JLabel();
		keynum9 = new javax.swing.JLabel();
		keynum5 = new javax.swing.JLabel();
		keynum6 = new javax.swing.JLabel();
		keynum4 = new javax.swing.JLabel();
		keynum3 = new javax.swing.JLabel();
		keynum2 = new javax.swing.JLabel();
		keynum1 = new javax.swing.JLabel();
		keynum0 = new javax.swing.JLabel();

		keyt_suli = new javax.swing.JLabel();
		keyt_sri = new javax.swing.JLabel();
		keyt_altnum = new javax.swing.JLabel();
		key1 = new javax.swing.JLabel();
		key2 = new javax.swing.JLabel();
		key3 = new javax.swing.JLabel();
		key4 = new javax.swing.JLabel();
		key5 = new javax.swing.JLabel();
		key6 = new javax.swing.JLabel();
		key7 = new javax.swing.JLabel();
		key8 = new javax.swing.JLabel();
		key9 = new javax.swing.JLabel();
		key0 = new javax.swing.JLabel();
		keyt_maatru = new javax.swing.JLabel();
		keybackspace = new javax.swing.JLabel();
		keyt_del = new javax.swing.JLabel();
		keynp_maatru = new javax.swing.JLabel();
		keynp_backspace = new javax.swing.JLabel();
		keynp_del = new javax.swing.JLabel();
		alphabetPanel = new javax.swing.JPanel();
		keytab = new javax.swing.JLabel();
		keyq = new javax.swing.JLabel();
		keyw = new javax.swing.JLabel();
		keye = new javax.swing.JLabel();
		keyr = new javax.swing.JLabel();
		keyt = new javax.swing.JLabel();
		keyy = new javax.swing.JLabel();
		keyu = new javax.swing.JLabel();
		keyi = new javax.swing.JLabel();
		keyo = new javax.swing.JLabel();
		keyp = new javax.swing.JLabel();
		keyt_o = new javax.swing.JLabel();
		keyt_ov = new javax.swing.JLabel();
		keyt_om = new javax.swing.JLabel();
		keyt_ayutha = new javax.swing.JLabel();
		keyopenbigbracket = new javax.swing.JLabel();
		keyclosebigbracket = new javax.swing.JLabel();
		keyj = new javax.swing.JLabel();
		keyk = new javax.swing.JLabel();
		keyl = new javax.swing.JLabel();
		keyt_ma = new javax.swing.JLabel();
		keyt_ya = new javax.swing.JLabel();
		keyt_ra = new javax.swing.JLabel();
		keyt_la = new javax.swing.JLabel();
		keyemoji = new javax.swing.JLabel();
		keyoldfont = new javax.swing.JLabel();
		keysemicolon = new javax.swing.JLabel();
		keya = new javax.swing.JLabel();
		keyenter = new javax.swing.JLabel();
		keyenterl = new javax.swing.JLabel();
		keys = new javax.swing.JLabel();
		keyd = new javax.swing.JLabel();
		keyf = new javax.swing.JLabel();
		keyg = new javax.swing.JLabel();
		keyh = new javax.swing.JLabel();
		keym = new javax.swing.JLabel();
		keyt_ja = new javax.swing.JLabel();
		keyt_ha = new javax.swing.JLabel();
		keyt_sth = new javax.swing.JLabel();
		keyshift = new javax.swing.JLabel();
		keyfslash = new javax.swing.JLabel();
		keyz = new javax.swing.JLabel();
		keyx = new javax.swing.JLabel();
		keyc = new javax.swing.JLabel();
		keyv = new javax.swing.JLabel();
		keyb = new javax.swing.JLabel();
		keyn = new javax.swing.JLabel();
		keyrightarrow = new javax.swing.JLabel();
		keyctrl1 = new javax.swing.JLabel();
		keywin = new javax.swing.JLabel();
		keysym = new javax.swing.JLabel();
		keyspace = new javax.swing.JLabel();
		keyrspace = new javax.swing.JLabel();
		keyctrl2 = new javax.swing.JLabel();
		keyleftarrow = new javax.swing.JLabel();
		keyuparrow = new javax.swing.JLabel();
		keydownarrow = new javax.swing.JLabel();
		keyrupee 	= 	new javax.swing.JLabel();
		keyampersand	=	new javax.swing.JLabel();	
		keyat			=	new javax.swing.JLabel();
		keypound		=	new javax.swing.JLabel();
		keyquestion		=	new javax.swing.JLabel();
		keysemicolon	=	new javax.swing.JLabel();
		keyfslash		=	new javax.swing.JLabel();
		keylrndbrace	=	new javax.swing.JLabel();
		keyrrndbrace	=	new javax.swing.JLabel();
		keycolon		=	new javax.swing.JLabel();
		keycomma		=	new javax.swing.JLabel();
		keydot			=	new javax.swing.JLabel();
		keyunderscr    	= new javax.swing.JLabel(); 
		emo_smile	=	new javax.swing.JLabel();
		emo_sad = new javax.swing.JLabel();
		emo_teeth = new javax.swing.JLabel();
		emo_love = new javax.swing.JLabel();
		emo_zen = new javax.swing.JLabel();
		emo_angry = new javax.swing.JLabel();
		emo_tear = new javax.swing.JLabel();
		emo_oneeye = new javax.swing.JLabel();
		emo_tongue = new javax.swing.JLabel();
		emo_sneeze = new javax.swing.JLabel();
		emo_cry = new javax.swing.JLabel();
		emo_zip = new javax.swing.JLabel();
		emo_suspense = new javax.swing.JLabel();
		emo_hands = new javax.swing.JLabel();
		emo_pam = new javax.swing.JLabel();
		emo_clap = new javax.swing.JLabel();
		emo_victor = new javax.swing.JLabel();
		emo_super = new javax.swing.JLabel();
		emo_thumbs = new javax.swing.JLabel();
		emo_thumbsdown = new javax.swing.JLabel();
		emo_punch = new javax.swing.JLabel();
		emo_indexfinger = new javax.swing.JLabel();
		emo_downfinger = new javax.swing.JLabel();
		emo_folded = new javax.swing.JLabel();
		emo_arm = new javax.swing.JLabel();
		emo_middlefinger = new javax.swing.JLabel();
		emo_whitelove = new javax.swing.JLabel();
		emo_blacklove = new javax.swing.JLabel();
		emo_heartbrk = new javax.swing.JLabel();
		emo_fries = new javax.swing.JLabel();
		emo_tick = new javax.swing.JLabel();
		emo_wrong = new javax.swing.JLabel();
		emo_whiteflag = new javax.swing.JLabel();
		emo_closeeye = new javax.swing.JLabel();
		emo_closeear = new javax.swing.JLabel();
		emo_closemouth = new javax.swing.JLabel();
		emo_girl = new javax.swing.JLabel();
		emo_explode = new javax.swing.JLabel();
		emo_petals = new javax.swing.JLabel();
		emo_headphone = new javax.swing.JLabel();
		emo_torch = new javax.swing.JLabel();
		emo_tone = new javax.swing.JLabel();
		emo_watch = new javax.swing.JLabel();
		emo_ghost = new javax.swing.JLabel();
		emo_nocell = new javax.swing.JLabel();
		emo_scissor = new javax.swing.JLabel();
		emo_boy = new javax.swing.JLabel();
		emo_umber = new javax.swing.JLabel();
		emo_car = new javax.swing.JLabel();
		emo_flight = new javax.swing.JLabel();
		emo_train = new javax.swing.JLabel();
		emo_bus = new javax.swing.JLabel();
		sym_exclamation = new javax.swing.JLabel();
		sym_minus = new javax.swing.JLabel();
		sym_plus = new javax.swing.JLabel();
		sym_multi = new javax.swing.JLabel();
		sym_div = new javax.swing.JLabel();
		sym_eq = new javax.swing.JLabel();
		sym_ne = new javax.swing.JLabel();
		sym_plusmin = new javax.swing.JLabel();
		sym_mod = new javax.swing.JLabel();
		sym_sqrt = new javax.swing.JLabel();
		sym_rupee = new javax.swing.JLabel();
		sym_le = new javax.swing.JLabel();
		sym_ge = new javax.swing.JLabel();
		sym_gr = new javax.swing.JLabel();
		sym_lt = new javax.swing.JLabel();
		sym_deg = new javax.swing.JLabel();
		sym_half = new javax.swing.JLabel();
		sym_qtr = new javax.swing.JLabel();
		sym_sqr = new javax.swing.JLabel();
		sym_cube = new javax.swing.JLabel();
		sym_lcurly = new javax.swing.JLabel();
		sym_rcurly = new javax.swing.JLabel();
		sym_lsqr = new javax.swing.JLabel();
		sym_rsqr = new javax.swing.JLabel();
		sym_pipe = new javax.swing.JLabel();
		sym_bslash = new javax.swing.JLabel();
		sym_semicolon = new javax.swing.JLabel();
		sym_quote = new javax.swing.JLabel();
		sym_euro = new javax.swing.JLabel();
		sym_pound = new javax.swing.JLabel();
		sym_dollar = new javax.swing.JLabel();
		sym_dblarr = new javax.swing.JLabel();
		sym_leftarr = new javax.swing.JLabel();
		sym_rightarr = new javax.swing.JLabel();
		sym_downarr = new javax.swing.JLabel();
		sym_uparr = new javax.swing.JLabel();
		sym_eqarr = new javax.swing.JLabel();
		sym_tilde = new javax.swing.JLabel();
		sym_caret = new javax.swing.JLabel();
		sym_asterick = new javax.swing.JLabel();
		sym_approx = new javax.swing.JLabel();
		sym_hash = new javax.swing.JLabel();
		sym_at = new javax.swing.JLabel();
		sym_underscr = new javax.swing.JLabel();
		sym_ampersand = new javax.swing.JLabel();
		sym_lcir = new javax.swing.JLabel();
		sym_rcir = new javax.swing.JLabel();
		sym_fslash = new javax.swing.JLabel();
		sym_singlequote = new javax.swing.JLabel();
		sym_colon = new javax.swing.JLabel();
		sym_question = new javax.swing.JLabel();
		sym_comma = new javax.swing.JLabel();
		sym_dot = new javax.swing.JLabel();
		cls_total = new javax.swing.JLabel();
		cls_uppalam = new javax.swing.JLabel();
		cls_onefifth = new javax.swing.JLabel();
		cls_one320th = new javax.swing.JLabel();
		cls_kuzhi = new javax.swing.JLabel();
		cls_kaasu = new javax.swing.JLabel();
		cls_mukkalvisam = new javax.swing.JLabel();
		cls_ulakku = new javax.swing.JLabel();
		cls_mooulakku = new javax.swing.JLabel();
		cls_mukkal = new javax.swing.JLabel();
		cls_pathakku = new javax.swing.JLabel();
		cls_nallathu = new javax.swing.JLabel();
		cls_silavinam = new javax.swing.JLabel();
		cls_one40 = new javax.swing.JLabel();
		cls_one32nd = new javax.swing.JLabel();
		cls_marakkal = new javax.swing.JLabel();
		cls_one20 = new javax.swing.JLabel();
		cls_one16 = new javax.swing.JLabel();
		cls_poga = new javax.swing.JLabel();
		cls_paaram = new javax.swing.JLabel();
		cls_punsey = new javax.swing.JLabel();
		cls_year = new javax.swing.JLabel();
		cls_muthal = new javax.swing.JLabel();
		cls_muthaliya = new javax.swing.JLabel();
		cls_nilam = new javax.swing.JLabel();
		cls_arai = new javax.swing.JLabel();
		cls_one160 = new javax.swing.JLabel();
		cls_enn = new javax.swing.JLabel();
		cls_three20 = new javax.swing.JLabel();
		cls_three16 = new javax.swing.JLabel();
		cls_mukkuruni = new javax.swing.JLabel();
		cls_nansey = new javax.swing.JLabel();
		cls_mukkani = new javax.swing.JLabel();
		cls_panam = new javax.swing.JLabel();
		cls_pon = new javax.swing.JLabel();
		cls_vaaraagan = new javax.swing.JLabel();
		cls_kaani = new javax.swing.JLabel();
		cls_kaalvisam = new javax.swing.JLabel();
		cls_one10 = new javax.swing.JLabel();
		cls_munthiri = new javax.swing.JLabel();
		cls_varavu = new javax.swing.JLabel();
		cls_vagayara = new javax.swing.JLabel();
		cls_vasam = new javax.swing.JLabel();
		cls_araikaal = new javax.swing.JLabel();
		cls_oneqtr = new javax.swing.JLabel();
		cls_arai_2 = new javax.swing.JLabel();
		cls_endoftext = new javax.swing.JLabel();
		cls_month = new javax.swing.JLabel();
		cls_veeli = new javax.swing.JLabel();
		cls_cevitu = new javax.swing.JLabel();
		cls_aalaku = new javax.swing.JLabel();
		cls_nel = new javax.swing.JLabel();


		InputStream stream =ClassLoader.getSystemClassLoader().getResourceAsStream("MuktaMalar-Regular.ttf");
	    Font font = null;
	    try {
	        font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(16f);
	    } catch (FontFormatException|IOException e) {
	        System.out.println("Not able to load custom font ");
	    }
	    
	    //InputStream notostream =ClassLoader.getSystemClassLoader().getResourceAsStream("NotoSansTamilSupplement-Regular.ttf");
	    InputStream notostream =ClassLoader.getSystemClassLoader().getResourceAsStream("NotoSansTamil.ttf");
	    Font notoFont = null;
	    try {
			notoFont = Font.createFont(Font.TRUETYPE_FONT, notostream).deriveFont(16f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    //InputStream notostream =ClassLoader.getSystemClassLoader().getResourceAsStream("NotoSansTamilSupplement-Regular.ttf");
	    InputStream chenetstream =ClassLoader.getSystemClassLoader().getResourceAsStream("ChenetUnicode_Classic-Regular.ttf");
	    Font chenetFont = null;
	    try {
	    	chenetFont = Font.createFont(Font.TRUETYPE_FONT, chenetstream).deriveFont(16f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    ge.registerFont(font);
	    ge.registerFont(notoFont);
	    
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("அகர விசைப் பலகை");
		setAlwaysOnTop(true);
		setAutoRequestFocus(false);
		setBackground(GlobalConstant.getDefaultPanelBgColor());
		setFocusable(false);
		setFocusableWindowState(false);
		setResizable(false);

		mainPanel.setBackground(GlobalConstant.getDefaultPanelBgColor());
		numpadPanel.setBackground(GlobalConstant.getDefaultPanelBgColor());
		symbolsPanel.setBackground(GlobalConstant.getDefaultPanelBgColor());
		emojiPanel.setBackground(GlobalConstant.getDefaultPanelBgColor());
		bottomctrlPanel.setBackground(GlobalConstant.getDefaultPanelBgColor());
		leftctrlPanel.setBackground(GlobalConstant.getDefaultPanelBgColor());
		rightctrlPanel.setBackground(GlobalConstant.getDefaultPanelBgColor());
		functionPanel.setBackground(GlobalConstant.getDefaultPanelBgColor());
		classicPanel.setBackground(GlobalConstant.getDefaultPanelBgColor());
		
		numpadPanel.setFocusable(false);
		setSize(getWidth(), getHeight());
		
		symbolsPanel.setFocusable(false);
		setSize(getWidth(), getHeight());

		classicPanel.setFocusable(false);
		setSize(getWidth(), getHeight());
		
		bottomctrlPanel.setFocusable(false);
		setSize(getWidth(), getHeight());
		
		leftctrlPanel.setFocusable(false);
		setSize(getWidth(), getHeight());
		
		rightctrlPanel.setFocusable(false);
		setSize(getWidth(), getHeight());
		
		bottomctrlPanel.setVisible(true);

		numpadPanel.setVisible(false);
		functionPanel.setVisible(true);
		symbolsPanel.setVisible(false);
		emojiPanel.setVisible(false);
		classicPanel.setVisible(false);
		
		
		keyt_numalt.setFont(font);
		keyt_numalt.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_numalt.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_numalt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_numalt.setText("12/௫௪");
		keyt_numalt.setFocusable(false);
		keyt_numalt.setMaximumSize(new java.awt.Dimension(40, 40));
		keyt_numalt.setMinimumSize(new java.awt.Dimension(40, 40));
		keyt_numalt.setOpaque(true);
		keyt_numalt.setBorder(blackline);
		keyt_numalt.setPreferredSize(new java.awt.Dimension(40, 40));
		keyt_numalt.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if(functionPanel.isVisible())
				{			
					functionPanel.setVisible(false);
					numpadPanel.setVisible(true);
				}
				else
				{
					functionPanel.setVisible(true);
					numpadPanel.setVisible(false);
				}
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				//keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				//keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				//keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				//keyReleased(evt);
			}
		});
		

		keynum1.setFont(font);
		keynum1.setBackground(GlobalConstant.getDefaultKeyColor());
		keynum1.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keynum1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keynum1.setText("1");
		keynum1.setFocusable(false);
		keynum1.setMaximumSize(new java.awt.Dimension(40, 40));
		keynum1.setMinimumSize(new java.awt.Dimension(40, 40));
		keynum1.setName("0x61"); // NOI18N
		keynum1.setOpaque(true);
		keynum1.setBorder(blackline);
		keynum1.setPreferredSize(new java.awt.Dimension(40, 40));
		keynum1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		
		keynum2.setFont(font);
		keynum2.setBackground(GlobalConstant.getDefaultKeyColor());
		keynum2.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keynum2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keynum2.setText("2");
		keynum2.setFocusable(false);
		keynum2.setMaximumSize(new java.awt.Dimension(40, 40));
		keynum2.setMinimumSize(new java.awt.Dimension(40, 40));
		keynum2.setName("0x62"); // NOI18N
		keynum2.setOpaque(true);
		keynum2.setBorder(blackline);
		keynum2.setPreferredSize(new java.awt.Dimension(40, 40));
		keynum2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});



		keynum3.setFont(font);
		keynum3.setBackground(GlobalConstant.getDefaultKeyColor());
		keynum3.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keynum3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keynum3.setText("3");
		keynum3.setFocusable(false);
		keynum3.setMaximumSize(new java.awt.Dimension(40, 40));
		keynum3.setMinimumSize(new java.awt.Dimension(40, 40));
		keynum3.setName("0x63"); // NOI18N
		keynum3.setOpaque(true);
		keynum3.setBorder(blackline);
		keynum3.setPreferredSize(new java.awt.Dimension(40, 40));
		keynum3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keynum4.setFont(font);
		keynum4.setBackground(GlobalConstant.getDefaultKeyColor());
		keynum4.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keynum4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keynum4.setText("4");
		keynum4.setFocusable(false);
		keynum4.setMaximumSize(new java.awt.Dimension(40, 40));
		keynum4.setMinimumSize(new java.awt.Dimension(40, 40));
		keynum4.setName("0x64"); // NOI18N
		keynum4.setOpaque(true);
		keynum4.setBorder(blackline);
		keynum4.setPreferredSize(new java.awt.Dimension(40, 40));
		keynum4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keynum5.setFont(font);
		keynum5.setBackground(GlobalConstant.getDefaultKeyColor());
		keynum5.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keynum5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keynum5.setText("5");
		keynum5.setFocusable(false);
		keynum5.setMaximumSize(new java.awt.Dimension(40, 40));
		keynum5.setMinimumSize(new java.awt.Dimension(40, 40));
		keynum5.setName("0x65"); // NOI18N
		keynum5.setOpaque(true);
		keynum5.setBorder(blackline);
		keynum5.setPreferredSize(new java.awt.Dimension(40, 40));
		keynum5.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keynum6.setFont(font);
		keynum6.setBackground(GlobalConstant.getDefaultKeyColor());
		keynum6.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keynum6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keynum6.setText("6");
		keynum6.setFocusable(false);
		keynum6.setMaximumSize(new java.awt.Dimension(40, 40));
		keynum6.setMinimumSize(new java.awt.Dimension(40, 40));
		keynum6.setName("0x66"); // NOI18N
		keynum6.setOpaque(true);
		keynum6.setBorder(blackline);
		keynum6.setPreferredSize(new java.awt.Dimension(40, 40));
		keynum6.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		keynum7.setFont(font);
		keynum7.setBackground(GlobalConstant.getDefaultKeyColor());
		keynum7.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keynum7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keynum7.setText("7");
		keynum7.setFocusable(false);
		keynum7.setMaximumSize(new java.awt.Dimension(40, 40));
		keynum7.setMinimumSize(new java.awt.Dimension(40, 40));
		keynum7.setName("0x67"); // NOI18N
		keynum7.setOpaque(true);
		keynum7.setBorder(blackline);
		keynum7.setPreferredSize(new java.awt.Dimension(40, 40));
		keynum7.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keynum8.setFont(font);
		keynum8.setBackground(GlobalConstant.getDefaultKeyColor());
		keynum8.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keynum8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keynum8.setText("8");
		keynum8.setFocusable(false);
		keynum8.setMaximumSize(new java.awt.Dimension(40, 40));
		keynum8.setMinimumSize(new java.awt.Dimension(40, 40));
		keynum8.setName("0x68"); // NOI18N
		keynum8.setOpaque(true);
		keynum8.setBorder(blackline);
		keynum8.setPreferredSize(new java.awt.Dimension(40, 40));
		keynum8.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keynum9.setFont(font);
		keynum9.setBackground(GlobalConstant.getDefaultKeyColor());
		keynum9.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keynum9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keynum9.setText("9");
		keynum9.setFocusable(false);
		keynum9.setMaximumSize(new java.awt.Dimension(40, 40));
		keynum9.setMinimumSize(new java.awt.Dimension(40, 40));
		keynum9.setName("0x69"); // NOI18N
		keynum9.setOpaque(true);
		keynum9.setBorder(blackline);
		keynum9.setPreferredSize(new java.awt.Dimension(40, 40));
		keynum9.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});


		keynum0.setFont(font);
		keynum0.setBackground(GlobalConstant.getDefaultKeyColor());
		keynum0.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keynum0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keynum0.setText("0");
		keynum0.setFocusable(false);
		keynum0.setMaximumSize(new java.awt.Dimension(86, 40));
		keynum0.setMinimumSize(new java.awt.Dimension(86, 40));
		keynum0.setName("0x30"); // NOI18N
		keynum0.setOpaque(true);
		keynum0.setBorder(blackline);
		keynum0.setPreferredSize(new java.awt.Dimension(86, 40));
		keynum0.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		keynp_maatru.setFont(font);
		keynp_maatru.setBackground(GlobalConstant.getDefaultKeyColor());
		keynp_maatru.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keynp_maatru.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keynp_maatru.setText("<html>🗘 மீள்</html>");
		keynp_maatru.setName("0x27F3"); // NOI18N
		keynp_maatru.setFocusable(false);
		keynp_maatru.setMaximumSize(new java.awt.Dimension(60, 40));
		keynp_maatru.setMinimumSize(new java.awt.Dimension(60, 40));
		keynp_maatru.setOpaque(true);
		keynp_maatru.setPreferredSize(new java.awt.Dimension(60, 40));
		keynp_maatru.setBorder(blackline);
		keynp_maatru.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if(emojiPanel.isVisible() | symbolsPanel.isVisible() | classicPanel.isVisible())
				{			
					alphabetPanel.setVisible(true);
					emojiPanel.setVisible(false);
					symbolsPanel.setVisible(false);
					classicPanel.setVisible(false);
				}
				keyClicked(evt);

			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});


		keynp_backspace.setFont(font);
		keynp_backspace.setBackground(GlobalConstant.getDefaultKeyColor());
		keynp_backspace.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keynp_backspace.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keynp_backspace.setText("< நீக்கு");
		keynp_backspace.setFocusable(false);
		keynp_backspace.setMaximumSize(new java.awt.Dimension(82, 40));
		keynp_backspace.setMinimumSize(new java.awt.Dimension(82, 40));
		keynp_backspace.setName("0x08"); // NOI18N
		keynp_backspace.setOpaque(true);
		keynp_backspace.setPreferredSize(new java.awt.Dimension(82, 40));
		keynp_backspace.setBorder(blackline);
		keynp_backspace.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keynp_del.setFont(font);
		keynp_del.setBackground(GlobalConstant.getDefaultKeyColor());
		keynp_del.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keynp_del.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keynp_del.setText("நீக்கு >");
		keynp_del.setFocusable(false);
		keynp_del.setMaximumSize(new java.awt.Dimension(82, 40));
		keynp_del.setMinimumSize(new java.awt.Dimension(82, 40));
		keynp_del.setName("0x7F"); // NOI18N
		keynp_del.setOpaque(true);
		keynp_del.setPreferredSize(new java.awt.Dimension(82, 40));
		keynp_del.setBorder(blackline);
		keynp_del.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		
		javax.swing.GroupLayout numpadPanelLayout = new javax.swing.GroupLayout(numpadPanel);
		numpadPanel.setLayout(numpadPanelLayout);
		numpadPanelLayout
				.setHorizontalGroup(numpadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(numpadPanelLayout.createSequentialGroup()
								.addGap(4, 4, 4)
								.addComponent(keyt_numalt, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keynum1, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keynum2, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keynum3, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keynum4, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keynum5, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keynum6, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keynum7, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keynum8, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keynum9, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keynum0, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keynp_maatru, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keynp_backspace, javax.swing.GroupLayout.PREFERRED_SIZE, 82,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keynp_del, javax.swing.GroupLayout.PREFERRED_SIZE, 82,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		numpadPanelLayout.setVerticalGroup(numpadPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(numpadPanelLayout.createSequentialGroup()
						.addGroup(numpadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGap(4, 4, 4)
								.addComponent(keyt_numalt, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keynum1, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keynum2, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keynum3, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keynum4, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keynum5, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keynum6, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keynum7, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keynum8, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keynum9, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keynum0, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keynp_maatru, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keynp_backspace, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keynp_del, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(4,4,4)));
						//.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		
		functionPanel.setBackground(GlobalConstant.getDefaultPanelBgColor());
		functionPanel.setFocusable(false);
		//setSize(getWidth(), getHeight());

		keyt_altnum.setFont(font);
		keyt_altnum.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_altnum.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_altnum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_altnum.setText("12/௫௪");
		keyt_altnum.setFocusable(false);
		keyt_altnum.setMaximumSize(new java.awt.Dimension(70, 40));
		keyt_altnum.setMinimumSize(new java.awt.Dimension(70, 40));
		keyt_altnum.setName("0xF423E"); // NOI18N
		keyt_altnum.setOpaque(true);
		keyt_altnum.setPreferredSize(new java.awt.Dimension(70, 40));
		keyt_altnum.setBorder(blackline);
		keyt_altnum.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if(functionPanel.isVisible())
				{			
					functionPanel.setVisible(false);
					numpadPanel.setVisible(true);
				}
				else
				{
					functionPanel.setVisible(true);
					numpadPanel.setVisible(false);
				}
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				//keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				//keyReleased(evt);
			}
		});

		key1.setFont(font);
		key1.setBackground(GlobalConstant.getDefaultKeyColor());
		key1.setForeground(GlobalConstant.getDefaultKeyFgColor());
		key1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		key1.setText("௧");
		key1.setFocusable(false);
		key1.setMaximumSize(new java.awt.Dimension(40, 40));
		key1.setMinimumSize(new java.awt.Dimension(40, 40));
		key1.setName("0x0BE7"); // NOI18N
		key1.setOpaque(true);
		key1.setPreferredSize(new java.awt.Dimension(40, 40));
		key1.setBorder(blackline);
		key1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		key2.setFont(font);
		key2.setBackground(GlobalConstant.getDefaultKeyColor());
		key2.setForeground(GlobalConstant.getDefaultKeyFgColor());
		key2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		key2.setText("௨");
		key2.setFocusable(false);
		key2.setMaximumSize(new java.awt.Dimension(40, 40));
		key2.setMinimumSize(new java.awt.Dimension(40, 40));
		key2.setName("0xBE8"); // NOI18N
		key2.setOpaque(true);
		key2.setPreferredSize(new java.awt.Dimension(40, 40));
		key2.setBorder(blackline);
		key2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		key3.setFont(font);
		key3.setBackground(GlobalConstant.getDefaultKeyColor());
		key3.setForeground(GlobalConstant.getDefaultKeyFgColor());
		key3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		key3.setText("௩");
		key3.setFocusable(false);
		key3.setMaximumSize(new java.awt.Dimension(40, 40));
		key3.setMinimumSize(new java.awt.Dimension(40, 40));
		key3.setName("0xBE9"); // NOI18N
		key3.setOpaque(true);
		key3.setPreferredSize(new java.awt.Dimension(40, 40));
		key3.setBorder(blackline);
		key3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		key4.setFont(font);
		key4.setBackground(GlobalConstant.getDefaultKeyColor());
		key4.setForeground(GlobalConstant.getDefaultKeyFgColor());
		key4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		key4.setText("௪");
		key4.setFocusable(false);
		key4.setMaximumSize(new java.awt.Dimension(40, 40));
		key4.setMinimumSize(new java.awt.Dimension(40, 40));
		key4.setName("0xBEA"); // NOI18N
		key4.setOpaque(true);
		key4.setPreferredSize(new java.awt.Dimension(40, 40));
		key4.setBorder(blackline);
		key4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		key5.setFont(font);
		key5.setBackground(GlobalConstant.getDefaultKeyColor());
		key5.setForeground(GlobalConstant.getDefaultKeyFgColor());
		key5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		key5.setText("௫");
		key5.setFocusable(false);
		key5.setMaximumSize(new java.awt.Dimension(40, 40));
		key5.setMinimumSize(new java.awt.Dimension(40, 40));
		key5.setName("0xBEB"); // NOI18N
		key5.setOpaque(true);
		key5.setPreferredSize(new java.awt.Dimension(40, 40));
		key5.setBorder(blackline);
		key5.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		key6.setFont(font);
		key6.setBackground(GlobalConstant.getDefaultKeyColor());
		key6.setForeground(GlobalConstant.getDefaultKeyFgColor());
		key6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		key6.setText("௬");
		key6.setFocusable(false);
		key6.setMaximumSize(new java.awt.Dimension(40, 40));
		key6.setMinimumSize(new java.awt.Dimension(40, 40));
		key6.setName("0xBEC"); // NOI18N
		key6.setOpaque(true);
		key6.setPreferredSize(new java.awt.Dimension(40, 40));
		key6.setBorder(blackline);
		key6.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		key7.setFont(font);
		key7.setBackground(GlobalConstant.getDefaultKeyColor());
		key7.setForeground(GlobalConstant.getDefaultKeyFgColor());
		key7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		key7.setText("௭");
		key7.setFocusable(false);
		key7.setMaximumSize(new java.awt.Dimension(40, 40));
		key7.setMinimumSize(new java.awt.Dimension(40, 40));
		key7.setName("0xBED"); // NOI18N
		key7.setOpaque(true);
		key7.setPreferredSize(new java.awt.Dimension(40, 40));
		key7.setBorder(blackline);
		key7.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		key8.setFont(font);
		key8.setBackground(GlobalConstant.getDefaultKeyColor());
		key8.setForeground(GlobalConstant.getDefaultKeyFgColor());
		key8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		key8.setText("௮");
		key8.setFocusable(false);
		key8.setMaximumSize(new java.awt.Dimension(40, 40));
		key8.setMinimumSize(new java.awt.Dimension(40, 40));
		key8.setName("0xBEE"); // NOI18N
		key8.setOpaque(true);
		key8.setPreferredSize(new java.awt.Dimension(40, 40));
		key8.setBorder(blackline);
		key8.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		key9.setFont(font);
		key9.setBackground(GlobalConstant.getDefaultKeyColor());
		key9.setForeground(GlobalConstant.getDefaultKeyFgColor());
		key9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		key9.setText("௯");
		key9.setFocusable(false);
		key9.setMaximumSize(new java.awt.Dimension(40, 40));
		key9.setMinimumSize(new java.awt.Dimension(40, 40));
		key9.setName("0xBEF"); // NOI18N
		key9.setOpaque(true);
		key9.setPreferredSize(new java.awt.Dimension(40, 40));
		key9.setBorder(blackline);
		key9.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		key0.setFont(font);
		key0.setBackground(GlobalConstant.getDefaultKeyColor());
		key0.setForeground(GlobalConstant.getDefaultKeyFgColor());
		key0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		key0.setText("0");
		key0.setFocusable(false);
		key0.setMaximumSize(new java.awt.Dimension(40, 40));
		key0.setMinimumSize(new java.awt.Dimension(40, 40));
		key0.setName("0x30"); 
		key0.setOpaque(true);
		key0.setPreferredSize(new java.awt.Dimension(40, 40));
		key0.setBorder(blackline);
		key0.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});


		keyt_maatru.setFont(font);
		keyt_maatru.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_maatru.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_maatru.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_maatru.setText("<html>🗘 மீள்</html>");
		keyt_maatru.setName("0x27F3"); // NOI18N
		keyt_maatru.setFocusable(false);
		keyt_maatru.setMaximumSize(new java.awt.Dimension(60, 40));
		keyt_maatru.setMinimumSize(new java.awt.Dimension(60, 40));
		keyt_maatru.setOpaque(true);
		keyt_maatru.setPreferredSize(new java.awt.Dimension(60, 40));
		keyt_maatru.setBorder(blackline);
		keyt_maatru.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if(emojiPanel.isVisible() | symbolsPanel.isVisible() | classicPanel.isVisible())
				{			
					alphabetPanel.setVisible(true);
					emojiPanel.setVisible(false);
					symbolsPanel.setVisible(false);
					classicPanel.setVisible(false);
				}
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});


		keybackspace.setFont(font);
		keybackspace.setBackground(GlobalConstant.getDefaultKeyColor());
		keybackspace.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keybackspace.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keybackspace.setText("< நீக்கு");
		keybackspace.setFocusable(false);
		keybackspace.setMaximumSize(new java.awt.Dimension(82, 40));
		keybackspace.setMinimumSize(new java.awt.Dimension(82, 40));
		keybackspace.setName("0x08"); // NOI18N
		keybackspace.setOpaque(true);
		keybackspace.setPreferredSize(new java.awt.Dimension(82, 40));
		keybackspace.setBorder(blackline);
		keybackspace.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyt_del.setFont(font);
		keyt_del.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_del.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_del.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_del.setText("நீக்கு >");
		keyt_del.setFocusable(false);
		keyt_del.setMaximumSize(new java.awt.Dimension(82, 40));
		keyt_del.setMinimumSize(new java.awt.Dimension(82, 40));
		keyt_del.setName("0x7F"); // NOI18N
		keyt_del.setOpaque(true);
		keyt_del.setPreferredSize(new java.awt.Dimension(82, 40));
		keyt_del.setBorder(blackline);
		keyt_del.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		javax.swing.GroupLayout functionPanelLayout = new javax.swing.GroupLayout(functionPanel);
		functionPanel.setLayout(functionPanelLayout);
		functionPanelLayout
				.setHorizontalGroup(functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(functionPanelLayout.createSequentialGroup()
								.addGap(4, 4, 4)
								.addComponent(keyt_altnum, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(key1, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(key2, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(key3, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(key4, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(key5, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(key6, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(key7, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(key8, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(key9, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(key0, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keyt_maatru, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keybackspace, javax.swing.GroupLayout.PREFERRED_SIZE, 82,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keyt_del, javax.swing.GroupLayout.PREFERRED_SIZE, 82,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)));
		functionPanelLayout.setVerticalGroup(functionPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(functionPanelLayout.createSequentialGroup()
						.addGroup(functionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGap(4, 4, 4)
								.addComponent(keyt_altnum, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(key1, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(key2, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(key3, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(key4, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(key5, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(key6, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(key7, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(key8, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(key9, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(key0, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keyt_maatru, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keybackspace, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keyt_del, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(4, 4, 4)));
		
		alphabetPanel.setBackground(GlobalConstant.getDefaultPanelBgColor());
		alphabetPanel.setFocusable(false);



		keyq.setFont(font);
		keyq.setBackground(GlobalConstant.getDefaultKeyColor());
		keyq.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyq.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyq.setText("அ");
		keyq.setFocusable(false);
		keyq.setMaximumSize(new java.awt.Dimension(40, 40));
		keyq.setMinimumSize(new java.awt.Dimension(40, 40));
		keyq.setName("0xB85"); // NOI18N
		keyq.setOpaque(true);
		keyq.setBorder(blackline);
		keyq.setPreferredSize(new java.awt.Dimension(40, 40));
		keyq.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyw.setFont(font);
		keyw.setBackground(GlobalConstant.getDefaultKeyColor());
		keyw.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyw.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyw.setText("ஆ");
		keyw.setFocusable(false);
		keyw.setMaximumSize(new java.awt.Dimension(40, 40));
		keyw.setMinimumSize(new java.awt.Dimension(40, 40));
		keyw.setName("0xB86"); // NOI18N
		keyw.setOpaque(true);
		keyw.setBorder(blackline);
		keyw.setPreferredSize(new java.awt.Dimension(40, 40));
		keyw.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keye.setFont(font);
		keye.setBackground(GlobalConstant.getDefaultKeyColor());
		keye.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keye.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keye.setText("இ");
		keye.setFocusable(false);
		keye.setMaximumSize(new java.awt.Dimension(40, 40));
		keye.setMinimumSize(new java.awt.Dimension(40, 40));
		keye.setName("0xB87"); // NOI18N
		keye.setOpaque(true);
		keye.setBorder(blackline);
		keye.setPreferredSize(new java.awt.Dimension(40, 40));
		keye.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyr.setFont(font);
		keyr.setBackground(GlobalConstant.getDefaultKeyColor());
		keyr.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyr.setText("ஈ");
		keyr.setFocusable(false);
		keyr.setMaximumSize(new java.awt.Dimension(40, 40));
		keyr.setMinimumSize(new java.awt.Dimension(40, 40));
		keyr.setName("0xB88"); // NOI18N
		keyr.setOpaque(true);
		keyr.setBorder(blackline);
		keyr.setPreferredSize(new java.awt.Dimension(40, 40));
		keyr.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyt.setFont(font);
		keyt.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt.setText("உ");
		keyt.setFocusable(false);
		keyt.setMaximumSize(new java.awt.Dimension(40, 40));
		keyt.setMinimumSize(new java.awt.Dimension(40, 40));
		keyt.setName("0xB89"); // NOI18N
		keyt.setOpaque(true);
		keyt.setBorder(blackline);
		keyt.setPreferredSize(new java.awt.Dimension(40, 40));
		keyt.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyy.setFont(font);
		keyy.setBackground(GlobalConstant.getDefaultKeyColor());
		keyy.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyy.setText("ஊ");
		keyy.setFocusable(false);
		keyy.setMaximumSize(new java.awt.Dimension(40, 40));
		keyy.setMinimumSize(new java.awt.Dimension(40, 40));
		keyy.setName("0xB8A"); // NOI18N
		keyy.setOpaque(true);
		keyy.setBorder(blackline);
		keyy.setPreferredSize(new java.awt.Dimension(40, 40));
		keyy.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyu.setFont(font);
		keyu.setBackground(GlobalConstant.getDefaultKeyColor());
		keyu.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyu.setText("எ");
		keyu.setFocusable(false);
		keyu.setMaximumSize(new java.awt.Dimension(40, 40));
		keyu.setMinimumSize(new java.awt.Dimension(40, 40));
		keyu.setName("0xB8E"); // NOI18N
		keyu.setOpaque(true);
		keyu.setBorder(blackline);
		keyu.setPreferredSize(new java.awt.Dimension(40, 40));
		keyu.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyi.setFont(font);
		keyi.setBackground(GlobalConstant.getDefaultKeyColor());
		keyi.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyi.setText("ஏ");
		keyi.setFocusable(false);
		keyi.setMaximumSize(new java.awt.Dimension(40, 40));
		keyi.setMinimumSize(new java.awt.Dimension(40, 40));
		keyi.setName("0xB8F"); // NOI18N
		keyi.setOpaque(true);
		keyi.setBorder(blackline);
		keyi.setPreferredSize(new java.awt.Dimension(40, 40));
		keyi.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyo.setFont(font);
		keyo.setBackground(GlobalConstant.getDefaultKeyColor());
		keyo.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyo.setText("ஐ");
		keyo.setFocusable(false);
		keyo.setMaximumSize(new java.awt.Dimension(40, 40));
		keyo.setMinimumSize(new java.awt.Dimension(40, 40));
		keyo.setName("0xB90"); // NOI18N
		keyo.setOpaque(true);
		keyo.setBorder(blackline);
		keyo.setPreferredSize(new java.awt.Dimension(40, 40));
		keyo.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyp.setFont(font);
		keyp.setBackground(GlobalConstant.getDefaultKeyColor());
		keyp.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyp.setText("ஒ");
		keyp.setFocusable(false);
		keyp.setMaximumSize(new java.awt.Dimension(50, 40));
		keyp.setMinimumSize(new java.awt.Dimension(50, 40));
		keyp.setName("0xB92"); // NOI18N
		keyp.setOpaque(true);
		keyp.setBorder(blackline);
		keyp.setPreferredSize(new java.awt.Dimension(50, 40));
		keyp.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyt_o.setFont(font);
		keyt_o.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_o.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_o.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_o.setText("ஓ");
		keyt_o.setFocusable(false);
		keyt_o.setMaximumSize(new java.awt.Dimension(50, 40));
		keyt_o.setMinimumSize(new java.awt.Dimension(50, 40));
		keyt_o.setName("0xB93"); // NOI18N
		keyt_o.setOpaque(true);
		keyt_o.setBorder(blackline);
		keyt_o.setPreferredSize(new java.awt.Dimension(50, 40));
		keyt_o.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyt_ov.setFont(font);
		keyt_ov.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_ov.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_ov.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_ov.setText("ஔ");
		keyt_ov.setFocusable(false);
		keyt_ov.setMaximumSize(new java.awt.Dimension(50, 40));
		keyt_ov.setMinimumSize(new java.awt.Dimension(50, 40));
		keyt_ov.setName("0xB94"); // NOI18N
		keyt_ov.setOpaque(true);
		keyt_ov.setBorder(blackline);
		keyt_ov.setPreferredSize(new java.awt.Dimension(50, 40));
		keyt_ov.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		
		keyt_ayutha.setFont(font);
		keyt_ayutha.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_ayutha.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_ayutha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_ayutha.setText("ஃ");
		keyt_ayutha.setFocusable(false);
		keyt_ayutha.setMaximumSize(new java.awt.Dimension(40, 40));
		keyt_ayutha.setMinimumSize(new java.awt.Dimension(40, 40));
		keyt_ayutha.setName("0xB83"); // NOI18N
		keyt_ayutha.setOpaque(true);
		keyt_ayutha.setBorder(blackline);
		keyt_ayutha.setPreferredSize(new java.awt.Dimension(40, 40));
		keyt_ayutha.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyt_om.setFont(font);
		keyt_om.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_om.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_om.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_om.setText("ௐ");
		keyt_om.setFocusable(false);
		keyt_om.setMaximumSize(new java.awt.Dimension(40, 40));
		keyt_om.setMinimumSize(new java.awt.Dimension(40, 40));
		keyt_om.setName("0xBD0"); // NOI18N
		keyt_om.setOpaque(true);
		keyt_om.setBorder(blackline);
		keyt_om.setPreferredSize(new java.awt.Dimension(40, 40));
		keyt_om.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyt_suli.setFont(font);
		keyt_suli.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_suli.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_suli.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_suli.setText("௳");
		keyt_suli.setFocusable(false);
		keyt_suli.setMaximumSize(new java.awt.Dimension(40, 40));
		keyt_suli.setMinimumSize(new java.awt.Dimension(40, 40));
		keyt_suli.setName("0x0BF3"); // NOI18N
		keyt_suli.setOpaque(true);
		keyt_suli.setBorder(blackline);
		keyt_suli.setPreferredSize(new java.awt.Dimension(40, 40));
		keyt_suli.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		keyt_sri.setFont(font);
		keyt_sri.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_sri.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_sri.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_sri.setText("ஸ்ரீ");
		keyt_sri.setFocusable(false);
		keyt_sri.setMaximumSize(new java.awt.Dimension(40, 40));
		keyt_sri.setMinimumSize(new java.awt.Dimension(40, 40));
		keyt_sri.setName("0xF423B"); // NOI18N
		keyt_sri.setOpaque(true);
		keyt_sri.setBorder(blackline);
		keyt_sri.setPreferredSize(new java.awt.Dimension(40, 40));
		keyt_sri.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		keyopenbigbracket.setBackground(GlobalConstant.getDefaultKeyColor());
		keyopenbigbracket.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyopenbigbracket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyopenbigbracket.setText("<html>{<br />&nbsp;&nbsp;[</html>");
		keyopenbigbracket.setFocusable(false);
		keyopenbigbracket.setMaximumSize(new java.awt.Dimension(40, 40));
		keyopenbigbracket.setMinimumSize(new java.awt.Dimension(40, 40));
		keyopenbigbracket.setName("0x5B"); // NOI18N
		keyopenbigbracket.setOpaque(true);
		keyopenbigbracket.setBorder(blackline);
		keyopenbigbracket.setPreferredSize(new java.awt.Dimension(40, 40));
		keyopenbigbracket.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyclosebigbracket.setBackground(GlobalConstant.getDefaultKeyColor());
		keyclosebigbracket.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyclosebigbracket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyclosebigbracket.setText("<html>}<br />&nbsp;&nbsp;]</html>");
		keyclosebigbracket.setFocusable(false);
		keyclosebigbracket.setMaximumSize(new java.awt.Dimension(40, 40));
		keyclosebigbracket.setMinimumSize(new java.awt.Dimension(40, 40));
		keyclosebigbracket.setName("0x5D"); // NOI18N
		keyclosebigbracket.setOpaque(true);
		keyclosebigbracket.setBorder(blackline);
		keyclosebigbracket.setPreferredSize(new java.awt.Dimension(40, 40));
		keyclosebigbracket.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});




		keya.setFont(font);
		keya.setBackground(GlobalConstant.getDefaultKeyColor());
		keya.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keya.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keya.setText("க");
		keya.setFocusable(false);
		keya.setMaximumSize(new java.awt.Dimension(40, 40));
		keya.setMinimumSize(new java.awt.Dimension(40, 40));
		keya.setName("0xB95"); // NOI18N
		keya.setOpaque(true);
		keya.setBorder(blackline);
		keya.setPreferredSize(new java.awt.Dimension(40, 40));
		keya.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});


		keys.setFont(font);
		keys.setBackground(GlobalConstant.getDefaultKeyColor());
		keys.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keys.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keys.setText("ங");
		keys.setFocusable(false);
		keys.setMaximumSize(new java.awt.Dimension(40, 40));
		keys.setMinimumSize(new java.awt.Dimension(40, 40));
		keys.setName("0xB99"); // NOI18N
		keys.setOpaque(true);
		keys.setBorder(blackline);
		keys.setPreferredSize(new java.awt.Dimension(40, 40));
		keys.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyd.setFont(font);
		keyd.setBackground(GlobalConstant.getDefaultKeyColor());
		keyd.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyd.setText("ச");
		keyd.setFocusable(false);
		keyd.setMaximumSize(new java.awt.Dimension(40, 40));
		keyd.setMinimumSize(new java.awt.Dimension(40, 40));
		keyd.setName("0xB9A"); // NOI18N
		keyd.setOpaque(true);
		keyd.setBorder(blackline);
		keyd.setPreferredSize(new java.awt.Dimension(40, 40));
		keyd.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyf.setFont(font);
		keyf.setBackground(GlobalConstant.getDefaultKeyColor());
		keyf.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyf.setText("ஞ");
		keyf.setFocusable(false);
		keyf.setMaximumSize(new java.awt.Dimension(40, 40));
		keyf.setMinimumSize(new java.awt.Dimension(40, 40));
		keyf.setName("0xB9E"); // NOI18N
		keyf.setOpaque(true);
		keyf.setBorder(blackline);
		keyf.setPreferredSize(new java.awt.Dimension(40, 40));
		keyf.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyg.setFont(font);
		keyg.setBackground(GlobalConstant.getDefaultKeyColor());
		keyg.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyg.setText("ட");
		keyg.setFocusable(false);
		keyg.setMaximumSize(new java.awt.Dimension(40, 40));
		keyg.setMinimumSize(new java.awt.Dimension(40, 40));
		keyg.setName("0xB9F"); // NOI18N
		keyg.setOpaque(true);
		keyg.setBorder(blackline);
		keyg.setPreferredSize(new java.awt.Dimension(40, 40));
		keyg.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyh.setFont(font);
		keyh.setBackground(GlobalConstant.getDefaultKeyColor());
		keyh.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyh.setText("ண");
		keyh.setFocusable(false);
		keyh.setMaximumSize(new java.awt.Dimension(40, 40));
		keyh.setMinimumSize(new java.awt.Dimension(40, 40));
		keyh.setName("0xBA3"); // NOI18N
		keyh.setOpaque(true);
		keyh.setBorder(blackline);
		keyh.setPreferredSize(new java.awt.Dimension(40, 40));
		keyh.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyj.setFont(font);
		keyj.setBackground(GlobalConstant.getDefaultKeyColor());
		keyj.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyj.setText("த");
		keyj.setFocusable(false);
		keyj.setMaximumSize(new java.awt.Dimension(40, 40));
		keyj.setMinimumSize(new java.awt.Dimension(40, 40));
		keyj.setName("0xBA4"); // NOI18N
		keyj.setOpaque(true);
		keyj.setBorder(blackline);
		keyj.setPreferredSize(new java.awt.Dimension(40, 40));
		keyj.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyk.setFont(font);
		keyk.setBackground(GlobalConstant.getDefaultKeyColor());
		keyk.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyk.setText("ந");
		keyk.setFocusable(false);
		keyk.setMaximumSize(new java.awt.Dimension(40, 40));
		keyk.setMinimumSize(new java.awt.Dimension(40, 40));
		keyk.setName("0xBA8"); // NOI18N
		keyk.setOpaque(true);
		keyk.setBorder(blackline);
		keyk.setPreferredSize(new java.awt.Dimension(40, 40));
		keyk.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyl.setFont(font);
		keyl.setBackground(GlobalConstant.getDefaultKeyColor());
		keyl.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyl.setText("ப");
		keyl.setFocusable(false);
		keyl.setMaximumSize(new java.awt.Dimension(40, 40));
		keyl.setMinimumSize(new java.awt.Dimension(40, 40));
		keyl.setName("0xBAA"); // NOI18N
		keyl.setOpaque(true);
		keyl.setBorder(blackline);
		keyl.setPreferredSize(new java.awt.Dimension(40, 40));
		keyl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		keyt_ma.setFont(font);
		keyt_ma.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_ma.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_ma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_ma.setText("ம");
		keyt_ma.setFocusable(false);
		keyt_ma.setMaximumSize(new java.awt.Dimension(40, 40));
		keyt_ma.setMinimumSize(new java.awt.Dimension(40, 40));
		keyt_ma.setName("0xBAE"); // NOI18N
		keyt_ma.setOpaque(true);
		keyt_ma.setBorder(blackline);
		keyt_ma.setPreferredSize(new java.awt.Dimension(40, 40));
		keyt_ma.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		keyt_ya.setFont(font);
		keyt_ya.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_ya.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_ya.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_ya.setText("ய");
		keyt_ya.setFocusable(false);
		keyt_ya.setMaximumSize(new java.awt.Dimension(40, 40));
		keyt_ya.setMinimumSize(new java.awt.Dimension(40, 40));
		keyt_ya.setName("0xBAF"); // NOI18N
		keyt_ya.setOpaque(true);
		keyt_ya.setBorder(blackline);
		keyt_ya.setPreferredSize(new java.awt.Dimension(40, 40));
		keyt_ya.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		keyt_ra.setFont(font);
		keyt_ra.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_ra.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_ra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_ra.setText("ர");
		keyt_ra.setFocusable(false);
		keyt_ra.setMaximumSize(new java.awt.Dimension(40, 40));
		keyt_ra.setMinimumSize(new java.awt.Dimension(40, 40));
		keyt_ra.setName("0xBB0"); // NOI18N
		keyt_ra.setOpaque(true);
		keyt_ra.setBorder(blackline);
		keyt_ra.setPreferredSize(new java.awt.Dimension(40, 40));
		keyt_ra.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyt_la.setFont(font);
		keyt_la.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_la.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_la.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_la.setText("ல");
		keyt_la.setFocusable(false);
		keyt_la.setMaximumSize(new java.awt.Dimension(40, 40));
		keyt_la.setMinimumSize(new java.awt.Dimension(40, 40));
		keyt_la.setName("0xBB2"); // NOI18N
		keyt_la.setOpaque(true);
		keyt_la.setBorder(blackline);
		keyt_la.setPreferredSize(new java.awt.Dimension(40, 40));
		keyt_la.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});



		




		keyz.setFont(font);
		keyz.setBackground(GlobalConstant.getDefaultKeyColor());
		keyz.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyz.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyz.setText("வ");
		keyz.setFocusable(false);
		keyz.setMaximumSize(new java.awt.Dimension(40, 40));
		keyz.setMinimumSize(new java.awt.Dimension(40, 40));
		keyz.setName("0xBB5"); // NOI18N
		keyz.setOpaque(true);
		keyz.setBorder(blackline);
		keyz.setPreferredSize(new java.awt.Dimension(40, 40));
		keyz.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyx.setFont(font);
		keyx.setBackground(GlobalConstant.getDefaultKeyColor());
		keyx.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyx.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyx.setText("ழ");
		keyx.setFocusable(false);
		keyx.setMaximumSize(new java.awt.Dimension(40, 40));
		keyx.setMinimumSize(new java.awt.Dimension(40, 40));
		keyx.setName("0xBB4"); // NOI18N
		keyx.setOpaque(true);
		keyx.setBorder(blackline);
		keyx.setPreferredSize(new java.awt.Dimension(40, 40));
		keyx.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyc.setFont(font);
		keyc.setBackground(GlobalConstant.getDefaultKeyColor());
		keyc.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyc.setText("ள");
		keyc.setFocusable(false);
		keyc.setMaximumSize(new java.awt.Dimension(40, 40));
		keyc.setMinimumSize(new java.awt.Dimension(40, 40));
		keyc.setName("0xBB3"); // NOI18N
		keyc.setOpaque(true);
		keyc.setBorder(blackline);
		keyc.setPreferredSize(new java.awt.Dimension(40, 40));
		keyc.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyv.setFont(font);
		keyv.setBackground(GlobalConstant.getDefaultKeyColor());
		keyv.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyv.setText("ற");
		keyv.setFocusable(false);
		keyv.setMaximumSize(new java.awt.Dimension(40, 40));
		keyv.setMinimumSize(new java.awt.Dimension(40, 40));
		keyv.setName("0xBB1"); // NOI18N
		keyv.setOpaque(true);
		keyv.setBorder(blackline);
		keyv.setPreferredSize(new java.awt.Dimension(40, 40));
		keyv.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyb.setFont(font);
		keyb.setBackground(GlobalConstant.getDefaultKeyColor());
		keyb.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyb.setText("ன");
		keyb.setFocusable(false);
		keyb.setMaximumSize(new java.awt.Dimension(40, 40));
		keyb.setMinimumSize(new java.awt.Dimension(40, 40));
		keyb.setName("0xBA9"); // NOI18N
		keyb.setOpaque(true);
		keyb.setBorder(blackline);
		keyb.setPreferredSize(new java.awt.Dimension(40, 40));
		keyb.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyn.setFont(font);
		keyn.setBackground(GlobalConstant.getDefaultKeyColor());
		keyn.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyn.setText("ஷ");
		keyn.setFocusable(false);
		keyn.setMaximumSize(new java.awt.Dimension(40, 40));
		keyn.setMinimumSize(new java.awt.Dimension(40, 40));
		keyn.setName("0xBB7"); // NOI18N
		keyn.setOpaque(true);
		keyn.setBorder(blackline);
		keyn.setPreferredSize(new java.awt.Dimension(40, 40));
		keyn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keym.setFont(font);
		keym.setBackground(GlobalConstant.getDefaultKeyColor());
		keym.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keym.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keym.setText("ஸ");
		keym.setFocusable(false);
		keym.setMaximumSize(new java.awt.Dimension(40, 40));
		keym.setMinimumSize(new java.awt.Dimension(40, 40));
		keym.setName("0xBB8"); // NOI18N
		keym.setOpaque(true);
		keym.setBorder(blackline);
		keym.setPreferredSize(new java.awt.Dimension(40, 40));
		keym.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});


		keyt_ja.setFont(font);
		keyt_ja.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_ja.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_ja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_ja.setText("ஜ");
		keyt_ja.setFocusable(false);
		keyt_ja.setMaximumSize(new java.awt.Dimension(40, 40));
		keyt_ja.setMinimumSize(new java.awt.Dimension(40, 40));
		keyt_ja.setName("0xB9C"); // NOI18N
		keyt_ja.setOpaque(true);
		keyt_ja.setBorder(blackline);
		keyt_ja.setPreferredSize(new java.awt.Dimension(40, 40));
		keyt_ja.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyt_ha.setFont(font);
		keyt_ha.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_ha.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_ha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_ha.setText("ஹ");
		keyt_ha.setFocusable(false);
		keyt_ha.setMaximumSize(new java.awt.Dimension(40, 40));
		keyt_ha.setMinimumSize(new java.awt.Dimension(40, 40));
		keyt_ha.setName("0xBB9"); // NOI18N
		keyt_ha.setOpaque(true);
		keyt_ha.setBorder(blackline);
		keyt_ha.setPreferredSize(new java.awt.Dimension(40, 40));
		keyt_ha.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		keyt_sth.setFont(font);
		keyt_sth.setBackground(GlobalConstant.getDefaultKeyColor());
		keyt_sth.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyt_sth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyt_sth.setText("க்ஷ");
		keyt_sth.setFocusable(false);
		keyt_sth.setMaximumSize(new java.awt.Dimension(40, 40));
		keyt_sth.setMinimumSize(new java.awt.Dimension(40, 40));
		keyt_sth.setName("0xF423F"); // NOI18N
		keyt_sth.setOpaque(true);
		keyt_sth.setBorder(blackline);
		keyt_sth.setPreferredSize(new java.awt.Dimension(40, 40));
		keyt_sth.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		
		keyrupee.setFont(font);
		keyrupee.setBackground(GlobalConstant.getDefaultKeyColor());
		keyrupee.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyrupee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyrupee.setText("₹");
		keyrupee.setFocusable(false);
		keyrupee.setMaximumSize(new java.awt.Dimension(40, 40));
		keyrupee.setMinimumSize(new java.awt.Dimension(40, 40));
		keyrupee.setName("0x20B9"); // NOI18N
		keyrupee.setOpaque(true);
		keyrupee.setBorder(blackline);
		keyrupee.setPreferredSize(new java.awt.Dimension(40, 40));
		keyrupee.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("₹");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		keyampersand.setFont(font);
		keyampersand.setBackground(GlobalConstant.getDefaultKeyColor());
		keyampersand.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyampersand.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyampersand.setText("&");
		keyampersand.setFocusable(false);
		keyampersand.setMaximumSize(new java.awt.Dimension(40, 40));
		keyampersand.setMinimumSize(new java.awt.Dimension(40, 40));
		keyampersand.setName("0x0026"); // NOI18N
		keyampersand.setOpaque(true);
		keyampersand.setBorder(blackline);
		keyampersand.setPreferredSize(new java.awt.Dimension(40, 40));
		keyampersand.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("&");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

		});

		keyat.setFont(font);
		keyat.setBackground(GlobalConstant.getDefaultKeyColor());
		keyat.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyat.setText("@");
		keyat.setFocusable(false);
		keyat.setMaximumSize(new java.awt.Dimension(40, 40));
		keyat.setMinimumSize(new java.awt.Dimension(40, 40));
		keyat.setName("0x1F60D"); // NOI18N
		keyat.setOpaque(true);
		keyat.setBorder(blackline);
		keyat.setPreferredSize(new java.awt.Dimension(40, 40));
		keyat.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("@");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

		});
		
		keypound.setFont(font);
		keypound.setBackground(GlobalConstant.getDefaultKeyColor());
		keypound.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keypound.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keypound.setText("#");
		keypound.setFocusable(false);
		keypound.setMaximumSize(new java.awt.Dimension(40, 40));
		keypound.setMinimumSize(new java.awt.Dimension(40, 40));
		keypound.setName("0x0023"); // NOI18N
		keypound.setOpaque(true);
		keypound.setBorder(blackline);
		keypound.setPreferredSize(new java.awt.Dimension(40, 40));
		keypound.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		keyquestion.setFont(font);
		keyquestion.setBackground(GlobalConstant.getDefaultKeyColor());
		keyquestion.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyquestion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyquestion.setText("?");
		keyquestion.setFocusable(false);
		keyquestion.setMaximumSize(new java.awt.Dimension(40, 40));
		keyquestion.setMinimumSize(new java.awt.Dimension(40, 40));
		keyquestion.setName("0x003F"); // NOI18N
		keyquestion.setOpaque(true);
		keyquestion.setBorder(blackline);
		keyquestion.setPreferredSize(new java.awt.Dimension(40, 40));
		keyquestion.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		
		keysemicolon.setFont(font);
		keysemicolon.setBackground(GlobalConstant.getDefaultKeyColor());
		keysemicolon.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keysemicolon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keysemicolon.setText(";");
		keysemicolon.setFocusable(false);
		keysemicolon.setMaximumSize(new java.awt.Dimension(40, 40));
		keysemicolon.setMinimumSize(new java.awt.Dimension(40, 40));
		keysemicolon.setName("0x3B"); // NOI18N
		keysemicolon.setOpaque(true);
		keysemicolon.setBorder(blackline);
		keysemicolon.setPreferredSize(new java.awt.Dimension(40, 40));
		keysemicolon.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyfslash.setFont(font);
		keyfslash.setBackground(GlobalConstant.getDefaultKeyColor());
		keyfslash.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyfslash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyfslash.setText("/");
		keyfslash.setFocusable(false);
		keyfslash.setMaximumSize(new java.awt.Dimension(40, 40));
		keyfslash.setMinimumSize(new java.awt.Dimension(40, 40));
		keyfslash.setName("0x2F"); // NOI18N
		keyfslash.setOpaque(true);
		keyfslash.setBorder(blackline);
		keyfslash.setPreferredSize(new java.awt.Dimension(40, 40));
		keyfslash.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		keylrndbrace.setFont(font);
		keylrndbrace.setBackground(GlobalConstant.getDefaultKeyColor());
		keylrndbrace.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keylrndbrace.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keylrndbrace.setText("(");
		keylrndbrace.setFocusable(false);
		keylrndbrace.setMaximumSize(new java.awt.Dimension(40, 40));
		keylrndbrace.setMinimumSize(new java.awt.Dimension(40, 40));
		keylrndbrace.setName("0x0028"); // NOI18N
		keylrndbrace.setOpaque(true);
		keylrndbrace.setBorder(blackline);
		keylrndbrace.setPreferredSize(new java.awt.Dimension(40, 40));
		keylrndbrace.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("(");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}
		});
		
		keyrrndbrace.setFont(font);
		keyrrndbrace.setBackground(GlobalConstant.getDefaultKeyColor());
		keyrrndbrace.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyrrndbrace.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyrrndbrace.setText(")");
		keyrrndbrace.setFocusable(false);
		keyrrndbrace.setMaximumSize(new java.awt.Dimension(40, 40));
		keyrrndbrace.setMinimumSize(new java.awt.Dimension(40, 40));
		keyrrndbrace.setName("0x0029"); // NOI18N
		keyrrndbrace.setOpaque(true);
		keyrrndbrace.setBorder(blackline);
		keyrrndbrace.setPreferredSize(new java.awt.Dimension(40, 40));
		keyrrndbrace.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyunderscr.setFont(font);
		keyunderscr.setBackground(GlobalConstant.getDefaultKeyColor());
		keyunderscr.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyunderscr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyunderscr.setText("_");
		keyunderscr.setFocusable(false);
		keyunderscr.setMaximumSize(new java.awt.Dimension(40, 40));
		keyunderscr.setMinimumSize(new java.awt.Dimension(40, 40));
		keyunderscr.setName("0x5F"); // NOI18N
		keyunderscr.setOpaque(true);
		keyunderscr.setBorder(blackline);
		keyunderscr.setPreferredSize(new java.awt.Dimension(40, 40));
		keyunderscr.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		keycolon.setFont(font);
		keycolon.setBackground(GlobalConstant.getDefaultKeyColor());
		keycolon.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keycolon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keycolon.setText(":");
		keycolon.setFocusable(false);
		keycolon.setMaximumSize(new java.awt.Dimension(50, 40));
		keycolon.setMinimumSize(new java.awt.Dimension(50, 40));
		keycolon.setName("0x3A"); // NOI18N
		keycolon.setOpaque(true);
		keycolon.setBorder(blackline);
		keycolon.setPreferredSize(new java.awt.Dimension(50, 40));
		keycolon.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});


		keycomma.setFont(font);
		keycomma.setBackground(GlobalConstant.getDefaultKeyColor());
		keycomma.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keycomma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keycomma.setText(",");
		keycomma.setFocusable(false);
		keycomma.setMaximumSize(new java.awt.Dimension(50, 40));
		keycomma.setMinimumSize(new java.awt.Dimension(50, 40));
		keycomma.setName("0x2C"); // NOI18N
		keycomma.setOpaque(true);
		keycomma.setBorder(blackline);
		keycomma.setPreferredSize(new java.awt.Dimension(50, 40));
		keycomma.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});


		keydot.setFont(font);
		keydot.setBackground(GlobalConstant.getDefaultKeyColor());
		keydot.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keydot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keydot.setText(".");
		keydot.setFocusable(false);
		keydot.setMaximumSize(new java.awt.Dimension(40, 40));
		keydot.setMinimumSize(new java.awt.Dimension(40, 40));
		keydot.setName("0x2E"); // NOI18N
		keydot.setOpaque(true);
		keydot.setBorder(blackline);
		keydot.setPreferredSize(new java.awt.Dimension(40, 40));
		keydot.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		javax.swing.GroupLayout alphabetPanelLayout = new javax.swing.GroupLayout(alphabetPanel);
		alphabetPanel.setLayout(alphabetPanelLayout);
		alphabetPanelLayout.setHorizontalGroup(alphabetPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(alphabetPanelLayout.createSequentialGroup()
						.addGroup(alphabetPanelLayout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(alphabetPanelLayout.createSequentialGroup()
										.addComponent(keya, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keys, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyd, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyf, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyg, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyh, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyj, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyk, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyl, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyt_ya, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyt_ra, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyt_la, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4))
								.addGroup(alphabetPanelLayout.createSequentialGroup()
										.addComponent(keyq, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyw, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keye, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyt, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyy, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyu, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyi, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyo, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyp, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyt_o, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyt_ov, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyt_ayutha, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										)))
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING, alphabetPanelLayout
												.createSequentialGroup()
												.addGroup(alphabetPanelLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(alphabetPanelLayout.createSequentialGroup()
										.addComponent(keyz, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyx, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyc, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyv, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyb, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keym, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyt_ja, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyt_ha, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyt_sth, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyt_om, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyt_suli, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keyt_sri, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4))))
										.addGroup(alphabetPanelLayout.createSequentialGroup()
												.addComponent(keyrupee, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(keyampersand, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(keyat, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(keypound, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(keyquestion, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(keysemicolon, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(keyfslash, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(keylrndbrace, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(keyrrndbrace, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(keyunderscr, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(keycolon, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(keycomma, javax.swing.GroupLayout.PREFERRED_SIZE,50,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(keydot, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)));
		alphabetPanelLayout.setVerticalGroup(alphabetPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(alphabetPanelLayout.createSequentialGroup().addGroup(alphabetPanelLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(alphabetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(keyq, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keyw, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keye, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keyr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keyt, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keyy, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keyu, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keyi, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keyo, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keyp, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keyt_o, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keyt_ov, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keyt_ayutha, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(4, 4, 4)
						.addGroup(alphabetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(alphabetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(keya, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keys, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyd, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyf, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyg, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyh, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyj, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyk, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyl, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyt_ya, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyt_ra, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyt_la, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(4, 4, 4)
						.addGroup(alphabetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(alphabetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(keyz, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyx, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyc, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyv, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyb, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keym, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyt_ja, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyt_ha, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyt_sth, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyt_om, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyt_suli, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyt_sri, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(4, 4, 4)
						.addGroup(alphabetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(alphabetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(keyrupee, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyampersand, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyat, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keypound, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyquestion, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keysemicolon, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyfslash, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keylrndbrace, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyrrndbrace, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keyunderscr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keycolon, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keycomma, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(keydot, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(4, 4, 4))));

		keysym.setFont(font);
		keysym.setBackground(GlobalConstant.getDefaultKeyColor());
		keysym.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keysym.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keysym.setText("குறியீடு");
		keysym.setFocusable(false);
		keysym.setMaximumSize(new java.awt.Dimension(84, 40));
		keysym.setMinimumSize(new java.awt.Dimension(84, 40));
		keysym.setName("0xF423D"); // NOI18N
		keysym.setOpaque(true);
		keysym.setBorder(blackline);
		keysym.setPreferredSize(new java.awt.Dimension(84, 40));
		keysym.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if(alphabetPanel.isVisible() | emojiPanel.isVisible() | classicPanel.isVisible())
				{			
					alphabetPanel.setVisible(false);
					emojiPanel.setVisible(false);
					symbolsPanel.setVisible(true);
					classicPanel.setVisible(false);
				}
				else
				{
					alphabetPanel.setVisible(true);
					emojiPanel.setVisible(false);
					symbolsPanel.setVisible(false);
					classicPanel.setVisible(false);
				}
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyspace.setFont(font);
		keyspace.setBackground(GlobalConstant.getDefaultKeyColor());
		keyspace.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyspace.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyspace.setText("இடவெளி");
		keyspace.setFocusable(false);
		keyspace.setMaximumSize(new java.awt.Dimension(202, 40));
		keyspace.setMinimumSize(new java.awt.Dimension(202, 40));
		keyspace.setName("0x20"); // NOI18N
		keyspace.setOpaque(true);
		keyspace.setBorder(blackline);
		keyspace.setPreferredSize(new java.awt.Dimension(202, 40));
		keyspace.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyleftarrow.setFont(font);
		keyleftarrow.setBackground(GlobalConstant.getDefaultKeyColor());
		keyleftarrow.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyleftarrow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		//keyleftarrow.setText("<");
		keyleftarrow.setIcon(new ImageIcon(getClass().getResource("/icons/left.png")));
		keyleftarrow.setFocusable(false);
		keyleftarrow.setMaximumSize(new java.awt.Dimension(40, 40));
		keyleftarrow.setMinimumSize(new java.awt.Dimension(40, 40));
		keyleftarrow.setName("0x25"); // NOI18N
		keyleftarrow.setOpaque(true);
		keyleftarrow.setBorder(blackline);
		keyleftarrow.setPreferredSize(new java.awt.Dimension(40, 40));
		keyleftarrow.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyuparrow.setFont(font);
		keyuparrow.setBackground(GlobalConstant.getDefaultKeyColor());
		keyuparrow.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyuparrow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		//keyuparrow.setText("↑");
		keyuparrow.setIcon(new ImageIcon(getClass().getResource("/icons/up.png")));
		keyuparrow.setFocusable(false);
		keyuparrow.setMaximumSize(new java.awt.Dimension(40, 19));
		keyuparrow.setMinimumSize(new java.awt.Dimension(40, 19));
		keyuparrow.setName("0x26"); // NOI18N
		keyuparrow.setOpaque(true);
		keyuparrow.setBorder(blackline);
		keyuparrow.setPreferredSize(new java.awt.Dimension(40, 19));
		keyuparrow.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keydownarrow.setFont(font);
		keydownarrow.setBackground(GlobalConstant.getDefaultKeyColor());
		keydownarrow.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keydownarrow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		//keydownarrow.setText("ᐯ");
		keydownarrow.setIcon(new ImageIcon(getClass().getResource("/icons/down.png")));
		keydownarrow.setFocusable(false);
		keydownarrow.setMaximumSize(new java.awt.Dimension(40, 19));
		keydownarrow.setMinimumSize(new java.awt.Dimension(40, 19));
		keydownarrow.setName("0x28"); // NOI18N
		keydownarrow.setOpaque(true);
		keydownarrow.setBorder(blackline);
		keydownarrow.setPreferredSize(new java.awt.Dimension(40, 19));
		keydownarrow.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		
		keyrightarrow.setFont(font);
		keyrightarrow.setBackground(GlobalConstant.getDefaultKeyColor());
		keyrightarrow.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyrightarrow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		//keyrightarrow.setText(">");
		keyrightarrow.setIcon(new ImageIcon(getClass().getResource("/icons/right.png")));
		keyrightarrow.setFocusable(false);
		keyrightarrow.setMaximumSize(new java.awt.Dimension(40, 40));
		keyrightarrow.setMinimumSize(new java.awt.Dimension(40, 40));
		keyrightarrow.setName("0x27"); // NOI18N
		keyrightarrow.setOpaque(true);
		keyrightarrow.setBorder(blackline);
		keyrightarrow.setPreferredSize(new java.awt.Dimension(40, 40));
		keyrightarrow.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		keyoldfont.setFont(font);
		keyoldfont.setBackground(GlobalConstant.getDefaultKeyColor());
		keyoldfont.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyoldfont.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyoldfont.setText("பிறஎழுத்து");
		keyoldfont.setFocusable(false);
		keyoldfont.setMaximumSize(new java.awt.Dimension(94, 40));
		keyoldfont.setMinimumSize(new java.awt.Dimension(94, 40));
		keyoldfont.setName("0x11FE"); // NOI18N
		keyoldfont.setOpaque(true);
		keyoldfont.setBorder(blackline);
		keyoldfont.setPreferredSize(new java.awt.Dimension(94, 40));
		keyoldfont.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if(alphabetPanel.isVisible() | emojiPanel.isVisible() | symbolsPanel.isVisible())
				{			
					alphabetPanel.setVisible(false);
					emojiPanel.setVisible(false);
					symbolsPanel.setVisible(false);
					classicPanel.setVisible(true);
				}
				else
				{
					alphabetPanel.setVisible(true);
					emojiPanel.setVisible(false);
					symbolsPanel.setVisible(false);
					classicPanel.setVisible(false);

				}
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		keyrspace.setFont(font);
		keyrspace.setBackground(GlobalConstant.getDefaultKeyColor());
		keyrspace.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyrspace.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyrspace.setText("இடவெளி");
		keyrspace.setFocusable(false);
		keyrspace.setMaximumSize(new java.awt.Dimension(222, 40));
		keyrspace.setMinimumSize(new java.awt.Dimension(222, 40));
		keyrspace.setName("0x20"); // NOI18N
		keyrspace.setOpaque(true);
		keyrspace.setBorder(blackline);
		keyrspace.setPreferredSize(new java.awt.Dimension(222, 40));
		keyrspace.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		javax.swing.GroupLayout bottomctrlPanelLayout = new javax.swing.GroupLayout(bottomctrlPanel);
		bottomctrlPanel.setLayout(bottomctrlPanelLayout);
		bottomctrlPanelLayout
				.setHorizontalGroup(bottomctrlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(bottomctrlPanelLayout.createSequentialGroup()
								.addGap(4, 4, 4)
								.addComponent(keyspace, javax.swing.GroupLayout.PREFERRED_SIZE, 202,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keysym, javax.swing.GroupLayout.PREFERRED_SIZE, 84,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keyleftarrow, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addGroup(bottomctrlPanelLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(keyuparrow, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(keydownarrow, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.PREFERRED_SIZE, 40,	javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(4, 4, 4)
								.addComponent(keyrightarrow,javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keyoldfont, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keyrspace, javax.swing.GroupLayout.PREFERRED_SIZE, 222,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
								
		bottomctrlPanelLayout.setVerticalGroup(bottomctrlPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(bottomctrlPanelLayout.createSequentialGroup()
						.addGroup(bottomctrlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGap(4, 4, 4)
								.addComponent(keyspace, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keysym, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keyleftarrow, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(bottomctrlPanelLayout
										.createSequentialGroup()
											.addComponent(keyuparrow, javax.swing.GroupLayout.PREFERRED_SIZE, 19,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addGap(4, 4, 4)
											.addComponent(keydownarrow, javax.swing.GroupLayout.PREFERRED_SIZE, 19,
													javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(keyrightarrow, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keyoldfont, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(keyrspace, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE))));			
		
		
		keytab.setFont(font);
		keytab.setBackground(GlobalConstant.getDefaultKeyColor());
		keytab.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keytab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keytab.setText("தாவு");
		keytab.setFocusable(false);
		keytab.setMaximumSize(new java.awt.Dimension(60, 40));
		keytab.setMinimumSize(new java.awt.Dimension(60, 40));
		keytab.setName("0x09"); // NOI18N
		keytab.setOpaque(true);
		keytab.setBorder(blackline);
		keytab.setPreferredSize(new java.awt.Dimension(60, 40));
		keytab.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		

		keyemoji.setFont(font);
		keyemoji.setBackground(GlobalConstant.getDefaultKeyColor());
		keyemoji.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyemoji.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyemoji.setText("படவுரு");
		keyemoji.setFocusable(false);
		keyemoji.setMaximumSize(new java.awt.Dimension(70, 40));
		keyemoji.setMinimumSize(new java.awt.Dimension(70, 40));
		keyemoji.setName("0x14");
		keyemoji.setOpaque(true);
		keyemoji.setBorder(blackline);
		keyemoji.setPreferredSize(new java.awt.Dimension(70, 40));
		keyemoji.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if(alphabetPanel.isVisible() | symbolsPanel.isVisible() | classicPanel.isVisible())
				{			
					alphabetPanel.setVisible(false);
					emojiPanel.setVisible(true);
					symbolsPanel.setVisible(false);
					classicPanel.setVisible(false);
				}
				else
				{
					alphabetPanel.setVisible(true);
					emojiPanel.setVisible(false);
					symbolsPanel.setVisible(false);
					classicPanel.setVisible(false);

				}
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

		});


		keyenterl.setFont(font);
		keyenterl.setBackground(GlobalConstant.getDefaultKeyColor());
		keyenterl.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyenterl.setText("<html>&nbsp;நுழை ⏎ <br>&nbsp;</html>");
		keyenterl.setHorizontalAlignment(SwingConstants.CENTER);
		keyenterl.setVerticalAlignment(SwingConstants.CENTER);
		//keyenterl.setIcon(new ImageIcon(getClass().getResource("/icons/enterkey.png")));
		//keyenterl.setVerticalTextPosition(JLabel.CENTER);
		//keyenterl.setHorizontalTextPosition(JLabel.CENTER);
		keyenterl.setFocusable(false);
		keyenterl.setMaximumSize(new java.awt.Dimension(70, 100));
		keyenterl.setMinimumSize(new java.awt.Dimension(70, 100));
		keyenterl.setName("0x0A"); // NOI18N
		keyenterl.setOpaque(true);
		keyenterl.setBorder(blackline);
		keyenterl.setPreferredSize(new java.awt.Dimension(70, 100));
		keyenterl.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
        
		javax.swing.GroupLayout leftctrlPanelLayout = new javax.swing.GroupLayout(leftctrlPanel);
		leftctrlPanel.setLayout(leftctrlPanelLayout);
		leftctrlPanelLayout
				.setHorizontalGroup(leftctrlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(keytab, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keyemoji, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keyenterl,70, 70,	70));
								
		leftctrlPanelLayout.setVerticalGroup(leftctrlPanelLayout.createSequentialGroup()
								.addComponent(keytab, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keyemoji, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keyenterl, 100, 100,100)
								.addGap(4, 4, 4));	
		

		keyctrl1.setFont(font);
		keyctrl1.setBackground(GlobalConstant.getDefaultKeyColor());
		keyctrl1.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyctrl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyctrl1.setText("சேர்");
		keyctrl1.setFocusable(false);
		keyctrl1.setMaximumSize(new java.awt.Dimension(70, 40));
		keyctrl1.setMinimumSize(new java.awt.Dimension(70, 40));
		keyctrl1.setName("0x11"); // NOI18N
		keyctrl1.setOpaque(true);
		keyctrl1.setBorder(blackline);
		keyctrl1.setPreferredSize(new java.awt.Dimension(70, 40));
		keyctrl1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		keyshift.setFont(font);
		keyshift.setBackground(GlobalConstant.getDefaultKeyColor());
		keyshift.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyshift.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		keyshift.setText("மாற்று");
		keyshift.setFocusable(false);
		keyshift.setMaximumSize(new java.awt.Dimension(70, 40));
		keyshift.setMinimumSize(new java.awt.Dimension(70, 40));
		keyshift.setName("0x10"); // NOI18N
		keyshift.setOpaque(true);
		keyshift.setBorder(blackline);
		keyshift.setPreferredSize(new java.awt.Dimension(70, 40));
		keyshift.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		keyenter.setFont(font);
		keyenter.setBackground(GlobalConstant.getDefaultKeyColor());
		keyenter.setForeground(GlobalConstant.getDefaultKeyFgColor());
		keyenter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		//keyenter.setIcon(new ImageIcon(getClass().getResource("/icons/enterkey.png")));
		keyenter.setText("<html>&nbsp;நுழை ⏎ <br>&nbsp;</html>");
		keyenter.setFocusable(false);
		keyenter.setMaximumSize(new java.awt.Dimension(76, 40));
		keyenter.setMinimumSize(new java.awt.Dimension(76, 40));
		keyenter.setName("0x0A"); // NOI18N
		keyenter.setOpaque(true);
		keyenter.setBorder(blackline);
		keyenter.setPreferredSize(new java.awt.Dimension(76, 40));
		keyenter.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		javax.swing.GroupLayout rightctrlPanelLayout = new javax.swing.GroupLayout(rightctrlPanel);
		rightctrlPanel.setLayout(rightctrlPanelLayout);
		rightctrlPanelLayout
				.setHorizontalGroup(rightctrlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(keyctrl1, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keyshift, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keyenter,70, 70,	70));
								
		rightctrlPanelLayout
				.setVerticalGroup(rightctrlPanelLayout.createSequentialGroup()
								.addComponent(keyctrl1, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keyshift, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addComponent(keyenter, 100, 100,100));
								
	/*		
	 * Emoji Panel 
	 * 
	 */
		
	emo_smile.setFont(font);emo_smile.setBackground(GlobalConstant.getDefaultKeyColor());emo_smile.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_smile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_smile.setIcon(new ImageIcon(getClass().getResource("/icons/smile.png")));emo_smile.setFocusable(false);emo_smile.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_smile.setMinimumSize(GlobalConstant.getSymEmojiMaxSize());emo_smile.setName("0x263A");emo_smile.setOpaque(true);emo_smile.setBorder(blackline);emo_smile.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_smile.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🙂");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_sad.setFont(font);emo_sad.setBackground(GlobalConstant.getDefaultKeyColor());emo_sad.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_sad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_sad.setIcon(new ImageIcon(getClass().getResource("/icons/sad.png")));emo_sad.setFocusable(false);emo_sad.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_sad.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_sad.setName("0x2639");emo_sad.setOpaque(true);emo_sad.setBorder(blackline);emo_sad.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_sad.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("☹");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_teeth.setFont(font);emo_teeth.setBackground(GlobalConstant.getDefaultKeyColor());emo_teeth.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_teeth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_teeth.setIcon(new ImageIcon(getClass().getResource("/icons/teeth.png")));emo_teeth.setFocusable(false);emo_teeth.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_teeth.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_teeth.setName("0x1F626");emo_teeth.setOpaque(true);emo_teeth.setBorder(blackline);emo_teeth.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_teeth.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("😬");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_love.setFont(font);emo_love.setBackground(GlobalConstant.getDefaultKeyColor());emo_love.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_love.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_love.setIcon(new ImageIcon(getClass().getResource("/icons/loveeye.png")));emo_love.setFocusable(false);emo_love.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_love.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_love.setName("0x1F60D");emo_love.setOpaque(true);emo_love.setBorder(blackline);emo_love.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_love.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("😍");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_zen.setFont(font);emo_zen.setBackground(GlobalConstant.getDefaultKeyColor());emo_zen.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_zen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_zen.setIcon(new ImageIcon(getClass().getResource("/icons/zen.png")));emo_zen.setFocusable(false);emo_zen.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_zen.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_zen.setName("0x1F607");emo_zen.setOpaque(true);emo_zen.setBorder(blackline);emo_zen.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_zen.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("😇");
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_angry.setFont(font);emo_angry.setBackground(GlobalConstant.getDefaultKeyColor());emo_angry.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_angry.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_angry.setIcon(new ImageIcon(getClass().getResource("/icons/angry.png")));emo_angry.setFocusable(false);emo_angry.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_angry.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_angry.setName("0x1F621");emo_angry.setOpaque(true);emo_angry.setBorder(blackline);emo_angry.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_angry.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("😠");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	
	emo_tear.setFont(font);emo_tear.setBackground(GlobalConstant.getDefaultKeyColor());emo_tear.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_tear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_tear.setIcon(new ImageIcon(getClass().getResource("/icons/tear.png")));emo_tear.setFocusable(false);emo_tear.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_tear.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_tear.setName("0x1F613");emo_tear.setOpaque(true);emo_tear.setBorder(blackline);emo_tear.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_tear.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("😢");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_oneeye.setFont(font);emo_oneeye.setBackground(GlobalConstant.getDefaultKeyColor());emo_oneeye.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_oneeye.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_oneeye.setIcon(new ImageIcon(getClass().getResource("/icons/oneeye.png")));emo_oneeye.setFocusable(false);emo_oneeye.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_oneeye.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_oneeye.setName("0x1F61C");emo_oneeye.setOpaque(true);emo_oneeye.setBorder(blackline);emo_oneeye.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_oneeye.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("😜");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_tongue.setFont(font);emo_tongue.setBackground(GlobalConstant.getDefaultKeyColor());emo_tongue.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_tongue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_tongue.setIcon(new ImageIcon(getClass().getResource("/icons/tongue.png")));emo_tongue.setFocusable(false);emo_tongue.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_tongue.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_tongue.setName("0x1F61D");emo_tongue.setOpaque(true);emo_tongue.setBorder(blackline);emo_tongue.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_tongue.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("😝");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_sneeze.setFont(font);emo_sneeze.setBackground(GlobalConstant.getDefaultKeyColor());emo_sneeze.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_sneeze.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_sneeze.setIcon(new ImageIcon(getClass().getResource("/icons/sneeze.png")));emo_sneeze.setFocusable(false);emo_sneeze.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_sneeze.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_sneeze.setName("0x1F624");emo_sneeze.setOpaque(true);emo_sneeze.setBorder(blackline);emo_sneeze.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_sneeze.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("😤");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_cry.setFont(font);emo_cry.setBackground(GlobalConstant.getDefaultKeyColor());emo_cry.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_cry.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_cry.setIcon(new ImageIcon(getClass().getResource("/icons/cry.png")));emo_cry.setFocusable(false);emo_cry.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_cry.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_cry.setName("0x1F62D");emo_cry.setOpaque(true);emo_cry.setBorder(blackline);emo_cry.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_cry.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("😭");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_zip.setFont(font);emo_zip.setBackground(GlobalConstant.getDefaultKeyColor());emo_zip.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_zip.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_zip.setIcon(new ImageIcon(getClass().getResource("/icons/zip.png")));emo_zip.setFocusable(false);emo_zip.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_zip.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_zip.setName("0x1F910");emo_zip.setOpaque(true);emo_zip.setBorder(blackline);emo_zip.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_zip.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🤐");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_suspense.setFont(font);emo_suspense.setBackground(GlobalConstant.getDefaultKeyColor());emo_suspense.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_suspense.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_suspense.setIcon(new ImageIcon(getClass().getResource("/icons/suspense.png")));emo_suspense.setFocusable(false);emo_suspense.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_suspense.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_suspense.setName("0x1F914");emo_suspense.setOpaque(true);emo_suspense.setBorder(blackline);emo_suspense.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_suspense.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🤫");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_hands.setFont(font);emo_hands.setBackground(GlobalConstant.getDefaultKeyColor());emo_hands.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_hands.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_hands.setIcon(new ImageIcon(getClass().getResource("/icons/hands.png")));emo_hands.setFocusable(false);emo_hands.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_hands.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_hands.setName("0x1F64F");emo_hands.setOpaque(true);emo_hands.setBorder(blackline);emo_hands.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_hands.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🙏");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_pam.setFont(font);emo_pam.setBackground(GlobalConstant.getDefaultKeyColor());emo_pam.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_pam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_pam.setIcon(new ImageIcon(getClass().getResource("/icons/palm.png")));emo_pam.setFocusable(false);emo_pam.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_pam.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_pam.setName("0x1F91A");emo_pam.setOpaque(true);emo_pam.setBorder(blackline);emo_pam.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_pam.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("✋");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_clap.setFont(font);emo_clap.setBackground(GlobalConstant.getDefaultKeyColor());emo_clap.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_clap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_clap.setIcon(new ImageIcon(getClass().getResource("/icons/clap.png")));emo_clap.setFocusable(false);emo_clap.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_clap.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_clap.setName("0x1F44F");emo_clap.setOpaque(true);emo_clap.setBorder(blackline);emo_clap.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_clap.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("👏");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_victor.setFont(font);emo_victor.setBackground(GlobalConstant.getDefaultKeyColor());emo_victor.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_victor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_victor.setIcon(new ImageIcon(getClass().getResource("/icons/victor.png")));emo_victor.setFocusable(false);emo_victor.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_victor.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_victor.setName("0x1270C");emo_victor.setOpaque(true);emo_victor.setBorder(blackline);emo_victor.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_victor.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("✌️");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_super.setFont(font);emo_super.setBackground(GlobalConstant.getDefaultKeyColor());emo_super.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_super.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_super.setIcon(new ImageIcon(getClass().getResource("/icons/super.png")));emo_super.setFocusable(false);emo_super.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_super.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_super.setName("0x1F44C");emo_super.setOpaque(true);emo_super.setBorder(blackline);emo_super.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_super.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("👌");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_thumbs.setFont(font);emo_thumbs.setBackground(GlobalConstant.getDefaultKeyColor());emo_thumbs.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_thumbs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_thumbs.setIcon(new ImageIcon(getClass().getResource("/icons/thumbs.png")));emo_thumbs.setFocusable(false);emo_thumbs.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_thumbs.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_thumbs.setName("0x1F44D");emo_thumbs.setOpaque(true);emo_thumbs.setBorder(blackline);emo_thumbs.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_thumbs.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("👍");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_thumbsdown.setFont(font);emo_thumbsdown.setBackground(GlobalConstant.getDefaultKeyColor());emo_thumbsdown.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_thumbsdown.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_thumbsdown.setIcon(new ImageIcon(getClass().getResource("/icons/thumbsdown.png")));emo_thumbsdown.setFocusable(false);emo_thumbsdown.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_thumbsdown.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_thumbsdown.setName("0x1F44E");emo_thumbsdown.setOpaque(true);emo_thumbsdown.setBorder(blackline);emo_thumbsdown.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_thumbsdown.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("👎");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_punch.setFont(font);emo_punch.setBackground(GlobalConstant.getDefaultKeyColor());emo_punch.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_punch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_punch.setIcon(new ImageIcon(getClass().getResource("/icons/punch.png")));emo_punch.setFocusable(false);emo_punch.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_punch.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_punch.setName("0x1F44A");emo_punch.setOpaque(true);emo_punch.setBorder(blackline);emo_punch.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_punch.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("👊");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_indexfinger.setFont(font);emo_indexfinger.setBackground(GlobalConstant.getDefaultKeyColor());emo_indexfinger.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_indexfinger.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_indexfinger.setIcon(new ImageIcon(getClass().getResource("/icons/indexfinger.png")));emo_indexfinger.setFocusable(false);emo_indexfinger.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_indexfinger.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_indexfinger.setName("0x1F446");emo_indexfinger.setOpaque(true);emo_indexfinger.setBorder(blackline);emo_indexfinger.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_indexfinger.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("👆");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_downfinger.setFont(font);emo_downfinger.setBackground(GlobalConstant.getDefaultKeyColor());emo_downfinger.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_downfinger.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_downfinger.setIcon(new ImageIcon(getClass().getResource("/icons/downfinger.png")));emo_downfinger.setFocusable(false);emo_downfinger.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_downfinger.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_downfinger.setName("0x1F447");emo_downfinger.setOpaque(true);emo_downfinger.setBorder(blackline);emo_downfinger.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_downfinger.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("👇");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_folded.setFont(font);emo_folded.setBackground(GlobalConstant.getDefaultKeyColor());emo_folded.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_folded.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_folded.setIcon(new ImageIcon(getClass().getResource("/icons/folded.png")));emo_folded.setFocusable(false);emo_folded.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_folded.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_folded.setName("0x270A");emo_folded.setOpaque(true);emo_folded.setBorder(blackline);emo_folded.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_folded.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("✊");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_arm.setFont(font);emo_arm.setBackground(GlobalConstant.getDefaultKeyColor());emo_arm.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_arm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_arm.setIcon(new ImageIcon(getClass().getResource("/icons/arm.png")));emo_arm.setFocusable(false);emo_arm.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_arm.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_arm.setName("0x1F4AA");emo_arm.setOpaque(true);emo_arm.setBorder(blackline);emo_arm.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_arm.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("💪");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_middlefinger.setFont(font);emo_middlefinger.setBackground(GlobalConstant.getDefaultKeyColor());emo_middlefinger.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_middlefinger.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_middlefinger.setIcon(new ImageIcon(getClass().getResource("/icons/middlefinger.png")));emo_middlefinger.setFocusable(false);emo_middlefinger.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_middlefinger.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_middlefinger.setName("0x1F595");emo_middlefinger.setOpaque(true);emo_middlefinger.setBorder(blackline);emo_middlefinger.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_middlefinger.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🖕");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_whitelove.setFont(font);emo_whitelove.setBackground(GlobalConstant.getDefaultKeyColor());emo_whitelove.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_whitelove.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_whitelove.setIcon(new ImageIcon(getClass().getResource("/icons/whitelove.png")));emo_whitelove.setFocusable(false);emo_whitelove.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_whitelove.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_whitelove.setName("0x2764");emo_whitelove.setOpaque(true);emo_whitelove.setBorder(blackline);emo_whitelove.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_whitelove.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("❤️");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_blacklove.setFont(font);emo_blacklove.setBackground(GlobalConstant.getDefaultKeyColor());emo_blacklove.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_blacklove.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_blacklove.setIcon(new ImageIcon(getClass().getResource("/icons/blacklove.png")));emo_blacklove.setFocusable(false);emo_blacklove.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_blacklove.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_blacklove.setName("0x1F5a4");emo_blacklove.setOpaque(true);emo_blacklove.setBorder(blackline);emo_blacklove.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_blacklove.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🖤");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_heartbrk.setFont(font);emo_heartbrk.setBackground(GlobalConstant.getDefaultKeyColor());emo_heartbrk.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_heartbrk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_heartbrk.setIcon(new ImageIcon(getClass().getResource("/icons/heartbrk.png")));emo_heartbrk.setFocusable(false);emo_heartbrk.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_heartbrk.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_heartbrk.setName("0x1F494");emo_heartbrk.setOpaque(true);emo_heartbrk.setBorder(blackline);emo_heartbrk.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_heartbrk.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("💔");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_fries.setFont(font);emo_fries.setBackground(GlobalConstant.getDefaultKeyColor());emo_fries.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_fries.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_fries.setIcon(new ImageIcon(getClass().getResource("/icons/fries.png")));emo_fries.setFocusable(false);emo_fries.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_fries.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_fries.setName("0x1F389");emo_fries.setOpaque(true);emo_fries.setBorder(blackline);emo_fries.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_fries.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🎉");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_tick.setFont(font);emo_tick.setBackground(GlobalConstant.getDefaultKeyColor());emo_tick.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_tick.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_tick.setIcon(new ImageIcon(getClass().getResource("/icons/tick.png")));emo_tick.setFocusable(false);emo_tick.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_tick.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_tick.setName("0x2713");emo_tick.setOpaque(true);emo_tick.setBorder(blackline);emo_tick.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_tick.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("✔️");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_wrong.setFont(font);emo_wrong.setBackground(GlobalConstant.getDefaultKeyColor());emo_wrong.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_wrong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_wrong.setIcon(new ImageIcon(getClass().getResource("/icons/wrong.png")));emo_wrong.setFocusable(false);emo_wrong.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_wrong.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_wrong.setName("0x2717");emo_wrong.setOpaque(true);emo_wrong.setBorder(blackline);emo_wrong.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_wrong.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("❌");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_whiteflag.setFont(font);emo_whiteflag.setBackground(GlobalConstant.getDefaultKeyColor());emo_whiteflag.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_whiteflag.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_whiteflag.setIcon(new ImageIcon(getClass().getResource("/icons/flag.png")));emo_whiteflag.setFocusable(false);emo_whiteflag.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_whiteflag.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_whiteflag.setName("0x2690");emo_whiteflag.setOpaque(true);emo_whiteflag.setBorder(blackline);emo_whiteflag.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_whiteflag.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🏳");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_closeeye.setFont(font);emo_closeeye.setBackground(GlobalConstant.getDefaultKeyColor());emo_closeeye.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_closeeye.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_closeeye.setIcon(new ImageIcon(getClass().getResource("/icons/closeeye.png")));emo_closeeye.setFocusable(false);emo_closeeye.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_closeeye.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_closeeye.setName("0x1F648");emo_closeeye.setOpaque(true);emo_closeeye.setBorder(blackline);emo_closeeye.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_closeeye.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🙈");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_closeear.setFont(font);emo_closeear.setBackground(GlobalConstant.getDefaultKeyColor());emo_closeear.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_closeear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_closeear.setIcon(new ImageIcon(getClass().getResource("/icons/closeear.png")));emo_closeear.setFocusable(false);emo_closeear.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_closeear.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_closeear.setName("0x1F649");emo_closeear.setOpaque(true);emo_closeear.setBorder(blackline);emo_closeear.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_closeear.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🙉");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_closemouth.setFont(font);emo_closemouth.setBackground(GlobalConstant.getDefaultKeyColor());emo_closemouth.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_closemouth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_closemouth.setIcon(new ImageIcon(getClass().getResource("/icons/closemouth.png")));emo_closemouth.setFocusable(false);emo_closemouth.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_closemouth.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_closemouth.setName("0x1F64A");emo_closemouth.setOpaque(true);emo_closemouth.setBorder(blackline);emo_closemouth.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_closemouth.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🙊");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_girl.setFont(font);emo_girl.setBackground(GlobalConstant.getDefaultKeyColor());emo_girl.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_girl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_girl.setIcon(new ImageIcon(getClass().getResource("/icons/girl.png")));emo_girl.setFocusable(false);emo_girl.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_girl.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_girl.setName("0x1F467");emo_girl.setOpaque(true);emo_girl.setBorder(blackline);emo_girl.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_girl.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("👧");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_explode.setFont(font);emo_explode.setBackground(GlobalConstant.getDefaultKeyColor());emo_explode.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_explode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_explode.setIcon(new ImageIcon(getClass().getResource("/icons/explode.png")));emo_explode.setFocusable(false);emo_explode.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_explode.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_explode.setName("0x2728");emo_explode.setOpaque(true);emo_explode.setBorder(blackline);emo_explode.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_explode.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("💥");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_petals.setFont(font);emo_petals.setBackground(GlobalConstant.getDefaultKeyColor());emo_petals.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_petals.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_petals.setIcon(new ImageIcon(getClass().getResource("/icons/petals.png")));emo_petals.setFocusable(false);emo_petals.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_petals.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_petals.setName("0x1F4A6");emo_petals.setOpaque(true);emo_petals.setBorder(blackline);emo_petals.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_petals.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("💦");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_headphone.setFont(font);emo_headphone.setBackground(GlobalConstant.getDefaultKeyColor());emo_headphone.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_headphone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_headphone.setIcon(new ImageIcon(getClass().getResource("/icons/headphone.png")));emo_headphone.setFocusable(false);emo_headphone.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_headphone.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_headphone.setName("0x1F3A7");emo_headphone.setOpaque(true);emo_headphone.setBorder(blackline);emo_headphone.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_headphone.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🎧");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_torch.setFont(font);emo_torch.setBackground(GlobalConstant.getDefaultKeyColor());emo_torch.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_torch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_torch.setIcon(new ImageIcon(getClass().getResource("/icons/torch.png")));emo_torch.setFocusable(false);emo_torch.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_torch.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_torch.setName("0x1F526");emo_torch.setOpaque(true);emo_torch.setBorder(blackline);emo_torch.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_torch.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🎤");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_tone.setFont(font);emo_tone.setBackground(GlobalConstant.getDefaultKeyColor());emo_tone.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_tone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_tone.setIcon(new ImageIcon(getClass().getResource("/icons/tone.png")));emo_tone.setFocusable(false);emo_tone.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_tone.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_tone.setName("0x1F3B5");emo_tone.setOpaque(true);emo_tone.setBorder(blackline);emo_tone.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_tone.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🎵");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_watch.setFont(font);emo_watch.setBackground(GlobalConstant.getDefaultKeyColor());emo_watch.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_watch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_watch.setIcon(new ImageIcon(getClass().getResource("/icons/watch.png")));emo_watch.setFocusable(false);emo_watch.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_watch.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_watch.setName("0x231A");emo_watch.setOpaque(true);emo_watch.setBorder(blackline);emo_watch.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_watch.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("⌚");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_ghost.setFont(font);emo_ghost.setBackground(GlobalConstant.getDefaultKeyColor());emo_ghost.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_ghost.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_ghost.setIcon(new ImageIcon(getClass().getResource("/icons/ghost.png")));emo_ghost.setFocusable(false);emo_ghost.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_ghost.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_ghost.setName("0x1F47B");emo_ghost.setOpaque(true);emo_ghost.setBorder(blackline);emo_ghost.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_ghost.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("👻");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_nocell.setFont(font);emo_nocell.setBackground(GlobalConstant.getDefaultKeyColor());emo_nocell.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_nocell.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_nocell.setIcon(new ImageIcon(getClass().getResource("/icons/nocell.png")));emo_nocell.setFocusable(false);emo_nocell.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_nocell.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_nocell.setName("0x1F4F5");emo_nocell.setOpaque(true);emo_nocell.setBorder(blackline);emo_nocell.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_nocell.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("📵");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_scissor.setFont(font);emo_scissor.setBackground(GlobalConstant.getDefaultKeyColor());emo_scissor.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_scissor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_scissor.setIcon(new ImageIcon(getClass().getResource("/icons/scissor.png")));emo_scissor.setFocusable(false);emo_scissor.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_scissor.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_scissor.setName("0x2702");emo_scissor.setOpaque(true);emo_scissor.setBorder(blackline);emo_scissor.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_scissor.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("✂");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_boy.setFont(font);emo_boy.setBackground(GlobalConstant.getDefaultKeyColor());emo_boy.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_boy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_boy.setIcon(new ImageIcon(getClass().getResource("/icons/boy.png")));emo_boy.setFocusable(false);emo_boy.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_boy.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_boy.setName("0x1F466");emo_boy.setOpaque(true);emo_boy.setBorder(blackline);emo_boy.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_boy.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("👦");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_umber.setFont(font);emo_umber.setBackground(GlobalConstant.getDefaultKeyColor());emo_umber.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_umber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_umber.setIcon(new ImageIcon(getClass().getResource("/icons/umber.png")));emo_umber.setFocusable(false);emo_umber.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_umber.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_umber.setName("0x2602");emo_umber.setOpaque(true);emo_umber.setBorder(blackline);emo_umber.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_umber.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("☂");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_car.setFont(font);emo_car.setBackground(GlobalConstant.getDefaultKeyColor());emo_car.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_car.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_car.setIcon(new ImageIcon(getClass().getResource("/icons/car.png")));emo_car.setFocusable(false);emo_car.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_car.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_car.setName("0x1F697");emo_car.setOpaque(true);emo_car.setBorder(blackline);emo_car.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_car.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🚗");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_flight.setFont(font);emo_flight.setBackground(GlobalConstant.getDefaultKeyColor());emo_flight.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_flight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_flight.setIcon(new ImageIcon(getClass().getResource("/icons/flight.png")));emo_flight.setFocusable(false);emo_flight.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_flight.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_flight.setName("0x2708");emo_flight.setOpaque(true);emo_flight.setBorder(blackline);emo_flight.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_flight.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("✈");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_train.setFont(font);emo_train.setBackground(GlobalConstant.getDefaultKeyColor());emo_train.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_train.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_train.setIcon(new ImageIcon(getClass().getResource("/icons/train.png")));emo_train.setFocusable(false);emo_train.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_train.setMinimumSize(GlobalConstant.getSymEmojiMinSize());emo_train.setName("0x1F686");emo_train.setOpaque(true);emo_train.setBorder(blackline);emo_train.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_train.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🚊");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	emo_bus.setFont(font);emo_bus.setBackground(GlobalConstant.getDefaultKeyColor());emo_bus.setForeground(GlobalConstant.getDefaultKeyFgColor());emo_bus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);emo_bus.setIcon(new ImageIcon(getClass().getResource("/icons/bus.png")));emo_bus.setFocusable(false);emo_bus.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());emo_bus.setMinimumSize(GlobalConstant.getSymEmojiMaxSize());emo_bus.setName("0x1F68c");emo_bus.setOpaque(true);emo_bus.setBorder(blackline);emo_bus.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	emo_bus.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("🚌");
			
	        pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			emoMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			emoMouseExited(evt);
		}
	});
	
		
	javax.swing.GroupLayout emojiPanelLayout = new javax.swing.GroupLayout(emojiPanel);
	emojiPanel.setLayout(emojiPanelLayout);
	emojiPanelLayout.setHorizontalGroup(emojiPanelLayout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(emojiPanelLayout.createSequentialGroup()
					.addGroup(emojiPanelLayout.createParallelGroup(
									javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(emojiPanelLayout.createSequentialGroup()
									.addComponent(emo_smile, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_sad, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_teeth, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_love, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_zen, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_angry, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_tear, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_oneeye, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_tongue, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_sneeze, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_cry, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_zip, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_suspense, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4))
							.addGroup(emojiPanelLayout.createSequentialGroup()
									.addComponent(emo_hands, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_pam, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_clap, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_victor, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_super, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_thumbs, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_thumbsdown, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_punch, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_indexfinger, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_downfinger, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_folded, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_arm, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_middlefinger, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									)))
							.addGroup(javax.swing.GroupLayout.Alignment.LEADING, emojiPanelLayout
											.createSequentialGroup()
											.addGroup(emojiPanelLayout
													.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
									.addGroup(emojiPanelLayout.createSequentialGroup()
									.addComponent(emo_whitelove, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_blacklove, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_heartbrk, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_fries, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_tick, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_wrong, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_whiteflag, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_closeeye, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_closeear, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_closemouth, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_girl, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_explode, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4)
									.addComponent(emo_boy, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(4, 4, 4))))
									.addGroup(emojiPanelLayout.createSequentialGroup()
											.addComponent(emo_petals, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addGap(4, 4, 4)
											.addComponent(emo_headphone, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addGap(4, 4, 4)
											.addComponent(emo_torch, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addGap(4, 4, 4)
											.addComponent(emo_tone, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addGap(4, 4, 4)
											.addComponent(emo_watch, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addGap(4, 4, 4)
											.addComponent(emo_ghost, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addGap(4, 4, 4)
											.addComponent(emo_nocell, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addGap(4, 4, 4)
											.addComponent(emo_scissor, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addGap(4, 4, 4)
											.addComponent(emo_umber, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addGap(4, 4, 4)
											.addComponent(emo_car, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addGap(4, 4, 4)
											.addComponent(emo_flight, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addGap(4, 4, 4)
											.addComponent(emo_train, javax.swing.GroupLayout.PREFERRED_SIZE,50,
													javax.swing.GroupLayout.PREFERRED_SIZE)
											.addGap(4, 4, 4)
											.addComponent(emo_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
													javax.swing.GroupLayout.PREFERRED_SIZE)));
	emojiPanelLayout.setVerticalGroup(emojiPanelLayout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(emojiPanelLayout.createSequentialGroup().addGroup(emojiPanelLayout
					.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(emojiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
							.addComponent(emo_smile, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
									javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(emo_sad, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
									javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(emo_teeth, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
									javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(emo_love, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
									javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(emo_zen, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
									javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(emo_angry, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
									javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(emo_tear, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
									javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(emo_oneeye, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
									javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(emo_tongue, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
									javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(emo_sneeze, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
									javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(emo_cry, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
									javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(emo_zip, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
									javax.swing.GroupLayout.PREFERRED_SIZE)
							.addComponent(emo_suspense, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
									javax.swing.GroupLayout.PREFERRED_SIZE)))
					.addGap(4, 4, 4)
					.addGroup(emojiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(emojiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
									.addComponent(emo_hands, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_pam, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_clap, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_victor, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_super, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_thumbs, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_thumbsdown, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_punch, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_indexfinger, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_downfinger, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_folded, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_arm, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_middlefinger, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)))
					.addGap(4, 4, 4)
					.addGroup(emojiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(emojiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
									.addComponent(emo_whitelove, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_blacklove, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_heartbrk, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_fries, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_tick, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_wrong, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_whiteflag, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_closeeye, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_closeear, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_closemouth, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_girl, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_explode, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_boy, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)))
					.addGap(4, 4, 4)
					.addGroup(emojiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(emojiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
									.addComponent(emo_petals, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_headphone, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_torch, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_tone, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_watch, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_ghost, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_nocell, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_scissor, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_umber, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_car, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_flight, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_train, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE)
									.addComponent(emo_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
											javax.swing.GroupLayout.PREFERRED_SIZE))
							.addGap(4, 4, 4))));
		 
	/*
	 * Symbol Panel 
	 */
	
	sym_exclamation.setFont(font);sym_exclamation.setBackground(GlobalConstant.getDefaultKeyColor());sym_exclamation.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_exclamation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_exclamation.setText("!");sym_exclamation.setFocusable(false);sym_exclamation.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_exclamation.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_exclamation.setName("0x0021");sym_exclamation.setOpaque(true);sym_exclamation.setBorder(blackline);sym_exclamation.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_exclamation.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			keyClicked(evt);
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_minus.setFont(font);sym_minus.setBackground(GlobalConstant.getDefaultKeyColor());sym_minus.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_minus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_minus.setText("-");sym_minus.setFocusable(false);sym_minus.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_minus.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_minus.setName("0x002D");sym_minus.setOpaque(true);sym_minus.setBorder(blackline);sym_minus.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_minus.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			keyClicked(evt);
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_plus.setFont(font);sym_plus.setBackground(GlobalConstant.getDefaultKeyColor());sym_plus.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_plus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_plus.setText("+");sym_plus.setFocusable(false);sym_plus.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_plus.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_plus.setName("0x002B");sym_plus.setOpaque(true);sym_plus.setBorder(blackline);sym_plus.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_plus.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			keyClicked(evt);
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	
	sym_multi.setFont(notoFont);sym_multi.setBackground(GlobalConstant.getDefaultKeyColor());sym_multi.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_multi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_multi.setText("×");sym_multi.setFocusable(false);sym_multi.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_multi.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_multi.setName("0x02DF");sym_multi.setOpaque(true);sym_multi.setBorder(blackline);sym_multi.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_multi.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("×");
			pressAndReleaseKey();
			//keyClicked(evt);
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	
	sym_div.setFont(font);sym_div.setBackground(GlobalConstant.getDefaultKeyColor());sym_div.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_div.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_div.setText("÷");sym_div.setFocusable(false);sym_div.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_div.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_div.setName("0x00F7");sym_div.setOpaque(true);sym_div.setBorder(blackline);sym_div.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_div.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("÷");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	
	sym_eq.setFont(font);sym_eq.setBackground(GlobalConstant.getDefaultKeyColor());sym_eq.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_eq.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_eq.setText("=");sym_eq.setFocusable(false);sym_eq.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_eq.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_eq.setName("0x003D");sym_eq.setOpaque(true);sym_eq.setBorder(blackline);sym_eq.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_eq.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			keyClicked(evt);
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	
	sym_ne.setFont(font);sym_ne.setBackground(GlobalConstant.getDefaultKeyColor());sym_ne.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_ne.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_ne.setText("≠");sym_ne.setFocusable(false);sym_ne.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_ne.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_ne.setName("0x2260");sym_ne.setOpaque(true);sym_ne.setBorder(blackline);sym_ne.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_ne.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("≠");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	
	sym_plusmin.setFont(font);sym_plusmin.setBackground(GlobalConstant.getDefaultKeyColor());sym_plusmin.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_plusmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_plusmin.setText("±");sym_plusmin.setFocusable(false);sym_plusmin.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_plusmin.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_plusmin.setName("0x2213");sym_plusmin.setOpaque(true);sym_plusmin.setBorder(blackline);sym_plusmin.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_plusmin.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("±");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	
	sym_mod.setFont(font);sym_mod.setBackground(GlobalConstant.getDefaultKeyColor());sym_mod.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_mod.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_mod.setText("%");sym_mod.setFocusable(false);sym_mod.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_mod.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_mod.setName("0x0025");sym_mod.setOpaque(true);sym_mod.setBorder(blackline);sym_mod.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_mod.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			JLabel key = (JLabel) evt.getSource(); // Source clicked key.
			int keycode = Integer.decode(key.getName()); // Virtual key code associated with the key
			Keyboard.pressUnicode(keycode);
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	
	sym_sqrt.setFont(font);sym_sqrt.setBackground(GlobalConstant.getDefaultKeyColor());sym_sqrt.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_sqrt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_sqrt.setText("√");sym_sqrt.setFocusable(false);sym_sqrt.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_sqrt.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_sqrt.setName("0x11FED");sym_sqrt.setOpaque(true);sym_sqrt.setBorder(blackline);sym_sqrt.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_sqrt.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("√");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_rupee.setFont(font);sym_rupee.setBackground(GlobalConstant.getDefaultKeyColor());sym_rupee.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_rupee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_rupee.setText("₹");sym_rupee.setFocusable(false);sym_rupee.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_rupee.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_rupee.setName("0x20B9");sym_rupee.setOpaque(true);sym_rupee.setBorder(blackline);sym_rupee.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_rupee.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("₹");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_le.setFont(font);sym_le.setBackground(GlobalConstant.getDefaultKeyColor());sym_le.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_le.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_le.setText("≤");sym_le.setFocusable(false);sym_le.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_le.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_le.setName("0x2264");sym_le.setOpaque(true);sym_le.setBorder(blackline);sym_le.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_le.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("≤");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_ge.setFont(font);sym_ge.setBackground(GlobalConstant.getDefaultKeyColor());sym_ge.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_ge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_ge.setText("≥");sym_ge.setFocusable(false);sym_ge.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_ge.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_ge.setName("0x2265");sym_ge.setOpaque(true);sym_ge.setBorder(blackline);sym_ge.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_ge.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("≥");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_gr.setFont(font);sym_gr.setBackground(GlobalConstant.getDefaultKeyColor());sym_gr.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_gr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_gr.setText(">");sym_gr.setFocusable(false);sym_gr.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_gr.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_gr.setName("0x003E");sym_gr.setOpaque(true);sym_gr.setBorder(blackline);sym_gr.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_gr.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			keyClicked(evt);
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_lt.setFont(font);sym_lt.setBackground(GlobalConstant.getDefaultKeyColor());sym_lt.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_lt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_lt.setText("<");sym_lt.setFocusable(false);sym_lt.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_lt.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_lt.setName("0x003C");sym_lt.setOpaque(true);sym_lt.setBorder(blackline);sym_lt.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_lt.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			keyClicked(evt);
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_deg.setFont(font);sym_deg.setBackground(GlobalConstant.getDefaultKeyColor());sym_deg.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_deg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_deg.setText("°");sym_deg.setFocusable(false);sym_deg.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_deg.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_deg.setName("0x00B0");sym_deg.setOpaque(true);sym_deg.setBorder(blackline);sym_deg.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_deg.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("°");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_half.setFont(font);sym_half.setBackground(GlobalConstant.getDefaultKeyColor());sym_half.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_half.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_half.setText("½");sym_half.setFocusable(false);sym_half.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_half.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_half.setName("0x00BD");sym_half.setOpaque(true);sym_half.setBorder(blackline);sym_half.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_half.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("½");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_qtr.setFont(font);sym_qtr.setBackground(GlobalConstant.getDefaultKeyColor());sym_qtr.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_qtr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_qtr.setText("¼");sym_qtr.setFocusable(false);sym_qtr.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_qtr.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_qtr.setName("0x00BC");sym_qtr.setOpaque(true);sym_qtr.setBorder(blackline);sym_qtr.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_qtr.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("¼");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_sqr.setFont(font);sym_sqr.setBackground(GlobalConstant.getDefaultKeyColor());sym_sqr.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_sqr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_sqr.setText("x²");sym_sqr.setFocusable(false);sym_sqr.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_sqr.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_sqr.setName("0x00B2");sym_sqr.setOpaque(true);sym_sqr.setBorder(blackline);sym_sqr.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_sqr.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("²");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_cube.setFont(font);sym_cube.setBackground(GlobalConstant.getDefaultKeyColor());sym_cube.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_cube.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_cube.setText("x³");sym_cube.setFocusable(false);sym_cube.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_cube.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_cube.setName("0x00B3");sym_cube.setOpaque(true);sym_cube.setBorder(blackline);sym_cube.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_cube.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("³");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_lcurly.setFont(font);sym_lcurly.setBackground(GlobalConstant.getDefaultKeyColor());sym_lcurly.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_lcurly.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_lcurly.setText("{");sym_lcurly.setFocusable(false);sym_lcurly.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_lcurly.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_lcurly.setName("0x007B");sym_lcurly.setOpaque(true);sym_lcurly.setBorder(blackline);sym_lcurly.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_lcurly.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			keyClicked(evt);
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_rcurly.setFont(font);sym_rcurly.setBackground(GlobalConstant.getDefaultKeyColor());sym_rcurly.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_rcurly.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_rcurly.setText("}");sym_rcurly.setFocusable(false);sym_rcurly.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_rcurly.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_rcurly.setName("0x007D");sym_rcurly.setOpaque(true);sym_rcurly.setBorder(blackline);sym_rcurly.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_rcurly.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			keyClicked(evt);
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_lsqr.setFont(font);sym_lsqr.setBackground(GlobalConstant.getDefaultKeyColor());sym_lsqr.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_lsqr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_lsqr.setText("[");sym_lsqr.setFocusable(false);sym_lsqr.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_lsqr.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_lsqr.setName("0x005B");sym_lsqr.setOpaque(true);sym_lsqr.setBorder(blackline);sym_lsqr.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_lsqr.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			keyClicked(evt);
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_rsqr.setFont(font);sym_rsqr.setBackground(GlobalConstant.getDefaultKeyColor());sym_rsqr.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_rsqr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_rsqr.setText("]");sym_rsqr.setFocusable(false);sym_rsqr.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_rsqr.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_rsqr.setName("0x005D");sym_rsqr.setOpaque(true);sym_rsqr.setBorder(blackline);sym_rsqr.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_rsqr.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			keyClicked(evt);
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_pipe.setFont(font);sym_pipe.setBackground(GlobalConstant.getDefaultKeyColor());sym_pipe.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_pipe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_pipe.setText("|");sym_pipe.setFocusable(false);sym_pipe.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_pipe.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_pipe.setName("0x007C");sym_pipe.setOpaque(true);sym_pipe.setBorder(blackline);sym_pipe.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_pipe.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			keyClicked(evt);
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_bslash.setFont(font);sym_bslash.setBackground(GlobalConstant.getDefaultKeyColor());sym_bslash.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_bslash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_bslash.setText("\\");sym_bslash.setFocusable(false);sym_bslash.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_bslash.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_bslash.setName("0x005C");sym_bslash.setOpaque(true);sym_bslash.setBorder(blackline);sym_bslash.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_bslash.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			keyClicked(evt);
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_semicolon.setFont(font);sym_semicolon.setBackground(GlobalConstant.getDefaultKeyColor());sym_semicolon.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_semicolon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_semicolon.setText(";");sym_semicolon.setFocusable(false);sym_semicolon.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_semicolon.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_semicolon.setName("0x003B");sym_semicolon.setOpaque(true);sym_semicolon.setBorder(blackline);sym_semicolon.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_semicolon.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			keyClicked(evt);
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_quote.setFont(font);sym_quote.setBackground(GlobalConstant.getDefaultKeyColor());sym_quote.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_quote.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_quote.setText("\"");sym_quote.setFocusable(false);sym_quote.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_quote.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_quote.setName("0x0022");sym_quote.setOpaque(true);sym_quote.setBorder(blackline);sym_quote.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_quote.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			keyClicked(evt);
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
	sym_euro.setFont(font);sym_euro.setBackground(GlobalConstant.getDefaultKeyColor());sym_euro.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_euro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_euro.setText("€");sym_euro.setFocusable(false);sym_euro.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_euro.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_euro.setName("0x20AC");sym_euro.setOpaque(true);sym_euro.setBorder(blackline);sym_euro.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
	sym_euro.addMouseListener(new java.awt.event.MouseAdapter() { 
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			Keyboard.copy("€");
			pressAndReleaseKey();
		}

		public void mouseEntered(java.awt.event.MouseEvent evt) {
			keyMouseEntered(evt);
		}

		public void mouseExited(java.awt.event.MouseEvent evt) {
			keyMouseExited(evt);
		}

		public void mousePressed(java.awt.event.MouseEvent evt) {
			keyPressed(evt);
		}

		public void mouseReleased(java.awt.event.MouseEvent evt) {
			keyReleased(evt);
		}
	});
		sym_pound.setFont(font);sym_pound.setBackground(GlobalConstant.getDefaultKeyColor());sym_pound.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_pound.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_pound.setText("£");sym_pound.setFocusable(false);sym_pound.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_pound.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_pound.setName("0x00A3");sym_pound.setOpaque(true);sym_pound.setBorder(blackline);sym_pound.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_pound.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("£");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_dollar.setFont(font);sym_dollar.setBackground(GlobalConstant.getDefaultKeyColor());sym_dollar.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_dollar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		sym_dollar.setText("$");sym_dollar.setFocusable(false);sym_dollar.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_dollar.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_dollar.setName("0x0024");sym_dollar.setOpaque(true);sym_dollar.setBorder(blackline);sym_dollar.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_dollar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		//sym_dblarr.setFont(font);
		sym_dblarr.setBackground(GlobalConstant.getDefaultKeyColor());
		sym_dblarr.setForeground(GlobalConstant.getDefaultKeyFgColor());
		sym_dblarr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		//sym_dblarr.setText("⇄");
		sym_dblarr.setIcon(new ImageIcon(getClass().getResource("/icons/sym_dblarr.png")));
		sym_dblarr.setFocusable(false);sym_dblarr.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());
		sym_dblarr.setMinimumSize(GlobalConstant.getSymEmojiMinSize());
		sym_dblarr.setName("0x21C6");
		sym_dblarr.setOpaque(true);
		sym_dblarr.setBorder(blackline);
		sym_dblarr.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_dblarr.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("⇄");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_leftarr.setFont(font);sym_leftarr.setBackground(GlobalConstant.getDefaultKeyColor());sym_leftarr.setForeground(GlobalConstant.getDefaultKeyFgColor());
		sym_leftarr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		//sym_leftarr.setText("←");
		sym_leftarr.setIcon(new ImageIcon(getClass().getResource("/icons/sym_leftarrow.png")));
		
		sym_leftarr.setFocusable(false);sym_leftarr.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());
		sym_leftarr.setMinimumSize(GlobalConstant.getSymEmojiMinSize());
		sym_leftarr.setName("0x2190");sym_leftarr.setOpaque(true);
		sym_leftarr.setBorder(blackline);
		sym_leftarr.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_leftarr.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("←");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_rightarr.setFont(font);sym_rightarr.setBackground(GlobalConstant.getDefaultKeyColor());sym_rightarr.setForeground(GlobalConstant.getDefaultKeyFgColor());
		sym_rightarr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		//sym_rightarr.setText("→");
		sym_rightarr.setIcon(new ImageIcon(getClass().getResource("/icons/sym_rightarrow.png")));
		sym_rightarr.setFocusable(false);sym_rightarr.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_rightarr.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_rightarr.setName("0x2192");sym_rightarr.setOpaque(true);sym_rightarr.setBorder(blackline);sym_rightarr.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_rightarr.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("→");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_downarr.setFont(font);sym_downarr.setBackground(GlobalConstant.getDefaultKeyColor());sym_downarr.setForeground(GlobalConstant.getDefaultKeyFgColor());
		sym_downarr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		//sym_downarr.setText("⇩");
		sym_downarr.setIcon(new ImageIcon(getClass().getResource("/icons/sym_downarrow.png")));
		sym_downarr.setFocusable(false);sym_downarr.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_downarr.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_downarr.setName("0x2193");sym_downarr.setOpaque(true);sym_downarr.setBorder(blackline);sym_downarr.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_downarr.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("↓");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_uparr.setFont(font);sym_uparr.setBackground(GlobalConstant.getDefaultKeyColor());sym_uparr.setForeground(GlobalConstant.getDefaultKeyFgColor());
		sym_uparr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		//sym_uparr.setText("↑");
		sym_uparr.setIcon(new ImageIcon(getClass().getResource("/icons/sym_uparrow.png")));
		sym_uparr.setFocusable(false);sym_uparr.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_uparr.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_uparr.setName("0x2191");sym_uparr.setOpaque(true);sym_uparr.setBorder(blackline);sym_uparr.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_uparr.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("↑");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_eqarr.setFont(font);sym_eqarr.setBackground(GlobalConstant.getDefaultKeyColor());sym_eqarr.setForeground(GlobalConstant.getDefaultKeyFgColor());
		sym_eqarr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		//sym_eqarr.setText("↔");
		sym_eqarr.setIcon(new ImageIcon(getClass().getResource("/icons/sym_eqarrow.png")));
		sym_eqarr.setFocusable(false);sym_eqarr.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_eqarr.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_eqarr.setName("0x2194");sym_eqarr.setOpaque(true);sym_eqarr.setBorder(blackline);sym_eqarr.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_eqarr.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("↔");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_tilde.setFont(font);sym_tilde.setBackground(GlobalConstant.getDefaultKeyColor());sym_tilde.setForeground(GlobalConstant.getDefaultKeyFgColor());
		sym_tilde.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		sym_tilde.setText("~");
		sym_tilde.setHorizontalTextPosition(JLabel.CENTER);
		sym_tilde.setFocusable(false);
		sym_tilde.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());
		sym_tilde.setMinimumSize(GlobalConstant.getSymEmojiMinSize());
		sym_tilde.setName("0x007E");
		sym_tilde.setOpaque(true);
		sym_tilde.setBorder(blackline);
		sym_tilde.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_tilde.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_caret.setFont(font);sym_caret.setBackground(GlobalConstant.getDefaultKeyColor());sym_caret.setForeground(GlobalConstant.getDefaultKeyFgColor());
		sym_caret.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		sym_caret.setText("^");
		sym_caret.setFocusable(false);sym_caret.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_caret.setMinimumSize(GlobalConstant.getSymEmojiMinSize());
		sym_caret.setName("0x2038");sym_caret.setOpaque(true);sym_caret.setBorder(blackline);sym_caret.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_caret.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("^");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_asterick.setFont(font);sym_asterick.setBackground(GlobalConstant.getDefaultKeyColor());sym_asterick.setForeground(GlobalConstant.getDefaultKeyFgColor());
		sym_asterick.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		sym_asterick.setText("*");
		sym_asterick.setFocusable(false);sym_asterick.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_asterick.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_asterick.setName("002A");sym_asterick.setOpaque(true);sym_asterick.setBorder(blackline);sym_asterick.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_asterick.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_approx.setFont(font);sym_approx.setBackground(GlobalConstant.getDefaultKeyColor());sym_approx.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_approx.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_approx.setText("≈");sym_approx.setFocusable(false);sym_approx.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_approx.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_approx.setName("0x2248");sym_approx.setOpaque(true);sym_approx.setBorder(blackline);sym_approx.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_approx.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("≈");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_at.setFont(font);
		sym_at.setBackground(GlobalConstant.getDefaultKeyColor());
		sym_at.setForeground(GlobalConstant.getDefaultKeyFgColor());
		sym_at.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		sym_at.setText("@");
		sym_at.setFocusable(false);
		sym_at.setMaximumSize(new java.awt.Dimension(40, 40));
		sym_at.setMinimumSize(new java.awt.Dimension(40, 40));
		sym_at.setName("0x1F60D"); // NOI18N
		sym_at.setOpaque(true);
		sym_at.setBorder(blackline);
		sym_at.setPreferredSize(new java.awt.Dimension(40, 40));
		sym_at.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("@");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

		});
		sym_hash.setFont(font);sym_hash.setBackground(GlobalConstant.getDefaultKeyColor());sym_hash.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_hash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_hash.setText("#");sym_hash.setFocusable(false);sym_hash.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_hash.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_hash.setName("0x0023");sym_hash.setOpaque(true);sym_hash.setBorder(blackline);sym_hash.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_hash.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_underscr.setFont(font);sym_underscr.setBackground(GlobalConstant.getDefaultKeyColor());sym_underscr.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_underscr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_underscr.setText("_");sym_underscr.setFocusable(false);sym_underscr.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_underscr.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_underscr.setName("0x005F");sym_underscr.setOpaque(true);sym_underscr.setBorder(blackline);sym_underscr.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_underscr.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_ampersand.setFont(font);sym_ampersand.setBackground(GlobalConstant.getDefaultKeyColor());sym_ampersand.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_ampersand.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_ampersand.setText("&");sym_ampersand.setFocusable(false);sym_ampersand.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_ampersand.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_ampersand.setName("0x0026");sym_ampersand.setOpaque(true);sym_ampersand.setBorder(blackline);sym_ampersand.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_ampersand.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("&");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_lcir.setFont(font);sym_lcir.setBackground(GlobalConstant.getDefaultKeyColor());sym_lcir.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_lcir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_lcir.setText("(");sym_lcir.setFocusable(false);sym_lcir.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_lcir.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_lcir.setName("0x0028");sym_lcir.setOpaque(true);sym_lcir.setBorder(blackline);sym_lcir.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_lcir.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("(");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_rcir.setFont(font);sym_rcir.setBackground(GlobalConstant.getDefaultKeyColor());sym_rcir.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_rcir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_rcir.setText(")");sym_rcir.setFocusable(false);sym_rcir.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_rcir.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_rcir.setName("0x0029");sym_rcir.setOpaque(true);sym_rcir.setBorder(blackline);sym_rcir.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_rcir.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_fslash.setFont(font);sym_fslash.setBackground(GlobalConstant.getDefaultKeyColor());sym_fslash.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_fslash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_fslash.setText("/");sym_fslash.setFocusable(false);sym_fslash.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_fslash.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_fslash.setName("0x002F");sym_fslash.setOpaque(true);sym_fslash.setBorder(blackline);sym_fslash.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_fslash.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_asterick.setFont(font);sym_asterick.setBackground(GlobalConstant.getDefaultKeyColor());sym_asterick.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_asterick.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_asterick.setText("*");sym_asterick.setFocusable(false);sym_asterick.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_asterick.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_asterick.setName("0x002A");sym_asterick.setOpaque(true);sym_asterick.setBorder(blackline);sym_asterick.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_asterick.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_singlequote.setFont(font);sym_singlequote.setBackground(GlobalConstant.getDefaultKeyColor());sym_singlequote.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_singlequote.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_singlequote.setText("'");sym_singlequote.setFocusable(false);sym_singlequote.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_singlequote.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_singlequote.setName("0x0027");sym_singlequote.setOpaque(true);sym_singlequote.setBorder(blackline);sym_singlequote.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_singlequote.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("'");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_colon.setFont(font);sym_colon.setBackground(GlobalConstant.getDefaultKeyColor());sym_colon.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_colon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_colon.setText(":");sym_colon.setFocusable(false);sym_colon.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_colon.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_colon.setName("0x003A");sym_colon.setOpaque(true);sym_colon.setBorder(blackline);sym_colon.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_colon.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_question.setFont(font);sym_question.setBackground(GlobalConstant.getDefaultKeyColor());sym_question.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_question.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_question.setText("?");sym_question.setFocusable(false);sym_question.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_question.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_question.setName("0x003F");sym_question.setOpaque(true);sym_question.setBorder(blackline);sym_question.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_question.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_comma.setFont(font);sym_comma.setBackground(GlobalConstant.getDefaultKeyColor());sym_comma.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_comma.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_comma.setText(",");sym_comma.setFocusable(false);sym_comma.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_comma.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_comma.setName("0x002C");sym_comma.setOpaque(true);sym_comma.setBorder(blackline);sym_comma.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_comma.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		sym_dot.setFont(font);sym_dot.setBackground(GlobalConstant.getDefaultKeyColor());sym_dot.setForeground(GlobalConstant.getDefaultKeyFgColor());sym_dot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);sym_dot.setText(".");sym_dot.setFocusable(false);sym_dot.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());sym_dot.setMinimumSize(GlobalConstant.getSymEmojiMinSize());sym_dot.setName("0x002E");sym_dot.setOpaque(true);sym_dot.setBorder(blackline);sym_dot.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());
		sym_dot.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				keyClicked(evt);
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				keyMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				keyMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		javax.swing.GroupLayout symbolsPanelLayout = new javax.swing.GroupLayout(symbolsPanel);
		symbolsPanel.setLayout(symbolsPanelLayout);
		symbolsPanelLayout.setHorizontalGroup(symbolsPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(symbolsPanelLayout.createSequentialGroup()
						.addGroup(symbolsPanelLayout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(symbolsPanelLayout.createSequentialGroup()
										.addComponent(sym_exclamation, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_minus, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_plus, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_multi, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_div, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_eq, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_ne, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_plusmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_sqrt, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_caret, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_le, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_ge, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4))
								.addGroup(symbolsPanelLayout.createSequentialGroup()
										.addComponent(sym_gr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_lt, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_deg, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_half, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_qtr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_sqr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_cube, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_lcurly, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_rcurly, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_lsqr, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_rsqr, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_pipe, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_bslash, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										)))
								.addGroup(javax.swing.GroupLayout.Alignment.LEADING, symbolsPanelLayout
												.createSequentialGroup()
												.addGroup(symbolsPanelLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(symbolsPanelLayout.createSequentialGroup()
										.addComponent(sym_euro, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_pound, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_dollar, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_dblarr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_leftarr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_rightarr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_downarr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_uparr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_eqarr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_tilde, javax.swing.GroupLayout.PREFERRED_SIZE,50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_quote, javax.swing.GroupLayout.PREFERRED_SIZE,50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_approx, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(sym_asterick, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4))))
										.addGroup(symbolsPanelLayout.createSequentialGroup()
												.addComponent(sym_rupee, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(sym_ampersand, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(sym_at, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(sym_hash, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(sym_question, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(sym_semicolon, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(sym_fslash, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(sym_lcir, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(sym_rcir, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(sym_underscr, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(sym_colon, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(sym_comma, javax.swing.GroupLayout.PREFERRED_SIZE,50,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(sym_dot, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)));
		symbolsPanelLayout.setVerticalGroup(symbolsPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(symbolsPanelLayout.createSequentialGroup().addGroup(symbolsPanelLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(symbolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(sym_exclamation, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(sym_minus, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(sym_plus, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(sym_multi, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(sym_div, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(sym_eq, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(sym_ne, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(sym_plusmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(sym_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(sym_sqrt, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(sym_caret, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(sym_le, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(sym_ge, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(4, 4, 4)
						.addGroup(symbolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(symbolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(sym_gr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_lt, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_deg, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_half, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_qtr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_sqr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_cube, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_lcurly, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_rcurly, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_lsqr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_rsqr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_pipe, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_bslash, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(4, 4, 4)
						.addGroup(symbolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(symbolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(sym_euro, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_pound, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_dollar, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_dblarr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_leftarr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_rightarr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_downarr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_uparr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_eqarr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_tilde, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_quote, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_approx, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_asterick, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(4, 4, 4)
						.addGroup(symbolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(symbolsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(sym_rupee, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_ampersand, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_at, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_hash, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_question, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_semicolon, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_fslash, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_lcir, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_rcir, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_underscr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_colon, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_comma, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(sym_dot, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(4, 4, 4))));

		/* Classic Keys Panel 
		 * Doing only Copy Paste since Unicode is not getting printed using robot
		 */
		cls_total.setFont(font);cls_total.setBackground(GlobalConstant.getDefaultKeyColor());
		cls_total.setForeground(GlobalConstant.getDefaultKeyFgColor());
		cls_total.setHorizontalAlignment(SwingConstants.CENTER);
		cls_total.setIcon(new ImageIcon(getClass().getResource("/icons/cls_total.png")));cls_total.setFocusable(false);cls_total.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_total.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_total.setName("0x11FED");cls_total.setOpaque(true);cls_total.setBorder(blackline);cls_total.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_total.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿭");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		
		cls_uppalam.setFont(font);cls_uppalam.setBackground(GlobalConstant.getDefaultKeyColor());cls_uppalam.setForeground(GlobalConstant.getDefaultKeyFgColor());
		cls_uppalam.setHorizontalAlignment(SwingConstants.CENTER);
		cls_uppalam.setIcon(new ImageIcon(getClass().getResource("/icons/cls_uppalam.png")));cls_uppalam.setFocusable(false);cls_uppalam.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_uppalam.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_uppalam.setName("0x11FE7");cls_uppalam.setOpaque(true);cls_uppalam.setBorder(blackline);cls_uppalam.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_uppalam.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿧");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
			cls_onefifth.setFont(font);cls_onefifth.setBackground(GlobalConstant.getDefaultKeyColor());cls_onefifth.setForeground(GlobalConstant.getDefaultKeyFgColor());
			cls_onefifth.setHorizontalAlignment(SwingConstants.CENTER);
			cls_onefifth.setIcon(new ImageIcon(getClass().getResource("/icons/cls_onefifth.png")));cls_onefifth.setFocusable(false);cls_onefifth.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_onefifth.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_onefifth.setName("0x11FC7");cls_onefifth.setOpaque(true);cls_onefifth.setBorder(blackline);cls_onefifth.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_onefifth.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿏");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_one320th.setFont(font);cls_one320th.setBackground(GlobalConstant.getDefaultKeyColor());cls_one320th.setForeground(GlobalConstant.getDefaultKeyFgColor());
		cls_one320th.setHorizontalAlignment(SwingConstants.CENTER);
		cls_one320th.setIcon(new ImageIcon(getClass().getResource("/icons/cls_one320th.png")));cls_one320th.setFocusable(false);cls_one320th.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_one320th.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_one320th.setName("0x11FD4");cls_one320th.setOpaque(true);cls_one320th.setBorder(blackline);cls_one320th.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_one320th.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿔");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_kuzhi.setFont(font);cls_kuzhi.setBackground(GlobalConstant.getDefaultKeyColor());
		cls_kuzhi.setHorizontalAlignment(SwingConstants.CENTER);
		cls_kuzhi.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_kuzhi.setIcon(new ImageIcon(getClass().getResource("/icons/cls_kuzhi.png")));cls_kuzhi.setFocusable(false);cls_kuzhi.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_kuzhi.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_kuzhi.setName("0x11FE2");cls_kuzhi.setOpaque(true);cls_kuzhi.setBorder(blackline);cls_kuzhi.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_kuzhi.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿢");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_kaasu.setFont(font);cls_kaasu.setBackground(GlobalConstant.getDefaultKeyColor());cls_kaasu.setForeground(GlobalConstant.getDefaultKeyFgColor());
		cls_kaasu.setHorizontalAlignment(SwingConstants.CENTER);
		cls_kaasu.setIcon(new ImageIcon(getClass().getResource("/icons/cls_kaasu.png")));cls_kaasu.setFocusable(false);cls_kaasu.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_kaasu.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_kaasu.setName("0x11FDD");cls_kaasu.setOpaque(true);cls_kaasu.setBorder(blackline);cls_kaasu.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_kaasu.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿝");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_mukkalvisam.setFont(font);cls_mukkalvisam.setBackground(GlobalConstant.getDefaultKeyColor());
		cls_mukkalvisam.setForeground(GlobalConstant.getDefaultKeyFgColor());
		cls_mukkalvisam.setHorizontalAlignment(SwingConstants.CENTER);
		cls_mukkalvisam.setIcon(new ImageIcon(getClass().getResource("/icons/cls_mukkalvisam.png")));cls_mukkalvisam.setFocusable(false);cls_mukkalvisam.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_mukkalvisam.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_mukkalvisam.setName("0x11FCF");cls_mukkalvisam.setOpaque(true);cls_mukkalvisam.setBorder(blackline);cls_mukkalvisam.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_mukkalvisam.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿇");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_ulakku.setFont(font);cls_ulakku.setBackground(GlobalConstant.getDefaultKeyColor());cls_ulakku.setForeground(GlobalConstant.getDefaultKeyFgColor());
		cls_ulakku.setHorizontalAlignment(SwingConstants.CENTER);
		cls_ulakku.setIcon(new ImageIcon(getClass().getResource("/icons/cls_ulakku.png")));cls_ulakku.setFocusable(false);cls_ulakku.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_ulakku.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_ulakku.setName("0x11FD8");cls_ulakku.setOpaque(true);cls_ulakku.setBorder(blackline);cls_ulakku.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_ulakku.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿘");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_mooulakku.setFont(font);cls_mooulakku.setBackground(GlobalConstant.getDefaultKeyColor());cls_mooulakku.setForeground(GlobalConstant.getDefaultKeyFgColor());
		cls_mooulakku.setHorizontalAlignment(SwingConstants.CENTER);
		cls_mooulakku.setIcon(new ImageIcon(getClass().getResource("/icons/cls_mooulakku.png")));cls_mooulakku.setFocusable(false);cls_mooulakku.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_mooulakku.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_mooulakku.setName("0x11FD9");cls_mooulakku.setOpaque(true);cls_mooulakku.setBorder(blackline);cls_mooulakku.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_mooulakku.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿙");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_mukkal.setFont(font);cls_mukkal.setBackground(GlobalConstant.getDefaultKeyColor());cls_mukkal.setForeground(GlobalConstant.getDefaultKeyFgColor());
		cls_mukkal.setHorizontalAlignment(SwingConstants.CENTER);
		cls_mukkal.setIcon(new ImageIcon(getClass().getResource("/icons/cls_mukkal.png")));cls_mukkal.setFocusable(false);cls_mukkal.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_mukkal.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_mukkal.setName("0x11FD3");cls_mukkal.setOpaque(true);cls_mukkal.setBorder(blackline);cls_mukkal.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_mukkal.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿓");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_pathakku.setFont(font);cls_pathakku.setBackground(GlobalConstant.getDefaultKeyColor());cls_pathakku.setForeground(GlobalConstant.getDefaultKeyFgColor());
		cls_pathakku.setHorizontalAlignment(SwingConstants.CENTER);
		cls_pathakku.setIcon(new ImageIcon(getClass().getResource("/icons/cls_pathakku.png")));cls_pathakku.setFocusable(false);cls_pathakku.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_pathakku.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_pathakku.setName("0x11FDB");cls_pathakku.setOpaque(true);cls_pathakku.setBorder(blackline);cls_pathakku.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_pathakku.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿛");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_nallathu.setFont(font);cls_nallathu.setBackground(GlobalConstant.getDefaultKeyColor());cls_nallathu.setForeground(GlobalConstant.getDefaultKeyFgColor());
		cls_nallathu.setHorizontalAlignment(SwingConstants.CENTER);
		cls_nallathu.setIcon(new ImageIcon(getClass().getResource("/icons/cls_nallathu.png")));cls_nallathu.setFocusable(false);cls_nallathu.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_nallathu.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_nallathu.setName("0x11FEA");cls_nallathu.setOpaque(true);cls_nallathu.setBorder(blackline);cls_nallathu.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_nallathu.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿪");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_silavinam.setFont(font);cls_silavinam.setBackground(GlobalConstant.getDefaultKeyColor());cls_silavinam.setForeground(GlobalConstant.getDefaultKeyFgColor());
		cls_silavinam.setHorizontalAlignment(SwingConstants.CENTER);
		cls_silavinam.setIcon(new ImageIcon(getClass().getResource("/icons/cls_silavinam.png")));cls_silavinam.setFocusable(false);cls_silavinam.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_silavinam.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_silavinam.setName("0x11FEB");cls_silavinam.setOpaque(true);cls_silavinam.setBorder(blackline);cls_silavinam.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_silavinam.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿫");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_one40.setFont(font);cls_one40.setBackground(GlobalConstant.getDefaultKeyColor());cls_one40.setForeground(GlobalConstant.getDefaultKeyFgColor());
		cls_one40.setHorizontalAlignment(SwingConstants.CENTER);
		cls_one40.setIcon(new ImageIcon(getClass().getResource("/icons/cls_one40.png")));cls_one40.setFocusable(false);cls_one40.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_one40.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_one40.setName("0x11FC4");cls_one40.setOpaque(true);cls_one40.setBorder(blackline);cls_one40.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_one40.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿄");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_one32nd.setFont(font);cls_one32nd.setBackground(GlobalConstant.getDefaultKeyColor());cls_one32nd.setForeground(GlobalConstant.getDefaultKeyFgColor());
		cls_one32nd.setHorizontalAlignment(SwingConstants.CENTER);
		cls_one32nd.setIcon(new ImageIcon(getClass().getResource("/icons/cls_one32nd.png")));cls_one32nd.setFocusable(false);cls_one32nd.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_one32nd.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_one32nd.setName("0x11FC5");cls_one32nd.setOpaque(true);cls_one32nd.setBorder(blackline);cls_one32nd.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_one32nd.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿅");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_marakkal.setFont(font);cls_marakkal.setBackground(GlobalConstant.getDefaultKeyColor());cls_marakkal.setForeground(GlobalConstant.getDefaultKeyFgColor());
		cls_marakkal.setHorizontalAlignment(SwingConstants.CENTER);
		cls_marakkal.setIcon(new ImageIcon(getClass().getResource("/icons/cls_marakkal.png")));cls_marakkal.setFocusable(false);cls_marakkal.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_marakkal.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_marakkal.setName("0x11FDA");cls_marakkal.setOpaque(true);cls_marakkal.setBorder(blackline);cls_marakkal.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_marakkal.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿚");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_one20.setFont(font);cls_one20.setBackground(GlobalConstant.getDefaultKeyColor());cls_one20.setForeground(GlobalConstant.getDefaultKeyFgColor());
		cls_one20.setHorizontalAlignment(SwingConstants.CENTER);cls_one20.setIcon(new ImageIcon(getClass().getResource("/icons/cls_one20.png")));cls_one20.setFocusable(false);cls_one20.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_one20.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_one20.setName("0x11FC8");cls_one20.setOpaque(true);cls_one20.setBorder(blackline);cls_one20.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_one20.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿈");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_one16.setFont(font);cls_one16.setBackground(GlobalConstant.getDefaultKeyColor());cls_one16.setForeground(GlobalConstant.getDefaultKeyFgColor());
		cls_one16.setHorizontalAlignment(SwingConstants.CENTER);cls_one16.setIcon(new ImageIcon(getClass().getResource("/icons/cls_one16.png")));cls_one16.setFocusable(false);cls_one16.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_one16.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_one16.setName("0x11FC9");cls_one16.setOpaque(true);cls_one16.setBorder(blackline);cls_one16.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_one16.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿉");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_poga.setFont(font);cls_poga.setBackground(GlobalConstant.getDefaultKeyColor());cls_poga.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_poga.setHorizontalAlignment(SwingConstants.CENTER);
		cls_poga.setIcon(new ImageIcon(getClass().getResource("/icons/cls_poga.png")));cls_poga.setFocusable(false);cls_poga.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_poga.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_poga.setName("0x11FEC");cls_poga.setOpaque(true);cls_poga.setBorder(blackline);cls_poga.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_poga.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿬");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_paaram.setFont(font);cls_paaram.setBackground(GlobalConstant.getDefaultKeyColor());cls_paaram.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_paaram.setHorizontalAlignment(SwingConstants.CENTER);
		cls_paaram.setIcon(new ImageIcon(getClass().getResource("/icons/cls_paaram.png")));cls_paaram.setFocusable(false);cls_paaram.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_paaram.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_paaram.setName("0x11FE1");cls_paaram.setOpaque(true);cls_paaram.setBorder(blackline);cls_paaram.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_paaram.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿡");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_punsey.setFont(font);cls_punsey.setBackground(GlobalConstant.getDefaultKeyColor());cls_punsey.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_punsey.setHorizontalAlignment(SwingConstants.CENTER);
		cls_punsey.setIcon(new ImageIcon(getClass().getResource("/icons/cls_punsey.png")));cls_punsey.setFocusable(false);cls_punsey.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_punsey.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_punsey.setName("0x11FE5");cls_punsey.setOpaque(true);cls_punsey.setBorder(blackline);cls_punsey.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_punsey.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿥");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_year.setFont(font);cls_year.setBackground(GlobalConstant.getDefaultKeyColor());cls_year.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_year.setHorizontalAlignment(SwingConstants.CENTER);
		cls_year.setIcon(new ImageIcon(getClass().getResource("/icons/cls_year.png")));cls_year.setFocusable(false);cls_year.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_year.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_year.setName("0x0BF5");cls_year.setOpaque(true);cls_year.setBorder(blackline);cls_year.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_year.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("௵");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_muthal.setFont(font);cls_muthal.setBackground(GlobalConstant.getDefaultKeyColor());cls_muthal.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_muthal.setHorizontalAlignment(SwingConstants.CENTER);
		cls_muthal.setIcon(new ImageIcon(getClass().getResource("/icons/cls_muthal.png")));cls_muthal.setFocusable(false);cls_muthal.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_muthal.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_muthal.setName("0x11FEF");cls_muthal.setOpaque(true);cls_muthal.setBorder(blackline);cls_muthal.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_muthal.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿯");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_muthaliya.setFont(font);cls_muthaliya.setBackground(GlobalConstant.getDefaultKeyColor());cls_muthaliya.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_muthaliya.setHorizontalAlignment(SwingConstants.CENTER);
		cls_muthaliya.setIcon(new ImageIcon(getClass().getResource("/icons/cls_muthaliya.png")));cls_muthaliya.setFocusable(false);cls_muthaliya.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_muthaliya.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_muthaliya.setName("0x11FF0");cls_muthaliya.setOpaque(true);cls_muthaliya.setBorder(blackline);cls_muthaliya.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_muthaliya.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿰");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_nilam.setFont(font);cls_nilam.setBackground(GlobalConstant.getDefaultKeyColor());cls_nilam.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_nilam.setHorizontalAlignment(SwingConstants.CENTER);
		cls_nilam.setIcon(new ImageIcon(getClass().getResource("/icons/cls_nilam.png")));cls_nilam.setFocusable(false);cls_nilam.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_nilam.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_nilam.setName("0x11FE6");cls_nilam.setOpaque(true);cls_nilam.setBorder(blackline);cls_nilam.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_nilam.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿦");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_arai.setFont(font);cls_arai.setBackground(GlobalConstant.getDefaultKeyColor());cls_arai.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_arai.setHorizontalAlignment(SwingConstants.CENTER);
		cls_arai.setIcon(new ImageIcon(getClass().getResource("/icons/cls_arai.png")));cls_arai.setFocusable(false);cls_arai.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_arai.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_arai.setName("0x11FD2");cls_arai.setOpaque(true);cls_arai.setBorder(blackline);cls_arai.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_arai.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿒");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_one160.setFont(font);cls_one160.setBackground(GlobalConstant.getDefaultKeyColor());cls_one160.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_one160.setHorizontalAlignment(SwingConstants.CENTER);
		cls_one160.setIcon(new ImageIcon(getClass().getResource("/icons/cls_one160.png")));cls_one160.setFocusable(false);cls_one160.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_one160.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_one160.setName("0x11FC1");cls_one160.setOpaque(true);cls_one160.setBorder(blackline);cls_one160.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_one160.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿁");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_enn.setFont(font);cls_enn.setBackground(GlobalConstant.getDefaultKeyColor());cls_enn.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_enn.setHorizontalAlignment(SwingConstants.CENTER);
		cls_enn.setIcon(new ImageIcon(getClass().getResource("/icons/cls_enn.png")));cls_enn.setFocusable(false);cls_enn.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_enn.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_enn.setName("0x11FE9");cls_enn.setOpaque(true);cls_enn.setBorder(blackline);cls_enn.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_enn.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿩");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_three20.setFont(font);cls_three20.setBackground(GlobalConstant.getDefaultKeyColor());cls_three20.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_three20.setHorizontalAlignment(SwingConstants.CENTER);
		cls_three20.setIcon(new ImageIcon(getClass().getResource("/icons/cls_three20.png")));cls_three20.setFocusable(false);cls_three20.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_three20.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_three20.setName("0x11FCD");cls_three20.setOpaque(true);cls_three20.setBorder(blackline);cls_three20.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_three20.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿍");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_three16.setFont(font);cls_three16.setBackground(GlobalConstant.getDefaultKeyColor());cls_three16.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_three16.setHorizontalAlignment(SwingConstants.CENTER);
		cls_three16.setIcon(new ImageIcon(getClass().getResource("/icons/cls_three16.png")));cls_three16.setFocusable(false);cls_three16.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_three16.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_three16.setName("0x11FCE");cls_three16.setOpaque(true);cls_three16.setBorder(blackline);cls_three16.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_three16.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿎");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_mukkuruni.setFont(font);cls_mukkuruni.setBackground(GlobalConstant.getDefaultKeyColor());cls_mukkuruni.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_mukkuruni.setHorizontalAlignment(SwingConstants.CENTER);
		cls_mukkuruni.setIcon(new ImageIcon(getClass().getResource("/icons/cls_mukkuruni.png")));cls_mukkuruni.setFocusable(false);cls_mukkuruni.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_mukkuruni.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_mukkuruni.setName("0x11FDC");cls_mukkuruni.setOpaque(true);cls_mukkuruni.setBorder(blackline);cls_mukkuruni.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_mukkuruni.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿜");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_nansey.setFont(font);cls_nansey.setBackground(GlobalConstant.getDefaultKeyColor());cls_nansey.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_nansey.setHorizontalAlignment(SwingConstants.CENTER);
		cls_nansey.setIcon(new ImageIcon(getClass().getResource("/icons/cls_nansey.png")));cls_nansey.setFocusable(false);cls_nansey.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_nansey.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_nansey.setName("0x11FEA");cls_nansey.setOpaque(true);cls_nansey.setBorder(blackline);cls_nansey.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_nansey.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿤");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_mukkani.setFont(font);cls_mukkani.setBackground(GlobalConstant.getDefaultKeyColor());cls_mukkani.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_mukkani.setHorizontalAlignment(SwingConstants.CENTER);
		cls_mukkani.setIcon(new ImageIcon(getClass().getResource("/icons/cls_mukkani.png")));cls_mukkani.setFocusable(false);cls_mukkani.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_mukkani.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_mukkani.setName("0x11FC6");cls_mukkani.setOpaque(true);cls_mukkani.setBorder(blackline);cls_mukkani.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_mukkani.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿆");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_panam.setFont(font);cls_panam.setBackground(GlobalConstant.getDefaultKeyColor());cls_panam.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_panam.setHorizontalAlignment(SwingConstants.CENTER);
		cls_panam.setIcon(new ImageIcon(getClass().getResource("/icons/cls_panam.png")));cls_panam.setFocusable(false);cls_panam.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_panam.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_panam.setName("0x11FDE");cls_panam.setOpaque(true);cls_panam.setBorder(blackline);cls_panam.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_panam.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿞");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_pon.setFont(font);cls_pon.setBackground(GlobalConstant.getDefaultKeyColor());cls_pon.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_pon.setHorizontalAlignment(SwingConstants.CENTER);
		cls_pon.setIcon(new ImageIcon(getClass().getResource("/icons/cls_pon.png")));cls_pon.setFocusable(false);cls_pon.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_pon.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_pon.setName("0x11FDF");cls_pon.setOpaque(true);cls_pon.setBorder(blackline);cls_pon.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_pon.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿟");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_vaaraagan.setFont(font);cls_vaaraagan.setBackground(GlobalConstant.getDefaultKeyColor());cls_vaaraagan.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_vaaraagan.setHorizontalAlignment(SwingConstants.CENTER);
		cls_vaaraagan.setIcon(new ImageIcon(getClass().getResource("/icons/cls_vaaraagan.png")));cls_vaaraagan.setFocusable(false);cls_vaaraagan.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_vaaraagan.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_vaaraagan.setName("0x11FFF");cls_vaaraagan.setOpaque(true);cls_vaaraagan.setBorder(blackline);cls_vaaraagan.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_vaaraagan.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿠");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_kaani.setFont(font);cls_kaani.setBackground(GlobalConstant.getDefaultKeyColor());cls_kaani.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_kaani.setHorizontalAlignment(SwingConstants.CENTER);
		cls_kaani.setIcon(new ImageIcon(getClass().getResource("/icons/cls_kaani.png")));cls_kaani.setFocusable(false);cls_kaani.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_kaani.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_kaani.setName("0x11FC2");cls_kaani.setOpaque(true);cls_kaani.setBorder(blackline);cls_kaani.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_kaani.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿂");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_kaalvisam.setFont(font);cls_kaalvisam.setBackground(GlobalConstant.getDefaultKeyColor());cls_kaalvisam.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_kaalvisam.setHorizontalAlignment(SwingConstants.CENTER);
		cls_kaalvisam.setIcon(new ImageIcon(getClass().getResource("/icons/cls_kaalvisam.png")));cls_kaalvisam.setFocusable(false);cls_kaalvisam.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_kaalvisam.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_kaalvisam.setName("0x11FC3");cls_kaalvisam.setOpaque(true);cls_kaalvisam.setBorder(blackline);cls_kaalvisam.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_kaalvisam.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿃");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_one10.setFont(font);cls_one10.setBackground(GlobalConstant.getDefaultKeyColor());cls_one10.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_one10.setHorizontalAlignment(SwingConstants.CENTER);
		cls_one10.setIcon(new ImageIcon(getClass().getResource("/icons/cls_one10.png")));cls_one10.setFocusable(false);cls_one10.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_one10.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_one10.setName("0x11FCB");cls_one10.setOpaque(true);cls_one10.setBorder(blackline);cls_one10.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_one10.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿋");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_munthiri.setFont(font);cls_munthiri.setBackground(GlobalConstant.getDefaultKeyColor());cls_munthiri.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_munthiri.setHorizontalAlignment(SwingConstants.CENTER);
		cls_munthiri.setIcon(new ImageIcon(getClass().getResource("/icons/cls_munthiri.png")));cls_munthiri.setFocusable(false);cls_munthiri.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_munthiri.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_munthiri.setName("0x11FC0");cls_munthiri.setOpaque(true);cls_munthiri.setBorder(blackline);cls_munthiri.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_munthiri.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿀");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_varavu.setFont(font);cls_varavu.setBackground(GlobalConstant.getDefaultKeyColor());cls_varavu.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_varavu.setHorizontalAlignment(SwingConstants.CENTER);
		cls_varavu.setIcon(new ImageIcon(getClass().getResource("/icons/cls_varavu.png")));cls_varavu.setFocusable(false);cls_varavu.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_varavu.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_varavu.setName("0x11FF8");cls_varavu.setOpaque(true);cls_varavu.setBorder(blackline);cls_varavu.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_varavu.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿨");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_vagayara.setFont(font);cls_vagayara.setBackground(GlobalConstant.getDefaultKeyColor());cls_vagayara.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_vagayara.setHorizontalAlignment(SwingConstants.CENTER);
		cls_vagayara.setIcon(new ImageIcon(getClass().getResource("/icons/cls_vagayara.png")));cls_vagayara.setFocusable(false);cls_vagayara.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_vagayara.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_vagayara.setName("0x11FF1");cls_vagayara.setOpaque(true);cls_vagayara.setBorder(blackline);cls_vagayara.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_vagayara.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿱");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_vasam.setFont(font);cls_vasam.setBackground(GlobalConstant.getDefaultKeyColor());cls_vasam.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_vasam.setHorizontalAlignment(SwingConstants.CENTER);
		cls_vasam.setIcon(new ImageIcon(getClass().getResource("/icons/cls_vasam.png")));cls_vasam.setFocusable(false);cls_vasam.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_vasam.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_vasam.setName("0x11FEE");cls_vasam.setOpaque(true);cls_vasam.setBorder(blackline);cls_vasam.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_vasam.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿮");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_araikaal.setFont(font);cls_araikaal.setBackground(GlobalConstant.getDefaultKeyColor());cls_araikaal.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_araikaal.setHorizontalAlignment(SwingConstants.CENTER);
		cls_araikaal.setIcon(new ImageIcon(getClass().getResource("/icons/cls_araikaal.png")));cls_araikaal.setFocusable(false);cls_araikaal.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_araikaal.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_araikaal.setName("0x11FCC");cls_araikaal.setOpaque(true);cls_araikaal.setBorder(blackline);cls_araikaal.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_araikaal.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿌");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_oneqtr.setFont(font);cls_oneqtr.setBackground(GlobalConstant.getDefaultKeyColor());cls_oneqtr.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_oneqtr.setHorizontalAlignment(SwingConstants.CENTER);
		cls_oneqtr.setIcon(new ImageIcon(getClass().getResource("/icons/cls_oneqtr.png")));cls_oneqtr.setFocusable(false);cls_oneqtr.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_oneqtr.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_oneqtr.setName("0x11FD0");cls_oneqtr.setOpaque(true);cls_oneqtr.setBorder(blackline);cls_oneqtr.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_oneqtr.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿐");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_arai_2.setFont(font);cls_arai_2.setBackground(GlobalConstant.getDefaultKeyColor());cls_arai_2.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_arai_2.setHorizontalAlignment(SwingConstants.CENTER);
		cls_arai_2.setIcon(new ImageIcon(getClass().getResource("/icons/cls_arai_2.png")));cls_arai_2.setFocusable(false);cls_arai_2.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_arai_2.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_arai_2.setName("0x11FD1");cls_arai_2.setOpaque(true);cls_arai_2.setBorder(blackline);cls_arai_2.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_arai_2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿑");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_endoftext.setFont(font);cls_endoftext.setBackground(GlobalConstant.getDefaultKeyColor());cls_endoftext.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_endoftext.setHorizontalAlignment(SwingConstants.CENTER);
		cls_endoftext.setIcon(new ImageIcon(getClass().getResource("/icons/cls_endoftext.png")));cls_endoftext.setFocusable(false);cls_endoftext.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_endoftext.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_endoftext.setName("0x11FE0");cls_endoftext.setOpaque(true);cls_endoftext.setBorder(blackline);cls_endoftext.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_endoftext.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿠");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_month.setFont(font);cls_month.setBackground(GlobalConstant.getDefaultKeyColor());cls_month.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_month.setHorizontalAlignment(SwingConstants.CENTER);
		cls_month.setIcon(new ImageIcon(getClass().getResource("/icons/cls_month.png")));cls_month.setFocusable(false);cls_month.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_month.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_month.setName("0x0BF4");cls_month.setOpaque(true);cls_month.setBorder(blackline);cls_month.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_month.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("௴");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_veeli.setFont(font);cls_veeli.setBackground(GlobalConstant.getDefaultKeyColor());cls_veeli.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_veeli.setHorizontalAlignment(SwingConstants.CENTER);
		cls_veeli.setIcon(new ImageIcon(getClass().getResource("/icons/cls_veeli.png")));cls_veeli.setFocusable(false);cls_veeli.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_veeli.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_veeli.setName("0x11FC3");cls_veeli.setOpaque(true);cls_veeli.setBorder(blackline);cls_veeli.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_veeli.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿣");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});

		cls_cevitu.setFont(font);cls_cevitu.setBackground(GlobalConstant.getDefaultKeyColor());cls_cevitu.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_cevitu.setHorizontalAlignment(SwingConstants.CENTER);
		cls_cevitu.setIcon(new ImageIcon(getClass().getResource("/icons/cls_cevitu.png")));cls_cevitu.setFocusable(false);cls_cevitu.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_cevitu.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_cevitu.setName("0x11FD6");cls_cevitu.setOpaque(true);cls_cevitu.setBorder(blackline);cls_cevitu.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_cevitu.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿖");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_aalaku.setFont(font);cls_aalaku.setBackground(GlobalConstant.getDefaultKeyColor());cls_aalaku.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_aalaku.setHorizontalAlignment(SwingConstants.CENTER);
		cls_aalaku.setIcon(new ImageIcon(getClass().getResource("/icons/cls_aalaku.png")));cls_aalaku.setFocusable(false);cls_aalaku.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_aalaku.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_aalaku.setName("0x11FD7");cls_aalaku.setOpaque(true);cls_aalaku.setBorder(blackline);cls_aalaku.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_aalaku.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿗");
				pressAndReleaseKey();
			}
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});
		cls_nel.setFont(font);cls_nel.setBackground(GlobalConstant.getDefaultKeyColor());cls_nel.setForeground(GlobalConstant.getDefaultKeyFgColor());cls_nel.setHorizontalAlignment(SwingConstants.CENTER);
		cls_nel.setIcon(new ImageIcon(getClass().getResource("/icons/cls_nel.png")));cls_nel.setFocusable(false);cls_nel.setMaximumSize(GlobalConstant.getSymEmojiMaxSize());cls_nel.setMinimumSize(GlobalConstant.getSymEmojiMinSize());cls_nel.setName("0x11FD5");cls_nel.setOpaque(true);cls_nel.setBorder(blackline);cls_nel.setPreferredSize(GlobalConstant.getSymEmojiPrfSize());cls_nel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				Keyboard.copy("𑿕");
				pressAndReleaseKey();
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				emoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				emoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				keyPressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				keyReleased(evt);
			}
		});


		javax.swing.GroupLayout classicPanelLayout = new javax.swing.GroupLayout(classicPanel);
		classicPanel.setLayout(classicPanelLayout);
		classicPanelLayout.setHorizontalGroup(classicPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(classicPanelLayout.createSequentialGroup()
						.addGroup(classicPanelLayout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(classicPanelLayout.createSequentialGroup()
										.addComponent(cls_total, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_uppalam, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_onefifth, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_one320th, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_kuzhi, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_kaasu, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_mukkalvisam, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_ulakku, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_mooulakku, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_mukkal, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_pathakku, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_nallathu, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_silavinam, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4))
								.addGroup(classicPanelLayout.createSequentialGroup()
										.addComponent(cls_one40, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_one32nd, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_marakkal, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_one20, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_one16, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_poga, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_paaram, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_punsey, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_year, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_muthal, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_muthaliya, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_nilam, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_arai, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										)))
								.addGroup(javax.swing.GroupLayout.Alignment.LEADING, classicPanelLayout
												.createSequentialGroup()
												.addGroup(classicPanelLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(classicPanelLayout.createSequentialGroup()
										.addComponent(cls_one160, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_enn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_three20, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_three16, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_mukkuruni, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_nansey, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_mukkani, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_panam, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_pon, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_vaaraagan, javax.swing.GroupLayout.PREFERRED_SIZE,50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_kaani, javax.swing.GroupLayout.PREFERRED_SIZE,50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_kaalvisam, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4)
										.addComponent(cls_one10, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4))))
										.addGroup(classicPanelLayout.createSequentialGroup()
												.addComponent(cls_munthiri, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(cls_varavu, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(cls_vagayara, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(cls_vasam, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(cls_araikaal, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(cls_oneqtr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(cls_arai_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(cls_endoftext, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(cls_month, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(cls_veeli, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(cls_cevitu, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(cls_aalaku, javax.swing.GroupLayout.PREFERRED_SIZE,50,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)
												.addComponent(cls_nel, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(4, 4, 4)));
		classicPanelLayout.setVerticalGroup(classicPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(classicPanelLayout.createSequentialGroup().addGroup(classicPanelLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(classicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(cls_total, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cls_uppalam, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cls_onefifth, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cls_one320th, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cls_kuzhi, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cls_kaasu, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cls_mukkalvisam, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cls_ulakku, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cls_mooulakku, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cls_mukkal, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cls_pathakku, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cls_nallathu, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cls_silavinam, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(4, 4, 4)
						.addGroup(classicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(classicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(cls_one40, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(cls_one32nd, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(cls_marakkal, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(cls_one20, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(cls_one16, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(cls_poga, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(cls_paaram, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(cls_punsey, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(cls_year, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(cls_muthal, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(cls_muthaliya, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(cls_nilam, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(cls_arai, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(4, 4, 4)
						.addGroup(classicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(classicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								          .addComponent(cls_one160, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_enn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_three20, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_three16, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_mukkuruni, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_nansey, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_mukkani, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_panam, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_pon, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_vaaraagan, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_kaani, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_kaalvisam, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_one10, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(4, 4, 4)
						.addGroup(classicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(classicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								          .addComponent(cls_munthiri, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_varavu, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_vagayara, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_vasam, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_araikaal, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_oneqtr, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_arai_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_endoftext, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_month, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_veeli, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_cevitu, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_aalaku, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE)
								          .addComponent(cls_nel, javax.swing.GroupLayout.PREFERRED_SIZE, 40,javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(4, 4, 4))));

		/* Build mainPanel and pack for UI display */
		
		
		javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
		mainPanel.setLayout(mainPanelLayout);
		mainPanelLayout.setHorizontalGroup(mainPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGap(4, 4, 4)
								.addComponent(functionPanel, javax.swing.GroupLayout.PREFERRED_SIZE,754,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(numpadPanel, javax.swing.GroupLayout.PREFERRED_SIZE,754,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(4, 4, 4)
								.addGroup(mainPanelLayout.createSequentialGroup()
								.addGap(4, 4, 4)
								.addComponent(leftctrlPanel, javax.swing.GroupLayout.PREFERRED_SIZE,70,
										javax.swing.GroupLayout.PREFERRED_SIZE)	
								.addGap(4,4,4)
								.addGroup(mainPanelLayout.createParallelGroup()
								.addComponent(alphabetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 600,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(emojiPanel, javax.swing.GroupLayout.PREFERRED_SIZE,600,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(symbolsPanel, javax.swing.GroupLayout.PREFERRED_SIZE,600,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(classicPanel, javax.swing.GroupLayout.PREFERRED_SIZE,600,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								)
								.addGap(2,2,2)
								.addComponent(rightctrlPanel, javax.swing.GroupLayout.PREFERRED_SIZE,70,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGap(4, 4, 4)
						.addComponent(bottomctrlPanel, javax.swing.GroupLayout.PREFERRED_SIZE,754,
										javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(4,4,4));
		mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(mainPanelLayout.createSequentialGroup()
						.addGap(4, 4, 4)
						.addComponent(functionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(numpadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(4, 4, 4)
							.addGroup(mainPanelLayout.createParallelGroup()
							.addGap(4, 4, 4)
							.addComponent(leftctrlPanel, javax.swing.GroupLayout.PREFERRED_SIZE,172,
											javax.swing.GroupLayout.PREFERRED_SIZE)
							.addGap(4, 4, 4)
							.addGroup(mainPanelLayout.createSequentialGroup()
							.addComponent(alphabetPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
									javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(emojiPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
									javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(symbolsPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
									javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(classicPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
									javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(2, 2,2)							
						.addComponent(rightctrlPanel, javax.swing.GroupLayout.PREFERRED_SIZE,172,
								javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(4, 4, 4)
						.addComponent(bottomctrlPanel, javax.swing.GroupLayout.PREFERRED_SIZE,40,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(4, 4, 4)));

		GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(0, 0, 0)));
		// fKeyboardframe.getContentPane().add(layout);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel alphabetPanel;
	private javax.swing.JPanel functionPanel;
	private javax.swing.JLabel key0;
	private javax.swing.JLabel key1;
	private javax.swing.JLabel key2;
	private javax.swing.JLabel key3;
	private javax.swing.JLabel key4;
	private javax.swing.JLabel key5;
	private javax.swing.JLabel key6;
	private javax.swing.JLabel key7;
	private javax.swing.JLabel key8;
	private javax.swing.JLabel key9;
	private javax.swing.JLabel keya;
	private javax.swing.JLabel keysym;
	private javax.swing.JLabel keyb;
	private javax.swing.JLabel keyt_suli;
	private javax.swing.JLabel keyt_sri;
	private javax.swing.JLabel keyt_altnum;	
	private javax.swing.JLabel keyt_maatru;
	private javax.swing.JLabel keynp_maatru;
	private javax.swing.JLabel keybackspace;
	private javax.swing.JLabel keynp_backspace;
	private javax.swing.JLabel keyt_del;
	private javax.swing.JLabel keynp_del;
	private javax.swing.JLabel keyc;
	private javax.swing.JLabel keyemoji;
	private javax.swing.JLabel keyoldfont;
	private javax.swing.JLabel keyclosebigbracket;
	private javax.swing.JLabel keyctrl1;
	private javax.swing.JLabel keyctrl2;
	private javax.swing.JLabel keyd;
	private javax.swing.JLabel keydownarrow;
	private javax.swing.JLabel keye;
	private javax.swing.JLabel keyenter;
	private javax.swing.JLabel keyenterl;
	private javax.swing.JLabel keyf;
	private javax.swing.JLabel keyg;
	private javax.swing.JLabel keyh;
	private javax.swing.JLabel keyi;
	private javax.swing.JLabel keyj;
	private javax.swing.JLabel keyk;
	private javax.swing.JLabel keyl;
	private javax.swing.JLabel keyt_ma;
	private javax.swing.JLabel keyt_ya;
	private javax.swing.JLabel keyt_ra;
	private javax.swing.JLabel keyt_la;
	private javax.swing.JLabel keyleftarrow;
	private javax.swing.JLabel keym;
	private javax.swing.JLabel keyt_ja;
	private javax.swing.JLabel keyt_ha;
	private javax.swing.JLabel keyt_sth;
	private javax.swing.JLabel keyn;
	private javax.swing.JLabel keynum0;
	private javax.swing.JLabel keynum1;
	private javax.swing.JLabel keynum2;
	private javax.swing.JLabel keynum3;
	private javax.swing.JLabel keynum4;
	private javax.swing.JLabel keynum5;
	private javax.swing.JLabel keynum6;
	private javax.swing.JLabel keynum7;
	private javax.swing.JLabel keynum8;
	private javax.swing.JLabel keynum9;
	private javax.swing.JLabel keyo;
	private javax.swing.JLabel keyopenbigbracket;
	private javax.swing.JLabel keyp;
	private javax.swing.JLabel keyt_o;
	private javax.swing.JLabel keyt_ov;
	private javax.swing.JLabel keyt_om;
	private javax.swing.JLabel keyt_ayutha;
	private javax.swing.JLabel keyt_numalt;
	private javax.swing.JLabel keyq;
	private javax.swing.JLabel keyr;
	private javax.swing.JLabel keyrightarrow;
	private javax.swing.JLabel keys;
	private javax.swing.JLabel keyshift;
	private javax.swing.JLabel keyspace;
	private javax.swing.JLabel keyrspace;
	private javax.swing.JLabel keyt;
	private javax.swing.JLabel keytab;
	private javax.swing.JLabel keyu;
	private javax.swing.JLabel keyuparrow;
	private javax.swing.JLabel keyv;
	private javax.swing.JLabel keyw;
	private javax.swing.JLabel keywin;
	private javax.swing.JLabel keyx;
	private javax.swing.JLabel keyy;
	private javax.swing.JLabel keyz;
	private javax.swing.JLabel keyrupee;
	private javax.swing.JLabel keyampersand;
	private javax.swing.JLabel keyat;		
	private javax.swing.JLabel keypound;	
	private javax.swing.JLabel keyquestion;	
	private javax.swing.JLabel keysemicolon;
	private javax.swing.JLabel keyfslash;	
	private javax.swing.JLabel keylrndbrace;
	private javax.swing.JLabel keyrrndbrace;
	private javax.swing.JLabel keycolon;	
	private javax.swing.JLabel keycomma;	
	private javax.swing.JLabel keydot;
	private javax.swing.JLabel keyunderscr;
	private javax.swing.JLabel emo_smile;
	private javax.swing.JLabel emo_sad;
	private javax.swing.JLabel emo_teeth;
	private javax.swing.JLabel emo_love;
	private javax.swing.JLabel emo_zen;
	private javax.swing.JLabel emo_angry;
	private javax.swing.JLabel emo_tear;
	private javax.swing.JLabel emo_oneeye;
	private javax.swing.JLabel emo_tongue;
	private javax.swing.JLabel emo_sneeze;
	private javax.swing.JLabel emo_cry;
	private javax.swing.JLabel emo_zip;
	private javax.swing.JLabel emo_suspense;
	private javax.swing.JLabel emo_hands;
	private javax.swing.JLabel emo_pam;
	private javax.swing.JLabel emo_clap;
	private javax.swing.JLabel emo_victor;
	private javax.swing.JLabel emo_super;
	private javax.swing.JLabel emo_thumbs;
	private javax.swing.JLabel emo_thumbsdown;
	private javax.swing.JLabel emo_punch;
	private javax.swing.JLabel emo_indexfinger;
	private javax.swing.JLabel emo_downfinger;
	private javax.swing.JLabel emo_folded;
	private javax.swing.JLabel emo_arm;
	private javax.swing.JLabel emo_middlefinger;
	private javax.swing.JLabel emo_whitelove;
	private javax.swing.JLabel emo_blacklove;
	private javax.swing.JLabel emo_heartbrk;
	private javax.swing.JLabel emo_fries;
	private javax.swing.JLabel emo_tick;
	private javax.swing.JLabel emo_wrong;
	private javax.swing.JLabel emo_whiteflag;
	private javax.swing.JLabel emo_closeeye;
	private javax.swing.JLabel emo_closeear;
	private javax.swing.JLabel emo_closemouth;
	private javax.swing.JLabel emo_girl;
	private javax.swing.JLabel emo_explode;
	private javax.swing.JLabel emo_petals;
	private javax.swing.JLabel emo_headphone;
	private javax.swing.JLabel emo_torch;
	private javax.swing.JLabel emo_tone;
	private javax.swing.JLabel emo_watch;
	private javax.swing.JLabel emo_ghost;
	private javax.swing.JLabel emo_nocell;
	private javax.swing.JLabel emo_scissor;
	private javax.swing.JLabel emo_boy;
	private javax.swing.JLabel emo_umber;
	private javax.swing.JLabel emo_car;
	private javax.swing.JLabel emo_flight;
	private javax.swing.JLabel emo_train;
	private javax.swing.JLabel emo_bus;
	private javax.swing.JLabel sym_exclamation;
	private javax.swing.JLabel sym_minus;
	private javax.swing.JLabel sym_plus;
	private javax.swing.JLabel sym_multi;
	private javax.swing.JLabel sym_div;
	private javax.swing.JLabel sym_eq;
	private javax.swing.JLabel sym_ne;
	private javax.swing.JLabel sym_plusmin;
	private javax.swing.JLabel sym_mod;
	private javax.swing.JLabel sym_sqrt;
	private javax.swing.JLabel sym_rupee;
	private javax.swing.JLabel sym_le;
	private javax.swing.JLabel sym_ge;
	private javax.swing.JLabel sym_gr;
	private javax.swing.JLabel sym_lt;
	private javax.swing.JLabel sym_deg;
	private javax.swing.JLabel sym_half;
	private javax.swing.JLabel sym_qtr;
	private javax.swing.JLabel sym_sqr;
	private javax.swing.JLabel sym_cube;
	private javax.swing.JLabel sym_lcurly;
	private javax.swing.JLabel sym_rcurly;
	private javax.swing.JLabel sym_lsqr;
	private javax.swing.JLabel sym_rsqr;
	private javax.swing.JLabel sym_pipe;
	private javax.swing.JLabel sym_bslash;
	private javax.swing.JLabel sym_semicolon;
	private javax.swing.JLabel sym_quote;
	private javax.swing.JLabel sym_euro;
	private javax.swing.JLabel sym_pound;
	private javax.swing.JLabel sym_dollar;
	private javax.swing.JLabel sym_dblarr;
	private javax.swing.JLabel sym_leftarr;
	private javax.swing.JLabel sym_rightarr;
	private javax.swing.JLabel sym_downarr;
	private javax.swing.JLabel sym_uparr;
	private javax.swing.JLabel sym_eqarr;
	private javax.swing.JLabel sym_tilde;
	private javax.swing.JLabel sym_caret;
	private javax.swing.JLabel sym_asterick;
	private javax.swing.JLabel sym_approx;
	private javax.swing.JLabel sym_at;
	private javax.swing.JLabel sym_hash;
	private javax.swing.JLabel sym_underscr;
	private javax.swing.JLabel sym_ampersand;
	private javax.swing.JLabel sym_lcir;
	private javax.swing.JLabel sym_rcir;
	private javax.swing.JLabel sym_fslash;
	private javax.swing.JLabel sym_singlequote;
	private javax.swing.JLabel sym_colon;
	private javax.swing.JLabel sym_question;
	private javax.swing.JLabel sym_comma;
	private javax.swing.JLabel sym_dot;
	private javax.swing.JLabel cls_total;
	private javax.swing.JLabel cls_uppalam;
	private javax.swing.JLabel cls_onefifth;
	private javax.swing.JLabel cls_one320th;
	private javax.swing.JLabel cls_kuzhi;
	private javax.swing.JLabel cls_kaasu;
	private javax.swing.JLabel cls_mukkalvisam;
	private javax.swing.JLabel cls_ulakku;
	private javax.swing.JLabel cls_mooulakku;
	private javax.swing.JLabel cls_mukkal;
	private javax.swing.JLabel cls_pathakku;
	private javax.swing.JLabel cls_nallathu;
	private javax.swing.JLabel cls_silavinam;
	private javax.swing.JLabel cls_one40;
	private javax.swing.JLabel cls_one32nd;
	private javax.swing.JLabel cls_marakkal;
	private javax.swing.JLabel cls_one20;
	private javax.swing.JLabel cls_one16;
	private javax.swing.JLabel cls_poga;
	private javax.swing.JLabel cls_paaram;
	private javax.swing.JLabel cls_punsey;
	private javax.swing.JLabel cls_year;
	private javax.swing.JLabel cls_muthal;
	private javax.swing.JLabel cls_muthaliya;
	private javax.swing.JLabel cls_nilam;
	private javax.swing.JLabel cls_arai;
	private javax.swing.JLabel cls_one160;
	private javax.swing.JLabel cls_enn;
	private javax.swing.JLabel cls_three20;
	private javax.swing.JLabel cls_three16;
	private javax.swing.JLabel cls_mukkuruni;
	private javax.swing.JLabel cls_nansey;
	private javax.swing.JLabel cls_mukkani;
	private javax.swing.JLabel cls_panam;
	private javax.swing.JLabel cls_pon;
	private javax.swing.JLabel cls_vaaraagan;
	private javax.swing.JLabel cls_kaani;
	private javax.swing.JLabel cls_kaalvisam;
	private javax.swing.JLabel cls_one10;
	private javax.swing.JLabel cls_munthiri;
	private javax.swing.JLabel cls_varavu;
	private javax.swing.JLabel cls_vagayara;
	private javax.swing.JLabel cls_vasam;
	private javax.swing.JLabel cls_araikaal;
	private javax.swing.JLabel cls_oneqtr;
	private javax.swing.JLabel cls_arai_2;
	private javax.swing.JLabel cls_endoftext;
	private javax.swing.JLabel cls_month;
	private javax.swing.JLabel cls_veeli;
	private javax.swing.JLabel cls_cevitu;
	private javax.swing.JLabel cls_aalaku;
	private javax.swing.JLabel cls_nel;
	private javax.swing.JPanel mainPanel;
	private javax.swing.JPanel numpadPanel;
	private javax.swing.JPanel symbolsPanel;
	private javax.swing.JPanel bottomctrlPanel;
	private javax.swing.JPanel leftctrlPanel;
	private javax.swing.JPanel rightctrlPanel;
	private javax.swing.JPanel emojiPanel;
	private javax.swing.JPanel classicPanel;


	// End of variables declaration//GEN-END:variables
}
//</editor-fold>