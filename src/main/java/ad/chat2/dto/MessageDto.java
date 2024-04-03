package ad.chat2.dto;

public class MessageDto {

    String message;
    String nick;
    Long idConversation;

    public MessageDto() {
    }

    public MessageDto(String message, String nick, Long idConversation) {
        this.message = message;
        this.nick = nick;
        this.idConversation = idConversation;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Long getIdConversation() {
        return this.idConversation;
    }

    public void setIdConversation(Long idConversation) {
        this.idConversation = idConversation;
    }

}
