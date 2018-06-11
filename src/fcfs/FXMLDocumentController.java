/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fcfs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
//import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
//import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TableView;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
//import static javafx.stage.Modality.APPLICATION_MODAL;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
/**
 *
 * @author Yash
 */
public class FXMLDocumentController implements Initializable {
    
    
     @FXML
     private Button imp_fcfs;
    @FXML
     private Button imp_srt;
    @FXML
     private Button imp_sjf;
    @FXML
           private Button btn_start; 
   
          
    
    public static String process_type=null;
    
          
            
            
            //......
   // private Label label;
    StringProperty id = new SimpleStringProperty();
    FloatProperty bt = new SimpleFloatProperty();
     FloatProperty at = new SimpleFloatProperty();
@FXML Button button =new Button();
@FXML TextField txtid = new TextField();
@FXML TextField txtbt = new TextField();
@FXML TextField txtat = new TextField();
@FXML TableView<process> j_tbl ;
@FXML Label avg = new Label();

  process len = new process();
   
    @FXML HBox shb=new HBox();
  @FXML ColorPicker scp=new ColorPicker();
  
  @FXML HBox fhb=new HBox();
  @FXML ColorPicker fcp=new ColorPicker();

  @FXML Label lblp=new Label();
@FXML Label lblcp=new Label();   
@FXML ProgressBar pb=new ProgressBar(0);
 @FXML ProgressBar pbsjf=new ProgressBar(0.0);
@FXML Button btns=new Button();

  @FXML Label label1=new Label();
  @FXML RadioButton rad_fcfs = new RadioButton();
  @FXML RadioButton rad_sjf = new RadioButton();
  @FXML RadioButton rad_srt = new RadioButton();
    @FXML RadioButton rad_rr = new RadioButton();
  
    //System.out.println(len.getProcess_no());
   process[] p;
       
   boolean flag=false;
   static int i=0;
   
   int no=0;
   
    //..........FOR START PAGE.... INITIALIZING PROCESS SCHEDULING PAGE.......

   
    @FXML
    private void handleBtnStart(ActionEvent event)throws IOException 
    {
          
       Stage stage; 
   // Parent root;
            FlowPane pane2=new FlowPane();  
            TextField txt1 = new TextField(); 
            Button accept_tot_p = new Button();
            accept_tot_p.setText("Submit No Of Processes");
            Button btnexit = new Button();
            btnexit.setText("Start Processing");
            
            //......................
            stage=(Stage) btn_start.getScene().getWindow();
            
            
            
            System.out.println("process_type="+process_type);
         
    //... FOR THE MAIN PAGE  ... primary stage.........
     Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
     
     
         
     //..putting cotrols in new stage................
          pane2.getChildren().addAll(txt1,accept_tot_p,btnexit);
   
     //make another scene , stage for  and set it.....
      Scene scene2 = new Scene(pane2, 200, 100);
     Stage newStage = new Stage();
     newStage.setScene(scene2);
     
     //tell stage it is meannt to pop-up (Modal)
     newStage.initModality(Modality.APPLICATION_MODAL);
     newStage.setTitle("Set Number of Processes");
      stage.setTitle("Process Scheduling Simulator");
     // SETTING PROCESS NO IN CLASS
     accept_tot_p.setOnAction(e -> setValue(txt1,btnexit,accept_tot_p));
     
     //.. SETTING CLOSE DISABLE TILL PROCESS NO IS NOT SET
         btnexit.setDisable(true);
         
     //.... WHEN START PROCESS BTN CLICKED THEN NEWSTAGE WILL CLOSE.....    
      btnexit.setOnAction(e ->  newStage.close());
      
      //... now setting stage and scene for primart stage.......
        Scene scene = new Scene(root);
        stage.setScene(scene);
      
        stage.show();
        
        newStage.show();
            
    }
    
   
   //......................
   
    
    
    
    //.......PROCESS SELECTION on STARTUP pAGE......................  
     @FXML
    private void handleRadSelect(ActionEvent event) {
       
        
       if (event.getSource()==rad_fcfs)
       {
        label1.setText("fcfs");
       }
       else if (event.getSource()==rad_sjf)
       {
        label1.setText("sjf");
       }
        else if (event.getSource()==rad_srt)
       {
        label1.setText("srt");
       }
        else if (event.getSource()==rad_rr)
       {
        label1.setText("rr");
       }
        process_type= label1.getText();
               
        btn_start.setDisable(false);
    }
    
