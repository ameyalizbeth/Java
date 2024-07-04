
import java.util.*;

public class Main {
    public static int countTotalSize(List<File> files){
        int sum=0;
        for(File f : files){
            sum+= f.size;
        }
        return sum;
    }
    public static List<Collection> biggestCollection(List<File> files, int k){
            Map<String, Integer> collectionMap = new HashMap<>();
            for(File f : files){
                if(f.collection != null){
                    for(String s : f.collection){
                        collectionMap.put(s, collectionMap.getOrDefault(s, 0)+f.size);
                    }
                }
            }
        PriorityQueue<Collection> maxHeap = new PriorityQueue<>((a, b) -> b.totalSize - a.totalSize);

        for (Map.Entry<String, Integer> entry : collectionMap.entrySet()) {
            maxHeap.offer(new Collection(entry.getKey(), entry.getValue()));
        }

        List<Collection> topKCollections = new ArrayList<>();
        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            topKCollections.add(maxHeap.poll());
        }

        return topKCollections;
    }
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to my file system organiser\n");
        List<String> collection1 = Arrays.asList("collection1");
        List<String> collection2 = Arrays.asList("collection2");
        List<File> files = Arrays.asList(
                new File("file1.txt", 100, null),
                new File("file2.txt", 200, new ArrayList<>(collection1)),
                new File("file3.txt", 200, new ArrayList<>(collection1)),
                new File("file4.txt", 300, new ArrayList<>(collection2)),
                new File("file5.txt", 100, null)

        );
        System.out.println("The total size of my files is " + countTotalSize(files));
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the value of k :");
        int k = sc.nextInt();
        List<Collection> topKCollection = biggestCollection(files, k);
        System.out.println("Top " + k + " collections:");
        for(Collection c : topKCollection){
            System.out.println(c.name);
        }

    }
}