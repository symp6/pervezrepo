
package Controller;

import Beans.Actor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


@WebServlet(name = "ActorServlet", urlPatterns = {"/ActorServlet"})
public class ActorServlet extends HttpServlet {
     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchname = request.getParameter("hero");
        String operation = request.getParameter("operation");
      String actrname = request.getParameter("actrname");
      String bestfilm = request.getParameter("bestfilm");
      String home = request.getParameter("home");  
       Actor a = new Actor(); PrintWriter pw = response.getWriter();
       Crud ch = new  Crud();
       String an="p"; 
       int i = 0;
     if(operation.equalsIgnoreCase("insert") ){  
        try {
            an = ch.checkunique(actrname);
           
       } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(ActorServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
       
      if(an.equalsIgnoreCase("true") || i==0){
    i++;
      a.setActorname(actrname);
      a.setBestfilm(bestfilm);
      a.setHome(home);
       i++;
       
    Configuration cfg = new Configuration();  
    cfg.configure("Resource/hibernate.cfg.xml");
     SessionFactory sf = cfg.buildSessionFactory();
      Session s = sf.openSession();
    Transaction tr = s.beginTransaction();
      s.save(a);
      tr.commit();
      s.close();
     HttpSession  sess = request.getSession();
      sess.setAttribute("actrname", actrname);
      sess.setAttribute("home", home);
      sess.setAttribute("bestfilm", bestfilm);
      response.sendRedirect("welcome.jsp");
       }
     else{
            pw.print("the ActorName u have Entered is already Exist");
            
               }
     }
     else if(operation.equalsIgnoreCase("allrecord")){
                      
            try {
                ArrayList<Actor> list = new ArrayList<>();
                list = ch.allrecord();
             
                request.setAttribute("list", list);
                RequestDispatcher rd = request.getRequestDispatcher("newpage.jsp");
                rd.forward(request,response);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ActorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
         
     }   
     else{
        
              try {
                 Actor a2 = new Actor();
                a2 = ch.SeeAll(searchname);
               
                  // request.setAttribute("rubel", a2);
     //  RequestDispatcher rd = request.getRequestDispatcher("showActorDetail.jsp");
        // rd.forward(request,response);
                
           HttpSession session = request.getSession();
              
             session.setAttribute("rubel",a2); 
            
             RequestDispatcher rd = request.getRequestDispatcher("showActorDetail.jsp");
               rd.forward(request,response); 
              
              
              } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ActorServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
               
         
         
     }
    
    
    }
    
}
