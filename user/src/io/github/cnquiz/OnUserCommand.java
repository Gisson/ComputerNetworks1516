package io.github.cnquiz;

/**
 * Contains commands that can be sent to the user.
 */
public interface OnUserCommand {
    void onListCommand(Object sender, EventArgs e);
    void onRequestCommand(Object sender, EventArgs e);
    void onSubmitCommand(Object sender, EventArgs e);
    void onExitCommand(Object sender, EventArgs e);
}
