import java.util.LinkedList;

// A classe Mergesort a ser completada
class Mergesort {

    static void split(LinkedList<Integer> l, LinkedList<Integer> l1, LinkedList<Integer> l2) {
        // a ser completada
        int tamanho = l.size();
        int meio = (l.size())/2;
        
        for (int i=0; i<meio; i++){
            l1.add(l.get(i));
        }
        for (int i=meio; i<tamanho; i++){
            l2.add(l.get(i));
        }
        
                
    }

    static LinkedList<Integer> merge(LinkedList<Integer> l1,
                                     LinkedList<Integer> l2) {
        LinkedList<Integer> lmerged = new LinkedList<>();
        int tamanho = l1.size() + l2.size();
        int meio = l1.size() - 1;
        int fim = l2.size() - 1;
        int p1 = 0;
        int p2 = 0;
        for (int i=0; i<tamanho; i++){
            if ((p1 <= meio) && (p2 <= fim)){
                if (l1.get(p1) < l2.get(p2)){
                    lmerged.add(i,l1.get(p1));
                    p1++;
                } else {
                    lmerged.add(i,l2.get(p2));
                    p2++;
                }
            } else {
                if (p1 <= meio){
                    lmerged.add(i,l1.get(p1));
                    p1++;
                } else {
                    lmerged.add(i,l2.get(p2));
                    p2++;
                }
            }
        }
            
        
        return lmerged;
    }

    static LinkedList<Integer> mergesort(LinkedList<Integer> l) {
    	if (l.size() >1){
    		LinkedList<Integer> l1 = new LinkedList<>();
    		LinkedList<Integer> l2 = new LinkedList<>();
			split(l,l1,l2);
			l1=mergesort(l1);
			l2=mergesort(l2);
			return merge(l1,l2);
    	}
    	else{
        	return l; // a ser completada
       }
    }
}

// A classe Ex1 eh fornecida fournie, para testar o codigo de Mergesort
public class Ex1 {

    static boolean is_sorted(LinkedList<Integer> l) {
        int v = Integer.MIN_VALUE;
        for (int x : l) {
            if (!(v <= x))
                return false;
            v = x;
        }
        return true;
    }

    static final int M = 10; // os elementos estao entre 0..M-1

    static int[] occurrences(LinkedList<Integer> l) {
        int[] occ = new int[M];
        for (int x : l)
            occ[x]++;
        return occ;
    }

    static boolean is_permut(LinkedList<Integer> l1, LinkedList<Integer> l2) {
        int[] occ1 = occurrences(l1);
        int[] occ2 = occurrences(l2);
        for (int i = 0; i < M; i++)
            if (occ1[i] != occ2[i])
                return false;
        return true;
    }

    static void test(LinkedList<Integer> l) {
        System.out.println("           l = " + l);
        int[] old_occ = occurrences(l);
        LinkedList<Integer> sl = Mergesort.mergesort(l);
        int[] new_occ = occurrences(l);
        for (int i = 0; i < M; i++)
            if (old_occ[i] != new_occ[i]) {
                System.out.println("ERRO : mergesort modificou seu parametro");
                System.exit(1);
            }
        System.out.println("mergesort(l) = " + sl);
        if (!is_sorted(sl)) {
            System.out.println("ERRO: o resultado nao esta ordenado");
            System.exit(1);
        }
        if (!is_permut(l, sl)) {
            System.out.println("ERRO : os elementos diferem");
            System.exit(1);
        }
    }

    static LinkedList<Integer> random_list(int len) {
        LinkedList<Integer> l = new LinkedList<Integer>();
        for (int i = 0; i < len; i++)
            l.add((int) (M * Math.random()));
        return l;
    }

    public static void main(String[] args) {
        System.out.println("teste de split");
        for (int len = 0; len < 10; len++) {
            LinkedList<Integer> l = random_list(len);
            System.out.println("         l = " + l);
            int occ[] = occurrences(l);
            LinkedList<Integer> l1 = new LinkedList<Integer>(), l2 = new LinkedList<Integer>();
            Mergesort.split(l, l1, l2);
            int[] new_occ = occurrences(l);
            for (int i = 0; i < M; i++)
                if (occ[i] != new_occ[i]) {
                    System.out.println("ERRO : split modificou seu parametro (l = " + l + ")");
                    System.exit(1);
                }
            System.out.println("  split(l) = " + l1 + " / " + l2);
            int occ0[] = occurrences(l1);
            int occ1[] = occurrences(l2);
            for (int i = 0; i < M; i++)
                if (occ0[i] + occ1[i] != occ[i]) {
                    System.out.println("ERRO : os elementos diferem");
                    System.exit(1);
                }
        }
        System.out.println("teste de merge");
        for (int len = 0; len < 5; len++) {
            LinkedList<Integer> l1 = new LinkedList<Integer>(), l2 = new LinkedList<Integer>();
            for (int i = 0; i < len; i++) {
                l1.add(2 * i);
                l2.add(2 * i + 1);
            }
            System.out.println("            l1 = " + l1);
            System.out.println("            l2 = " + l2);
            int occ1[] = occurrences(l1);
            int occ2[] = occurrences(l2);
            LinkedList<Integer> l = Mergesort.merge(l1, l2);
            System.out.println("  merge(l1,l2) = " + l);
            if (!is_sorted(l)) {
                System.out.println("ERRO : o resultado nao esta ordenado");
                System.exit(1);
            }
            int occ[] = occurrences(l);
            for (int i = 0; i < M; i++)
                if (occ1[i] + occ2[i] != occ[i]) {
                    System.out.println("ERRO : os elementos diferem");
                    System.exit(1);
                }
        }
        System.out.println("teste de mergesort");
        for (int len = 0; len < 10; len++)
            for (int j = 0; j <= len; j++)
                test(random_list(len));
        System.out.println("SUCESSO");
    }

}