    //........................................
   
    
    
    
 //-----<<<<<<WHEN SUBMIT PROCESS CLICKED>>>>>---------------------------------
    @FXML
    private void handleBtn(ActionEvent event) {
        
        
        ObservableList<process> data = j_tbl.getItems();
    
        
   //... IF PROCESS COUNTER NOT SET THEN SET IT.. THIS WOTKS ONLT AT VERY FIRST PROCESS ALLOCATION
        if (p == null)
        {
            System.out.println("yaa not initialized");
            p= new process[len.getProcess_no()];   
        }
        
        
//        ObservableList<process> data = j_tbl.getItems();
      
        // PROCESS EACH LEMENT IS ALLOCATED ......
        p[i]=new process();
        
        
        
        
  // PROCESS ONLY PERMITS NO OF PROCESSES ASKED.....DISABLING ASKING CONTENT 
           if (p[i].getCntr()+1 == p[i].getProcess_no())
           {
               txtid.setDisable(true);
               txtbt.setDisable(true);
               txtat.setDisable(true);
              button.setDisable(true);
               flag = true;
           }
           
        
        

      //... SETTING VALUE FROM TEXTFIELD TO CLASS FIELD.......
        id.set(txtid.getText());
             p[i].setJ_id(txtid.getText());
         bt.set(Float.parseFloat(txtbt.getText()));
              p[i].setJ_bt(bt.get());
         at.set(Float.parseFloat(txtat.getText()));
                   p[i].setJ_at(at.get());
          p[i].setJ_color(String.valueOf(scp.getValue()));
          p[i].setJ_color(String.valueOf(fcp.getValue()));
          
    //..ADDING ROW IN TABLEVIEW.....        
            data.add(new process(id.get(),p[i].getJ_color(),bt.get(),at.get()));
           
            
    //... IN CONSOLE.... SHOWING PROCESS DETAILS....    
              System.out.println("jid=" + p[i].getJ_id());
                 System.out.println("\n jbt = "+ p[i].getJ_bt());
            System.out.println("\n jat = "+ p[i].getJ_at());
            System.out.println("\n jcolor= "+ p[i].getJ_color());
   //... INCREMENTING PROCESS COUNTER......         
   p[i++].cntr++;
   
   
   //.. CLERING TEXTFIELD ADTER SUBMISSION...........
   txtid.clear();
   txtbt.clear();
   txtat.clear();
  
    
    }
    
   //..---<END OF METHOD>>>> 
    
    
  
    
    
    
    
    
 //...............IMPLEMENT SCHEDULING........................
    
    @FXML
  private void handleBtnImp(ActionEvent event) throws IOException {
      
      if (process_type=="fcfs")
      {
          imp_fcfs();
      }
      else if (process_type=="sjf" || process_type=="srt")
      {
        imp_sjf_srt(); 
      }
      else 
      {
        imp_rr(); 
      }
      
  }
  
    //--<<<< END OF METHOD...>>>>-
    
  
  
  
  
  
  
