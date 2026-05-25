import java.util.*;

public class PrimMST {

    


    public static double prim(double[][] points) {

        int n = points.length;

        boolean[] visited = new boolean[n];

        double[] minDist = new double[n];

        Arrays.fill(minDist, Double.POSITIVE_INFINITY);

        minDist[0] = 0.0;

        double total = 0.0;

        for (int i = 0; i < n; i++) {

            int u = -1;

            double best = Double.POSITIVE_INFINITY;

            // Escolhe próximo vértice
            for (int j = 0; j < n; j++) {

                if (!visited[j] && minDist[j] < best) {

                    best = minDist[j];
                    u = j;
                }
            }

            visited[u] = true;

            total += best;

            // Atualiza distâncias
            for (int v = 0; v < n; v++) {

                if (!visited[v]) {

                    double d = dist(points[u], points[v]);

                    if (d < minDist[v]) {
                        minDist[v] = d;
                    }
                }
            }
        }

        return total;
    }

    private static double dist(double[] a, double[] b) {

        double dx = a[0] - b[0];
        double dy = a[1] - b[1];

        return Math.sqrt(dx * dx + dy * dy);
    }
}


