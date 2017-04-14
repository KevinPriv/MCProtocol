package com.lucadev.mcprotocol.game.nbt;

import java.io.IOException;

/**
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class NBTException extends IOException {

    public NBTException() {
    }

    public NBTException(String s) {
        super(s);
    }

    public NBTException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NBTException(Throwable throwable) {
        super(throwable);
    }
}
