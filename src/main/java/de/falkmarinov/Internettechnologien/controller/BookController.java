package de.falkmarinov.Internettechnologien.controller;

import de.falkmarinov.Internettechnologien.config.ThymeleafConfig;
import de.falkmarinov.Internettechnologien.model.Book;
import de.falkmarinov.Internettechnologien.model.Category;
import de.falkmarinov.Internettechnologien.service.BookService;
import de.falkmarinov.Internettechnologien.service.CategoryService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookController", value = "/book")
public class BookController extends HttpServlet {

    @Inject
    private CategoryService categoryService;

    @Inject
    private BookService bookService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        WebContext context = new WebContext(request, response, request.getServletContext());
        TemplateEngine engine = (TemplateEngine) request.getServletContext().getAttribute(ThymeleafConfig.TEMPLATE_ENGINE_ATTR);

        context.setVariable("categories", categoryService.getAllCategories());
        context.setVariable("books", bookService.getAllBooks());

        response.setCharacterEncoding("UTF-8");

        engine.process("book.html", context, response.getWriter());
    }
}
