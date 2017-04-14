package com.lucadev.mcprotocol.game.nbt.tags;

import java.util.List;

/**
 * Class that can be used by types that contain a list of types
 * @author Luca Camphuisen < Luca.Camphuisen@hva.nl >
 */
public abstract class CollectionTag<T extends Tag> extends Tag<List<T>> {

    public static final String COLLECTION_OPEN = "{";
    public static final String COLLECTION_CLOSE = "}";
    public static final String ENTRY_SPACING = "   ";//spaces for all stuff inside.

    public CollectionTag(int id, String type) {
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
        if(getName() != null && !getName().isEmpty()) {
            sb.append("'");
            sb.append(getName());
            sb.append("'");
        } else {
            sb.append("None");
        }
        sb.append("): ");
        sb.append(getPayload().size());
        sb.append(" entries");
        sb.append(NEWLINE);
        sb.append(COLLECTION_OPEN);
        sb.append(NEWLINE);
        for (T t : getPayload()) {
            sb.append(ENTRY_SPACING);
            sb.append(t.toString().replaceAll(NEWLINE, NEWLINE + ENTRY_SPACING));
            sb.append(NEWLINE);
        }
        sb.append(COLLECTION_CLOSE);
        sb.append(NEWLINE);
        return sb.toString();
    }

    /**
     * Amount of entries.
     * @return
     */
    public int getSize() {
        return getPayload().size();
    }

    public void add(T tag) {
        getPayload().add(tag);
    }

    public void remove(T tag) {
        getPayload().remove(tag);
    }
}
