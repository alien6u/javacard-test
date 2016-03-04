/** 
 * Copyright (c) 1998, 2015, Oracle and/or its affiliates. All rights reserved.
 * 
 */

/*
 */

package com.sun.jcclassic.samples.rmi;

import java.rmi.Remote;

import javacard.framework.APDU;
import javacard.framework.ISOException;
import javacard.framework.service.Dispatcher;
import javacard.framework.service.RMIService;
import javacard.framework.service.RemoteService;

/**
 * 
 * @author vo113324
 */
public class PurseApplet extends javacard.framework.Applet {

    private Dispatcher disp;
    private RemoteService serv;
    private Remote purse;

    public PurseApplet() {
        purse = new PurseImpl();

        disp = new Dispatcher((short) 1);
        serv = new RMIService(purse);
        disp.addService(serv, Dispatcher.PROCESS_COMMAND);

        register();
    }

    public static void install(byte[] aid, short s, byte b) {
        new PurseApplet();
    }

    @Override
    public void process(APDU apdu) throws ISOException {

        disp.process(apdu);

    }

}
