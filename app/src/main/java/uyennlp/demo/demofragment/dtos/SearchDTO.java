package uyennlp.demo.demofragment.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SearchDTO implements Serializable {
    private Date date;
    private List<Integer> idList;

    public SearchDTO() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }
}
