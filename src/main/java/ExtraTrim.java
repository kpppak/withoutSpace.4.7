public class ExtraTrim {
    private String input;

    public ExtraTrim(String input) {
        this.input = input;
    }

    public String trimExtraSpaces() {
        String border = input.trim();
        String noRepeat = border.replaceAll("\\s+"," ");
        String question = noRepeat.replaceAll("\\s+\\?","?");
        String dot = question.replaceAll("\\s+\\.",".");
        String comma = dot.replaceAll("\\s+,",",");
        String colon = comma.replaceAll("\\s+:",":");
        String commaDot = colon.replaceAll("\\s+;",";");
        String exclamation = commaDot.replaceAll("\\s+\\!","!");
        String ellipsis = exclamation.replaceAll("\\s+\\.\\.\\.","...");
        String result = ellipsis;
        return result;
    }
}
