package ca.mydemo.tcoffice.domain;

import java.util.List;

public class MessageInfo {
    private int unreadCnt;
    private boolean atLeastOneForceMess;
    private List<Message> messages;

    public int getUnreadCnt() {
        return unreadCnt;
    }

    public void setUnreadCnt(int unreadCnt) {
        this.unreadCnt = unreadCnt;
    }

    public boolean isAtLeastOneForceMess() {
        return atLeastOneForceMess;
    }

    public void setAtLeastOneForceMess(boolean atLeastOneForceMess) {
        this.atLeastOneForceMess = atLeastOneForceMess;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
