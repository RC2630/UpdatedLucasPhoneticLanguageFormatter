import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.Map;

public class Formatter {

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
        // TODO
        throw new NotImplementedException();
    }

    private String formatRawToIPA(String flplLine) {
        // TODO
        throw new NotImplementedException();
    }

    private String formatFLPLtoIPA(String flplLine) {
        // TODO
        throw new NotImplementedException();
    }

    private String formatIPAtoRaw(String flplLine) {
        // TODO
        throw new NotImplementedException();
    }

    private String formatIPAtoLPL(String flplLine) {
        // TODO
        throw new NotImplementedException();
    }

    private String formatIPAtoFLPL(String flplLine) {
        // TODO
        throw new NotImplementedException();
    }

    private String formatLPLtoRaw(String flplLine) {
        // TODO
        throw new NotImplementedException();
    }

    private String formatLPLtoFLPL(String flplLine) {
        // TODO
        throw new NotImplementedException();
    }

    private String formatLPLtoIPA(String flplLine) {
        // TODO
        throw new NotImplementedException();
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