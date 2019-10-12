package _01control;

public class E2_20 {
    //Ascii graphics - x-mas tree
    public static void main(String[] args) {

        for (int i=0; i<4; i++ ){
            for(int j=0; j<4-i; j++){
                System.out.print(" ");
            }
            System.out.print("/");
            for(int k=0; k<2*i; k++){
                System.out.print(" ");
            }
            System.out.print("\\");
            for(int l=0; l<4-i; l++){
                System.out.print(" ");
            }
            System.out.print("\n");
        }

        System.out.println(" -------- ");
        for (int i=0; i<3; i++){
            System.out.println("   \"  \"    ");
        }

    }
}
