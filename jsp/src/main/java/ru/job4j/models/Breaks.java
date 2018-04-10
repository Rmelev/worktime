package ru.job4j.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "breaks")
public class Breaks {
    /**
     * id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id")
    private DayTiming record;

    @Column(name = "out")
    private Timestamp cameOut;

    @Column(name = "comein")
    private Timestamp returned;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DayTiming getRecord() {
        return record;
    }

    public void setRecord(DayTiming record) {
        this.record = record;
    }

    public Timestamp getCameOut() {
        return cameOut;
    }

    public void setCameOut(Timestamp cameOut) {
        this.cameOut = cameOut;
    }

    public Timestamp getReturned() {
        return returned;
    }

    public void setReturned(Timestamp returned) {
        this.returned = returned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Breaks breaks = (Breaks) o;
        return id == breaks.id
                && Objects.equals(record, breaks.record)
                && Objects.equals(cameOut, breaks.cameOut)
                && Objects.equals(returned, breaks.returned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, record, cameOut, returned);
    }

    @Override
    public String toString() {
        return "Breaks{"
                + "id=" + id
                + ", record id=" + record.getId()
                + ", cameOut=" + cameOut
                + ", returned=" + returned
                + '}';
    }
}
