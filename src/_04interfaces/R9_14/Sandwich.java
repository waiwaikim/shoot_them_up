package _04interfaces.R9_14;

public class Sandwich implements Edible {

    private int calory;
    private String type;

    public Sandwich(){
    }

    public Sandwich(int c, String t){
        this.calory = c;
        this.type = t;
    }

    @Override
    public String howToEast() {
        if(this.type == "sub"){
            return "just eat it with your hands";
        }
        else if (this.type == "open"){
            return "use fork and knife";
        }
        else{
            return "figure it out";
        }
    }
}
