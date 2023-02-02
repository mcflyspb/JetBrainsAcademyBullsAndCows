import java.util.*;

public class Main {
    public static void main(String[] args) {
        int minSeed;
        int minMax;
        int currentMinMax;
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int count = b - a;

        int[] seed = new int[count + 1];
        int[] max = new int[count + 1];

        for (int x = 0; x <= count; x++) {
            seed[x] = a + x;
            max[x] = generateMaxRandom(a + x, n, k);
            //System.out.println(x);
            //System.out.println(max[x]);
        }

        minSeed = 0;
        currentMinMax = max[minSeed];

        for (int y = 1; y <= count; y++) {
            //System.out.println(count + y + " " + max[y]);
            if (max[y] < currentMinMax) {
                currentMinMax = max[y];
                minSeed = seed[y];
            }
        }
        //System.out.println(max[279]);
        //System.out.println(max[280]);

        System.out.println(minSeed);
        System.out.println(currentMinMax);
    }

    private static int generateMaxRandom(int i, int n, int k) {
        Random random = new Random(i);
        int currentMax = 0;
        int randomNumber = 0;
        for (int x = 0; x < n; x++) {
            randomNumber = random.nextInt(k);
            //System.out.println(randomNumber);
            if (randomNumber > currentMax) {
                currentMax = randomNumber;
            }
        }
        return currentMax;
    }
}