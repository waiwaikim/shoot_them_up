package _08final_raster.mvc.model;

public class Bullet1 extends Sprite {


    public Bullet1(P38 p38){
        super(p38.getCenter().x, p38.getCenter().y);
        setTeam(Team.FRIEND);

    }
}
