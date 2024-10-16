package myProj;

import java.util.ArrayList;

public class Main {

    public  static String[][] lab;
    public  static int numR = 10;
    public static int numC  = 10;
    public static int[] ultimoBivio = new int[2];

    public static void main(String[] args){



        lab = new String[numR][numC];

        lab[0] =  new String[] {"S",".",".",".","#","#","#","#","#","#"};
        lab[1] =  new String[] {".","#","#",".","#",".",".",".","E",".","."};
        lab[2] =  new String[] {".","#","#",".","#",".","#","#","#","#"};
        lab [3] = new String[] {".",".",".","#",".",".",".",".",".","."};
        lab[4] =  new String[] {".","#","#",".","#","#","#","#","#","."};
        lab[5] = new String[] {".",".","#","#","#",".","#","#","#","."};
        lab[6] = new String[]  {".",".",".","#","#","#","#",".",".","."};
        lab [7] = new String[] {".",".",".",".",".","#",".",".",".","."};
        lab[8] = new String[] {"#","#","#","#",".",".","#","#","#","."};
        lab[9] = new String[]{".",".",".",".",".",".",".",".",".",".","."};


        stampaLab();

       System.out.println(run());

    }

    public static void stampaLab(){
        for(int i=0;i<numR;i++){
            for(int j=0;j<numC ;j++){
                System.out.print(lab[i][j]+" ");
            }
            System.out.println("\n");
        }
    }

    public static void  isCross(int r, int c){
            //bivio = due strade possibili
        boolean flag = false;

            //correggere uno dei due può essere = E

            if(c+1 < numC && r+1 <numR) {
                if(lab[r][c+1].compareTo("E") == 0 && lab[r+1][c].compareTo(".") == 0) {
                    flag     = true;
                }else if(lab[r][c+1].compareTo(".") == 0 && lab[r+1][c].compareTo("E") == 0){
                    flag = true;
                }
                if (lab[r][c + 1].compareTo(".") == 0 && lab[r + 1][c].compareTo(".") == 0 ) {
                    //posso andare sia dx che giu
                    flag = true;
                }
            }

            if(c+1 < numC && r-1 >= 0) {
                if(lab[r][c+1].compareTo("E") == 0 && lab[r-1][c].compareTo(".") == 0) {
                    flag     = true;
                }else if(lab[r][c+1].compareTo(".") == 0 && lab[r-1][c].compareTo("E") == 0){
                    flag = true;
                }
                if (lab[r][c + 1].compareTo(".") == 0 && lab[r - 1][c].compareTo(".") == 0 ) {
                    //posso andare sia dx che su
                    flag    = true;
                }
            }

            if(c-1 >= 0 && r+1 <numR) {
                if(lab[r][c-1].compareTo("E") == 0 && lab[r+1][c].compareTo(".") == 0) {
                    flag     = true;
                }else if(lab[r][c-1].compareTo(".") == 0 && lab[r+1][c].compareTo("E") == 0){
                    flag = true;
                }
                if (lab[r][c - 1].compareTo(".") == 0 && lab[r + 1][c].compareTo(".") == 0 ) {
                    //sx o giu
                    flag = true;

                }
            }

            if(c-1 >= 0 && r-1 >= 0) {
                if(lab[r][c-1].trim().compareTo("E") == 0 && lab[r-1][c].compareTo(".") == 0) {
                    flag     = true;
                }else if(lab[r][c-1].trim().compareTo(".") == 0 && lab[r-1][c].compareTo("E") == 0){
                    flag = true;
                }
                if (lab[r][c - 1].compareTo(".") == 0 && lab[r - 1][c].compareTo(".") == 0 ) {
                    //posso andare sia sx che su
                    flag = true;
                }
            }
            if(flag){
                ultimoBivio[0] = r;
                ultimoBivio[1] = c;
            }
    }

    public static  ArrayList<String> run (){
        boolean flag = true;
        int r = 0;
        int c = 0;
        ArrayList <String> comandi = new ArrayList <String> ();



        while(flag) {

            stampaLab();

            //controllo se posso andare a dx
            if (c + 1 < numC && lab[r][c + 1].compareTo("#") != 0 && lab[r][c + 1].compareTo("0") != 0) {
                //vado a dx

                isCross(r,c);

                //controllo che non sia la fine
                if(lab[r][c+1].compareTo("E") == 0){
                    flag = false;
                }else{
                    lab[r][c + 1] = "0"; //segno lo spostamento
                }
                c++; //mi sposto

                comandi.add("dx");



            } else {
                //non posso andare a dx

                //controllo se posso andare sotto
                if (r + 1 < numR && lab[r + 1][c].compareTo("#") != 0 && lab[r + 1][c].compareTo("0") != 0) {
                    //vado sotto

                    isCross(r,c);


                    if(lab[r+1][c].compareTo("E") == 0){
                        flag = false;
                    }else{
                        lab[r+1][c] = "0"; //segno lo spostamento
                    }
                    r++;
                    comandi.add("down");

                } else {
                    //non posso andare sotto ne a dx

                    //controllo se posso andare sopra
                    if (r != 0 && lab[r - 1][c].compareTo("#") != 0 && lab[r - 1][c].compareTo("0") != 0) {
                        //vado sopra

                        isCross(r,c);

                        if(lab[r-1][c].compareTo("E") == 0){
                            flag = false;
                        }else{
                            lab[r-1][c] = "0"; //segno lo spostamento
                        }
                        r--;
                        comandi.add("up");

                    } else {
                        //non posso andare sotto, sopra o dx

                        //provo ad andare a sx
                        if (c != 0 && lab[r][c - 1].compareTo("#") != 0 && lab[r][c - 1].compareTo("0") != 0) {
                            //vado a sx

                            isCross(r,c);

                            if(lab[r][c-1].compareTo("E") == 0){
                                flag = false;
                            }else{
                                lab[r][c -1] = "0"; //segno lo spostamento
                            }
                            lab[r][c - 1] = "0";
                            c--;
                            comandi.add("left");
                        } else {
                            //non posso andare sopra sotto dx o sx
                            if(lab[r][c].equals("E") == false) {
                                //leggo l'ultima operazione e torno indietro
                                while(r != ultimoBivio[0] || c != ultimoBivio[1]){

                                    switch (comandi.removeLast()) {
                                        case "up":
                                            //torno in giu
                                            r++;
                                            break;
                                        case "sx":
                                            //torno a dx
                                            c++;

                                            break;
                                        case "down":
                                            //torno in su
                                            r--;
                                            break;
                                        case "dx":
                                            //torno a sx
                                            c--;
                                            break;

                                    }
                            }   }
                        }
                    }
                }
            }
        }
        return comandi;
    }

}
