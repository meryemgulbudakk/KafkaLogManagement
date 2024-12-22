package com.kafka.logmanagement.logs.controller;

import com.kafka.logmanagement.logs.model.Log;
import com.kafka.logmanagement.logs.exception.LogNotFoundException;
import com.kafka.logmanagement.logs.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller for handling log-related web requests.
 * Provides methods for displaying, creating, editing, and deleting logs.
 */
@Controller
public class LogController {

    @Autowired
    private LogService service;

    /**
     * Displays the list of logs.
     *
     * @param model the model to add attributes to
     * @return the name of the view to render
     */
    @GetMapping("/api/logs")
    public String showLogsList(Model model) {
        List<Log> listLogs = service.listAll();
        model.addAttribute("listLogs", listLogs);
        return "logs";
    }

    /**
     * Displays the form for creating a new log.
     *
     * @param model the model to add attributes to
     * @return the name of the view to render
     */
    @GetMapping("/api/logs/new")
    public String showLogForm(Model model) {
        model.addAttribute("log", new Log());
        model.addAttribute("pageTitle", "Add New Log");
        return "log_form";
    }

    /**
     * Saves a new or updated log entry.
     *
     * @param log       the log entry to save
     * @param redirectA the redirect attributes to add flash attributes to
     * @return the redirect URL
     */
    @PostMapping("/api/logs/save")
    public String saveLog(Log log, RedirectAttributes redirectA) {
        service.save(log);
        redirectA.addFlashAttribute("message", "Log added successfully");
        return "redirect:/api/logs";
    }

    /**
     * Displays the form for editing an existing log.
     *
     * @param id        the ID of the log to edit
     * @param model     the model to add attributes to
     * @param redirectA the redirect attributes to add flash attributes to
     * @return the name of the view to render or redirect URL if not found
     */
    @GetMapping("/api/logs/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectA) {
        try {
            Log log = service.get(id);
            model.addAttribute("log", log);
            model.addAttribute("pageTitle", "Edit Log (ID: " + id + ")");
            return "log_form";
        } catch (LogNotFoundException e) {
            redirectA.addFlashAttribute("message", e.getMessage());
            return "redirect:/api/logs";
        }
    }

    /**
     * Deletes a log entry by its ID.
     *
     * @param id        the ID of the log to delete
     * @param redirectA the redirect attributes to add flash attributes to
     * @return the redirect URL
     */
    @GetMapping("/api/logs/delete/{id}")
    public String deleteLog(@PathVariable("id") Integer id, RedirectAttributes redirectA) {
        try {
            service.delete(id);
            redirectA.addFlashAttribute("message", "The log ID: " + id + " has been deleted successfully");
        } catch (LogNotFoundException e) {
            redirectA.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/api/logs";
    }

    @GetMapping("/api/logs/filter")
    public String showFilter(Model model) {
        model.addAttribute("pageTitle", "Filters");
        return "logs_filters";
    }

    @GetMapping("/api/logs/filtering")
    public String showFiltering(
            @RequestParam(value = "timestamp", required = false) String timestamp,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "topic", required = false) String topic,
            @RequestParam(value = "partition", required = false) Integer partition,
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "server", required = false) String server,
            Model model
    ) {
        // Servis katmanındaki dinamik filtreleme metoduna parametreleri geçiriyoruz.
        List<Log> listLogs = service.findLogsByDynamicFilter(timestamp, status, topic, partition, offset, key, userId, server);

        // Filtrelenmiş sonuçları modele ekliyoruz.
        model.addAttribute("listLogs", listLogs);

        return "redirect:/api/logs";
    }

}
