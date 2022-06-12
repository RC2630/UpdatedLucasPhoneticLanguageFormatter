import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.Map;

public class Formatter {

    private boolean isLetterOrSpace(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || (c == ' ');
    }

    private String formatRawToAnything(String rawLine, Map<Character, String> circumflexMap,
                                       Map<Character, String> diaeresisMap, Map<Character, String> specialNormals) {

        String formatted = "";

        for (int i = 0; i < rawLine.length(); i++) {
            if (i != rawLine.length() - 1) {

                if (rawLine.charAt(i) == '\\') { // escape character
                    formatted += rawLine.charAt(i + 1);
                    i++;
                    continue;
                }

                if (rawLine.charAt(i + 1) == '^' && circumflexMap.containsKey(rawLine.charAt(i))) {
                    formatted += circumflexMap.get(rawLine.charAt(i));
                    i++;
                } else if (rawLine.charAt(i + 1) == ':' && diaeresisMap.containsKey(rawLine.charAt(i))) {
                    formatted += diaeresisMap.get(rawLine.charAt(i));
                    i++;
                } else if (specialNormals.containsKey(rawLine.charAt(i))) {
                    formatted += specialNormals.get(rawLine.charAt(i));
                } else {
                    formatted += rawLine.charAt(i);
                }

            } else {

                if (rawLine.charAt(i) == '\\') { // last character is escape character
                    break;
                }

                formatted += rawLine.charAt(i);

            }
        }

        return formatted;

    }

    private String formatRawToLPL(String rawLine) {
        return formatRawToAnything(rawLine, References.RAW_TO_LPL_CIRCUMFLEX_MAP,
                                   References.RAW_TO_LPL_DIAERESIS_MAP, References.NO_SPECIAL_NORMALS);
    }

    private String formatRawToFLPL(String rawLine) {

        String halfFormatted =
                formatRawToAnything(rawLine, References.RAW_TO_FLPL_CIRCUMFLEX_MAP,
                                    References.RAW_TO_FLPL_DIAERESIS_MAP, References.RAW_TO_FLPL_SPECIAL_NORMALS);
        String formatted = "";

        for (int i = 0; i < halfFormatted.length(); i++) {
            if (i != halfFormatted.length() - 1) {

                boolean nextLetterIsW = (halfFormatted.charAt(i + 1) == 'w' || halfFormatted.charAt(i + 1) == 'W');

                if (halfFormatted.charAt(i) == 'k' && nextLetterIsW) {
                    formatted += 'q';
                    i++;
                } else if (halfFormatted.charAt(i) == 'K' && nextLetterIsW) {
                    formatted += 'Q';
                    i++;
                } else {
                    formatted += halfFormatted.charAt(i);
                }

            } else {

                formatted += halfFormatted.charAt(i);

            }
        }

        return formatted;

    }

    private String formatFLPLtoRaw(String flplLine) {
        // TODO
        throw new NotImplementedException();
    }

    private String formatFLPLtoLPL(String flplLine) {
        return formatRawToLPL(formatFLPLtoRaw(flplLine));
    }

    private String formatRawToIPA(String rawLine) {

        String halfFormatted =
                formatRawToAnything(rawLine, References.RAW_TO_IPA_CIRCUMFLEX_MAP, References.RAW_TO_IPA_DIAERESIS_MAP,
                                    References.RAW_TO_IPA_SPECIAL_NORMALS).toLowerCase();
        String formatted = "";

        // in the IPA, punctuation is interpreted as special phonetic symbols sometimes (instead of interpreted as punctuation)
        // for example, a colon (:) could be interpreted as a lengthening symbol, and an apostrophe (') as a stress symbol
        // therefore, to avoid confusion, all punctuation in the original raw text are removed in the generated IPA transcription
        for (char c : halfFormatted.toCharArray()) {
            if (isLetterOrSpace(c) || References.ALL_IPA_SPECIAL_CHARACTERS.contains(c)) {
                formatted += c;
            }
        }

        return formatted;

    }

    private String formatFLPLtoIPA(String flplLine) {
        return formatRawToIPA(formatFLPLtoRaw(flplLine));
    }

    private String formatIPAtoRaw(String ipaLine) {
        // TODO
        throw new NotImplementedException();
    }

    private String formatIPAtoLPL(String ipaLine) {
        return formatRawToLPL(formatIPAtoRaw(ipaLine));
    }

    private String formatIPAtoFLPL(String ipaLine) {
        return formatRawToFLPL(formatIPAtoRaw(ipaLine));
    }

    private String formatLPLtoRaw(String lplLine) {
        // TODO
        throw new NotImplementedException();
    }

    private String formatLPLtoFLPL(String lplLine) {
        return formatRawToFLPL(formatLPLtoRaw(lplLine));
    }

    private String formatLPLtoIPA(String lplLine) {
        return formatRawToIPA(formatLPLtoRaw(lplLine));
    }

    public void performFormattingOperation(int option)
            throws IOException, CustomExceptions.NotAnOptionException, CustomExceptions.FileIsEmptyException {

        File inputFile = new File("files/input.txt");

        if (inputFile.length() == 0) {
            throw new CustomExceptions.FileIsEmptyException();
        }

        BufferedReader reader = new BufferedReader(new FileReader("files/input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("files/output.txt", false));
        String line;

        while ((line = reader.readLine()) != null) {
            switch (option) {

                case References.RAW_TO_LPL: {
                    writer.write(formatRawToLPL(line) + "\n");
                    break;
                }

                case References.RAW_TO_FLPL: {
                    writer.write(formatRawToFLPL(line) + "\n");
                    break;
                }

                case References.FLPL_TO_RAW: {
                    writer.write(formatFLPLtoRaw(line) + "\n");
                    break;
                }

                case References.FLPL_TO_LPL: {
                    writer.write(formatFLPLtoLPL(line) + "\n");
                    break;
                }

                case References.RAW_TO_IPA: {
                    writer.write(formatRawToIPA(line) + "\n");
                    break;
                }

                case References.FLPL_TO_IPA: {
                    writer.write(formatFLPLtoIPA(line) + "\n");
                    break;
                }

                case References.IPA_TO_RAW: {
                    writer.write(formatIPAtoRaw(line) + "\n");
                    break;
                }

                case References.IPA_TO_LPL: {
                    writer.write(formatIPAtoLPL(line) + "\n");
                    break;
                }

                case References.IPA_TO_FLPL: {
                    writer.write(formatIPAtoFLPL(line) + "\n");
                    break;
                }

                case References.LPL_TO_RAW: {
                    writer.write(formatLPLtoRaw(line) + "\n");
                    break;
                }

                case References.LPL_TO_FLPL: {
                    writer.write(formatLPLtoFLPL(line) + "\n");
                    break;
                }

                case References.LPL_TO_IPA: {
                    writer.write(formatLPLtoIPA(line) + "\n");
                    break;
                }

                default: {
                    reader.close();
                    writer.close();
                    throw new CustomExceptions.NotAnOptionException();
                }

            }
        }

        reader.close();
        writer.close();

    }

}