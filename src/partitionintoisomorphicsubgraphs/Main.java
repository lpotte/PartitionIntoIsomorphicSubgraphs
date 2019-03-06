package partitionintoisomorphicsubgraphs;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author LUIS POTTE
 */
public class Main {

    public static int fact(int n) {
        int cb = 1;
        for (int j = 2; j <= n; j++) {
            cb = j * cb;
        }
        return cb;
    }

    public static void permutación(String[] elem, String act, int n, int r, ArrayList array) {
        if (n == 0) {
            array.add(act);
        } else {
            for (int i = 0; i < r; i++) {
                if (!act.contains(elem[i])) {
                    permutación(elem, act + elem[i], n - 1, r, array);
                }
            }
        }
    }

    public static void combinacion_3_en_n(String[] elem, ArrayList<String> array) {
        String mn;
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < elem.length; i++) {
            mn = "";
            for (int j = i + 1; j < elem.length; j++) {
                mn = mn + String.valueOf(j);
            }
            temp.add(mn);
        }
        for (int i = 0; i < elem.length; i++) {
            for (int j = 0; j < temp.get(i).length(); j++) {
                if (!temp.get(i).substring(j, j + 1).equals(String.valueOf(i))) {
                    for (int k = j; k < temp.get(i + 1).length(); k++) {
                        if (!temp.get(i + 1).substring(k, k + 1).equals(String.valueOf(j))) {
                            mn = String.valueOf(i) + temp.get(i).substring(j, j + 1) + temp.get(i + 1).substring(k, k + 1);
                            array.add(mn);
                        }
                    }
                }
            }
        }
    }

    public static void combinacion_k_en_n(String[] elem, int len, int inicio, String[] array, ArrayList a) {
        if (len == 0) {
            String nm = "";
            for (String array1 : array) {
                nm = nm + array1;
            }
            a.add(nm);
        } else {
            for (int i = inicio; i <= elem.length - len; i++) {
                array[array.length - len] = elem[i];
                combinacion_k_en_n(elem, len - 1, i + 1, array, a);
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<String> array = new ArrayList<>();
        ArrayList<String> arrayG = new ArrayList<>();
        int G[][] = {
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},};
        int H[][] = {
            {0, 1},
            {1, 0},};
        /*int G[][] = {
            {0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0}
        };
        int H[][] = {
            {0, 1, 0},
            {1, 0, 1},
            {0, 1, 0}
        };*/

        int Vg = G[0].length;
        int Vh = H[0].length;
        String aux, mad = "";
        if (Vg % Vh == 0) {
            String num[] = new String[Vg];
            for (int i = 0; i < Vg; i++) {
                num[i] = String.valueOf(i);
            }
            permutación(num, "", Vh, Vh, array);
            System.out.println(array);
            ArrayList mads = new ArrayList();
            for (int i = 0; i < array.size(); i++) {
                String subm = "";
                for (int j = 0; j < array.get(i).length(); j++) {
                    int temp1 = Integer.parseInt(array.get(i).substring(j, j + 1));
                    for (int k = 0; k < array.get(i).length(); k++) {
                        int temp2 = Integer.parseInt(array.get(i).substring(k, k + 1));
                        subm = subm + H[temp1][temp2];
                    }
                    if (j != array.get(i).length() - 1) {
                        subm = subm + "/";
                    }
                }
                if (!mads.contains(subm)) {
                    mads.add(subm);
                }
            }
            System.out.println(mads);
            //combinacion_3_en_n(num, arrayG);
            //System.out.println(arrayG);
            String[] arrayn = new String[Vh];
            ArrayList<String> subgraphs = new ArrayList<>();
            combinacion_k_en_n(num, Vh, 0, arrayn, subgraphs);
            System.out.println(subgraphs);
            ArrayList madsub = new ArrayList();
            ;
            for (int i = 0; i < subgraphs.size(); i++) {
                String subm = "";
                for (int j = 0; j < subgraphs.get(i).length(); j++) {
                    int temp1 = Integer.parseInt(subgraphs.get(i).substring(j, j + 1));
                    for (int k = 0; k < subgraphs.get(i).length(); k++) {
                        int temp2 = Integer.parseInt(subgraphs.get(i).substring(k, k + 1));
                        subm = subm + G[temp1][temp2];
                    }
                    if (j != subgraphs.get(i).length() - 1) {
                        subm = subm + "/";
                    }
                }
                madsub.add(subm);
            }
            System.out.println(madsub);
            ArrayList<String> Isomorphics = new ArrayList<>();
            for (int i = 0; i < madsub.size(); i++) {
                if (mads.contains(madsub.get(i))) {
                    Isomorphics.add(subgraphs.get(i));
                }
            }
            System.out.println(Isomorphics);
            ArrayList<String> Partition = new ArrayList<>();
            Boolean sw = true;
            int cont = 0;
            String h, h1;
            int l = 0;
            while (l < Isomorphics.size()) {
                for (int i = 0; i < Isomorphics.get(l).length(); i++) {
                    h = Isomorphics.get(l).substring(i, i + 1);
                    for (String Isomorphic1 : Isomorphics) {
                        if (Isomorphic1.contains(h)) {
                            cont++;
                        }
                    }
                    if (cont == 1) {
                        if (!Partition.contains(Isomorphics.get(l))) {
                            Partition.add(Isomorphics.get(l));
                        }
                    }
                    cont = 0;
                }
                if (Partition.contains(Isomorphics.get(l))) {
                    h = Partition.get(Partition.indexOf(Isomorphics.get(l)));
                    for (int i = 0; i < h.length(); i++) {
                        h1 = h.substring(i, i + 1);
                        for (int j = 0; j < Isomorphics.size(); j++) {
                            if (Isomorphics.get(j).contains(h1)) {
                                Isomorphics.remove(Isomorphics.get(j));
                                j = 0;
                            }
                        }
                    }
                    l = 0;
                } else {
                    l++;
                }
            }
            if(!Partition.isEmpty()){
                System.out.println("Las " + Partition.size() + " Particiones de G Isomorfas a H son: " + Partition);
            }else{
                System.out.println("No es posible partitionar G en q subgrafos isomorfos a H");
            }
            
        }else{
            System.out.println("G no se puede particionar en Q subgrafos isomorfos a H");
        }
    }
}
