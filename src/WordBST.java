import java.io.Console;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;
public class WordBST {

/*** Oppdaterte versjon av WordBST som benytter heller javas bibliotek for TreeMap-datastrukturen***/
    Map<String, Integer> T;

    public WordBST()
    {
       T = new TreeMap<>();
    }

    public int size()
    {
       return T.size();
    }


    /*** oppgave 2 ***/
    private void insert(String ord) {
        T.put(ord, T.getOrDefault(ord, 0) + 1);
    }



    public void search(String ord)
    {
        /*** oppgave 3 ***/
      if(T.containsKey(ord)){
          System.out.println(ord + " :" + T.get(ord));
      }
      else {
          System.out.println("The tree does not contain the word");
      }

    }

    public void print() {
        for (Map.Entry<String, Integer> entry : T.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }


    // main(): Testprogram
    public static void main (String argv[])
    {

        Scanner scan = new Scanner(System.in);
        System.out.print("File? ");
        String fileName = scan.next();


        WordReader wR = new WordReader(fileName);
        WordBST wBST = new WordBST();

        String ord = wR.nextWord();
        while (ord != null)
        {
            wBST.insert(ord);
            ord = wR.nextWord();
        }

        System.out.println(wBST.size() + " unique words " +
                "read from file " + fileName);

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
