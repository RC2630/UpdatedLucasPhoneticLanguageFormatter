import java.util.*;

public class References {

    public static final int RAW_TO_LPL = 1, RAW_TO_FLPL = 2, FLPL_TO_RAW = 3, FLPL_TO_LPL = 4,
                            RAW_TO_IPA = 5, FLPL_TO_IPA = 6, IPA_TO_RAW = 7, IPA_TO_LPL = 8,
                            IPA_TO_FLPL = 9, LPL_TO_RAW = 10, LPL_TO_FLPL = 11, LPL_TO_IPA = 12;

    private static final List<Character> REGULAR_LETTERS_HATTABLE =
            Arrays.asList('a', 'e', 'i', 'o', 'u', 'n', 's', 'x', 'z',
                          'A', 'E', 'I', 'O', 'U', 'N', 'S', 'X', 'Z');
    private static final List<String> CIRCUMFLEX_LETTERS =
            Arrays.asList("â", "ê", "î", "ô", "û", "n̂", "ŝ", "x̂", "ẑ",
                          "Â", "Ê", "Î", "Ô", "Û", "N̂", "Ŝ", "X̂", "Ẑ");
    private static final List<String> FLPL_CIRCUMFLEX_CORRESPONDENCES =
            Arrays.asList("ua", "e", "ai", "o", "au", "nn", "ss", "xx", "zz",
                          "Ua", "E", "Ai", "O", "Au", "Nn", "Ss", "Xx", "Zz");
    private static final List<Character> REGULAR_LETTERS_DOTTABLE =
            Arrays.asList('a', 'e', 'i', 'o', 'u',
                          'A', 'E', 'I', 'O', 'U');
    private static final List<String> DIAERESIS_LETTERS =
            Arrays.asList("ä", "ë", "ï", "ö", "ü",
                          "Ä", "Ë", "Ï", "Ö", "Ü");
    private static final List<String> FLPL_DIAERESIS_CORRESPONDENCES =
            Arrays.asList("a", "ea", "i", "oi", "u",
                          "A", "Ea", "I", "Oi", "U");
    private static final List<String> NORMAL_VOWELS_DOUBLED =
            Arrays.asList("aa", "ee", "ii", "oo", "uu",
                          "Aa", "Ee", "Ii", "Oo", "Uu");

    public static final Map<Character, String> NO_SPECIAL_NORMALS =
            new HashMap<>();
    public static final Map<Character, String> RAW_TO_LPL_CIRCUMFLEX_MAP =
            makeMap(REGULAR_LETTERS_HATTABLE, CIRCUMFLEX_LETTERS);
    public static final Map<Character, String> RAW_TO_LPL_DIAERESIS_MAP =
            makeMap(REGULAR_LETTERS_DOTTABLE, DIAERESIS_LETTERS);
    public static final Map<Character, String> RAW_TO_FLPL_CIRCUMFLEX_MAP =
            makeMap(REGULAR_LETTERS_HATTABLE, FLPL_CIRCUMFLEX_CORRESPONDENCES);
    public static final Map<Character, String> RAW_TO_FLPL_DIAERESIS_MAP =
            makeMap(REGULAR_LETTERS_DOTTABLE, FLPL_DIAERESIS_CORRESPONDENCES);
    public static final Map<Character, String> RAW_TO_FLPL_SPECIAL_NORMALS =
            makeMap(REGULAR_LETTERS_DOTTABLE, NORMAL_VOWELS_DOUBLED);

    private static Map<Character, String> makeMap(List<Character> l1, List<String> l2) {
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < l1.size(); i++) {
            map.put(l1.get(i), l2.get(i));
        }
        return map;
    }

}