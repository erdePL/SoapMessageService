package webService;

import database.DatabaseClass;
import model.Message;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebService(endpointInterface = "webService.MessageService")
public class MessageServiceImpl implements MessageService {
    private Map<Long, Message> messages = DatabaseClass.getMessages();
    public MessageServiceImpl(){

    }
    @Override
    public List<Message> getAllMessages(){
        return new ArrayList<>(messages.values());
    }
    @Override
    public Message getMessage(long id){
        return messages.get(id);
    }
    @Override
    public Message addMessage(Message message){
        if(message.getAuthor()!=null && message.getMessageContent()!=null) {
            message.setId(messages.size() + 1);
            message.setCreationDate(new Date());
            messages.put(message.getId(), message);
            return messages.get(message.getId());
        }
        else
            return null;
    }
    @Override
    public Message updateMessage(Message message){
        if(message.getAuthor()!=null && message.getMessageContent()!=null && messages.get(message.getId())!=null) {
            message.setCreationDate(    messages.get( message.getId() ).getCreationDate() );
            messages.put(message.getId(), message);
            return messages.get(message.getId());
        }else
            return null;
    }
    @Override
    public Message removeMessage(long id){
        return  messages.remove(id);
    }
    @Override
    public String removeAllMessages(){
        messages.clear();
        if(messages.isEmpty())
            return "All messages have been succesfully removed";
        else
            return "Failed to remove alll messages";
    }
}