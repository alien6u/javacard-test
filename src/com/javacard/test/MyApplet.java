package com.javacard.test;

/**
 * Copyright (c) 1998, 2015, Oracle and/or its affiliates. All rights reserved.
 * 
 */

/*
 */

// /*
// Workfile:@(#)HelloWorld.java	1.7
// Version:1.7
// Date:01/03/06
//
// Archive:  /Products/Europa/samples/com/sun/javacard/samples/HelloWorld/HelloWorld.java
// Modified:01/03/06 12:13:08
// Original author:  Mitch Butler
// */
import javacard.framework.APDU;
import javacard.framework.Applet;
import javacard.framework.ISO7816;
import javacard.framework.ISOException;
import javacard.framework.Util;

/**
 */

public class MyApplet extends Applet {
    private byte[] echoBytes;
    private static final short LENGTH_ECHO_BYTES = 256;

    /**
     * Only this class's install method should create the applet object.
     */
    protected MyApplet() {
        echoBytes = new byte[LENGTH_ECHO_BYTES];
        register();
    }

    /**
     * Installs this applet.
     *
     * @param bArray
     *            the array containing installation parameters
     * @param bOffset
     *            the starting offset in bArray
     * @param bLength
     *            the length in bytes of the parameter data in bArray
     */
    public static void install(byte[] bArray, short bOffset, byte bLength) {
        new MyApplet();
    }

    /**
     * Processes an incoming APDU.
     *
     * @see APDU
     * @param apdu
     *            the incoming APDU
     * @exception ISOException
     *                with the response bytes per ISO 7816-4
     */
   @Override 
   public void process(APDU apdu) {
        byte buffer[] = apdu.getBuffer();

        // check SELECT APDU command
        if ((buffer[ISO7816.OFFSET_CLA] == 0) &&
                (buffer[ISO7816.OFFSET_INS] == (byte) (0xA4))) {
            return;
        }

        short bytesRead = apdu.setIncomingAndReceive();
        short echoOffset = (short) 0;

        while (bytesRead > 0) {
            Util.arrayCopyNonAtomic(buffer, ISO7816.OFFSET_CDATA, echoBytes, echoOffset, bytesRead);
            echoOffset += bytesRead;
            bytesRead = apdu.receiveBytes(ISO7816.OFFSET_CDATA);
        }

        apdu.setOutgoing();
        apdu.setOutgoingLength((short) (echoOffset + 5));

        // echo header
        apdu.sendBytes((short) 0, (short) 5);
        // echo data
        apdu.sendBytesLong(echoBytes, (short) 0, echoOffset);
    }

}
