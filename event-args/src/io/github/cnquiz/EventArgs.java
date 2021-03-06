package io.github.cnquiz;

/**
 * Represents the base class for classes that contain event data, and provides a value to use for events that do not
 * include event data.
 */
public class EventArgs {
    /**
     * Provides a value to use with events that do not have event data.
     */
    public static EventArgs EMPTY = new EventArgs();
}
