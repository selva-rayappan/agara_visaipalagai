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

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.VK_ALT;
import static java.awt.event.KeyEvent.VK_CAPS_LOCK;
import static java.awt.event.KeyEvent.VK_CONTROL;
import static java.awt.event.KeyEvent.VK_NUM_LOCK;
import static java.awt.event.KeyEvent.VK_SCROLL_LOCK;
import static java.awt.event.KeyEvent.VK_SHIFT;
import static java.awt.event.KeyEvent.VK_V;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_WINDOWS;

/*
 * Keyboard events received and handled 
 * Special Keys-SHIFT/ALT/WIN/CAPS/SCROLLS are tracked separately in different functions
 */
public class Keyboard extends GlobalConstant {
    private static boolean IS_NUM_LOCK_ON = true;
    private static boolean IS_CAPS_LOCK_ON = false;
    private static boolean IS_SCROLL_LOCK_ON = false;
    private static boolean IS_SHIFT_PRESSED = false;
    private static boolean IS_CTRL_PRESSED = false;
    private static boolean IS_ALT_PRESSED = false;
    private static boolean IS_WIN_PRESSED = false;
    private static boolean IS_MAATRU_PRESSED = false;
    
    /*
     * Keyboard default constructor 
     */
	Keyboard() {

	}
	
    public static void initialize_hashtable() {
		// TODO Auto-generated method stub
 		ht.put(2949,'???' );    		ht.put(2950,'???' );			ht.put(2951,'???' );			ht.put(2952,'???' );			ht.put(2953,'???' );			ht.put(2954,'???' );			ht.put(2958,'???' );
		ht.put(2959,'???' );			ht.put(2960,'???' );			ht.put(2962,'???' );			ht.put(2963,'???' );			ht.put(2964,'???');			ht.put(2965,'???' );			ht.put(2969,'???' );
		ht.put(2970,'???' );			ht.put(2972,'???' );			ht.put(2974,'???' );			ht.put(2975,'???' );			ht.put(2979,'???');			ht.put(2980,'???' );			ht.put(2984,'???' );
		ht.put(2985,'???' );			ht.put(2986,'???' );			ht.put(2990,'???' );			ht.put(2991,'???' );			ht.put(2992,'???' );			ht.put(2993,'???' );			ht.put(2994,'???' );
		ht.put(2995,'???' );			ht.put(2996,'???' );			ht.put(2997,'???' );			ht.put(2998,'???' );			ht.put(2999,'???' );			ht.put(3000,'???' );			ht.put(3001,'???');
		ht.put(3006,'???');			ht.put(3007,'???');			ht.put(3008,'???');			ht.put(3009,'???');			ht.put(3010,'???');			ht.put(3014,'???');			ht.put(3015,'???');
		ht.put(3016,'???');			ht.put(3018,'???');			ht.put(3019,'???');			ht.put(3020,'???');			ht.put(3021,'???');			ht.put(3024,'???');			ht.put(3046,'???');
		ht.put(3047,'???');			ht.put(3048,'???');			ht.put(3049,'???');			ht.put(3050,'???');			ht.put(3051,'???');			ht.put(3052,'???');			ht.put(3053,'???');
		ht.put(3054,'???');			ht.put(3055,'???');			ht.put(3056,'???');			ht.put(3057,'???');			ht.put(3058,'???');			ht.put(3059,'???');			ht.put(3060,'???');
		ht.put(3061,'???');			ht.put(3062,'???');			ht.put(3063,'???');			ht.put(3064,'???');			ht.put(3065,'???');			ht.put(3066,'???');			ht.put(2947,'???');
		ht.put(33, '!');	ht.put(34, '"');	ht.put(35, '#');	ht.put(36, '$');	ht.put(37, '%');	ht.put(38, '&');	ht.put(39, '\'');	ht.put(40, '(');		ht.put(41, ')');
		ht.put(42, '*');	ht.put(43, '+');	ht.put(44, ',');	ht.put(45, '-');	ht.put(46, '.');	ht.put(47, '/');	ht.put(58, ':');	ht.put(59,';');		ht.put(60, '<');
		ht.put(61, '=');	ht.put(62, '>');	ht.put(63, '?');	ht.put(64, '@');	ht.put(91, '[');	ht.put(92, '\\');	ht.put(93, ']');	ht.put(94, '^');	ht.put(95, '_');	ht.put(96, '`');
		ht.put(123, '{');	ht.put(124, '|');	ht.put(125, '}');	ht.put(126, '~');
	}

