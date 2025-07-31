package com.hotel.servlet;

import com.alibaba.fastjson.JSON;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@WebServlet("/roomFileUploadServlet")
public class RoomFileUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
        servletFileUpload.setHeaderEncoding("UTF-8");

        List<FileItem> items = new ArrayList<>();
        try {
            items = servletFileUpload.parseRequest(req);

            for (FileItem item : items) {
                if(!item.isFormField()) {
                    String fileName = item.getName();
                    String rootString = req.getSession().getServletContext().getRealPath("/uploads");
                    File rootPath = new File(rootString);
                    if(!rootPath.exists()) rootPath.mkdirs();

                    String newFileName = UUID.randomUUID().toString().replace("-", "") + fileName;
                    File rootFilePath = new File(rootPath + File.separator + newFileName);
                    item.write(rootFilePath);

                    Map<String, String> map = new HashMap<>();
                    map.put("message", "success");
                    map.put("imageURL", "/uploads/" + newFileName);
                    resp.getWriter().write(JSON.toJSONString(map));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //resp.getWriter().write(JSON.toJSONString(Map.of("message", "fail", "error", e.getMessage())));
            HashMap<String,Object> map=new HashMap<>();
            map.put("message", "fail");
            map.put("error", e.getMessage());
            resp.getWriter().write(JSON.toJSONString(map));
            return;
        }
    }
}