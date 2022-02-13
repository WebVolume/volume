package volume;

public class NoExectFollow extends RuntimeException{
    public NoExectFollow(){
        super();
    }

    public NoExectFollow(String message){
        super(message);
    }
}
