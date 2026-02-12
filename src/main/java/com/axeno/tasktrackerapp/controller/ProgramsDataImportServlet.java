package com.axeno.tasktrackerapp.controller;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@MultipartConfig
@WebServlet("/import")
public class DataImportServlet extends HttpServlet
{
    public void doPost(HttpServletRequest request , HttpServletResponse response)
    {
        Part part = request.getPart();
    }
}
