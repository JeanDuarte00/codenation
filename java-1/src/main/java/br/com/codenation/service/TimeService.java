package br.com.codenation.service;

import br.com.codenation.ententies.Time;

import java.util.ArrayList;
import java.util.List;

public class TimeService {


    List<Time> listaTime = new ArrayList();

    public TimeService () {

    }

    public void incluir (Time time) {
        this.listaTime.add(time);
    }

    public List<Time> getListaTime() {
        return listaTime;
    }

    public void setListaTime(List<Time> listaTime) {
        this.listaTime = listaTime;
    }
}