	private static Robot robot = null;
    static { 
        try {
            robot = new Robot();
        }catch(AWTException ex) { }
    }
    
    
    /**
     * Performs a key press and key release activity 
     * @param keycode 
     */
    public static void typeKey(int keycode) {
        pressKey(keycode);
        releaseKey(keycode);
        
        switch(keycode) {
            case VK_CAPS_LOCK:
                IS_CAPS_LOCK_ON = !IS_CAPS_LOCK_ON;
                break;
            case VK_NUM_LOCK:
                IS_NUM_LOCK_ON = !IS_NUM_LOCK_ON;
                break;
            case VK_SCROLL_LOCK:
                IS_SCROLL_LOCK_ON = !IS_SCROLL_LOCK_ON;
            case TM_KEYMAATRU:
                IS_MAATRU_PRESSED = !IS_MAATRU_PRESSED;
        }
    }
    
    public static void pressUnicode(int key_code)
    {
    	robot.keyPress(KeyEvent.VK_ALT);

        for(int i = 3; i >= 0; --i)
        {
            // extracts a single decade of the key-code and adds
            // an offset to get the required VK_NUMPAD key-code
            int numpad_kc = key_code / (int) (Math.pow(10, i)) % 10 + KeyEvent.VK_NUMPAD0;

            robot.keyPress(numpad_kc);
            robot.keyRelease(numpad_kc);
        }

        robot.keyRelease(KeyEvent.VK_ALT);
    }
    /**
     * Performs a key press activity. A key pressed must be released using 
     * releaseKey()
     * @param keycode integer representing the Virtual Key code.
     */
    public static void pressKey(int keycode) {
    	
		char keystroke = 0;	
		String keyhit = "";
		
        if(robot == null)
            throw new UnsupportedOperationException("This platform doesn't supports low level keyboard activities.");
        
        /*
         * Check if the special function key is already pressed then simply return
         */
        
        switch (keycode) {
            case VK_SHIFT:
                if (IS_SHIFT_PRESSED)
                    return;
                IS_SHIFT_PRESSED = true;
                break;
            case VK_CONTROL:
                if (IS_CTRL_PRESSED)
                    return;
                IS_CTRL_PRESSED = true;
                break;
            case VK_ALT:
                if (IS_ALT_PRESSED)
                    return;
                IS_ALT_PRESSED = true;
                break;
            case VK_WINDOWS:
                if(IS_WIN_PRESSED)
                    return;
                IS_WIN_PRESSED = true;
            case TM_KEYMAATRU:
                IS_MAATRU_PRESSED = true;
                if(IS_MAATRU_PRESSED)
                    return;
            case VK_UP:
            case VK_DOWN:
            case VK_LEFT:
            case VK_RIGHT:
            	robot.keyPress(keycode);
                    return;
        }
        
        /*
         * Keycode is scanned for ????????????????????????????????? and utilizes System Copy-Paste functions to write the letters
         */
        
        if((keycode >=TM_UNICODE_START & keycode <=TM_UNICODE_END) | 
        		(keycode == TM_UNICODE_KEYSTH) | keycode == TM_UNICODE_KEYSRI |
        		(keycode >=SYM_UNICODE_START & keycode <=SYM_UNICODE_END) |
        		(keycode >=SYM1_UNICODE_START & keycode <=SYM1_UNICODE_END) |
        		(keycode >=SYM2_UNICODE_START & keycode <=SYM2_UNICODE_END) |
        		(keycode >=SYM3_UNICODE_START & keycode <=SYM3_UNICODE_END) |
        		(keycode >=SYM4_UNICODE_START & keycode <=SYM4_UNICODE_END)
        		)
    	{
    		
	        	switch(keycode) {
	        	
	        	case TM_UNICODE_KEYSTH:
	              		keyhit = "?????????";	        			break;
	        	case TM_UNICODE_KEYSRI:
	        			keyhit = "????????????";						break;
	        	case 37:
	        			keyhit = "%";	        			break;
	        	case 38:
	        			keyhit = "&";	        			break;
	        	case 39:
	        			keyhit = "'";	        			break;
	        	case 40:
	        			keyhit = "(";	        			break;
	        	default: 
	        			keystroke = ht.get(keycode);
	        	}
	        	
	        	keyhit = keyhit.concat(String.valueOf(keystroke));
    			copy(keyhit);
    	        robot.keyPress(VK_CONTROL);
    	        IS_CTRL_PRESSED = true;
    	        robot.keyPress(VK_V);
        }
        else
        {
        		robot.keyPress(keycode);
        }

    }
    
