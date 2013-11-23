package com.hongikapp.utility.sos.reservingin;

import java.util.Calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class Sos_ReservingInSelectTime_OnedaySchedule extends View {

    private int year;
    private int month;
    private int day;
    private int dayOfWeek;
     
    private String textDay;
    private String textActCnt;
    
    private Paint bgEnrolledPaint;
    private Paint bgDayPaint;
    private Paint bgSelectedDayPaint;
    private Paint bgActcntPaint;
    private Paint bgTodayPaint;
    private Paint textDayPaint;
    private Paint textActcntPaint;
 
    private int textDayTopPadding;
    private int textDayLeftPadding;
    private int textActcntTopPadding;
    private int textActcntLeftPadding;
 
    private Paint mPaint;
 
    private boolean mSelected;
    public boolean isToday = false;
    
    public String scheTitle;					//Schedule의 제목
    public int schetime_Hour;					//Schedule의 제한 시간의 '시'
    public int schetime_minute;					//Schedule의 제한 시간의 '분'
    											//		원활한 관리를 위해서 integer로 관리한다.
    public boolean alarmON;						//Schedule의 알람 작동 여부
    public String details;						//Schedule의 세부 사항
    public boolean isEnrolled = false;			//Schedule의 지정 여부
    
    
    //function: public void setSchedule(String title, int hour, int min, boolean ala, String det, boolean enrollOX)
    //description: schedule의 정보를 setting한다.
    //parameter
    //		String title : 지정하려는 schedule의 제목
    //		int hour : 지정하려는 schedule의 제한 시간의 '시'
    //		int min : 지정하려는 schedule의 제한 시간의 '분'
    //		boolean ala : 지정하려는 schedule의 알람 작동 여부
    //		String det : 지정하려는 schedule의 세부 사항
    //		boolean enrollOX : 지정하려는 schedule의 등록여부
    public void setSchedule(String title, int hour, int min, boolean ala, String det, boolean enrollOX){
    	scheTitle = title;
    	schetime_Hour = hour;
    	schetime_minute = min;
    	alarmON = ala;
    	details = det;
    	isEnrolled = enrollOX;
    }
    //function: public void deleteSchedule()
    //description: schedule의 정보를 삭제한다.
    public void deleteSchedule(){
    	scheTitle = "";
    	schetime_Hour = 0;
    	schetime_minute = 0;
    	alarmON = false;
    	details = "";
    	isEnrolled = false;
    }
    
    public Sos_ReservingInSelectTime_OnedaySchedule(Context context , android.util.AttributeSet attrs) {
        super(context, attrs);
        init();
    }
 
    public Sos_ReservingInSelectTime_OnedaySchedule(Context context) {
        super(context);
        init();
 
    }
 
    private void init()
    {
    	bgEnrolledPaint = new Paint();
        bgDayPaint = new Paint();
        bgSelectedDayPaint = new Paint();
        bgActcntPaint = new Paint();
        textDayPaint = new Paint();
        textActcntPaint = new Paint();
        bgTodayPaint = new Paint();
        bgEnrolledPaint.setColor(Color.RED);
        bgDayPaint.setColor(Color.WHITE);
        bgActcntPaint.setColor(Color.YELLOW);
        textDayPaint.setColor(Color.WHITE);
        textDayPaint.setAntiAlias(true);
        textActcntPaint.setColor(Color.WHITE);
        textActcntPaint.setAntiAlias(true);
        bgTodayPaint.setColor(Color.GREEN);
        rect = new RectF();
 
        setTextDayTopPadding(0);
        setTextDayLeftPadding(0);
 
        setTextActcntTopPadding(0);
        setTextActcntLeftPadding(0);
 
        mPaint = new Paint();
 
        mSelected = false;
    }
 
    RectF rect;
    @Override
    protected void onDraw(Canvas canvas) {
 

        if(mSelected){
            canvas.drawPaint(bgSelectedDayPaint);
        }else{
            if(isToday){
                canvas.drawPaint(bgTodayPaint);
                mPaint.setStyle(Paint.Style.STROKE);
                // Line Color
                mPaint.setColor(Color.RED);
                // Line Width
                mPaint.setStrokeWidth(7);
                // Top Line
                canvas.drawLine(0, 0, this.getWidth()-1, 0, mPaint);
                // Bottom Line
                canvas.drawLine(0, this.getHeight()-1, this.getWidth()-1, this.getHeight()-1, mPaint);
                // Left Line
                canvas.drawLine(0, 0, 0, this.getHeight()-1, mPaint); 
                // Right Line
                canvas.drawLine(this.getWidth()-1, 0, this.getWidth()-1, this.getHeight()-1, mPaint);
            } else {
                canvas.drawPaint(bgDayPaint);
            }
            
           	if(isEnrolled)
        		canvas.drawPaint(bgEnrolledPaint);
        }
 
        int width = this.getWidth()/2;
        int height = this.getHeight()/2;
 
        int textDaysize = (int)textDayPaint.measureText(getTextDay()) / 2;
        int textActsize = (int)textActcntPaint.measureText(getTextActCnt()) / 2;
        canvas.drawText(getTextDay(), width - textDaysize + getTextDayLeftPadding(), height + getTextDayTopPadding(), textDayPaint); 
     /*   
        if(getTextActCnt() != "")
        {
            rect.set(10, 45, 55, 65);
            canvas.drawRoundRect(rect, 10, 30, bgActcntPaint);
        }
 */
        canvas.drawText(getTextActCnt(), width - textActsize + getTextActcntLeftPadding(), height + getTextActcntTopPadding(), textActcntPaint);
        
 /*
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(0, this.getHeight()-1, this.getWidth()-1, this.getHeight()-1, mPaint);
        canvas.drawLine(this.getWidth()-1, 0, this.getWidth()-1, this.getHeight()-1, mPaint);
*/
    }
    public int getTextDayTopPadding() {
        return this.textDayTopPadding;
    }
    public int getTextDayLeftPadding() {
        return this.textDayLeftPadding;
    }
    public void setTextDayTopPadding(int top){
        this.textDayTopPadding = top;
    }
    public void setTextDayLeftPadding(int left){
        this.textDayLeftPadding = left;
    }
 
    public int getTextActcntTopPadding() {
        return this.textActcntTopPadding;
    }
    public int getTextActcntLeftPadding() {
        return this.textActcntLeftPadding;
    }
    public void setTextActcntTopPadding(int top){
        this.textActcntTopPadding = top;
    }
    public void setTextActcntLeftPadding(int left){
        this.textActcntLeftPadding = left;
    }
 
    public void setBgTodayPaint(int color){
        this.bgTodayPaint.setColor(color);
    }
    public void setBgDayPaint(int color){
        this.bgDayPaint.setColor(color);
    }
    public void setBgSelectedDayPaint(int color){
        this.bgSelectedDayPaint.setColor(color);
    }
    public void setBgActcntPaint(int color){
        this.bgActcntPaint.setColor(color);
    }
    public void setSelected(boolean selected){
        this.mSelected = selected;
    }
    public boolean getSelected() {
        return this.mSelected;
    }
 
    public String getTextDay() {
        return this.textDay;
    }
    
    public void setTextDay(String string) {
        this.textDay = string;
    }
 
    
    public String getTextActCnt(){
        return this.textActCnt;
    }
  
    public void setTextActCnt(String string){
        this.textActCnt = string;
    }
  
    public void setTextDayColor(int color){
        this.textDayPaint.setColor(color);
    }
  
    public void setTextDaySize(int size){
        this.textDayPaint.setTextSize(size);
    }
 
    public void setTextActcntColor(int color){
        this.textActcntPaint.setColor(color);
    }
    
    public void setTextActcntSize(int size){
        this.textActcntPaint.setTextSize(size);
    }
     
    
    public void setYear(int _year){
        year = _year;
    }
    
    public int getYear(){
        return year;
    }
 
    public void setMonth(int _month){
        month = Math.min(Calendar.DECEMBER, Math.max(Calendar.JANUARY, _month));
        month = _month;
    }
   
    public int getMonth(){
        return month;
    }
     
    public void setDay(int _day){
        day = Math.min(31, Math.max(1, _day));
        day = _day;
    }
   
    public int getDay(){
        return day;
    }
    
    public void setDayOfWeek(int _dayOfWeek){
        dayOfWeek = Math.min(Calendar.SATURDAY, Math.max(Calendar.SUNDAY, _dayOfWeek));
        dayOfWeek = _dayOfWeek;
    }
     

    public int getDayOfWeek(){
        return dayOfWeek;
    }
     

    public String getDayOfWeekKorean(){
        final String[]korean = {"오류", "일", "월", "화", "수", "목", "금", "토"};
        return korean[dayOfWeek];
    }
     
    public String getDayOfWeekEnglish(){
        final String[]korean = {"E", "Sun", "Mon", "Tues", "Wednes", "Thurs", "Fri", "Satur"};
        return korean[dayOfWeek];
    }

    /**
     * 기본 정보 복사
     * @param srcDay
     */
    public void copyData(Sos_ReservingInSelectTime_OnedaySchedule srcDay){
        setYear(srcDay.getYear());
        setMonth(srcDay.getMonth());
        setDay(srcDay.getDay());
        setDayOfWeek(srcDay.getDayOfWeek());
        setTextDay(srcDay.getTextDay());
        setTextActCnt(srcDay.getTextActCnt());
    }
    
    public boolean getisenrolled(){
    	return isEnrolled;
    }
    public String getschetitle(){
    	return scheTitle;
    }
    public String getdetails(){
    	return details;
    }
    public boolean getalarmON(){
    	return alarmON;
    }
    public int getschetime_hour(){ 
    	return schetime_Hour;
    }
    public int getschetime_minute(){
    	return schetime_minute; 
    }
    
             
}