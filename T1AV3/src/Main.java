import java.util.*;
import java.io.*;



public class Main {
    



    public static void main(String[] args) throws Exception {

        BufferedReader br;

        // Se existir arquivo, lê do arquivo
        File file = new File("dados/entrada.txt");

        if (file.exists()) {
            br = new BufferedReader(new FileReader(file));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int t = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();

        for (int caseNum = 0; caseNum < t; caseNum++) {

            int n = Integer.parseInt(br.readLine().trim());

            double[][] points = new double[n][2];

            for (int i = 0; i < n; i++) {

                String[] parts = br.readLine().trim().split("\\s+");

                points[i][0] = Double.parseDouble(parts[0]);
                points[i][1] = Double.parseDouble(parts[1]);
            }

            double answer = PrimMST.prim(points);

            sb.append(String.format(Locale.US, "%.12f\n", answer));
        }

        System.out.print(sb);
    }
}




