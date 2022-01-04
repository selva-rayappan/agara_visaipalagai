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
import java.util.Hashtable;

import static java.awt.event.KeyEvent.VK_ALT;
import static java.awt.event.KeyEvent.VK_CAPS_LOCK;
import static java.awt.event.KeyEvent.VK_CONTROL;
import static java.awt.event.KeyEvent.VK_NUM_LOCK;
import static java.awt.event.KeyEvent.VK_SCROLL_LOCK;
import static java.awt.event.KeyEvent.VK_SHIFT;
import static java.awt.event.KeyEvent.VK_V;
import static java.awt.event.KeyEvent.VK_WINDOWS;

/*
 * Keyboard events received and handled 
 * Special Keys-SHIFT/ALT/WIN/CAPS/SCROLLS are tracked separately in different functions
 */
public class Keyboard implements GlobalConstant {
    private static boolean IS_NUM_LOCK_ON = true;
    private static boolean IS_CAPS_LOCK_ON = false;
    private static boolean IS_SCROLL_LOCK_ON = false;
    private static boolean IS_SHIFT_PRESSED = false;
    private static boolean IS_CTRL_PRESSED = false;
    private static boolean IS_ALT_PRESSED = false;
    private static boolean IS_WIN_PRESSED = false;
    private static boolean IS_MAATRU_PRESSED = false;
    private static final int TM_KEYMAATRU = 10227; // Uyir Maatru - Reload Decimal keycode
    private static final int TM_UNICODE_START = 2944; // Tamil unicode start - Decimal
    private static final int TM_UNICODE_END = 3071; // Tamil unicode end - Decimal
    private static final int TM_UNICODE_KEYSTH = 999999; // Tamil unicode Keysth - Decimal 
    
    
    /*
     * Keyboard default constructor 
     */
	Keyboard() {
		

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
        
        /**
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
        }
        /*
         * Keycode is scanned for மெய்எழுத்து and utilizes System Copy-Paste functions to write the letters
         */
        if((keycode >=TM_UNICODE_START & keycode <=TM_UNICODE_END) | (keycode == TM_UNICODE_KEYSTH))
    	{
        	final Hashtable<Integer, Character> ht = new Hashtable<>();

    		ht.put(2949,'அ' );			ht.put(2950,'ஆ' );			ht.put(2951,'இ' );			ht.put(2952,'ஈ' );			ht.put(2953,'உ' );			ht.put(2954,'ஊ' );			ht.put(2958,'எ' );
    		ht.put(2959,'ஏ' );			ht.put(2960,'ஐ' );			ht.put(2962,'ஒ' );			ht.put(2963,'ஓ' );			ht.put(2964,'ஔ');			ht.put(2965,'க' );			ht.put(2969,'ங' );
    		ht.put(2970,'ச' );			ht.put(2972,'ஜ' );			ht.put(2974,'ஞ' );			ht.put(2975,'ட' );			ht.put(2979,'ண');			ht.put(2980,'த' );			ht.put(2984,'ந' );
    		ht.put(2985,'ன' );			ht.put(2986,'ப' );			ht.put(2990,'ம' );			ht.put(2991,'ய' );			ht.put(2992,'ர' );			ht.put(2993,'ற' );			ht.put(2994,'ல' );
    		ht.put(2995,'ள' );			ht.put(2996,'ழ' );			ht.put(2997,'வ' );			ht.put(2998,'ஶ' );			ht.put(2999,'ஷ' );			ht.put(3000,'ஸ' );			ht.put(3001,'ஹ');
    		ht.put(3006,'ா');			ht.put(3007,'ி');			ht.put(3008,'ீ');			ht.put(3009,'ு');			ht.put(3010,'ூ');			ht.put(3014,'ெ');			ht.put(3015,'ே');
    		ht.put(3016,'ை');			ht.put(3018,'ொ');			ht.put(3019,'ோ');			ht.put(3020,'ௌ');			ht.put(3021,'்');			ht.put(3024,'ௐ');			ht.put(3046,'௦');
    		ht.put(3047,'௧');			ht.put(3048,'௨');			ht.put(3049,'௩');			ht.put(3050,'௪');			ht.put(3051,'௫');			ht.put(3052,'௬');			ht.put(3053,'௭');
    		ht.put(3054,'௮');			ht.put(3055,'௯');			ht.put(3056,'௰');			ht.put(3057,'௱');			ht.put(3058,'௲');			ht.put(3059,'௳');			ht.put(3060,'௴');
    		ht.put(3061,'௵');			ht.put(3062,'௶');			ht.put(3063,'௷');			ht.put(3064,'௸');			ht.put(3065,'௹');			ht.put(3066,'௺');			ht.put(2947,'ஃ');
    		
	        	if(keycode == TM_UNICODE_KEYSTH)
	        	{
	        		keyhit = "க்ஷ";
	        	}
	        	else
	        	{				
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
     * Releases a key that was previously pressed.2950
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
         * Keycode is scanned for மெய்எழுத்து and utilizes System Copy-Paste functions to write the letters
         */    
        
        if(keycode >=2944 & keycode <=3071) 
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