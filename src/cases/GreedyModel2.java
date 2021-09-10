package cases;

public class GreedyModel2 {
    public static void main(String[] args) {
        int[] stops = {0, 200, 375, 550, 750, 950};
        System.out.println(minStops(stops, 400));
    }

    public static int minStops(int[] stops, int capacity) {
        int result = 0;
        int currentStop = 0;
        while (currentStop < stops.length - 1) {
            int nextStop = currentStop;
            while (nextStop < stops.length - 1 && stops[nextStop + 1] - stops[currentStop] <= capacity) {
                nextStop++;
            }
            if (currentStop == nextStop) return -1;

            if (nextStop < stops.length - 1) result++;

            currentStop = nextStop;

        }
        return result;
    }
}
