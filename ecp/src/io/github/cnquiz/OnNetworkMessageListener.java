package io.github.cnquiz;

/**
 * Interface for protocol related communications, namely when a relevant message is received.
 */
public interface OnNetworkMessageListener {

    /**
     *  "TQR" string received.
     *
     * @param sender event sender
     * @param e event arguments
     */
     void onUserListRequest(Object sender, EventArgs e);
    /**
     *  "TER Tn" string received, where Tn is a topic number.
     *
     * @param sender event sender
     * @param e event arguments
     */
     void onUserTopicRequest(Object sender, EventArgs e);

    void onError(Object sender, EventArgs e);
}
