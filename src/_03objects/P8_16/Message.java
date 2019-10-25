package _03objects.P8_16;

public class Message {

    private String recipient, sender, text;

    public Message(){
    }

    public Message(String r, String s){
        this.recipient = r;
        this.sender = s;
        this.text = "";
    }
    public void append(String t){
        text += t + "\n";
    }

    public String toString(){
        return "From: "+this.sender+ "\n" +"To: " + this.recipient +"\n" + this.text;
    }
}
