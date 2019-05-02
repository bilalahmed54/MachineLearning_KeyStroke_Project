package com.itu;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Keystroke implements NativeKeyListener {

    private int count;

    private static Logger log = Logger.getLogger(Keystroke.class.getName());

    public static void main(String[] args) {

        // Clear previous logging configurations.
        LogManager.getLogManager().reset();

        log.setLevel(Level.WARNING);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new Keystroke());

    }

    public void nativeKeyPressed(NativeKeyEvent e) {

        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()) + " Time: " + System.currentTimeMillis());

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
//        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {

    }

}