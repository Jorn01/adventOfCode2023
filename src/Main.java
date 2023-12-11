import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        HashMap<Node, String> map = new HashMap<Node, String>();
        List<String> meow = List.of(Utils.readFile("C:\\Users\\JornL\\Downloads\\adventOfCode\\src\\input.txt").split("\n"));

        for (String s : meow) {
            Character flush = isFiveOfAKind(s.split(" ")[0].chars().mapToObj(c -> (char) c).toList());
            Character fourOfAKind = isFourOfAKind(s.split(" ")[0].chars().mapToObj(c -> (char) c).toList());
            Character threeOfAKind = isThreeOfAKind(s.split(" ")[0].chars().mapToObj(c -> (char) c).toList());
            Character pair = isPair(s.split(" ")[0].chars().mapToObj(c -> (char) c).toList());
            Character[] twoPair = isTwoPair(s.split(" ")[0].chars().mapToObj(c -> (char) c).toList());

            if (flush != null) {
                map.put(new Node("flush", s.split(" ")[0]), s.split(" ")[1]);
            } else if (fourOfAKind != null) {
                map.put(new Node("fourOfAKind", s.split(" ")[0]), s.split(" ")[1]);
            } else if (threeOfAKind != null && pair != null) {
                map.put(new Node("fullHouse", s.split(" ")[0]), s.split(" ")[1]);
            } else if (threeOfAKind != null) {
                map.put(new Node("threeOfAKind", s.split(" ")[0]), s.split(" ")[1]);
            } else if (twoPair != null) {
                map.put(new Node("twoPair", s.split(" ")[0]), s.split(" ")[1]);
            } else if (pair != null) {
                map.put(new Node("pair", s.split(" ")[0]), s.split(" ")[1]);
            } else {
                map.put(new Node("highCard", s.split(" ")[0]), s.split(" ")[1]);
            }
        }
        HashMap<Node, String> flush = new HashMap<>();
        for (Map.Entry<Node, String> entry : map.entrySet()) {
            if (entry.getKey().getName().equals("flush")) {
                flush.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Node, String> fourOfAKind = new HashMap<>();
        for (Map.Entry<Node, String> entry : map.entrySet()) {
            if (entry.getKey().getName().equals("fourOfAKind")) {
                fourOfAKind.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Node, String> fullHouse = new HashMap<>();
        for (Map.Entry<Node, String> entry : map.entrySet()) {
            if (entry.getKey().getName().equals("fullHouse")) {
                fullHouse.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Node, String> threeOfAKind = new HashMap<>();
        for (Map.Entry<Node, String> entry : map.entrySet()) {
            if (entry.getKey().getName().equals("threeOfAKind")) {
                threeOfAKind.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Node, String> twoPair = new HashMap<>();
        for (Map.Entry<Node, String> entry : map.entrySet()) {
            if (entry.getKey().getName().equals("twoPair")) {
                twoPair.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Node, String> pair = new HashMap<>();
        for (Map.Entry<Node, String> entry : map.entrySet()) {
            if (entry.getKey().getName().equals("pair")) {
                pair.put(entry.getKey(), entry.getValue());
            }
        }
        HashMap<Node, String> highCard = new HashMap<>();
        for (Map.Entry<Node, String> entry : map.entrySet()) {
            if (entry.getKey().getName().equals("highCard")) {
                highCard.put(entry.getKey(), entry.getValue());
            }
        }
        List<Node> flushList = null;
        List<Node> fourOfAKindList = null;
        List<Node> fullHouseList = null;
        List<Node> threeOfAKindList = null;
        List<Node> twoPairList = null;
        List<Node> pairList = null;
        List<Node> highCardList = null;

        //TODO fix small problem (remove when 0 --> i TIAAA
        for (int i = 0; i < 4; i++) {
            flushList = sortMapByValue(flush, i);
            fourOfAKindList = sortMapByValue(fourOfAKind, i);
            fullHouseList = sortMapByValue(fullHouse, i);
            threeOfAKindList = sortMapByValue(threeOfAKind, i);
            twoPairList = sortMapByValue(twoPair, i);
            pairList = sortMapByValue(pair, i);
            highCardList = sortMapByValue(highCard, i);
        }

        List<Node> allLists = new ArrayList<>();
        allLists.addAll(flushList);
        allLists.addAll(fourOfAKindList);
        allLists.addAll(fullHouseList);
        allLists.addAll(threeOfAKindList);
        allLists.addAll(twoPairList);
        allLists.addAll(pairList);
        allLists.addAll(highCardList);

        Collections.reverse(allLists);

        HashMap<Node, String> finalMap = new HashMap<>();
        for (Node node : allLists) {
            if (node.getName().equals("flush")) {
                finalMap.put(node, flush.get(node));
            }
            if (node.getName().equals("fourOfAKind")) {
                finalMap.put(node, fourOfAKind.get(node));
            }
            if (node.getName().equals("fullHouse")) {
                finalMap.put(node, fullHouse.get(node));
            }
            if (node.getName().equals("threeOfAKind")) {
                finalMap.put(node, threeOfAKind.get(node));
            }
            if (node.getName().equals("twoPair")) {
                finalMap.put(node, twoPair.get(node));
            }
            if (node.getName().equals("pair")) {
                finalMap.put(node, pair.get(node));
            }
            if (node.getName().equals("highCard")) {
                finalMap.put(node, highCard.get(node));
            }
        }

        int total = 0;
        for (int i = 0; i < finalMap.keySet().size(); i++) {
            Node key = finalMap.keySet().stream().toList().get(i);
            total += (Integer.parseInt(finalMap.get(key)) * (i + 1));
            System.out.println(key + " " + finalMap.get(key) + " " + i);
        }

        System.out.println(total);


    }

    public static List<Node> sortMapByValue(Map<Node, String> map, int indexOfValue) {
        List<Node> sortedMap = map.keySet().stream().sorted((Node, Node2) -> {
            return switch (Node.getValue().split("")[indexOfValue]) {
                case "T" -> switch (Node2.getValue().split("")[indexOfValue]) {
                    case "T" -> 0;
                    case "J" -> -1;
                    case "Q" -> -1;
                    case "K" -> -1;
                    case "A" -> -1;
                    default -> -1;
                };
                case "J" -> switch (Node2.getValue().split("")[indexOfValue]) {
                    case "T" -> 1;
                    case "J" -> 0;
                    case "Q" -> -1;
                    case "K" -> -1;
                    case "A" -> -1;
                    default -> -1;
                };
                case "Q" -> switch (Node2.getValue().split("")[indexOfValue]) {
                    case "T" -> 1;
                    case "J" -> 1;
                    case "Q" -> 0;
                    case "K" -> -1;
                    case "A" -> -1;
                    default -> -1;
                };
                case "K" -> switch (Node2.getValue().split("")[indexOfValue]) {
                    case "T" -> 1;
                    case "J" -> 1;
                    case "Q" -> 1;
                    case "K" -> 0;
                    case "A" -> -1;
                    default -> -1;
                };
                case "A" -> switch (Node2.getValue().split("")[indexOfValue]) {
                    case "T" -> 1;
                    case "J" -> 1;
                    case "Q" -> 1;
                    case "K" -> 1;
                    case "A" -> 0;
                    default -> -1;
                };
                default -> switch (Node2.getValue().split("")[indexOfValue]) {
                    case "T" -> 1;
                    case "J" -> 1;
                    case "Q" -> 1;
                    case "K" -> 1;
                    case "A" -> 1;
                    default ->
                            Node.getValue().split("")[indexOfValue].compareTo(Node2.getValue().split("")[indexOfValue]);
                };
            };
        }).collect(Collectors.toList());

        //reverse list
        Collections.reverse(sortedMap);

        return sortedMap;
    }


    public static Character isFiveOfAKind(List<Character> hand) {
        Character flush = null;
        for (Character card : hand) {
            if (hand.stream().filter(c -> c.equals(card)).count() == 5) {
                flush = card;
            }
        }
        return flush;
    }

    public static Character isFourOfAKind(List<Character> hand) {
        Character fourOfAKind = null;
        for (Character card : hand) {
            if (hand.stream().filter(c -> c.equals(card)).count() == 4) {
                fourOfAKind = card;
            }
        }
        return fourOfAKind;
    }

    public static Character isThreeOfAKind(List<Character> hand) {
        Character threeOfAKind = null;
        for (Character card : hand) {
            if (hand.stream().filter(c -> c.equals(card)).count() == 3) {
                threeOfAKind = card;
            }
        }
        return threeOfAKind;
    }

    public static Character isPair(List<Character> hand) {
        Character pair = null;
        for (Character card : hand) {
            if (hand.stream().filter(c -> c.equals(card)).count() == 2) {
                pair = card;
            }
        }
        return pair;
    }

    public static Character[] isTwoPair(List<Character> hand) {
        Character[] pair = new Character[2];
        for (Character card : hand) {
            if (hand.stream().filter(c -> c.equals(card)).count() == 2) {
                pair[0] = card;
            }
        }

        for (Character card : hand) {
            if (hand.stream().filter(c -> c.equals(card)).count() == 2) {
                if (!Objects.equals(card, pair[0])) {
                    pair[1] = card;
                }
            }
        }
        if (pair[0] != null && pair[1] != null) {
            return pair;
        } else {
            return null;
        }
    }


}