package _03objects.P8_16;

public class Drivemessage {
    public static void main(String[] args) {

        Mailbox myBox = new Mailbox();

        Message m1 = new Message("Mentor", "Waiwai");
        m1.append("Hope this message finds you well.");
        m1.append("I've been enjoying U Chicago.");
        myBox.addMessage(m1);
        //System.out.println(m1.toString());

        Message m2 = new Message("Muhammad Ali", "James Brown");
        m2.append("Hello from Buffalo.");
        myBox.addMessage(m2);
        //System.out.println(m2.toString());

        System.out.println(myBox.getMessage(1).toString());
        myBox.removeMessage(1);
        System.out.println(myBox.getMessage(0).toString());



    }
}
