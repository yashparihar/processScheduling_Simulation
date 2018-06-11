/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fcfs;

/**
 *
 * @author Yash
 */
public class process {

  //  static void setText(String string) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    
    static int process_no;
    String j_id;
    String j_color;
    boolean doneFlag=false;
    float j_bt;
    float j_at;
    float j_st;
    float j_wt;
    int j_pt;
    int j_lt;
    int j_tat;
    String j_status = "not";
    
    static int cntr=0;
   public static boolean cpu_working=false;
    public static int curr = -1 ;
    public static int total_p;
    public static int total_p_halt=0;
    public static int total_p_done=0;
    public static int cpu_time;
    public static int p_in_queue=0;
    
    
    public process()
    { }
    
    public process(String id,String color,float bt,float at)
    {
        this.j_id = id;
        this.j_color=color;
        this.j_bt = bt;
        this.j_at = at;
    }
    public process(String id,String color,float bt,float at,float wt,int tat)
    {
        this.j_id = id;
        this.j_color=color;
        this.j_bt = bt;
        this.j_at = at;
        this.j_wt = wt;
        this.j_tat = tat;
    }
 
    
    public static int getCntr()
    {
        return cntr;    
        
    }
    
     public static void setProcess_no(int no)
    {
        process_no = no;   
    }
      public static int getProcess_no()
    {
        return process_no;
    }
    public void setJ_id(String s)
    {
        this.j_id = s;   
    }
     public void setJ_color(String c)
    {
        this.j_color = c;   
    }
    public void setJ_bt(float b)
    {
        this.j_bt = b;
        
    }
    public void setJ_at(float t)
    {
        this.j_at = t;
    }
    
    public String getJ_id()
    {
        return this.j_id;
    }
    public String getJ_color()
    {
        return this.j_color;
    }
    public float getJ_bt()
    {
        return this.j_bt;
    }
    public float getJ_at()
    {
        return this.j_at;
    }
    
    
    //....STATINF=G TIME................
     public void setJ_st(float t)
    {
        this.j_st = t;
    }
    
    public float getJ_st()
    {
        return this.j_st;
    }
    
    //.......waiting time;;
     public void setJ_wt(float t)
    {
        this.j_wt = t;
    }
    
    public float getJ_wt()
    {
        return this.j_wt;
    }
    
    
    //.....PROCEED TIME.............
     public void setJ_pt(int t)
    {
        this.j_pt = t;
    }
    
    public int getJ_pt()
    {
        return this.j_pt;
    }
    
    //...LEFT TIME..................
     public void setJ_lt(int t)
    {
        this.j_lt = t;
    }
    
    public int getJ_lt()
    {
        return this.j_lt;
    }
    
    
     //...TURN AROUND TIME..................
     public void setJ_tat(int t)
    {
        this.j_tat = t;
    }
    
    public int getJ_tat()
    {
        return this.j_tat;
    }
    
    
    
      //...JOB STATUS..................
     public void setJ_status(String t)
    {
        this.j_status = t;
    }
    
    public String getJ_status()
    {
        return this.j_status;
    }
    
}
