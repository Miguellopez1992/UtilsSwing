/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utilsswing.validation.jtextfield;

import com.utilsswing.validation.constants.Constants;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Miguel López
 * @date 19/07/2014
 * @version V 1.0.0
 */
public class ValInput {

    private static ValInput valInput = null;

    private ValInput() {
    }

    /**
     * this method is a singleton
     *
     * @return a instance the class ValInput
     */
    public static ValInput getValInput() {
        if (valInput == null) {
            valInput = new ValInput();
        }
        return valInput;
    }

    /**
     * this method replace the (") and (') for ($) and (€)
     *
     * @param txt this parameter receives a String it will modified
     * @return a String it replace (") and (') for $ and €
     */
    public String withoutQuotes(String txt) {
        return txt.replace((char) 34, '$').replace((char) 39, '€');
    }

    /**
     *
     * this method does not allow you to paste
     *
     * @param evt receives a KeyEvent
     */
    public void noCtrlV(KeyEvent evt) {
        if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_V) {
            evt.consume();
        }
    }

    /**
     * this method does not allow you to copy
     *
     * @param evt receives a KeyEvent
     */
    public void noCtrlC(KeyEvent evt) {
        if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_C) {
            evt.consume();
        }
    }

    /**
     * this method does not allow you to cut
     *
     * @param evt receives a KeyEvent
     */
    public void noCtrlX(KeyEvent evt) {
        if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_X) {
            evt.consume();
        }
    }

    /**
     * this method does not allow writes of number
     *
     * @param evt receives a KeyEvent
     */
    public void noNumbre(KeyEvent evt) {
        aloneTxt(evt, Constants.NUMBERS, true);
    }

    /**
     * this method alone allow write letters
     *
     * @param evt receives a KeyEvent
     */
    public void letter(KeyEvent evt) {
        aloneTxt(evt, Constants.LETTERS, false);
    }

    /**
     * this method alone allow writes special character
     *
     * @param evt receives a KeyEvent
     */
    public void specialCharacter(KeyEvent evt) {
        aloneTxt(evt, Constants.WITHOUT_SPECIAL_CHARACTERS, true);
    }

    /**
     * this method alone allow write integers numbers
     *
     * @param evt receives a KeyEvent
     */
    public void numberInt(KeyEvent evt) {
        aloneTxt(evt, Constants.NUMBERS, false);
    }

    /**
     * this method alone allow write decimals numbers
     *
     * @param evt receives a KeyEvent
     * @param txt receives the text is writing
     */
    public void numberFloat(KeyEvent evt,String txt) {
        oneSepatator(evt, txt+evt.getKeyChar(), '.');
        aloneTxt(evt, Constants.NUMBERS + ".", false);
    }

    /**
     * this method allow all type number but need the decimal separator
     *
     * @param evt receives a KeyEvent
     * @param sep receives the separator
     * @param txt receives the text is writing
     */
    public void numberFloat(KeyEvent evt, char sep,String txt) {
        oneSepatator(evt,txt+evt.getKeyChar(),sep);
        aloneTxt(evt, Constants.NUMBERS + sep, false);
    }
    
    private void oneSepatator(KeyEvent evt, String txt, char sep) {
        int countSep=0;
        for (int i = 0; i < txt.length(); i++) {
            if(txt.charAt(i)==sep){
                countSep++;
            }
        }
        System.out.println("countSep = " + countSep);
        if(countSep>1){
            evt.consume();
        }
    }
    
    /**
     * this method allow or not the KeyEvent.
     *
     * @param evt receives a KeyEvent
     * @param notxt receives the characters that aren't going to use
     * @param difequ if this parameter is true the decision will be taken when
     * equal, but it will be different when false
     */
    public void aloneTxt(KeyEvent evt, String notxt, boolean difequ) {
        if (notxt.contains(String.valueOf(evt.getKeyChar())) == difequ) {
            evt.consume();
        }
    }

    /**
     * this method limit the length of a text field
     *
     * @param evt receives a KeyEvent
     * @param lenght receives the length of the text
     * @param maxlenght receives the max length
     */
    public void limitLength(KeyEvent evt, int lenght, int maxlenght) {
        if (lenght > maxlenght - 1) {
            evt.consume();
        }
    }

    /**
     * this method focuses a text field and selected his contained
     *
     * @param nexttxt receives any component that extend JTextComponent
     */
    public void nextText(JTextComponent nexttxt) {
        nexttxt.requestFocus();
        nexttxt.setSelectionStart(0);

    }

    /**
     * this method focuses a text field
     *
     * @param nextc receives any component that extend JComponent
     */
    public void nextText(JComponent nextc) {
        nextc.requestFocus();
    }

    /**
     * this method makes click on button
     *
     * @param nextb receives a JButton
     */
    public void pressButton(JButton nextb) {
        nextb.doClick();
    }

    /**
     * this method makes click on button when pressed enter
     *
     * @param evt receives a KeyEvent
     * @param nextbutton receives a JButton
     */
    public void enterButton(KeyEvent evt, JButton nextbutton) {
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            pressButton(nextbutton);
        }
    }

    /**
     * this method focuses a text field and selected his contained when pressed
     * enter
     *
     * @param evt receives a KeyEvent
     * @param nexttxt receives any component that extend JTextComponent
     */
    public void enterNextText(KeyEvent evt, JTextComponent nexttxt) {
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            nextText(nexttxt);
        }
    }

    /**
     * this method focuses a JComponent when pressed enter
     *
     * @param evt receives a KeyEvent
     * @param nextc receives any component that extend JComponent
     */
    public void enterNextComponent(KeyEvent evt, JComponent nextc) {
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            nextc.requestFocus();
        }
    }

    /**
     * this method focuses a JTextComponent when pressed down
     *
     * @param evt receives a KeyEvent
     * @param nexttxt receives any component that extend JTextComponent
     */
    public void downNextText(KeyEvent evt, JTextComponent nexttxt) {
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            nextText(nexttxt);
        }
    }

    /**
     * this method focuses a JComponent when pressed down
     *
     * @param evt receives a KeyEvent
     * @param nextc receives any component that extend JComponent
     */
    public void downNextComponent(KeyEvent evt, JComponent nextc) {
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            nextc.requestFocus();
        }
    }

    /**
     * this method focuses a JButton when pressed down
     *
     * @param evt receives a KeyEvent
     * @param nextb receives any component that extend JButton
     */
    public void downNextButton(KeyEvent evt, JButton nextb) {
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            nextb.requestFocus();
        }
    }

    /**
     * this method focuses a JTextComponent when pressed up
     *
     * @param evt receives a KeyEvent
     * @param nexttxt receives any component that extend JTextComponent
     */
    public void upNextText(KeyEvent evt, JTextComponent nexttxt) {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            nextText(nexttxt);
        }
    }

    /**
     * this method focuses a JComponent when pressed up
     *
     * @param evt receives a KeyEvent
     * @param nextc receives any component that extend JComponent
     */
    public void upNextComponent(KeyEvent evt, JComponent nextc) {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            nextc.requestFocus();
        }
    }

    /**
     * this method focuses a JButton when pressed up
     *
     * @param evt receives a KeyEvent
     * @param nextb receives any component that extend JButton
     */
    public void upNextButton(KeyEvent evt, JButton nextb) {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            nextb.requestFocus();
        }
    }


}
