package nl.saxion.minesweeper.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer{
    private Date start;
    private Date end;
    private DateFormat timeFormat;
    private boolean inGame;

    public Timer(){
        this.inGame = false;
        this.timeFormat = new SimpleDateFormat("mm:ss");
        this.start = new Date();
        this.end = this.start;
    }
    public String getTimeFormatted(){
        if(inGame)
            return timeFormat.format((new Date().getTime() - start.getTime()));
        else
            return timeFormat.format(end.getTime()-start.getTime());
    }
    public long getTime(){
        return end.getTime()-start.getTime();
    }
    public void startTimer(){
        this.inGame = true;
        this.start = new Date();
    }
    public void stopTimer(){
        this.inGame = false;
        this.end = new Date();
    }
}