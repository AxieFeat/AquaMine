package net.aquamine.server.shapes.merger;

import it.unimi.dsi.fastutil.doubles.AbstractDoubleList;

/*
 * This hacky class allows us to keep size being a method on IndexMerger and avoids us running in to conflicts when implementing
 * NonOverlappingMerger.
 *
 * The problem is that Kotlin makes fake overrides of java's collection methods, specifically the size method becomes a size property,
 * which conflicts with our size method on IndexMerger.
 */
public abstract class AbstractDoubleListIndexMerger extends AbstractDoubleList implements IndexMerger {

    @Override
    public abstract int size();
}
