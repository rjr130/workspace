import java.util.*;

public class Graphnode<T> {
	private boolean visitMark;
    private List<Graphnode<T>> successors;
 
    public boolean getVisitMark() {
        return visitMark;
    }
 
    public void setVisitMark(boolean mark) {
        visitMark = mark;
    }
 
    public List<Graphnode<T>> getSuccessors() {
        return successors;
    }
}
