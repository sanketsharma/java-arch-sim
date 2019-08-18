package com.ssharma;

import lombok.Data;

@Data
public class Bus {
    private int bits; // All bits including the ones we don't care about
    private int relevantLSBs; //Number of relevant lowest significant bits
}
