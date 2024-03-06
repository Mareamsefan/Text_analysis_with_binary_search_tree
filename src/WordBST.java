import java.io.Console;
import java.util.Scanner;

public class WordBST {
    // WordNode: Indre klasse for en node i sÃ¸ketreet
    class WordNode
    {
        /*** oppgave 1 ***/
        String word;
        WordNode right, left = null;
        int counter;

        public WordNode(String word, WordNode right, WordNode left){
            this.word = word;
            this.right = right;
            this.left = left;
            counter ++;
        }

        public String print(){
            return  word + ": " + counter;
        }
    }

    private WordNode rot; // Roten i hele sÃ¸ketreet
    private int n;        // Antall noder i hele treet

    // WordBST(): KonstruktÃ¸r som lager et tomt sÃ¸ketre
    public void WordBST()
    {
        rot = null;
        n = 0;
    }

    // size(): Antall ord som er lagret i treet
    public int size()
    {
        return n;
    }


    /*** oppgave 2 ***/
    private void insert(String ord) {
        if (rot == null) {
            rot = new WordNode(ord, null, null);
            n++;
            return;
        }
        WordNode current = rot;
        while (true) {
            int compareResult = ord.compareTo(current.word);

            if (compareResult == 0) {
                current.counter++;

                return;
            } else if (compareResult < 0) {
                if (current.left == null) {
                    current.left = new WordNode(ord, null, null);
                    n++;
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new WordNode(ord, null, null);
                    n++;
                    return;
                }
                current = current.right;
            }
        }
    }



    // search(): SÃ¸k etter et ord. Skriv ut ordet og ordfrekvensen
    // hvis det finnes i sÃ¸ketreet.
    public void search(String ord)
    {
        /*** oppgave 3 ***/
        WordNode current = rot;
        while (current != null){
            int compareResult = ord.compareTo(current.word);
            if(compareResult == 0){
                System.out.println(current.print());
                return;
            }
            if(compareResult < 0){
                current = current.left;
            }
            else current = current.right;
        }
        System.out.println("Word not found");
    }

    // print(): Alfabetisk utskrift av hele sÃ¸ketreet. Kaller en
    // rekursiv metode som gjÃ¸r selve utskriften.
    public void print()
    {
        print(rot);
    }

    // print(): Rekursiv utskrift av hele sÃ¸ketreet med rot i "rot"
    private void print(WordNode rot)
    {
        /*** oppgave 4 ***/
        if(rot != null){
            print(rot.left);
            System.out.println(rot.print());
            print(rot.right);
        }
    }


    // main(): Testprogram
    public static void main (String argv[])
    {
        // Leser filnavn fra bruker
        Scanner scan = new Scanner(System.in);
        System.out.print("File? ");
        String fileName = scan.next();

        // Oppretter ordleser og tomt sÃ¸ketre
        WordReader wR = new WordReader(fileName);
        WordBST wBST = new WordBST();

        // Leser alle ordene pÃ¥ filen og legger inn i treet
        String ord = wR.nextWord();
        while (ord != null)
        {
            wBST.insert(ord);
            ord = wR.nextWord();
        }
        // Skriver ut antall ulike ord som fantes i filen
        System.out.println(wBST.size() + " unique words " +
                "read from file " + fileName);

        // Menyvalg for Ã¥ teste programmet
        int valg = 0;
        while(valg != 3)
        {
            System.out.print("\n1:Search, 2:Print, 3:Quit ? ");
            valg = scan.nextInt();
            if (valg == 1)
            {
                System.out.print("Search for? ");
                ord = scan.next();
                wBST.search(ord.toLowerCase());
            }
            else if (valg == 2)
                wBST.print();
        }
    }
}
