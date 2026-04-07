package br.com.cortaai.client.interfaces;

import java.time.LocalTime;

public interface HasOpenCloseTime {
    LocalTime hrOpensAt();
    LocalTime hrClosesAt();
}
