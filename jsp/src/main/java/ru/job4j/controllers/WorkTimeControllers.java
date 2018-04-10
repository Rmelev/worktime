package ru.job4j.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.job4j.models.Breaks;
import ru.job4j.models.DayTiming;
import ru.job4j.models.User;
import ru.job4j.repository.BreaksDAO;
import ru.job4j.repository.DayTimingDAO;
import ru.job4j.repository.RoleDAO;
import ru.job4j.repository.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Controller
public class WorkTimeControllers {

    private static final Logger LOG = LoggerFactory.getLogger(WorkTimeControllers.class);

    private final UserDAO userDAO;

    private final RoleDAO roleDAO;

    private final DayTimingDAO dayTimingDAO;

    private final BreaksDAO breaksDAO;

    @Autowired
    public WorkTimeControllers(UserDAO userDAO, RoleDAO roleDAO, DayTimingDAO dayTimingDAO, BreaksDAO breaksDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.dayTimingDAO = dayTimingDAO;
        this.breaksDAO = breaksDAO;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showRecords(HttpSession session) throws IOException {
        return "index";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPanel(ModelMap model) throws IOException {
        List<DayTiming> list = (List<DayTiming>) dayTimingDAO.findAll();
        model.addAttribute("orders", list);
        return "admin";
    }

    @Transactional
    @GetMapping(value = "/come")
    public String comeFix(HttpSession session) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userDAO.getByLogin(name);
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DATE);
        DayTiming dayTiming = dayTimingDAO.getByUserAndFordate(user, today);

        if (dayTiming == null) {
            dayTiming = new DayTiming();
            dayTiming.setUser(user);
            dayTiming.setComeIn(new Timestamp(System.currentTimeMillis()));
            dayTimingDAO.save(dayTiming);
            dayTiming.setStatus(true);
            dayTiming.setFordate(today);
            session.setAttribute("record", dayTiming);
        } else {
            int id = (int) session.getAttribute("curBreakId");
            Breaks breaks = breaksDAO.findById(id);
            if (breaks != null) {
                breaks.setReturned(new Timestamp(System.currentTimeMillis()));
                dayTiming.setStatus(true);
            }
        }

//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValueAsString("Вы отметились");
        return "index";
    }

    @Transactional
    @GetMapping(value = "/away")
    public String awayFix(HttpSession session) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userDAO.getByLogin(name);
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DATE);
        DayTiming dayTiming = dayTimingDAO.getByUserAndFordate(user, today);
        Breaks breaks = new Breaks();
        breaks.setCameOut(new Timestamp(System.currentTimeMillis()));
        DayTiming record = (DayTiming) session.getAttribute("record");
        breaks.setRecord(record);
        breaksDAO.save(breaks);
        int id = breaks.getId();
        //оно нужно?
        session.setAttribute("curBreakId", id);
        dayTiming.setStatus(false);
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValueAsString("Вы вышли");
        return "index";
    }

    @SuppressWarnings("deprecation")
    @Transactional
    @GetMapping(value = "/finish")
    public String finishFix() throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userDAO.getByLogin(name);
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DATE);
        DayTiming dayTiming = dayTimingDAO.getByUserAndFordate(user, today);
        int recordId = dayTiming.getId();
        dayTiming.setGoAway(new Timestamp(System.currentTimeMillis()));
        dayTiming.setStatus(false);
        List<Breaks> breaksList = breaksDAO.findAllByRecordId(recordId);
        long sum = 0;
        for (Breaks next : breaksList) {
            long finish = next.getReturned().getTime();
            long start = next.getCameOut().getTime();
            long timeSegment = finish - start;
            sum = sum + timeSegment;
        }

        Time time = new Time(sum);
        int hours = time.getHours();
        time.setHours(hours - 3);
        dayTiming.setTotaltime(time);

        //не нужно, потому что это сбивает время расчета. У него уже закрыт отрезок ушел/пришел.
//        breaks.setCameOut(new Timestamp(System.currentTimeMillis()));

//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValueAsString("Вы закончили работу на сегодня");
        return "index";
    }
}
