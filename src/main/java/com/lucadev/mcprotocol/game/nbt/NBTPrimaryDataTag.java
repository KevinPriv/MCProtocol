package com.lucadev.mcprotocol.game.nbt;

/**
 * Tags that have a primary java datatype as payload may extend to this class instead of the NBTTag class.
 * String payload also works.
 *
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class NBTPrimaryDataTag<T> extends NBTTag<T> {

    public NBTPrimaryDataTag(int id, String type) {
        super(id, type);
    }

    /**
     * Get String representation of the NBT tag
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getType());
        sb.append("(");
        if (getName() != null && !getName().isEmpty()) {
            sb.append("'");
            sb.append(getName());
            sb.append("'");
        } else {
            sb.append("None");
        }
        sb.append("): ");
        if (getPayload() instanceof String) {
            sb.append("'");
            sb.append(getPayload());
            sb.append("'");
        } else {
            sb.append(String.valueOf(getPayload()));
        }
        return sb.toString();
    }
}
