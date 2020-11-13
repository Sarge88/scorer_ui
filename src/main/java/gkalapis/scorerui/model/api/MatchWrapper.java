package gkalapis.scorerui.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

import java.util.ArrayList;

public class MatchWrapper {

    @SerializedName("")
    private List<Match> matches = new ArrayList<Match>();

    public List<Match> getMatches() {
        return matches;
    }
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MatchWrapper matchWrapper = (MatchWrapper) o;
        return Objects.equals(matchWrapper, matchWrapper.matches);
    }

    @Override
    public int hashCode() {
        return Objects.hash( matches);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Matches {\n");

        sb.append("    matches: ").append(toIndentedString(matches)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
