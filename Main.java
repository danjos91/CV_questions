import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = reader.readLine();
        int n = s.length();

        // M = mayor frecuencia de una sola letra.
        // Ninguna subcadena puede tener mas de M apariciones no solapadas.
        int[] frequency = new int[26];
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';
            frequency[index]++;
            maxCount = Math.max(maxCount, frequency[index]);
        }

        // Guardamos las posiciones de cada letra.
        ArrayList<ArrayList<Integer>> pos = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            pos.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';
            pos.get(index).add(i);
        }

        int answer = 1;
        for (int i = 0; i < 26; i++) {
            // Solo probamos letras que alcanzan la frecuencia maxima M.
            if (frequency[i] == maxCount) {
                answer = Math.max(answer, getBestLength(s, pos.get(i)));
            }
        }

        writer.write(String.valueOf(answer));

        reader.close();
        writer.close();
    }

    static int getBestLength(String s, ArrayList<Integer> pos) {
        int occur = pos.size();
        int n = s.length();

        // La ultima ocurrencia no puede salirse del string.
        int limit = n - pos.get(occur - 1);

        // Para que no haya interseccion, la longitud no puede superar
        // la distancia entre dos comienzos consecutivos.
        for (int i = 1; i < occur; i++) {
            limit = Math.min(limit, pos.get(i) - pos.get(i - 1));
        }

        int len = 0;
        while (len < limit) {
            // Comparamos el caracter en offset "len" en todas las ocurrencias.
            char expected = s.charAt(pos.get(0) + len);
            boolean allEqual = true;

            for (int i = 1; i < occur; i++) {
                if (s.charAt(pos.get(i) + len) != expected) {
                    allEqual = false;
                    break;
                }
            }

            if (!allEqual) {
                break;
            }

            // Si todos coinciden, podemos extender la subcadena un caracter mas.
            len++;
        }

        return len;
    }
}