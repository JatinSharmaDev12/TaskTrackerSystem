package com.axeno.tasktrackerapp.controller;

import com.axeno.tasktrackerapp.exception.ValidationException;
import com.axeno.tasktrackerapp.service.ImportService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@MultipartConfig()
@WebServlet("/api/import")
public class ProgramsDataImportServlet extends HttpServlet {

    private final ImportService importService = new ImportService();



    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        try {
            Part filePart = validateAndGetFilePart(request);

            File savedFile = saveFile(request, filePart);

            importService.importData(savedFile);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\":\"File uploaded and data imported successfully.\"}");

        } catch (ValidationException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Internal server error.\"}");
        }
    }



    /*
     * Validate multipart and return file part
     */
    private Part validateAndGetFilePart(HttpServletRequest request)
            throws IOException, ServletException {

        Part part = request.getPart("programsData");

        if (part == null || part.getSize() == 0) {
            throw new ValidationException("No file uploaded.");
        }

        if (!"application/json".equals(part.getContentType())) {
            throw new ValidationException("Only JSON files are allowed.");
        }

        return part;
    }

    /*
     * Save file to server
     */
    private File saveFile(HttpServletRequest request, Part part)
            throws IOException {

        String fileName = part.getSubmittedFileName();

        File uploadDir = new File(getServletContext().getRealPath(""));

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        File file = new File(uploadDir, fileName);

        part.write(file.getAbsolutePath());

        return file;
    }

}
