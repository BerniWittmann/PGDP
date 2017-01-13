package animalChess;

public class Move {
    private String from;
    private String to;
    public Move(String from, String to){
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString(){
        return from + to;
    }

    public boolean equals(Object other) {
        return false;
    }
}
