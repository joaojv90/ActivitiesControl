package com.cybertools.servlet;

//@author jpjar
import com.cybertools.controller.Controller;
import com.cybertools.model.ActivitiesModel;
import com.google.gson.*;
import java.io.*;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ControlS", urlPatterns = "/ControlS")
@MultipartConfig

public class ControlServlet extends HttpServlet {
    
    private static final Gson objGson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").create();

    //Obtiene datos del servidor
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        List<ActivitiesModel> list = new Controller().read();
        String data = objGson.toJson(list);        
        out.write(data);
    }

    //Crea datos en el server
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String formData = objGson.toJson(request.getParameterMap());
        System.out.println(formData);
        formData = formData.replaceAll("[\\[\\]]", "");
        ActivitiesModel actM = objGson.fromJson(formData, ActivitiesModel.class);
        boolean create = new Controller().create(actM);
        if(create){
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            response.sendError(HttpServletResponse.SC_CONFLICT);
        }
    }

    //Actualiza datos del server
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String formData = objGson.toJson(request.getParameterMap());
        formData = formData.replaceAll("[\\[\\]]", "");
        ActivitiesModel actM = objGson.fromJson(formData, ActivitiesModel.class);
        boolean create = new Controller().update(actM);
        if(create){
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            response.sendError(HttpServletResponse.SC_CONFLICT);
        }
    }

    //Elimina datos del server
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String formData = objGson.toJson(request.getParameterMap());
        formData = formData.replaceAll("[\\[\\]]", "");
        ActivitiesModel actM = objGson.fromJson(formData, ActivitiesModel.class);
        boolean create = new Controller().delete(actM);
        if(create){
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            response.sendError(HttpServletResponse.SC_CONFLICT);
        }
    }

}