    /**
     * Releases a key that was previously pressed.
     * @param keycode integer representing the Virtual Key code.
     */
    public static void releaseKey(int keycode) {
        if(robot == null)
            throw new UnsupportedOperationException("This platform doesn't supports low level keyboard activities.");
        
        /**
         * Check if the special function key is was pressed or not. If not then simply return
         */
        switch (keycode) {
            case VK_SHIFT:
                if (!IS_SHIFT_PRESSED)
                    return;
                IS_SHIFT_PRESSED = false;
                break;
            case VK_CONTROL:
                if (!IS_CTRL_PRESSED)
                    return;
                IS_CTRL_PRESSED = false;
                break;
            case VK_ALT:
                if (!IS_ALT_PRESSED)
                    return;
                IS_ALT_PRESSED = false;
                break;
            case VK_WINDOWS:
                if(!IS_WIN_PRESSED)
                    return;
                IS_WIN_PRESSED = false;
            case TM_KEYMAATRU:
                IS_MAATRU_PRESSED = true;
                if(IS_MAATRU_PRESSED)
                    return;         
        }
        
        /*
         * Keycode is scanned for ????????????????????????????????? and utilizes System Copy-Paste functions to write the letters
         */    
        
        if((keycode >=TM_UNICODE_START & keycode <=TM_UNICODE_END) | 
        		(keycode == TM_UNICODE_KEYSTH) | keycode == TM_UNICODE_KEYSRI |
        		(keycode >=SYM_UNICODE_START & keycode <=SYM_UNICODE_END) |
        		(keycode >=SYM1_UNICODE_START & keycode <=SYM1_UNICODE_END) |
        		(keycode >=SYM2_UNICODE_START & keycode <=SYM2_UNICODE_END) |
        		(keycode >=SYM3_UNICODE_START & keycode <=SYM3_UNICODE_END) |
        		(keycode >=SYM4_UNICODE_START & keycode <=SYM4_UNICODE_END)
        		)
    	{
            robot.keyRelease(VK_CONTROL);
            robot.keyRelease(VK_V);
            IS_CTRL_PRESSED = false;
    	}
        else
        {
        	robot.keyRelease(keycode);
        }
        
    }
    
    /**
     * Releases all special keys that was previously pressed. This function releases
     * Alt, Control, Shift, Windows key.
     */
    public static void releaseAllSpecialKeys() {
        if(IS_ALT_PRESSED) {
            releaseKey(VK_ALT);
            IS_ALT_PRESSED = false;
        }
        if(IS_CTRL_PRESSED) {
            releaseKey(VK_CONTROL);
            IS_CTRL_PRESSED = false;
        }
        if(IS_SHIFT_PRESSED) {
            releaseKey(VK_SHIFT);
            IS_SHIFT_PRESSED = false;
        }
        if(IS_WIN_PRESSED) {
            releaseKey(VK_WINDOWS);
            IS_WIN_PRESSED = false;
        }
    }
    
    /**
     * Gets, the current status of NumLock key. Whether the key is pressed or not.
     * @return true if NumLock is on; otherwise false.
     */
    public static boolean isNumLockOn() {
        return IS_NUM_LOCK_ON;
    }
    
    /**
     * Gets, the current status of CapsLock key. Whether the key is pressed or not.
     * @return true if CapsLock is on; otherwise false.
     */
    public static boolean isCapsLockOn() {
        return IS_CAPS_LOCK_ON;
    }
    
    /**
     * Gets, the current status of ScrollLock key. Whether the key is pressed or not.
     * @return true if ScrollLock is on; otherwise false.
     */
    public static boolean isScrollLockOn() {
        return IS_SCROLL_LOCK_ON;
    }
    
    /**
     * Gets, the current status of Shift key. Whether the shift key is pressed or not.
     * @return true if the shift key is pressed; otherwise false.
     */
    public static boolean isShiftPressed() {
        return IS_SHIFT_PRESSED;
    }
    
    /**
     * Gets, the current status of Control key. Whether the control key is pressed or not.
     * @return true if Control key is pressed; otherwise false.
     */
    public static boolean isCtrlPressed() {
        return IS_CTRL_PRESSED;
    }
    
    /**
     * Gets, the current status of Alt key. Whether the Alt key is pressed or not.
     * @return true if Alt key is pressed; otherwise false.
     */
    public static boolean isAltPressed(){
        return IS_ALT_PRESSED;
    }
    
    /**
     * Gets, the current status of Windows key. Whether the Windows key is pressed 
     * or not.
     * @return true is the Win key is currently pressed; otherwise false. 
     */
    public static boolean isWinPressed() {
        return IS_WIN_PRESSED;
    }
    public static boolean isMaatruPressed() {
        return IS_MAATRU_PRESSED;
    }
    public static void copy(String keyhit)
    {
        Clipboard clipboard = getSystemClipboard();
        clipboard.setContents(new StringSelection(keyhit), null);
    }

    public static void paste() throws AWTException
    {
        Robot robot = new Robot();
        robot.keyPress(VK_CONTROL);
        robot.keyPress(VK_V);
        robot.keyRelease(VK_CONTROL);
        robot.keyRelease(VK_V);
    }

    public static String get() throws Exception
    {
        Clipboard systemClipboard = getSystemClipboard();
        DataFlavor dataFlavor = DataFlavor.stringFlavor;

        if (systemClipboard.isDataFlavorAvailable(dataFlavor))
        {
            Object text = systemClipboard.getData(dataFlavor);
            return (String) text;
        }

        return null;
    }

    private static Clipboard getSystemClipboard()
    {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        return defaultToolkit.getSystemClipboard();
    }

}