  //....IMPLEM ROUND ROBIN..........
  private void imp_rr()
 {
       

        System.out.println("IN RR............");
        int total_wt=0;
        float avg_wt;
        int total_tat=0;
        float avg_tat;
        int quantum = 2;
        float service;
        
        process temp;
     
       // int total_p = p[0].getProcess_no();
     
        
       
        
        System.out.println("curr= "+ p[0].curr);
        System.out.println("total p ="+p[0].getProcess_no());
        System.out.println("total p done="+ p[0].total_p_done);
     
        
          for(int j=0;j<len.getProcess_no();j++)
        {
            for(int k=j+1;k<len.getProcess_no();k++)
            {
                  if(p[j].getJ_at()> p[k].getJ_at())  
                  {
                   temp=new process();
                   temp=p[j];
                   p[j]=p[k];
                   p[k]=temp;
                  }
            }
            System.out.println("pid="+p[j].getJ_id());
            
        }
        
        //... REMEMBER I=0... SO GETTING 1st PROCESS A.T. INTO CPU TIME(SERVICE)...
        service=p[0].getJ_at();
        
       int pcnt=0;
       int q_cnt;
       int i;
    
       while (p[0].total_p_done != len.getProcess_no() - 1)
     {
         q_cnt=0;
         i=0;
         while(q_cnt!=quantum)
         {
             
             
             if(p[i].getJ_lt()==0)
             {
                 if(p[i].j_status != "done")
                 {
                     p[i].j_status="done";
                     p[i].total_p_done += 1;
                     
                 }
                 q_cnt = quantum;
             }
             else
             {
                 service = service + 1;
                 p[i].j_status = "work";
                 p[i].setJ_lt( p[i].getJ_lt() - 1 );
                 System.out.println("curr Job working:"+ p[i].getJ_id());
                 System.out.println("its Left time: "+ p[i].getJ_lt());
                 q_cnt += 1;
                 
             }
         }
         
         
         if (pcnt == len.getProcess_no() )
         {
             pcnt = 0;
         }
         else
         {
             pcnt += 1;
         }
     }
     
     
     for (i=0;i<len.getProcess_no(); i++)
     {
         System.out.println("Job id=" + p[i].getJ_id());
         System.out.println("Job bt=" + p[i].getJ_bt());
            System.out.println("Job at=" + p[i].getJ_at());
     }
     
     
        
     
 }

  //......END ROUND ROBIN................
  
  
   

