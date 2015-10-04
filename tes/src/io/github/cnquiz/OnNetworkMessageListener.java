package io.github.cnquiz;

/**
 * Interface for TES-supported network requests.
 */
public interface OnNetworkMessageListener {

    /** RQT SID
     * called when the user is asking for a questionnaire
     */
    void onRequestQuestionnaireTopic( Object sender, EventArgs args );

    // TODO

}
