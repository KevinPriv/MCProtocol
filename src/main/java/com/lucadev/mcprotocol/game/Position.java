package com.lucadev.mcprotocol.game;

/**
 * http://wiki.vg/Protocol#Position
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public class Position {

    private long x;
    private long y;
    private long z;

    public Position(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position(long val) {
        x = val >> 38;
        y = (val >> 26) & 0xFFF;
        z = val << 38 >> 38;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    public long encode() {
        return ((x & 0x3FFFFFF) << 38) | ((y & 0xFFF) << 26) | (z & 0x3FFFFFF);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        if (y != position.y) return false;
        return z == position.z;

    }

    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + (int) (y ^ (y >>> 32));
        result = 31 * result + (int) (z ^ (z >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
