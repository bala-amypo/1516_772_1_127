package com.example.demo.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;

import java.io.IOException;

public class SimpleEchoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/plain");

        String name = req.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            resp.getWriter().write("Hello, Guest");
        } else {
            resp.getWriter().write("Hello, " + name.trim());
        }
    }
}
