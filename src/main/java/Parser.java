import java.util.*;
import java.util.regex.Pattern;

public class Parser {
    public static List<Item> mainParse(String data) throws Exception {
        Map<String, List<String>> map = new LinkedHashMap<>();
        String regex = "##";
        Pattern pattern = Pattern.compile(regex);
        String[] items = pattern.split(data);
        int errCount = 0;
        List<Item> itemList = new ArrayList<Item>();

        for(String item: items){
            itemList.add(miniParse(item));

            // checking cookies + variations of cookies, replacing the name in list with "cookies"
            if(itemList.get(0).name != null && itemList.get(0).name.matches("^c[\\w]*s$")){
                //itemList.set(0, "cookies");

            }

            // if null, up the count
            // else if the map already has the grocery item in it,
            // add the price to the list
            // else create a new key-value pair

            if(itemList.contains(null)){
                errCount++;
            }
            else if(map.containsKey(itemList.get(0))){
                map.get(itemList.get(0)).add(itemList.get(1));
            }
            else{

                List<String> prices = new ArrayList<>();
                prices.add(itemList.get(1));
                map.put(itemList.get(0), prices);
            }
        }
        // creating the error key-value pair using errCount to make a new list of that size
        map.put("error", Arrays.asList(new String[errCount]));
        return null;
    }

    public static Item miniParse(String item) throws Exception {
        String regex = "[$&+,;=?@#|'<>.^*()%!-]";
        Pattern pattern = Pattern.compile(regex);
        String[] temp = pattern.split(item.toLowerCase());
        ArrayList<String> itemList = new ArrayList<>();

        // temp[0] = {name: milk}
        Pattern newPattern = Pattern.compile(":(.+)");
        for(String items: temp){
            String[] newTemp = newPattern.split(items);
            itemList.add(newTemp[0]);
        }
        Item item = new Item(itemList.get(0), itemList.get(1));
        return item;
    }
}
