public class TypingRace
{
    public static void main(String[] args)
    {
        Typist t1 = new Typist('A', "Alice", 0.80);
        Typist t2 = new Typist('B', "Bob", 0.70);
        Typist t3 = new Typist('C', "Charlie", 0.90);

        int raceLength = 20;
        boolean raceFinished = false;

        while (!raceFinished)
        {
            takeTurn(t1);
            takeTurn(t2);
            takeTurn(t3);
        
            printRace(t1, t2, t3, raceLength);

            if (t1.getProgress() >= raceLength)
            {
                System.out.println(t1.getName() + " wins!");
                raceFinished = true;
            }
            else if (t2.getProgress() >= raceLength)
            {
                System.out.println(t2.getName() + " wins!");
                raceFinished = true;
            }
            else if (t3.getProgress() >= raceLength)
            {
                System.out.println(t3.getName() + " wins!");
                raceFinished = true;
            }
            try { Thread.sleep(500); } catch (Exception e) {}
        }
    }

    public static void takeTurn(Typist t)
    {
        if (t.isBurntOut())
        {
        t.recoverFromBurnout();
            return;
    }

    if (Math.random() < 0.05)
    {
        t.burnOut(2);
        return;
    }
    if (Math.random() < t.getAccuracy())
    {
        t.typeCharacter();
    }
    else
    {
        t.slideBack(1);
    }
}

public static void printRace(Typist t1, Typist t2, Typist t3, int raceLength)
    {
        printTypist(t1);
        printTypist(t2);
        printTypist(t3);
        System.out.println();
    }
    public static void printTypist(Typist t)
    {
        System.out.print(t.getSymbol());

        for(int i=0; i<t.getProgress(); i++)
        {
            System.out.print("-");
        }
        System.out.print("|");
    }

}