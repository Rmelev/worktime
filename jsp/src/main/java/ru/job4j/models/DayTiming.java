package ru.job4j.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Entity
@Table(name = "records")
public class DayTiming {
    /**
     * id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    /**
     * come to work time.
     */
    @Column(name = "come")
    private Timestamp comeIn;

    @JsonIgnore
    @OneToMany(mappedBy = "record", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<Breaks> breaks = new CopyOnWriteArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "status")
    private boolean status;

    /**
     * go away time.
     */
    @Column(name = "away")
    private Timestamp goAway;

    @Column(name = "totaltime")
    private Time totaltime;

    @Column(name = "fordate")
    private int fordate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getComeIn() {
        return comeIn;
    }

    public void setComeIn(Timestamp comeIn) {
        this.comeIn = comeIn;
    }

    public List<Breaks> getBreaks() {
        return breaks;
    }

    public void setBreaks(List<Breaks> breaks) {
        this.breaks = breaks;
    }

    public Timestamp getGoAway() {
        return goAway;
    }

    public void setGoAway(Timestamp goAway) {
        this.goAway = goAway;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Time getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(Time totaltime) {
        this.totaltime = totaltime;
    }

    public int getFordate() {
        return fordate;
    }

    public void setFordate(int fordate) {
        this.fordate = fordate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DayTiming dayTiming = (DayTiming) o;
        return id == dayTiming.id
                && Objects.equals(comeIn, dayTiming.comeIn)
                && Objects.equals(breaks, dayTiming.breaks)
                && Objects.equals(goAway, dayTiming.goAway);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comeIn, breaks, goAway);
    }

    @Override
    public String toString() {
        return "DayTiming{"
                + "id=" + id
                + ", comeIn=" + comeIn
                + ", breaks=" + breaks
                + ", user=" + user
                + ", status=" + status
                + ", goAway=" + goAway
                + '}';
    }
}
