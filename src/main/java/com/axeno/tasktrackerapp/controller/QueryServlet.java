package com.axeno.tasktrackerapp.controller;

import com.axeno.tasktrackerapp.model.Program;
import com.axeno.tasktrackerapp.model.Project;
import com.axeno.tasktrackerapp.service.QueryService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/query/*")
public class QueryServlet extends HttpServlet {

    private final QueryService queryService = new QueryService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
       response.setContentType("application/json");


        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing endpoint");
                return;
            }

            // /api/query/programs?userId=...
            if (pathInfo.equals("/programs")) {
                String userId = request.getParameter("userId");
                if (userId == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing userId parameter");
                    return;
                }
                List<Program> programs = queryService.getProgramsForIndividual(userId);
                response.getWriter().write(gson.toJson(programs));
                return;
            }

            // /api/query/projects?userId=...
            if (pathInfo.equals("/projects")) {
                String userId = request.getParameter("userId");
                if (userId == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing userId parameter");
                    return;
                }
                List<Project> projects = queryService.getProjectsForIndividual(userId);
                response.getWriter().write(gson.toJson(projects));
                return;
            }

            // /api/query/tasks/pending/count?userId=...&programId=...
            if (pathInfo.equals("/tasks/pending/count")) {
                String userId = request.getParameter("userId");
                String programId = request.getParameter("programId");
                String projectId = request.getParameter("projectId");

                long count = 0;
                if (userId != null && programId != null) {
                    count = queryService.getTotalPendingTasksForIndividualInProgram(userId, programId);
                } else if (userId != null) {
                    count = queryService.getTotalPendingTasksForIndividual(userId);
                } else if (projectId != null) {
                    count = queryService.getTotalPendingTasksForProject(projectId);
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters for pending tasks count");
                    return;
                }
                response.getWriter().write("{\"count\": " + count + "}");
                return;
            }

            // /api/query/programs/name?projectId=...
             if (pathInfo.equals("/programs/name")) {
                String projectId = request.getParameter("projectId");
                if (projectId == null) {
                     response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing projectId parameter");
                     return;
                }
                String programName = queryService.getProgramNameForProject(projectId);
                if (programName == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Program not found for project");
                    return;
                }
                response.getWriter().write("{\"programName\": \"" + programName + "\"}");
                return;
            }

            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Endpoint not found");

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