  //....IMPLEMENTING FCFS........
private void imp_fcfs()
{
    
    
       System.out.println("IN FCFS............");
      Task task1 = new Task<Void>() {
            @Override public Void call() throws Exception{
 
                 ObservableList<process> data = j_tbl.getItems();
    
                       //.. DECLARING USEFUL VAR....
         float sum=0 , sum_tat=0;
        float avgWt=0 , avgTat=0;
        int i=0,j,k,tid,tbt,tat;
        float service; // THESE IS CPU COUNTER..............
        
   //.CREATING TEMP OBJ ... ALLOCATED WHILE SWAPPING PROCESS PURPOSE....
        process pt;
   
        
    //.IN CONSOLE... SHOWING CURRENT TOT PROCESS AND TOTAL PROCESS TO INSTANTIALTE 
        System.out.println("proc no="+ len.getCntr() );
        System.out.println("total pro no="+ len.getProcess_no() );
        
        
      //..... SWAPPINP PROCESS ACCORDING TO WT....... 
        for(j=0;j<len.getProcess_no();j++)
        {
            for(k=j+1;k<len.getProcess_no();k++)
            {
                  if(p[j].getJ_at()> p[k].getJ_at())  
                  {
                   pt=new process();
                   pt=p[j];
                   p[j]=p[k];
                   p[k]=pt;
                  }
            }
            System.out.println("pid="+p[j].getJ_id());
            
        }
        
        //... REMEMBER I=0... SO GETTING 1st PROCESS A.T. INTO CPU TIME(SERVICE)...
        service=p[i].getJ_at();
        
      // .... FINDING TOTAL WAITING TIME...................  
        for( i=0;i<len.getProcess_no();i++ )
        {
            
               for(int m=0;m<p[i].getJ_bt();m++)
               {
                   try {
                       Rectangle r=new Rectangle(20,50,Paint.valueOf(p[i].getJ_color()));
                    fhb.setSpacing(5);
              
                    Platform.runLater(() -> fhb.getChildren().addAll(r));
               
                    Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    } 
                   
               }
            
          p[i].setJ_st(service);
         
          p[i].setJ_wt(service - p[i].getJ_at());
         
          
          p[i].setJ_tat((int)(p[i].getJ_wt()+p[i].getJ_bt()));
          

          
          service = service + p[i].getJ_bt();
          sum = sum + p[i].getJ_wt();
         sum_tat = sum_tat + p[i].getJ_tat();
         
        //..ADDING ROW IN TABLEVIEW.....
          if(flag==true)
          {
              j_tbl.getItems().clear();
            //  data.add(new process(p[i].getJ_id(),p[i].getJ_color(),p[i].getJ_bt(),p[i].getJ_at(),p[i].getJ_wt(),p[i].getJ_tat()));
              flag = false;
          }
          if(flag==false)
              data.add(new process(p[i].getJ_id(),p[i].getJ_color(),p[i].getJ_bt(),p[i].getJ_at(),p[i].getJ_wt(),p[i].getJ_tat()));
        }
        
        //..AVERAGE WAITING TIME................
        avgWt =  sum / len.getProcess_no();
        avgTat = sum_tat / len.getProcess_no();
        
      if (avgWt<1)
        {
            avgWt = 0;
        }
        
          
  final float avgwt1 = avgWt;
  final float avgtat1 = avgTat;
  
       Platform.runLater(() -> avg.setText(String.valueOf(avgtat1)));  
       Platform.runLater(() -> lblcp.setText(String.valueOf(avgwt1)));  
   
       return null;
            }
      };

// Run the task in a background thread
Thread backgroundThread = new Thread(task1);
// Terminate the running thread if the application exits
backgroundThread.setDaemon(true);

// Start the thread
backgroundThread.start();
 
   }
    
   
   //....IMPLEMENTING SJF OR SRT.........
    private void imp_sjf_srt()
    {
        Task task1 = new Task<Void>() {
            @Override public Void call() throws Exception{

               ObservableList<process> data = j_tbl.getItems();
    
        System.out.println("IN SRT............");
        int total_wt=0;
        float avg_wt;
        int total_tat=0;
        float avg_tat;
        process temp;
      //  int curr = -1;
       // int p_in_queue = 0;
        int total_p = p[0].getProcess_no();
       // int total_p_halt=0;
       // int total_p_done=0;
       // int cpu_time=0;
        //boolean cpu_working = false;
        
       
        
        System.out.println("curr= "+ p[0].curr);
        System.out.println("total p ="+p[0].getProcess_no());
        System.out.println("total p done="+ p[0].total_p_done);
         
           //Step1 : LOOP TILL PROCESS DONE
        while (p[0].total_p_done != p[0].getProcess_no())
        {
            //STEP1.2 : IF ALREADY PROCESS RUNNING THEN CHANGE LT N PT
            if (p[0].curr != -1)
            {
                int pt=p[p[0].curr].getJ_pt() + 1;
                p[p[0].curr].setJ_pt(pt);
               
                int lt =p[p[0].curr].getJ_lt() - 1;
                p[p[0].curr].setJ_lt(lt);
                
                //for making gantt chart
                 try {
                       Rectangle r=new Rectangle(20,50,Paint.valueOf(p[p[0].curr].getJ_color()));
                    fhb.setSpacing(5);
              
                    Platform.runLater(() -> fhb.getChildren().addAll(r));
               
                    Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }     
                
                    
            }
            
            //STEP 1.3 : CHECK FOR NEW PROCESS AND PUT IN QUEUE WITH HALT STATUS...
           for (int i=0;i<p[0].getProcess_no() ; i++)
           {
               
               if (p[i].getJ_at() == p[0].cpu_time)
               {
                   System.out.println("getting in queue new process="+p[i].getJ_id());
                   
                //   p[0].curr = i;
                   p[i].setJ_pt(0);
                   p[i].setJ_lt( (int)(p[i].getJ_bt()) );
                   p[i].setJ_status("halt");
                   p[i].setJ_wt(0);
                   
                 //  System.out.println("pt ="+  p[i].getJ_pt());
                //  System.out.println("lt ="+  p[i].getJ_lt());
                //   System.out.println("st ="+  p[i].getJ_status());
               //     System.out.println("wt ="+  p[i].getJ_wt());
                   p[0].p_in_queue ++;
                   p[0].total_p_halt++;    
                   
                   System.out.println("IN IT THE CHECK OF TYPE="+process_type);
                 //*******>>.STEP FOR PEEMPTIVE PURPOSE....IF ITS SRT------  
                    if(process_type=="srt"){
                        
                   if (p[0].curr !=-1)
                   {
                       if (p[i].getJ_lt() < p[p[0].curr].getJ_lt())
                       {
                           p[p[0].curr].setJ_status("halt");
                           p[0].curr = i;
                           
                           p[p[0].curr].setJ_st(p[0].cpu_time);
                           p[p[0].curr].setJ_status("work");
                       }
                   }
                    }
                   //...............................
               
               }
               
           }
           
           
           //STEP 1.4 : CPU WORKING --> CHECK FOR CURR PROCESS FINISHED / NOT....
           if (p[0].cpu_working)
           {
               
               System.out.println("checking if process finished");
               if (p[p[0].curr].getJ_lt() == 0)
               {
                   p[p[0].curr].setJ_status("done");
                   p[0].total_p_done ++;
                   p[0].cpu_working = false;
                   p[0].p_in_queue --;
                   
                   p[p[0].curr].setJ_tat(p[0].cpu_time - (int)(p[p[0].curr].getJ_at()));
                   
                   p[p[0].curr].setJ_wt( p[p[0].curr].getJ_tat() - p[p[0].curr].getJ_bt() );
                   System.out.println("......FINISHED.......");
                   System.out.println("process id="+p[p[0].curr].getJ_id()+" done");
                   System.out.println("its wt="+p[p[0].curr].getJ_wt());
                  System.out.println("TAT="+ p[p[0].curr].getJ_tat());
                   System.out.println("---------------------------");
                   
                   total_wt += p[p[0].curr].getJ_wt();
                   total_tat += p[p[0].curr].getJ_tat();
                   p[0].curr=-1;
                   
      
               }
           }
           
           //initialize T TO -1;
           int T = -1;
           
           //STEP 1.5 : CPU IDLE --> INITIATE A PROCESS IF THEIR IN QUEUE
           if (!p[0].cpu_working)
           {
              
               if (p[0].total_p_halt == p[0].p_in_queue)
               {
                   System.out.println("yes...total halt==total in queue");
                   //STEP 1.5.1 :
                   for (int i=0; i<p[0].getProcess_no() ; i++)
                   {
                          if (p[i].getJ_status().equals("not"))
                             { 
                                 System.out.println("pid with not status ="+p[i].getJ_id());
                                 continue;
                             }
                           if (p[i].getJ_status().equals("done"))
                             { 
                                 System.out.println("pid with done status ="+p[i].getJ_id());
                                 continue;
                             }
                          
                          
                           if (T== -1)
                           { T=i;  }
                           
                           System.out.println("T ="+T);
                           if (T==i)
                           {}
                           
                           else if (p[i].getJ_bt() < p[T].getJ_bt() )
                           {
                               T = i;
                           }
                           else if (p[i].getJ_bt() == p[T].getJ_bt() )
                           {
                               if ( p[i].getJ_at() < p[T].getJ_at() )
                               {
                                   T=i;
                               }
                           }
                               
                       }
                   
                   // STEP 1.5.2 :..
                   if (T == -1)
                   {
                       System.out.println("got no process.. no halt proc all not process");
                       p[0].cpu_working = false;
                       p[0].curr = -1;
                   
                   }
                   else
                   {
                       System.out.println("got process of index="+T+" of id="+p[T].getJ_id());
                       
                       p[T].setJ_status("work");
                       p[T].setJ_st(p[0].cpu_time);
                       p[0].curr = T;
                       p[0].cpu_working = true;
                       p[0].total_p_halt--;
                       
                   }
               
               }
               
               
           }
           
           
           //..INCREMENTING CPU TIME.....
           System.out.println("curr= "+ p[0].curr);
         //  System.out.println("left time= "+ p[p[0].curr].getJ_lt());
         //  System.out.println("proceed time :-"+ p[p[0].curr].getJ_pt());
         //  System.out.println("status = "+ p[p[0].curr].getJ_status());
          //  System.out.println("cpu time = "+ p[0].cpu_time);
          //      System.out.println("cpu worke = "+ p[0].cpu_working);
          //   System.out.println("total proces in queue = "+ p[0].p_in_queue);
           //  System.out.println("total process halt = "+ p[0].total_p_halt);
          //   System.out.println("total process done= "+ p[0].total_p_done);
           
           
           
            //..INCREMENTING CPU TIME.....
             p[0].cpu_time++;
                
        }
    //...ALL PROCESSES SUCCESSFULLY BEEN PROCEEDED..........LOOP END..............
        
        
        //..SHOWING OF EACH PROCESS WT , ST AND TAT........and also AVG.WT N TAT..
           for (int i=0;i<p[0].getProcess_no() ; i++)
           {
              System.out.println("----------------------");
                System.out.println("PID="+p[i].getJ_id());
              System.out.println("P_ST="+p[i].getJ_st());
             System.out.println("P_WT="+p[i].getJ_wt());
             System.out.println("P_TAT="+p[i].getJ_tat());
             
                                  //..ADDING ROW IN TABLEVIEW.....
                if(flag==true)
                {
                    j_tbl.getItems().clear();
            //  data.add(new process(p[i].getJ_id(),p[i].getJ_color(),p[i].getJ_bt(),p[i].getJ_at(),p[i].getJ_wt(),p[i].getJ_tat()));
                    flag = false;
                }
                if(flag==false)
                {
                    data.add(new process(p[i].getJ_id(),p[i].getJ_color(),p[i].getJ_bt(),p[i].getJ_at(),p[i].getJ_wt(),p[i].getJ_tat()));
                }

            
            }
        
                 avg_wt = total_wt / p[0].getProcess_no();
                avg_tat = total_tat / p[0].getProcess_no();
        
//                if (avg_wt<1) { avg_wt=0; }
                
             System.out.println("-----------------..");
             System.out.println("AVG WT="+ avg_wt);
             System.out.println("AVG TAT="+ avg_tat);
       
                final float avgwt1= avg_wt;
                final float avgtat1= avg_tat;
       
       Platform.runLater(() -> avg.setText(String.valueOf(avgtat1)));  
       Platform.runLater(() -> lblcp.setText(String.valueOf(avgwt1)));  
   
                return null;
            }
      };
   // Run the task in a background thread
Thread backgroundThread = new Thread(task1);
// Terminate the running thread if the application exits
backgroundThread.setDaemon(true);

// Start the thread
backgroundThread.start();
     
        
    } //....EVENT HANDLER ENDS HERRE...................................................
  
    
    
 
        
    
@Override
    public void initialize(URL url, ResourceBundle rb) {     
  //todo
       
    }    
    
    
    //..useD BY STARTP EVENT.....FOR THE SAMLL WINDOW ASKING NO OF PROCESS MANIPULATION......
    
       private void setValue(TextField txt1,Button btnexit,Button btn1) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         process pno = new process();  
         pno.setProcess_no(Integer.parseInt(txt1.getText()));
        
         btnexit.setDisable(false); 
         btn1.setDisable(true);
         txt1.setDisable(true);
         
        //stage.setOpacity(0.5);
         
    }
      
     //.................................................. 
}
